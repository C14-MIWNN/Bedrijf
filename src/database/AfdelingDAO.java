package database;

import model.Afdeling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class AfdelingDAO extends AbstractDAO {

    public AfdelingDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaAfdelingOp(Afdeling afdeling) {
        String sql = "INSERT INTO `Afdeling` (afdelingsnaam, afdelingsplaats) VALUES (?, ?);";

        try {
            setupPreparedStatement(sql);
            getPreparedStatement().setString(1, afdeling.getAfdelingsNaam());
            getPreparedStatement().setString(2, afdeling.getAfdelingsPlaats());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }
    }

    public Afdeling geefAfdeling(String afdelingsnaam) {
        Afdeling afdeling = null;

        String sql = "SELECT afdelingsplaats FROM `Afdeling` WHERE afdelingsnaam = ?;";

        try {
            setupPreparedStatement(sql);
            getPreparedStatement().setString(1, afdelingsnaam);
            ResultSet resultSet = executeSelectStatement();

            if (resultSet.next()) {
                String afdelingsplaats = resultSet.getString("afdelingsplaats");

                afdeling = new Afdeling(afdelingsnaam, afdelingsplaats);
            }
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }

        return afdeling;
    }

    public ArrayList<Afdeling> geefAfdelingen() {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();

        String sql = "SELECT afdelingsnaam, afdelingsplaats FROM `Afdeling`;";

        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()) {
                String afdelingsnaam = resultSet.getString("afdelingsnaam");
                String afdelingsplaats = resultSet.getString("afdelingsplaats");

                afdelingen.add(new Afdeling(afdelingsnaam, afdelingsplaats));
            }
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }

        return afdelingen;
    }
    public ArrayList<Afdeling> geefAfdelingenMetPlaats(String afdelingsplaats) {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();

        String sql = "SELECT afdelingsnaam FROM `Afdeling` WHERE afdelingsplaats = ?;";

        try {
            setupPreparedStatement(sql);
            getPreparedStatement().setString(1, afdelingsplaats);
            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()) {
                String afdelingsnaam = resultSet.getString("afdelingsnaam");

                afdelingen.add(new Afdeling(afdelingsnaam, afdelingsplaats));
            }
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }

        return afdelingen;
    }

}
