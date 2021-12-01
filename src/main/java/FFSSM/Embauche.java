package FFSSM;

import java.time.LocalDate;

public class Embauche {

    private LocalDate debut;

    private LocalDate fin;

    private final Moniteur employe;

    private final Club employeur;

    public Embauche(LocalDate debut, Moniteur employe, Club employeur) {
        this.debut = debut;
        this.employe = employe;
        this.employeur = employeur;
    }

    /**
     * Termine cette embauche
     * @param dateFin la date à laquelle cette embauche est terminée
     */
    public void terminer(LocalDate dateFin) {
         // TODO: Implémenter cette méthode
        throw new UnsupportedOperationException("Pas encore implémenté");	    
    }
    

    public boolean estTerminee() {
        return (fin != null);
    }

    public Club getEmployeur() {
        return employeur;
    }

    
    public Moniteur getEmploye() {
        return employe;
    }


    public LocalDate getFin() {
        return fin;
    }


    public void setFin(LocalDate fin) {
        this.fin = fin;
    }


    public LocalDate getDebut() {
        return debut;
    }

    
}
