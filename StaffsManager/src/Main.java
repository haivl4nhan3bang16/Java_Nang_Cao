import View.StaffListView;
import model.StaffModel_lmpl;
import model.StaffsModel;

public class Main{

    public static void main(String[] args) {
        StaffsModel model = new StaffModel_lmpl();
        StaffListView studentListView = new StaffListView(model);

    }
}
