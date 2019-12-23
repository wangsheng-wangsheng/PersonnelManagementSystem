package dao;

import domain.Contract;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public class ContractDao {
        private static ContractDao contractDao = new ContractDao();
        private ContractDao(){}
        public static ContractDao getInstance(){
            return contractDao;
        }

        public Collection<Contract> findAll() throws SQLException {
            TreeSet<Contract> contracts = new TreeSet<Contract>();
            //获得连接对象
            Connection connection = JdbcHelper.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contract");
            //若结果集仍然有下一条记录，则执行循环体
            while (resultSet.next()){
                //以当前记录中的id,description,no,remarks值为参数，创建Degree对象
                Contract contract = new Contract(resultSet.getInt("id"),resultSet.getString("startTime"),
                        resultSet.getString("endTime"),resultSet.getInt("signTimes"),
                        resultSet.getString("signStatus"),resultSet.getString("ifWork"));
                //向degrees集合中添加Degree对象
                contracts.add(contract);
            }
            //关闭资源
            JdbcHelper.close(resultSet,statement,connection);
            return contracts;
        }

        public Contract find(Integer id) throws SQLException{
            Contract contract = null;
            Connection connection = JdbcHelper.getConn();
            String findContract_sql = "SELECT * FROM contract WHERE id=?";
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(findContract_sql);
            //为预编译参数赋值
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //由于id不能取重复值，故结果集中最多有一条记录
            //若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建Degree对象
            //若结果集中没有记录，则本方法返回null
            if (resultSet.next()) {
                contract = new Contract(resultSet.getInt("id"), resultSet.getString("startTime"),
                        resultSet.getString("endTime"), resultSet.getInt("signTimes"),
                        resultSet.getString("signStatus"), resultSet.getString("ifWork"));
            }
            //关闭资源
            JdbcHelper.close(resultSet,preparedStatement,connection);
            return contract;
        }

        public boolean add(Contract contract) throws SQLException{
            Connection connection = JdbcHelper.getConn();
            String addContract_sql = "INSERT INTO contract (startTime,endTime,signTimes," +
                    "signStatus,ifWork) VALUES (?,?,?,?,?)";
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(addContract_sql);
            //为预编译参数赋值
            preparedStatement.setString(1,contract.getStartTime());
            preparedStatement.setString(2,contract.getEndTime());
            preparedStatement.setInt(3,contract.getSignTimes());
            preparedStatement.setString(4,contract.getSignStatus());
            preparedStatement.setString(5,contract.getIfWork());
            //执行预编译语句，获取添加记录行数并赋值给affectedRowNum
            int affectedRowNum = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            return affectedRowNum>0;
        }


        //delete方法，根据degree的id值，删除数据库中对应的degree对象
        public boolean delete(int id) throws SQLException{
            Connection connection = JdbcHelper.getConn();
            //写sql语句
            String deleteContract_sql = "DELETE FROM contract WHERE id=?";
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(deleteContract_sql);
            //为预编译参数赋值
            preparedStatement.setInt(1,id);
            //执行预编译语句，获取删除记录行数并赋值给affectedRowNum
            int affectedRows = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            return affectedRows>0;
        }

        public boolean update(Contract contract) throws SQLException {
            Connection connection = JdbcHelper.getConn();
            //写sql语句
            String updateContract_sql = " UPDATE contranct SET startTime=?,endTime=?," +
                    "signTimes=?,signStatus=?,ifWork=? where id=?";
            //在该连接上创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(updateContract_sql);
            //为预编译参数赋值
            preparedStatement.setString(1,contract.getStartTime());
            preparedStatement.setString(2,contract.getEndTime());
            preparedStatement.setInt(3,contract.getSignTimes());
            preparedStatement.setString(4,contract.getSignStatus());
            preparedStatement.setString(5,contract.getIfWork());
            preparedStatement.setInt(6,contract.getId());
            //执行预编译语句，获取改变记录行数并赋值给affectedRowNum
            int affectedRows = preparedStatement.executeUpdate();
            //关闭资源
            JdbcHelper.close(preparedStatement,connection);
            return affectedRows>0;
        }

}
