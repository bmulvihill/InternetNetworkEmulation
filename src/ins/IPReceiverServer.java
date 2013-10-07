/*
 * Receives TCP requests and packets
 */
package ins;

import java.io.*;
import java.net.*;

/**
 *
 * @author bmulvihill
 */
public class IPReceiverServer extends Thread {
    private int serverPort;
    public IPReceiverServer(){
        Config config = Config.getInstance();
        serverPort = config.serverPort;
    }
    
    @Override
    public void run(){
        try{  
            ServerSocket welcomeSocket = new ServerSocket(serverPort);   
            System.out.println("IP Server Receiver listening on port " + serverPort + "... ... ...");
            while(true) { 
                    Socket clientSocket = welcomeSocket.accept(); 
                    Connection c = new Connection(clientSocket); 
            } 
	} 
	catch(IOException e) {
		System.out.println("Listen :"+e.getMessage());
        } 
    }
}

class Connection extends Thread { 
	DataInputStream input; 
	DataOutputStream output; 
	Socket clientSocket; 
	Config config = Config.getInstance();
        PacketQueue pq = PacketQueue.getInstance();
        
	public Connection (Socket aClientSocket) { 
		try { 
                    clientSocket = aClientSocket; 
                    input = new DataInputStream( clientSocket.getInputStream()); 
                    output =new DataOutputStream( clientSocket.getOutputStream()); 
                    this.start(); 
                } 
                    catch(IOException e) {
                    System.out.println("Connection:"+e.getMessage());
		} 
	  } 

	  public void run() { 
		try { 		
                                  
                          //FileWriter out = new FileWriter("/Users/bmulvihill/Desktop/builtfile.txt", true);
			  FileWriter out = null;
			  //Step 1 read length
			  int nb = input.readInt();
			  System.out.println("Read Length: "+ nb);
                          int total = 0;             
                          byte buffer[] = new byte[config.packetSize + Packet.HEADERSIZE];
                            //Step 2 read byte
                            for(int s; (s=input.read(buffer)) != -1; )
                            {
                             System.out.println ("Current value of s: " + s);
                              Packet p = new Packet(buffer);
                              //out = new FileWriter("/Users/bmulvihill/Desktop/" + p.fileName, true);   
                              pq.add(p);
                              
                              total += s;
                              if (total == nb) break;
                            }
                           
                           //System.out.println ("receive from : " + 
                           //clientSocket.getInetAddress() + ":" +
                           //clientSocket.getPort() + " message - " + st);
			} 
			catch(EOFException e) {
			System.out.println("EOF:"+e.getMessage()); } 
			catch(IOException e) {
			System.out.println("IO:"+e.getMessage());}  
   
			finally { 
			  try { 
                            clientSocket.close();
			  }
			  catch (IOException e){/*close failed*/}
			}
		}
}