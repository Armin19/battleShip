package ir.aut.test.logic;


import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;
    public NetworkHandler(SocketAddress socketAddress,INetworkHandlerCallback iNetworkHandlerCallback){

    }
    public NetworkHandler(Socket socket,INetworkHandlerCallback iNetworkHandlerCallback){

    }
    public void sendMessage(BaseMessage baseMessage){

    }
    @Override public void run(){

    }
    public void stop(){

    }
    private byte[] readChannel(){

    }
    private class ReceivedMessageConsumer extends Thread{


        @Override public void run(){

        }
    }
    public interface INetworkHandlerCallback {
        void onMessageReceived(BaseMessage baseMessage);
        void onSocketClosed();
    }
}
