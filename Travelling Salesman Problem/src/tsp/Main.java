/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Main {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        ArrayList <City> cities = new ArrayList<>();       
        
        System.out.println("Enter number of cities:");
        int n = sc.nextInt();
        
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter the x coordinate of city " + Integer.valueOf(i));
            int x = sc.nextInt();
            System.out.println("Enter the y coordinate of city " + Integer.valueOf(i));
            int y = sc.nextInt();
            cities.add(new City(i,x,y));
        }
        
        for (int i=0;i<cities.size();i++) 
        {
            City city = cities.get(i);
            System.out.println("City: " + city.number + " Position: (" + city.x + "," + city.y + ")");
        }
        
        Solver solver = new Solver(cities);
        
        /*for(int i=0;i<5;i++)
        {
            cities.add(new City(i,0,0));
        }
        
        double[][] adjMatrix = new double[5][5];
        adjMatrix[0][0] = 0;
        adjMatrix[0][1] = 10;
        adjMatrix[0][2] = 8;
        adjMatrix[0][3] = 9;
        adjMatrix[0][4] = 7;
        adjMatrix[1][0] = 10;
        adjMatrix[1][1] = 0;
        adjMatrix[1][2] = 10;
        adjMatrix[1][3] = 5;
        adjMatrix[1][4] = 6;
        adjMatrix[2][0] = 8;
        adjMatrix[2][1] = 10;
        adjMatrix[2][2] = 0;
        adjMatrix[2][3] = 8;
        adjMatrix[2][4] = 9;
        adjMatrix[3][0] = 9;
        adjMatrix[3][1] = 5;
        adjMatrix[3][2] = 8;
        adjMatrix[3][3] = 0;
        adjMatrix[3][4] = 6;
        adjMatrix[4][0] = 7;
        adjMatrix[4][1] = 6;
        adjMatrix[4][2] = 9;
        adjMatrix[4][3] = 6;
        adjMatrix[4][4] = 0;
    
        Solver solver = new Solver(cities,adjMatrix);*/
        
        //System.out.println("Enter starting node:");
        //int start = sc.nextInt();
        System.out.println("NEAREST NEIGHBOR HEURISTIC");
        System.out.println("------------------------------------------------------------");
        solver.nearestNeighbor();
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        System.out.println("MINIMUM SPANNING TREE HEURISTIC");
        System.out.println("------------------------------------------------------------");
        solver.MST();
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        System.out.println("VERTEX SWAP HEURISTIC");
        System.out.println("------------------------------------------------------------");
        solver.replaceNode();
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        System.out.println("EDGE SWAP HEURISTIC");
        System.out.println("------------------------------------------------------------");
        solver.replaceEdge();
        System.out.println("------------------------------------------------------------");
    }   
}
