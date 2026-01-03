package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {
    private IDao<EmployeTache> dao;

    public EmployeTacheService() {
        dao = new DaoImpl<>(EmployeTache.class);
    }

    @Override
    public boolean create(EmployeTache o) {
        return dao.create(o);
    }

    @Override
    public boolean update(EmployeTache o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(EmployeTache o) {
        return dao.delete(o);
    }

    @Override
    public EmployeTache findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<EmployeTache> findAll() {
        return dao.findAll();
    }
}

