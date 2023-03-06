//import java.util.Scanner;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.HashMap;
//import javax.swing.text.DefaultEditorKit.CutAction;
//import java.util.ArrayList;
import java.util.List;

//import javafx.util.converter.PercentageStringConverter;

public class Game {
    //Card[] BotHand = new Card[2];
    //Card[] CommunityCards = new Card[5];
    //int PlayerCount = 0;

        
    public int Score5Cards(Card[] cards){
        Card temp;

        for(int j = 1; j < 5; j++){
            for(int k = j; k> 0; k--){
                if(cards[k].value < cards[k-1].value){
                    temp = cards[k];
                    cards[k] = cards[k-1];
                    cards[k-1] = temp;
                }
            }
        }
        //check straight flush
        if(isStraight(cards) && isFlush(cards)){
            return 3069 - 2 + cards[4].value;
        }
        //check four of a kind
        int[] Duplicates = findDuplicates(cards);
        int kicker = 0;
        for(int i = 0; i > 14; i++){
            if(Duplicates[i] == 1){
                kicker = Duplicates[i];
            }
            if(Duplicates[i] == 4){
                return 2900 + (i-2)*13 + (kicker-2);
            }
        }
        //check FH
        int FHT = 0;
        int FHD = 0;
        for(int i = 0; i < 14; i++){
            if(Duplicates[i] == 3){
                FHT = i;
            }
            if(Duplicates[i] == 2){
                FHD = i;
            }
            if(FHT != 0 && FHT != 0){
                return 2731 + ((FHT-2)*13)+(FHD-2);
            }
        }
        //check Flush
        if(isFlush(cards)){
            return(2718 + cards[4].value-2);
        }
        //check straight
        if(isStraight(cards)){
            return(2705 + cards[4].value-2);
        }
        //check Three of a Kind
            for(int i = 0; i < 14; i++){
            if(Duplicates[i] == 3){
                for(int j = 0; j < 5; j++){
                    if(cards[j].value != i){
                        kicker = cards[j].value;
                    }
                }
                return 2536 + (i-2)*13+kicker-2;
            }
        }
        //check two pair
        for(int i = 0; i < 14; i++){
            if(Duplicates[i] == 2){
                for(int k = 0; k < 14; k++){
                    if(k != i && Duplicates[k]==2){
                        for(int j = 0; j < 5; j++){
                            if(cards[j].value != i && cards[j].value != k){
                                kicker = cards[j].value;
                            }
                        }
                        return 339 +(k-2)*169+(i-2)*13+kicker-2;
                    }
                }   
            }
        }
        //check pair
        for(int i = 0; i < 14; i++){
            if(Duplicates[i] == 2){
                for(int j = 0; j < 5; j++){
                    if(cards[j].value != i){
                        kicker = cards[j].value;
                    }
                }
                return 170 + (i-2)*13+kicker-2;
            }
        }
        //check highs card
        return (cards[4].value-2)*13+cards[3].value-2;

    }


    public Boolean isStraight(Card[] cards){
        for(int j = 0; j < 4; j++){
            if(cards[j].value != cards[j+1].value-1){
                return false;
            }
        }
        return true;
    }

    public Boolean isFlush(Card[] cards){
        for(int j = 0; j < 4; j++){
            if(cards[j].suit != cards[j+1].suit){
                return false;
            }
        }
        return true;
    }

    public int[] findDuplicates(Card[] cards){
        int[] Duplicates = new int[14];
        for(int i = 0; i < cards.length; i++){
            Duplicates[cards[i].value - 1]++;
        }
        return Duplicates;
    }

    public int scoreHand(Card[] cards){
        Card[] tempcards = new Card[5];
        int index = 0;
        int topScore = 0;
        for(int i = 0; i < 7; i++){
            for(int j = i+1; j < 7; j++){
                for(int k = 0; k < 7; k++){
                    if(k != i && k != j){
                        tempcards[index] = cards[k];
                        index++;
                    }
                }
                index = 0;
                if(Score5Cards(tempcards) > topScore){
                    topScore = Score5Cards(tempcards);
                }
            }
        }
        return topScore;
    }

