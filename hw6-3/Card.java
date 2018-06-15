import java.util.HashMap;
import java.util.Comparator;

public class Card implements Comparable<Card> {
	private String  face;
	private String  suit;
    private int suit2Int;
    
	public static final Comparator<Card> SUIT_ORDER = new SuitOrder();
	
	public Card(String face, String suit){
            this.face = face;
            this.suit = suit;
            switch (suit){
                case "Clubs":
                    this.suit2Int = 0;
                    break;
                case "Diamonds":
                    this.suit2Int = 1;
                    break;
                case "Hearts":
                    this.suit2Int = 2;
                    break;
                case "Spades":
                    this.suit2Int = 3;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    
    public String getFace(){
        return this.face;
    }
        
    public String getSuit(){
        return this.suit;
    }
    
    public int getSuitInt(){
        return this.suit2Int;
    }
    
    private int face2Int(String face){
        switch(face){
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(face);
        }
    }
    
    public int compareTo(Card that) {
        String faceA = this.getFace();
        String faceB = that.getFace();
        int a = face2Int(faceA);
        int b = face2Int(faceB);
        if(a > b){
            return +1;
        }
        else if(a == b){
            if(SUIT_ORDER.compare(this, that) == 1){
                return +1;
            }
            else if(SUIT_ORDER.compare(this, that) == 0){
                return 0;
            }
        }
        return -1;
    }
        
    private static class SuitOrder implements Comparator<Card> {
        	
        public int compare(Card c1, Card c2) {
            int a = c1.getSuitInt();
            int b = c2.getSuitInt();
            if(a > b){
                return +1;
            }
            else if(a == b){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
}

