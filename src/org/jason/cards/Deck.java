package org.jason.cards;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
public class Deck {
	public void addCard(PlayCard a){
		cards.add(a);
	}
	public PlayCard poll(){
		PlayCard card = cards.get(0);
		cards.remove(0);
		return card;
	}
	public void print(){
		for(PlayCard card: cards){
			card.printCard();
		}
	}
	public void generate(){
		for(Suit suit: Suit.values()){
			for(Value value: Value.values()){
				addCard(new PlayCard(value, suit));
			}
		}
	}
	public void shuffle(){
		Collections.shuffle(cards);
		
	}
	
	
	protected List<PlayCard> cards= new LinkedList<PlayCard>();

}
