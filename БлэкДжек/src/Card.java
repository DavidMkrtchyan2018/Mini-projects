public class Card {
    private int num;
    private String value;
    private Suit suit;

    public Card(String aValue, Suit aSuit) {
        value = aValue;
        suit = aSuit;

        if (value.equals("10") || value.equals("J") || value.equals("Q") || value.equals("K")) {
            num = 10;
        } else {
            switch (value) {
                case "2":
                    num = 2;
                    break;
                case "3":
                    num = 3;
                    break;
                case "4":
                    num = 4;
                    break;
                case "5":
                    num = 5;
                    break;
                case "6":
                    num = 6;
                    break;
                case "7":
                    num = 7;
                    break;
                case "8":
                    num = 8;
                    break;
                case "9":
                    num = 9;
                    break;
                case "A":
                    num = 11;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return value + " " + suit;
    }

    public int getNum() {
        return num;
    }
}
