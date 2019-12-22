package dao;
import domain.Leader;
import jdk.swing.interop.SwingInterOpUtils;
import util.JdbcHelper;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
public final class LeaderDao {
    private static LeaderDao leaderDao = new LeaderDao();
    private LeaderDao() {
    }
    public static LeaderDao getInstance() {
        return leaderDao;
    }
    private static Collection<Leader> leaders = new TreeSet<Leader>();
    public Collection<Leader> findAll() throws SQLException {

        leaders = new HashSet<Leader>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from leader");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            leaders.add(new Leader(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getString("age")));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回leaders
        return LeaderDao.leaders;
    }
    public Leader find(Integer id) throws SQLException{
        Leader leader = null;
        Connection connection = JdbcHelper.getConn();
        String findLeader_sql = "SELECT * FROM leader WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findLeader_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Leader对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            leader = new Leader(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("sex"),
                    resultSet.getString("age"));
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return leader;
    }

    public boolean update(Leader leader) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        //写sql语句
        String updateLeader_sql = " UPDATE leader SET name=?,sex=?, age=? WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(updateLeader_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,leader.getName());
        preparedStatement.setString(2,leader.getSex());
        preparedStatement.setString(3,leader.getAge());
        preparedStatement.setInt(4,leader.getId());
        //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  affectedRows>0;
    }
    //增加职位的方法
    public boolean add(Leader leader) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt = connection.prepareStatement("insert into leader" + "(name,sex,age)" + " values (?,?,?)");
        //为预编译参数赋值
        pstmt.setString(1, leader.getName());
        pstmt.setString(2, leader.getSex());
        pstmt.setString(3, leader.getAge());
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
        String deleteLeader_sql = "DELETE FROM leader" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deleteLeader_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
}

