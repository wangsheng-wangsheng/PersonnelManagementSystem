package dao;
import util.JdbcHelper;
import domain.Attendance;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public final class AttendanceDao {
    private static Collection<Attendance> attendances = new TreeSet<Attendance>();
    private static AttendanceDao attendanceDao=new AttendanceDao();
    private AttendanceDao(){}
    /**
     * 定义一个AttendanceDao类型的getInstance()方法
     * @return attendanceDao
     */
    public static AttendanceDao getInstance(){
        return attendanceDao;
    }

    public Collection<Attendance> findAll() throws SQLException {
        attendances = new HashSet<Attendance>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM attendance");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            attendances.add(new Attendance(
                    resultSet.getInt("id"),
                    resultSet.getString("attendanceTime"),
                    resultSet.getString("remarks"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id"))
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回degrees
        return attendances;
    }
    public Attendance find(Integer id)throws SQLException{
        Attendance attendance = null;
        Connection connection = JdbcHelper.getConn();
        String findAttendance_sql = "SELECT * FROM attendance WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findAttendance_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Degree对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            attendance = new Attendance(
                    resultSet.getInt("id"),
                    resultSet.getString("attendanceTime"),
                    resultSet.getString("remarks"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id"))
            );
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return attendance;
    }
    public boolean update(Attendance attendance)throws SQLException{
        Connection connection = JdbcHelper.getConn();
        String updateAttendance_sql = "UPDATE attendance SET attendanceTime=?, remarks=?, staff_id=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateAttendance_sql);
        preparedStatement.setString(1,attendance.getAttendanceTime());
        preparedStatement.setString(2,attendance.getRemarks());
        preparedStatement.setInt(3,attendance.getStaff().getId());
        preparedStatement.setInt(4,attendance.getId());
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        preparedStatement.close();
        connection.close();
        return  affectedRows>0;
    }
    public boolean add(Attendance attendance) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt =
                connection.prepareStatement("INSERT INTO attendance" +
                        "(attendanceTime,remarks,staff_id)"
                        + " VALUES (?,?,?)");
        //为预编译参数赋值
        pstmt.setString(1, attendance.getAttendanceTime());
        pstmt.setString(2, attendance.getRemarks());
        pstmt.setInt(3, attendance.getStaff().getId());
        //执行预编译对象的executeUpdate方法，获取添加的记录行数
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("添加"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt, connection);
        //如果影响的行数大于1，则返回true，否则返回false
        return affectedRowNum > 0;
    }
    public boolean delete(Integer id) throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //创建sql语句
        String deleteAttendance_sql = "DELETE FROM attendance" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deleteAttendance_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
    public boolean delete(Attendance attendance)throws SQLException{
        return delete(attendance.getId());
    }
    public Collection<Attendance> findAllByStaff(Integer staffId) throws SQLException {
        attendances = new HashSet<Attendance>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        String findAttendances ="SELECT * FROM attendance WHERE staff_id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findAttendances);
        preparedStatement.setInt(1,staffId);
        ResultSet resultSet = preparedStatement.executeQuery();
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            attendances.add(new Attendance(
                    resultSet.getInt("id"),
                    resultSet.getString("attendanceTime"),
                    resultSet.getString("remarks"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id"))
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(resultSet,preparedStatement,connection);
        //返回degrees
        return attendances;
    }
}

