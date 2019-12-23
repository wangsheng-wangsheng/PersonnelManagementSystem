import domain.Salary;
import service.StaffService;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryDao {
    private static SalaryDao salaryDao=new SalaryDao();
    private SalaryDao(){}
    /**
     * 定义一个AttendanceDao类型的getInstance()方法
     * @return attendanceDao
     */
    public static SalaryDao getInstance(){
        return salaryDao;
    }

    public String find(Integer staffId)throws SQLException{
        String salary = null;
        int baseSalary = 0;
        int overtimePay = 0;
        int carAllowance = 0;
        int houseAllowance = 0;
        int pension = 0;
        int getMedicalInsurance = 0;
        String time = null;
        int staff_id = staffId;
        String name = StaffService.getInstance().find(staffId).getName();
        String position = StaffService.getInstance().find(staffId).getPosition().getName();
        salary = "{staff_id:"+ staff_id +",name:"+ name + ",position:" + position + ",baseSalary:" + baseSalary + ",overtime:" + overtimePay
                + ",carAllowance:" + carAllowance + ",houseAllowance:" + houseAllowance + ",pension:" + pension + ",getMedicalInsurance:" + getMedicalInsurance
                + ",time:" + time + "}";
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM salary WHERE salary_id=?");
        //为预编译参数赋值
        preparedStatement.setInt(1,staffId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            baseSalary = resultSet.getInt("baseSalary");
            overtimePay = resultSet.getInt("overtimePay");
            carAllowance = resultSet.getInt("carAllowance");
            houseAllowance = resultSet.getInt("houseAllowance");
            pension = resultSet.getInt("pension");
            getMedicalInsurance = resultSet.getInt("getMedicalInsurance");
            time = resultSet.getString("time");
            staff_id = resultSet.getInt("staff_id");
            name = StaffService.getInstance().find(resultSet.getInt("staff_id")).getName();
            position = StaffService.getInstance().find(resultSet.getInt("staff_id")).getPosition().getName();
            salary = "{staff_id:"+ staff_id +",name:"+ name + ",position:" + position + ",baseSalary:" + baseSalary + ",overtime:" + overtimePay
                    + ",carAllowance:" + carAllowance + ",houseAllowance:" + houseAllowance + ",pension:" + pension + ",getMedicalInsurance:" + getMedicalInsurance
                    + ",time:" + time + "}";
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return salary;
    }
    public boolean update(Salary salary)throws SQLException {
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE salary SET baseSalary=?, overtimePay=?, carAllowance=?, " +
                "houseAllowance=?, pension=?, getMedicalInsurance=?, time=?, salary_id=? WHERE id=?");
        preparedStatement.setInt(1, salary.getBaseSalary());
        preparedStatement.setInt(2, salary.getOvertimePay());
        preparedStatement.setInt(3, salary.getCarAllowance());
        preparedStatement.setInt(4, salary.getHouseAllowance());
        preparedStatement.setInt(5, salary.getPension());
        preparedStatement.setInt(6, salary.getMedicalInsurance());
        preparedStatement.setString(7, salary.getTime());
        preparedStatement.setInt(8, salary.getStaff().getId());
        preparedStatement.setInt(9, salary.getId());
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新" + affectedRows + "条记录");
        preparedStatement.close();
        connection.close();
        return affectedRows > 0;
    }
}
