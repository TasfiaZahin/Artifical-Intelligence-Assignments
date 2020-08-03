/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author acer
 */
public class FuncComparator implements Comparator<Board>{
    
    HashMap<Board, Integer> costSoFar;
    int mode;
    
    public FuncComparator(HashMap<Board, Integer> costSoFar, int mode)
    {
        this.costSoFar = costSoFar;
        this.mode = mode;
    }

    @Override
    public int compare(Board b1, Board b2) {
        
        int b1value = 0;
        int b2value = 0;
        
        if(mode == 1)
        {
            b1value = (int) b1.h1();
            b2value = (int) b2.h1();
        }
        else if(mode == 2)
        {
            b1value = (int) b1.h2();
            b2value = (int) b2.h2();
        }
        else if(mode == 3)
        {
            b1value = (int) b1.h3();
            b2value = (int) b2.h3();
        }
        else if(mode == 4)
        {
            b1value = (int) b1.h4();
            b2value = (int) b2.h4();
        }
        else if(mode == 5)
        {
            b1value = (int) b1.h5();
            b2value = (int) b2.h5();
        }
        
        int f1 = (int) (costSoFar.get(b1) + b1value);
        System.out.println("printing t in comparefunc\n");
        b1.show();
        System.out.println(f1);
        System.out.println("end printing t\n");
   
        
        int f2 = (int) (costSoFar.get(b2) + b2value);
        System.out.println("printing t1 in comparefunc\n");
        b2.show();
        System.out.println(f2);
        System.out.println("end printing t1\n");
        //System.out.println("comparing\n");
        if(f1 < f2)
        {
            return -1;
        }
        return 1;
    }

}
