package org.jason.cards;
import java.util.Scanner;

public class Round {
	
//The players round
public  int player(Deck deck, Hand hand){
	System.out.println("Players Hand");
	/*deal deck to player*/
	hand.addCard(deck.poll());
	hand.addCard(deck.poll());
	boolean running = true;
	while (running){
		
	
		hand.print();
		Scanner scan = new Scanner(System.in);
		System.out.println("hit? count:"+hand.count());
		int input = scan.nextInt();
		
		if(input==1){
			hand.addCard(deck.poll());//take card from deck, and add it to hand
		}
		else{		
			running = false;//if stop taking cards
		}
	}
	if (hand.isOver21()){
		return Constants.BUST;//BUST = -99999
	}
	return hand.count();
}
public int dealer(Deck deck, Hand hand){
	System.out.println("Dealer's Hand");
	/*deal deck to player*/
	hand.addCard(deck.poll());
	hand.addCard(deck.poll());
	while(hand.count()<12){//how I actually play black jack
		hand.addCard(deck.poll());
	}
	hand.print();
	if (hand.isOver21()){
		return Constants.BUST;//BUST = -99999
	}
	return hand.count();
}

}
