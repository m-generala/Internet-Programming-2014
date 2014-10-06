package org.crysis.date_manipulation;

import java.io.IOException;

import org.crysis.web.BasicSocketServer;
import org.crysis.web.IClientHandler;

public class DateServer extends BasicSocketServer {

	public DateServer(int serverPort, int poolSize, IClientHandler clientHandler)
			throws IOException {
		super(serverPort, poolSize, clientHandler);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		DateServer server = new DateServer(2020, 10, new DateClientHandler());
		server.startServer();
	}
}
