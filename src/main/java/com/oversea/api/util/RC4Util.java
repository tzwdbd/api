package com.oversea.api.util;


public class RC4Util {
	
	public static byte[] decry_RC4(byte[] data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return RC4Base(data, key);
    }
   
   
    public static String decry_RC4(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return new String(RC4Base(HexString2Bytes(data), key));
    }
   
   
    public static byte[] encry_RC4_byte(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        byte b_data[] = data.getBytes();
        return RC4Base(b_data, key);
    }
   
   
    public static String encry_RC4_string(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return toHexString(asString(encry_RC4_byte(data, key)));
    }
   
   
    private static String asString(byte[] buf) {
        StringBuffer strbuf = new StringBuffer(buf.length);
        for (int i = 0; i < buf.length; i++) {
            strbuf.append((char) buf[i]);
        }
        return strbuf.toString();
    }
   

    private static byte[] initKey(String aKey) {
        byte[] b_key = aKey.getBytes();
        byte state[] = new byte[256];

        for (int i = 0; i < 256; i++) {
            state[i] = (byte) i;
        }
        int index1 = 0;
        int index2 = 0;
        if (b_key == null || b_key.length == 0) {
            return null;
        }
        for (int i = 0; i < 256; i++) {
            index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
            byte tmp = state[i];
            state[i] = state[index2];
            state[index2] = tmp;
            index1 = (index1 + 1) % b_key.length;
        }
        return state;
    }
   
    private static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch & 0xFF);
            if (s4.length() == 1) {
                s4 = '0' + s4;
            }
            str = str + s4;
        }
        return str;// 0x表示十六进制
    }
   
   
    private static byte[] HexString2Bytes(String src) {
        int size = src.length();
        byte[] ret = new byte[size / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < size / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }
   
    private static byte uniteBytes(byte src0, byte src1) {
        char _b0 = (char)Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (char) (_b0 << 4);
        char _b1 = (char)Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }
   
    public static byte[] RC4Base (byte [] input, String mKkey) {
    	int[] s = new int[256],t = new int[256];
        int length = input.length;
        char[] k = mKkey.toCharArray();
        byte[] plaintext = new byte[length];
        
        for(int i=0;i<256;i++)//////////////给字节状态矢量和可变长的密钥数组赋值
        {
            s[i]=i;
            t[i]=k[i % k.length];
        }
        
        int j=0;
        for(int i=0;i<256;i++) //////使用可变长的密钥数组初始化字节状态矢量数组s
        {
            int temp;
            j=(j+s[i]+t[i])%256;
            temp=s[i];
            s[i]=s[j];
            s[j]=temp;
        }
        
        int m,n,q;
        int[] key = new int[length];
        m=n=0;
        int i;
        for(i=0;i<length;i++)/////////////由字节状态矢量数组变换生成密钥流并对密文字符进行解密
        {
            int temp;
            m=(m+1)% 256;
            n=(n+s[n])% 256;
            temp=s[m];
            s[m]=s[n];
            s[n]=temp;
            
            q=(s[m]+s[n])%256;
            key[i]=s[q];
            plaintext[i]=(byte) (input[i]^key[i]);
        }
        
        return plaintext;
    }
}
