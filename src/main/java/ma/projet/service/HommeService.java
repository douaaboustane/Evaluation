package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    private IDao<Homme> dao;

    public HommeService() {
        dao = new DaoImpl<>(Homme.class);
    }

    @Override
    public boolean create(Homme o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Homme o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Homme o) {
        return dao.delete(o);
    }

    @Override
    public Homme findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Homme> findAll() {
        return dao.findAll();
    }

    // Afficher les épouses d'un homme entre deux dates
    public List<Femme> getEpousesEntreDates(Long hommeId, Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Femme> query = session.createQuery(
                "SELECT DISTINCT m.femme FROM Mariage m " +
                "WHERE m.homme.id = :hommeId " +
                "AND m.dateDebut BETWEEN :dateDebut AND :dateFin",
                Femme.class
            );
            query.setParameter("hommeId", hommeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher les mariages d'un homme avec tous les détails
    public void afficherMariagesAvecDetails(Long hommeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Homme homme = session.get(Homme.class, hommeId);
            if (homme != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());
                System.out.println("Mariages En Cours :");
                
                int index = 1;
                for (Mariage m : homme.getMariages()) {
                    if (m.getDateFin() == null) {
                        System.out.println(index + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                                         "   Date Début : " + sdf.format(m.getDateDebut()) +
                                         "    Nbr Enfants : " + m.getNombreEnfants());
                        index++;
                    }
                }
                
                System.out.println("\nMariages échoués :");
                index = 1;
                for (Mariage m : homme.getMariages()) {
                    if (m.getDateFin() != null) {
                        System.out.println(index + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                                         "  Date Début : " + sdf.format(m.getDateDebut()) +
                                         "    Date Fin : " + sdf.format(m.getDateFin()) +
                                         "    Nbr Enfants : " + m.getNombreEnfants());
                        index++;
                    }
                }
            }
        } finally {
            session.close();
        }
    }
}

