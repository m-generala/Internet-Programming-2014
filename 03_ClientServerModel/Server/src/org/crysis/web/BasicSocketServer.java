package org.crysis.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicSocketServer implements IBasicSocketServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private IClientHandler clientHandler;
	private int serverPort;
	
	public BasicSocketServer(final int serverPort, final IClientHandler clientHandler)
			throws IOException {
		this.setServerPort(serverPort);
		this.setServerSocket(new ServerSocket(this.getServerPort()));
		this.setClientHandler(clientHandler);
	}

	public void startServer() throws IOException {
		this.setClientSocket(this.getServerSocket().accept());
				
		try{
			this.handleClient(this.getClientSocket());
		} finally {
			this.dispose();
		}
		
	}

	public void dispose() throws IOException {
			this.getClientSocket().close();
			this.getServerSocket().close();
	}

	
	public void handleClient(final Socket clientSocket)
			throws IOException{
		this.getClientHandler().handleClient(clientSocket);
	}

	protected ServerSocket getServerSocket() {
		return serverSocket;
	}

	protected void setServerSocket(final ServerSocket serverSocket) {
		if(serverSocket != null) {
			this.serverSocket = serverSocket;
		} else {
			throw new  IllegalArgumentException("Can't set server socket to null");
		}
	}

	protected Socket getClientSocket() {
		return clientSocket;
	}

	protected void setClientSocket(final Socket clientSocket) {
		if(clientSocket != null) {
			this.clientSocket = clientSocket;
		} else {
			throw new  IllegalArgumentException("Can't set server socket to null");
		}
	}
	
	public int getServerPort() {
		return this.serverPort;
	}
	
	private void setServerPort(final int port) {
		if(port < 0){
			throw new IllegalArgumentException(
					"Port must be a positive number");
		} else if(port < 1024){
			this.serverPort = port;
		} else {
			throw new IllegalArgumentException(
					"The surver port must be over 1024");
		}
	}
	
	protected IClientHandler getClientHandler() {
		return clientHandler;
	}

	protected void setClientHandler(final IClientHandler clientHandler) {
		if(clientHandler == null){
			throw new IllegalArgumentException(
					"Client handler cannot be null");
		}
		
		this.clientHandler = clientHandler;
	}
}
