// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/java/nilian/online/message/GameMessage.proto
// Protobuf Java Version: 4.28.2

package nilian.online.message;

public interface GameConfigMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:GameConfigMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string map = 1;</code>
   * @return The map.
   */
  java.lang.String getMap();
  /**
   * <code>string map = 1;</code>
   * @return The bytes for map.
   */
  com.google.protobuf.ByteString
      getMapBytes();

  /**
   * <code>string theme = 2;</code>
   * @return The theme.
   */
  java.lang.String getTheme();
  /**
   * <code>string theme = 2;</code>
   * @return The bytes for theme.
   */
  com.google.protobuf.ByteString
      getThemeBytes();

  /**
   * <code>.GameModeType mode = 3;</code>
   * @return The enum numeric value on the wire for mode.
   */
  int getModeValue();
  /**
   * <code>.GameModeType mode = 3;</code>
   * @return The mode.
   */
  nilian.online.message.GameModeType getMode();

  /**
   * <code>int64 timestamp = 4;</code>
   * @return The timestamp.
   */
  long getTimestamp();
}
