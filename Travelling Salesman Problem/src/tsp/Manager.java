/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author acer
 */
public class Manager {
    
    ArrayList<City> cities;
    double [][] adjMatrix;
    int n;
    
    public Manager()
    {
        
    }
    
    public Manager(ArrayList<City> cities, double[][] adjMatrix)
    {
        n = cities.size();
        this.cities = cities;
        this.adjMatrix = adjMatrix;  
    }
    
    public int getNearestNeighbor(boolean [] visited, int start) {
         
        double minDist = 0.0;
        int minDistCity = 0;
        
        for(int i=0;i<n;i++)
        {   
            if(i != start && visited[i] == false)
            {
                minDist = adjMatrix[start][i];
                minDistCity = i;
                break;
            }
        }
        
        for(int i=0;i<n;i++)
        {   
            if(i != start && visited[i] == false)
            {
                if(adjMatrix[start][i] < minDist)
                {
                    minDist = adjMatrix[start][i];
                    minDistCity = i; 
                }
            }
        }
        
        //pathCost += minDist;
        return minDistCity;
    }
    
    public Boolean isAllVisited(boolean [] visited)
    {
        for(int i=0;i<n;i++)
        {
            if(visited[i] == false)
            {
                return false;
            }
        }
        return true;
    }
    
    public void printPath(ArrayList<Integer> solution, double cost)
    {
        //System.out.println("The path is:");
        
        for(int i=0;i<solution.size()-1;i++)
        {
            System.out.print(solution.get(i) + "->");
        }
        
        if(solution.size() >= 1)
        {
            System.out.println(solution.get(solution.size()-1));
        }
        
        System.out.println("Total Cost: " + cost);
    }
    
    public double getPathCost(ArrayList<Integer> nodes)
    {
        double cost = 0;
        
        for(int i=0;i<nodes.size()-1;i++)
        {
            cost += adjMatrix[nodes.get(i)][nodes.get(i+1)];
        }
   
        return cost;
    }
    
    public ArrayList<Integer> nearestNeighbor() { //nearest neighbor algo
        
        System.out.println("Solving using Nearest Neighbor heuristic....");
        System.out.println("Path for starting node: 0");
        
        int node = 0;
        boolean [] visited = new boolean[n];
        for(int i=0;i<n;i++)
        {
            visited[i] = false;
        }

        int current = node;
        ArrayList<Integer> solution = new ArrayList<>();
        visited[current] = true;
        solution.add(current);

        while(!isAllVisited(visited))
        { 
            current = getNearestNeighbor(visited,current);
            visited[current] = true;
            solution.add(current);    
        }       
        solution.add(node);
        
        ArrayList<Integer> minPath = new ArrayList<Integer>(); //initial min values
        for(int i=0;i<solution.size();i++)
        {
            minPath.add(solution.get(i));
        }
        
        double minCost = getPathCost(minPath);
        printPath(solution,minCost);
        
        //path for rest nodes

        for(node=1;node<n;node++)
        {
            visited = new boolean[n];
            for(int i=0;i<n;i++)
            {
                visited[i] = false;
            }

            current = node;
            solution = new ArrayList<>();
            visited[current] = true;
            solution.add(current);

            while(!isAllVisited(visited))
            { 
                current = getNearestNeighbor(visited,current);
                visited[current] = true;
                solution.add(current);    
            }       
            
            solution.add(node);
            
            double cost = 0;
            cost = getPathCost(solution);
            
            if(cost < minCost)
            {
                minCost = cost;
                minPath.clear();
                for(int j=0;j<solution.size();j++)
                {
                   minPath.add(solution.get(j));
                }
            }
            System.out.println("Path for starting node " + node);
            printPath(solution,cost);
        }
        
        System.out.println("Final nearest Neighbor path is:");
        printPath(minPath,minCost);
        return minPath;
    }
    
    public boolean isInMSTedges(int src, int dest, ArrayList <Edge> MSTedges)
    {
        for(int i=0;i<MSTedges.size();i++)
        {
            Edge e = MSTedges.get(i);
            if((e.src == src && e.dest == dest) || (e.src == dest && e.dest == src))
            {
                return true;
            }
        }
        return false;
    }
    
