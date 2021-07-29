package enums;

public enum Suit {

    SPADES("S"),
    DIAMONDS("D"),
    CLUBS("C"),
    HEARTS("H");

    private final String abbr;

    Suit(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbreviatedName() {
        return abbr;
    }
}
