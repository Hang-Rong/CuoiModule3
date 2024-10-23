package service;

import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

public List<Room> getAll() {
    List<Room> rooms = new ArrayList<>();
    String sql = "select * from room_lists;";
    try {
        PreparedStatement preparedStatement = DBConnect.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Room room = new Room(
                    resultSet.getInt("room_id"),
                    resultSet.getString("renter_name"),
                    resultSet.getString("renter_contact"),
                    resultSet.getDate("move_in_date"),
                    resultSet.getInt("pay_cycle"),
                    resultSet.getString("note")
            );
            rooms.add(room);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return rooms;
}

public boolean add(Room room) {
    String sql = "insert into room_lists (room_id, renter_name, renter_contact, move_in_date, pay_cycle, note) VALUES ( ?, ?, ?, ?, ?, ?);";
    try {
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, room.getRoomId());
        statement.setString(2, room.getRenterName());
        statement.setString(3, room.getRenterContact());
        statement.setDate(4, room.getMoveInDate());
        statement.setInt(5, room.getPayCycle());
        statement.setString(6, room.getNote());
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean delete(int id) {
    String sql = "delete from room_lists where room_id = ?;";
    try {
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean edit(Room room, int id) {
    String sql = "update room_lists set  renter_name = ?, renter_contact = ?, move_in_date = ?, pay_cycle = ?, note = ? where room_id = ?;";
    try {
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, room.getRenterName());
        statement.setString(2, room.getRenterContact());
        statement.setDate(3, room.getMoveInDate());
        statement.setInt(4, room.getPayCycle());
        statement.setString(5, room.getNote());
        statement.setInt(6, id);
        statement.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public Room findById(int id) {
    String sql = "select * from room_lists where room_id = ?;";
    try {
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Room(
                    resultSet.getInt("room_id"),
                    resultSet.getString("renter_name"),
                    resultSet.getString("renter_contact"),
                    resultSet.getDate("move_in_date"),
                    resultSet.getInt("pay_cycle"),
                    resultSet.getString("note")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public List<Room> findByString(String name, String contact) {
    List<Room> rooms = new ArrayList<>();
    String sql = "select * from room_lists where renter_name like lower (?) or renter_contact like lower (?);";
    try {
        PreparedStatement statement = DBConnect.getConnection().prepareStatement(sql);
        statement.setString(1, "%" + name + "%");
        statement.setString(2, "%" + contact + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Room room = new Room(
                    resultSet.getInt("room_id"),
                    resultSet.getString("renter_name"),
                    resultSet.getString("renter_contact"),
                    resultSet.getDate("move_in_date"),
                    resultSet.getInt("pay_cycle"),
                    resultSet.getString("note")
            );
            rooms.add(room);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rooms;
}
}