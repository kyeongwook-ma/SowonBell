package ma.dev.sowondejong.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

public class ImageUtil {

	
	public static byte[] bitmapToByteArray( Bitmap bitmap ) {  
		ByteArrayOutputStream stream = new ByteArrayOutputStream() ;  
		bitmap.compress( CompressFormat.JPEG, 100, stream) ;  
		byte[] byteArray = stream.toByteArray() ;  
		return byteArray ;  		
	}  
	
	public static Bitmap resizeBitmapImage(Bitmap source, int maxResolution)
	{
		int width = source.getWidth();
		int height = source.getHeight();
		int newWidth = width;
		int newHeight = height;
		float rate = 0.0f;

		if(width > height)
		{
			if(maxResolution < width)
			{
				rate = maxResolution / (float) width;
				newHeight = (int) (height * rate);
				newWidth = maxResolution;
			}
		}
		else
		{
			if(maxResolution < height)
			{
				rate = maxResolution / (float) height;
				newWidth = (int) (width * rate);
				newHeight = maxResolution;
			}
		}

		return Bitmap.createScaledBitmap(source, newWidth, newHeight, true);
	}

	public static byte[] convertFiletoByte(String fullPath) {
		ByteArrayOutputStream bos = null;
		InputStream is ;

		try {

			is = new BufferedInputStream(new FileInputStream(fullPath));
			bos = new ByteArrayOutputStream();

			while(is.available() > 0) 
				bos.write(is.read());

			is.close();
			bos.close();

		}catch (IOException e) {
			e.printStackTrace();
		} 

		return bos.toByteArray();
	}

	public static String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = SowonDejonApp.getContext().getContentResolver().query(contentUri, proj, null, null, null);

		if (cursor == null)
			return null;

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		cursor.moveToFirst();

		return cursor.getString(column_index);
	}
	
	public static Bitmap processImage(String imagePath) {
		Bitmap image = BitmapFactory.decodeFile(imagePath);

		ExifInterface exif;
		try {
			exif = new ExifInterface(imagePath);
			int exifOrientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			int exifDegree = exifOrientationToDegrees(exifOrientation);

			if(exifDegree != 0)
				image = rotate(image, exifDegree);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static int exifOrientationToDegrees(int exifOrientation)
	{
		switch(exifOrientation)
		{
		case ExifInterface.ORIENTATION_NORMAL :
			return 0;
		case ExifInterface.ORIENTATION_ROTATE_90 :
			return 90;
		case ExifInterface.ORIENTATION_ROTATE_180 :
			return 180;
		case ExifInterface.ORIENTATION_ROTATE_270 :
			return 270;

		}

		return 0;
	}


	/**
	 * �대�吏�� �뚯쟾�쒗궢�덈떎.
	 * 
	 * @param bitmap 鍮꾪듃留��대�吏�
	 * @param degrees �뚯쟾 媛곷룄
	 * @return �뚯쟾���대�吏�
	 */
	private static Bitmap rotate(Bitmap bitmap, int degrees)
	{
		if(degrees != 0 && bitmap != null)
		{
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) bitmap.getWidth() / 2 , 
					(float) bitmap.getHeight() /2 );

			try
			{
				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);
				if(bitmap != converted)
				{
					bitmap.recycle();
					bitmap = converted;
				}
			}
			catch(OutOfMemoryError ex)
			{
			}
		}
		return bitmap;
	}

}
