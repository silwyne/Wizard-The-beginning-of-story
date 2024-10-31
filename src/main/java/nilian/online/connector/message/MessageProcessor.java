package nilian.online.connector.message;

import com.google.protobuf.Message;

/**
 * Processes Messages after they get read
 */
public interface MessageProcessor {

    void process(Message message);
}
