package ir.aut.test;

import ir.aut.test.view.BattleShipFrame;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BattleShipFrame gui = new BattleShipFrame();
        while (gui.isActive()) {
            while (selectedValue.equals(" ")) {

            }
            System.out.println("xenophobia");
            System.out.println("Object = " + selectedValue);
            if (selectedValue.equals("Online")) {
                selectedValue = " ";
                while (ready != 1) {

                }
                try {
                    me = new BattleShipClient();
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
                        c.removeAll();
                        d.removeAll();
                        players[you] = new Player(user);
                        players[enemy] = new Player("Computer");
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
