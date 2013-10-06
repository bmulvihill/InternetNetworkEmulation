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
        serverPort = 7134;//config.serverPort;
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
			  FileWriter out = new FileWriter("/Users/bmulvihill/Desktop/builtfile.txt", true);
			  BufferedWriter bufWriter = new BufferedWriter(out);
			  //Step 1 read length
			  int nb = input.readInt();
			  System.out.println("Read Length: "+ nb);
                          System.out.println("Writing.......");
                          int total = 0;
                          ByteArrayOutputStream baos = new ByteArrayOutputStream();
                          byte buffer[] = new byte[config.packetSize + 47];
                            //Step 2 read byte
                            for(int s; (s=input.read(buffer)) != -1; )
                            {
                             System.out.println ("Current value of s: " + s);
                              Packet p = new Packet(buffer);
                              //pq.add(p);
                              baos.write(buffer, 0, s);
                              total += s;
                              if (total == nb) break;
                            }
                           byte result[] = baos.toByteArray();
			   String st = new String(result);
                           bufWriter.append(st);
			   bufWriter.close();
                           System.out.println ("receive from : " + 
                           clientSocket.getInetAddress() + ":" +
                           clientSocket.getPort() + " message - " + st);
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