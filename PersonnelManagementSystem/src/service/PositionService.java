package service;
import dao.PositionDao;

import java.sql.SQLException;
import java.util.Collection;
public final class PositionService {
    private static PositionDao positionDao
            = PositionDao.getInstance();
    private static PositionService positionService
            =new PositionService();
    private PositionService(){}

    public static PositionService getInstance(){
        return positionService;
    }
    public Collection<Position> findAll() throws SQLException{
        return positionDao.findAll();
    }
    public Position find(Integer id)throws SQLException{
        return positionDao.find(id);
    }
    public boolean update(Position position) throws SQLException{
        return positionDao.update(position);
    }
    public void add(Position position) throws SQLException {
        positionDao.add(position);
    }
    public boolean delete(Integer id) throws SQLException{
        return positionDao.delete(id);
    }
}

