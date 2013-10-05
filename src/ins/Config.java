/*
 * Singleton object stores all config settings
 */
package ins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                BufferedReader inFromUser = new BufferedReader(new FileReader("/Users/bmulvihill/Desktop/config.txt"));
                hostIP = inFromUser.readLine();
                serverPort = Integer.parseInt(inFromUser.readLine());
                packetSize = Integer.parseInt(inFromUser.readLine());
            }
            catch (FileNotFoundException e){
                //..
            }
            catch (IOException e){
                //..
            }
    }
    
    protected String hostIP;
    protected int serverPort;
    protected int packetSize;
}