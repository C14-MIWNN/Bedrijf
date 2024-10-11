package controller;

import database.AfdelingDAO;
import database.DBaccess;
import model.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Vincent Velthuizen
 * Werk met de personeelsadministratie van mijn Bedrijf
 */
public class BedrijfLauncher {

    public static void main(String[] args) {
        DBaccess dBaccess = new DBaccess("Bedrijf", "userBedrijf", "userBedrijfPW");
        AfdelingDAO afdelingDAO = new AfdelingDAO(dBaccess);

        dBaccess.openConnection();
//        afdelingDAO.slaAfdelingOp(new Afdeling("HR", "Hilversum"));

        ArrayList<Afdeling> afdelingen = afdelingDAO.geefAfdelingenMetPlaats("Hilversum");
        for (Afdeling afdeling : afdelingen) {
            System.out.println(afdeling);
        }
        dBaccess.closeConnection();
    }

    private static void printPersonenPerAfdeling(ArrayList<Afdeling> afdelingen, ArrayList<Persoon> personen) {
        try (PrintWriter personenPerAfdelingWriter = new PrintWriter("resources/PersonenPerAfdeling.txt")) {
            for (Afdeling afdeling : afdelingen) {
                personenPerAfdelingWriter.printf("Afdeling: %s\n", afdeling.getAfdelingsNaam());

                for (Persoon persoon : personen) {
                    if (persoon.getAfdeling().equals(afdeling)) {
                        personenPerAfdelingWriter.println("-- " + persoon);
                    }
                }

                personenPerAfdelingWriter.println();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Het is niet gelukt het Personen per Afdeling bestand te openen.");
        }
    }

    private static ArrayList<Persoon> leesPersonenUitBestand(String bestandsnaam, ArrayList<Afdeling> afdelingen) {
        ArrayList<Persoon> personen = new ArrayList<>();

        try (Scanner personenScanner = new Scanner(new File(bestandsnaam))) {
            while (personenScanner.hasNextLine()) {
                String[] persoonEigenschappen = personenScanner.nextLine().split(",");

                String type = persoonEigenschappen[0];
                String naam = persoonEigenschappen[1];
                String woonplaats = persoonEigenschappen[2];

                int afdelingsIndex = Integer.parseInt(persoonEigenschappen[3]);
                Afdeling afdeling = afdelingen.get(afdelingsIndex);

                double ietsMetGeld = Double.parseDouble(persoonEigenschappen[4]);

                switch (type) {
                    case "Werknemer":
                        personen.add(new Werknemer(naam, woonplaats, afdeling, ietsMetGeld));
                        break;
                    case "Zzper":
                        personen.add(new Zzper(naam, woonplaats, afdeling, ietsMetGeld));
                        break;
                    case "Vrijwilliger":
                        personen.add(new Vrijwilliger(naam, woonplaats, afdeling));
                        break;
                    default:
                        System.err.printf("Onbekend type: %s, geen persoon toegevoegd.", type);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Het is niet gelukt het personenbestand te openen");
        }

        return personen;
    }

    public static ArrayList<Afdeling> leesAfdelingenUitBestand(String bestandsnaam) {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();

        try (Scanner afdelingenScanner = new Scanner(new File(bestandsnaam))) {
            while (afdelingenScanner.hasNextLine()) {
                String afdelingsnaam = afdelingenScanner.nextLine();
                String afdelingsplaats = afdelingenScanner.nextLine();

                afdelingen.add(new Afdeling(afdelingsnaam, afdelingsplaats));
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Het is niet gelukt het afdelingenbestand te openen");
        }

        return afdelingen;
    }

}
