package controller;

import model.Persoon;

/**
 * @author Vincent Velthuizen
 * Werk met de personeelsadministratie van mijn Bedrijf
 */
public class BedrijfLauncher {

    public static void main(String[] args) {
        System.out.println(Persoon.getAantalPersonen());
        Persoon baas = new Persoon("Mark", "Den Haag", 10000);
        System.out.println(Persoon.getAantalPersonen());
        System.out.println(baas.getPersoneelsNummer());
        Persoon medewerker = new Persoon("Caroline", "Delft", 4000);
        System.out.println(Persoon.getAantalPersonen());
        System.out.println(medewerker.getPersoneelsNummer());

        Persoon assistent = new Persoon("Klaas");
        Persoon manager = new Persoon();
        System.out.println(Persoon.getAantalPersonen());

        String bonus = "geen";
        if (baas.heeftRechtOpBonus()) {
            bonus = "wel";
        }
        System.out.printf("%s verdient %.2f en heeft %s recht op een bonus\n",
                baas.getNaam(), baas.getMaandSalaris(), bonus);

        bonus = "geen";
        if (medewerker.heeftRechtOpBonus()) {
            bonus = "wel";
        }
        System.out.printf("%s verdient %.2f en heeft %s recht op een bonus\n",
                medewerker.getNaam(), medewerker.getMaandSalaris(), bonus);
    }
}
