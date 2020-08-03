/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class Board {
 
    int n;
    int[][] grid;
    Board parent;
    
    public Board(int n)
    {
        parent = null;
        this.n = n;
    }
    public Board(int[][] grid, int n)
    {
        this.grid = grid;
        parent = null;
        this.n = n;
    }

    @Override
    public int hashCode() {
        
        int hash = 7;
        hash = 83 * hash + this.n;
        hash = 83 * hash + Arrays.deepHashCode(this.grid);
        return hash;
    } 
    
    @Override
    public boolean equals(Object o) {
        
        if (o == this) 
        {
            return true;
        }
        if(o == null)
        {
            return false;
        }
        if(o instanceof Board)
        {
            Board test = (Board) o;
            if(test.n != n)
            {
                return false;
            }
            
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(this.grid[i][j] != test.grid[i][j])
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
 
    public Position getBlank(int[][] temp)
    {
        Position blank = new Position(n);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(temp[i][j] == 0)
                {
                    blank.x = i;
                    blank.y = j;
                    break;
                }
            }
        }
        return blank;
    }
    
    public int h1() //no adj/blank restriction;no of misplaced tiles
    {
        int misplaced = 0;
        Manager manager = new Manager(n);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(!manager.inPosition(new Position(n,i,j), grid[i][j]))
                {
                    misplaced++;
                }
            }
        }
        return misplaced;
    }
    
    public int h2() //N-swap no adj restriction
    {    
        int moves = 0;
        int [][] temp = new int [n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                temp[i][j] = grid[i][j];
            }
        }
        //print(temp);
        
        /*temp[0][0] = 0;
        
        System.out.println("printing org grid h2:");
        print(grid);
        System.out.println("end printing org grid h2");
        System.out.println("printing temp h2");
        print(temp);
        System.out.println("end printing temp h2");*/
        
        Manager manager = new Manager(n);
        while(manager.isGoal(temp) != true)
        {
            //System.out.println("printing temp h2");
            //print(temp);
            //System.out.println("end printing temp h2");
            //System.out.println("in lopp");
            Position blank = new Position(n);
            blank = getBlank(temp);
            //System.out.println("blank position: " + blank.x + " " + blank.y);
                   
            if(blank.x == n-1 && blank.y == n-1)
            {
                //System.out.println("corner case");
                Position toSwap = manager.getRandomPosition(temp);
                //System.out.println("random position: " + toSwap.x + " " + toSwap.y);
                int v = temp[toSwap.x][toSwap.y];
                //print(temp);
                //System.out.println("val of random pos " + v);
                temp[blank.x][blank.y] = v;
                temp[toSwap.x][toSwap.y] = 0;
                //print(temp);
                moves++;
                blank.x = toSwap.x;
                blank.y = toSwap.y;
                //continue;
            }
            
            int aimTile = manager.getValue(blank);
            //System.out.println("aimtile: " + aimTile);
            
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(temp[i][j] == aimTile)
                    {
                        temp[i][j] = 0;
                        break;
                    }
                }
            }
            temp[blank.x][blank.y] = aimTile;
            
            moves++;          
        } 
        //System.out.println("moves: " + moves);
        return moves;
    }
    
    public int h3() //Manhattan distance
    {   
        int sum = 0;
        Manager manager = new Manager(n);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                Position src = new Position(n,i,j);
                Position dest = manager.getPosition(grid[i][j]);
                
                int dist = Math.abs(dest.x - src.x) + Math.abs(dest.y - src.y);
                sum += dist;
            }
        }
        return sum;
    }
    
    public double h4() //Euclidean distance
    {
        double dist = 0.0;       
        Manager manager = new Manager(n);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                Position src = new Position(n,i,j);
                Position dest = manager.getPosition(grid[i][j]);
                
                double temp = Math.sqrt((dest.x - src.x)*(dest.x - src.x) + (dest.y - src.y)*(dest.y - src.y));
                dist += temp;
            }
        }
        return dist;
    }
    
    public int h5() //no. of tiles not in goal row + no. of tiles not in goal column
    {
        int rowMisplaced = 0;
        int colMisplaced = 0;
        Manager manager = new Manager(n);
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                Position src = new Position(n,i,j);
                Position dest = manager.getPosition(grid[i][j]);
                
                if(src.x != dest.x)
                {
                    rowMisplaced++;
                }
            }
        }
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                Position src = new Position(n,i,j);
                Position dest = manager.getPosition(grid[i][j]);
                
                if(src.y != dest.y)
                {
                    colMisplaced++;
                }
            }
        }
        return rowMisplaced + colMisplaced;
    }
    
    void show()
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    void print(int [][] temp)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(temp[i][j] + " ");
            }
            System.out.println();
        }
    }   
}
