package ir.aut.test.view;


import javax.swing.*;
import java.awt.*;

public class BattleShipFrame extends JFrame {
    private static JButton ok = new JButton("OK"), done = new JButton("Done");
    private static JFrame statistics = new JFrame("Statistics"), options = new JFrame("Options");
    private static JLabel data, title;
    private static JPanel stats = new JPanel(), opts, inputpanel;
    private static Container b, c, d;
    private JPanel input;
    private static JMenuItem m, pvp, pvc, cvc;
    private static String[] cletters = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String[] cnumbers = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static String[] ships = {"Carrier", "Battleship", "Submarine", "Destroyer", "Patrol Boat"};
    private static String[] direction = {"Horizontal", "Vertical"};
    private static String[] level = {"Normal", "Ridiculously Hard"};
    private static String[] layout = {"Manual", "Automatic"};
    private static String[] colors = {"Cyan", "Green", "yellow", "Magenta", "Pink", "Red", "White"};
    private static String[] first = {"Player 1", "Player 2", "Random"};
    private JComboBox cshi = new JComboBox(ships);
    private JComboBox cdir = new JComboBox(direction);
    private JComboBox aiLevel = new JComboBox(level);
    private JComboBox shipLayout = new JComboBox(layout);
    private JComboBox shipColor = new JComboBox(colors);
    private JComboBox playsFirst = new JComboBox(first);
    private JTextField mbar = new JTextField();
    private static int enemy = 1, i, j, length = 5, you = 0, prevcolor = 0, prevFirst = 0, prevLayout = 0, prevLevel = 0, ready = 0, sindex = 0, dindex = 0;
    private static Player players[] = new Player[2];
    private static JButton deploy = new JButton("DEPLOY");
    private static int w = 0, a = 0, s = 0, t = 0, e = 0;//counters to track the use of all ships
    private static String[][] shiphit = new String[10][10];
    private static String user, user2;
    private static Color[] color = {Color.cyan, Color.green, Color.yellow, Color.magenta, Color.pink, Color.red, Color.white};
    private static Object selectedValue = " ", gametype;
    private static BattleshipClient me;
    private static boolean gameover = false;

    public BattleShipFrame() {
        setTitle("Battleship");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        setResizable(false);
        user = JOptionPane.showInputDialog("Enter your name.");
        int dummy = 0;
        while (((user == null) || (user.equals(""))) && (dummy < 3)) {
            user = JOptionPane.showInputDialog("You have to input something.");
            if ((user != null) && (!user.equals("")))
                dummy = 4;
            else
                dummy++;
        }
        if (dummy == 3) {
            JOptionPane.showMessageDialog(null, "Since you're having trouble inp"
                    + "utting your name, I'll just call you stupid.", "", JOptionPane.INFORMATION_MESSAGE);
            user = "Stupid";
        }
        players[you] = new Player(user);
        players[enemy] = new Player("Computer");
        b = getContentPane();
        b.add(setBoard(you), BorderLayout.CENTER);
        c = getContentPane();
        d = getContentPane();
        inputpanel = shipinput();
        d.add(inputpanel, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }

    public static boolean getGameOver() {
        return gameover;
    }

    public static void setGameOver(boolean b) {
        gameover = b;
    }

    public void whoGoesFirst() {
        int x = 0;
        if (playsFirst.getSelectedIndex() != 2) {
            if (playsFirst.getSelectedIndex() != you)
                flipYou();
            players[playsFirst.getSelectedIndex()].getTimer().start();
            x = playsFirst.getSelectedIndex();
        } else {
            int rand = (int) (Math.random() * 2);
            JOptionPane.showMessageDialog(null, players[rand].getUser() + " will "
                    + "go first.", "", JOptionPane.PLAIN_MESSAGE);
            if (rand != you)
                flipYou();
            players[rand].getTimer().start();
            x = rand;
        }
        if
                ((!players[x].getUser().equals("Computer")) || (!players[x].getUser().equals("CPU1")) || (!players[x].getUser().equals("CPU2")))
            players[x].setMove(true);
    }

    //returns ship color, as selected by the user
    public static Color getColor() {
        return (color[shipColor.getSelectedIndex()]);
    }

    //asks if two players are playing on the same computer or over the web
    public static boolean isLocal() {
        if ((gametype == pvp) && (selectedValue.equals("Local")))
            return true;
        else
            return false;
    }

    public static void flipYou() {
        if (you == 1) {
            you = 0;
            enemy = 1;
        } else {
            you = 1;
            enemy = 0;
        }
    }//determines whether or not is shipLayout is set to automatic

    public static boolean isAutoSet() {
        if (shipLayout.getSelectedIndex() == 0)
            return false;
        else
            return true;
    }


    //variable that determines whether or not a carrier has been placed
    
}
