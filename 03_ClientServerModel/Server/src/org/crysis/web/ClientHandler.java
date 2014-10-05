package org.crysis.web;

import java.net.Socket;
import java.io.*;

public abstract class ClientHandler implements IClientHandler  {
	private Socket clientSocket;
	
	public ClientHandler(final Socket clientSocket) {
		setClientSocket(clientSocket);
	}

	protected void setClientSocket(Socket clientSocket) {
		if(clientSocket == null){
			throw new IllegalArgumentException("Client socket cannot be null");
		}
		
		this.clientSocket = clientSocket;
	}

	protected Socket getClientSocket() {
		return this.clientSocket;
	}

	//From runnable interface
	public void run() {
		try {
			handleClient(clientSocket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void handleClient(final Socket clientSocket) throws IOException;
}
