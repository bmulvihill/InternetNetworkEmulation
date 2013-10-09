/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ins;
// TCPClient.java
// A client program implementing TCP socket
import java.net.*; 
import java.io.*; 


public class InternetNetworkEmulation { 

	public static void main (String args[]) 
	{// arguments supply message and hostname of destination
                Config config = Config.getInstance();
                config.setConfig();

                HostProgram h = new HostProgram();
                IPProtocol ipp = new IPProtocol();
       }
}


