// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/java/nilian/online/message/GameMessage.proto
// Protobuf Java Version: 4.28.2

package nilian.online.message;

public interface PlayerMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:PlayerMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.PlayerMessageType type = 1;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.PlayerMessageType type = 1;</code>
   * @return The type.
   */
  nilian.online.message.PlayerMessageType getType();

  /**
   * <code>int64 timestamp = 2;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>int64 player_hash = 3;</code>
   * @return The playerHash.
   */
  long getPlayerHash();

  /**
   * <code>int32 x = 4;</code>
   * @return The x.
   */
  int getX();

  /**
   * <code>int32 y = 5;</code>
   * @return The y.
   */
  int getY();

  /**
   * <code>string suit_code = 6;</code>
   * @return The suitCode.
   */
  java.lang.String getSuitCode();
  /**
   * <code>string suit_code = 6;</code>
   * @return The bytes for suitCode.
   */
  com.google.protobuf.ByteString
      getSuitCodeBytes();

  /**
   * <code>string name = 7;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 7;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string nameColor = 8;</code>
   * @return The nameColor.
   */
  java.lang.String getNameColor();
  /**
   * <code>string nameColor = 8;</code>
   * @return The bytes for nameColor.
   */
  com.google.protobuf.ByteString
      getNameColorBytes();

  /**
   * <code>string state = 9;</code>
   * @return The state.
   */
  java.lang.String getState();
  /**
   * <code>string state = 9;</code>
   * @return The bytes for state.
   */
  com.google.protobuf.ByteString
      getStateBytes();

  /**
   * <code>string orientation = 10;</code>
   * @return The orientation.
   */
  java.lang.String getOrientation();
  /**
   * <code>string orientation = 10;</code>
   * @return The bytes for orientation.
   */
  com.google.protobuf.ByteString
      getOrientationBytes();

  /**
   * <code>int32 teamCode = 11;</code>
   * @return The teamCode.
   */
  int getTeamCode();
}
