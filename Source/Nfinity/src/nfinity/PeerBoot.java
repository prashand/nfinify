/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kademlia.JKademliaNode;
import kademlia.node.KademliaId;
import kademlia.node.Node;

/**
 *
 * @author 90173
 */
public class PeerBoot extends Thread{

    JKademliaNode kad=null;
    public PeerBoot(JKademliaNode kadp) {
        setDaemon(true);
        this.kad = kadp;
    }
    
    @Override
    public void run() {
        PeerListener pl = new PeerListener();
        pl.start();
        FindPeer f = new FindPeer();
        try {
            InetAddress RemotePeerIP = InetAddress.getByName(f.ScanNetwork());
            kad.bootstrap(new Node(new KademliaId(f.GetRemotePeerID()),RemotePeerIP , 7572));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
