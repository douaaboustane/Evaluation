package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employe_tache")
public class EmployeTache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
    
    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    public EmployeTache() {
    }

    public EmployeTache(Employe employe, Tache tache) {
        this.employe = employe;
        this.tache = tache;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }
}