    public void DFSvisit(boolean [] explored, int start, ArrayList<Integer>DFSnodes, ArrayList <Edge> MSTedges)
    {
        explored[start] = true;
        //System.out.print(start + "->");
        DFSnodes.add(start);
        for(int i=0;i<n;i++)
        {
            if(!explored[i] && isInMSTedges(start, i, MSTedges))
            {
                DFSvisit(explored, i, DFSnodes, MSTedges);
                //System.out.print(i + "->");
            }
        }
        //System.out.print(start + "->");
    }

    public ArrayList<Integer> MST() {
       
        ArrayList <Edge> MSTedges = new ArrayList<Edge>();
        MinSpanTree minSpanTree = new MinSpanTree(adjMatrix,n);
        MSTedges = minSpanTree.buildTree();
        
        /*System.out.println("Getting possible path using nearest neighbor...");
        ArrayList<Integer> possiblePath = nearestNeighbor();
        for(int i=0;i<possiblePath.size()-1;i++)
        {
            int src = possiblePath.get(i);
            int dest = possiblePath.get(i+1);
            Edge ed = new Edge(src,dest,adjMatrix[src][dest]);
            MSTedges.add(ed);
        }
  
        double maxCost = MSTedges.get(0).cost;
        //System.out.println("MaxCost: " + maxCost);
        int index = 0;
        for(int i=0;i<MSTedges.size();i++)
        {
            if(MSTedges.get(i).cost > maxCost)
            {
                //System.out.println("aaaa " + MSTedges.get(i).cost);
                maxCost = MSTedges.get(i).cost;
                //System.out.println("MaxCost up: " + maxCost);
                index = i;
            }
        }
        System.out.println("MaxCost: " + maxCost + " index: " + index);
        MSTedges.remove(index);*/
        
        /*System.out.println("MST edges are:");
        for(int i=0;i<MSTedges.size();i++)
        {
            MSTedges.get(i).printEdge();
        }
        
        double sum = 0;
        
        for(int i=0;i<MSTedges.size();i++)
        {
            sum += MSTedges.get(i).cost;
        }
        System.out.println("MST cost: " + sum);*/
        
        System.out.println("Starting MST heuristic...");
        //run dfs from all nodes to get preorder path
        
        System.out.println("MST path for starting node 0");
        
        int node = 0;
        
        boolean [] explored = new boolean [n];
        
        for(int i=0;i<n;i++)
        {
            explored[i] = false;
        }
        
        ArrayList<Integer> DFSnodes = new ArrayList<Integer>();
        //System.out.println("size:" + DFSnodes.size());
        
        //System.out.println("printing DFS path");
        DFSvisit(explored, node, DFSnodes, MSTedges);
        DFSnodes.add(node);
        //System.out.println("size:" + DFSnodes.size());        
        //System.out.println("printing DFS nodes:");
 
        ArrayList<Integer> minPath = new ArrayList<Integer>(); //initial min values
        for(int i=0;i<DFSnodes.size();i++)
        {
            minPath.add(DFSnodes.get(i));
        }
        
        double minCost = getPathCost(DFSnodes);
        printPath(DFSnodes,minCost);
        
        //DFS from rest nodes
        
        for(node=1;node<n;node++)
        {       
            explored = new boolean [n];

            for(int i=0;i<n;i++)
            {
                explored[i] = false;
            }

            DFSnodes = new ArrayList<Integer>();
            //System.out.println("size:" + DFSnodes.size());

            //System.out.println("printing DFS path");
            DFSvisit(explored, node, DFSnodes, MSTedges);
            DFSnodes.add(node);
            //System.out.println("size:" + DFSnodes.size());        
            //System.out.println("printing DFS nodes:");

            double cost = 0;
            cost = getPathCost(DFSnodes);
            if(cost < minCost)
            {
                minCost = cost;
                minPath.clear();
                for(int j=0;j<DFSnodes.size();j++)
                {
                   minPath.add(DFSnodes.get(j));
                }
            }
            System.out.println("Path for starting node " + node);
            printPath(DFSnodes,cost);
        }
        
        System.out.println("Minimum MST path is:");
        printPath(minPath,minCost);
        return minPath;
    }
    
