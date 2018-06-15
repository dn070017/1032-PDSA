/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;
/**
 *
 * @author 1032-pdsa
 */
public class Card implements Comparable<Card> {
	private String face;
	private String suit;
	
	public static final Comparator<Card> SUIT_ORDER = new SuitOrder();
	
	public Card(String face, String suit){
            this.face = face;
            this.suit = suit;
        }
        
        public String getFace(){
            return this.face;
        }
        
        public String getSuit(){
            return this.suit;
        }        
             
        public int compareTo(Card that) {
        /* implement this function such that Cards can be sorted according to the card value
           (consider both face and suit)
        if () return -1;
        if () return +1;
        */
        return 0;
        }
        
        private static class SuitOrder implements Comparator<Card> {
        	
            public int compare(Card c1, Card c2) {
                /* implement this function such that Cards can be sorted according to the suit
                if () return -1;
                if () return +1;
                */
                return 0;
            }
        }

}

