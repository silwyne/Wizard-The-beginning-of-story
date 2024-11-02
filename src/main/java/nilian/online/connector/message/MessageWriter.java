package nilian.online.connector.message;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MessageWriter {

    private final Socket socket;

    public MessageWriter(Socket socket) {
        this.socket = socket;
    }

    public void send(Message message) {
        int messageSize = message.getSerializedSize();
        int totalSize = CodedOutputStream.computeUInt32SizeNoTag(messageSize) + messageSize;
        byte[] data = new byte[totalSize];
        CodedOutputStream output = CodedOutputStream.newInstance(data);

        try {
            output.writeUInt32NoTag(messageSize);
            message.writeTo(output);
            output.checkNoSpaceLeft();
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize message", e);
        }

        try {
            OutputStream socketOutputStream = socket.getOutputStream();
            socketOutputStream.write(data);
            socketOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}