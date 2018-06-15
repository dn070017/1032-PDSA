/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1032-pdsa
 */
public class Card{
	private String face;
	private String suit;
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
}

