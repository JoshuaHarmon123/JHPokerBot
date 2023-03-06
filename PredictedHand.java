import java.util.ArrayList;
import java.util.List;

public class PredictedHand {
    List<Card> PlayerCards = new ArrayList<Card>();
    Card[] CommunityCards = new Card[5];
    Boolean win = false;

    public PredictedHand(List<Card> playerCards, Card[] communityCards){
        this.CommunityCards = communityCards;
        this.PlayerCards = playerCards;
    }


}