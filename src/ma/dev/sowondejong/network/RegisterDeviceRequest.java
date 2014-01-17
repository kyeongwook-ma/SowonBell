package ma.dev.sowondejong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

public class RegisterDeviceRequest extends NetworkRequest {
	private final String servAddr = "http://sowondejong.herokuapp.com/register";
	private String requestMethod = "POST";
	private String gcmId;
	
	public RegisterDeviceRequest(String id) {
		this.gcmId = id;
		setUrl(servAddr);	
	}
	
	@Override
	protected void parsing() {

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			// ������� �����Ѵ�.
			conn.setRequestProperty("Content-Type", "application/json");

			// �����κ��� �޼����� ���� �� �ֵ��� �Ѵ�. 
			conn.setDoInput(true);

			// ������ �����͸� ����� �� �ֵ��� �Ѵ�.  
			conn.setDoOutput(true);
				
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);

			conn.connect();
			
			OutputStream os = conn.getOutputStream();
			
			JSONObject sendObj = new JSONObject();
			try {
				sendObj.put("gcmId", gcmId);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			os.write( sendObj.toString().getBytes("UTF-8") );
			os.flush();
			os.close();

			InputStream is      = null;
			BufferedReader in   = null;

			is  = conn.getInputStream();
			in  = new BufferedReader(new InputStreamReader(is), 8 * 1024);
			String line = null;

			line = in.readLine();

			result = line;


		} catch (IOException e) {
			e.printStackTrace();	
			// Toast.makeText(SowonDejonApp.getContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	
		}
	}
}
