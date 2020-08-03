/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author acer
 */
public class Manager {
    
    int n;
    int[][] goal;
    HashMap<Integer,Position> map;
    
    public Manager(int n)
    {
        this.n = n;
        goal = new int[n][n];
        map = new HashMap<>();
        
        int count = 1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                goal[i][j] = count;
                map.put(count, new Position(n,i,j));
                count++;
            }
        }
        
        goal[n-1][n-1] = 0;
        map.put(0, new Position(n,n-1,n-1));
    }
    
    public Position getPosition(int value)
    {
        return map.get(value);
    }
    
    public int getValue(Position pos)
    {
        for (int key : map.keySet()) 
        {            
            if(map.get(key).x == pos.x && map.get(key).y == pos.y)
            {
                return key;
            }
        }
        return -1;
    }
    
    public boolean isGoal(int[][] matrix)
    {   
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(goal[i][j] != matrix[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean inPosition(Position pos, int val) 
    {
        if(map.get(val).x == pos.x && map.get(val).y == pos.y)
        {
            return true;
        }
        return false;
    }

    public Position getRandomPosition(int[][] temp) {
        
        ArrayList <Position> list = new ArrayList<>();
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                Position pos = new Position(n,i,j);
                if(!inPosition(pos,temp[i][j]))
                {
                    list.add(pos);
                    return pos;
                }
            }
        }
         
        //Random rand = new Random();
        //int offset = rand.nextInt(list.size()-1);
        //System.out.println("random offset: " + offset);
        //return list.get(offset);
        return null;
    }
}
