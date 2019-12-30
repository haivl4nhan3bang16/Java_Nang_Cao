package View;

import controller.NewStaffController;
import controller.NewStaffController_Impl;
import controller.StaffController_Impl;
import controller.StaffsController;
import model.Staff;
import model.StaffsModel;
import model.TableObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class StaffListView extends JFrame implements TableObserver {
    private JPanel panel1;
    private JPanel rootPanel;
    private JTable studentTable;

    private StaffTableModel staffTableModel;

    private JButton addButton;

    private StaffsModel staffsModel;

    private List<TableObserver> tableObserver = new Vector<>();

    private List<Staff> staffs = new Vector<>();

    private JButton deleteButton;
    private JButton updateButton;

    String[] Gender = {"Nam", "Nữ"};

    public StaffListView(StaffsModel model){
        this.staffsModel = model;
        this.staffsModel.registerObserver(this);

        setTitle("Staff Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(800,600));
        setLocation(500,200);
        pack();
        setVisible(true);

        staffTableModel = new StaffTableModel();
        studentTable.setModel(staffTableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddStaff(e);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteStaff(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdateStaff(e);
            }
        });

        List<Staff> staff = this.staffsModel.getAllStaff();
        staffTableModel.updateStudents(staff);

    }

    private void onAddStaff(ActionEvent e){
        NewStaffController controller = new NewStaffController_Impl(this,staffsModel,new InsertStaff());
        controller.newStaff();
    }

    private void onDeleteStaff(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StaffsController staffController = new StaffController_Impl(this, staffsModel, new InsertStaff());
            staffController.deleteStaff((Integer) staffTableModel.getValueAt(studentTable.getSelectedRow(), 0));
            System.out.println(studentTable.getSelectedRow());
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    private void onUpdateStaff(ActionEvent e){
        if (studentTable.getSelectedRow() != -1) {
            StaffsController staffController = new StaffController_Impl(this, staffsModel, new InsertStaff());
            staffController.updateStaff((Integer) staffTableModel.getValueAt(studentTable.getSelectedRow(), 0));
        }else {
            JOptionPane.showConfirmDialog(null, "Chưa chọn bất kỳ nhân viên nào", "Xóa nhân viên", JOptionPane.OK_OPTION);
        }
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }


    @Override
    public void updateTable(List<Staff> students) {
        staffTableModel.updateStudents(students);
    }




}
