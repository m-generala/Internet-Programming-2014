package org.crysis.date_manipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.crysis.web.ClientHandler;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateClientHandler extends ClientHandler{

	@Override
	public void handleClient() throws IOException {
		//get I/O streams
		final InputStream inputStream = this.getClientSocket().getInputStream();
		final OutputStream outputStream = this.getClientSocket().getOutputStream();
		final InputStreamReader inputStreamReader =
		new InputStreamReader(inputStream);
		final BufferedReader in = new BufferedReader(inputStreamReader);
		final PrintWriter out = new PrintWriter(outputStream);
		// read from socket
		final String dateString = in.readLine();
		
		String[] splitDate = dateString.split(" ");
		
		int year = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int day = Integer.parseInt(splitDate[2]);
		
		LocalDate givenDate = new LocalDate(year, month, day);
		
		int days = Days.daysBetween(givenDate, new LocalDate()).getDays();
		
		out.println(days);
		out.flush();
	}
}
