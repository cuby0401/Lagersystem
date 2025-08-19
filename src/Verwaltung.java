public enum Verwaltung {
    EINLAGERN,
    AUSLAGERN,
    BESTAND_ANZEIGE,
    NEUES_PRODUKT,
    PRODUKT_ENTFERNEN,
    BEENDEN,
    UNGUELTIG;

    public static Verwaltung parseAktion(String input) {
        return switch (input) {
            case "1" -> Verwaltung.EINLAGERN;
            case "2" -> Verwaltung.AUSLAGERN;
            case "3" -> Verwaltung.BESTAND_ANZEIGE;
            case "4" -> Verwaltung.NEUES_PRODUKT;
            case "5" -> Verwaltung.PRODUKT_ENTFERNEN;
            case "6" -> Verwaltung.BEENDEN;
            default  -> Verwaltung.UNGUELTIG;
        };
    }
}