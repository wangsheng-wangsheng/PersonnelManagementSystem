package dao;

import domain.*;
import domain.empower.User;
import service.*;
import service.empowerService.UserService;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public class StaffDao {
    private static StaffDao staffDao = new StaffDao();

    private StaffDao() { }

    public static StaffDao getInstance() {
        return staffDao;
    }

    public Collection<Staff> findAll() throws SQLException {
        TreeSet<Staff> staffTreeSet = new TreeSet<Staff>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from staff");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()) {
            staffTreeSet.add(new Staff(
                    UserService.getInstance().findUserById(resultSet.getInt("user_id")),
                    resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getString("idCard"),
                    resultSet.getString("nativePlace"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("mail"),
                    ContractService.getInstance().find(resultSet.getInt("contract_id")),
                    JobService.getInstance().find(resultSet.getInt("job_id")),
                    EducationService.getInstance().find(resultSet.getInt("education_id"))));
        }
        //执行预编译语句
        JdbcHelper.close(resultSet, stmt, connection);
        return staffTreeSet;
    }

    public Staff find(Integer id) throws SQLException {
        Staff staff = null;
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE id=?");
        //为预编译参数赋值
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,name,title,department,degree值为参数，创建对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()) {
            staff = new Staff(
                    UserService.getInstance().findUserById(resultSet.getInt("user_id")),
                    resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getString("idCard"),
                    resultSet.getString("nativePlace"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("mail"),
                    ContractService.getInstance().find(resultSet.getInt("contract_id")),
                    JobService.getInstance().find(resultSet.getInt("job_id")),
                    EducationService.getInstance().find(resultSet.getInt("education_id")));
        }
        //关闭资源
        JdbcHelper.close(resultSet, preparedStatement, connection);
        return staff;
    }

    public Staff findByStaffId(Integer staffId) throws SQLException {
        Staff staff = null;
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE staffId=?");
        //为预编译参数赋值
        preparedStatement.setInt(1, staffId);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的值为参数，创建对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()) {
            staff = new Staff(null,resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("name"), resultSet.getString("sex"),
                    resultSet.getString("idCard"), resultSet.getString("nativePlace"),
                    resultSet.getString("phoneNumber"), resultSet.getString("mail"),
                    ContractService.getInstance().find(resultSet.getInt("contract_id")),
                    JobService.getInstance().find(resultSet.getInt("job_id")),
                    EducationService.getInstance().find(resultSet.getInt("education_id")));
        }
        //关闭资源
        JdbcHelper.close(resultSet, preparedStatement, connection);
        return staff;
    }

    public boolean update(Staff staff) throws SQLException{
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE staff SET idCard=?,name=?, sex=?, phoneNumber=?, mail=?, nativePlace=?, " +
                        "contract_id=?, job_id=?, education_id=? WHERE id=?");
        //为预编译参数赋值
        preparedStatement.setString(1, staff.getIdCard());
        preparedStatement.setString(2, staff.getName());
        preparedStatement.setString(3, staff.getSex());
        preparedStatement.setString(4, staff.getPhoneNumber());
        preparedStatement.setString(5, staff.getMail());
        preparedStatement.setString(6, staff.getNativePlace());
        preparedStatement.setInt(7, staff.getContract().getId());
        preparedStatement.setInt(8, staff.getJob().getId());
        preparedStatement.setInt(9, staff.getEducation().getId());
        preparedStatement.setInt(10, staff.getId());
        //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
        int affectedRowNum = preparedStatement.executeUpdate();
        JdbcHelper.close(preparedStatement, connection);
        return affectedRowNum > 0;
    }

    public boolean add(Staff staff) throws SQLException {
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        int affectedRowNum = 0;
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id=(select MAX(id) from user)");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"));
            //添加预编译语句
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO staff (idCard, no, name, sex, phoneNumber, mail, nativePlace," +
                            "contract_id, job_id, education_id, user_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, staff.getIdCard());
            preparedStatement.setString(2, staff.getNo());
            preparedStatement.setString(3, staff.getName());
            preparedStatement.setString(4, staff.getSex());
            preparedStatement.setString(5, staff.getPhoneNumber());
            preparedStatement.setString(6, staff.getMail());
            preparedStatement.setString(7, staff.getNativePlace());
            preparedStatement.setInt(8, staff.getContract().getId());
            preparedStatement.setInt(9, staff.getJob().getId());
            preparedStatement.setInt(10, staff.getEducation().getId());
            preparedStatement.setInt(11, user.getId());
            //执行预编译对象的executeUpdate()方法，获得增加行数
            affectedRowNum = preparedStatement.executeUpdate();
        }
        JdbcHelper.close(preparedStatement, connection);
        return affectedRowNum > 0;
    }

    public boolean delete(Integer id) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM staff WHERE id=?");
        //为预编译参数赋值
        preparedStatement.setInt(1, id);
        //执行预编译语句，获取删除记录行数并赋值给affectedRowNum
        int affectedRows = preparedStatement.executeUpdate();
        //关闭资源
        JdbcHelper.close(preparedStatement, connection);
        return affectedRows>0;
    }
    public Staff findByStaffNo(String no) throws SQLException {
        Staff staff = null;
        Connection connection = JdbcHelper.getConn();
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE no=?");
        //为预编译参数赋值
        preparedStatement.setString(1, no);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的值为参数，创建对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()) {
            staff = new Staff(
                    null,
                    resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("name"), resultSet.getString("sex"),
                    resultSet.getString("idCard"), resultSet.getString("nativePlace"),
                    resultSet.getString("phoneNumber"), resultSet.getString("mail"),
                    ContractService.getInstance().find(resultSet.getInt("contract_id")),
                    JobService.getInstance().find(resultSet.getInt("job_id")),
                    EducationService.getInstance().find(resultSet.getInt("education_id")));
        }
        //关闭资源
        JdbcHelper.close(resultSet, preparedStatement, connection);
        return staff;
    }
}
