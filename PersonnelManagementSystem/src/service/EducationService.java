package service;

import dao.EducationDao;
import domain.Education;

import java.sql.SQLException;
import java.util.Collection;

public final class EducationService {
    private static EducationDao educationDao
            = EducationDao.getInstance();
    private static EducationService educationService
            =new EducationService();
    private EducationService(){}

    public static EducationService getInstance(){
        return educationService;
    }
    public Collection<Education> findAll() throws SQLException {
        return educationDao.findAll();
    }
    public Education find(Integer id)throws SQLException{
        return educationDao.find(id);
    }
    public boolean update(Education education) throws SQLException{
        return educationDao.update(education);
    }
    public void add(Education education) throws SQLException {
        educationDao.add(education);
    }
    public boolean delete(Integer id) throws SQLException{
        Education education = this.find(id);
        return this.delete(education);
    }
    public boolean delete(Education education) throws SQLException{
        return educationDao.delete(education);
    }
}
