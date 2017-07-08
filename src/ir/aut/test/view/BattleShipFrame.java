package ir.aut.test.view;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public static int getW() {
        return w;
    }

    //variable that determines whether or not a battleship has been placed
    public static int getA() {
        return a;
    }

    //variable that determines whether or not a submarine has been placed
    public static int getS() {
        return s;
    }

    //variable that determines whether or not a destroyer has been placed
    public static int getT() {
        return t;
    }

    //variable that determines whether or not a patrol boat has been placed
    public static int getE() {
        return e;
    }

    public static int getReady() {
        return ready;
    }

    public static JFrame getStatistics() {
        return statistics;
    }

    public static void setData(JLabel x) {
        data = x;
    }

    public static JLabel getData() {
        return data;
    }

    public static JPanel getStats() {
        return stats;
    }

    public static void setDeploy(boolean k) {
        deploy.setEnabled(k);
    }

    public static Player getPlayers(int x) {
        return players[x];
    }

    public static String getDirection(int i) {
        return direction[i];
    }

    public static String getCletters(int i) {
        return cletters[i];
    }

    public static String getShips(int i) {
        return ships[i];
    }

    public static String getCnumbers(int i) {
        return cnumbers[i];
    }

    public static int getSIndex() {
        return sindex;
    }

    public static int getDIndex() {
        return dindex;
    }

    public static int getYou() {
        return you;
    }

    public static int getEnemy() {
        return enemy;
    }

    public static void setYou(int x) {
        you = x;
    }

    public static void setEnemy(int x) {
        enemy = x;
    }

    //creates Game menu and submenus
    public JMenuBar createMenuBar() {
        JMenu menu;
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Game");
        menuBar.add(menu);
        m = new JMenu("New Game");
        menu.add(m);
        GameListener stuff = new GameListener();
        pvp = new JMenuItem("Player vs. Player");
        pvp.addActionListener(stuff);
        m.add(pvp);
        pvc = new JMenuItem("Player vs. Computer");
        pvc.addActionListener(stuff);
        m.add(pvc);
        cvc = new JMenuItem("Computer vs. Computer");
        cvc.addActionListener(stuff);
        m.add(cvc);

        m = new JMenuItem("Rules");
        m.addActionListener(new RulesListener());
        menu.add(m);
        m = new JMenuItem("Statistics");
        m.addActionListener(new StatsListener());
        menu.add(m);
        m = new JMenuItem("Options");
        m.addActionListener(new OptionsListener());
        menu.add(m);
        m = new JMenuItem("Exit");
        m.addActionListener(new ExitListener());
        menu.add(m);
        return menuBar;
    }//creates panels that used to place ships

    public JPanel shipinput() {
        input = new JPanel();
        mbar.setText("Select a ship, its front position and direction.");
        mbar.setFont(new Font("Courier New", Font.BOLD, 14));
        mbar.setEditable(false);
        cshi.setSelectedIndex(0);
        cshi.addActionListener(new ShipsListener());
        TitledBorder title;//used for titles around combo boxes
        title = BorderFactory.createTitledBorder("Ships");
        cshi.setBorder(title);
        input.add(cshi);
        cdir.setSelectedIndex(0);
        cdir.addActionListener(new DirectListener());
        input.add(cdir);
        title = BorderFactory.createTitledBorder("Direction");
        cdir.setBorder(title);
        deploy.setEnabled(false);
        deploy.addActionListener(new DeployListener());
        input.add(deploy);
        return input;
    }//creates board for manual ship placement

    public JPanel setBoard(int n) {
        players[n].setMyBoard(new JPanel(new GridLayout(11, 11)));//panel to store board
        JTextField k;
        for (i = 0; i < 11; i++) {
            for (j = 0; j < 11; j++) {
                if ((j != 0) && (i != 0)) {
                    players[n].getBboard(i - 1, j - 1).addActionListener(new BoardListener());
                    players[n].getMyBoard().add(players[n].getBboard(i - 1, j - 1));
                }
                if (i == 0) {
                    if (j != 0) {
                        //used to display row of numbers
                        k = new JTextField(Battleship.getCnumbers(j));
                        k.setEditable(false);
                        k.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
                    } else {
                        //used to display column of numbers
                        k = new JTextField();
                        k.setEditable(false);
                    }
                    players[n].getMyBoard().add(k);
                } else if (j == 0) {
                    k = new JTextField(Battleship.getCletters(i));
                    k.setEditable(false);
                    k.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
                    players[n].getMyBoard().add(k);
                }
            }
        }
        return players[n].getMyBoard();
    }//creates board and automatically places ship

    public JPanel autoBoard(int u, int t) {
        players[u].setGBoard(new JPanel(new GridLayout(11, 11)));
        JTextField k;
        if (!players[u].getUser().equals("Unknown"))
            for (i = 0; i < 5; i++) {
                players[u].setBoats(i, players[u].getBoats(i).compinput(i, u));
            }
        for (i = 0; i < 11; i++) {
            for (j = 0; j < 11; j++) {
                if ((j != 0) && (i != 0)) {
                    if ((players[u].getUser().equals("Computer")) || (isLocal())) {
                        players[u].getBboard(i - 1, j - 1).addActionListener(new AttackListener());
                    } else if
                            ((players[t].getUser().equals("Computer")) || (players[t].getUser().equals("CPU1")) || (players[t].getUser().equals("CPU2")) || (players[t].getUser().equals("Unknown"))) {
                        if (players[u].getHitOrMiss(i - 1, j - 1))
                            players[u].setBboard(i - 1, j - 1, getColor());
                    } else {
                        players[u].getBboard(i - 1, j - 1).addActionListener(new InternetListener());
                    }
                    players[u].getGBoard().add(players[u].getBboard(i - 1, j - 1));
                }
                if (i == 0) {
                    if (j != 0) {
                        k = new JTextField(Battleship.getCnumbers(j));
                        k.setEditable(false);
                        k.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
                    } else {
                        k = new JTextField();
                        k.setEditable(false);
                    }
                    players[u].getGBoard().add(k);
                } else if (j == 0) {
                    k = new JTextField(Battleship.getCletters(i));
                    k.setEditable(false);
                    k.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
                    players[u].getGBoard().add(k);
                }
            }
        }
        return players[u].getGBoard();
    }//Listener for combo boxes used to layout ships

    private class ShipsListener implements ActionListener {
        public void actionPerformed(ActionEvent v) {
            sindex = cshi.getSelectedIndex();
            if (players[you].getBoats(sindex) != null)
                cdir.setSelectedIndex(players[you].getBoats(sindex).getDirect());
            switch (sindex) {
                case 0:
                    length = 5;
                    break;
                case 1:
                    length = 4;
                    break;
                case 2:
                    length = 3;
                    break;
                case 3:
                    length = 3;
                    break;
                case 4:
                    length = 2;
                    break;
            }
            if (players[you].getBoats(sindex) != null) {
                Ship boat = new Ship(ships[sindex], players[you].getBoats(sindex).getDirect()
                        , length, players[you].getBoats(sindex).getX(), players[you].getBoats(sindex).getY());
                players[you].getBoats(sindex).clearship();
                players[you].setBoats(sindex, boat);
                players[you].getBoats(sindex).placeship();
            }
        }
    }

    //Listener for the Direction combo box
    private class DirectListener implements ActionListener {
        public void actionPerformed(ActionEvent v) {
            dindex = cdir.getSelectedIndex();
            if (players[you].getBoats(sindex) != null) {
                Ship boat = new Ship(ships[sindex], dindex, players[you].getBoats(sindex).getLength(),
                        players[you].getBoats(sindex).getX(), players[you].getBoats(sindex).getY());
                players[you].getBoats(sindex).clearship();
                players[you].setBoats(sindex, boat);
                players[you].getBoats(sindex).placeship();
            }
        }
    }

    //Listener for the buttons on the board
    private class BoardListener implements ActionListener {
        public void actionPerformed(ActionEvent v) {
            if (ready == 0) {
                if (players[you].getBoats(sindex) != null)
                    players[you].getBoats(sindex).clearship();
                Object source = v.getSource();
                outer:
                for (i = 0; i < 10; i++) {
                    for (j = 0; j < 10; j++) {
                        if (source == players[you].getBboard(i, j)) {
                            switch (sindex) {
                                case 0: {
                                    if (w == 0)
                                        w++;
                                }
                                break;
                                case 1: {
                                    if (a == 0)
                                        a++;
                                }
                                break;
                                case 2: {
                                    if (s == 0)
                                        s++;
                                }
                                break;
                                case 3: {
                                    if (t == 0)
                                        t++;
                                }
                                break;
                                case 4: {
                                    if (e == 0)
                                        e++;
                                }
                                break;
                            }
                            players[you].setBoats(sindex, new Ship(ships[sindex], dindex, length, i, j));
                            break outer;
                        }
                    }
                }
                players[you].getBoats(sindex).placeship();
            }
        }
    }

    //creates a panel that tells whose board is which
    private JPanel whoseBoard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(players[you].getUser() + "'s Board", SwingConstants.LEFT), BorderLayout.WEST);
        panel.add(new JLabel(players[enemy].getUser() + "'s Board", SwingConstants.RIGHT), BorderLayout.EAST);
        return panel;
    }

    //Listener for exit choice on Game menu
    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int r = JOptionPane.showConfirmDialog(null, "Are you sure you would l"
                    + "ike to exit Battleship?", "Exit?", JOptionPane.YES_NO_OPTION);
            if (r == 0)
                System.exit(0);
        }
    }

    //listener for New Game submenu
    private class GameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int q = JOptionPane.showConfirmDialog(null, "Are you sure you would l"
                    + "ike to start a new game?", "New Game?", JOptionPane.YES_NO_OPTION);
            if (q == 0) {
                //resets variables
                b.removeAll();
                c.removeAll();
                d.removeAll();
                you = 0;
                enemy = 1;
                ready = 0;

                if (players[you].getTimer() != null)
                    if (players[you].getTimer().isRunning())
                        players[you].getTimer().stop();
                if (players[enemy].getTimer() != null)
                    if (players[enemy].getTimer().isRunning())
                        players[enemy].getTimer().stop();

                gametype = e.getSource();

                if (gametype == pvp) {
                    if (!selectedValue.equals("no server")) {
                        String[] possibleValues = {"Local", "Online"};
                        selectedValue = JOptionPane.showInputDialog(null,
                                "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,
                                possibleValues, possibleValues[0]);
                    }
                    if (!players[you].getUser().equals("CPU1")) {
                        if (players[you].getUser().equals("Stupid")) {
                            int w = JOptionPane.showConfirmDialog(null, "Would you"
                                            + " like to try inputting your name again?", "",
                                    JOptionPane.YES_NO_OPTION);
                            if (w == JOptionPane.YES_OPTION) {
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
                                    JOptionPane.showMessageDialog(null, "Still a"
                                                    + "cting stupid.  Oh well, we'll run with it."
                                            , "", JOptionPane.INFORMATION_MESSAGE);
                                    user = "Stupid";
                                } else
                                    JOptionPane.showMessageDialog(null, "That wasn't"
                                                    + " so hard now, was it?", "YEAH!",
                                            JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        players[you] = new Player(players[you].getUser());
                    } else
                        players[you] = new Player(user);
                    if (selectedValue.equals("Online")) {
                        players[enemy] = new Player("Unknown");
                        if (!isAutoSet()) {
                            b.add(setBoard(you), BorderLayout.CENTER);
                            deploy.setEnabled(false);
                            d.add(inputpanel, BorderLayout.NORTH);
                        } else {
                            b.add(autoBoard(you, enemy), BorderLayout.WEST);
                            c.add(autoBoard(enemy, you), BorderLayout.EAST);
                            ready = 1;
                        }
                    } else {
                        //gets user to input name
                        if ((players[enemy].getUser().equals("Computer")) || (players[enemy].getUser().equals("CPU2")) || (players[enemy].getUser().equals("Unknown"))) {
                            user2 = JOptionPane.showInputDialog("Enter your name.");
                            while ((user2 == null) || (user2.equals(""))) {
                                user2 = JOptionPane.showInputDialog("You have to input something.");
                            }
                        } else
                            user2 = players[enemy].getUser();
                        players[enemy] = new Player(user2);
                        b.add(autoBoard(you, enemy), BorderLayout.WEST);
                        c.add(autoBoard(enemy, you), BorderLayout.EAST);
                        d.add(whoseBoard(), BorderLayout.NORTH);
                        whoGoesFirst();
                        ready = 1;
                    }
                    //ready=1;
                } else if (gametype == pvc)//Player vs Computer
                {
                    if (!players[you].getUser().equals("CPU1")) {
                        if (players[you].getUser().equals("Stupid")) {
                            int w = JOptionPane.showConfirmDialog(null, "Would you"
                                            + " like to try inputting your name again?", "",
                                    JOptionPane.YES_NO_OPTION);
                            if (w == JOptionPane.YES_OPTION) {
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
                                    JOptionPane.showMessageDialog(null, "Still a"
                                                    + "cting stupid.  Oh well, we'll run with it."
                                            , "", JOptionPane.INFORMATION_MESSAGE);
                                    user = "Stupid";
                                } else
                                    JOptionPane.showMessageDialog(null, "That wasn't"
                                                    + " so hard now, was it?", "YEAH!",
                                            JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        players[you] = new Player(players[you].getUser());
                    } else
                        players[you] = new Player(user);
                    players[enemy] = new Player("Computer");
                    if (!isAutoSet()) {
                        b.add(setBoard(you), BorderLayout.CENTER);
                        deploy.setEnabled(false);
                        d.add(inputpanel, BorderLayout.NORTH);
                    } else {
                        b.add(autoBoard(you, enemy), BorderLayout.WEST);
                        c.add(autoBoard(enemy, you), BorderLayout.EAST);
                        whoGoesFirst();
                    }
                } else if (gametype == cvc)//Computer vs Computer
                {
                    mbar.setText("Battleship Demo");
                    mbar.setEditable(false);
                    d.add(mbar, BorderLayout.NORTH);
                    players[you] = new Player("CPU1");
                    players[enemy] = new Player("CPU2");
                    b.add(autoBoard(you, enemy), BorderLayout.WEST);
                    c.add(autoBoard(enemy, you), BorderLayout.EAST);
                    whoGoesFirst();
                }
                pack();
                repaint();
            }
        }
    }

}
