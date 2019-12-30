package controller;

import View.InsertStaff;
import model.Staff;
import model.StaffsModel;

import javax.swing.*;
import java.awt.*;

public class StaffController_Impl implements StaffsController {
    private Component parent;
    private StaffsModel staffsModel;
    private InsertStaff view;

    public StaffController_Impl(Component parent, StaffsModel model, InsertStaff view) {
        this.parent = parent;
        this.staffsModel = model;
        this.view = view;
    }

    @Override
    public void deleteStaff(int id) {
        int option = JOptionPane.showConfirmDialog(parent, "Bạn có muốn xóa nhân viên này ???", "Xóa nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            staffsModel.deleteStaff(id);
        }
    }

    @Override
    public void updateStaff(int id) {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel(), "Chỉnh sử nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "Chưa nhập tên nhân viên", "Lỗi !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String birthYear = view.getGender();
                Staff staff = new Staff();
                staff.setFullName(fullName);
                staff.setGender(birthYear);
                staffsModel.updateStaff(staff, id);
            }
        }
    }
}
