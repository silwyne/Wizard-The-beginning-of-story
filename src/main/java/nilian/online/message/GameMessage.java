package nilian.online.message;

public class GameMessage {

    private final String message;
    private final MessageType messageType;
    private final DetailType detailType;

    public GameMessage(MessageType messageType, DetailType detailType, String ms) {
        this.messageType = messageType;
        this.detailType = detailType;
        this.message = ms;
    }

    public GameMessage(String fullMessage) {
        String[] arr = fullMessage.split(",");
        this.messageType = MessageType.valueOf(arr[0]);
        this.detailType = DetailType.valueOf(arr[1]);
        this.message = arr[2];
    }

    @Override
    public String toString() {
        return messageType.toString()+","+
                detailType.toString()+","+
                message;
    }
}
