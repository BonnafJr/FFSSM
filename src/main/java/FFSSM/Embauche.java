package FFSSM;

import java.time.LocalDate;
import java.util.Objects;

public class Embauche {

    private LocalDate debut;

    private LocalDate fin;

    private final Moniteur employe;

    private final Club employeur;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Embauche embauche = (Embauche) o;
        return Objects.equals(debut, embauche.debut) && Objects.equals(fin, embauche.fin) && Objects.equals(employe, embauche.employe) && Objects.equals(employeur, embauche.employeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debut, fin, employe, employeur);
    }

    public Embauche(LocalDate debut, Moniteur employe, Club employeur) {
        this.debut = debut;
        this.employe = employe;
        this.employeur = employeur;
    }

    public void terminer(LocalDate dateFin) throws Exception {

        if (!this.estTerminee()) {
            this.fin = dateFin;
        } else {
            throw new Exception("Le moniteur n'a pas d'emploi en cours");
        }
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
