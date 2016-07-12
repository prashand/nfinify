/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 *
 * @author lumesh
 */
public class PeerListener extends Thread
{
    private ServerSocket server;

    public PeerListener() {
        try {
            setDaemon(true);
            server = new ServerSocket(1000);
        } catch (IOException ex) {
            System.out.println("Socket creation failed : "+ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                ClientHandler client = new ClientHandler(server.accept());
                client.start();
            }catch(SocketTimeoutException ex)
            {
                System.out.println("Connection timeout "+ex.getMessage());
            }catch (IOException ex) {
                System.out.println("Accept failed "+ex.getMessage());
            }
        }
    }
    
}

class ClientHandler extends Thread
{
    Socket clientsocket;
    
    public ClientHandler(Socket socket) {
        setDaemon(true);
        this.clientsocket = socket;
        System.out.println("Connection from: "+socket.getRemoteSocketAddress());
    }

    @Override
    public void run() {
        try
        {
            DataOutputStream output = new DataOutputStream(clientsocket.getOutputStream());
            output.writeUTF("Response=> MyPeerID: "+GUI.MyPeerID);
            clientsocket.close();
        }catch(IOException ex)
        {
            System.out.println("Client handler: IO Error "+ex.getMessage());
        }
    }
    
    
}