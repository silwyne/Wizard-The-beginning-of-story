package nilian.online.connector.joiner;

import java.io.*;
import java.net.Socket;

public class GameClient {

    private Socket socket;
    private final String serverIP;
    private final int serverPort;

    private BufferedReader bufferedReader ;
    private BufferedWriter bufferedWriter;
    private final String username ;
    private final int userHash ;

    private Thread listener;

    public GameClient(String username, String serverIP, int serverPort) {
        this.username = username;
        this.serverIP = serverIP;
        this.serverPort = serverPort;

        // generate unique hash for user
        userHash = ((System.currentTimeMillis() * 100 + username).hashCode());
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


    /**
    * Simply sends message to server.
    * @param message message string!
    */
    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
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
    public void listenForMessage() {
        if(listener == null) {
            listener = new Thread(() -> {
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
            });
            listener.start();
        } else {
            try {
                throw new Exception("it is already listening for new messages");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void introduceToServer() {
        String introductionMessage =
                "INTRO,"+ username+","+userHash;

        sendMessage(introductionMessage);
    }
}