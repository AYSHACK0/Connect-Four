import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;


public class GraphicalInterface
{
    static int ANIMATION_TIME_IN_SECONDS = 200;
    //declaration of gui objects

    private JFrame frame;
    private JLabel[][] squares;
    private JButton[] buttons;
    //variables used in grid
    private int width = 7;
    private int length = 6;
    private int currPlayer = 1;
    //game variables to communicate with top program
    private boolean didWin = false;
    private boolean didDraw = false;
    private boolean Quit = false;
    private boolean NewGame = false;
    //making of grid and logic
    Grid graph = new Grid();
    Calc log = new Calc(graph); //create game logic

    public GraphicalInterface()
    {

        frame = new JFrame("Connect - Four");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(width, length + 1));

        squares = new JLabel[width][length];
        buttons = new JButton[width];

        for (int i = 0; i < width; i++)
        {
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setActionCommand("" + i);
            buttons[i].addActionListener(
                    new ActionListener()
                    {

                        public void actionPerformed(ActionEvent e)
                        {
                            int act = Integer.parseInt(e.getActionCommand());
                            int y = graph.searchLength(act);//check for space in collumn
                            if (y != -1)
                            {
                                //sets a place to current player
                                if (log.verify(act, y, currPlayer))
                                {
                                    didWin = true;
                                } 
                                else if (log.draw())
                                {//checks for drawgame
                                    didDraw = true;
                                } 
                                else
                                {
                                    //change player
                                    currPlayer = graph.switchplayer(currPlayer, 2);
                                    frame.setTitle("Connect Four - Player " + currPlayer + "'s turn");
                                }
                            } 
                            else 
                            {
                                JOptionPane.showMessageDialog(null, "Choose A Different One", "The Column is Full", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    });
                    panel.add(buttons[i]);
        }
        
        for (int col = 0; col < length; col++)
        {
            for (int row = 0; row < width; row++)
            {
                squares[row][col] = new JLabel();
                squares[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                squares[row][col].setBorder(new LineBorder(Color.blue));
                panel.add(squares[row][col]);
            }
        }

        //jframe stuff
        frame.setContentPane(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void NewBoard()
    {//keeps the gui in sync with the logic and grid
        for (int row = 0; row < width; row++)
        {
            for (int col = 0; col < length; col++)
            {
                if (graph.gridIsEqualTo(row, col, 1))
                {
                    squares[row][col].setOpaque(true);
                    squares[row][col].setBackground(Color.red);
                }
                if (graph.gridIsEqualTo(row, col, 2))
                {
                    squares[row][col].setOpaque(true);
                    squares[row][col].setBackground(Color.yellow);
                }
            }
        }
    }

    public void DisplayWin()
    {
        String Winner = "Player " + currPlayer + " Wins";
        int n = JOptionPane.showConfirmDialog(
                frame,
                "New Game?",
                Winner,
                JOptionPane.YES_NO_OPTION);
        if (n < 1)
        {
            frame.dispose();
            NewGame = true;
        } 
        else
        {
            frame.dispose();
            Quit = true;
        }
    }

    public void DisplayDraw()
    {
        String Winner = "Draw Game";
        int n = JOptionPane.showConfirmDialog(
                frame,
                "New Game?",
                Winner,
                JOptionPane.YES_NO_OPTION);
        if (n < 1)
        {
            frame.dispose();
            NewGame = true;
        }
        else
        {
            frame.dispose();
            Quit = true;
        }
    }

    public boolean getdidWin()
    {
        return didWin;
    }

    public boolean getdidDraw()
    {
        return didDraw;
    }

    public boolean getQuit()
    {
        return Quit;
    }

    public boolean getNewGame()
    {
        return NewGame;
    }
/*************************************************************************MAIN*************************************************************************************************/
    public static void main(String[] args)throws InterruptedException
    {
        GraphicalInterface gui = new GraphicalInterface();
    }
}