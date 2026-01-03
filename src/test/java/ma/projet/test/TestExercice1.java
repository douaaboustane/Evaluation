package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestExercice1 {
    public static void main(String[] args) {
        try {
            // Création des services
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();

            // Test 1: Créer des catégories
            System.out.println("=== Test 1: Création des catégories ===");
            Categorie cat1 = new Categorie("CAT1", "Ordinateurs");
            Categorie cat2 = new Categorie("CAT2", "Périphériques");
            categorieService.create(cat1);
            categorieService.create(cat2);
            System.out.println("Catégories créées avec succès\n");

            // Test 2: Créer des produits
            System.out.println("=== Test 2: Création des produits ===");
            Produit p1 = new Produit("ES12", "Ecran Samsung", 120, cat1);
            Produit p2 = new Produit("ZR85", "Souris Logitech", 100, cat2);
            Produit p3 = new Produit("EE85", "Clavier Mécanique", 200, cat2);
            Produit p4 = new Produit("PC01", "PC Portable", 5000, cat1);
            produitService.create(p1);
            produitService.create(p2);
            produitService.create(p3);
            produitService.create(p4);
            System.out.println("Produits créés avec succès\n");

            // Test 3: Afficher les produits par catégorie
            System.out.println("=== Test 3: Produits par catégorie ===");
            List<Produit> produitsCat1 = produitService.getProduitsByCategorie(cat1.getId());
            System.out.println("Produits de la catégorie " + cat1.getLibelle() + ":");
            for (Produit p : produitsCat1) {
                System.out.println("- " + p.getReference() + ": " + p.getDesignation() + " (" + p.getPrix() + " DH)");
            }
            System.out.println();

            // Test 4: Créer des commandes
            System.out.println("=== Test 4: Création des commandes ===");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf.parse("14/03/2013");
            Date date2 = sdf.parse("20/03/2013");
            Commande cmd1 = new Commande(date1);
            Commande cmd2 = new Commande(date2);
            commandeService.create(cmd1);
            commandeService.create(cmd2);
            System.out.println("Commandes créées avec succès\n");

            // Test 5: Créer des lignes de commande
            System.out.println("=== Test 5: Création des lignes de commande ===");
            LigneCommande lc1 = new LigneCommande(7, p1, cmd1);
            LigneCommande lc2 = new LigneCommande(14, p2, cmd1);
            LigneCommande lc3 = new LigneCommande(5, p3, cmd1);
            ligneCommandeService.create(lc1);
            ligneCommandeService.create(lc2);
            ligneCommandeService.create(lc3);
            System.out.println("Lignes de commande créées avec succès\n");

            // Test 6: Afficher les produits d'une commande
            System.out.println("=== Test 6: Produits d'une commande ===");
            produitService.afficherProduitsParCommande(cmd1.getId());
            System.out.println();

            // Test 7: Produits commandés entre deux dates
            System.out.println("=== Test 7: Produits commandés entre deux dates ===");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("31/03/2013");
            List<Produit> produitsCommandes = produitService.getProduitsCommandesEntreDates(dateDebut, dateFin);
            System.out.println("Produits commandés entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + ":");
            for (Produit p : produitsCommandes) {
                System.out.println("- " + p.getReference() + ": " + p.getDesignation());
            }
            System.out.println();

            // Test 8: Produits avec prix > 100 DH (requête nommée)
            System.out.println("=== Test 8: Produits avec prix > 100 DH ===");
            List<Produit> produitsChers = produitService.getProduitsPrixSuperieur100();
            System.out.println("Produits avec prix supérieur à 100 DH:");
            for (Produit p : produitsChers) {
                System.out.println("- " + p.getReference() + ": " + p.getDesignation() + " (" + p.getPrix() + " DH)");
            }
            System.out.println();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

