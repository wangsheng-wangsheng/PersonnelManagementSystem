package service;
import dao.JobDao;
import domain.Job;

import java.sql.SQLException;
import java.util.Collection;
public final class JobService {
    private static JobDao jobDao
            = JobDao.getInstance();
    private static JobService jobService
            =new JobService();
    private JobService(){}

    public static JobService getInstance(){
        return jobService;
    }
    public Collection<Job> findAll() throws SQLException{
        return jobDao.findAll();
    }
    public Job find(Integer id)throws SQLException{
        return jobDao.find(id);
    }
    public boolean update(Job job) throws SQLException{
        return jobDao.update(job);
    }
    public void add(Job job) throws SQLException {
        jobDao.add(job);
    }
    public boolean delete(Integer id) throws SQLException{
        return jobDao.delete(id);
    }
}

