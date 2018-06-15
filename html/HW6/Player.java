/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1032-pdsa
 */
public class Player implements Comparable<Player> {
     private Card[] cards = new Card[5];
     private String name;
     
     public Player(String name) {
         this.name = name;
     }
     
     public String getName() {
         return this.name;
     }
     
     public void setCards(Card[] cards) {
         this.cards = cards;
     }
     
     public int compareTo(Player that) {
         
         /* implement this function such that Players can be sorted according to the poker hand they have
         if () return -1;
         if () return +1;
         */
         return 0;
     }
}

