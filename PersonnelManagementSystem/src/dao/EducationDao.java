package dao;

import domain.Education;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class EducationDao {
    private static EducationDao educationDao = new EducationDao();
    private EducationDao() {
    }
    public static EducationDao getInstance() {
        return educationDao;
    }
    private static Collection<Education> educations = new TreeSet<Education>();
    public Collection<Education> findAll() throws SQLException {

        educations = new HashSet<Education>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from education");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            educations.add(new Education(resultSet.getInt("id"),
                    resultSet.getString("name")));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回positions
        return EducationDao.educations;
    }
    public Education find(Integer id) throws SQLException{
        Education education = null;
        Connection connection = JdbcHelper.getConn();
        String findEducation_sql = "SELECT * FROM education WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findEducation_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Position对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            education = new Education(resultSet.getInt("id"),
                    resultSet.getString("name"));
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return education;
    }

    public boolean update(Education education) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        //写sql语句
        String updatePosition_sql = " UPDATE education SET name=? WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(updatePosition_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,education.getName());
        preparedStatement.setInt(2,education.getId());
        //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  affectedRows>0;
    }
    //增加职位的方法
    public boolean add(Education education) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt = connection.prepareStatement("insert into education" + "name" + " values (?)");
        //为预编译参数赋值
        pstmt.setString(1, education.getName());
        //执行预编译对象的executeUpdate方法，获取添加的记录行数
        //执行预编译语句，用其返回值、影响的行数为赋值affectedRowNum
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("添加"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt, connection);
        //如果影响的行数大于1，则返回true，否则返回false
        return affectedRowNum > 0;
    }

    //职位删除方法
    //Integer类是基本数据类型int的包装器类，是抽象类Number的子类，位于java.lang包中。
    public boolean delete(Integer id) throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //创建sql语句
        String deleteEducatin_sql = "DELETE FROM education" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deleteEducatin_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
    public boolean delete(Education education)throws SQLException {
        return delete(education.getId());
    }
}
