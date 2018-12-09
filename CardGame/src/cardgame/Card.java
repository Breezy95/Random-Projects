/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

public class Card {
String rank,suit;
int num;
    public Card(){
        
    }
    public Card(String rank, String suit, int num){
        this.rank = rank;
        this.suit = suit;
        this.num = num;
    }
    
    public String toString(){
        return num + ":" + rank + " of " + suit;  
    }
    
    
}
