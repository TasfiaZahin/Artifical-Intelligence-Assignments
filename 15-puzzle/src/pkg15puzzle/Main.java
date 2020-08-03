/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Main {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of n:");
        
        int n = sc.nextInt();
    
        System.out.println("Enter the matrix:");
        int[][] blocks = new int[n][n];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int c = sc.nextInt();
                blocks[i][j] = c;
            }
        }
        System.out.println();
        
        /*blocks[0][0] = 1;
        blocks[0][1] = 5;
        blocks[0][2] = 2;
        blocks[0][3] = 0;
        blocks[1][0] = 4;
        blocks[1][1] = 6;
        blocks[1][2] = 10;
        blocks[1][3] = 3;
        blocks[2][0] = 12;
        blocks[2][1] = 9;
        blocks[2][2] = 14;
        blocks[2][3] = 7;
        blocks[3][0] = 13;
        blocks[3][1] = 8;
        blocks[3][2] = 15;
        blocks[3][3] = 11;*/
        
        Board initial = new Board(blocks,n);
        initial.parent = null;
                    
        //Solver solver = new Solver(n);    
        System.out.println("Initial board:\n");
        initial.show();
        System.out.println();
        System.out.println("solving...\n");
        
        ArrayList<Integer> steps = new ArrayList<>();
        ArrayList<Integer> gen = new ArrayList<>();
        ArrayList<Integer> exp = new ArrayList<>();
        ArrayList<Double> bfact = new ArrayList<>();
        
        for(int mode=1;mode<=5;mode++)
        {
            System.out.println("--------------------------------------------------------------");
            System.out.println("HEURISTIC " + mode + '\n');
            int temp [][] = new int[n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    temp[i][j] = initial.grid[i][j];
                }
            }
            Board b = new Board(temp,n);
            Solver solver = new Solver(n);
            solver.solve(b,mode,steps,gen,exp,bfact);
            System.out.println("--------------------------------------------------------------");
        }
        
        System.out.println("h   steps    expanded     generated    branchingfactor");
        for(int i=0;i<5;i++)
        {           
            System.out.println(i+1 + "     " + steps.get(i) + "      " + exp.get(i) + "       " + gen.get(i) + "       " + bfact.get(i));
        }
        
        /*for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(blocks[i][j] + " ");
            }
            System.out.println();
        }*/
    }
    
}
