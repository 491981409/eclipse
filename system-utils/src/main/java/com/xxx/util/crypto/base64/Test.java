package com.xxx.util.crypto.base64;

public class Test
{
  public static void main(String[] args)
  {
    String content ="bb" ;
    String skey = "aa";
    try
    {
      byte[] encryptResultStr = BackAES.encrypt(content, skey, 0);

      System.out.println("方法-加密后：" + new String(encryptResultStr));
      String decryptString = BackAES.decrypt(new String(encryptResultStr), 
        skey, 0);
      System.out.println("方法-解密后：" + decryptString);
      Shared sh = new Shared();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}