package org.crysis.http_get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.crysis.web.BasicClient;
import org.crysis.http_get.CharacterResponse;
import org.crysis.http_get.HttpHeader;

public class HttpClient extends BasicClient{
	private static final String PROTOCOL_VERSION = "1.1";
	private String path;

	public HttpClient(String host, String path, int port) {
		super(host, port);
		this.setPath(path);
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public CharacterResponse createGet (String method) 
			throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(this.getServer(), this.getPort());
		try {
			final InputStream inputStream = clientSocket .getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			final InputStreamReader inputStreamReader =
					new InputStreamReader(inputStream);
			
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);
			
			writeRequest(out, method);
			out.flush();
			
			final CharacterResponse res = parseResponse(in);
			
			return res;
		} finally {
			clientSocket.close();
		}
	}

	private CharacterResponse parseResponse(BufferedReader in) throws IOException {
		final CharacterResponse result = new CharacterResponse();
		result.setStatusLine(in.readLine());
		
		String next;
		while(!(next = in.readLine()).equals("")) {
			result.getHeaders().add(HttpHeader.createFromHeaderLine(next));
		}
		
		final char[] body = result.getBody();
		
		in.read(body);
		return result;
	}

	private void writeRequest(PrintWriter out, String method) {
		out.printf("%s %s HTTP/%s", method, this.getPath(), PROTOCOL_VERSION);
		out.printf("\n");
		out.printf("Host: %s\n", this.getServer());
		out.printf("\n");
	}
}
