package service;
import dao.AttendanceDao;
import domain.Attendance;
import domain.Staff;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
public final class AttendanceService {
    private static AttendanceDao attendanceDao= AttendanceDao.getInstance();
    private static AttendanceService attendanceService=new AttendanceService();
    private AttendanceService(){}

    public static AttendanceService getInstance(){
        return attendanceService;
    }

    public Collection<Attendance> getAll()throws SQLException {
        return attendanceDao.findAll();
    }

    public Collection<Attendance> getAll(Staff staff) throws SQLException{
        Collection<Attendance> attendances = new HashSet<Attendance>();
        for(Attendance attendance: attendanceDao.findAll()){
            if(attendance.getStaff()==staff){
                attendances.add(attendance);
            }
        }
        return attendances;
    }

    public Collection<Attendance> findAll() throws SQLException{
        return attendanceDao.findAll();
    }
    public Attendance find(Integer id)throws SQLException{
        return attendanceDao.find(id);
    }

    public boolean update(Attendance attendance)throws SQLException{
        return attendanceDao.update(attendance);
    }

    public boolean add(Attendance attendance) throws SQLException{
        return attendanceDao.add(attendance);
    }

    public boolean delete(Integer id) throws SQLException{
        Attendance attendance = this.find(id);
        return attendanceDao.delete(attendance);
    }
    public boolean delete(Attendance attendance)throws SQLException{
        return attendanceDao.delete(attendance);
    }
    public Collection<Attendance> findAllByStaff(Integer staff_id) throws SQLException {
        return attendanceDao.findAllByStaff(staff_id);
    }
}

