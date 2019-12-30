package model;

import java.util.List;
import java.util.Vector;

public class StaffModel_lmpl implements StaffsModel {

    private List<TableObserver> tableObserver = new Vector<>();

    @Override
    public List<Staff> getAllStaff() {
        StaffDAO dao = new StaffDAO_Impl();

        return dao.getAllStaff();
    }

    @Override
    public void addStaff(Staff staff) {
        StaffDAO dao = new StaffDAO_Impl();
        dao.insertStaff(staff);
        notifyObserver();
    }

    @Override
    public void deleteStaff(int id) {
        StaffDAO dao = new StaffDAO_Impl();
        dao.delete(id);
        notifyObserver();
    }

    @Override
    public void updateStaff(Staff staff, int id) {
        StaffDAO dao = new StaffDAO_Impl();
        dao.update(staff,id);
        notifyObserver();
    }

    @Override
    public void registerObserver(TableObserver observer) {
        if(!tableObserver.contains(observer)){
            tableObserver.add(observer);
        }
    }

    @Override
    public void un_registerObserver(TableObserver observer) {
        tableObserver.remove(observer);
    }

    private void notifyObserver(){
        List<Staff> students = getAllStaff();
        for (TableObserver observer: tableObserver){
            observer.updateTable(students);
        }
    }
}
