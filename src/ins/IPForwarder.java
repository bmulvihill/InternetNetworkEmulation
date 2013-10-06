/*
 * Forewards packet to next hop
 */
package ins;
import java.net.*; 
import java.io.*;
/**
 *
 * @author bmulvihill
 */
public class IPForwarder extends Thread {
    private PacketQueue pq = PacketQueue.getInstance();
    
    public IPForwarder(){
        
    }
    
    @Override
    public void run(){
        try{
            while(true){
                if(!pq.isEmpty()){
                    Packet p = pq.remove();
                    Socket s = new Socket(p.destIP, 7134);
                    DataOutputStream output = new DataOutputStream( s.getOutputStream()); 
                    output.writeInt((int)p.size + 47); 
                    output.write(p.getPacket(), 0, p.size + 47);      
                }
                Thread.sleep(50);
            }
                                  
            //DataInputStream input = new DataInputStream( s.getInputStream()); 
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        
    }
}
