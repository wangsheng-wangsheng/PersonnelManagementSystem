import java.sql.SQLException;

public class SalaryService {
    private static SalaryDao salaryDao = SalaryDao.getInstance();
    private static SalaryService salaryService = new SalaryService();
    private SalaryService(){}

    public static SalaryService getInstance(){
        return salaryService;
    }

    public String find(Integer id)throws SQLException{
        return salaryDao.find(id);
    }

    public boolean update(Salary salary)throws SQLException{
        return salaryDao.update(salary);
    }
}
