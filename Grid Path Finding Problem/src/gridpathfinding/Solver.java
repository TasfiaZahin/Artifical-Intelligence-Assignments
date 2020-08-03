/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridpathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author acer
 */
class Solver {

    HashMap<Position, Integer> costSoFar;
    int moves;
    int countNodes;
    int expanded;
    
    public Solver()            
    {
       costSoFar = new HashMap<>();
       moves = 0;   //depth
       countNodes = 0; //generated noes
       expanded = 0;
    }
    
    public void printSolution(Board board, Position pos)
    {
        //System.out.println("hi");
        if(pos.parent != null)
        {
            //System.out.println("by");
            moves++;
            printSolution(board, pos.parent);
            board.grid[pos.x][pos.y] = 5;
            //board.show();
            //System.out.println();
        }
    }
    
    public ArrayList <Position> getNeighbors(Board board, Position pos)
    {
        ArrayList <Position> list = new ArrayList<>();
        
        Position left = new Position();
        left.x = pos.x;
        left.y = pos.y - 1;
        if(left.isValid(board.n) && board.grid[left.x][left.y] != 1)
        {
            list.add(left);
        }
        
        Position right = new Position();
        right.x = pos.x;
        right.y = pos.y + 1;
        if(right.isValid(board.n) && board.grid[right.x][right.y] != 1)
        {
            list.add(right);
        }
        
        Position top = new Position();
        top.x = pos.x - 1;
        top.y = pos.y;
        if(top.isValid(board.n) && board.grid[top.x][top.y] != 1)
        {
            list.add(top);
        }
        
        Position bottom = new Position();
        bottom.x = pos.x + 1;
        bottom.y = pos.y;
        if(bottom.isValid(board.n) && board.grid[bottom.x][bottom.y] != 1)
        {
            list.add(bottom);
        }       
        return list;
    }
    
    void showOnlyPath(Board board)
    {
        for(int i=0;i<board.grid.length;i++)
        {
            for(int j=0;j<board.grid.length;j++)
            {
                if(board.grid[i][j] == 4)
                {
                    board.grid[i][j] = 0;
                }
            }
        }
        board.show();
        System.out.println();
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
        final double TOLERANCE = 0.0001;
        
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
        ArrayList<Double> bfact) {
        
        Position src = board.getSrc();
        Position dest = board.getDest();
        Comparator <Position> comparator = new FuncComparator(costSoFar,dest,mode);
        PriorityQueue <Position> frontier = new PriorityQueue <Position>(30, comparator);
        int flag = 0;

        frontier.add(src);
        costSoFar.put(src, 0);
        //board.grid[src.x][src.y] = 4; 
        
        while(!frontier.isEmpty())
        {
            /*System.out.println("in loop printing frontier\n");
            Iterator<Position> it = frontier.iterator();
            while(it.hasNext() ) 
            {
                it.next().show();
                System.out.println();
            }
            System.out.println("frontier print end\n");
            
            System.out.println("printing costsofar\n");
            for (Position key : costSoFar.keySet()) {
                
                key.show();
                System.out.println("cost:" + costSoFar.get(key) + '\n');             
            }
            System.out.println("end printing costSofar\n");*/
            
            Position current = frontier.poll();
            expanded++;
            /*if(current != src)
            {
                board.grid[current.x][current.y] = 4;
            }*/
            
            if(current.x == dest.x && current.y == dest.y)
            {
                flag = 1;
                
                board.grid[dest.x][dest.y] = 3;
                System.out.println("showing expanded board:\n");
                board.show();
                System.out.println();
                
                //System.out.println("printing solution path:\n");              
                //board.grid[src.x][src.y] = 5;
                //board.show();
                //System.out.println();
                
                printSolution(board, current);
                
                board.grid[src.x][src.y] = 2;
                board.grid[dest.x][dest.y] = 3;
                System.out.println("Showing final board with src and dest:\n");
                showOnlyPath(board);
                //board.show();
                //System.out.println();
                
                System.out.println("path length: " + this.moves);
                break;
            }
            
            ArrayList<Position> neighbors = getNeighbors(board, current);
            
            /*System.out.println("printing neighbors");
            for(int i=0;i<neighbors.size();i++)
            {
                neighbors.get(i).show();
                System.out.println();
            }
            System.out.println("end printing neighbors");*/
            
            for(int i=0;i<neighbors.size();i++)
            {
                Position next = neighbors.get(i);
                int new_cost = costSoFar.get(current) + 1;
                
                //boolean test = costSoFar.containsKey(next);
                //if(test) System.out.println("yes yes present");
                //else System.out.println("not present");
                
                if(!costSoFar.containsKey(next) || new_cost < costSoFar.get(next))
                {
                    countNodes++;
                    //System.out.println("inside\n");
                    costSoFar.put(next, new_cost);
                    board.grid[next.x][next.y] = 4;
                    //board.show();
                    frontier.add(next);
                    next.parent = current;
                }
            }
        }
        
        double bf = -1;
        if(flag == 0)
        {
            this.moves = -1;
            System.out.println("No possible path:\n");
            board.grid[src.x][src.y] = 2;
            board.grid[dest.x][dest.y] = 3;
            board.show();
        }
        
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
