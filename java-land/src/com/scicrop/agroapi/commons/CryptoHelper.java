package com.scicrop.agroapi.commons;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class CryptoHelper {

	private static CryptoHelper INSTANCE = null;

	public static CryptoHelper getInstance(){
		if(INSTANCE == null) INSTANCE = new CryptoHelper();
		return INSTANCE;
	}

	static final byte[] HEX_CHAR_TABLE = {
			(byte)'0', (byte)'1', (byte)'2', (byte)'3',
			(byte)'4', (byte)'5', (byte)'6', (byte)'7',
			(byte)'8', (byte)'9', (byte)'a', (byte)'b',
			(byte)'c', (byte)'d', (byte)'e', (byte)'f'
	};   

	public String byteToHexstr(byte[] source) throws UnsupportedEncodingException{

		byte[] hex = new byte[2 * source.length];
		int index = 0;

		for (byte b : source) {
			int v = b & 0xFF;
			hex[index++] = HEX_CHAR_TABLE[v >>> 4];
			hex[index++] = HEX_CHAR_TABLE[v & 0xF];
		}

		String result = null;


		result = new String(hex, "ASCII");


		return result;

	}

	public byte[] fileToByteArray(File inFile) throws IOException {
		Path path = inFile.toPath();
		byte[] bArray = null;
		bArray = Files.readAllBytes(path);
		return bArray;
	}

	public PrivateKey getRsaPrivateKey(String filename) throws Exception {

		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public PublicKey getRsaPublicKey(String filename) throws Exception {

		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

}
