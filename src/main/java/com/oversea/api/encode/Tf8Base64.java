package com.oversea.api.encode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

public class Tf8Base64 {
	

	/**
	 * 64进制和10进制的转换类
	 * 
	 * @author Administrator
	 * 
	 */

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '=', '-', };
	
	private static final String base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

	private static final int[] base32Lookup =
	    { 0xFF,0xFF,0x1A,0x1B,0x1C,0x1D,0x1E,0x1F, // '0', '1', '2', '3', '4', '5', '6', '7'
	      0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF,0xFF, // '8', '9', ':', ';', '<', '=', '>', '?'
	      0xFF,0x00,0x01,0x02,0x03,0x04,0x05,0x06, // '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
	      0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E, // 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
	      0x0F,0x10,0x11,0x12,0x13,0x14,0x15,0x16, // 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W'
	      0x17,0x18,0x19,0xFF,0xFF,0xFF,0xFF,0xFF, // 'X', 'Y', 'Z', '[', '\', ']', '^', '_'
	      0xFF,0x00,0x01,0x02,0x03,0x04,0x05,0x06, // '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g'
	      0x07,0x08,0x09,0x0A,0x0B,0x0C,0x0D,0x0E, // 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
	      0x0F,0x10,0x11,0x12,0x13,0x14,0x15,0x16, // 'p', 'q', 'r', 's', 't', 'u', 'v', 'w'
	      0x17,0x18,0x19,0xFF,0xFF,0xFF,0xFF,0xFF  // 'x', 'y', 'z', '{', '|', '}', '~', 'DEL'
	    };
	
	public static int shift=6;
	
	private static final char[] MONTH = {'1','2','3','4','5','6','7','8','9','A','B','C'};
	
	private static final char[] DAY = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	/**
	 * 把10进制的数字转换成64进制
	 * 
	 * @param number
	 * @param shift
	 * @return
	 */
	public static String CompressNumber(long number) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= shift;
		} while (number != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	/**
	 * 把10进制的数字转换成64进制
	 * 
	 * @param number
	 * @param shift
	 * @return
	 */
	public static String CompressNumberAndUrlEncode(long number) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= shift;
		} while (number != 0);
		String s = new String(buf, charPos, (64 - charPos));
		try {
			return URLEncoder.encode(s, "UTF8");
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}
	
	/**
	 * 把64进制的字符串转换成10进制
	 * 
	 * @param decompStr
	 * @return
	 */
	public static long UnCompressNumber(String decompStr) {
		long result = 0;
		for (int i = decompStr.length() - 1; i >= 0; i--) {
			if (i == decompStr.length() - 1) {
				result += getCharIndexNum(decompStr.charAt(i));
				continue;
			}
			for (int j = 0; j < digits.length; j++) {
				if (decompStr.charAt(i) == digits[j]) {
					result += ((long) j) << 6 * (decompStr.length() - 1 - i);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param ch
	 * @return
	 */
	public static long getCharIndexNum(char ch) {
		if(ch=='='){
			return 62;
		}else if(ch=='-'){ 
			return 63;
		}
		int num = ((int) ch);
		if (num >= 48 && num <= 57) {
			return num - 48;
		} else if (num >= 97 && num <= 122) {
			return num - 87;
		} else if (num >= 65 && num <= 90) {
			return num - 29;
		} else if (num == 43) {
			return 62;
		} else if (num == 47) {
			return 63;
		}
		return 0;
	}
	
	public static String int2Base32(int l){
		byte [] b = new byte[4];  
		for(int i= 0; i < 4; i++){  
		   b[3 - i] = (byte)(l >>> (i * 8));  
		}  
		return base32Encode(b);
	}
	
	public static int base322int(String s){
		byte[] b = base32Decode(s);
		int res = 0;
		for(int i =0; i < 4; i++){      
			   res <<= 8;  
			   res ^= (long)b[i] & 0xFF;      
			}  
		return res;
	}
	
	static public String base32Encode(final byte[] bytes)
    {
        int i =0, index = 0, digit = 0;
        int currByte, nextByte;

        // begin fix
        // added by jonelo@jonelo.de, Feb 13, 2005
        // according to RFC 3548, the encoding must also contain paddings in some cases
        int add=0;
        switch (bytes.length) {
            case 1: add=6; break;
            case 2: add=4; break;
            case 3: add=3; break;
            case 4: add=1; break;
        }
        // end fix

        StringBuffer base32 = new StringBuffer(((bytes.length+7)*8/5)+add);

        while(i < bytes.length)
        {
            currByte = (bytes[i]>=0) ? bytes[i] : (bytes[i]+256); // unsign

            /* Is the current digit going to span a byte boundary? */
            if (index > 3)
            {
                if ((i+1)<bytes.length)
                    nextByte = (bytes[i+1]>=0) ? bytes[i+1] : (bytes[i+1]+256);
                else
                    nextByte = 0;

                digit = currByte & (0xFF >> index);
                index = (index + 5) % 8;
                digit <<= index;
                digit |= nextByte >> (8 - index);
                i++;
            }
            else
            {
                digit = (currByte >> (8 - (index + 5))) & 0x1F;
                index = (index + 5) % 8;
                if (index == 0)
                    i++;
            }
            base32.append(base32Chars.charAt(digit));
        }

        // begin fix
        // added by jonelo@jonelo.de, Feb 13, 2005
        // according to RFC 3548, the encoding must also contain paddings in some cases
        switch (bytes.length) {
            case 1: base32.append("000000"); break;
            case 2: base32.append("0000"); break;
            case 3: base32.append("000"); break;
            case 4: base32.append("0"); break;
        }
        // end fix

        return base32.toString();
    }
	
	static public byte[] base32Decode(final String base32)
    {
        int    i, index, lookup, offset, digit;
        byte[] bytes = new byte[base32.length()*5/8];

        for(i = 0, index = 0, offset = 0; i < base32.length(); i++)
        {
            lookup = base32.charAt(i) - '0';

            /* Skip chars outside the lookup table */
            if ( lookup < 0 || lookup >= base32Lookup.length)
                continue;

            digit = base32Lookup[lookup];

            /* If this digit is not in the table, ignore it */
            if (digit == 0xFF)
                continue;

            if (index <= 3)
            {
                index = (index + 5) % 8;
                if (index == 0)
                {
                   bytes[offset] |= digit;
                   offset++;
                   if(offset>=bytes.length) break;
                }
                else
                   bytes[offset] |= digit << (8 - index);
            }
            else
            {
                index = (index + 5) % 8;
                bytes[offset] |= (digit >>> index);
                offset++;

                if(offset>=bytes.length) break;
                bytes[offset] |= digit << (8 - index);
            }
        }
        return bytes;
    }

	public static final String encodeDate(Date d){
		char[] c = new char[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		c[0] = MONTH[calendar.get(Calendar.MONTH)];
		c[1] = DAY[calendar.get(Calendar.DAY_OF_MONTH ) - 1];
		return new String(c);
	}
	
	public static final Date decodeDate(String date){
		if(date == null) return null;
		char[] c = date.toCharArray();
		if(c.length != 2)
			return null;
		Calendar calendar = Calendar.getInstance();
		for(int i = 0; i < MONTH.length; i++){
			if(MONTH[i] == c[0]){
				calendar.set(Calendar.MONTH, i);
				break;
			}
		}
		for(int i = 0; i < DAY.length; i++){
			if(DAY[i] == c[1]){
				calendar.set(Calendar.DATE, i+1);
				break;
			}
		}
		if(calendar.getTime().compareTo(new Date()) > 0){
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		}
		return calendar.getTime();
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//System.out.println("update mms_fanli set user_id=" + base322int("BCDUJOWA0".substring(1)) + " where trade_id=211990721505285;");
//		Date d = new Date();
//		System.out.println(encodeDate(d));
//		System.out.println(decodeDate("1B"));
		
//		System.out.println(Tf8Base64.int2Base32(123456));
//		System.out.println(Tf8Base64.base322int(Tf8Base64.int2Base32(123456)));
		
		System.out.println(Tf8Base64.CompressNumber(12345699999L));
		System.out.println(Tf8Base64.UnCompressNumber(Tf8Base64.CompressNumber(12345699999L)));
	}	

}
