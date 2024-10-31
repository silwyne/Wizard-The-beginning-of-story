package nilian.online.connector.host;


import com.google.protobuf.Message;
import nilian.online.connector.message.MessageListener;
import nilian.online.connector.message.MessageProcessor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {

    //List of Shared Connections between all Clients!
    public static ArrayList<ClientHandler> allOtherClients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername ;
    public String clientHashcode ;
    private MessageListener messageListener;

    public ClientHandler(Socket socket)
    {
        this.socket = socket ;
        allOtherClients.add(this) ;
        clientHashcode = (System.currentTimeMillis() * 33)+"";
    }

    public void startMessageListener() {
        messageListener = new MessageListener(socket, new MessageProcessor() {
            @Override
            public void process(Message message) {
                for(ClientHandler client : allOtherClients)
                {
                    if(client != null && message != null) {
                        if(!client.clientHashcode.equals(clientHashcode))
                        {

                        }
                    }
                }
            }
        });
        messageListener.start();
    }


    public void removeClient()
    {
        allOtherClients.remove(this);
//        broadcastMessage("SERVER: "+clientUsername+" has left the game");
    }


    public void closeEverything()
    {
        removeClient();
        try
        {
            if(bufferedReader != null)
            {
                bufferedReader.close();
            }
            if(bufferedWriter != null)
            {
                bufferedWriter.close();
            }
            if(socket != null)
            {
                socket.close();
            }
        } catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

}
