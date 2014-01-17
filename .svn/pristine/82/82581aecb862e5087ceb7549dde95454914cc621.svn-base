package ma.dev.sowondejong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ma.dev.sowondejong.data.SowonMessage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class PhraseGetRequest extends NetworkRequest {

	private final String servAddr = "http://sowondejong.herokuapp.com/phraselist";
	private String requestMethod = "GET";
	private ArrayList<String> phraseList = new ArrayList<String>();

	public PhraseGetRequest() {
		setUrl(servAddr);	

	}

	@Override
	protected void parsing() {

		setUrl(servAddr);

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);

			// �����κ��� �޼����� ���� �� �ֵ��� �Ѵ�. 
			conn.setDoInput(true);

			// ������� �����Ѵ�.
			conn.setRequestProperty("Content-Type", "application/json");

			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);

			conn.connect();

			InputStream is      = null;
			BufferedReader in   = null;

			is  = conn.getInputStream();
			in  = new BufferedReader(new InputStreamReader(is), 8 * 1024);

			String line = null;
			StringBuilder sb = new StringBuilder();
			
			while ( ( line = in.readLine() ) != null )
			{
				sb.append(line);
			}
			
			String[] phrases = new Gson().fromJson(sb.toString(), String[].class);
			
			for(String str : phrases) {
				phraseList.add(str);
			}
		
			result = phraseList;

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}

