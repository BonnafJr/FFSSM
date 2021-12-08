package FFSSM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
* Changements:
*
* CLASS => Moniteur // employeurActuel => Changer && par || et on enleve le !
*
* CLASS => LICENCE // Ajout du if else
*
* */
public class TestFFSSM {

    Moniteur shanks;
    Plongeur luffy;
    Plongeur zoro;
    Club club;

    @BeforeEach
    public void setUp() {

        shanks = new Moniteur("RS2052001", "Roger", "shanks", "12 impasse du hameau du lac 81600 Rivieres", "0768246879", LocalDate.of(2000, 3, 7), 76320);
        luffy = new Plongeur("ML5363654", "Monkey", "luffy", "7 rue de la libération 81100 Castres", "0610236600", LocalDate.of(2000, 12, 11));
        zoro = new Plongeur("RZ47869875", "Roronoa", "zoro", "7 rue de la libération 81100 Castres", "0644287504", LocalDate.of(2000, 11, 06));
        club = new Club(shanks, "Roux", "9876543210");

    }

    @Test
    public void testAjouteLicence() throws Exception {

        Licence licence1 = new Licence(shanks, "010RS", LocalDate.of(2021, 12, 2), club);
        Licence licence2 = new Licence(shanks, "020ML", LocalDate.of(2021, 6, 4), club);
        Licence licence3 = new Licence(luffy, "030RZ", LocalDate.of(2020, 11, 3), club);

        assertThrows(Exception.class, () -> {
            shanks.derniereLicence();
        });

        shanks.ajouteLicence("010RS", LocalDate.of(2021, 12, 2), club);

        assertEquals(shanks.derniereLicence(), licence1);

        shanks.ajouteLicence("020ML", LocalDate.of(2021, 6, 4), club);

        assertEquals(shanks.derniereLicence(), licence2);
    }

    @Test
    public void testNouvelEmbauche() {
        Embauche emb1 = new Embauche(LocalDate.of(2020, 6, 4), shanks, club);
        Embauche emb2 = new Embauche(LocalDate.of(2020, 3, 3), shanks, club);

        ArrayList<Embauche> listEmbauches = new ArrayList<>();
        listEmbauches.add(emb1);
        listEmbauches.add(emb2);

        shanks.nouvelleEmbauche(club, LocalDate.of(2020, 6, 4));
        shanks.nouvelleEmbauche(club, LocalDate.of(2020, 3, 3));

        assertEquals(listEmbauches, shanks.emplois());
    }

    @Test
    public void testTerminerEmbauche() throws Exception {

        assertThrows(Exception.class, () -> {
            shanks.terminerEmbauche(LocalDate.now());
        });

        shanks.nouvelleEmbauche(club, LocalDate.of(2020, 6, 4));

        shanks.terminerEmbauche(LocalDate.now());

        assertTrue(shanks.emplois().get(shanks.emplois().size() - 1).estTerminee());
    }

    @Test
    public void testEmployeurActuel() throws Exception {

        //TODO regler ça
        assertEquals(shanks.employeurActuel(), Optional.empty());

        shanks.nouvelleEmbauche(club, LocalDate.of(2020, 6, 4));

        assertEquals(shanks.employeurActuel(), Optional.of(club));

        Club club2 = new Club(shanks, "Mugiwara", "0123456789");

        shanks.nouvelleEmbauche(club, LocalDate.of(2020, 6, 4));
        shanks.terminerEmbauche(LocalDate.now());
        shanks.nouvelleEmbauche(club2, LocalDate.now());

        assertEquals(shanks.employeurActuel(), Optional.of(club2));

        shanks.terminerEmbauche(LocalDate.now());

        assertEquals(shanks.employeurActuel(), Optional.empty());

    }

