package ir.aut.test.logic;


import java.io.IOException;
import java.net.*;

public class ServerSocketHandler extends Thread {
    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback, IServerSocketHandlerCallback iServerSocketHandlerCallback) {

    }

    private int connectedSocketLimit = 10000;
    private RequestCallback onRequest;
    private ErrorCallback onError;

    private ServerSocket server;
    private boolean connected;
    public final ArrayList<JSONClient> clients = new ArrayList<JSONClient>();

    public void setOnRequest(RequestCallback handler) {
        onRequest = handler;
    }

    public void setOnError(ErrorCallback handler) {
        onError = handler;
    }

    public boolean isConnected() {
        return connected;
    }

    public void start(int port) throws IOException {
        server = new ServerSocket();
        server.setSoTimeout(5000);
        server.setReuseAddress(true);
        server.setPerformancePreferences(1, 0, 0);
        server.bind(new InetSocketAddress(InetAddress.getByAddress(new byte[]{
                0, 0, 0, 0}), port));

        Thread accepter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (connected) {
                    Socket client = null;
                    try {
                        client = server.accept();
                    } catch (SocketTimeoutException e) {
                        continue;
                    } catch (IOException e) {
                        if (onError != null) {
                            onError.onError(e);
                        }
                    }
                    if (client != null) {
                        handleClient(new ServerSocketHandler(client));
                    }
                }
            }
        }, "JSONServer-accepter");

        connected = true;
        accepter.start();
    }

    public void close() {
        connected = false;
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private void handleClient(final ServerSocketHandler client) {
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clients.remove(client);
                    clients.add(client);
                    client.setSoTimeout(5000);
                    while (connected) {
                        ServerSocketHandler request = client.readPacket();
                        if (request == null) {
                            break;
                        }
                        ServerSocketHandler response;
                        if (onRequest != null) {
                            response = onRequest.onRequest(request);
                        } else {
                            response = new ServerSocketHandler(new ServerSocketHandler());
                        }

                        client.writePacket(response);

                        if (clients.size() > connectedSocketLimit) {
                            break;
                        }
                    }
                } catch (SocketTimeoutException e) {
                    if (onError != null) {
                        onError.onError(e);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    client.close();
                    clients.remove(client);
                }
            }
        }, "JSONServer-clientHandler");
        clientThread.start();
    }

    public interface RequestCallback {
        public ServerSocketHandler onRequest(ServerSocketHandler request);
    }

    public interface ErrorCallback {
        public void onError(Exception e);
    }

    public interface IServerSocketHandlerCallback {
        void onNewConnectionReceived(NetworkHandler networkHandler);

    }
}
