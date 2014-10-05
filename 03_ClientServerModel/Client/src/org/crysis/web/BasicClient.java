package org.crysis.web;

import java.net.Socket;

public abstract class BasicClient implements IBasicClient {
	private final String SERVER;
	private Socket clientSocket;
	
	public BasicClient(String server){
		this.SERVER = server;
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

	public abstract void sendRequest();

	public abstract void receiveRequest();
}
