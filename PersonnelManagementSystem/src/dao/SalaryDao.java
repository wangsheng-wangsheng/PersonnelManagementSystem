package dao;
import domain.Salary;
import service.StaffService;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

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

    public Salary find(Integer staffId)throws SQLException{
        Salary salary = null;
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM salary WHERE staff_id=?");
        //为预编译参数赋值
        preparedStatement.setInt(1,staffId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            salary = new Salary(
                    resultSet.getInt("id"),
                    resultSet.getInt("baseSalary"),
                    resultSet.getInt("overtimePay"),
                    resultSet.getInt("carAllowance"),
                    resultSet.getInt("houseAllowance"),
                    resultSet.getInt("pension"),
                    resultSet.getInt("medicalInsurance"),
                    resultSet.getString("time"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id"))
            );
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return salary;
    }
    public boolean update(Salary salary)throws SQLException {
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE salary SET baseSalary=?, overtimePay=?, carAllowance=?, " +
                "houseAllowance=?, pension=?, medicalInsurance=?, time=?, staff_id=? WHERE id=?");
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
        System.out.println("修改" + affectedRows + "条记录");
        preparedStatement.close();
        connection.close();
        return affectedRows > 0;
    }
    public boolean add(Salary salary) throws SQLException {
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //添加预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO salary (baseSalary, overtimePay, carAllowance, houseAllowance, pension, medicalInsurance, time," +
                        "staff_id) VALUES (?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, salary.getBaseSalary());
        preparedStatement.setInt(2, salary.getOvertimePay());
        preparedStatement.setInt(3, salary.getCarAllowance());
        preparedStatement.setInt(4, salary.getHouseAllowance());
        preparedStatement.setInt(5, salary.getPension());
        preparedStatement.setInt(6, salary.getMedicalInsurance());
        preparedStatement.setString(7, salary.getTime());
        preparedStatement.setInt(8, salary.getStaff().getId());
        //执行预编译对象的executeUpdate()方法，获得增加行数
        int affectedRowNum = preparedStatement.executeUpdate();
        System.out.println("增加" + affectedRowNum + "条记录");
        JdbcHelper.close(preparedStatement, connection);
        return affectedRowNum > 0;
    }
    public Collection<Salary> findByStaffNo(String no) throws SQLException{
        Collection<Salary> salaries = new HashSet<>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        String findAttendances ="SELECT * FROM salary,staff WHERE staff.id = salary.staff_id and no =?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findAttendances);
        preparedStatement.setString(1,no);
        ResultSet resultSet = preparedStatement.executeQuery();
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            salaries.add(new Salary(
                    resultSet.getInt("id"),
                    resultSet.getInt("baseSalary"),
                    resultSet.getInt("overtimePay"),
                    resultSet.getInt("carAllowance"),
                    resultSet.getInt("houseAllowance"),
                    resultSet.getInt("pension"),
                    resultSet.getInt("medicalInsurance"),
                    resultSet.getString("time"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id"))
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(preparedStatement,connection);
        //返回degrees
        return salaries;
    }

}
