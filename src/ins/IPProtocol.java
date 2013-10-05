/*
 * Emulates IP Protocols
 */
package ins;

import java.io.*;

/**
 *
 * @author bmulvihill
 */
public class IPProtocol {
    public IPProtocol(){
        //IPReceiverServer ipServ = new IPReceiverServer();
        //ipServ.start();
        IPForwarder ipFwd = new IPForwarder();
        ipFwd.start();
    } 
}
