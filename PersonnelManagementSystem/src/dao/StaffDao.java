package dao;


import domain.*;
import service.ContractService;
import service.DepartmentService;
import service.EducationService;
import service.PositionService;

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
            Connection connection = util.JdbcHelper.getConn();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from staff");
            //若结果集仍然有下一条记录，则执行循环体
            while (resultSet.next()) {
                staffTreeSet.add(new Staff(resultSet.getInt("id"), resultSet.getString("staffId"),
                        resultSet.getString("name"),resultSet.getString("sex"),
                        resultSet.getString("nativePlace"),resultSet.getString("idCard"),
                        resultSet.getString("mail"),
                        DepartmentService.getInstance().find(resultSet.getInt("department")),
                        EducationService.getInstance().find(resultSet.getInt("title")),
                        ContractService.getInstance().find(resultSet.getInt("degree")),
                        PositionService.getInstance().find(resultSet.getInt("degree")),
                        SalaryService.getInstance().find(resultSet.getInt("degree"))));
            }
            //执行预编译语句
            util.JdbcHelper.close(resultSet, stmt, connection);
            return staffTreeSet;
        }

        public Staff find(Integer id) throws SQLException {
            Staff staff = null;
            Connection connection = util.JdbcHelper.getConn();
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM staff WHERE id=?");
            //为预编译参数赋值
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //由于id不能取重复值，故结果集中最多有一条记录
            //若结果集有一条记录，则以当前记录中的id,name,title,department,degree值为参数，创建对象
            //若结果集中没有记录，则本方法返回null
            if (resultSet.next()) {
                staff = new Staff(resultSet.getInt("id"), resultSet.getString("staffId"),
                        resultSet.getString("name"),resultSet.getString("sex"),
                        resultSet.getString("nativePlace"),resultSet.getString("idCard"),
                        resultSet.getString("mail"),
                        DepartmentService.getInstance().find(resultSet.getInt("department")),
                        EducationService.getInstance().find(resultSet.getInt("title")),
                        ContractService.getInstance().find(resultSet.getInt("degree")),
                        PositionService.getInstance().find(resultSet.getInt("degree")),
                        SalaryService.getInstance().find(resultSet.getInt("degree")));
            }
            //关闭资源
            util.JdbcHelper.close(resultSet, preparedStatement, connection);
            return staff;
        }

        public boolean update(Staff staff) throws SQLException{
            Connection connection = util.JdbcHelper.getConn();
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE staff SET idCard=?,name=?, sex=?, phoneNumber=?, mail=?, nativePlace=?, " +
                            "department_id=?, contranct_id=?,position_id=?, education_id=? WHERE id=?");
            //为预编译参数赋值
            preparedStatement.setString(1, staff.getIdCard());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, staff.getSex());
            preparedStatement.setString(4, staff.getPhoneNumber());
            preparedStatement.setString(5, staff.getMail());
            preparedStatement.setString(6, staff.getNativePlace());
            preparedStatement.setInt(7, staff.getDepartment().getId());
            preparedStatement.setInt(8, staff.getContract().getId());
            preparedStatement.setInt(9, staff.getPosition().getId());
            preparedStatement.setInt(10, staff.getEducation().getId());
            preparedStatement.setInt(11, staff.getId());
            //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
            int affectedRowNum = preparedStatement.executeUpdate();
            util.JdbcHelper.close(preparedStatement, connection);
            return affectedRowNum > 0;
        }

    public boolean add(Staff staff) throws SQLException {
        //获取数据库连接对象
        Connection connection = util.JdbcHelper.getConn();
        //添加预编译语句
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO staff (idCard, staffId, name, sex, phoneNumber, mail, nativePlace," +
                        " department_id, contranct_id,position_id, education_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1, staff.getIdCard());
        preparedStatement.setInt(2, staff.getStaffId());
        preparedStatement.setString(3, staff.getName());
        preparedStatement.setString(4, staff.getSex());
        preparedStatement.setString(5, staff.getPhoneNumber());
        preparedStatement.setString(6, staff.getMail());
        preparedStatement.setString(7, staff.getNativePlace());
        preparedStatement.setInt(8, staff.getDepartment().getId());
        preparedStatement.setInt(9, staff.getContract().getId());
        preparedStatement.setInt(10, staff.getPosition().getId());
        preparedStatement.setInt(11, staff.getEducation().getId());
        //执行预编译对象的executeUpdate()方法，获得删除行数
        int affectedRowNum = preparedStatement.executeUpdate();
        util.JdbcHelper.close(preparedStatement, connection);
        return affectedRowNum > 0;
        }

        public boolean delete(Integer id) throws SQLException {
            Connection connection = util.JdbcHelper.getConn();
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM staff WHERE id=?");
            //为预编译参数赋值
            preparedStatement.setInt(1, id);
            //执行预编译语句，获取删除记录行数并赋值给affectedRowNum
            int affectedRows = preparedStatement.executeUpdate();
            //关闭资源
            util.JdbcHelper.close(preparedStatement, connection);
            return affectedRows>0;
    }

}
