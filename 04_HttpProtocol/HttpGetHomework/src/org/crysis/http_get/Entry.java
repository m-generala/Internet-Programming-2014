package org.crysis.http_get;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class Entry {
	private static final int MAXIMUM_RUN_TIME = 15 * 1000;
	private static final int HTTP_PORT = 80;
	private static final String HOST = "wikipedia.org";
	private static final String PATH = "/";
	private static final String HTTP_METHOD_GET = "GET";
	private static final int HTTP_MOVED_PERMANETLY = 301;
	private static final int HTTP_FOUND = 302;
	
	public static void main(String[] args) 
			throws UnknownHostException, IOException, InterruptedException {
		HttpClient client = new HttpClient(HOST, PATH, HTTP_PORT);
		CharacterResponse response = client.createGet(HTTP_METHOD_GET);
		
		int status = getStatus(response.getStatusLine());
		System.out.println("Current status: " + status);
		
		response = getMeaningfulAnswer(client, response, status);
		
		String responseBody = new String(response.getBody());
		System.out.println(responseBody.isEmpty() ? "No body, sorry" : "Body: " + responseBody);
	}

	private static CharacterResponse getMeaningfulAnswer(HttpClient client,
			CharacterResponse response, int status)
			throws IOException, UnknownHostException {
		long startTime = System.currentTimeMillis();
		
		List<HttpHeader> headers = response.getHeaders();
		while(status == HTTP_MOVED_PERMANETLY
				|| status == HTTP_FOUND) {
			long estimatedTime = System.currentTimeMillis() - startTime;
			if(estimatedTime > MAXIMUM_RUN_TIME) {
				System.out.println("I smell something circular...");
				System.out.println("Exiting the fast way!!!");
				System.exit(-1);
			}
			
			for(HttpHeader header : headers) {
				if(header.getName().equalsIgnoreCase("location")) {
					handleUrl(client, header);
					response = client.createGet(HTTP_METHOD_GET);
					headers = response.getHeaders();
					break;
				}
			}
			
			status = getStatus(response.getStatusLine());
			System.out.println("Current status: " + status);
		}
		
		return response;
	}

	private static void handleUrl(HttpClient client, HttpHeader header) {
		String url = header.getValue();
		
		//Some sites return not the whole url
		//but just path so this is for that case
		if(!isValidUrl(url)) {
			client.setPath("/" + url);
		} else {
			//UnknownHostException if http is in the string
			setHostPath(client, url);
		}
	}

	private static void setHostPath(HttpClient client, String url) {
		String host;
		String path;
		url = url.replace("http://", "");
		url = url.replace("https://", "");
		String[] splittedUrl = url.split("/");
		host = splittedUrl[0];
		
		StringBuilder builder = new StringBuilder();
		builder.append('/');
		for(int i = 1; i < splittedUrl.length; i++) {
			builder.append(splittedUrl[i]);
			if(i != splittedUrl.length - 1) {
				builder.append('/');
			}
		}

		path = builder.toString();
		client.setServer(host);
		client.setPath(path);
	}
	
	private static Boolean isValidUrl(String givenUrl) {
		String url = givenUrl;
		Boolean hasHttp = url.substring(0, 4).equalsIgnoreCase("http");
		
		// Let's assume it's correct almost always
		if(hasHttp) {
			return true;
		}
		else {
			String regex = "<\\b(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(url);
	        
	        if(matcher.matches()) {
	        	return true;
	        }
		}
		
		return false;
	}
	
	private static int getStatus(String statusLine) {
		return Integer.parseInt(statusLine.split(" ")[1]);
	}
}
