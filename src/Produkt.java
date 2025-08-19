public class Produkt {
    private String name;
    private int menge;

    public Produkt(String name, int menge) {
        this.name = name;
        this.menge = menge;
    }

    public String getName() { return name; }
    public int getMenge() { return menge; }

    public void setMenge(int menge) { this.menge = menge; }

    @Override
    public String toString() {
        return name + " | Menge: " + menge;
    }
}
