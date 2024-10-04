package model;

/**
 * @author Vincent Velthuizen
 * Een persoon die extern en incidenteel wordt ingehuurd door mijn bedrijf
 */
public class Zzper extends Persoon {
    private final double uurtarief;
    private int urenGewerkt;

    public Zzper(String naam, String woonplaats, Afdeling afdeling, double uurtarief) {
        super(naam, woonplaats, afdeling);
        this.uurtarief = uurtarief;
        this.urenGewerkt = 0;
    }

    public void huurIn(int uren) {
        urenGewerkt += uren;
    }

    @Override
    public double berekenJaarinkomen() {
        return uurtarief * urenGewerkt;
    }

    @Override
    public String toString() {
        return String.format("%s en is een zzp-er met een uurtarief van %.2f", super.toString(), this.uurtarief);
    }
}
