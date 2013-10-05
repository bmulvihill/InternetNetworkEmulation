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
                System.out.println(pq.isEmpty());
                if(!pq.isEmpty()){
                    
                    Packet p = pq.remove();
                    //Socket s = new Socket(p.destIP, p.destPort);
                }
                Thread.sleep(5000);
            }
                                  
            //DataInputStream input = new DataInputStream( s.getInputStream()); 
        }
        //catch (IOException e){
        //    System.out.println(e.getMessage());
        //}
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        
    }
}
