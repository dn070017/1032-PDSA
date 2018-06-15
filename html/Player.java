/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author 1032-pdsa
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
         boolean flag;
         
         // implement this function to determine if the cards hold as a hand of Flush?
         
         return flag;
     }
}

