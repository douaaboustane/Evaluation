package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProjetService implements IDao<Projet> {
    private IDao<Projet> dao;

    public ProjetService() {
        dao = new DaoImpl<>(Projet.class);
    }

    @Override
    public boolean create(Projet o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Projet o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Projet o) {
        return dao.delete(o);
    }

    @Override
    public Projet findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Projet> findAll() {
        return dao.findAll();
    }

    // Afficher la liste des tâches planifiées pour un projet
    public List<Tache> getTachesPlanifieesParProjet(Long projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Tache> query = session.createQuery(
                "SELECT t FROM Tache t WHERE t.projet.id = :projetId",
                Tache.class
            );
            query.setParameter("projetId", projetId);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher la liste des tâches réalisées avec les dates réelles
    public void afficherTachesRealiseesParProjet(Long projetId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Projet projet = session.get(Projet.class, projetId);
            if (projet != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                SimpleDateFormat sdfShort = new SimpleDateFormat("dd/MM/yyyy");
                
                System.out.println("Projet : " + projet.getId() + 
                                 "      Nom : " + projet.getNom() + 
                                 "     Date début : " + sdf.format(projet.getDateDebut()));
                System.out.println("Liste des tâches:");
                System.out.println("Num Nom            Date Début Réelle   Date Fin Réelle");
                
                for (Tache t : projet.getTaches()) {
                    if (t.getDateDebutReelle() != null && t.getDateFinReelle() != null) {
                        System.out.println(t.getId() + "  " + 
                                         String.format("%-15s", t.getNom()) + 
                                         sdfShort.format(t.getDateDebutReelle()) + "          " +
                                         sdfShort.format(t.getDateFinReelle()));
                    }
                }
            }
        } finally {
            session.close();
        }
    }
}

