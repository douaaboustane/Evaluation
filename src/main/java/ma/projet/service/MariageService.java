package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class MariageService implements IDao<Mariage> {
    private IDao<Mariage> dao;

    public MariageService() {
        dao = new DaoImpl<>(Mariage.class);
    }

    @Override
    public boolean create(Mariage o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Mariage o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Mariage o) {
        return dao.delete(o);
    }

    @Override
    public Mariage findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Mariage> findAll() {
        return dao.findAll();
    }

    // Afficher le nombre d'hommes mariés à quatre femmes entre deux dates (API Criteria)
    public Long getNombreHommesMariesQuatreFemmesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Note: Hibernate 5+ utilise l'API Criteria moderne (JPA Criteria)
            // Pour compatibilité avec l'ancienne API Criteria, on utilise une requête HQL
            Query<Long> query = session.createQuery(
                "SELECT COUNT(DISTINCT h.id) FROM Homme h " +
                "WHERE (SELECT COUNT(DISTINCT m.femme.id) FROM Mariage m " +
                "       WHERE m.homme.id = h.id " +
                "       AND m.dateDebut BETWEEN :dateDebut AND :dateFin) = 4",
                Long.class
            );
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            Long result = query.uniqueResult();
            return result != null ? result : 0L;
        } finally {
            session.close();
        }
    }
}

