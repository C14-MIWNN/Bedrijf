package database;

import model.Persoon;

import java.sql.SQLException;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class PersoonDAO extends AbstractDAO {
    public PersoonDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    protected int slaPersoonOp(Persoon persoon) {
        AfdelingDAO afdelingDAO = new AfdelingDAO(getdBaccess());
        if (afdelingDAO.geefAfdeling(persoon.getAfdeling().getAfdelingsNaam()) == null) {
            afdelingDAO.slaAfdelingOp(persoon.getAfdeling());
        }

        String sql = "INSERT INTO `Persoon` (`naam`, `woonplaats`, `afdeling`) VALUES (?, ?, ?);";

        try {
            setupPreparedStatementWithKey(sql);
            getPreparedStatement().setString(1, persoon.getNaam());
            getPreparedStatement().setString(2, persoon.getWoonplaats());
            getPreparedStatement().setString(3, persoon.getAfdeling().getAfdelingsNaam());
            return executeInsertStatementWithKey();
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }

        throw new IllegalStateException("Het is niet gelukt deze persoon in de database te zetten.");
    }
}
