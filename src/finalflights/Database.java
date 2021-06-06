package finalflights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Database {

    private static final String dbURL = "jdbc:mysql://localhost:3306/finaldb";
    private static final String username = "root";
    private static final String password = "root2021";

    private Connection conn;

    public Database() throws SQLException {
        conn = DriverManager.getConnection(dbURL, username, password);

    }

    public ArrayList<Flight> getAllFlights() throws SQLException, InvalidDataException {

        ArrayList<Flight> list = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        PreparedStatement statement = conn.prepareStatement(sql);
        try (ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                int id = result.getInt("id");
                Date onDay = result.getDate("onDay");
                String fromCode = result.getString("fromCode");
                String toCode = result.getString("toCode");
                Flight.Type type = Flight.Type.valueOf(result.getString("type"));
                int passengers = result.getInt("passengers");
                list.add(new Flight(id, onDay, fromCode, toCode, type, passengers));       //ex
            }
        }
        return list;

    }

    Flight getFlightById(int id) throws SQLException, InvalidDataException {
        //Result set get the result of the SQL query
        PreparedStatement stmtSelect = conn.prepareStatement("SELECT * FROM flights WHERE id=?");
        stmtSelect.setInt(1, id);
        try (ResultSet result = stmtSelect.executeQuery()) {
            if (result.next()) {
//                int id = result.getInt("id");
                Date onDay = result.getDate("onDay");
                String fromCode = result.getString("fromCode");
                String toCode = result.getString("toCode");
                Flight.Type type = Flight.Type.valueOf(result.getString("type"));
                int passengers = result.getInt("passengers");
                
                return new Flight(id, onDay, fromCode,toCode,type,passengers);      // ex InvalidDataException
            } else {
                throw new SQLException("Recored not found");
            }
        }
    }

    public void addFlight(Flight flight) throws SQLException {
        String sql = "INSERT INTO flights Values(NULL, ?, ?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(flight.getOnDay().getTime()));
        statement.setString(2, flight.fromCode);
        statement.setString(3, flight.toCode);
        statement.setString(4, flight.type + "");
        statement.setInt(5, flight.passengers);
        statement.executeUpdate();  // for insert, update, and delete
        System.out.println("Record inserted.");
    }

    public void updateFlight(Flight flight) throws SQLException {
        String sql = "UPDATE flights SET onDay = ?, fromCode= ?, toCode=?, type= ?, passengers =?  WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(flight.getOnDay().getTime()));
        statement.setString(2, flight.fromCode);
        statement.setString(3, flight.toCode);
        statement.setString(4, flight.type + "");
        statement.setInt(5, flight.passengers);
        statement.setInt(6, flight.id);
        statement.executeUpdate();
        System.out.println("Record updated id=" + flight.id);
    }

    public void deleteFlight(int id) throws SQLException {
        String sql = "DELETE FROM flights WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        System.out.println("Record deleted id=" + id);
    }

}
