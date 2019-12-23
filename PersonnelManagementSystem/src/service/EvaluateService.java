package service;

import dao.EvaluateDao;
import domain.Evaluate;
import domain.Staff;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

public class EvaluateService {
    private static EvaluateDao evaluateDao= EvaluateDao.getInstance();
    private static EvaluateService evaluateService=new EvaluateService();
    private EvaluateService(){}

    public static EvaluateService getInstance(){
        return evaluateService;
    }

    public Collection<Evaluate> getAll()throws SQLException {
        return evaluateDao.findAll();
    }

    public Collection<Evaluate> getAll(Staff staff) throws SQLException{
        Collection<Evaluate> evaluates = new HashSet<Evaluate>();
        for(Evaluate evaluate: evaluateDao.findAll()){
            if(evaluate.getStaff()==staff){
                evaluates.add(evaluate);
            }
        }
        return evaluates;
    }

    public Collection<Evaluate> findAll() throws SQLException{
        return evaluateDao.findAll();
    }
    public Evaluate find(Integer id)throws SQLException{
        return evaluateDao.find(id);
    }

    public boolean update(Evaluate evaluate)throws SQLException {
        return evaluateDao.update(evaluate);
    }

    public boolean add(Evaluate evaluate) throws SQLException{
        return evaluateDao.add(evaluate);
    }

    public boolean delete(Integer id) throws SQLException{
        Evaluate evaluate = this.find(id);
        return evaluateDao.delete(evaluate);
    }
    public boolean delete(Evaluate evaluate)throws SQLException{
        return evaluateDao.delete(evaluate);
    }
    public Collection<Evaluate> findAllByStaff(Integer staff_id) throws SQLException {
        return evaluateDao.findAllByStaff(staff_id);
    }
}

