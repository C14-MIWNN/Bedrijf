package model;

/**
 * @author Vincent Velthuizen
 * Een persoon die vrijwillig diens tijd aan ons bedrijf spendeert
 */
public class Vrijwilliger extends Persoon implements Oproepbaar {
    private int urenGewerkt;

    public Vrijwilliger(String naam, String woonplaats, Afdeling afdeling) {
        super(naam, woonplaats, afdeling);
        urenGewerkt = 0;
    }

    @Override
    public double berekenJaarinkomen() {
        return 0;
    }

    @Override
    public void huurIn(int uren) {
        urenGewerkt += uren;
    }

    @Override
    public String toString() {
        return String.format("%s en is een vrijwilliger", super.toString());
    }
}
