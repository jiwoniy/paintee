package com.paintee.common.util;

import java.util.UUID;

import org.junit.Test;

public class Sha512EncryptTest {

	@Test
	public void testhash() {
		String plainText = "1111";

		System.out.println("id:"+UUID.randomUUID().toString());
		System.out.println("password:"+Sha512Encrypt.hash(plainText));
	}
}