/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridpathfinding;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Board {
    
    int[][] grid;
    int n;
    Board parent;
    
    public Board()
    {
        n = 8;
    }
    
    public Board(int n)
    {
        this.n = n;
        grid = new int[n][n];
        parent = null;
    }

    Board(int[][] key, int n) {
        this.grid = grid;
        parent = null;
        this.n = n;
    }
    
    public void generateWall(int percent)
    {
        double blocknum = percent/100.0*n*n;
        int blocks = (int) blocknum;
        //System.out.println(blocks);
        
         //0 free, 1 block, 2 src, 3 goal
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                grid[i][j] = 0;
            }
        }
        
        Random rand = new Random();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter src x coordinate:");
        int srcRow = sc.nextInt();
        System.out.println("Enter src y coordinate:");
        int srcCol = sc.nextInt();
        
        System.out.println("Enter dest x coordinate:");
        int destRow = sc.nextInt();
        System.out.println("Enter dest y coordinate:");
        int destCol = sc.nextInt();
                
        
        /*srcRow = rand.nextInt(n);
        srcCol = rand.nextInt(n);
        
        destRow = rand.nextInt(n);
        destCol = rand.nextInt(n);
        
        while(srcRow == destRow && srcCol == destCol)
        {
            destRow = rand.nextInt(n);
            destCol = rand.nextInt(n);
        }*/
        
        /*int srcRow = n-2;
        int srcCol = 0;
        int destRow = 2;
        int destCol = n-1;*/
        
        for(int i=0;i<blocks;i++)
        {
            //System.out.println("hi");
            int row = rand.nextInt(n);
            int col = rand.nextInt(n);

            while((row == srcRow && col == srcCol) || (row == destRow && col == destCol))
            {
                row = rand.nextInt(n);
                //col = rand.nextInt(n);
            }           
            grid[row][col] = 1;
        }
        
        grid[srcRow][srcCol] = 2;
        grid[destRow][destCol] = 3;
        
        /*grid[0][0] = 1;
        grid[0][1] = 1;
        grid[1][0] = 1;
        grid[1][2] = 1;
        grid[2][0] = 1;
        grid[2][1] = 1;
        grid[4][2] = 0;
        grid[5][2] = 0;
        grid[6][2] = 0;
        grid[7][2] = 0;
        grid[1][3] = 1;
        grid[2][3] = 1;
        grid[3][3] = 1;
        grid[4][3] = 0;
        grid[5][3] = 1;
        grid[6][3] = 1;
        grid[7][3] = 1;
        grid[7][4] = 0;
        grid[7][5] = 0;
        grid[0][4] = 0;
        grid[1][4] = 0;
        grid[2][4] = 0;
        grid[3][4] = 0;
        grid[4][4] = 0;
        grid[6][0] = 2;
        grid[2][7] = 3;*/
    }
    
    public void show()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public Position getSrc()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 2)
                {
                    return new Position(i,j);
                }
            }
        }
        return null;
    }
    
    public Position getDest()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 3)
                {
                    return new Position(i,j);
                }
            }
        }
        return null;
    }
}
