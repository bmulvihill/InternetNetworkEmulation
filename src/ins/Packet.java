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
    public static int HEADERSIZE = 71;
    protected String destIP;
    protected int destPort;
    protected int total;
    protected int size;
    protected int seqNum;
    protected int totalPackets;
    protected String fileName;

    // Constructor for new packet
    Packet(byte[] packet, HashMap headerMap) {
        this.packet = packet; 
        for(int i=0; i < headerMap.toString().length(); i++){  
            header = headerMap.toString().getBytes();  
        }
        setHeaderValues(headerMap);
        setPacketWithHeaders();
    }
    
    // Constructor for packet moving through network
    Packet (byte[] packet){
        this.packetWithHeader = packet;
        HashMap headerHash = new HashMap();
        String s = new String(packetWithHeader);
        this.packet = s.substring(Packet.HEADERSIZE, s.length()).getBytes();
        String headerValues = s.substring(1, Packet.HEADERSIZE - 1);
        String[] pairs = headerValues.split(",");
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i].trim();
            String[] keyValue = pair.split("=");
            headerHash.put(keyValue[0], keyValue[1]);
        }
        setHeaderValues(headerHash);
    }
    
    /**
     * 
     * @param h HashMap that will be used to set Packet properties
     */
    protected void setHeaderValues(HashMap h){
        System.out.println(h);
        destIP = h.get("I").toString();
        destPort = Integer.parseInt(h.get("P").toString());
        total = Integer.parseInt(h.get("F").toString());
        size = Integer.parseInt(h.get("S").toString());
        seqNum = Integer.parseInt(h.get("N").toString());
        totalPackets = Integer.parseInt(h.get("T").toString());
        fileName = h.get("FNAME").toString();
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
     * @return size of packet with headers
     */
    protected int getSize(){
        return packetWithHeader.length;
    }
    /**
    *
    * Returns packet with header byte array
    */
    protected byte[] getPacket(){
        return packet;
    }
    
    protected byte[] getPacketWithHeader(){
        return packetWithHeader;
    }
    
    private byte[] packet;
    private byte[] header;
    private byte[] packetWithHeader;
    
}
