package model;

/**
 * @author Vincent Velthuizen
 * Een persoon die werkzaam is binnen ons bedrijf
 */
public class Persoon {
    protected static final String DEFAULT_NAAM = "Onbekend";
    protected static final String DEFAULT_WOONPLAATS = "Onbekend";

    protected static int aantalPersonen = 0;

    protected int personeelsNummer;
    protected String naam;
    protected String woonplaats;
    protected Afdeling afdeling;

    public Persoon(String naam, String woonplaats, Afdeling afdeling) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        this.afdeling = afdeling;

        this.personeelsNummer = ++aantalPersonen;
    }

    public Persoon(String naam) {
        this(naam, DEFAULT_WOONPLAATS, new Afdeling());
    }

    public Persoon() {
        this(DEFAULT_NAAM);
    }

    public double berekenJaarinkomen() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s woont in %s en werkt op %s", this.naam, this.woonplaats, this.afdeling);
    }

    public static int getAantalPersonen() {
        return aantalPersonen;
    }

    public String getNaam() {
        return naam;
    }
}
