import java.util.*;

public class Terminal
{
    //input scanner to get user input
    Scanner input = new Scanner(System.in); 
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

    public Terminal()
    {
    }

    public void Terminal_Run()
    {
        System.out.println("Player " + currPlayer + "'s turn");
        DisplayBoard();//print out the grid
        int xpos = 0;// xposition is set to 0 before taking user input
        try
        {// tries to do get an int from the user
            xpos = input.nextInt();
            if (xpos <= 0 || xpos > graph.get_width())
            {
                throw new Exception();
            }
        }
        catch (Exception except)
        {
            System.out.println("Not a number between 1 and "
                    + graph.get_width());
            input.nextLine();
        }
        //checks if user input is within range
        if (xpos > 0 && xpos < graph.get_width() + 1)
        {
            xpos--;
            int ypos = graph.searchLength(xpos);//check for space in collumn
            if (ypos != -1)
            {
                //sets a place to current player
                if (log.verify(xpos, ypos, currPlayer))
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
                }
            }
            else
            {
                System.out.println("Column is Full");
            }
        }
    }

    public void DisplayWin()
    {//prints to terminal when a player wins

        DisplayBoard();//print out the grid
        System.out.println("\nWinner is Player " + currPlayer
                + "\nPlay Again?\n"
                + "Press 0 for New Game\n"
                + "Press a Letter to Quit");
        int option = -1;
        try
        {//checks for user input
            option = input.nextInt();
        }
        catch (Exception except)
        {
            System.out.println("Quitting");
            Quit = true;
        }
        if (option == 0)
        {
            NewGame = true;
        }
    }

    public void DisplayDraw()
    {
        DisplayBoard();//print out the grid
        System.out.println(
                "\nDraw Game"
                + "\nPlay Again?\n"
                + "Press 0 for New Game\n"
                + "Press a Letter to Quit");
        int option = -1;
        try
        {//checks for user input
            option = input.nextInt();
        }
        catch (Exception except)
        {
            System.out.println("Quitting");
            Quit = true;
        }
        if (option == 0)
        {
            NewGame = true;
        }
    }

    public void DisplayBoard()
    {//prints out the matrix board
        for (int i = -1; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (i < 0)
                {
                    System.out.print(" " + (j + 1) + " ");
                }
                else
                {
                    System.out.print("");
                    if (graph.gridIsEqualTo(j, i, 0))
                    {
                        System.out.print("");
                    } 
                    else
                    {
                        int[][] temp_grid = graph.get_grid();
                        System.out.print(temp_grid[j][i] + "");
                    }
                }
            }
            System.out.println();
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
}