package controller;

import View.InsertStaff;
import model.Staff;
import model.StaffsModel;

import javax.swing.*;
import java.awt.*;

public class NewStaffController_Impl implements NewStaffController {
    private Component parent;

    private StaffsModel staffsModel;
    private InsertStaff view;
    public NewStaffController_Impl(Component parent, StaffsModel staffsModel, InsertStaff view){
        this.parent = parent;
        this.view = view;
        this.staffsModel = staffsModel;
    }
    @Override
    public void newStaff() {
        int option = JOptionPane.showConfirmDialog(parent, view.getRootPanel() , "Thêm nhân viên", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option == JOptionPane.YES_OPTION){
            if(view.getFullName() == null){
                JOptionPane.showConfirmDialog(null, "Chưa nhập tên nhân viên", "Lỗi !!!", JOptionPane.OK_OPTION);
            }else {
                String fullName = view.getFullName();
                String gender = view.getGender();
                Staff staff = new Staff();
                staff.setFullName(fullName);
                staff.setGender(gender);
                staffsModel.addStaff(staff);
            }
        }
    }
}
