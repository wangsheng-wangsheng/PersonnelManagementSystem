package service;
import domain.Staff;
import java.sql.SQLException;
public class StaffService {
    private static StaffService staffService=new StaffService();
    public static StaffService getInstance(){
        return staffService;
    }
    public Staff find(Integer id)throws SQLException {
        return staffService.find(id);
    }
}
