package ir.aut.test.logic;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    public TcpChannel(SocketAddress socketAddress, int timeout){

    }
    public TcpChannel(Socket socket,int timeout){

    }
    public byte[] read(final int count){

    }
    public void write(byte[] data){

    }
    public boolean isConnected(){

    }
    public void closeChannel(){
        
    }

}
