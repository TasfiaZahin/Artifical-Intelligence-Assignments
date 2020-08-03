/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author acer
 */
class Solver {
    
    public Solver() {
        
    }
    
    Utility minValue(int n, Board board, int alpha, int beta, int level) //user min node
    {  
        if(board.isGameOver() == true)
        {
            int value = board.calculateUtility();
            Utility util = new Utility (new Position(n),value);
            return util;
        }       
        
        int v = Integer.MAX_VALUE; //initial min node value
        
        ArrayList<Position> blanks = new ArrayList<>();
        blanks = board.getBlanks();
        Utility finalUtil = new Utility();
 
        for(int i=0;i<blanks.size();i++)
        {
            Position pos = blanks.get(i);
            Board b = new Board(n);
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<n;k++)
                {
                    b.grid[j][k] = board.grid[j][k];
                }
            }
            
            b.makeMove(pos,"O");

            int current = maxValue(n,b,alpha,beta,level+1).value;
            if(current < v)
            {
                v = current;
                finalUtil = new Utility(pos,v);
            }
            if(v <= alpha) //pruning
            {
                return finalUtil;
            }
            beta = Math.min(beta,v);
        }
        return finalUtil;  
    }
    
    Utility maxValue(int n, Board board, int alpha, int beta, int level) //computer max node
    {      
        if(board.isGameOver() == true)
        {
            int value = board.calculateUtility();
            Utility util = new Utility (new Position(n),value);
            return util;
        }               
        
        int v = Integer.MIN_VALUE; //initial max node value
        
        ArrayList<Position> blanks = new ArrayList<>();
        blanks = board.getBlanks();
        Utility finalUtil = new Utility();
 
        for(int i=0;i<blanks.size();i++)
        {
            Position pos = blanks.get(i);
            Board b = new Board(n);
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<n;k++)
                {
                    b.grid[j][k] = board.grid[j][k];
                }
            }
            
            b.makeMove(pos,"X");

            int current = minValue(n,b,alpha,beta,level+1).value;
            if(current > v)
            {
                v = current;
                finalUtil = new Utility(pos,v);
            }
            if(v >= beta) //pruning
            {
                return finalUtil;
            }
            alpha = Math.max(alpha,v);
        }
        return finalUtil;
    }

    Utility alphaBeta(int n, Board board) {
        
        Board newBoard = new Board(n);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                newBoard.grid[i][j] = board.grid[i][j];
            }
        }
        
        Utility util = maxValue(n,newBoard,Integer.MIN_VALUE,Integer.MAX_VALUE,0);       
        return util;
    }
    
    void solve(int n, Board board)
    {
        Scanner scan = new Scanner(System.in);
        
        while(board.isGameOver() != true)
        {
            System.out.println("Your turn:");
            int x = scan.nextInt();
            int y = scan.nextInt();
            Position userMove = new Position(n,x,y);
            if(!userMove.isValid())
            {
                System.out.println("Not valid move, try again!");
                board.show();
                continue;
            }
            if(!board.isPositionEmpty(userMove))
            {
                System.out.println("Not valid move, try again!");
                board.show();
                continue;
            }
            
            board.makeMove(userMove,"O");
            board.show();
            
            if(board.isGameOver() == true)
            {
                break;
            }
            
            System.out.println("Computer's turn...");
            
            Utility util = alphaBeta(n,board);
            Position computerMove = util.position;
                                  
            board.makeMove(computerMove,"X");
            board.show();
        }
        
        if(board.isUserWinner() == true)
        {
            System.out.println("You win!");
        }
        else if(board.isComputerWinner() == true)
        {
            System.out.println("Computer wins!");
        }
        else
        {
            System.out.println("Draw!");
        }
    }
}
