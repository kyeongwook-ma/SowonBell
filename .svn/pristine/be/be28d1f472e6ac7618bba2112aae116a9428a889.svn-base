/*
 * Copyright 2009-2013 by KLSoft Corp.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of KLSoft Corp. ("Confidential Information").
 */
package ma.dev.sowondejong.util;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * 1. FileName	: SMSUtil.java
 * 2. Package	: ma.dev.sowondejong.util
 * 3. Comments	:	
 * 4. Author	: kyoung wook
 * 5. DateTime	: 2013. 12. 15. 오후 7:43:15
 * 6. History	:
 * -----------------------------------------------------------------
 *	Date		 |	Name			|	Comment
 * -------------  -----------------   ------------------------------
 * 2013. 12. 15.		 | kyoung wook			|	최초작성
 * -----------------------------------------------------------------
 */

public class SMSUtil {

	public static void sendSMS(Activity activity, String phoneNumber, String message, String url){

		SmsManager sms = SmsManager.getDefault();
	
		ArrayList<String> parts = new ArrayList<String>();
		ArrayList<PendingIntent> sentList = new ArrayList<PendingIntent>();
		ArrayList<PendingIntent> deliveredList = new ArrayList<PendingIntent>();
		
		
		if(StringUtil.isEmptyString(message) || StringUtil.isEmptyString(phoneNumber)) {
			Toast.makeText(SowonDejonApp.getContext(), "수신인 정보가 잘못되었습니다. 다시 확인해주세요", Toast.LENGTH_SHORT).show();
			return;
		}

		try {
			PendingIntent piSent=PendingIntent.getBroadcast(activity, 0, new Intent("SMS_SENT"), 0);
			PendingIntent piDelivered=PendingIntent.getBroadcast(activity, 0, new Intent("SMS_DELIVERED"), 0);
		
			message += "\n아래 링크를 클릭하여 소원 메시지를 확인하여주세요!\n" ;
			
			if(Build.MODEL.contains("IM")) {
				sms.sendTextMessage(phoneNumber, null, message, piSent, piDelivered);							
				sms.sendTextMessage(phoneNumber, null, url, piSent, piDelivered);
			} else {
				
				message += url;
				
				sentList.add(piSent);
				deliveredList.add(piDelivered);
				
				parts = sms.divideMessage(message);
						
				sms.sendMultipartTextMessage(phoneNumber, null, parts, sentList, deliveredList);
			}
			
		} catch(NullPointerException e) {
			sms.sendTextMessage(phoneNumber, null, message, null, null);

		}



	}



}
