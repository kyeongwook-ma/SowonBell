package ma.dev.sowondejong.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import ma.dev.sowondejong.data.SowonMessage;

public class UploadSowonRequest extends NetworkRequest {

	private final String servAddr = "http://sowondejong.herokuapp.com/uploadsowon";
	private String requestMethod = "POST";
	private SowonMessage message;

	public UploadSowonRequest( SowonMessage message ) {
		setUrl(servAddr);	
		this.message = message;

	}	

	@Override
	protected void parsing() {

		try {
			
			String htmlForm = message.getMessage();
			htmlForm = htmlForm.replace(System.getProperty("line.separator"), "<br>"); 
			message.setMessage(htmlForm);
			
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
							
			os.write( message.getJson().getBytes("UTF-8") );
			os.flush();
			os.close();

			InputStream is      = null;
			BufferedReader in   = null;

			is  = conn.getInputStream();
			in  = new BufferedReader(new InputStreamReader(is), 8 * 1024);
			String line = null;
			StringBuffer buff   = new StringBuffer();

			while ( ( line = in.readLine() ) != null )
			{
				buff.append(line + "\n");
			}

			result = buff.toString().trim();


		} catch (IOException e) {
			e.printStackTrace();	
			// Toast.makeText(SowonDejonApp.getContext(), R.string.server_error, Toast.LENGTH_SHORT).show();	
		}
	}
}