    public ArrayList<Integer> moveNode(ArrayList<Integer> path, int index)
    { 
        ArrayList<Integer> minPath = new ArrayList<Integer>();
        
        /*int pos = -1;
        for(int i=0;i<path.size();i++)
        {
            if(path.get(i) == node)
            {
                pos = i;
            }
        }*/
        
        int pos = index;
        
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int j=0;j<path.size();j++)
        {
            temp.add(path.get(j));
            minPath.add(path.get(j));
        }
        
        double minCost = getPathCost(minPath);
        System.out.println("initial minCost: " + minCost);
        printPath(path,minCost);
        
        //System.out.println("pos: " + pos);
        
        temp.remove(temp.size()-1);
        
        while(pos > 0)
        {
            //System.out.println("ipos: " + pos);
            //printPath(temp,-2);
            //System.out.println("printing swap " + pos);
            Collections.swap(temp, pos, pos-1);
            //printPath(temp,-1);
            pos = pos - 1;
            //System.out.println("possss" + pos);
            
            temp.add(temp.get(0));
            double cost = getPathCost(temp);
            printPath(temp,cost);
            temp.remove(temp.size()-1);
            
            if(cost < minCost)
            {
                minCost = cost;
                minPath.clear();
                for(int k=0;k<temp.size();k++)
                {
                   minPath.add(temp.get(k));
                }
                minPath.add(minPath.get(0));
            }                      
        }

        //System.out.println("final minPath:");
        //printPath(minPath, minCost);
        
        /*pos = -1;
        for(int i=0;i<path.size();i++)
        {
            if(path.get(i) == node)
            {
                pos = i;
            }
        }*/
        
        pos = index;
        
        //printPath(path,-1);
        temp = new ArrayList<Integer>();
        for(int j=0;j<path.size();j++)
        {
            temp.add(path.get(j));
        }
        //System.out.println("pos: " + pos);
        //printPath(temp,-1);
        
        temp.remove(temp.size()-1);
        
        while(pos < temp.size()-1)
        {
            //printPath(temp,0);
            //System.out.println("printing swap " + pos);
            Collections.swap(temp, pos, pos+1);
            //printPath(temp,0);
            pos = pos + 1;
            
            temp.add(temp.get(0));
            double cost = getPathCost(temp);
            printPath(temp,cost);
            temp.remove(temp.size()-1);
            
            if(cost < minCost)
            {
                minCost = cost;
                minPath.clear();
                for(int k=0;k<temp.size();k++)
                {
                   minPath.add(temp.get(k));
                }
                minPath.add(minPath.get(0));
            }                      
        }
        
        System.out.println("minPath:");
        printPath(minPath, minCost);
        
