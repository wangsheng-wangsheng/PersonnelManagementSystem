package dao;
import domain.Department;
import service.LeaderService;
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
                    resultSet.getString("address"),
                    resultSet.getString("remarks"),
                    LeaderService.getInstance().find(resultSet.getInt("leader_id"))
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(stmt,connection);
        //返回degrees
        return departments;
    }
    public Collection<Department> findAllByName(String name) throws SQLException {
        departments = new HashSet<Department>();
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        String findDepartmentByName_sql = "SELECT * FROM department where name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(findDepartmentByName_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,"%"+name+"%");
        //执行sql语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //若结果集仍然有下一条记录，则执行循环体
        while (resultSet.next()){
            //新建部门然后添加到departments这个集合中
            departments.add(new Department(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("remarks"),
                    LeaderService.getInstance().find(resultSet.getInt("leader_id"))
            ));
        }
        //使用JdbcHelper关闭Connection对象
        JdbcHelper.close(preparedStatement,connection);
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
                    resultSet.getString("address"),
                    resultSet.getString("remarks"),
                    LeaderService.getInstance().find(resultSet.getInt("leader_id"))
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
        String updateDepartment_sql = "UPDATE department SET name=?,address=?,remarks=?,leader_id=? WHERE id=?";
        //在该连接上创建预编译语句对象
        PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment_sql);
        //为预编译参数赋值
        preparedStatement.setString(1,department.getName());
        preparedStatement.setString(2,department.getAddress());
        preparedStatement.setString(3,department.getRemarks());
        preparedStatement.setInt(4,department.getLeader().getId());
        preparedStatement.setInt(5,department.getId());
        //执行预编译语句
        int affectedRows = preparedStatement.executeUpdate();
        System.out.println("更新"+affectedRows+"条记录");
        //关闭资源
        JdbcHelper.close(preparedStatement,connection);
        return  affectedRows>0;
    }
//     create procedure sp_addDepartment(
//         IN name varchar(225),
//            address varchar(225),
//            remarks varchar(225),
//            leader_id int,
//         OUT id int
//      )
//      Begin
//         insert into sp_addDepartment(name,address,leader_id) values (name,address,leader_id);
//         select last_insert_id() into id;
//      end;
    public Department addWithStoredProcedure(Department department)throws SQLException{
        //获取数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备可调用语句对象，sp_addDepartment为存储过程名称，后面为4个参数
        CallableStatement callableStatement=connection.prepareCall("{CALL sp_addDepartment (?,?,?,?,?)}");
        //得第4个参数设置为输出参数，类型为长整型（数据库的数据类型)
        callableStatement.registerOutParameter(5, Types.BIGINT);
        callableStatement.setString(1,department.getName());
        callableStatement.setString(2,department.getAddress());
        callableStatement.setString(3,department.getRemarks());
        callableStatement.setInt(4,department.getLeader().getId());
        //执行可调用语句callableStatement
        callableStatement.execute();
        //获得第5个参数的值，数据库为该记录自动生成的id
        int id = callableStatement.getInt(5);
        //为参数department的id字段赋值
        department.setId(id);
        callableStatement.close();
        connection.close();
        return department;
    }
//    编写一个存储过程，通过输入部门id，获得相对应的一组数据
//    CREATE PROCEDURE selectDepartment(@inputDepId int)
//    AS DECLARE
//    @depId int,
//    @depName varchar(255),
//    @depAddress varchar(255),
//    @depRemarks varchar(255),
//    @depLeaderName varchar(255);
//    BEGIN
//         SELECT @depId =id FROM Department WHERE id=@inputDepId;
//         IF @depId IS NULL
//         BEGIN
//             PRINT '部门不存在';
//             RETURN;
//         END;
//         IF @depId=@inputDepId
//         SELECT @depName=Department.name,@depAddress=address,@depRemarks=remarks,@depLeaderName=Leader.name FROM Department,Leader
//         WHERE id=@inputDepId AND Department.leader_id=Leader.id;
//         BEGIN
//             PRINT '部门信息是:'+'部门名称:'+@depName+'部门地址：'+@depAddress+'简介:'+@depRemarks+'负责人:'+@depLeaderName;
//             RETURN;
//         END;
//         COMMIT;
//    END;

    public boolean add(Department department) throws SQLException{
        //获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
        //根据连接对象准备语句对象
        //SQL语句为多行时，注意语句不同部分之间有空格
        PreparedStatement pstmt =
                connection.prepareStatement("INSERT INTO department" +
                        "(name,address,remarks,leader_id)"
                        + " VALUES (?,?,?,?)");
        //为预编译参数赋值
        pstmt.setString(1,department.getName());
        pstmt.setString(2,department.getAddress());
        pstmt.setString(3,department.getRemarks());
        pstmt.setInt(4,department.getLeader().getId());
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

