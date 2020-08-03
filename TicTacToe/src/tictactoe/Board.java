/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author acer
 */
class Board {

    int n;
    String grid[][];
    
    public Board() {
        
        n = 4;
        grid = new String [n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                grid[i][j] = "_";
            }
        }
    }

    public Board(int n) {
        
        this.n = n;
        grid = new String [n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                grid[i][j] = "_";
            }
        }
    }

    void makeMove(Position pos,String value) {
        
        if(pos.isValid())
        {
            grid[pos.x][pos.y] = value;
        }
        else
        {
            System.out.println("Not valid move");
        }
    }

    void show() {
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    ArrayList<Position> getBlanks()
    {
        ArrayList<Position> list = new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j].equalsIgnoreCase("_"))
                {
                    list.add(new Position(n,i,j));
                }
                    
            }
        }
        return list;
    }

    boolean isGameOver() {
        
        if(isComputerWinner())
        {
            //System.out.println("Computer win");
            return true;
        }
        else if(isUserWinner())
        {
            //System.out.println("User win");
            return true;
        }
        else if(isBoardFull())
        {
            //System.out.println("Draw");
            return true;
        }
        return false;
    }
    
    int calculateUtility()
    {
        if(isUserWinner())
        {
            return -1;
        }
        else if(isComputerWinner())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    boolean checkRow(int row,String val)
    {
        for(int j=0;j<n;j++)
        {
            if(!grid[row][j].equalsIgnoreCase(val))
            {
                return false;
            }
        }
        return true;
    }
    
    boolean checkColumn(int col,String val)
    {
        for(int i=0;i<n;i++)
        {
            if(!grid[i][col].equalsIgnoreCase(val))
            {
                return false;
            }
        }
        return true;
    }

    boolean isUserWinner() {
        
        boolean diagonal1Flag = true;
        boolean diagonal2Flag = true;
        
        for(int i=0;i<n;i++)
        {
            if(!grid[i][i].equalsIgnoreCase("O"))
            {
                diagonal1Flag = false;
            }     
        }
        
        if(diagonal1Flag == true)
        {
            return true;
        }
        
        for(int i=0;i<n;i++)
        {
            if(!grid[i][n-1-i].equalsIgnoreCase("O"))
            {
                diagonal2Flag = false;
            }     
        }
        
        if(diagonal2Flag == true)
        {
            return true;
        }
        
        for(int i=0;i<n;i++)
        {
            if(checkRow(i,"O") == true)
            {
                return true;
            }
        }
        
        for(int i=0;i<n;i++)
        {
            if(checkColumn(i,"O") == true)
            {
                return true;
            }
        }
        return false;
    }

    boolean isComputerWinner() {
        
        boolean diagonal1Flag = true;
        boolean diagonal2Flag = true;
        
        for(int i=0;i<n;i++)
        {
            if(!grid[i][i].equalsIgnoreCase("X"))
            {
                diagonal1Flag = false;
            }     
        }
        
        if(diagonal1Flag == true)
        {
            return true;
        }
        
        for(int i=0;i<n;i++)
        {
            if(!grid[i][n-1-i].equalsIgnoreCase("X"))
            {
                diagonal2Flag = false;
            }     
        }
        
        if(diagonal2Flag == true)
        {
            return true;
        }
        
        for(int i=0;i<n;i++)
        {
            if(checkRow(i,"X") == true)
            {
                return true;
            }
        }
        
        for(int i=0;i<n;i++)
        {
            if(checkColumn(i,"X") == true)
            {
                return true;
            }
        }
        return false;
    }
    
    boolean isBoardFull()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j].equalsIgnoreCase("_"))
                {
                    return false;
                }                  
            }
        }
        return true;
    }

    boolean isPositionEmpty(Position pos) {
        
        if(grid[pos.x][pos.y] == "_")
        {
            return true;
        }
        return false;
    }
}
