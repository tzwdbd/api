package com.oversea.api.encode;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;



public class ThreeDES {

	
	public static final byte[] keybyte = "6a97cccb651f6a02c6b9603b".getBytes();
    private static final String Algorithm = "DESede";  //定义 加密算法,可用 DES,DESede,Blowfish
    
    public ThreeDES(){
    	Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }
 
    // 加密字符串
    public static String encryptMode(byte[] src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return new sun.misc.BASE64Encoder().encode(c1.doFinal(src));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
 
    // 解密字符串
    public static String decryptMode(String src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            
            return new String(c1.doFinal(new BASE64Decoder().decodeBuffer(src)));
            
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
 
    public static void main(String[] args){ // 添加新安全算法,如果用JCE就要把它添加进去
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        String src="39555";
        String encoded = encryptMode(src.getBytes());
        System.out.println("加密后的字符串:" + encoded);
        
//        String encodeText="prd/z7IN1a8o26AutfbdR12C42gcg7t3";
        String srcBytes = decryptMode(encoded);
        System.out.println("解密后的字符串:" + srcBytes);
    }
	
}
