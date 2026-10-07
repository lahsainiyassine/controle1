package ma.projet.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "assurance")

public class Assurance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "montant", nullable = false)
    private double montant;

    @Column(name = "couverture", length = 150)
    private String couverture;

    @OneToMany(mappedBy = "assurance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contrat> contrats;

    public Assurance() {
    }

    public Assurance(String type, double montant, String couverture) {
        this.type = type;
        this.montant = montant;
        this.couverture = couverture;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public double getMontant() {

        return montant;
    }

    public void setMontant(double montant) {

        this.montant = montant;
    }

    public String getCouverture() {

        return couverture;
    }

    public void setCouverture(String couverture) {

        this.couverture = couverture;
    }

    public List<Contrat> getContrats() {

        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {

        this.contrats = contrats;
    }

    @Override
    public String toString() {
        return "Assurance{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", montant=" + montant +
                ", couverture='" + couverture + '\'' +
                '}';
    }
}
