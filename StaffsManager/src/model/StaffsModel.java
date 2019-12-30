package model;

import java.util.List;

public interface StaffsModel {
    List<Staff> getAllStaff();

    void addStaff(Staff staff);

    void deleteStaff(int id);

    void updateStaff(Staff staff, int id);

    void registerObserver(TableObserver observer);

    void un_registerObserver(TableObserver observer);
}
