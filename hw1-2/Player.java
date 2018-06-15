/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author cychen
 */
class Player{
     private ArrayList<Card> cards = new ArrayList<Card>();
     private String name;
     
     public Player(String name){
         this.name = name;
     }
     
     public String getName(){
         return this.name;
     }
     
     public void setCards(ArrayList<Card> cards){
         this.cards = cards;
     }
     
     public boolean Flush(){
         // is it Flush?
         boolean flag = true;
         Iterator<Card> it = cards.iterator();
         String s = it.next().getSuit();
         while(it.hasNext()) {
             if (!s.equals(it.next().getSuit())) {
                 flag = false;
             }
         }
         return flag;
     }
}
