package org.jason.cards;

import java.util.List;

public class Hand extends Deck {
	public PlayCard discard(int index){
		PlayCard card = cards.get(0);
		cards.remove(0);
		return card;
		 
	}
	/*@Override
	public void addCard(PlayCard a){
		cards.add(a);
		//maybe later
		
	}*/
	public int count(){
		int count=0;
		for(PlayCard card: cards){
			int raw=card.rawValue();
			if (raw==0 && count<=10)
				raw=11;
			else if (raw==0){
				raw=1;
			}
			count+=raw;
		}
		return count;
	}
	
	
		
		
		

	
	public List<PlayCard> getList(){
		return cards;
	}
	public boolean isOver21(){
		
		return (count()>21);
	}

}
