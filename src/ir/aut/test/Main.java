package ir.aut.test;

import com.sun.corba.se.impl.protocol.giopmsgheaders.FragmentMessage;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import com.sun.corba.se.spi.ior.iiop.GIOPVersion;
import ir.aut.test.logic.*;
import ir.aut.test.view.BattleShipFrame;
import ir.aut.test.view.BattleshipClient;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

import static ir.aut.test.view.BattleShipFrame.b;
import static ir.aut.test.view.BattleShipFrame.d;


public class Main {

    private static boolean gameover;
    private static int you;

    public static void main(String[] args) {
        // write your code here
        MessageTypes messageTypes = new MessageTypes();
        NetworkHandler networkHandler = new NetworkHandler();
        RequestLoginMessage requestLoginMessage = new RequestLoginMessage();
        ServerSocketHandler serverSocketHandler = new ServerSocketHandler();
        TcpChannel tcpChannel = new TcpChannel();
        BaseMessage baseMessage = new BaseMessage() {
            @Override
            protected void serialize() {

            }

            @Override
            protected void deserialize() {

            }

            @Override
            public byte getMessageType() {
                return 0;
            }

            @Override
            public GIOPVersion getGIOPVersion() {
                return null;
            }

            @Override
            public byte getEncodingVersion() {
                return 0;
            }

            @Override
            public boolean isLittleEndian() {
                return false;
            }

            @Override
            public boolean moreFragmentsToFollow() {
                return false;
            }

            @Override
            public int getType() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public ByteBuffer getByteBuffer() {
                return null;
            }

            @Override
            public int getThreadPoolToUse() {
                return 0;
            }

            @Override
            public void read(InputStream istream) {

            }

            @Override
            public void write(OutputStream ostream) {

            }

            @Override
            public void setSize(ByteBuffer byteBuffer, int size) {

            }

            @Override
            public FragmentMessage createFragmentMessage() {
                return null;
            }

            @Override
            public void callback(MessageHandler handler) throws IOException {

            }

            @Override
            public void setByteBuffer(ByteBuffer byteBuffer) {

            }

            @Override
            public void setEncodingVersion(byte version) {

            }
        }
        BattleshipClient me = new BattleshipClient();
        BattleShipFrame gui = new BattleShipFrame();
        while (gui.isActive()) {
            String selectedValue;
            while (selectedValue.equals(" ")) {

            }
            System.out.println("xenophobia");
            System.out.println("Object = " + selectedValue);
            if (selectedValue.equals("Online")) {
                selectedValue = " ";
                int ready;
                while (ready != 1) {

                }
                try {
                    me = new BattleshipClient();
                    if (!me.getServerName().equals("invalid")) {
                        me.sendShips();
                        while (!gameover) {
                            if (!players[you].getMove()) {
                                try {
                                    me.listen();
                                } catch (IOException e) {
                                    System.out.println("Aw naw.");
                                }
                            }
                            while (players[you].getMove()) {

                            }
                            me.results();
                        }
                    } else {
                        b.removeAll();
                        gui.c.removeAll();
                        d.removeAll();
                        players[you] = new Player(user);
                        players[gui.enemy] = new Player("Computer");
                        b.add(gui.setBoard(you), BorderLayout.CENTER);
                        inputpanel = gui.shipinput();
                        d.add(inputpanel, BorderLayout.NORTH);
                        gui.pack();
                        gui.repaint();
                    }
                } catch (IOException e) {
                    System.out.println("  ");
                }
            }
        }//System.out.println("okay");
    }
}
