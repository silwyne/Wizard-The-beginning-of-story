package nilian.online.connector.host;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    //List of Shared Connections between all Clients!
    public static ArrayList<ClientHandler> allOtherClients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername ;

    public ClientHandler(Socket socket)
    {
        try
        {
            this.socket = socket ;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            allOtherClients.add(this) ;
            broadcastMessage("SERVER: "+ clientUsername+" has entered the game!") ;
        } catch(IOException e) {
            closeEverything();
        }
    }


    /**
     * This runnable listens for clients messages and broadcasts them to other clients!
     */
    @Override
    public void run() {
        String messageFromClient;
        System.out.println("ClientHandler: waiting for new messages");
        while(socket.isConnected())
        {
            try {
                messageFromClient = bufferedReader.readLine();
                // printing the received message
                System.out.println(messageFromClient);
                // processing the message
                processReceivedMessage(messageFromClient);
            }catch(IOException e)
            {
                closeEverything();
                break ;
            }
        }
    }

    private void processReceivedMessage(String message) {
        String[] arr = message.split(",");

        if(arr[0].equals("INTRO")) {
            // TODO: New Client Connected
        }
        broadcastMessage(message);
    }


    /**
     * This broadCasts a message to all connected Clients!
     * @param message some nilian.client.client message to other Clients
     */
    public void broadcastMessage(String message)
    {
        for(ClientHandler client : allOtherClients)
        {
            try
            {
                if(!client.clientUsername.equals(clientUsername))
                {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }catch(IOException e)
            {
                closeEverything() ;
            }
        }
    }


    public void removeClient()
    {
        allOtherClients.remove(this);
        broadcastMessage("SERVER: "+clientUsername+" has left the game");
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
