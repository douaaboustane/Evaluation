package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;

import java.util.List;

public class CategorieService implements IDao<Categorie> {
    private IDao<Categorie> dao;

    public CategorieService() {
        dao = new DaoImpl<>(Categorie.class);
    }

    @Override
    public boolean create(Categorie o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Categorie o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Categorie o) {
        return dao.delete(o);
    }

    @Override
    public Categorie findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Categorie> findAll() {
        return dao.findAll();
    }
}

