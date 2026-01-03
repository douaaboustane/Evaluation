package ma.projet.service;

import ma.projet.classes.LigneCommande;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;

import java.util.List;

public class LigneCommandeService implements IDao<LigneCommande> {
    private IDao<LigneCommande> dao;

    public LigneCommandeService() {
        dao = new DaoImpl<>(LigneCommande.class);
    }

    @Override
    public boolean create(LigneCommande o) {
        return dao.create(o);
    }

    @Override
    public boolean update(LigneCommande o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(LigneCommande o) {
        return dao.delete(o);
    }

    @Override
    public LigneCommande findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<LigneCommande> findAll() {
        return dao.findAll();
    }
}

