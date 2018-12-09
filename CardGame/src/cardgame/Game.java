/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Fabrice Benoit
 */
public interface Game {
    
    public void gameStart();
    public void turnStart();
    public void drawCard();
    public void turnEnd();
    public void gameEnd();
}
    
    
