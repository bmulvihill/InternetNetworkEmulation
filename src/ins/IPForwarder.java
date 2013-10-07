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
                    if("localhost".equals(p.destIP)){
                        ChunkQueue q = ChunkQueue.getInstance();
                        q.add(p);
                    } else {
                        Socket s = new Socket(p.destIP, 7134);
                        DataOutputStream output = new DataOutputStream( s.getOutputStream()); 
                        output.writeInt((int)p.size + Packet.HEADERSIZE); 
                        output.write(p.getPacket(), 0, p.size + Packet.HEADERSIZE);  
                    }    
                }
                Thread.sleep(50);
            }
                                  
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        
    }
}
