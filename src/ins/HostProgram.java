/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;

import java.io.*;

/**
 *
 * @author bmulvihill
 */
public class HostProgram {
    //Singleton PacketQueue
    //PacketQueue pq = PacketQueue.getInstance();
    public HostProgram(){
        HostThread sendThread = new HostThread();
        sendThread.start();    
    }
    
}
