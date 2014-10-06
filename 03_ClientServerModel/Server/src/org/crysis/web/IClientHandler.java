package org.crysis.web;

import java.io.IOException;
import java.net.Socket;

public interface IClientHandler extends Runnable{
	void handleClient() throws IOException;
	void setClientSocket(final Socket clientSocket);
	Socket getClientSocket();
}
