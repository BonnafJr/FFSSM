package FFSSM;

import java.time.LocalDate;

public class Plongeur extends Personne {

    private int niv;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niv) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance);
        this.niv = niv;
    }

}
