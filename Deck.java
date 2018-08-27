import java.util.*;
import java.io.*;

public class Deck {
    private int   index;
    private int[] cards;
    
    Deck(int num){
	this.index = 0;
	cards = new int[num];
    }

    public void fillDeck(){
	int count = 0;
	int hold  = 1;
	for (int i = 0; i < cards.length; i++){
	    if ((count > 0) && (count % 4) == 0){
		hold++;
	    }
	    count++;
	    if (hold > 10){
		cards[i] = 10;
	    } else {
	    cards[i] = hold;
	    }
	}
    }

    public void printDeck(){
	for (int i : cards){
	    System.out.print(i);
	}
    }

    public void shuffle(){
    int index;
    int hold;
    Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            hold = cards[index];
            cards[index] = cards[i];
            cards[i] = hold;
	}
	this.index = 0;
    }

    public int dealCard(){
	this.index ++;
 	return cards[this.index-1];
    }

    public int getIndex(){
	return this.index;
    }
}
