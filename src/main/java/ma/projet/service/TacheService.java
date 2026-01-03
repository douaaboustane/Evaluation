package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {
    private IDao<Tache> dao;

    public TacheService() {
        dao = new DaoImpl<>(Tache.class);
    }

    @Override
    public boolean create(Tache o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Tache o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Tache o) {
        return dao.delete(o);
    }

    @Override
    public Tache findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Tache> findAll() {
        return dao.findAll();
    }

    // Afficher les tâches dont le prix est supérieur à 1000 DH (requête nommée)
    public List<Tache> getTachesPrixSuperieur1000() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Tache> query = session.createNamedQuery("Tache.findByPrixSuperieur", Tache.class);
            query.setParameter("prix", 1000.0);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher les tâches réalisées entre deux dates
    public List<Tache> getTachesRealiseesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Tache> query = session.createQuery(
                "SELECT t FROM Tache t " +
                "WHERE t.dateDebutReelle BETWEEN :dateDebut AND :dateFin " +
                "AND t.dateFinReelle IS NOT NULL",
                Tache.class
            );
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } finally {
            session.close();
        }
    }
}

