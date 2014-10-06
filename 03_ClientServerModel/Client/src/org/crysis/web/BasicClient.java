package org.crysis.web;

import java.io.IOException;
import java.net.Socket;

public abstract class BasicClient implements IBasicClient {
	private final String server;
	private final int port;
	private Socket clientSocket;
	
	public BasicClient(String server, int port){
		this.server = server;
		this.port = port;
	}
	
	protected Socket getServerSocket() {
		return this.clientSocket;
	}

	protected void setclientSocket(final Socket clientSocket) {
		if(clientSocket != null) {
			this.clientSocket = clientSocket;
		} else {
			throw new  IllegalArgumentException("Can't set server socket to null");
		}
	}
	
	public String getServer() {
		return this.server;
	}
	
	public int getPort() {
		return this.port;
	}

	public abstract String sendRequest() throws IOException;

	public abstract void receiveAnswer();
}
