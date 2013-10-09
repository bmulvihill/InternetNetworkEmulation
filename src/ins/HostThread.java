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
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        Logger.log(e.getMessage());
                    }
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
            Logger.log("User Command: " + args[1]);
            BufferedReader inFromUser = new BufferedReader(new FileReader(file));
            int packets = (int)f.length() % 1000 > 0 ? (int)(f.length()/packetSize) +1 : (int)f.length()/packetSize;           
            Logger.log("File is " + f.length() + " bytes and will be sent in " + packets + " packets");
            
            FileInputStream in = new FileInputStream(f);
            int count;
            byte[] packet = new byte[packetSize];
            int packetNum = 1;
            while ((count = in.read(packet)) > 0){
               
                HashMap header = new HashMap();
                header.put("I", destIP);
                header.put("P", config.serverPort);
                header.put("F", String.format("%04d", f.length()));
                header.put("S", String.format("%04d", count));
                header.put("N", packetNum);
                header.put("T", packets);
                header.put("H", config.hostIP);
                header.put("FNAME", f.getName());
                Packet p = new Packet(packet, header);
                pq.add(p);
                packetNum++;
            }

        } 
        catch (FileNotFoundException e) {
            Logger.log(e.getMessage());
            System.out.println(e.getMessage());
        } 
        catch (IOException e) {
            Logger.log(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
