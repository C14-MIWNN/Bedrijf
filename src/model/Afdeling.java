package model;

import java.util.Objects;

/**
 * @author Vincent Velthuizen
 * Een afdeling binnen mijn bedrijf waar personen werken
 */
public class Afdeling {
    private static final String DEFAULT_AFDELINGS_NAAM = "Onbekend";
    private static final String DEFAULT_AFDELINGS_PLAATS = "Onbekend";

    private final String afdelingsNaam;
    private final String afdelingsPlaats;

    public Afdeling(String afdelingsNaam, String afdelingsPlaats) {
        this.afdelingsNaam = afdelingsNaam;
        this.afdelingsPlaats = afdelingsPlaats;
    }

    public Afdeling() {
        this(DEFAULT_AFDELINGS_NAAM, DEFAULT_AFDELINGS_PLAATS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Afdeling afdeling = (Afdeling) o;
        return Objects.equals(afdelingsNaam, afdeling.afdelingsNaam) && Objects.equals(afdelingsPlaats, afdeling.afdelingsPlaats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(afdelingsNaam, afdelingsPlaats);
    }

    @Override
    public String toString() {
        return String.format("afdeling %s te %s", afdelingsNaam, afdelingsPlaats);
    }

    public String getAfdelingsNaam() {
        return afdelingsNaam;
    }
}
