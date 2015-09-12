package edu.msg.ticketmaster.backend.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncrypter {

	
	public static String generateHashedPassword(final String password) {
		String salt = "asae";
		String hashedPassword = "";
		byte[] initialBytes;
		try {
			initialBytes = (password + salt).getBytes("UTF-16");
			final MessageDigest algorithm = MessageDigest
					.getInstance("SHA1");
			algorithm.reset();
			algorithm.update(initialBytes);
			final byte[] hashedBytes = algorithm.digest();
			hashedPassword = new String(hashedBytes);
		} catch (final UnsupportedEncodingException e) {
			//LOG.error("Password encryption failed: unsupported encoding", e);
		} catch (final NoSuchAlgorithmException nsae) {
			//LOG.error("Password encryption failed: hashing algorithm not supported", nsae);
		}
		return hashedPassword;
	}
}
