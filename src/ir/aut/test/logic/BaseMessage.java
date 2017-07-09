package ir.aut.test.logic;


import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import java.net.URL;

public abstract class BaseMessage implements Message {

    private long id;
    private long creationTime;
    private long updateTime;
    private URL url;
    protected byte[] mSerialized;

    public static BaseMessage buildFromSerializedData(byte[] data) {

    }

    public String getChannelURL() {
        return String.valueOf(url);
    }

    public boolean isOpenChannel() {

    }

    public boolean isGroupChannel() {

    }

    public long getCreatedAt() {
        return creationTime;
    }

    public long getUpdatedAt() {
        return updateTime;
    }

    public long getMessageId() {
        return id;
    }

    protected abstract void serialize();

    protected abstract void deserialize();

    public abstract byte getMessageType();

    public byte[] getSerialized() {
        return mSerialized;
    }

}
