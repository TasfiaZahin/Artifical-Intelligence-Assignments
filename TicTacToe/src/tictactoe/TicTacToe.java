/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class TicTacToe {

    public static void main(String[] args) {
        
        startGame();
    }
    
    private static void startGame() {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter size of board:");
        int n = scan.nextInt();
        Board board = new Board(n);
        board.show();
        System.out.println("Your move:O");
        System.out.println("Computer move:X");
        
        System.out.println("Who goes first? (Press 1 for user, 2 for computer)");               
        int choice = scan.nextInt();
        
        if(choice == 2)
        {
            Random rand = new Random();
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
            Position pos = new Position(n,x,y);
            board.makeMove(pos,"X");
            board.show();
        }
        Solver solver = new Solver();
        solver.solve(n,board);      
    }
}
