package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produit")
@NamedQuery(name = "Produit.findByPrixSuperieur", 
            query = "SELECT p FROM Produit p WHERE p.prix > :prix")
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String reference;
    private String designation;
    private double prix;
    
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<LigneCommande> ligneCommandes;

    public Produit() {
    }

    public Produit(String reference, String designation, double prix, Categorie categorie) {
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }
}

