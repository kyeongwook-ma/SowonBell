package ma.dev.sowondejong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import ma.dev.sowondejong.data.SowonMessage;
import ma.dev.sowondejong.util.SowonAccountManager;

import com.google.gson.Gson;

public class SowonGetRequest extends NetworkRequest {
	
	private final String servAddr = "http://sowondejong.herokuapp.com/phraselist";
	private String requestMethod = "GET";
	private ArrayList<String> phraseList = new ArrayList<String>();
	private String id;
	
	public SowonGetRequest(String id) {
		setUrl(servAddr);
		this.id = id;
	}
	
	@Override
	protected void parsing() {
		
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);

			// �����κ��� �޼����� ���� �� �ֵ��� �Ѵ�. 
			conn.setDoInput(true);

			// ������ �����͸� ����� �� �ֵ��� �Ѵ�.  
			conn.setDoOutput(true);

			// ������� �����Ѵ�.
			conn.setRequestProperty("Content-Type", "application/json");

			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);

			conn.connect();
				
			OutputStream os = conn.getOutputStream();
			
			os.write( id.getBytes("UTF-8") );
			os.flush();
			os.close();
			
			InputStream is      = null;
			BufferedReader in   = null;

			is  = conn.getInputStream();
			in  = new BufferedReader(new InputStreamReader(is), 8 * 1024);

			String line = null;

			while ( ( line = in.readLine() ) != null )
			{
				phraseList.add( line );
			}

			result = phraseList;
		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

