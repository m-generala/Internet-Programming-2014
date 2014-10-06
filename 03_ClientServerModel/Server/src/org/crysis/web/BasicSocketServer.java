package org.crysis.web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BasicSocketServer implements IBasicSocketServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private int serverPort;
	private ExecutorService pool;
	private IClientHandler clientHandler;
	private Boolean running;
	
	
	public BasicSocketServer(final int serverPort, int poolSize, IClientHandler clientHandler)
			throws IOException {
		this.setServerPort(serverPort);
		this.setServerSocket(new ServerSocket(this.getServerPort()));
		this.setPool(poolSize);
		this.setClientHandler(clientHandler);
		this.running = true;
	}
	
	protected ExecutorService getPool() {
		return this.pool;
	}

	protected void setPool(int poolSize) {
		if(poolSize < 0){
			throw new IllegalArgumentException("Pool size must be a positive number");
		}
		
		this.pool = Executors.newFixedThreadPool(poolSize);
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
	
	protected IClientHandler getClientHandler() {
		return clientHandler;
	}

	protected void setClientHandler(IClientHandler clientHandler) throws IOException {
		if(clientHandler == null){
			throw new IllegalArgumentException("Client handler cannot be null");
		}
		
		this.clientHandler = clientHandler;
	}

	public int getServerPort() {
		return this.serverPort;
	}
	
	private void setServerPort(final int port) {
		if(port < 0){
			throw new IllegalArgumentException(
					"Port must be a positive number");
		} else if(port < 1024){
			throw new IllegalArgumentException(
					"The surver port must be over 1024");
		} else {
			this.serverPort = port;
		}
	}
	
	public void startServer() throws IOException {		
		try{
			this.handleClient();
		} finally {
			this.dispose();
		}
		
	}

	public void dispose() throws IOException {
			this.getClientSocket().close();
			this.getServerSocket().close();
	}

	
	public void handleClient()
			throws IOException{
		try{
			while(this.running){
        		this.setClientSocket(this.getServerSocket().accept());
        		Socket clientSocket = this.getClientSocket();
        		this.getClientHandler().setClientSocket(clientSocket);	
				this.pool.execute(this.getClientHandler());
			}
		} finally {
			this.shutDownPool();
		}
	}

    public void run() throws IOException { // run the service
    	this.handleClient();
    }

	public void shutDownPool() {
	    this.pool.shutdown(); 
	    try {
		    if (!this.pool.awaitTermination(60, TimeUnit.SECONDS)) {
		    	this.pool.shutdownNow(); 
			   
			    if (!this.pool.awaitTermination(60, TimeUnit.SECONDS)){
			    	System.err.println("Pool did not terminate");
			    }
		    }
	    } catch (InterruptedException ie) {
		    this.pool.shutdownNow();
		    Thread.currentThread().interrupt();
	    }	
	}
}
