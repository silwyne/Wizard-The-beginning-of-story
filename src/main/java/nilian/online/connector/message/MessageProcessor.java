package nilian.online.connector.message;

import com.google.protobuf.Message;

/**
 * Processes Messages after they get read
 */
public interface MessageProcessor<T extends Message> {

    void process(T message);
}
