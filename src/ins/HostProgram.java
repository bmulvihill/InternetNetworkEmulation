/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author bmulvihill
 */
public class HostProgram {
    public HostProgram(){
        HostThread sendThread = new HostThread();
        sendThread.start();
        ChunkThread chunkThread = new ChunkThread();
        chunkThread.start();
    }
    
}

class ChunkThread extends Thread{
    private ChunkQueue cq = ChunkQueue.getInstance();
    public ChunkThread(){
        
    }
    
    public void run(){
            while(true){
                try{
                    if(!cq.isEmpty()){
                        Packet p = cq.remove();
                        System.out.println(p.size);
                        System.out.println("Writing to Filesystem..");
                        FileWriter out = new FileWriter(p.fileName, true);   
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        baos.write(p.getPacket(), 0, p.size);
                        byte result[] = baos.toByteArray();
			String st = new String(result);
                        BufferedWriter bufWriter = new BufferedWriter(out);
                        bufWriter.append(st);
			bufWriter.close();
                    }
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    Logger.log(e.getMessage());
                    System.out.println(e.getMessage());
                }
                catch (IOException e){
                    Logger.log(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }
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
            Logger.log(e.getMessage());
            System.out.println("INTERRUPTED EXCEPTION:"+e.getMessage()); 
        }
            queue.add(p);
            //System.out.println("Chunk Queue: " + queue.size());
            notifyAll(); 
    }
    
    public synchronized Packet remove(){
        try{
            while (queue.isEmpty() == true)
            wait();
        }
        catch (InterruptedException e) {
            Logger.log(e.getMessage());
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
