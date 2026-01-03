package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestExercice2 {
    public static void main(String[] args) {
        try {
            // Création des services
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Test 1: Créer des employés
            System.out.println("=== Test 1: Création des employés ===");
            Employe emp1 = new Employe("ALAMI", "Ahmed");
            Employe emp2 = new Employe("BENNANI", "Fatima");
            employeService.create(emp1);
            employeService.create(emp2);
            System.out.println("Employés créés avec succès\n");

            // Test 2: Créer des projets
            System.out.println("=== Test 2: Création des projets ===");
            Date dateProjet1 = sdf.parse("14/01/2013");
            Projet projet1 = new Projet("Gestion de stock", dateProjet1, emp1);
            projetService.create(projet1);
            System.out.println("Projets créés avec succès\n");

            // Test 3: Créer des tâches
            System.out.println("=== Test 3: Création des tâches ===");
            Date dateDebut1 = sdf.parse("10/02/2013");
            Date dateFin1 = sdf.parse("20/02/2013");
            Date dateDebutReelle1 = sdf.parse("10/02/2013");
            Date dateFinReelle1 = sdf.parse("20/02/2013");
            
            Date dateDebut2 = sdf.parse("10/03/2013");
            Date dateFin2 = sdf.parse("15/03/2013");
            Date dateDebutReelle2 = sdf.parse("10/03/2013");
            Date dateFinReelle2 = sdf.parse("15/03/2013");
            
            Date dateDebut3 = sdf.parse("10/04/2013");
            Date dateFin3 = sdf.parse("25/04/2013");
            Date dateDebutReelle3 = sdf.parse("10/04/2013");
            Date dateFinReelle3 = sdf.parse("25/04/2013");

            Tache tache1 = new Tache("Analyse", 500, dateDebut1, dateFin1, projet1);
            tache1.setDateDebutReelle(dateDebutReelle1);
            tache1.setDateFinReelle(dateFinReelle1);
            
            Tache tache2 = new Tache("Conception", 1200, dateDebut2, dateFin2, projet1);
            tache2.setDateDebutReelle(dateDebutReelle2);
            tache2.setDateFinReelle(dateFinReelle2);
            
            Tache tache3 = new Tache("Développement", 2000, dateDebut3, dateFin3, projet1);
            tache3.setDateDebutReelle(dateDebutReelle3);
            tache3.setDateFinReelle(dateFinReelle3);

            tacheService.create(tache1);
            tacheService.create(tache2);
            tacheService.create(tache3);
            System.out.println("Tâches créées avec succès\n");

            // Test 4: Créer des relations EmployeTache
            System.out.println("=== Test 4: Création des relations EmployeTache ===");
            EmployeTache et1 = new EmployeTache(emp1, tache1);
            EmployeTache et2 = new EmployeTache(emp1, tache2);
            EmployeTache et3 = new EmployeTache(emp2, tache3);
            employeTacheService.create(et1);
            employeTacheService.create(et2);
            employeTacheService.create(et3);
            System.out.println("Relations créées avec succès\n");

            // Test 5: Tâches réalisées par un employé
            System.out.println("=== Test 5: Tâches réalisées par un employé ===");
            List<Tache> tachesEmp1 = employeService.getTachesRealiseesParEmploye(emp1.getId());
            System.out.println("Tâches réalisées par " + emp1.getNom() + " " + emp1.getPrenom() + ":");
            for (Tache t : tachesEmp1) {
                System.out.println("- " + t.getNom() + " (" + t.getPrix() + " DH)");
            }
            System.out.println();

            // Test 6: Projets gérés par un employé
            System.out.println("=== Test 6: Projets gérés par un employé ===");
            List<Projet> projetsEmp1 = employeService.getProjetsGeresParEmploye(emp1.getId());
            System.out.println("Projets gérés par " + emp1.getNom() + " " + emp1.getPrenom() + ":");
            for (Projet p : projetsEmp1) {
                System.out.println("- " + p.getNom());
            }
            System.out.println();

            // Test 7: Tâches planifiées pour un projet
            System.out.println("=== Test 7: Tâches planifiées pour un projet ===");
            List<Tache> tachesPlanifiees = projetService.getTachesPlanifieesParProjet(projet1.getId());
            System.out.println("Tâches planifiées pour le projet " + projet1.getNom() + ":");
            for (Tache t : tachesPlanifiees) {
                System.out.println("- " + t.getNom());
            }
            System.out.println();

            // Test 8: Tâches réalisées avec dates réelles
            System.out.println("=== Test 8: Tâches réalisées avec dates réelles ===");
            projetService.afficherTachesRealiseesParProjet(projet1.getId());
            System.out.println();

            // Test 9: Tâches avec prix > 1000 DH
            System.out.println("=== Test 9: Tâches avec prix > 1000 DH ===");
            List<Tache> tachesCheres = tacheService.getTachesPrixSuperieur1000();
            System.out.println("Tâches avec prix supérieur à 1000 DH:");
            for (Tache t : tachesCheres) {
                System.out.println("- " + t.getNom() + " (" + t.getPrix() + " DH)");
            }
            System.out.println();

            // Test 10: Tâches réalisées entre deux dates
            System.out.println("=== Test 10: Tâches réalisées entre deux dates ===");
            Date dateDebut = sdf.parse("01/02/2013");
            Date dateFin = sdf.parse("31/03/2013");
            List<Tache> tachesEntreDates = tacheService.getTachesRealiseesEntreDates(dateDebut, dateFin);
            System.out.println("Tâches réalisées entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + ":");
            for (Tache t : tachesEntreDates) {
                System.out.println("- " + t.getNom());
            }
            System.out.println();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

