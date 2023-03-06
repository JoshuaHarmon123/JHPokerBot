public class Card {

    public Card() {
    }

    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;
    }

    int value = 0;
    int suit = 0;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card))
            return false;

        Card card = (Card) o;
        if (this.suit == card.suit && this.value == card.value) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return value + "," + suit;
    }

    public void printCard() {
        System.out.println(this.value);
        System.out.println(this.suit);
    }
}
