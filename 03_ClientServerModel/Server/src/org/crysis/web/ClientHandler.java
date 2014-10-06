package org.crysis.web;

import java.io.IOException;
import java.net.Socket;

public abstract class ClientHandler implements IClientHandler {
	private Socket clientSocket;

	public ClientHandler() {
	}

	public void setClientSocket(final Socket clientSocket) {
		if(clientSocket == null){
			throw new IllegalArgumentException("Client socket cannot be null");
		}
		
		this.clientSocket = clientSocket;
	}

	public Socket getClientSocket() {
		return this.clientSocket;
	}
	
	public void run(){
		try {
			this.handleClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void handleClient() throws IOException;
}
