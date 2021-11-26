public class ConnectFour
{
    /*MAIN TO RUN THE PROGRAM*/
    public static void main(String[] args)
    {
        int flag = 0;
        boolean implementGui = true;//sets if we want the Terminal or GUI.

        if (implementGui)
        {
            GraphicalInterface gui = new GraphicalInterface();
            while (flag != -1)
            {//checks if program is in quitting stage
                switch (flag)
                {
                    case 0:
                        
                        gui.NewBoard();
                        
                        if (gui.getdidWin())
                        {
                            flag = 1;
                        } 
                        else if (gui.getdidDraw())
                        {
                            flag = 2;
                        } 
                        else if (gui.getNewGame())
                        {
                            gui = new GraphicalInterface();
                            flag = 0;
                        }
                        break;
                    
                    case 1://end game with winner
                        
                        gui.DisplayWin();
                        
                        if (gui.getQuit())
                        {
                            flag = -1;
                        }
                        else  if (gui.getNewGame())
                        {
                            gui = new GraphicalInterface();
                            flag = 0;
                        }
                        break;
                    
                    case 2://end game with drawgame
                        
                        gui.DisplayDraw();
                        
                        if (gui.getQuit())
                        {
                            flag = -1;
                        } 
                        else if (gui.getNewGame())
                        {
                            gui = new GraphicalInterface();
                            flag = 0;
                        }
                        break;
                }
            }
        } 
        
        else
        {
            Terminal term = new Terminal();
            
            while (flag != -1)
            {//checks if program is in quitting stage
                switch (flag)
                {
                    case 0:
                        
                        term.Terminal_Run();
                        
                        if (term.getdidWin())
                        {
                            flag = 1;
                        } 
                        else if (term.getdidDraw())
                        {
                            flag = 2;
                        }
                        break;
                    
                    case 1://prints end game with winner
                        
                        term.DisplayWin();
                        
                        if (term.getQuit())
                        {
                            flag = -1;
                        }
                        else if (term.getNewGame())
                        {
                            term = new Terminal();
                            flag = 0;
                        }
                        break;
                   
                    case 2://prints end game with draw game
                        
                        term.DisplayDraw();
                        
                        if (term.getQuit())
                        {
                            flag = -1;
                        } 
                        else if (term.getNewGame())
                        {
                            term = new Terminal();
                            flag = 0;
                        }
                        break;
                }
            }
        }
    }
}