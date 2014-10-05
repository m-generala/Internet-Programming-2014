package org.crysis.web;

import java.io.IOException;
import java.net.Socket;

public interface IClientHandler extends Runnable{
	void handleClient(final Socket clientSocket) throws IOException;
}
