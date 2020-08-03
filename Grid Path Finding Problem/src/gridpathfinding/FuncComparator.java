/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridpathfinding;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class FuncComparator implements Comparator<Position>{
    
    int mode;
    HashMap<Position, Integer> costSoFar;
    Position dest;
    
    public FuncComparator(HashMap<Position,Integer> costSoFar, Position dest, int mode)
    {
        this.costSoFar = costSoFar;
        this.dest = dest;
        this.mode = mode;
    }
    
    @Override
    public int compare(Position p1, Position p2) 
    {        
        int p1value = 0;
        int p2value = 0;
        
        if(mode == 1)
        {
            p1value = (int) p1.h1(dest);
            p2value = (int) p2.h1(dest);
        }
        else if(mode == 2)
        {
            p1value = (int) p1.h2(dest);
            p2value = (int) p2.h2(dest);
        }
        else if(mode == 3)
        {
            p1value = (int) p1.h3(dest);
            p2value = (int) p2.h3(dest);
        }
        
        int f1 = (int) (costSoFar.get(p1) + p1value);
        //System.out.println("printing t in comparefunc\n");
        //p1.show();
        //System.out.println(f1);
        //System.out.println("end printing t\n");
   
        
        int f2 = (int) (costSoFar.get(p2) + p2value);
        //System.out.println("printing t1 in comparefunc\n");
        //p2.show();
        //System.out.println(f2);
        //System.out.println("end printing t1\n");
        //System.out.println("comparing\n");
        if(f1 < f2)
        {
            return -1;
        }
        return 1;
    }
   
}
