/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author acer
 */
class Utility {
    
    Position position;
    int value;

    public Utility() {
    }

    public Utility(Position position, int value) {
        this.position = position;
        this.value = value;
    }
    
    public void show()
    {
        position.show();
        System.out.print("Value: " + value + '\n');
    }
}