        return minPath;
    }
    
    public void replaceNode()
    {
        ArrayList<Integer> tempPath = nearestNeighbor();
        
        ArrayList<Integer> minPath = new ArrayList<>();  //initial min values
        ArrayList<Integer> finalMinPath = new ArrayList<>();
        
        for(int i=0;i<tempPath.size();i++)
        {
            minPath.add(tempPath.get(i));
            finalMinPath.add(tempPath.get(i));
        }
        double minCost = getPathCost(minPath);
        double finalMinCost = getPathCost(minPath);
        
        /*System.out.println("printing temp path:");
        for(int i=0;i<tempPath.size();i++)
        {
            System.out.print(tempPath.get(i) + "->");
        }*/
        
        System.out.println("Solving using vertex swap heuristic....");
        
        if(n > 2)
        {
            //System.out.println("Node replacement path for node in index 0");
            
            //ArrayList<Integer> minPath = moveNode(tempPath,0);  //initial min values
            //double minCost = getPathCost(minPath);
            
            int cnt = 1;
            while(true)
            {    
                System.out.println("ITERATION " + cnt);
                System.out.println("Iteration initial path:");
                printPath(tempPath,getPathCost(tempPath));
                System.out.println();
                
                minPath.clear();
                for(int i=0;i<tempPath.size();i++)
                {
                    minPath.add(tempPath.get(i));
                }
                minCost = getPathCost(minPath);
                
                for(int i=0;i<tempPath.size()-1;i++)
                {
                    System.out.println("Vertex swap path for node in index " + i);
                    ArrayList<Integer> temp = moveNode(tempPath,i);
                    double cost = getPathCost(temp);
                    if(cost < minCost)
                    {
                        minCost = cost;
                        minPath.clear();
                        for(int j=0;j<temp.size();j++)
                        {
                           minPath.add(temp.get(j));
                        }
                    }
                }
                System.out.println("Final vertex swap minPath in current iteration " + cnt + " is:");
                printPath(minPath, minCost);
                
                tempPath.clear();
                for(int i=0;i<minPath.size();i++)
                {
                    tempPath.add(minPath.get(i));
                }
                
                if(minCost < finalMinCost)
                {
                    System.out.println("BETTER PATH FOUND, ITERATING AGAIN :D");
                    finalMinCost = minCost;
                    finalMinPath.clear();
                    for(int i=0;i<tempPath.size();i++)
                    {
                        finalMinPath.add(minPath.get(i));
                    }
                    //System.out.println("print temPath for itn");
                    //printPath(tempPath, getPathCost(tempPath));
                }
                else
                {
                    break;
                }
                cnt++;
            }
            
            System.out.println("Final vertex swap minPath after all iterations is:");
            printPath(finalMinPath, finalMinCost);
        }
        else
        {
            nearestNeighbor();
        }    
    }
    
    public ArrayList<Integer> swapEdge(ArrayList<Integer> path, Edge edge1, Edge edge2)
    { 
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int j=0;j<path.size();j++)
        {
            temp.add(path.get(j));
        }
        
        temp.remove(temp.size()-1); //removing src in the end
        
        /*System.out.println("printing temp after copying:");
        for(int i=0;i<temp.size();i++)
        {
            System.out.print(temp.get(i) + "->");
        }
        System.out.println();*/
        
        ArrayList<Integer> subset1 = new ArrayList<>();
        //subset1.add(edge1.src);
        subset1.add(edge2.src);
        subset1.add(edge2.dest);
        subset1.add(edge1.dest);
        
        ArrayList<Integer> subset2 = new ArrayList<>();
        //subset2.add(edge1.src);
        subset2.add(edge2.dest);
        subset2.add(edge2.src);
        subset2.add(edge1.dest);
        
        //System.out.println(edge1.src + " " + edge1.dest + " " + edge2.src + " " + edge2.dest);
        //System.out.println(temp.size());
        
        for(int i=0;i<temp.size();i++)
        {
            //System.out.println("pi" + i);
            if(temp.get(i) == edge1.dest || temp.get(i) == edge2.src || temp.get(i) == edge2.dest)
            {
                //System.out.println("rem" + i);
                temp.remove(i);
                i--; 
            }
        }
        
        /*System.out.println("elements in temp after removing nodes to be changed:");
        for(int i=0;i<temp.size();i++)
        {
            System.out.println(temp.get(i));
        }*/
        
        ArrayList<Integer> temp1 = new ArrayList<>();
        for(int i=0;i<temp.size();i++)
        {
            temp1.add(temp.get(i));        
        }
        
        int index = temp.indexOf(edge1.src);
        //System.out.println("index1: " + index);
        
        for(int i=0;i<subset1.size();i++)
        {         
            temp.add(index+1, subset1.get(i));
            index++;
        }
        
        index = temp1.indexOf(edge1.src);
        //System.out.println("index2: " + index);
        
        for(int i=0;i<subset2.size();i++)
        {         
            temp1.add(index+1, subset2.get(i));
            index++;
        }

        temp.add(temp.get(0));
        temp1.add(temp1.get(0));
        
        double cost = getPathCost(temp);
        double cost1 = getPathCost(temp1);
        
        System.out.println("first type of swap:");
        printPath(temp, cost);
        
        System.out.println("second type of swap:");
        printPath(temp1, cost1);
        
        if(cost < cost1)
        {
            System.out.println("minCost: " + cost);
            return temp;
        }
        
        System.out.println("minCost: " + cost1);
        return temp1;
    }
    
    public void replaceEdge()
    {
        ArrayList<Integer> tempPath = nearestNeighbor();
        
        ArrayList<Integer> minPath = new ArrayList<>();  //initial min values
        ArrayList<Integer> finalMinPath = new ArrayList<>();
        
        for(int i=0;i<tempPath.size();i++)
        {
            minPath.add(tempPath.get(i));
            finalMinPath.add(tempPath.get(i));
        }
        double minCost = getPathCost(minPath);
        double finalMinCost = getPathCost(finalMinPath);
        
        System.out.println("Solving using edge swap heuristic....");
        
        if(n > 3)
        {      
            int cnt = 1;
            while(true)
            {
                System.out.println("ITERATION " + cnt);
                System.out.println("Iteration initial path:");
                printPath(tempPath,getPathCost(tempPath));
                System.out.println();
                
                minPath.clear();
                for(int i=0;i<tempPath.size();i++)
                {
                    minPath.add(tempPath.get(i));
                }
                minCost = getPathCost(minPath);
                
                ArrayList <Edge> edges = new ArrayList<>();
                for(int i=0;i<tempPath.size()-1;i++)
                {
                    int src = tempPath.get(i);
                    int dest = tempPath.get(i+1);
                    Edge edge = new Edge (src,dest,adjMatrix[src][dest]);
                    edges.add(edge);
                }

                System.out.println("Printing all edges:");
                for(int i=0;i<edges.size();i++)
                {
                    edges.get(i).printEdge();
                }

                int index1 = 0;
                int index2 = 2;

                Edge edge1 = edges.get(index1);
                Edge edge2 = edges.get(index2);

                //System.out.println("Swapping index1: " + index1 + " index2: " + index2);

                //ArrayList<Integer> minPath = swapEdge(tempPath, edge1, edge2);
                //double minCost = getPathCost(minPath);
           
                System.out.println("Iteration " + cnt);
                for(int i=0;i<edges.size()-1;i++)
                {
                    edge1 = edges.get(i);
                    for(int j=i+1;j<edges.size();j++)
                    {                    
                        edge2 = edges.get(j);          
                        if(edge1.dest != edge2.src)
                        {
                            System.out.println("Swapping index1: " + i + " index2: " + j);
                            ArrayList<Integer> temp = swapEdge(tempPath, edge1, edge2);
                            double cost = getPathCost(temp);
                            if(cost < minCost)
                            {
                                minCost = cost;
                                minPath.clear();
                                for(int k=0;k<temp.size();k++)
                                {
                                   minPath.add(temp.get(k));
                                }
                            }
                        }
                    }
                }

                /*Random rand = new Random();
                int index1 = rand.nextInt(edges.size()-1);
                int index2 = rand.nextInt(edges.size()-1);

                while(index1 == index2 || Math.abs(index1 - index2) == 1)
                {
                    index2 = rand.nextInt(edges.size()-1);
                }

                Edge edge1 = edges.get(index1);
                Edge edge2 = edges.get(index2);

                System.out.println("index1: " + index1 + " index2: " + index2);

                minPath = swapEdge(tempPath, edge1, edge2);
                minCost = getPathCost(minPath);*/

                System.out.println("Final edge swap minPath in current iteration " + cnt + " is:");
                printPath(minPath, minCost);
                
                tempPath.clear();
                for(int i=0;i<minPath.size();i++)
                {
                    tempPath.add(minPath.get(i));
                }
                
                if(minCost < finalMinCost)
                {
                    System.out.println("BETTER PATH FOUND, ITERATING AGAIN :D");
                    finalMinCost = minCost;
                    finalMinPath.clear();
                    for(int i=0;i<tempPath.size();i++)
                    {
                        finalMinPath.add(minPath.get(i));
                    }
                    //System.out.println("print temPath for itn");
                    //printPath(tempPath, getPathCost(tempPath));
                }
                else
                {
                    break;
                }
                cnt++;
            }
            
            System.out.println("Final edge swap minPath after all iterations is:");
            printPath(finalMinPath, finalMinCost);

            /*Edge edge1 = edges.get(0);
            Edge edge2 = edges.get(2);
            ArrayList<Integer> temp = swapEdge(tempPath, edge1, edge2);
            System.out.println("Final cost: " + getPathCost(temp));*/
        }
        else
        {
            nearestNeighbor();
        }
    }
}
