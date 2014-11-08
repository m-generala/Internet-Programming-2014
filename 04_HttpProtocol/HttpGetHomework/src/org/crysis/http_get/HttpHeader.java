package org.crysis.http_get;

public class HttpHeader {
	private final String name;
	private final String value;
	
	public static HttpHeader createFromHeaderLine(String headerLine) {
		final String[] splitted = headerLine.split("((: )|(:))", 2);
		if(splitted.length != 2) {
			throw new IllegalArgumentException(
					"Header line must be properly formatted, not " + headerLine);
		}
		
		return new HttpHeader(splitted[0], splitted[1]);
	}
	
	public HttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getValue() {
		return this.value;
	}
}
