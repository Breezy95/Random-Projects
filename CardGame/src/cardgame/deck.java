/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author Fabrice Benoit
 */
public class deck {
    ArrayList<Card> deck = new ArrayList<>();
    Card[] cds = new Card[10];

    
    
    public deck(){
    }
 
    public static deck deckCreator(String gameName){
        deck neoDeck = null;
        if(gameName.toLowerCase().contentEquals("war"))
            neoDeck = warDeck();

     
        return neoDeck;
    }
    
    
    public static deck warDeck(){
        deck neoDeck = new deck();
    ArrayList<Card> deck = new ArrayList<>();
    String value = "";
    int j = 2;
    for(int i = 0; i<52;i++){
        if(j>14)
            j =2;
        if(i<13){
            if(j == 14)
                value = "Ace";
            if(j==13)
                value = "King";
            if(j == 12)
                value = "Queen";
            if(j == 11)
                value = "Jack";
            if(j<11)
                value = Integer.toString((i%13) +2);
            deck.add(new Card(value,"Spade",(i%13) + 1));
        }
        if(i<26 && i>=13){
            if(j == 14)
                value = "Ace";
         if(j==13)
                value = "King";
            if(j == 12)
                value = "Queen";
            if(j == 11)
                value = "Jack";
            if(j<11)
                value = Integer.toString((i%13) + 2);
            deck.add(new Card(value,"Hearts",(i%13) + 1)); 
        }
        if(i<39 && i>=26){
            if(j == 14)
                value = "Ace";
            if(j==13)
                value = "King";
            if(j == 12)
                value = "Queen";
            if(j == 11)
                value = "Jack";
            if(j<11)
                value = Integer.toString((i%13) + 2);
            deck.add(new Card(value,"Diamonds",(i%13) + 1));
        }
      
        if(i<52 && i>=39){
            if(j == 14)
                value = "Ace";
            if(j==13)
                value = "King";
            if(j == 12)
                value = "Queen";
            if(j == 11)
                value = "Jack";
            if(j<11)
                value = Integer.toString((i%13) + 2);
            deck.add(new Card(value,"Clubs",(i%13) + 1));
        }
        j++;
            }
        neoDeck.deck = deck;
    return neoDeck;
        
        
    } 
    
    public void addCard(Card cd){
        deck.add(cd);
    }
    
    public Card removeTop(){
        return deck.remove(0);
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
}
