/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;

import java.io.*;
import java.util.*;

/**
 *
 * @author bmulvihill
 */
public class HostProgram {
    public HostProgram(){
        HostThread sendThread = new HostThread();
        sendThread.start();    
    }
    
}

class ChunkQueue {
    private Queue<Packet> queue = new LinkedList<Packet>();
    private static ChunkQueue instance = null;
    protected ChunkQueue() {}
    
    public static ChunkQueue getInstance() {
      if(instance == null) {
         instance = new ChunkQueue();
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
            System.out.println("Chunk Queue: " + queue.size());
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
