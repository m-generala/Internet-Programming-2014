package org.crysis.web;

import java.io.IOException;

public interface IBasicSocketServer {
	void handleClient() throws IOException;
	
	void startServer() throws IOException;
	
	void dispose() throws IOException;
	
	void shutDownPool();
	
	int getServerPort();
}
