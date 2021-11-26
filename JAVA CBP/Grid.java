public class Grid
{

    private int width;
    private int length;
    private int[][] grid;
    private int empty = 0;

    public Grid()
    {
        width = 7;
        length = 6;

        grid = new int[width][length];
        for (int i = 0; i < width; i++)
        {
            for (int k = 0; k < length; k++)
            {
                grid[i][k] = 0;
                empty++;
            }
        }
    }
    //methods to gain access to internal private data

    public int empty()
    {
        return empty;
    }

    public int[][] get_grid()
    {
        return grid;
    }

    public boolean gridIsEqualTo(int a, int b, int c)
    {
        return grid [a][b] == c;
    }

    public void set_grid(int a, int b, int temp_player)
    {
        grid[a][b] = temp_player;
    }

    public int get_width()
    {//returns the width
        return width;
    }

    public int get_length()
    {//returns the length
        return length;
    }

    public int searchLength(int x)
    {//checks for room in column and returns free spot.
        int y = -1;
        for (int i = 0; i < length; i++)
        {
            if (grid[x][i] == 0)
            {
                y = i;
            }
        }
        return y;
    }

    public int switchplayer(int player, int max)
    {
        player++;
        if (player > max)
        {
            return 1;
        }
        return player;
    }
}