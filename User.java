import java.util.*;
import java.io.*;

public class User{
    private String pin;
    private int currency;

    User(String pin) {
	File users = null;
	Scanner sc = null;
	
	this.pin = pin;
	try{
	users = new File ("Users.txt");
        sc = new Scanner (users);
	} catch (FileNotFoundException e) {
	}
	while(sc.hasNextLine()){
	    String line = sc.nextLine();
	    if (line.startsWith(pin)){
		line = line.substring(2);
		this.addFunds(Integer.parseInt(line));
	    }
	}
    }
    public void addFunds(int amount){
	this.currency += amount;
    }

    public void bet(int amount){
	this.currency -= amount;
    }

    public int getFunds(){
	return this.currency;
    }
}
