/*******************************************************************************
/* 																				
/* Copyright [2017]	Jose Ricardo de Oliveira Damico								
/* 					SciCrop Informacao e Tecnologia S.A.							
/* 					info@scicrop.com / https://scicrop.com						
/* 																				
/* Licensed under the Apache License, Version 2.0 (the "License");				
/* you may not use this file except in compliance with the License.				
/* You may obtain a copy of the License at										
/* 																				
/*     http://www.apache.org/licenses/LICENSE-2.0								
/* 																				
/* Unless required by applicable law or agreed to in writing, software			
/* distributed under the License is distributed on an "AS IS" BASIS,			
/* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.		
/* See the License for the specific language governing permissions and			
/* limitations under the License.												
/*																				
/********************************************************************************/

package com.scicrop.agroapi.commons;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class UrlHelper {

	private static UrlHelper INSTANCE = null;

	public static UrlHelper getInstance(){
		if(INSTANCE == null) INSTANCE = new UrlHelper();
		return INSTANCE;
	}


	public String getStringFromUrl(String baseUrl, String args, String username, String password, String method) throws Exception {
		
		System.out.println("\n\nConnecting to ["+method+"]: "+baseUrl);
		
		StringBuffer response = null;
		HttpURLConnection con = null;
		DataOutputStream wr = null;
		BufferedReader in = null;
		try{
			URL obj = new URL(baseUrl);
			con = (HttpURLConnection) obj.openConnection();

			String encoded = Base64.getEncoder().encodeToString((username+":"+password).getBytes());  
			con.setRequestProperty("Authorization", "Basic "+encoded);
			con.setRequestMethod(method);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);

			if(null != args && args.length() > 0){
				wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(args);
			}

			int responseCode = con.getResponseCode();

			if(responseCode == 200){

				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

			}else throw new Exception(String.valueOf(responseCode));

		}catch(IOException e){
			throw new Exception(e);
		}finally{

			if(in != null ) try { in.close(); } catch (IOException e) { throw new Exception(e); }
			if(wr != null ) try { wr.flush(); } catch (IOException e) { throw new Exception(e); }
			if(wr != null ) try { wr.close(); } catch (IOException e) { throw new Exception(e); }
			if(con != null) con.disconnect();

		}

		return (response.toString());

	}

}
