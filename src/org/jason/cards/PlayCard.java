package org.jason.cards;

public class PlayCard {
	public PlayCard(Value value, Suit suit){
		_value=value;
		_suit=suit;
	}
	public Suit getSuit(){
		return _suit;
	}
	public  Value getValue(){
		return _value;
	}
	public String getImgUri(){
		String suit = ""+_suit+"s";
		suit=suit.toLowerCase();
		//there needs to be a special case for face cards
		String value="";
		switch(_value){
		case TEN:
			value = "10";
			break;
		case JACK:
			value = "j";
			break;
		case QUEEN:
			value = "q";
			break;
		case KING:
			value = "k";
			break;
		case ACE:
			value = "a";
			break;
			
		default:
			value = ""+rawValue();
			
		}
		
		
		return suit+"-"+value+"-75.png";
	}
	public void printCard(){
		System.out.println("value:"+getValue()+" suit:"+getSuit());
	}
	public PlayCard compareTo(PlayCard a){
		//not implemented
		System.out.println("Not Implemented");
		return a;
	
	}
	public int rawValue(){
		int raw_value;
		switch(this.getValue()){
		case ACE: raw_value =0;//needs special treatment
			break;
		case ONE: raw_value = 1;
			break;
		case TWO: raw_value = 2;
			break;
		case THREE: raw_value =3;
			break;
		case FOUR: raw_value = 4;
			break;
		case FIVE: raw_value = 5;
			break;
		case SIX: raw_value = 6;
			break;
		case SEVEN: raw_value = 7;
			break;
		case EIGHT: raw_value = 8;
			break;
		case NINE: raw_value =9;
			break;
		default: raw_value = 10;
			break;
		}
		return raw_value;
	}


private final Value _value;
private final Suit _suit;
	

}
