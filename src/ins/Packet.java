/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;
import java.net.*; 
import java.io.*; 
import java.util.*;
/**
 *
 * @author bmulvihill
 */
public class Packet {
    private static int HEADERSIZE = 10;
    protected String destIP;
    protected int destPort;
    
    Packet(byte[] packet, HashMap headerMap) {
        this.packet = packet; 
        destIP = headerMap.get("IP").toString();
        destPort = Integer.parseInt(headerMap.get("Port").toString());
        for(int i=0; i < headerMap.toString().length(); i++){  
            header = headerMap.toString().getBytes();  
        }
        setPacketWithHeaders();
    }
    
    Packet (byte[] packet){
        this.packet = packet;
    }
    
    /**
    *
    * Puts header and packet data into single byte array
    */ 
    protected void setPacketWithHeaders(){                               
        byte[] tempPacket = new byte[packet.length + header.length];
        System.arraycopy(header, 0, tempPacket, 0, header.length);
        System.arraycopy(packet, 0, tempPacket, header.length, packet.length);
        this.packetWithHeader = tempPacket;
    }
    
    /**
    *
    * Returns packet with header byte array
    */
    protected byte[] getPacket(){
        return packetWithHeader;
    }
    
    private byte[] packet;
    private byte[] header;
    private byte[] packetWithHeader;
    
}
