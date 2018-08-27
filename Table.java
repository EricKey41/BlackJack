import java.lang.*;
import java.util.*;
class Table {
    private ArrayList playerHand;
    private ArrayList houseHand;

    Table (ArrayList player, ArrayList house) {
	this.playerHand = (ArrayList<int>)player.clone();
	this.houseHand  = (ArrayList<int>)house.clone();
    }

    public int getUserHand(){
	return this.userHand();
    }

    public int getHouseHand(){
	return this.houseHand();
    }

    public int addCardToPlayer(int card){
	int card = this.playerHand.add(card);
	return card;
    }

    public void addCardToHouse(int card){
	this.houseHand.add(card);
    }
}
    
