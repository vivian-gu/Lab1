package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {
	private Socket s;
	
	BufferedReader br = null;

	public ClientThread(Socket s) throws IOException {
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public void run() {
		try {
			String content = null;
			
			while ((content = br.readLine()) != null) {
				System.out.println(content);
			}
		} catch (Exception e) {
			System.out.println("Server is closed!");
		}
		
	}
	
	
}