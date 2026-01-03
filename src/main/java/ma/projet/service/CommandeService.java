package ma.projet.service;

import ma.projet.classes.Commande;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;

import java.util.List;

public class CommandeService implements IDao<Commande> {
    private IDao<Commande> dao;

    public CommandeService() {
        dao = new DaoImpl<>(Commande.class);
    }

    @Override
    public boolean create(Commande o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Commande o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Commande o) {
        return dao.delete(o);
    }

    @Override
    public Commande findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Commande> findAll() {
        return dao.findAll();
    }
}

