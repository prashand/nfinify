/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import org.apache.commons.net.util.SubnetUtils;

/**
 *
 * @author lumesh
 */
public class FindPeer
{
    private String RemotePeerID = null;
    
    public FindPeer()
    {
    }
    
    public String ScanNetwork()
    {
        try
        {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
            {
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for(InetAddress inetAddress : Collections.list(inetAddresses))
                {
                    if(!inetAddress.getClass().toString().equals("class java.net.Inet6Address"))
                    {
                        if(!inetAddress.isLoopbackAddress())
                        {
                            try
                            {
                            SubnetUtils utils = new SubnetUtils(inetAddress.getHostAddress()+"/"+netint.getInterfaceAddresses().get(1).getNetworkPrefixLength());
                            String[] allIps = utils.getInfo().getAllAddresses();
                            return ConnectIP(allIps, inetAddress);
                            }catch(IllegalArgumentException ex)
                            {
                                int prefix = NetworkInterface.getByInetAddress(inetAddress).getInterfaceAddresses().get(0).getNetworkPrefixLength();
                                if(!inetAddress.isLoopbackAddress())
                                {
                                    System.out.println("IP: "+inetAddress.getHostAddress()+" Prefix: "+prefix);
                                    SubnetUtils utils = new SubnetUtils(inetAddress.getHostAddress()+"/"+prefix);
                                    String[] allIps = utils.getInfo().getAllAddresses();
                                    return ConnectIP(allIps, inetAddress);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketException ex)
        {
            System.out.println("Connection failed "+ex.getMessage());
        }
        return "Peer not found";
    }
    
    private String ConnectIP(String[] allIps,InetAddress inetAddress)
    {
        for(String ip:allIps)
        {
            if(!ip.equals(inetAddress.getHostAddress()))
            {
                System.out.println(ip);
                if (ConnectPeerListener(ip))
                {
                    return ip;
                }
            }
        }
        return "Peer not found";
    }
    
    public String GetRemotePeerID()
    {
        return RemotePeerID;
    }
    
    public Boolean ConnectPeerListener(String ip)
    {
        try {
            //Soocket = socket = new Socket(ip, 1000);
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, 1000), 1000);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            String data = input.readUTF();
            System.out.println(data);
            String rspdata[]=data.split(" ");
            if(!rspdata[2].equals(""))
            {
                RemotePeerID = rspdata[2];
                return true;
            }
            socket.close();
        }catch (IOException ex) {
            System.out.println("Connection failed "+ex.getMessage());
        }
        return false;
    }
}