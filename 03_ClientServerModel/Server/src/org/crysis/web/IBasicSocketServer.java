package org.crysis.web;

import java.io.IOException;
import java.net.Socket;

public interface IBasicSocketServer {
	void handleClient(final Socket clientSocket) throws IOException;
	
	void startServer() throws IOException;
	
	void dispose() throws IOException;
	
	int getServerPort();
}
