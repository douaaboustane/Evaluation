package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommande;
import ma.projet.dao.DaoImpl;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {
    private IDao<Produit> dao;

    public ProduitService() {
        dao = new DaoImpl<>(Produit.class);
    }

    @Override
    public boolean create(Produit o) {
        return dao.create(o);
    }

    @Override
    public boolean update(Produit o) {
        return dao.update(o);
    }

    @Override
    public boolean delete(Produit o) {
        return dao.delete(o);
    }

    @Override
    public Produit findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Produit> findAll() {
        return dao.findAll();
    }

    // Afficher la liste des produits par catégorie
    public List<Produit> getProduitsByCategorie(Long categorieId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Produit> query = session.createQuery(
                "SELECT p FROM Produit p WHERE p.categorie.id = :categorieId", 
                Produit.class
            );
            query.setParameter("categorieId", categorieId);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher les produits commandés entre deux dates
    public List<Produit> getProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Produit> query = session.createQuery(
                "SELECT DISTINCT p FROM Produit p " +
                "JOIN p.ligneCommandes lc " +
                "JOIN lc.commande c " +
                "WHERE c.date BETWEEN :dateDebut AND :dateFin",
                Produit.class
            );
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.list();
        } finally {
            session.close();
        }
    }

    // Afficher les produits commandés dans une commande donnée
    public void afficherProduitsParCommande(Long commandeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Commande commande = session.get(Commande.class, commandeId);
            if (commande != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
                System.out.println("Commande : " + commande.getId() + 
                                 "     Date : " + sdf.format(commande.getDate()));
                System.out.println("Liste des produits :");
                System.out.println("Référence   Prix    Quantité");
                
                for (LigneCommande lc : commande.getLigneCommandes()) {
                    Produit p = lc.getProduit();
                    System.out.println(p.getReference() + "        " + 
                                     p.getPrix() + " DH  " + 
                                     lc.getQuantite());
                }
            }
        } finally {
            session.close();
        }
    }

    // Afficher la liste des produits dont le prix est supérieur à 100 DH (requête nommée)
    public List<Produit> getProduitsPrixSuperieur100() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Produit> query = session.createNamedQuery("Produit.findByPrixSuperieur", Produit.class);
            query.setParameter("prix", 100.0);
            return query.list();
        } finally {
            session.close();
        }
    }
}

