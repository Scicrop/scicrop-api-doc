package com.scicrop.agroapi.tests;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.junit.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.scicrop.agroapi.commons.CryptoHelper;
import com.scicrop.agroapi.commons.UrlHelper;

public class TestJWT {

	public String url      = "https://engine.scicrop.com/scicrop-engine-web/api/v1";

	@Test
	public void testJwt_HMAC256(){

		String token = null;

		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			token = JWT.create()
					.withIssuer("auth0")
					.sign(algorithm);

			System.out.println(token);

		} catch (UnsupportedEncodingException exception){
			exception.printStackTrace();
		} catch (JWTCreationException exception){
			exception.printStackTrace();
		}

		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer("auth0")
					.build(); //Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);

		} catch (UnsupportedEncodingException exception){
			exception.printStackTrace();
		} catch (JWTVerificationException exception){
			exception.printStackTrace();
		}
	}



	@Test
	public void testJwt_RSA256(){

		/*
		 * Generate a 2048-bit RSA private key

$ openssl genrsa -out private_key.pem 2048

Convert private Key to PKCS#8 format (so Java can read it)

$ openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem -out private_key.der -nocrypt

Output public key portion in DER format (so Java can read it)

$ openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der
		 */

		String token = null;

		PublicKey pubKey = null;
		PrivateKey privKey = null;
		try {
			pubKey = CryptoHelper.getInstance().getRsaPublicKey("/tmp/rsa/public_key.der");
			privKey = CryptoHelper.getInstance().getRsaPrivateKey("/tmp/rsa/private_key.der");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	
		RSAPublicKey publicKey = (RSAPublicKey) pubKey;
		RSAPrivateKey privateKey = (RSAPrivateKey) privKey;

		try {



			Algorithm algorithm = Algorithm.RSA256(null, privateKey);
			
			DecodedJWT a = JWT.decode("");
			
				
			token = JWT.create()
					.withIssuer("auth0")
					.sign(algorithm);

			System.out.println(token);

		} catch (Exception exception){
			exception.printStackTrace();
		}


		try {
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer("auth0")
					.build(); //Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
		} catch (JWTVerificationException exception){
			exception.printStackTrace();
		}

	}

	@Test
	public void testJwtIntegration() throws Exception {

		String api_id = "MYAPIID";
		String restPath = "/status/stats";
		String baseUrl = url + restPath;
		PrivateKey privKey = CryptoHelper.getInstance().getRsaPrivateKey("/tmp/rsa/private_key.der");
		RSAPrivateKey privateKey = (RSAPrivateKey) privKey;
		Algorithm algorithm = Algorithm.RSA256(null, privateKey);
		String token = JWT.create().withIssuer("https://engine.scicrop.com").withSubject(api_id).sign(algorithm);
		String json = UrlHelper.getInstance().getStringFromUrlJWT(baseUrl, null, token, "GET");
		System.out.println(json);

	}

}
