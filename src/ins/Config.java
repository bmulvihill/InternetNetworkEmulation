/*
 * Singleton object stores all config settings
 */
package ins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;

/**
 *
 * @author bmulvihill
 */

public class Config {
    private static Config instance = null;
    
    protected Config() {}
    
    public static Config getInstance() {
      if(instance == null) {
         instance = new Config();
      }
      return instance;
    }
    
    protected void setConfig(){
            try{
                BufferedReader inFromUser = new BufferedReader(new FileReader("config.txt"));
                destIP = inFromUser.readLine();
                serverPort = Integer.parseInt(inFromUser.readLine());
                packetSize = Integer.parseInt(inFromUser.readLine());
                hostIP = Inet4Address.getLocalHost().getHostAddress().toString();
            }
            catch (FileNotFoundException e){
                //..
            }
            catch (IOException e){
                //..
            }
    }
    
    protected String destIP;
    protected String hostIP;
    protected int serverPort;
    protected int packetSize;
}