public class Calc
{

    private int empty = 0;
    private int max;
    private int length;
    private int width;
    Grid meGrid;

    public Calc(Grid temp)
    {
        max = 4;//connect 4 or n
        meGrid = temp;
        empty = meGrid.empty();
        width = meGrid.get_width();
        length = meGrid.get_length();
    }

    public boolean verify(int x, int y, int player)
    {//sets the found coordinate to current player
        meGrid.set_grid(x, y, player);
        empty--;
        return check(x, y, 0, 1, player)
                || check(x, y, -1, 1, player)
                || check(x, y, -1, 0, player)
                || check(x, y, 1, 1, player);
    }

    public boolean draw()
    {//checks for draw game
        return empty == 0;
    }

    private boolean check(int x, int y, int bjx, int bjy, int player)
    {
        int flag = 0;
        int tempwidth = x;
        int templength = y;

        while (flag < max && right(tempwidth, templength))
        {
            if (!meGrid.gridIsEqualTo(tempwidth, templength, player))
            {
                break;

            }
            tempwidth += bjx;
            templength += bjy;
            flag++;
        }
        tempwidth = x - bjx;
        templength = y - bjy;
        while (flag < max && right(tempwidth, templength))
        {
            if (!meGrid.gridIsEqualTo(tempwidth, templength, player))
            {
                break;
            }
            tempwidth -= bjx;
            templength -= bjy;
            flag++;
        }
        return flag == max;
    }

    private boolean right(int x, int y)
    {
        //if the bounds are set to be > 0 only then first row and column 
        //doesnt work
        return x >= 0 && x < width && y >= 0 && y < length;
    }
}