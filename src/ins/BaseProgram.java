/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;
// TCPClient.java
// A client program implementing TCP socket
import java.net.*; 
import java.io.*; 


public class BaseProgram { 
 public static int HEADERSIZE = 10;
 
	public static void main (String args[]) 
	{// arguments supply message and hostname of destination
                Config config = Config.getInstance();
                config.setConfig();

                HostProgram h = new HostProgram();
                IPProtocol ipp = new IPProtocol();
//		try{ 
//                        //config settings
//                        BufferedReader inFromUser = new BufferedReader(new FileReader("config.txt"));
//                        String ip = inFromUser.readLine();
//                        int serverPort = Integer.parseInt(inFromUser.readLine());
//                        int packetSize = Integer.parseInt(inFromUser.readLine());
//
//                        File file = new File("test.txt");
//                        s = new Socket(ip, serverPort); 
//                        DataInputStream input = new DataInputStream( s.getInputStream()); 
//                        DataOutputStream output = new DataOutputStream( s.getOutputStream()); 
//   
//                        int packets = (int)file.length() % 1000 > 0 ? (int)(file.length()/packetSize) +1 : (int)file.length()/packetSize;
//                            
//                            System.out.println("Writing.......");
//                            //Step 1 send length
//                            output.writeInt((int)file.length() + (HEADERSIZE * packets)); // UTF is a string encoding
//                            FileInputStream in = new FileInputStream(file);
//                            int count;
//                            byte[] packet = new byte[1000];
//                            int packetNum = 1;
//                            while ((count = in.read(packet)) > 0)
//                            {
//                                // move to packet class
//                                String headerString = "Packet " + String.format("%02d", packetNum)+ ".";
//                                byte[] header = new byte[HEADERSIZE];                          
//                                for(int i=0; i < headerString.length(); i++){  
//                                    header = headerString.getBytes();  
//                                }
//                                Packet p = new Packet(packet, header);
//                                pq.add(p);
//                                packetNum++;
//                            }
//                            
//                            while(pq.isEmpty() == false){
//                                Packet sendPacket = pq.remove();
//                                output.write(sendPacket.getPacket(), 0, sendPacket.getPacket().length);
//                            }
                            //read response from server
                          //Step 1 read length
			  //int nb = input.readInt();
			  //byte[] digit = new byte[nb];
                          //System.out.println(digit);
			  //Step 2 read byte
			  //for(int i = 0; i < nb; i++)
			//	digit[i] = input.readByte();
		  
			  //String st = new String(digit);
		  //System.out.println("Received: "+ st); 
//		}
//		catch (UnknownHostException e){ 
//			System.out.println("Sock:"+e.getMessage());}
//		catch (EOFException e){
//			System.out.println("EOF:"+e.getMessage()); }
//		catch (IOException e){
//			System.out.println("IO:"+e.getMessage());} 
//		finally {
//			  if(s!=null) 
//                    try {
//                        s.close();
//                    } 
//                    catch (IOException e) {/*close failed*/}
//                }
       }
}


