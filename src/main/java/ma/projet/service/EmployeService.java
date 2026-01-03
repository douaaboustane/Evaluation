package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeService implements IDao<Employe> {
    private IDao<Employe> dao;

    public EmployeService() {
        dao = new DaoImpl<>(Employe.class);
    }

    @Override
    public boolean create(Employe o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Employe o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Employe o) {
        return dao.delete(o);
    }

    @Override
    public Employe findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Employe> findAll() {
        return dao.findAll();
    }

    // Afficher la liste des tâches réalisées par un employé
    public List<Tache> getTachesRealiseesParEmploye(Long employeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Tache> query = session.createQuery(
                "SELECT DISTINCT t FROM Tache t " +
                "JOIN t.employeTaches et " +
                "WHERE et.employe.id = :employeId " +
                "AND t.dateDebutReelle IS NOT NULL",
                Tache.class
            );
            query.setParameter("employeId", employeId);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher la liste des projets gérés par un employé
    public List<Projet> getProjetsGeresParEmploye(Long employeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Projet> query = session.createQuery(
                "SELECT p FROM Projet p WHERE p.employe.id = :employeId",
                Projet.class
            );
            query.setParameter("employeId", employeId);
            return query.list();
        } finally {
            session.close();
        }
    }
}

