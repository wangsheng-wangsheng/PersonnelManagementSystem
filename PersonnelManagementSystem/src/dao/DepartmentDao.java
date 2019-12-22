package dao;
import domain.Department;
import util.JdbcHelper;
import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
public final class DepartmentDao {
    private static Collection<Department> departments = new TreeSet<Department>();
    private static DepartmentDao departmentDao=new DepartmentDao();
    private DepartmentDao(){}
    /**
     * 定义一个DepartmentDao类型的getInstance()方法
     * @return departmentDao
     */
    public static DepartmentDao getInstance(){
        return departmentDao;
    }

    public Collection<Department> findAll() throws SQLException {
        departments = new HashSet<Department>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        //执行sql语句并返回结果集
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM department");
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            //新建部门然后添加到departments这个集合中
            departments.add(new Department(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("leader"),
                    resultSet.getString("address"),
                    resultSet.getString("remarks")
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回degrees
        return departments;
    }
    public Department find(Integer id)throws SQLException{
        Department department = null;
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //创建sql语句
        String findDepartment_sql = "SELECT * FROM department WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(findDepartment_sql);
        //为预编译参数赋值
        preparedStatement.setInt(1,id);
        //执行预编译语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //由于id不能取重复值，故结果集中最多有一条记录
        //若结果集有一条记录，则以当前记录中的id,name,address,remarks值为参数，创建Department对象
        //若结果集中没有记录，则本方法返回null
        if (resultSet.next()){
            department = new Department(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("leader"),
                    resultSet.getString("address"),
                    resultSet.getString("remarks")
            );
        }
        //关闭资源
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return department;
    }
    public boolean update(Department department)throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //创建sql语句
        String updateDepartment_sql = "UPDATE department SET name=?,leader=?,address=?,remarks=? WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,department.getName());
        preparedStatement.setString(2,department.getLeader());
        preparedStatement.setString(3,department.getAddress());
        preparedStatement.setString(4,department.getRemarks());
        preparedStatement.setInt(5,department.getId());
        //执行预编译语句
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  affectedRows>0;
    }
    public boolean add(Department department) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt =
                connection.prepareStatement("INSERT INTO department" +
                        "(name,leader,address,remarks)"
                        + " VALUES (?,?,?,?)");
        //为预编译参数赋值
        pstmt.setString(1,department.getName());
        pstmt.setString(2,department.getLeader());
        pstmt.setString(3,department.getAddress());
        pstmt.setString(4,department.getRemarks());
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
        String deleteDepartment_sql = "DELETE FROM department" + " WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement pstmt = connection.prepareStatement(deleteDepartment_sql);
        //为预编译语句赋值
        pstmt.setInt(1,id);
        int affectedRowNum = pstmt.executeUpdate();
        System.out.println("删除"+affectedRowNum+"条记录");
        //关闭pstmt, connection对象（关闭资源）
        JdbcHelper.close(pstmt,connection);
        return affectedRowNum > 0;
    }
}

