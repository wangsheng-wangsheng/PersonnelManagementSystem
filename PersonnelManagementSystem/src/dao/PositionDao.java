package dao;
import domain.Position;
import util.JdbcHelper;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
public final class PositionDao {
    private static PositionDao positionDao =
            new PositionDao();

    private PositionDao() {
    }

    public static PositionDao getInstance() {
        return positionDao;
    }

    private static Collection<Position> positions = new TreeSet<Position>();
    public Collection<Position> findAll() throws SQLException {

        positions = new HashSet<Position>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from position");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            positions.add(new Position(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("remarks")));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回positions
        return PositionDao.positions;
    }
    public Position find(Integer id) throws SQLException{
        Position position = null;
        Connection connection = JdbcHelper.getConn();
        String findPosition_sql = "SELECT * FROM position WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findPosition_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Position对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            position = new Position(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("remarks"));
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return position;
    }

    public boolean update(Position position) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        //写sql语句
        String updatePosition_sql = " UPDATE position SET name=?,remarks=? WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(updatePosition_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,position.getName());
        preparedStatement.setString(2,position.getRemarks());
        preparedStatement.setInt(3,position.getId());
        //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  affectedRows>0;
    }
    //增加职位的方法
    public boolean add(Position position) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt = connection.prepareStatement("insert into position" + "(name,remarks)" + " values (?,?)");
        //为预编译参数赋值
        pstmt.setString(1, position.getName());
        pstmt.setString(2, position.getRemarks());
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
        String deletePosition_sql = "DELETE FROM position" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deletePosition_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
    public boolean delete(Position position)throws SQLException {
        return delete(position.getId());
    }
}

