package nilian.online.connector.message;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Message;

import java.io.IOException;
import java.net.Socket;

public class MessageWriter {

    private final Socket socket;

    public MessageWriter(Socket socket) {
        this.socket = socket;
    }

    public void send(Message message) {
        byte[] data = message.toByteArray();
        CodedOutputStream output = CodedOutputStream.newInstance(data);
        try {
            output.writeUInt32NoTag(message.getSerializedSize());
            message.writeTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
