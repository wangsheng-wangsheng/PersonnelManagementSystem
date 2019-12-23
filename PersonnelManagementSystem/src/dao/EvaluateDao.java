package dao;

import domain.Evaluate;
import service.StaffService;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class EvaluateDao {
    private static Collection<Evaluate> evaluates = new TreeSet<Evaluate>();
    private static EvaluateDao evaluateDao=new EvaluateDao();
    private EvaluateDao(){}
    /**
     * 定义一个EvaluateDao类型的getInstance()方法
     * @return evaluateDao
     */
    public static EvaluateDao getInstance(){
        return evaluateDao;
    }

    public Collection<Evaluate> findAll() throws SQLException {
        evaluates = new HashSet<Evaluate>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM evaluate");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            evaluates.add(new Evaluate(
                    resultSet.getInt("id"),
                    resultSet.getDate("assessmentStartDate"),
                    resultSet.getDate("assessmentEndDate"),
                    resultSet.getString("professionalSkill"),
                    resultSet.getString("workAttitude"),
                    resultSet.getString("wordPerformance"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id")),
                    resultSet.getString("comment")
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回degrees
        return evaluates;
    }
    public Evaluate find(Integer id)throws SQLException{
        Evaluate evaluate = null;
        Connection connection = JdbcHelper.getConn();
        String findEvaluate_sql = "SELECT * FROM evaluate WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findEvaluate_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Degree对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            evaluate = new Evaluate(
                    resultSet.getInt("id"),
                    resultSet.getDate("assessmentStartDate"),
                    resultSet.getDate("assessmentEndDate"),
                    resultSet.getString("professionalSkill"),
                    resultSet.getString("workAttitude"),
                    resultSet.getString("wordPerformance"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id")),
                    resultSet.getString("comment")
            );
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return evaluate;
    }
    public boolean update(Evaluate evaluate)throws SQLException{
        Connection connection = JdbcHelper.getConn();
        String updateEvaluate_sql = "UPDATE evaluate SET assessmentStartDate=?, assessmentEndDate=?, professionalSkill=?, workAttitude=?, workPerformance=?, staff_id=?, comment=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateEvaluate_sql);
        preparedStatement.setDate(1, (Date) evaluate.getAssessmentStartDate());
        preparedStatement.setDate(2, (Date) evaluate.getAssessmentEndDate());
        preparedStatement.setString(3,evaluate.getProfessionalSkill());
        preparedStatement.setString(4,evaluate.getWorkAttitude());
        preparedStatement.setString(5,evaluate.getWorkPerformance());
        preparedStatement.setInt(6,evaluate.getStaff().getId());
        preparedStatement.setString(7,evaluate.getComment());
        preparedStatement.setInt(8,evaluate.getId());
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        preparedStatement.close();
        connection.close();
        return  affectedRows>0;
    }
    public boolean add(Evaluate evaluate) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt =
                connection.prepareStatement("INSERT INTO evaluate" +
                        "(assessmentStartDate,assessmentEndDate,professionalSkill,workAttitude,workPerformance,staff_id,comment)"
                        + " VALUES (?,?,?,?,?,?,?)");
        //为预编译参数赋值
        pstmt.setDate(1, (Date) evaluate.getAssessmentStartDate());
        pstmt.setDate(2, (Date) evaluate.getAssessmentEndDate());
        pstmt.setString(3,evaluate.getProfessionalSkill());
        pstmt.setString(4,evaluate.getWorkAttitude());
        pstmt.setString(5,evaluate.getWorkPerformance());
        pstmt.setInt(6, evaluate.getStaff().getId());
        pstmt.setString(7,evaluate.getComment());
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
        String deleteEvaluate_sql = "DELETE FROM evaluate" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deleteEvaluate_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
    public boolean delete(Evaluate evaluate)throws SQLException {
        return delete(evaluate.getId());
    }
    public Collection<Evaluate> findAllByStaff(Integer staffId) throws SQLException {
        evaluates = new HashSet<Evaluate>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        String findEvaluates ="SELECT * FROM evaluate WHERE staff_id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findEvaluates);
        preparedStatement.setInt(1,staffId);
        ResultSet resultSet = preparedStatement.executeQuery();
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            evaluates.add(new Evaluate(
                    resultSet.getInt("id"),
                    resultSet.getDate("assessmentStartDate"),
                    resultSet.getDate("assessmentEndDate"),
                    resultSet.getString("professionalSkill"),
                    resultSet.getString("workAttitude"),
                    resultSet.getString("wordPerformance"),
                    StaffService.getInstance().find(resultSet.getInt("staff_id")),
                            resultSet.getString("comment")
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(resultSet,preparedStatement,connection);
        //返回degrees
        return evaluates;
    }
}


