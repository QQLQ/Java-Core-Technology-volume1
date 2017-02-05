package com.qty.test;

import java.util.Arrays;

public class StringByteSwitch {

	public static void main(String[] args) {
		String testStr = "A0000000000000";
		byte[] bytes = stringToBytes(testStr, testStr.length());
		//System.out.println("bytes = " + Arrays.toString(bytes));
		String result = bytesToString(bytes, testStr.length());
		System.out.println("orign  = " + testStr);
		System.out.println("resutl = " + result.toUpperCase());
	}
	
	public static byte[] stringToBytes(String str, int length) {
		if (str == null || "".equals(str)) {
			return null;
		}
		int len = length / 2;
		if (length % 2 != 0) {
			len++;
		}
		//System.out.println("stringToBytes=>len: " + len);
		byte[] bytes = new byte[len];
		for (int i = 0, j = 0; i < str.length(); i+=2, j++) {
			if (i + 1 >= str.length()) {
				bytes[j] = (byte)str.charAt(i);
			} else {
				bytes[j] = mergeByte(str.charAt(i), str.charAt(i + 1));
			}
		}
		return bytes;
	}
	
	public static byte mergeByte(char first, char second) {
		int f = Integer.parseInt(String.valueOf(first), 16);
		int s = Integer.parseInt(String.valueOf(second), 16);
		int result = f << 4;
		//System.out.println("mergeByte=>first: " + Integer.toBinaryString(f) + " result: " + Integer.toBinaryString(result));
		result |= s;
		//System.out.println("mergeByte=>first: " + first + " second: " + second + " byte: " + Integer.toBinaryString(result));
		return (byte) (result & 0xFF);
	}
	
	public static String bytesToString(byte[] bytes, int length) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		byte[] result = new byte[length];
		for (int i = 0, j = 0; i < bytes.length; i++, j += 2) {
			if (j + 1 >= length) {
				result[j] = bytes[i];
			} else {
				result[j] = getHightByte(bytes[i]);
				result[j + 1] = getLowByte(bytes[i]);
			}
		}
		return new String(result);
	}
	
	public static byte getHightByte(byte b) {
		//System.out.println("getHightByte=>b: " + Integer.toBinaryString(b) + " result: " + Integer.toBinaryString(((b & 0xFF) >>> 4)));
		return Integer.toString(((b & 0xFF) >>> 4), 16).getBytes()[0];
	}
	
	public static byte getLowByte(byte b) {
		//System.out.println("getLowByte=>b: " + Integer.toBinaryString(b) + " result: " + Integer.toBinaryString(b & 0xF));
		return Integer.toString((b & 0xF), 16).getBytes()[0];
	}
}
