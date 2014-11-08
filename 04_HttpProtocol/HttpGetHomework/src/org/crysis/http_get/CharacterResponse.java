package org.crysis.http_get;

import java.util.LinkedList;
import java.util.List;

public class CharacterResponse {
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;
	private String statusLine;
	private final List<HttpHeader> headers = 
			new LinkedList<HttpHeader>();
	private char[] body;
	
	public String getStatusLine() {
		return statusLine;
	}
	
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	
	public List<HttpHeader> getHeaders() {
		return headers;
	}
	
	public char[] getBody() {
		if(body == null) {
			body = new char[getContentLength()];
		}
		return body;
	}

	private int getContentLength() {
		for(HttpHeader next : headers) {
			if(next.getName().toLowerCase().equals("content-length")) {
				final int result = Integer.parseInt(next.getValue());
				return Math.min(MAX_REQUEST_SIZE, result);
			}
		}
		return 0;
	}
}
