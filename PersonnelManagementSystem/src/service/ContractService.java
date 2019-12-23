package service;
import domain.Contract;
import dao.ContractDao;

import java.sql.SQLException;
import java.util.Collection;

public class ContractService {
    private static ContractDao contractDao = ContractDao.getInstance();
    private static ContractService contractService = new ContractService();

    private ContractService() {
    }

    public static ContractService getInstance() {
        return contractService;
    }

    public Collection<Contract> findAll() throws SQLException {
        return contractDao.findAll();
    }

    public Contract find(Integer id) throws SQLException {
        return contractDao.find(id);
    }

    public boolean update(Contract contract) throws SQLException {
        return contractDao.update(contract);
    }

    public boolean add(Contract contract) throws SQLException {
        return contractDao.add(contract);
    }

    public boolean delete(Integer id) throws SQLException {
        return contractDao.delete(id);
    }

}
