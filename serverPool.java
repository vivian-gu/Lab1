package server;

import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.concurrent.Executor;  
import java.util.concurrent.Executors;  
  
  
public class serverPool {  
  
    public static void main(String[] args) throws IOException{  
         
    	ServerSocket server = new ServerSocket(Integer.parseInt(args[0]));  
        Socket client = null;  
        
        Executor service = Executors.newCachedThreadPool();  
        boolean f = true;  
        while(f){  
             
            client = server.accept();  
            System.out.println("Connect with server successfully!");  
             
            service.execute(new ServerThread(client));  
        }   
        server.close();  
    }  
}  
