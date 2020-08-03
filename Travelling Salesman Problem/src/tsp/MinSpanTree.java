
package tsp;

import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
public class MinSpanTree { //Kruskal's algo
    
    double [][] adjMatrix;
    int n;
    
    public MinSpanTree()
    {
        
    }
    
    public MinSpanTree(double [][] adjMatrix, int n)
    {
        this.adjMatrix = adjMatrix;
        this.n = n;
    }
    
    public int find(int[] parent,int node)
    {       
        if(parent[node] != node)
        {
            //System.out.println("in find");
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }
    
    public void union(int[] parent, int node1,int node2)
    {
        int root1 = find(parent, node1);
        int root2 = find(parent, node2);
        parent[root1] = root2;
        System.out.println("root of " + node1 + " becomes " + root2);
    }
    
    public ArrayList<Edge> buildTree()
    {
        ArrayList <Edge> edges = new ArrayList<>();
        for(int i=0;i<n-1;i++)
        {
            //System.out.println("ji");
            int src = i;
            for(int j=src+1;j<n;j++)
            {
                int dest = j;
                Edge edge = new Edge (src,dest,adjMatrix[i][j]);
                edges.add(edge);
            }
        }
        
        /*for(int i=0;i<edges.size();i++)
        {
            edges.get(i).printEdge();
        }*/
        
        ArrayList <Edge> MSTedges = new ArrayList<>();
        
        Collections.sort(edges);
        System.out.println("printing sorted edges:");
        
        for(int i=0;i<edges.size();i++)
        {
            edges.get(i).printEdge();
        }
        
        int [] parent = new int [n];
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
        }
        
        System.out.println("Building MST...");
        
        int index = 0;
        while(MSTedges.size() != n-1)
        {
            //System.out.println("in loop");
            Edge next = edges.get(index);
            int root1 = find(parent, next.src);         
            int root2 = find(parent, next.dest);
            System.out.println("src:" + next.src + " dest:" + next.dest + " root of src:" + root1 + " root of dest:" + root2);
            
            if(root1 != root2)
            {
                System.out.println("added " + next.src + "," + next.dest);
                MSTedges.add(next);
                union(parent,root1,root2);
            }
            index++;
        }
        
        System.out.println("Edges in MST: ");
        for(int i=0;i<MSTedges.size();i++)
        {
            MSTedges.get(i).printEdge();
        }
        
        double cost = 0;
        for(int i=0;i<MSTedges.size();i++)
        {
            Edge e = MSTedges.get(i);
            cost += adjMatrix[e.src][e.dest];
        }
        System.out.println("Cost of MST: " + cost);
        return MSTedges;
    }
}