    @Test
    public void testEstTerminee() throws Exception {

        shanks.nouvelleEmbauche(club, LocalDate.of(2002, 3, 7));
        assertFalse(shanks.emplois().get(shanks.emplois().size() - 1).estTerminee());

        shanks.terminerEmbauche(LocalDate.of(2021, 3, 7));
        assertTrue(shanks.emplois().get(shanks.emplois().size() - 1).estTerminee());

        Club club2 = new Club(shanks, "Mugiwara", "0123456789");
        shanks.nouvelleEmbauche(club2, LocalDate.of(2002, 3, 7));
        shanks.emplois().get(shanks.emplois().size() - 1).terminer(LocalDate.of(2021, 9, 2));

        assertTrue(shanks.emplois().get(shanks.emplois().size() - 1).estTerminee());
        assertEquals(shanks.emplois().get(shanks.emplois().size() - 1).getFin(), LocalDate.of(2021, 9, 2));

    }

    @Test
    public void testPlongeesNonConformes() throws Exception {

        Plongee plongee1 = new Plongee(new Site("Wano", "Cplonge"), shanks, LocalDate.of(2021, 12, 7), 20, 2);
        Plongee plongee2 = new Plongee(new Site("Marine", "Cplonge2"), shanks, LocalDate.of(2021, 12, 7), 20, 2);
        Plongee plongee3 = new Plongee(new Site("BLue East", "Cplonge3"), shanks, LocalDate.of(2021, 12, 7), 20, 2);

        shanks.ajouteLicence("010RS", LocalDate.of(2021, 12, 2), club);
        zoro.ajouteLicence("020ML", LocalDate.of(2021, 6, 4), club);
        luffy.ajouteLicence("030RZ", LocalDate.of(2020, 11, 3), club);

        plongee1.ajouteParticipant(shanks);
        plongee1.ajouteParticipant(zoro);

        plongee2.ajouteParticipant(zoro);
        plongee2.ajouteParticipant(luffy);

        plongee3.ajouteParticipant(luffy);
        plongee3.ajouteParticipant(shanks);

        HashSet<Plongee> plongeesNonConformes = new HashSet<>();

        club.organisePlongee(plongee1);

        assertEquals(club.plongeesNonConformes(), plongeesNonConformes);

        plongeesNonConformes.add(plongee2);
        club.organisePlongee(plongee2);

        assertEquals(club.plongeesNonConformes(), plongeesNonConformes);

        plongeesNonConformes.add(plongee3);
        club.organisePlongee(plongee3);

        assertEquals(club.plongeesNonConformes(), plongeesNonConformes);
    }

    @Test
    public void testEstConforme() throws Exception {
        Plongee plongee1 = new Plongee(new Site("Wano", "Cplonge"), shanks, LocalDate.of(2021, 12, 7), 20, 2);

        shanks.ajouteLicence("010RS", LocalDate.of(2021, 12, 2), club);
        zoro.ajouteLicence("020ML", LocalDate.of(2021, 6, 4), club);
        luffy.ajouteLicence("030RZ", LocalDate.of(2020, 11, 3), club);

        plongee1.ajouteParticipant(shanks);
        plongee1.ajouteParticipant(zoro);

        ArrayList<Licence> listLicences = new ArrayList<>();

        listLicences.add(shanks.derniereLicence());
        listLicences.add(zoro.derniereLicence());

        assertTrue(plongee1.estConforme());

        plongee1.ajouteParticipant(luffy);
        listLicences.add(luffy.derniereLicence());

        assertFalse(plongee1.estConforme());
        assertEquals(plongee1.getPalanquees(), listLicences);

    }

    @Test
    public void testEstValide() throws Exception {
        Licence l = new Licence(luffy, "LM020RS", LocalDate.of(2010, 1, 1), club);

        assertTrue(l.estValide(LocalDate.of(2010, 1, 3)));
        assertFalse(l.estValide(LocalDate.of(2015, 1, 1)));
        assertTrue(l.estValide(LocalDate.of(2010, 1, 1)));
    }

}
