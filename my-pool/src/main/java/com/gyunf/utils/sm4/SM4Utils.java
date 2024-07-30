package com.gyunf.utils.sm4;

import com.gyunf.utils.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SM4Utils
{
	private static boolean hexString = false;

	public SM4Utils()
	{
	}

	/**
	 * sm4加密
	 * @param plainText 加密内容
	 * @param secretKey key值
	 * @return
	 */
	public static String encryptData_ECB(String plainText,String secretKey)
	{
		return encryptData_ECB(plainText,"GBK",secretKey);
	}
	
	public static String encryptData_ECB(String plainText,String encoding,String secretKey)
	{
		try
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_ENCRYPT;

			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = ctx.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes(encoding));
			String cipherText = new BASE64Encoder().encode(encrypted);
			/*if (cipherText != null && cipherText.trim().length() > 0)
			{
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}*/
			return cipherText;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decryptData_ECB(String cipherText, String secretKey)
	{
		return decryptData_ECB(cipherText,"GBK", secretKey);
		
	}
	public static String decryptData_ECB(String cipherText,String encoding, String secretKey)
	{
		try
		{
			SM4_Context ctx = new SM4_Context();
			ctx.isPadding = true;
			ctx.mode = SM4.SM4_DECRYPT;

			byte[] keyBytes;
			if (hexString)
			{
				keyBytes = ctx.hexStringToBytes(secretKey);
			}
			else
			{
				keyBytes = secretKey.getBytes();
			}

			SM4 sm4 = new SM4();
			sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
			return new String(decrypted, encoding);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		String str = "123456";
		// 第一个
		//String strEn = encryptData_ECB(str,"1234567891234567");
		//System.out.println("加密后：" + strEn);
		//System.out.println("解密后：" + decryptData_ECB(strEn, "1234567891234567"));

		System.out.println("字符:"+ new String(str.getBytes()));

		String jm = Base64.encode(str.getBytes());
		System.out.println("加密:"+ jm);

		System.out.println("解密:"+ Base64.decode(jm));
	}

}
