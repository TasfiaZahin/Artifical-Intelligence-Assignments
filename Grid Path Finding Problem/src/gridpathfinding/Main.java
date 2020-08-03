/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridpathfinding;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of n for n*n board:");
        int n = sc.nextInt();
        System.out.println("Enter percentage of wall:");
        int wall = sc.nextInt();

        Board board = new Board(n); //10
        
        board.generateWall(wall); //10% wall
        System.out.println("initial board:\n");
        board.show();
        System.out.println();
        System.out.println("solving...\n");
        
        ArrayList<Integer> steps = new ArrayList<>();
        ArrayList<Integer> gen = new ArrayList<>();
        ArrayList<Integer> exp = new ArrayList<>();
        ArrayList<Double> bfact = new ArrayList<>();
        
        for(int mode=1;mode<=3;mode++)
        {
            System.out.println("--------------------------------------------------------------");
            System.out.println("HEURISTIC " + mode + '\n');
            Solver solver = new Solver();
            solver.solve(board,mode,steps,gen,exp,bfact);
            System.out.println("--------------------------------------------------------------");
        }
        
        System.out.println("h   steps    expanded     generated    branchingfactor");
        for(int i=0;i<3;i++)
        {           
            System.out.println(i+1 + "     " + steps.get(i) + "         " + exp.get(i) + "          " + gen.get(i) + "       " + bfact.get(i));
        }
    }
    
}
