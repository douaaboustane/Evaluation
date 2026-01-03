package ma.projet.test;

import ma.projet.beans.*;
import ma.projet.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestExercice3 {
    public static void main(String[] args) {
        try {
            // Création des services
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Test 1: Créer 10 femmes
            System.out.println("=== Test 1: Création de 10 femmes ===");
            Femme f1 = new Femme("SALIMA", "RAMI", sdf.parse("15/05/1970"));
            Femme f2 = new Femme("AMAL", "ALI", sdf.parse("20/06/1975"));
            Femme f3 = new Femme("WAFA", "ALAOUI", sdf.parse("10/07/1980"));
            Femme f4 = new Femme("KARIMA", "ALAMI", sdf.parse("25/08/1965"));
            Femme f5 = new Femme("FATIMA", "BENNANI", sdf.parse("12/09/1972"));
            Femme f6 = new Femme("AICHA", "CHAKIR", sdf.parse("30/10/1978"));
            Femme f7 = new Femme("SOUAD", "ELAMI", sdf.parse("05/11/1982"));
            Femme f8 = new Femme("NADIA", "FADILI", sdf.parse("18/12/1976"));
            Femme f9 = new Femme("LATIFA", "HAMIDI", sdf.parse("22/01/1974"));
            Femme f10 = new Femme("ZINEB", "IDRISSI", sdf.parse("14/02/1985"));

            femmeService.create(f1);
            femmeService.create(f2);
            femmeService.create(f3);
            femmeService.create(f4);
            femmeService.create(f5);
            femmeService.create(f6);
            femmeService.create(f7);
            femmeService.create(f8);
            femmeService.create(f9);
            femmeService.create(f10);
            System.out.println("10 femmes créées avec succès\n");

            // Test 2: Créer 5 hommes
            System.out.println("=== Test 2: Création de 5 hommes ===");
            Homme h1 = new Homme("SAFI", "SAID", sdf.parse("10/03/1960"));
            Homme h2 = new Homme("ALAMI", "AHMED", sdf.parse("15/04/1965"));
            Homme h3 = new Homme("BENNANI", "MOHAMED", sdf.parse("20/05/1970"));
            Homme h4 = new Homme("CHAKIR", "HASSAN", sdf.parse("25/06/1968"));
            Homme h5 = new Homme("FADILI", "OMAR", sdf.parse("30/07/1972"));

            hommeService.create(h1);
            hommeService.create(h2);
            hommeService.create(h3);
            hommeService.create(h4);
            hommeService.create(h5);
            System.out.println("5 hommes créés avec succès\n");

            // Test 3: Afficher la liste des femmes
            System.out.println("=== Test 3: Liste des femmes ===");
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom() + 
                                 " (Née le " + sdf.format(f.getDateNaissance()) + ")");
            }
            System.out.println();

            // Test 4: Afficher la femme la plus âgée
            System.out.println("=== Test 4: Femme la plus âgée ===");
            Femme femmeAgee = femmeService.getFemmeLaPlusAgee();
            if (femmeAgee != null) {
                System.out.println("Femme la plus âgée : " + femmeAgee.getNom() + " " + 
                                 femmeAgee.getPrenom() + " (Née le " + 
                                 sdf.format(femmeAgee.getDateNaissance()) + ")");
            }
            System.out.println();

            // Test 5: Créer des mariages
            System.out.println("=== Test 5: Création des mariages ===");
            Mariage m1 = new Mariage(sdf.parse("03/09/1990"), h1, f1);
            m1.setNombreEnfants(4);
            Mariage m2 = new Mariage(sdf.parse("03/09/1995"), h1, f2);
            m2.setNombreEnfants(2);
            Mariage m3 = new Mariage(sdf.parse("04/11/2000"), h1, f3);
            m3.setNombreEnfants(3);
            Mariage m4 = new Mariage(sdf.parse("03/09/1989"), h1, f4);
            m4.setDateFin(sdf.parse("03/09/1990"));
            m4.setNombreEnfants(0);
            Mariage m5 = new Mariage(sdf.parse("01/01/2005"), h1, f5);
            m5.setNombreEnfants(1);
            Mariage m6 = new Mariage(sdf.parse("15/06/2000"), h2, f6);
            m6.setNombreEnfants(2);
            Mariage m7 = new Mariage(sdf.parse("20/07/2002"), h2, f7);
            m7.setNombreEnfants(1);
            Mariage m8 = new Mariage(sdf.parse("10/08/2004"), h2, f8);
            m8.setNombreEnfants(3);
            Mariage m9 = new Mariage(sdf.parse("25/09/2006"), h2, f9);
            m9.setNombreEnfants(2);
            Mariage m10 = new Mariage(sdf.parse("12/10/2008"), h2, f10);
            m10.setNombreEnfants(1);

            mariageService.create(m1);
            mariageService.create(m2);
            mariageService.create(m3);
            mariageService.create(m4);
            mariageService.create(m5);
            mariageService.create(m6);
            mariageService.create(m7);
            mariageService.create(m8);
            mariageService.create(m9);
            mariageService.create(m10);
            System.out.println("Mariages créés avec succès\n");

            // Test 6: Afficher les épouses d'un homme donné
            System.out.println("=== Test 6: Épouses d'un homme entre deux dates ===");
            Date dateDebut = sdf.parse("01/01/1990");
            Date dateFin = sdf.parse("31/12/2000");
            List<Femme> epouses = hommeService.getEpousesEntreDates(h1.getId(), dateDebut, dateFin);
            System.out.println("Épouses de " + h1.getNom() + " " + h1.getPrenom() + 
                             " entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + ":");
            for (Femme f : epouses) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom());
            }
            System.out.println();

            // Test 7: Afficher le nombre d'enfants d'une femme entre deux dates
            System.out.println("=== Test 7: Nombre d'enfants d'une femme entre deux dates ===");
            Long nombreEnfants = femmeService.getNombreEnfantsEntreDates(f1.getId(), dateDebut, dateFin);
            System.out.println("Nombre d'enfants de " + f1.getNom() + " " + f1.getPrenom() + 
                             " entre " + sdf.format(dateDebut) + " et " + sdf.format(dateFin) + 
                             ": " + nombreEnfants);
            System.out.println();

            // Test 8: Afficher les femmes mariées deux fois ou plus
            System.out.println("=== Test 8: Femmes mariées au moins deux fois ===");
            List<Femme> femmesMarieesPlusieursFois = femmeService.getFemmesMarieesAuMoinsDeuxFois();
            System.out.println("Femmes mariées au moins deux fois:");
            for (Femme f : femmesMarieesPlusieursFois) {
                System.out.println("- " + f.getNom() + " " + f.getPrenom());
            }
            System.out.println();

            // Test 9: Afficher les hommes mariés à quatre femmes entre deux dates
            System.out.println("=== Test 9: Hommes mariés à quatre femmes entre deux dates ===");
            Date dateDebut2 = sdf.parse("01/01/2000");
            Date dateFin2 = sdf.parse("31/12/2010");
            Long nombreHommes = mariageService.getNombreHommesMariesQuatreFemmesEntreDates(dateDebut2, dateFin2);
            System.out.println("Nombre d'hommes mariés à quatre femmes entre " + 
                             sdf.format(dateDebut2) + " et " + sdf.format(dateFin2) + ": " + nombreHommes);
            System.out.println();

            // Test 10: Afficher les mariages d'un homme avec tous les détails
            System.out.println("=== Test 10: Mariages d'un homme avec détails ===");
            hommeService.afficherMariagesAvecDetails(h1.getId());
            System.out.println();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

