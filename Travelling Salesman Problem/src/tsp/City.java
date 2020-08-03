/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author acer
 */
public class City {
   
    int number;
    public int x;
    public int y;
    City parent;
    
    public City()
    {
        parent = null;
    }
  
    public City(int number,int x,int y)
    {
        this.number = number;
        this.x = x;
        this.y = y;
        parent = null;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.x;
        hash = 17 * hash + this.y;
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (o == this) 
        {
            return true;
        }
        if(o == null)
        {
            return false;
        }
        if(o instanceof City)
        {
            City test = (City) o;
            if(test.x == this.x && test.y == this.y)
            {
                return true;
            }
        }
        return false;
    }
    
    public void show()
    {
        System.out.println("number: " + number);
        System.out.println("x value: " + x);
        System.out.println("y value: " + y);
    }

}
