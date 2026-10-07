package ma.projet;

import java.text.SimpleDateFormat;
import java.util.Date;
import ma.projet.entities.Assurance;
import ma.projet.entities.Client;
import ma.projet.entities.Contrat;
import ma.projet.entities.StatutContrat;
import ma.projet.service.AssuranceService;
import ma.projet.service.ClientService;
import ma.projet.service.ContratService;

public class Test {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        AssuranceService assuranceService = new AssuranceService();
        ClientService clientService = new ClientService();
        ContratService contratService = new ContratService();

        try {
         
            Client c1 = new Client("AB123456", "Alami", "Ahmed", "alami@email.com", "0661112233");
            Client c2 = new Client("CD789012", "Bennani", "Fatima", "bennani@email.com", "0662223344");
            clientService.create(c1);
            clientService.create(c2);

            Assurance a1 = new Assurance("Auto", 5000.0, "Tous risques");
            Assurance a2 = new Assurance("Habitation", 2500.0, "Multirisque habitation");
            Assurance a3 = new Assurance("Sante", 3500.0, "Couverture hospitalisation");
            assuranceService.create(a1);
            assuranceService.create(a2);
            assuranceService.create(a3);

            Date dDebut = sdf.parse("01/01/2026");
            Date dFin1 = sdf.parse("31/12/2026");
            Date dFin2 = sdf.parse("01/06/2026");
            Date dFin3 = sdf.parse("31/12/2027");

            Contrat ct1 = new Contrat(dDebut, dFin1, StatutContrat.ACTIF, c1, a1);
            Contrat ct2 = new Contrat(dDebut, dFin2, StatutContrat.RESILIE, c1, a2);
            Contrat ct3 = new Contrat(dDebut, dFin3, StatutContrat.ACTIF, c2, a1);
            contratService.create(ct1);
            contratService.create(ct2);
            contratService.create(ct3);

            System.out.println("====================================");
            System.out.println("1. RECHERCHER UNE ASSURANCE PAR SON TYPE :");
            System.out.println("====================================");
            Assurance assuranceTrouvee = assuranceService.findByType("Auto");
            if (assuranceTrouvee != null) {
                System.out.printf("Assurance trouvée : ID=%d | Type=%s | Montant=%.2f DH | Couverture=%s\n",
                        assuranceTrouvee.getId(),
                        assuranceTrouvee.getType(),
                        assuranceTrouvee.getMontant(),
                        assuranceTrouvee.getCouverture());
            } else {
                System.out.println("Aucune assurance trouvée pour ce type.");
            }

            System.out.println("\n====================================");
            System.out.println("2. AFFICHER LES CONTRATS D'UN CLIENT (PAR CIN) :");
            System.out.println("======================================");
            contratService.afficherContratsParClientCin("AB123456");

            System.out.println("\n=============================================");
            System.out.println("3. AFFICHER LES CONTRATS ASSOCIÉS À UNE ASSURANCE (PAR TYPE) :");
            System.out.println("===============================================");
            contratService.afficherContratsParTypeAssurance("Auto");

            System.out.println("\n=========================================");
            System.out.println("4. AFFICHER LES CONTRATS ACTIFS NON EXPIRÉS APRÈS UNE DATE DONNÉE :");
            System.out.println("===========================================");
            Date dateReference = sdf.parse("01/07/2026");
            contratService.afficherContratsActifsNonExpires(dateReference);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
