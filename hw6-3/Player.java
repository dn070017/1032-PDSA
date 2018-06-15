public class Player implements Comparable<Player> {
    /* 
        Hand:
        5: Full House
        4: Flush
        3: Straight
        2: Two Pairs
        1: One Pairs
        0: High Cards
    */
    
    private Card[] cards = new Card[5];
    private String name;
    private int    hand;
    private Card   bestCard;
    
    public Player(String name) {
        this.name = name;
    }
     
    public String getName() {
        return this.name;
    }
    
    public int getHand(){
        return this.hand;
    }
    
    public Card getBestCard(){
        return this.bestCard;
    }
    
    public void InsertionSort(Card[] cards){
        for(int i = 0; i < cards.length; i++){
            for(int j = i; j > 0; j--){
                if(cards[j].compareTo(cards[j-1]) < 0){
                    Card tmp   = cards[j];
                    cards[j]   = cards[j-1];
                    cards[j-1] = tmp;
                }
                else{
                    break;
                }
            }
        }
    }
    
    private int suit2Int(String suit){
        switch(suit){
            case "Clubs":
                return 0;
            case "Diamonds":
                return 1;
            case "Hearts":
                return 2;
            case "Spades":
                return 3;
            default:
                throw new IllegalArgumentException();
        }
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

    public void setCards(Card[] cards) {
        int[] faceCount  = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] suitNumber = {0, 0, 0, 0, 0, 0};
        int[] faceNumber = {0, 0, 0, 0, 0};
        int[] suitCount  = {0, 0, 0, 0};
        InsertionSort(cards);
        this.cards = cards;
        
        for(int i = 0; i < 5; i++){
            int suit = suit2Int(cards[i].getSuit());
            int face = face2Int(cards[i].getFace()) - 2;
            suitCount[suit] += 1;
            faceCount[face] += 1;
        }
        for(int i = 0; i < 4; i++){
            suitNumber[suitCount[i]] += 1;
        }
        for(int i = 0; i < 13; i++){
            faceNumber[faceCount[i]] += 1;
        }

        // Full House
        if(faceNumber[3] == 1 && faceNumber[2] == 1){
            int bestCardFace = -1;
            this.hand = 5;
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 3){
                    bestCardFace = i + 2;
                    break;
                }
            }
            for(int i = 4; i >= 0; i--){
                if(face2Int(cards[i].getFace()) == bestCardFace){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // Flush
        else if(suitNumber[5] == 1){
            this.hand = 4;
            this.bestCard = cards[4];
            return;
        }
        
        // Straight and High Card
        else if(faceNumber[1] == 5){
            int count = 5;
            int index = 0;
            int[] straight = new int[5];
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 1){
                    straight[index++] = i;
                }
            }
            if(straight[4] == 12 && straight[0] == 0){
                count = 4;
            }
            int tmp = 1;
            for(int i = 0; i < 4; i++){
                if(straight[i + 1] - straight[i] == 1){
                    tmp++;
                }
                else{
                    break;
                }
            }
            if(tmp == count){
                this.hand = 3;
                if(count == 5){
                    this.bestCard = cards[4];
                    return;
                }
                else{
                    this.bestCard = cards[3];
                    return;
                }
            }
            else{
                this.hand = 0;
                this.bestCard = cards[4];
                return;
            }
        }
        
        // Two Pairs
        else if(faceNumber[2] == 2){
            int bestCardFaceA = -1;
            int bestCardFaceB = -1;
            int i;
            this.hand = 2;
            for(i = 0; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFaceA = i + 2;
                    break;
                }
            }
            for(i = i + 1; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFaceB = i + 2;
                    break;
                }
            }
            for(i = 4; i >= 0; i--){
                int face = face2Int(cards[i].getFace());
                if(face == bestCardFaceA || face == bestCardFaceB){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // One Pair
        else if(faceNumber[2] == 1){
            int bestCardFace = -1;
            this.hand = 1;
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFace = i + 2;
                    break;
                }
            }
            for(int i = 4; i >= 0; i--){
                if(face2Int(cards[i].getFace()) == bestCardFace){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // High Card
        else{
            // System.out.printf("Some Error Occurs\n");
        }
        // System.out.printf("%s: %s, %s-%s\n", this.name, this.hand, this.bestCard.getSuit(), this.bestCard.getFace());
        return;
    }
    
    public int compareTo(Player that) {
        int handA = this.getHand();
        int handB = that.getHand();
        if(handA > handB){
            return +1;
        }
        else if(handA == handB){
            return this.getBestCard().compareTo(that.getBestCard());
        }
        else{
            return -1;
        }
    }
}

