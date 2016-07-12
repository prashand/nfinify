/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kademlia.simulations;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import kademlia.JKademliaNode;
import kademlia.node.KademliaId;
import kademlia.node.Node;

/**
 *
 * @author lumesh
 */
public class Nfinity {
    public static void main(String[] args) {
        try {
            JKademliaNode kad1 = new JKademliaNode("JoshuaK", new KademliaId("BBBBBBBBBBBBBBBBBBBB"), 7572);
            System.out.println("Created Node Kad 1: " + kad1.getNode().getNodeId());
            
            //kad1.bootstrap(new Node(new KademliaId(), InetAddress.getByName("192.168.1.3"), 7572));
        } catch (IOException ex) {
            Logger.getLogger(Nfinity.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
