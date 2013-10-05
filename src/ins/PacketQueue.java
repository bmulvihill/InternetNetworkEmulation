/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;

import java.util.*;

/**
 * Queue of packets that need to be sent
 * Singleton pattern
 * @author bmulvihill
 */
public class PacketQueue {
    private static PacketQueue instance = null;
    private Queue<Packet> queue = new LinkedList<Packet>();
    
    protected PacketQueue() {}
    
    public static PacketQueue getInstance() {
      if(instance == null) {
         instance = new PacketQueue();
      }
      return instance;
    }
    
    public synchronized void add(Packet p){
        try{
            while (queue.size() == 10) 
                wait();
        }
        catch (InterruptedException e) {
            System.out.println("INTERRUPTED EXCEPTION:"+e.getMessage()); 
        }
            queue.add(p);
            notifyAll(); 
    }
    
    public synchronized Packet remove(){
        try{
            while (queue.isEmpty() == true)
            wait();
        }
        catch (InterruptedException e) {
            System.out.println("INTERRUPTED EXCEPTION:"+e.getMessage()); 
        }
        Packet returnPacket = queue.poll();
        notifyAll();
        return returnPacket;
    }
    
    protected Boolean isEmpty(){
        return (queue.size() == 0);
    }
    
    protected int count() {
        return queue.size();
    }
    
}
