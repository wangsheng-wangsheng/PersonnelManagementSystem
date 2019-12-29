package service;

import dao.StaffDao;
import domain.Staff;

import java.sql.SQLException;
import java.util.Collection;

public class StaffService {
    private static StaffDao staffDao = StaffDao.getInstance();
    private static StaffService staffService=new StaffService();

    public static StaffService getInstance() {
        return staffService;
    }

    public Collection<Staff> findAll() throws SQLException {
        return staffDao.findAll();
    }

    public Staff find(Integer id) throws SQLException {
        return staffDao.find(id);
    }

    public Staff findByStaffId(Integer staffId) throws SQLException {
        return staffDao.findByStaffId(staffId);
    }

    public boolean update(Staff staff) throws SQLException {
        return staffDao.update(staff);
    }

    public boolean add(Staff staff) throws SQLException {
        return staffDao.add(staff);
    }

    public boolean delete(Integer id) throws SQLException {
        return staffDao.getInstance().delete(id);
    }
    public Staff findByStaffNo(String no) throws SQLException {
        return staffDao.findByStaffNo(no);
    }
}
