package org.crysis.web;

import java.io.IOException;

public interface IBasicClient {
	String sendRequest() throws IOException;
	
	void receiveAnswer();
}