    public float getWinPercentRandom(Card[] cards, int players){
        //player count does not include bot
        Card[] tempHand = new Card[7];
        for(int i = 0; i < tempHand.length; i++) { 
            tempHand[i] = new Card(); 
            tempHand[i] = cards[i];
        }
        int[] scores = new int[players+1];
        float lossCount = 0;
        float depth = 1;
        scores[0] = scoreHand(cards);
        boolean validHand = true;
        for(int i = 0; i < depth; i++){ 
            for(int j = 0; j < players; j++){
                validHand = true;
                tempHand[0] = getRandomCard();
                tempHand[1] = getRandomCard();
                for(int l = 0; l < 7; l++){
                    for(int m = 0; m < 7; m++){
                        if(tempHand[l].equals(tempHand[m]) && l != m){
                            validHand = false;
                        }
                        
                    }
                }
                if(validHand){
                    scores[j+1] = scoreHand(tempHand);
                }
            }
            for(int k = 0; k<scores.length-1; k++){
                if(scores[k+1] > scores[0]){
                    lossCount++;
                    break;
                }
            }
        }

        //float percentage = lossCount/depth;
        //System.out.println(String.valueOf(percentage));
        return(1-(lossCount/depth));
        
    }

    public Card getRandomCard(){
        Card randomCard = new Card();
        randomCard.value = ThreadLocalRandom.current().nextInt(2, 14 + 1);
        randomCard.suit = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        return randomCard;
    }

    public Card convertString(String cardstring){
        Map<String, Integer> suitmap = new HashMap<String, Integer>();
        suitmap.put("hearts", 0);
        suitmap.put("diamonds", 1);
        suitmap.put("spades", 2);
        suitmap.put("clubs", 3);

        Map<String, Integer> valuemap = new HashMap<String, Integer>();
        valuemap.put("2", 2);
        valuemap.put("3", 3);
        valuemap.put("4", 4);
        valuemap.put("5", 5);
        valuemap.put("6", 6);
        valuemap.put("7", 7);
        valuemap.put("8", 8);
        valuemap.put("9", 9);
        valuemap.put("10", 10);
        valuemap.put("jack", 11);
        valuemap.put("queen", 12);
        valuemap.put("king", 13);
        valuemap.put("ace", 14);

        String[] splitcardstring = cardstring.split(" ");
        Card card = new Card();
        card.value = valuemap.get(splitcardstring[0]);
        card.suit = suitmap.get(splitcardstring[2]);
        //System.out.println(card.value);
        //System.out.println(card.suit);
        return card;

    }

    public void printHand(Card hand[]){
        for(int i = 0; i < hand.length; i++){
            System.out.println(hand[i].value);
            System.out.println(hand[i].suit);
        }
    }

    
    public float generateWinPercentDeck(List <Card> visibleCards, int depth, int opponents){
        //visible cards: first two cards are player cards. The next 5 are community cards. Any remaining slots are other player's cards
        int wins = 0;

        for (int j = 0; j < depth; j++) {
            Deck deck = new Deck();
            Card[] tempTable = new Card[7 + (opponents * 2)];
            
            deck.removeCard(visibleCards);
            for (int i = 0; i < visibleCards.size(); i++) {
                tempTable[i] = visibleCards.get(i);
            }

            for (int i = visibleCards.size(); i < tempTable.length; i++) {
                tempTable[i] = deck.draw();
            }

            int bestOpponentScore = 0;
            Card[] tempHand = new Card[7];

            // fill temp hand with community cards
            for (int i = 0; i < 5; i++) {
                tempHand[i] = tempTable[i+2];
            }
            
            // fill temp hand with opponent cards and score
            for (int i = 0; i < opponents; i++) {
                tempHand[5] = tempTable[2*i + 7];
                tempHand[6] = tempTable[2*i + 8];
                bestOpponentScore = Math.max(scoreHand(tempHand), bestOpponentScore);
            }

            tempHand[5] = tempTable[0];
            tempHand[6] = tempTable[1];

            if (scoreHand(tempHand) > bestOpponentScore) {
                wins++;
            }
        }
        
        return (float) wins / depth;
    }
    


    //public float EvBetValue(float finalpercentage, int pot)



}



