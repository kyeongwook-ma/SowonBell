/*
 * Copyright 2009-2013 by KLSoft Corp.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of KLSoft Corp. ("Confidential Information").
 */
package ma.dev.sowondejong.util;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore.Audio;

/**
 * 1. FileName	: MusicController.java
 * 2. Package	: ma.dev.sowondejong.util
 * 3. Comments	:	
 * 4. Author	: kyoung wook
 * 5. DateTime	: 2013. 12. 15. 오후 7:12:14
 * 6. History	:
 * -----------------------------------------------------------------
 *	Date		 |	Name			|	Comment
 * -------------  -----------------   ------------------------------
 * 2013. 12. 15.		 | kyoung wook			|	최초작성
 * -----------------------------------------------------------------
 */

public class MusicController {

	private MediaPlayer mediaPlayer;

	public MusicController(Context context, int resId) {
		mediaPlayer = MediaPlayer.create(context, resId);
//		mediaPlayer.setVolume(1.0f, 1.0f);
//		mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
	}

	public void play() {
	
		if(mediaPlayer.isPlaying()){
			//음악이 실행되고 있다면 pause
			mediaPlayer.stop();

			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			mediaPlayer.seekTo(0);

		} else {
			// 재생중이 아니면 재생
			mediaPlayer.start();				
		}

	}

}
