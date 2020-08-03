/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gridpathfinding;

/**
 *
 * @author acer
 */
public class Position {
   
    public int x;
    public int y;
    public Position parent;
    
    public Position()
    {
        
    }
    
    public Position(int x,int y)
    {
        this.x = x;
        this.y = y; 
        parent = null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
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
        if(o instanceof Position)
        {
            Position test = (Position) o;
            if(test.x == this.x && test.y == this.y)
            {
                return true;
            }
        }
        return false;
    }
    
    public void show()
    {
        System.out.println("x value: " + x);
        System.out.println("y value: " + y);
    }
    
    boolean isValid(int n)
    {
        if(x>=0 && x<=n-1 && y>=0 && y<=n-1)
        {
            return true;
        }
        return false;
    }

    public int h1(Position dest) //Manhattan dist
    {    
        int dist = 0;
        dist = Math.abs(dest.x - x) + Math.abs(dest.y - y);
        return dist;
    }
    
    public double h2(Position dest) //Euclidean dist
    {
        double dist = 0.0;
        double temp = Math.sqrt((dest.x - x)*(dest.x - x) + (dest.y - y)*(dest.y - y));
        return dist;
    }
    
    public double h3(Position dest) //max of diff of same coordinate
    {

        int diff_x = Math.abs(dest.x - x);
        int diff_y = Math.abs(dest.y - y);
        
        if(diff_x > diff_y)
        {
            return diff_x;
        }
        return diff_y;
    }
}
