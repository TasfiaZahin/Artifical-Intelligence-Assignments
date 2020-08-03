/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg15puzzle;

/**
 *
 * @author acer
 */
public class Position {
   
    int n;
    public int x;
    public int y;
    
    public Position(int n)
    {
        this.n = n;
    }
    
    public Position(int n,int x,int y)
    {
        this.n = n;
        this.x = x;
        this.y = y;   
    }
    
    boolean isValid()
    {
        if(x >= 0 && x < n && y >= 0 && y < n)
        {
            return true;
        }
        return false;
    }
}
