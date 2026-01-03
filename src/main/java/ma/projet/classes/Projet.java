package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projet")
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
    
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Tache> taches;

    public Projet() {
    }

    public Projet(String nom, Date dateDebut, Employe employe) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.employe = employe;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }
}

