/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author ifish
 */
public class Game {

    public static void main(String[] args) {
        In in = new In(args[0]); // open the input file
        int n = in.readInt();
        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {
            players[i] = new Player(in.readString());
            String s = in.readString();
            String a[] = s.split(",");
            ArrayList<Card> c = new ArrayList<Card>();
            for (int j = 0; j < 5; j++) {
                String b[] = a[j].split("_");
                c.add(new Card(b[1], b[0]));
            }
            players[i].setCards(c);
            if (players[i].Flush()) {
                StdOut.println(players[i].getName());
            }
        }
    }
}
