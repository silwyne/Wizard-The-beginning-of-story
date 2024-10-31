// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: src/main/java/nilian/online/message/GameMessage.proto
// Protobuf Java Version: 4.28.2

package nilian.online.message;

/**
 * Protobuf type {@code GameConfigMessage}
 */
public final class GameConfigMessage extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:GameConfigMessage)
    GameConfigMessageOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 28,
      /* patch= */ 2,
      /* suffix= */ "",
      GameConfigMessage.class.getName());
  }
  // Use GameConfigMessage.newBuilder() to construct.
  private GameConfigMessage(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GameConfigMessage() {
    map_ = "";
    theme_ = "";
    mode_ = 0;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return nilian.online.message.GameMessage.internal_static_GameConfigMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return nilian.online.message.GameMessage.internal_static_GameConfigMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            nilian.online.message.GameConfigMessage.class, nilian.online.message.GameConfigMessage.Builder.class);
  }

  public static final int MAP_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object map_ = "";
  /**
   * <code>string map = 1;</code>
   * @return The map.
   */
  @java.lang.Override
  public java.lang.String getMap() {
    java.lang.Object ref = map_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      map_ = s;
      return s;
    }
  }
  /**
   * <code>string map = 1;</code>
   * @return The bytes for map.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getMapBytes() {
    java.lang.Object ref = map_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      map_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int THEME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object theme_ = "";
  /**
   * <code>string theme = 2;</code>
   * @return The theme.
   */
  @java.lang.Override
  public java.lang.String getTheme() {
    java.lang.Object ref = theme_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      theme_ = s;
      return s;
    }
  }
  /**
   * <code>string theme = 2;</code>
   * @return The bytes for theme.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getThemeBytes() {
    java.lang.Object ref = theme_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      theme_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MODE_FIELD_NUMBER = 3;
  private int mode_ = 0;
  /**
   * <code>.GameModeType mode = 3;</code>
   * @return The enum numeric value on the wire for mode.
   */
  @java.lang.Override public int getModeValue() {
    return mode_;
  }
  /**
   * <code>.GameModeType mode = 3;</code>
   * @return The mode.
   */
  @java.lang.Override public nilian.online.message.GameModeType getMode() {
    nilian.online.message.GameModeType result = nilian.online.message.GameModeType.forNumber(mode_);
    return result == null ? nilian.online.message.GameModeType.UNRECOGNIZED : result;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 4;
  private long timestamp_ = 0L;
  /**
   * <code>int64 timestamp = 4;</code>
   * @return The timestamp.
   */
  @java.lang.Override
  public long getTimestamp() {
    return timestamp_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(map_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, map_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(theme_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, theme_);
    }
    if (mode_ != nilian.online.message.GameModeType.GAME_MODE_TYPE_UNSPECIFIED.getNumber()) {
      output.writeEnum(3, mode_);
    }
    if (timestamp_ != 0L) {
      output.writeInt64(4, timestamp_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(map_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, map_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(theme_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, theme_);
    }
    if (mode_ != nilian.online.message.GameModeType.GAME_MODE_TYPE_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, mode_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, timestamp_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof nilian.online.message.GameConfigMessage)) {
      return super.equals(obj);
    }
    nilian.online.message.GameConfigMessage other = (nilian.online.message.GameConfigMessage) obj;

    if (!getMap()
        .equals(other.getMap())) return false;
    if (!getTheme()
        .equals(other.getTheme())) return false;
    if (mode_ != other.mode_) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MAP_FIELD_NUMBER;
    hash = (53 * hash) + getMap().hashCode();
    hash = (37 * hash) + THEME_FIELD_NUMBER;
    hash = (53 * hash) + getTheme().hashCode();
    hash = (37 * hash) + MODE_FIELD_NUMBER;
    hash = (53 * hash) + mode_;
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static nilian.online.message.GameConfigMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static nilian.online.message.GameConfigMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static nilian.online.message.GameConfigMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static nilian.online.message.GameConfigMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(nilian.online.message.GameConfigMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code GameConfigMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:GameConfigMessage)
      nilian.online.message.GameConfigMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return nilian.online.message.GameMessage.internal_static_GameConfigMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return nilian.online.message.GameMessage.internal_static_GameConfigMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              nilian.online.message.GameConfigMessage.class, nilian.online.message.GameConfigMessage.Builder.class);
    }

    // Construct using nilian.online.message.GameConfigMessage.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      map_ = "";
      theme_ = "";
      mode_ = 0;
      timestamp_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return nilian.online.message.GameMessage.internal_static_GameConfigMessage_descriptor;
    }

    @java.lang.Override
    public nilian.online.message.GameConfigMessage getDefaultInstanceForType() {
      return nilian.online.message.GameConfigMessage.getDefaultInstance();
    }

    @java.lang.Override
    public nilian.online.message.GameConfigMessage build() {
      nilian.online.message.GameConfigMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public nilian.online.message.GameConfigMessage buildPartial() {
      nilian.online.message.GameConfigMessage result = new nilian.online.message.GameConfigMessage(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(nilian.online.message.GameConfigMessage result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.map_ = map_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.theme_ = theme_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.mode_ = mode_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.timestamp_ = timestamp_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof nilian.online.message.GameConfigMessage) {
        return mergeFrom((nilian.online.message.GameConfigMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(nilian.online.message.GameConfigMessage other) {
      if (other == nilian.online.message.GameConfigMessage.getDefaultInstance()) return this;
      if (!other.getMap().isEmpty()) {
        map_ = other.map_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getTheme().isEmpty()) {
        theme_ = other.theme_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.mode_ != 0) {
        setModeValue(other.getModeValue());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              map_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              theme_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              mode_ = input.readEnum();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              timestamp_ = input.readInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object map_ = "";
    /**
     * <code>string map = 1;</code>
     * @return The map.
     */
    public java.lang.String getMap() {
      java.lang.Object ref = map_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        map_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string map = 1;</code>
     * @return The bytes for map.
     */
    public com.google.protobuf.ByteString
        getMapBytes() {
      java.lang.Object ref = map_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        map_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string map = 1;</code>
     * @param value The map to set.
     * @return This builder for chaining.
     */
    public Builder setMap(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      map_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string map = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMap() {
      map_ = getDefaultInstance().getMap();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string map = 1;</code>
     * @param value The bytes for map to set.
     * @return This builder for chaining.
     */
    public Builder setMapBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      map_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object theme_ = "";
    /**
     * <code>string theme = 2;</code>
     * @return The theme.
     */
    public java.lang.String getTheme() {
      java.lang.Object ref = theme_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        theme_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string theme = 2;</code>
     * @return The bytes for theme.
     */
    public com.google.protobuf.ByteString
        getThemeBytes() {
      java.lang.Object ref = theme_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        theme_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string theme = 2;</code>
     * @param value The theme to set.
     * @return This builder for chaining.
     */
    public Builder setTheme(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      theme_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string theme = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTheme() {
      theme_ = getDefaultInstance().getTheme();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string theme = 2;</code>
     * @param value The bytes for theme to set.
     * @return This builder for chaining.
     */
    public Builder setThemeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      theme_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private int mode_ = 0;
    /**
     * <code>.GameModeType mode = 3;</code>
     * @return The enum numeric value on the wire for mode.
     */
    @java.lang.Override public int getModeValue() {
      return mode_;
    }
    /**
     * <code>.GameModeType mode = 3;</code>
     * @param value The enum numeric value on the wire for mode to set.
     * @return This builder for chaining.
     */
    public Builder setModeValue(int value) {
      mode_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>.GameModeType mode = 3;</code>
     * @return The mode.
     */
    @java.lang.Override
    public nilian.online.message.GameModeType getMode() {
      nilian.online.message.GameModeType result = nilian.online.message.GameModeType.forNumber(mode_);
      return result == null ? nilian.online.message.GameModeType.UNRECOGNIZED : result;
    }
    /**
     * <code>.GameModeType mode = 3;</code>
     * @param value The mode to set.
     * @return This builder for chaining.
     */
    public Builder setMode(nilian.online.message.GameModeType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000004;
      mode_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.GameModeType mode = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMode() {
      bitField0_ = (bitField0_ & ~0x00000004);
      mode_ = 0;
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <code>int64 timestamp = 4;</code>
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <code>int64 timestamp = 4;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int64 timestamp = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000008);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:GameConfigMessage)
  }

  // @@protoc_insertion_point(class_scope:GameConfigMessage)
  private static final nilian.online.message.GameConfigMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new nilian.online.message.GameConfigMessage();
  }

  public static nilian.online.message.GameConfigMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GameConfigMessage>
      PARSER = new com.google.protobuf.AbstractParser<GameConfigMessage>() {
    @java.lang.Override
    public GameConfigMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<GameConfigMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GameConfigMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public nilian.online.message.GameConfigMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

