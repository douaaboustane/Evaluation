package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tache")
@NamedQuery(name = "Tache.findByPrixSuperieur", 
            query = "SELECT t FROM Tache t WHERE t.prix > :prix")
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private double prix;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebutPlanifiee;
    
    @Temporal(TemporalType.DATE)
    private Date dateFinPlanifiee;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;
    
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
    
    @OneToMany(mappedBy = "tache", cascade = CascadeType.ALL)
    private List<EmployeTache> employeTaches;

    public Tache() {
    }

    public Tache(String nom, double prix, Date dateDebutPlanifiee, Date dateFinPlanifiee, Projet projet) {
        this.nom = nom;
        this.prix = prix;
        this.dateDebutPlanifiee = dateDebutPlanifiee;
        this.dateFinPlanifiee = dateFinPlanifiee;
        this.projet = projet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebutPlanifiee() {
        return dateDebutPlanifiee;
    }

    public void setDateDebutPlanifiee(Date dateDebutPlanifiee) {
        this.dateDebutPlanifiee = dateDebutPlanifiee;
    }

    public Date getDateFinPlanifiee() {
        return dateFinPlanifiee;
    }

    public void setDateFinPlanifiee(Date dateFinPlanifiee) {
        this.dateFinPlanifiee = dateFinPlanifiee;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<EmployeTache> getEmployeTaches() {
        return employeTaches;
    }

    public void setEmployeTaches(List<EmployeTache> employeTaches) {
        this.employeTaches = employeTaches;
    }
}

