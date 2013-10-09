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
                    if(Inet4Address.getLocalHost().getHostAddress().toString().equals(p.destIP)){
                        ChunkQueue q = ChunkQueue.getInstance();
                        q.add(p);
                    } else {
                        Socket s = new Socket(p.destIP, Config.getInstance().serverPort);
                        DataOutputStream output = new DataOutputStream( s.getOutputStream()); 
                        Logger.log("Sending Packet: " + + p.seqNum + " out of " + p.totalPackets + " from " + p.fileName);
                        output.writeInt((int)p.size + Packet.HEADERSIZE); 
                        output.write(p.getPacketWithHeader(), 0, p.size + Packet.HEADERSIZE);  
                    }    
                }
                Thread.sleep(50);
            }
                                  
        }
        catch (IOException e){
            Logger.log(e.getMessage());
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e){
            Logger.log(e.getMessage());
            System.out.println(e.getMessage());
        }
        
    }
}
