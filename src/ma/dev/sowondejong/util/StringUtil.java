/*
 * Copyright 2009-2013 by KLSoft Corp.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of KLSoft Corp. ("Confidential Information").
 */
package ma.dev.sowondejong.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * 1. FileName	: StringUtil.java
 * 2. Package	: ma.dev.sowondejong.util
 * 3. Comments	:	
 * 4. Author	: kyoung wook
 * 5. DateTime	: 2013. 12. 15. 오후 7:46:11
 * 6. History	:
 * -----------------------------------------------------------------
 *	Date		 |	Name			|	Comment
 * -------------  -----------------   ------------------------------
 * 2013. 12. 15.		 | kyoung wook			|	최초작성
 * -----------------------------------------------------------------
 */

public class StringUtil {


	public static boolean isEmptyString(String str) {
		return (str == null || str.trim().length() == 0);
	}

	public static String getIPAddr() {
		InetAddress Address = null;
		try {
			Address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return Address.getHostAddress();
	}

	public static String getDateWithStrForm() {
		GregorianCalendar today = new GregorianCalendar ( );

		int year = today.get ( today.YEAR );
		int month = today.get ( today.MONTH ) + 1;
		int day = today.get ( today.DAY_OF_MONTH ); 
		int hour = today.get( today.HOUR );
		int minuete = today.get( today.MINUTE );
		int sec = today.get( today.SECOND);
		int ampm = today.get ( today.AM_PM );

		String strAmpm;

		strAmpm = ampm  == 1 ? "PM" : "AM";

		return new String( String.valueOf(year) +  "-" + String.valueOf(month)  
				+ "-" + String.valueOf(day) + " " + String.valueOf(hour) 
				+ ":" + String.valueOf(minuete)
				+ ":" + String.valueOf(sec) 
				+ " " + strAmpm);
	}

	public static String getMyPhoneNumber(Context context) {

		TelephonyManager mgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
				  
		return byteArrayToHexString( messageDigest.digest(
				getNationPhoneNumber(mgr.getLine1Number(), "KR").getBytes()));

	}
	
	public static String getNationPhoneNumber(String phoneNumber, String nation) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		 PhoneNumber numberProto = null ;
		try {
		  numberProto = phoneUtil.parse(phoneNumber, nation);
		}catch (NumberParseException e) {}
		
		return String.valueOf(numberProto.getNationalNumber());
	}

	public static String getSHA(String str) {

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return byteArrayToHexString(messageDigest.digest(str.getBytes()));
	}
	
	public static String getPhoneSHA(String number) {
		return getSHA(getNationPhoneNumber(number, "KR"));
		
	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i=0; i < b.length; i++) {
			result +=
					Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}
}
