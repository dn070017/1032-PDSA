import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class CardTester{
    
    private static int playerCount = 0;
    private static Card[]  cardSort;
    
    public static void main(String[] argv) throws Exception{
        File file = new File(argv[0]);
        Scanner scanner = new Scanner(file);
        
        if(scanner.hasNext()){
            playerCount = Integer.parseInt(scanner.nextLine());
            cardSort = new Card[playerCount*5];
        }
        int number  = 0;
        int cardIdx = 0;
        while(scanner.hasNext()){
            String       name = scanner.nextLine();
            String[]    cards = scanner.nextLine().split(",");
            Card[] cardsArray = new Card[5];
            
            for(int i = 0; i < 5; i++){
                String[] tmp = cards[i].split("_");
                Card card = new Card(tmp[1], tmp[0]);
                cardSort[cardIdx++] = card;
                cardsArray[i] = card;
            }
        }
        Arrays.sort(cardSort);
        for(int i = 0; i < cardSort.length; i++){
            System.out.printf("%s-%s\n", cardSort[i].getSuit(), cardSort[i].getFace());
        }
        Arrays.sort(cardSort, Card.SUIT_ORDER);
        for(int i = 0; i < cardSort.length; i++){
            System.out.printf("%s-%s\n", cardSort[i].getSuit(), cardSort[i].getFace());
        }
    }
}
