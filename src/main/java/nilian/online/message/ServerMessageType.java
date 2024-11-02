// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/java/nilian/online/message/GameMessage.proto
// Protobuf Java Version: 4.28.2

package nilian.online.message;

/**
 * <pre>
 * Server messages
 * </pre>
 *
 * Protobuf enum {@code ServerMessageType}
 */
public enum ServerMessageType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>SERVER_MESSAGE_TYPE_UNSPECIFIED = 0;</code>
   */
  SERVER_MESSAGE_TYPE_UNSPECIFIED(0),
  /**
   * <code>SERVER_MESSAGE_TYPE_WELCOME = 1;</code>
   */
  SERVER_MESSAGE_TYPE_WELCOME(1),
  /**
   * <code>SERVER_MESSAGE_TYPE_PLAYER = 2;</code>
   */
  SERVER_MESSAGE_TYPE_PLAYER(2),
  /**
   * <code>SERVER_MESSAGE_TYPE_GAME = 3;</code>
   */
  SERVER_MESSAGE_TYPE_GAME(3),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 28,
      /* patch= */ 2,
      /* suffix= */ "",
      ServerMessageType.class.getName());
  }
  /**
   * <code>SERVER_MESSAGE_TYPE_UNSPECIFIED = 0;</code>
   */
  public static final int SERVER_MESSAGE_TYPE_UNSPECIFIED_VALUE = 0;
  /**
   * <code>SERVER_MESSAGE_TYPE_WELCOME = 1;</code>
   */
  public static final int SERVER_MESSAGE_TYPE_WELCOME_VALUE = 1;
  /**
   * <code>SERVER_MESSAGE_TYPE_PLAYER = 2;</code>
   */
  public static final int SERVER_MESSAGE_TYPE_PLAYER_VALUE = 2;
  /**
   * <code>SERVER_MESSAGE_TYPE_GAME = 3;</code>
   */
  public static final int SERVER_MESSAGE_TYPE_GAME_VALUE = 3;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static ServerMessageType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static ServerMessageType forNumber(int value) {
    switch (value) {
      case 0: return SERVER_MESSAGE_TYPE_UNSPECIFIED;
      case 1: return SERVER_MESSAGE_TYPE_WELCOME;
      case 2: return SERVER_MESSAGE_TYPE_PLAYER;
      case 3: return SERVER_MESSAGE_TYPE_GAME;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<ServerMessageType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      ServerMessageType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<ServerMessageType>() {
          public ServerMessageType findValueByNumber(int number) {
            return ServerMessageType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return nilian.online.message.GameMessage.getDescriptor().getEnumTypes().get(2);
  }

  private static final ServerMessageType[] VALUES = values();

  public static ServerMessageType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private ServerMessageType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:ServerMessageType)
}
