package model;

import java.util.List;

public interface StaffDAO {
    //Create
    void insertStaff(Staff staff);

    //Get by ID
    Staff getStudentById(int id);

    //Get All
    List<Staff> getAllStaff();

    void update(Staff staff, int id);

    void delete(int id);


}
