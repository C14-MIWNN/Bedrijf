package model;

/**
 * @author Vincent Velthuizen
 * Een persoon in loondienst bij ons bedrijf
 */
public class Werknemer extends Persoon {
    private static final double GRENSWAARDE_BONUS = 4500.0;
    private static final double DEFAULT_MAAND_SALARIS = 0.0;
    private static final int AANTAL_MAANDEN_PER_JAAR = 12;

    private double maandSalaris;

    public Werknemer(String naam, String woonplaats, Afdeling afdeling, double maandSalaris) {
        super(naam, woonplaats, afdeling);
        setMaandSalaris(maandSalaris);
    }

    public Werknemer(String naam) {
        this(naam, DEFAULT_WOONPLAATS, new Afdeling(), DEFAULT_MAAND_SALARIS);
    }

    public Werknemer() {
        this(DEFAULT_NAAM);
    }

    public boolean heeftRechtOpBonus() {
        return maandSalaris >= GRENSWAARDE_BONUS;
    }

    @Override
    public double berekenJaarinkomen() {
        double jaarinkomen = AANTAL_MAANDEN_PER_JAAR * maandSalaris;

        if (heeftRechtOpBonus()) {
            jaarinkomen += maandSalaris;
        }

        return jaarinkomen;
    }

    @Override
    public String toString() {String bonus = "zonder";
        if (heeftRechtOpBonus()) {
            bonus = "met";
        }

        return String.format("%s en is een werknemer %s recht op een bonus", super.toString(), bonus);
    }

    public void setMaandSalaris(double maandSalaris) {
        if (maandSalaris < 0) {
            throw new IllegalArgumentException("Het maandsalaris mag niet negatief zijn.");
        }
        this.maandSalaris = maandSalaris;
    }

    public double getMaandSalaris() {
        return maandSalaris;
    }
}
