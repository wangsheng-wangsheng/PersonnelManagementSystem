package service;
import dao.LeaderDao;
import domain.Leader;

import java.sql.SQLException;
import java.util.Collection;
public final class LeaderService {
    private static LeaderDao leaderDao
            = LeaderDao.getInstance();
    private static LeaderService leaderService
            =new LeaderService();
    private LeaderService(){}

    public static LeaderService getInstance(){
        return leaderService;
    }
    public Collection<Leader> findAll() throws SQLException{
        return leaderDao.findAll();
    }
    public Leader find(Integer id)throws SQLException{
        return leaderDao.find(id);
    }
    public boolean update(Leader leader) throws SQLException{
        return leaderDao.update(leader);
    }
    public void add(Leader leader) throws SQLException {
        leaderDao.add(leader);
    }
    public boolean delete(Integer id) throws SQLException{
        return leaderDao.delete(id);
    }
}

