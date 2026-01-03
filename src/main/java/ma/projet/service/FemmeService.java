package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    private IDao<Femme> dao;

    public FemmeService() {
        dao = new DaoImpl<>(Femme.class);
    }

    @Override
    public boolean create(Femme o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Femme o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Femme o) {
        return dao.delete(o);
    }

    @Override
    public Femme findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Femme> findAll() {
        return dao.findAll();
    }

    // Requête native nommée retournant le nombre d'enfants d'une femme entre deux dates
    public Long getNombreEnfantsEntreDates(Long femmeId, Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            NativeQuery<?> query = session.createNamedNativeQuery("Femme.countEnfantsEntreDates");
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            Object result = query.getSingleResult();
            if (result instanceof Number) {
                return ((Number) result).longValue();
            }
            return 0L;
        } finally {
            session.close();
        }
    }

    // Requête nommée retournant les femmes mariées au moins deux fois
    public List<Femme> getFemmesMarieesAuMoinsDeuxFois() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createNamedQuery("Femme.findMarieesAuMoinsDeuxFois", Femme.class);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Trouver la femme la plus âgée
    public Femme getFemmeLaPlusAgee() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createQuery(
                "SELECT f FROM Femme f ORDER BY f.dateNaissance ASC",
                Femme.class
            );
            query.setMaxResults(1);
            return query.uniqueResult();
        } finally {
            session.close();
        }
    }
}

