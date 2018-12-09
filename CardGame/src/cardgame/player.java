/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author Fabrice Benoit
 */
public class player {
    boolean needsDeck;
    String name;
    deck graveyard;
    deck cardGame;
    Card[] hand;
    
    public player(){
        
    }

    public player(boolean needsDeck, String name) {
        this.needsDeck = needsDeck;
        this.name = name;
    }
    

    public player(boolean needsDeck, String name,String gameName ) {
        this.needsDeck = needsDeck;
        this.name = name;
        this.cardGame = deck.deckCreator(gameName);
    }

    public player(boolean needsDeck, String name, deck cardGame) {
        this.needsDeck = needsDeck;
        this.name = name;
        this.cardGame = cardGame;
    }
    
    
    public void drawCard(int num){
        int added = 0;
       for(int i = 0;i<num;i++){
               hand[freeSpace()] = cardGame.removeTop();
       }
    }
    
    //do not forget to remove the card from player side when
    //discarding from hand
    public void toGraveyard(Card discard){
        graveyard.addCard(discard);
    }
    
    public void toGraveyard(Card[] discard){
        for(int i = 0;i<discard.length;i++){
            graveyard.addCard(discard[i]);      
        }
    }
    
    public int freeSpace(){
        int space = -1;
        for(int i = 0;i<hand.length;i++){
            if(hand[i] == null)
                space = i;
        }
        return space;
    }
    
    
}
