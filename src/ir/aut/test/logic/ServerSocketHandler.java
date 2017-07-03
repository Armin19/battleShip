package ir.aut.test.logic;


public class ServerSocketHandler extends Thread {
    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback, IServerSocketHandlerCallback iServerSocketHandlerCallback) {

    }

    @Override
    public void run() {

    }

    public void stop() {

    }

    public interface IServerSocketHandlerCallback {
        void onNewConnectionReceived(NetworkHandler networkHandler);
    }
}
