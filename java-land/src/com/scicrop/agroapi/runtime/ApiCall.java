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

package com.scicrop.agroapi.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.scicrop.agroapi.commons.UrlHelper;

public class ApiCall {

	static String url      = "http://127.0.0.1:8080/scicrop-engine-web/api/v1";//"https://engine.scicrop.com/scicrop-engine-web/api/v1";
	static String username = "";
	static String password = "";
	static String api_id   = "";

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("\n\nType your USERNAME:");
		username = br.readLine();
		System.out.println("\n\nType your PASSWORD:");
		password = br.readLine();
		System.out.println("\n\nPaste your API_ID:");
		api_id = br.readLine();
		testGET();
		testPOST();
		if(br != null) br.close();
	}

	private static void testPOST() throws Exception {

		String restPath = "/station/scicrop";

		String baseUrl = url + restPath;

		String json = "{\"authEntity\":{\"userEntity\":{\"email\":\""+username+"\",\"api_id\":\""+api_id+"\"}},\"payloadEntity\":{},\"actionEntityLst\":[],\"responseEntity\":{}}";

		json = UrlHelper.getInstance().getStringFromUrl(baseUrl, json, username, password, "POST");
		System.out.println("testPOST() RESPONSE: "+json);


	}

	private static void testGET() throws Exception {

		String restPath = "/status/my";

		String baseUrl = url + restPath;
		
		String json = UrlHelper.getInstance().getStringFromUrl(baseUrl, null, username, password, "GET");
		System.out.println("testGET() RESPONSE: "+json);

	}

}
