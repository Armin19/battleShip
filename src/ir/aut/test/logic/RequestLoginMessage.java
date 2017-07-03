package ir.aut.test.logic;


import java.nio.ByteBuffer;

public class RequestLoginMessage extends BaseMessage {
    private String mUsername;
    private String mPassword;

    public RequestLoginMessage(String username, String password) {
        mUsername = username;
        mPassword = password;
        serialize();
    }

    public RequestLoginMessage(byte[] serialized) {
        mSerialized = serialized;
        deserialize();
    }

    @Override
    protected void serialize() {
        int usernameLength = mUsername.length();
        int passwordLength = mPassword.length();
        int messageLength = 4 + 1 + 1 + 4 + usernameLength + 4 + passwordLength;
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageLength);
        byteBuffer.putInt(messageLength);
        byteBuffer.put(MessageTypes.PROTOCOL_VERSON);
        byteBuffer.put(MessageTypes.REQUEST_LOGIN);
        byteBuffer.putInt(usernameLength);
        byteBuffer.put(mUsername.getBytes());
        byteBuffer.putInt(passwordLength);
        byteBuffer.put(mPassword.getBytes());
        mSerialized = byteBuffer.array();
    }
}
