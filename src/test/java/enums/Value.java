package enums;

public enum Value {
    ACE("ACE","A"),
    TWO("2","2"),
    THREE("3","3"),
    FOUR("4","4"),
    FIVE("5","5"),
    SIX("6","6"),
    SEVEN("7","7"),
    EIGHT("8","8"),
    NINE("9","9"),
    TEN("10","0"),
    JACK("JACK","J"),
    QUEEN("QUEEN","Q"),
    KING("KING","K");

    private final String valueString;
    private final String abbr;

    Value(String valueString, String abbr) {
        this.valueString = valueString;
        this.abbr = abbr;
    }

    public String getAbbreviatedName() {
        return abbr;
    }

    public String getValueString(){
        return valueString;
    }
}

