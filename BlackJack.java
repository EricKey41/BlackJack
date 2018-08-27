/*

*/

import java.util.*;
import java.io.*;
import java.lang.*;

class BlackJack {
    public static void main(String args[]) {
	Scanner sc = new Scanner(System.in);
	User activeUser = login();
	System.out.print("Welcome, you have: $" + activeUser.getFunds() + "\n");
	play(activeUser,sc);
    }

    public static void play(User activeUser, Scanner sc){
	boolean again     = true;
	boolean gameOver  = false;
	boolean roundOver = false;
	int bet = 0;
	int pot = 0;
	Deck  d = new Deck(getNumOfDecks(sc,activeUser) * 52);
	d.fillDeck();
        d.shuffle();
	do {

	    bet  = placeBet(activeUser,sc);
	    pot += bet * 2;
	    
	    ArrayList userHand  = new ArrayList();
	    ArrayList houseHand = new ArrayList();

	    userHand.add(deal(d));
	    houseHand.add(deal(d));
	    userHand.add(deal(d));
	    houseHand.add(deal(d));
	    if (checkBlackJack(sumOfHand(userHand))) {
		userWin(activeUser,pot);
		} else {
		       do {
		       	int userIn = userChoise(sc);
			switch(userIn){
			    case 1:
				userHand.add(deal(d));
			        break;
			    case 2:
				if (checkBlackJack(sumOfHand(houseHand))) {
					roundOver = true;
				    }
				     else {
					 System.out.print("null");
					//"Flip" of facedown
				}
				break;
			    default:
			        activeUser.bet(bet);
				pot *= 2;
				break;
			}
		    } while ((!roundOver) && (d.getIndex < 45));
		}
	} while ((gameNotOver) && (d.getIndex() < 45));
    }

    public static void output(Table t){
	outputLines();
	printf("%7s%7s","User Hand: ","House Hand: ");
	outputLines();
    }

    public static void userWin(User user, int pot){
	user.addFunds(pot);
    }

    public static int userChoise(Scanner sc) {
	String s = "";
	printf("%2s%2s%2s","1:Hit","2:Stay","3:Double Down");
        s = sc.next();
	while ((!isInt(s)) && (!isValidChoise(s))){
	    System.out.print("Invalid input 1-3 only");
	    s = sc.next();  
	}
	    return Integer.parseInt(s);
    }

    public static boolean isValidChoise(String s){
	if ((Integer.parseInt(s)) < 1 || (Integer.parseInt(s) > 3)){
	    return false;
	}
	return true;
    }
	
    public static boolean isInt(String s){
	try {
	    Integer.parseInt(s);
	} catch (NumberFormatException e){
	    return false;
	}
	return true;
    }

    public static void outputLines(){
	System.out.print("+");
	for ( int i = 0; i < 30; i ++) {
	    System.out.print("-");
	}
	System.out.print("+\n\n");
    }

    public static boolean hasMin(User activeUser){
	if (activeUser.getFunds() >= 5){
	    return true;
	}
	return false;
    }
    
    public static boolean over(int num){
	if (num >= 21){
	    return true;
	}
	return false;
    }

    public static int deal(Deck d){
	return d.dealCard();
    }

    public static int placeBet(User activeUser , Scanner sc){
	int bet = 0;
	System.out.print("Place Bet (Minimum $5): ");
	String in = sc.next();
	System.out.print("\n");
	try {
	    bet = Integer.parseInt(in);
	} catch (Exception e){
	}
	if ((bet < 5) || (bet > activeUser.getFunds())){
	    do {
		System.out.print("Invalid amount \n");
		in = sc.next();
		try {
	       	    bet = Integer.parseInt(in);
       		} catch (Exception e){			}
	    } while ((Integer.parseInt(in) < 5) || (Integer.parseInt(in) > activeUser.getFunds()));
	}
	activeUser.bet(bet);
	return bet;
    }

    public static int sumOfHand(ArrayList hand){
	int hand;
	for (int i : hand){
	    hand += i;
	}
	return hand;
    }

    public static boolean checkBlackJack(int num){
	return num == 21;
    }

    public static boolean isAce(int card){
        return card == 0;
    }

    //User and house interchangeable when checking for win condition
    public static boolean checkWinCondition(int user, int house){
	if ((user > house) && (user <= 21)){
		return true;
	    }
	    return false;
    }

    public static int getNumOfDecks(Scanner sc , User activeUser){
	    String numOfDecks = "";
	    System.out.print("Select deck amount: 1 - 7\n");
	    numOfDecks = sc.next();
	    if ((Integer.parseInt(numOfDecks) > 7) || (Integer.parseInt(numOfDecks) < 0)) {
		do {
		    System.out.print("Invalid input 1 - 7 only\n");
		    numOfDecks = sc.next();
		} while ((Integer.parseInt(numOfDecks) > 7) || (Integer.parseInt(numOfDecks) < 0));
	    }
		return Integer.parseInt(numOfDecks);
     }

    public static User login(){
	String pin = "";
	Scanner sc = new Scanner(System.in);
	do {
	    System.out.print("Enter Pin: ");
	    pin = sc.next();
	} while ((invalid(pin)) && !(isFound(pin)));
	User user = new User(pin);
	return user;
    }

    public static boolean invalid(String in){
	String s = in;
	int hold;
	try {
	    hold = Integer.parseInt(s);
	} catch (Exception e) {
	    System.out.print("Invalid pin\n");
	    return false;
	}
	return true;
    }

    public static boolean isFound(String in){
	File users = null;
	Scanner sc = null;
	try{
	users = new File("Users.txt");
        sc = new Scanner(users);
	} catch (Exception e){
	    System.out.print("Save data not found\n");
	}
	String input = in;
	while (sc.hasNextLine()){
	    String hold = sc.nextLine();
	    if (hold.startsWith(in)) {
		return true;
	    }
	}
	    return false;
    }
}
