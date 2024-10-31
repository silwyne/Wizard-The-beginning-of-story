package nilian.online.connector.message;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import nilian.online.message.ServerMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Listens for incoming Messages
 */
public class MessageListener {

    private final MessageProcessor messageProcessor;
    private final Socket socket;
    private Thread listener;
    private volatile boolean running = false;

    public MessageListener(Socket socket, MessageProcessor messageProcessor) {
        this.socket = socket;
        this.messageProcessor = messageProcessor;
    }

    public void start() throws IllegalStateException {
        if (listener != null && listener.isAlive()) {
            throw new IllegalStateException("Already listening for messages");
        }

        running = true;
        listener = new Thread(this::listenForMessages);
        listener.start();
    }

    public void stop() {
        running = false;
        if (listener != null) {
            listener.interrupt();
        }
    }

    private void listenForMessages() {
        try (InputStream inputStream = socket.getInputStream()) {
            CodedInputStream in = CodedInputStream.newInstance(inputStream);

            while (running && !Thread.currentThread().isInterrupted()) {
                try {
                    if (in.isAtEnd()) {
                        break;
                    }

                    // Read the size of the message
                    int messageSize = in.readUInt32();

                    // Read the message bytes
                    byte[] messageBytes = in.readRawBytes(messageSize);

                    // Parse the message
                    ServerMessage message = ServerMessage.parseFrom(messageBytes);

                    // Process the message
                    messageProcessor.process(message);

                } catch (InvalidProtocolBufferException e) {
                    System.err.println("Invalid protocol buffer: " + e.getMessage());
                } catch (IOException e) {
                    if (running) {
                        System.err.println("I/O error: " + e.getMessage());
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error setting up input stream: " + e.getMessage());
        } finally {
            System.out.println("Stopped listening for messages");
        }
    }
}