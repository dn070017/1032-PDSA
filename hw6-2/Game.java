import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Game{
    
    private static int playerCount = 0;
    private static Player[] players;
    
    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        
        if(scanner.hasNext()){
            playerCount = Integer.parseInt(scanner.nextLine());
            players = new Player[playerCount];
        }
        int number = 0;
        while(scanner.hasNext()){
            String       name = scanner.nextLine();
            String[]    cards = scanner.nextLine().split(",");
            Player     player = new Player(name);
            Card[] cardsArray = new Card[5];
            players[number++] = player;
            
            for(int i = 0; i < 5; i++){
                String[] tmp = cards[i].split("_");
                Card card = new Card(tmp[1], tmp[0]);
                cardsArray[i] = card;
            }
            player.setCards(cardsArray);
        }
        
        Arrays.sort(players);
        System.out.println(players[playerCount - 1].getName());
    }
}
