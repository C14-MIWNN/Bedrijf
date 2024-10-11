package database;

import model.Werknemer;

import java.sql.SQLException;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class WerknemerDAO extends PersoonDAO {
    public WerknemerDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaWerknemerOp(Werknemer werknemer) {
        try {
            int personeelsnummer = slaPersoonOp(werknemer);

            String sql = "INSERT INTO `Werknemer` (personeelsnummer, maandsalaris) VALUES (?, ?);";

            setupPreparedStatement(sql);
            getPreparedStatement().setInt(1, personeelsnummer);
            getPreparedStatement().setDouble(2, werknemer.getMaandSalaris());
            executeManipulateStatement();
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Het is niet gelukt om deze persoon, en dus de werknemer in de database te zetten.");
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }
    }
}
