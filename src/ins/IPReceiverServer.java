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
                Logger.log(e.getMessage());
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
                        Logger.log(e.getMessage());
                        System.out.println("Connection: "+e.getMessage());
		} 
	  } 

	  public void run() { 
		try { 		                                
			  //Step 1 read length
			  int nb = input.readInt();
			  System.out.println("Read Length: "+ nb);
                          int total = 0;             
                          byte buffer[] = new byte[config.packetSize + Packet.HEADERSIZE];
                            //Step 2 read byte
                            for(int s; (s=input.read(buffer)) != -1; )
                            {
                             System.out.println ("Receiving file with size : " + s);
                              Packet p = new Packet(buffer);  
                              Logger.log("Received Packet from :" + p.hostIP + "| Packet Number: " + p.seqNum  + " out of " + p.totalPackets + " from " + p.fileName);;
                              pq.add(p);
                              total += s;
                              if (total == nb) break;
                            }
			} 
			catch(EOFException e) {
                            Logger.log(e.getMessage());
                            System.out.println("EOF:"+e.getMessage()); } 
			catch(IOException e) {
                            Logger.log(e.getMessage());
                            System.out.println("IO:"+e.getMessage());}  
   
			finally { 
			  try { 
                            clientSocket.close();
			  }
			  catch (IOException e){
                              Logger.log(e.getMessage());
                          }
			}
		}
}