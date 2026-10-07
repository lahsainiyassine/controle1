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
@Table(name = "client")

public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cin", unique = true, nullable = false, length = 20)
    private String cin;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contrat> contrats;

    public Client() {
    }

    public Client(String cin, String nom, String prenom, String email, String telephone) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getCin() {

        return cin;
    }

    public void setCin(String cin) {

        this.cin = cin;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getPrenom() {

        return prenom;
    }

    public void setPrenom(String prenom) {

        this.prenom = prenom;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {

        return telephone;
    }

    public void setTelephone(String telephone) {

        this.telephone = telephone;
    }

    public List<Contrat> getContrats() {

        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {

        this.contrats = contrats;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
