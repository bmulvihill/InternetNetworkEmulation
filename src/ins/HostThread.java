/*
 * This Thread reads user input and divides files into packets
 */
package ins;
import java.io.*;
import java.util.*;
/**
 *
 * @author bmulvihill
 */
public class HostThread extends Thread {
    public static int HEADERSIZE = 10;
    PacketQueue pq = PacketQueue.getInstance();
    private int packetSize;
    Config config = Config.getInstance();
    
    public HostThread () {
        packetSize = config.packetSize;
    } 
    
    @Override
    public void run(){
         while (true){
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String[] args = br.readLine().split(" ");
                if("SEND".equals(args[0].toUpperCase())){
                    parseFile(args);
                } else 
                {
                   System.out.println("Unrecognized Command"); 
                }
            } 
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void parseFile(String args[]){
        try {

            String file = args[1];
            String destIP = args[2];
            File f = new File(file);
            BufferedReader inFromUser = new BufferedReader(new FileReader(file));
            int packets = (int)f.length() % 1000 > 0 ? (int)(f.length()/packetSize) +1 : (int)f.length()/packetSize;           
            System.out.println("File is " + f.length() + " bytes and will be broken into " + packets + " packets");
            
            FileInputStream in = new FileInputStream(f);
            int count;
            byte[] packet = new byte[packetSize];
            int packetNum = 1;
            while ((count = in.read(packet)) > 0){
                HashMap header = new HashMap();
                header.put("IP", destIP);
                header.put("Port", config.serverPort);
                header.put("SeqNum", packetNum);
                header.put("TotalSize", packets);
                //"Packet: " + String.format("%02d", packetNum)+ ".";
                Packet p = new Packet(packet, header);
                pq.add(p);
                packetNum++;
            }

        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}