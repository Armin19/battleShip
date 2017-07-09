package ir.aut.test.logic;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;

    public NetworkHandler(SocketAddress socketAddress, INetworkHandlerCallback iNetworkHandlerCallback) {

    }

    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback) {

    }

    public void sendMessage(BaseMessage baseMessage) {

    }

    public void stop() {

    }

    private byte[] readChannel() {

    }

    private class ReceivedMessageConsumer extends Thread {


        @Override
        public void run() {

        }
    }

    ServerSocket serverSocket;

    private HostComponent component;

    public static int PORT = 4444;

    public NetworkHandler(HostComponent component) {
        this.component = component;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("listening on port: " + PORT);
                final Socket s = serverSocket.accept();
                System.out.println("connection made with: " + s);
                new Thread() {
                    public void run() {
                        component.connectionReceived(s);
                    }
                }.start();
                ;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("listener is closing....");
    }

    public interface INetworkHandlerCallback {
        void onMessageReceived(BaseMessage baseMessage);

        void onSocketClosed();
    }
}
