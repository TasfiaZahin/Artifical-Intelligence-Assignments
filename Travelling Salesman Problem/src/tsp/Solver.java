/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author acer
 */
class Solver {
  
    double [][] adjMatrix;
    ArrayList <City> cities;
    int n;
    
    public Solver()
    {
        
    }

    Solver(ArrayList<City> cities) {
        
        n = cities.size();
        this.cities = cities;
        adjMatrix = new double [n][n];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                double dist = getDistance(i,j);
                adjMatrix[i][j] = dist;
            }
        }
        
        System.out.println("Adj Matrix:");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(adjMatrix[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();

    }
    
    Solver(ArrayList<City> cities, double[][] adjMatrix) {

        n = cities.size();
        this.cities = cities;
        this.adjMatrix = adjMatrix;
        
        System.out.println("Adj Matrix:");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(adjMatrix[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public double getDistance(int start, int end)
    {
        City src = cities.get(start);
        City dest = cities.get(end);
        
        double dist = Math.sqrt((dest.x - src.x)*(dest.x - src.x) + (dest.y - src.y)*(dest.y - src.y));
        return dist;
    }
    
    void nearestNeighbor() 
    {
        Manager manager = new Manager(cities,adjMatrix);
        manager.nearestNeighbor();
    }
    
    void MST()
    {       
        Manager manager = new Manager(cities,adjMatrix);
        manager.MST();
    }
    
    void replaceNode()
    {       
        Manager manager = new Manager(cities,adjMatrix);
        manager.replaceNode();
    }
    
    void replaceEdge()
    {        
        Manager manager = new Manager(cities,adjMatrix);
        manager.replaceEdge();
    }
}