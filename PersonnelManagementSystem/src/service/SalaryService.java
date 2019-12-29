package service;
import dao.SalaryDao;
import domain.Salary;

import java.sql.SQLException;
import java.util.Collection;

public class SalaryService {
    private static SalaryDao salaryDao = SalaryDao.getInstance();
    private static SalaryService salaryService = new SalaryService();
    private SalaryService(){}

    public static SalaryService getInstance(){
        return salaryService;
    }

    public Salary find(Integer id)throws SQLException{
        return salaryDao.find(id);
    }

    public boolean update(Salary salary)throws SQLException{
        return salaryDao.update(salary);
    }
    public boolean add(Salary salary) throws SQLException {
        return salaryDao.add(salary);
    }
    public Collection<Salary> findByStaffNo(String no) throws SQLException{
        return salaryDao.findByStaffNo(no);
    }
}
