/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author acer
 */
public class Solver {
  
    int n;
    HashMap<Board, Integer> costSoFar;
    int moves;
    int countNodes;
    int expanded;
    
    public Solver(int n)            
    {
       costSoFar = new HashMap<>();
       moves = 0;   //depth
       countNodes = 0; //generated nodes
       expanded = 0;
       this.n = n;
    }
    
    public void printSolution(Board board)
    {
        if(board.parent != null)
        {
            moves++;
            printSolution(board.parent);
            board.show();
            System.out.println();
        }
    }
    
    public ArrayList <Board> getNeighbors(Board board)
    {
        Position blank = new Position(n);
        ArrayList <Board> list = new ArrayList<>();
        int grid[][] = board.grid;
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(grid[i][j] == 0)
                {
                    blank.x = i;
                    blank.y = j;
                    break;                    
                }
            }
        }
        //System.out.println("blank position: " + blank.x + " " + blank.y);
        Position left = new Position(n);
        left.x = blank.x;
        left.y = blank.y - 1;
        
        Position right = new Position(n);
        right.x = blank.x;
        right.y = blank.y + 1;
        
        Position top = new Position(n);
        top.x = blank.x - 1;
        top.y = blank.y;
        
        Position bottom = new Position(n);
        bottom.x = blank.x + 1;
        bottom.y = blank.y;

        if(left.isValid())
        {
            int [][] g = new int [n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    g[i][j] = grid[i][j];
                }
            }
            Board b = new Board(n);
            b.grid = g;
 
            int val = b.grid[left.x][left.y];
            b.grid[blank.x][blank.y] = val;
            b.grid[left.x][left.y] = 0;
            list.add(b);
        }
        if(right.isValid())
        {
            int [][] g = new int [n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    g[i][j] = grid[i][j];
                }
            }
            Board b = new Board(n);
            b.grid = g;
      
            int val = b.grid[right.x][right.y];
            b.grid[blank.x][blank.y] = val;
            b.grid[right.x][right.y] = 0;
            list.add(b);           
        }
        if(top.isValid())
        {
            int [][] g = new int [n][n];        
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    g[i][j] = grid[i][j];
                }
            }

            Board b = new Board(n);
            b.grid = g;
            
            int val = b.grid[top.x][top.y];
            b.grid[blank.x][blank.y] = val;
            b.grid[top.x][top.y] = 0;
            list.add(b);
        }
        if(bottom.isValid())
        {
            int [][] g = new int [n][n];
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    g[i][j] = grid[i][j];
                }
            }
        
            Board b = new Board(n);
            b.grid = g;
            
            int val = b.grid[bottom.x][bottom.y];
            b.grid[blank.x][blank.y] = val;
            b.grid[bottom.x][bottom.y] = 0;
            list.add(b);
            //System.out.println("is valid");
        }  
        return list;    
    }
    
    public double funcValue(double x)
    {
        int d = moves;
        int N = countNodes;
        double sum = 0;
        for(int i=1;i<=d;i++)
        {
            sum += Math.pow(x,i);
        }
        return sum - N;
    }
    
    public double findBranchingFactor()
    {
        double a = -1;
        double b = 5;
        double c;
        final double TOLERANCE = 0.00001;
        
        while (Math.abs(a - b) > TOLERANCE) 
        {
            c = (a + b)/2;
            double f = funcValue(c);
            if ((f * funcValue(a)) == 0 || (f * funcValue(b)) == 0) 
            {
                return c;
            } 
            else if (f * funcValue(a) > 0) 
            {
                a = c;
            } 
            else 
            {
                b = c;
            }
        }
        return (a + b)/2;
    }
    
    void solve(Board board, int mode,ArrayList<Integer> steps, 
        ArrayList<Integer> gen, 
        ArrayList<Integer> exp,
        ArrayList<Double> bfact) 
    {         
        Comparator<Board> comparator = new FuncComparator(costSoFar,mode);
        PriorityQueue<Board> frontier = new PriorityQueue<Board>(300, comparator);
        Manager manager = new Manager(n);
        int flag = 0;
        
        frontier.add(board);
        costSoFar.put(board, 0);
        
        while(!frontier.isEmpty())
        {
            System.out.println("in loop printing frontier\n");
            Iterator<Board> it = frontier.iterator();
            while(it.hasNext() ) 
            {
                it.next().show();
                System.out.println();
            }
            System.out.println("frontier print end\n");
            
            System.out.println("printing costsofar\n");
            for (Board key : costSoFar.keySet()) {
               
                key.show();
                System.out.println("cost:" + costSoFar.get(key) + '\n');             
            }
            System.out.println("end printing costSofar\n");
            
            Board current = frontier.poll();
            expanded++;
            //current.show();
            //System.out.println();
            if(manager.isGoal(current.grid))
            {
                flag = 1;
                System.out.println("printing solution path:\n");
                board.show();
                System.out.println();
                printSolution(current);
                System.out.println("No. of moves: " + this.moves);
                break;
            }
            
            ArrayList<Board> neighbors = getNeighbors(current);
            
            System.out.println("printing neighbors");
            for(int i=0;i<neighbors.size();i++)
            {
                neighbors.get(i).show();
                System.out.println();
            }
            System.out.println("end printing neighbors");
            System.out.println(neighbors.size());
            
            for(int i=0;i<neighbors.size();i++)
            {
                Board next = neighbors.get(i);
                int new_cost = costSoFar.get(current) + 1;
                //boolean test = costSoFar.containsKey(next);
                //if(test) System.out.println("yes yes present");
                //else   System.out.println("not present");

                if(!costSoFar.containsKey(next) || new_cost < costSoFar.get(next)) 
                {
                    countNodes++;
                    //System.out.println("nodes searched: " + countNodes);
                    costSoFar.put(next, new_cost);
                    frontier.add(next);
                    next.parent = current;
                    //System.out.println("size of costSofar: " + costSoFar.size());;
                }
            }
        }
        double bf = -1;
        if(flag == 0)
        {
            System.out.println("No possible path");
            this.moves = -1;
        }
        //System.out.println("No of moves: " + moves);
        //System.out.println("size of costSofar: " + costSoFar.size());
        System.out.println("size of generated noes: " + countNodes);
        System.out.println("size of expanded noes: " + expanded);
        
        if(flag == 1)
        {
            bf = findBranchingFactor();
            System.out.println("Branching factor: " + bf);
        }
        
        steps.add(this.moves);
        gen.add(countNodes);
        exp.add(expanded);
        bfact.add(bf);
    }
}
