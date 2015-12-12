/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textGame;

/**
 *
 * @author Nathan
 */
public class Health {

    TextGame TextGame = new TextGame();
    int value = TextGame.PLAYERHP;
    int MAXPLAYERHP = TextGame.MAXPLAYERHP;

    public Health(int value) {
        if (value < 0 || value > MAXPLAYERHP) {
            throw new IllegalArgumentException();
        } else {
            this.value = value;
        }
    }


    public int getHealthValue() {
        return value;
    }

    public void setHealthValue(int newValue) {
        if (newValue < 0 || newValue > MAXPLAYERHP) {
            value = MAXPLAYERHP;
        } else {
            value = newValue;
        }
    }

}
