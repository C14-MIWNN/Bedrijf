package model;

/**
 * @author Vincent Velthuizen
 * Een persoon die werkzaam is binnen ons bedrijf
 */
public class Persoon {
    private static final String DEFAULT_NAAM = "Onbekend";
    private static final String DEFAULT_WOONPLAATS = "Onbekend";
    private static final double DEFAULT_MAAND_SALARIS = 0.0;

    private static final double GRENSWAARDE_BONUS = 4500.0;

    private static final int MAANDEN_PER_JAAR = 12;

    private static int aantalPersonen = 0;

    private int personeelsNummer;
    private String naam;
    private String woonplaats;
    private double maandSalaris;

    public Persoon(String naam, String woonplaats, double maandSalaris) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        setMaandSalaris(maandSalaris);

        this.personeelsNummer = ++aantalPersonen;
    }

    public Persoon(String naam) {
        this(naam, DEFAULT_WOONPLAATS, DEFAULT_MAAND_SALARIS);
    }

    public Persoon() {
        this(DEFAULT_NAAM);
    }

    public double berekenJaarinkomen() {
        return MAANDEN_PER_JAAR * maandSalaris;
    }

    public boolean heeftRechtOpBonus() {
        return maandSalaris >= GRENSWAARDE_BONUS;
    }

    public static int getAantalPersonen() {
        return aantalPersonen;
    }

    public int getPersoneelsNummer() {
        return personeelsNummer;
    }

    public String getNaam() {
        return naam;
    }

    public double getMaandSalaris() {
        return maandSalaris;
    }

    public void setMaandSalaris(double maandSalaris) {
        if (maandSalaris < 0) {
            System.err.printf("Een maandsalaris van %d is niet geldig, het maandsalaris wordt op %d gezet.\n",
                    maandSalaris, DEFAULT_MAAND_SALARIS);
            maandSalaris = DEFAULT_MAAND_SALARIS;
        }
        this.maandSalaris = maandSalaris;
    }
}
