/**
 * @(#) Site.java
 */
package FFSSM;

import java.util.ArrayList;

public class Site {

    public String nom;
    private String details;
    private ArrayList<Plongee> plongees;

    public Site(String nom, String details) {
        this.nom = nom;
        this.details = details;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Site{" + "nom=" + nom + ", details=" + details + '}';
    }
}
