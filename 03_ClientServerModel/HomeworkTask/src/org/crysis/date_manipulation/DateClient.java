package org.crysis.date_manipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.crysis.web.BasicClient;

public class DateClient extends BasicClient{

	private String request;
	public DateClient(String server, int port, String request) {
		super(server, port);
		this.request = request;
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println("Enter date: ");
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		final String request = reader.readLine();
		final DateClient echoClient = new DateClient("localhost", 2020, request);
		final String response = echoClient.sendRequest();
		System.out.println("Response: " + response);
	}

	@Override
	public String sendRequest() throws IOException {
		final Socket clientSocket = new Socket(this.getServer(), this.getPort());
		//get I/O streams
		final InputStream inputStream = clientSocket .getInputStream();
		final OutputStream outputStream = clientSocket.getOutputStream();
		final InputStreamReader inputStreamReader =
		new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		final PrintWriter out = new PrintWriter(outputStream);
		// write to socket what we have red (this is echo server)
		out.println(request);
		// always flush writer
		out.flush();
		// read from socket
		final String result = in.readLine();
		// we should ALWAYS close sockets!
		clientSocket.close();
		return result;
	}

	@Override
	public void receiveAnswer() {
		
	}

}
