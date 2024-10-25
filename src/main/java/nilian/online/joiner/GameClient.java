package nilian.online.joiner;

import java.io.*;
import java.net.Socket;

public class GameClient {

    private Socket socket;
    private final String serverIP;
    private final int serverPort;

    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private final String username ;

    private Thread listener;

    public GameClient(String username, String serverIP, int serverPort) throws IOException {
        this.username = username;
        this.serverIP = serverIP;
        this.serverPort = serverPort;

        // first try to connect
        connect();

        // listen for incoming messages
        listenForMessage();
    }

    /**
     * Connects you to GameServer
     * @throws IOException I don't know
     */
    public void connect() throws IOException {
        socket = new Socket(serverIP, serverPort);

        // getting reader and writer to server
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Connected to server at " + serverIP + ":" + serverPort);
    }


    /**
     * Simply sends message to server.
     * @param s message string!
     */
    public void sendMessage(String s)
    {
        try {
            bufferedWriter.write(username + ": " + s);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
    }


    /**
     * Listens for other Clients messages!
     * Actually from server which broadcasts other client messages  to me!
     */
    public void listenForMessage()
    {
        listener = new Thread(new Runnable() {
            @Override
            public void run() {
                String incomingMessage ;
                while(socket.isConnected())
                {
                    try {
                        incomingMessage = bufferedReader.readLine();
                        System.out.println(incomingMessage);
                    } catch(IOException e)
                    {
                        closeEverything() ;
                    }
                }
            }

        });

        listener.start();
    }


    /**
     * Closes all Connection and Objects!
     */
    public void closeEverything()
    {
        try
        {
            if(bufferedReader != null) {
                bufferedReader.close();

            } if(bufferedWriter != null) {
                bufferedWriter.close();

            } if(socket != null) {
                socket.close();
            }
        } catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }
}