package server;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class ServerThread implements Runnable {
	
	PrintWriter outToClient;
	BufferedReader inFromClient;
	private Socket client = null;
	
	public ServerThread(Socket client){
		this.client = client;
	}
	
	public void execute(Socket client) throws IOException{
	//	boolean flag = true;
		
		outToClient = new PrintWriter(client.getOutputStream());
		
		inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String str;
		while(( str = inFromClient.readLine())!=null){
		try {
		
			
						if("KILL_SERVICE".equals(str)){
						
						System.exit(0);
						break;
					}else{
						if("HELO BASE_TEST".equals(str)){
							String clientIP = "IP:"+InetAddress.getLocalHost().getHostAddress();
							String clientPort = "Port:" + client.getLocalPort();
							String studentID = "StudentID:14306748";
							
							String stt = str + "\n"+ clientIP + "\n" + clientPort + "\n" + studentID;
														
							outToClient.println(stt);
							outToClient.flush();
						}else{
							outToClient.println(str);
							outToClient.flush();
						     }
					}
						
			
		}catch(Exception e){
			e.printStackTrace(); 
		}
		
		/*finally{
			
			outToClient.close();
			inFromClient.close();
			client.close();
			
		} */
		}
		outToClient.close();
		inFromClient.close();
		client.close();
	} 
	
	@Override
	public void run() {  
	    try {
			execute(client);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}  

}


