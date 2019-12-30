package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class StaffDAO_Impl implements StaffDAO {

    public StaffDAO_Impl(){
        Database db = new Database();
        String SQL_CREATE_STAFF_TABLE = "CREATE TABLE IF NOT EXISTS StaffTable (\n"
                + "    ID integer PRIMARY KEY,\n"
                + "    FullName text NOT NULL,\n"
                + "    Gender text\n"
                + ");";
        try {
            Statement statement = db.getConnection().createStatement();
            statement.execute(SQL_CREATE_STAFF_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();

    }
    @Override
    public void insertStaff(Staff staff) {
        Database db = new Database();
        final String SQL_CREATE_STAFF = "INSERT INTO StaffTable(FullName, Gender)" +
                "VALUES(?,?)";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_CREATE_STAFF, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, staff.getFullName());
            ps.setString(2, staff.getGender());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                staff.setId(id);
                System.out.println("Inserted id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public Staff getStudentById(int id) {
        Staff staff = null;
        Database db = new Database();
        final String SQL_SELECT_STAFF_BY_ID = "SELECT * FROM StaffTable WHERE ID=?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT_STAFF_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int staff_id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                staff = new Staff(staff_id, fullName, gender);
                System.out.println("ID: " + staff_id + ", Full Name: " + fullName + ", Gender : " + gender);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> staffs = new Vector<>();

        Database db = new Database();
        final String SQL_SELECT_ALL_STAFF = "SELECT * FROM StaffTable";
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_STAFF);
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullName = rs.getString(2);
                String gender = rs.getString(3);
                Staff student = new Staff(id, fullName, gender);
                staffs.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

    @Override
    public void update(Staff staff, int id) {
        Database db = new Database();
        final String SQL_UPDATE_STAFF_BY_ID = "UPDATE StaffTable SET FullName = ?, Gender = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_UPDATE_STAFF_BY_ID);
            ps.setString(1, staff.getFullName());
            ps.setString(2, staff.getGender());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

    @Override
    public void delete(int id) {
        Database db = new Database();
        final String SQL_DELETE_STAFF_BY_ID = "DELETE FROM StaffTable WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_DELETE_STAFF_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }
}
