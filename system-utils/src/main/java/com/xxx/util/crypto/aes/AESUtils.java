package com.xxx.util.crypto.aes;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.xxx.util.crypto.base64.AESType;
import com.xxx.util.crypto.base64.Base64;

public class AESUtils
{
  private static String WAYS = "AES";
  private static String MODE = "";
  private static String IV = "1234567890123456";
  private static int AES_SIZE = 128;
  private static int pwdLength = 16;
  private static String defaultPwd = null;
  private static boolean isPwd = false;
  private static String ModeCode = "PKCS5Padding";

  private static int type = 0;

  public static String selectMod(int type)
  {
    switch (type) {
    case 0:
      isPwd = false;
      MODE = WAYS + "/" + AESType.ECB.key() + "/" + ModeCode;

      break;
    case 1:
      isPwd = true;
      MODE = WAYS + "/" + AESType.CBC.key() + "/" + ModeCode;
      break;
    case 2:
      isPwd = true;
      MODE = WAYS + "/" + AESType.CFB.key() + "/" + ModeCode;
      break;
    case 3:
      isPwd = true;
      MODE = WAYS + "/" + AESType.OFB.key() + "/" + ModeCode;
    }

    return MODE;
  }

  private static SecretKeySpec toKey(String password)
    throws UnsupportedEncodingException
  {
    try
    {
      KeyGenerator kgen = KeyGenerator.getInstance(WAYS);

      kgen.init(128, new SecureRandom(password.getBytes()));
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();

      System.out.println(new String(enCodeFormat).getBytes("UTF-8"));
      return new SecretKeySpec(enCodeFormat, WAYS);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String Encrypt(String content)
    throws Exception
  {
    SecretKeySpec skeySpec = toKey(defaultPwd);
    Cipher cipher = Cipher.getInstance(selectMod(type));
    IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
    if (!isPwd)
      cipher.init(1, skeySpec);
    else {
      cipher.init(1, skeySpec, iv);
    }

    byte[] encrypted = cipher.doFinal(content.getBytes());

    return Base64.encode(new String(encrypted));
  }

  public static String Decrypt(String content) throws Exception
  {
    try
    {
      SecretKeySpec skeySpec = toKey(defaultPwd);
      Cipher cipher = Cipher.getInstance(selectMod(type));
      IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
      if (!isPwd)
        cipher.init(2, skeySpec);
      else {
        cipher.init(2, skeySpec, iv);
      }

      byte[] encrypted1 = Base64.decode(content, "UTF-8").getBytes();
      try {
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
      } catch (Exception e) {
        System.out.println(e.toString());
        return null;
      }
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }return null;
  }

  @SuppressWarnings("unused")
private static String parseByte2HexStr(byte[] buf)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; i++) {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

  @SuppressWarnings("unused")
private static byte[] parseHexStr2Byte(String hexStr)
  {
    if (hexStr.length() < 1) {
      return null;
    }
    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; i++) {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 
        16);
      int low = Integer.parseInt(
        hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
      result[i] = (byte)(high * 16 + low);
    }
    return result;
  }

  public static void main(String[] args)
    throws Exception
  {
    isPwd = false;
    defaultPwd = "admin";
    String resultString = Encrypt("HelloWrod");

    System.out.println(resultString);
    System.out.println(Decrypt(resultString));
  }
}