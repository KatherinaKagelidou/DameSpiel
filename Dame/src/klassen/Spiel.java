package klassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import daten.DatenzugriffCSV;
import daten.DatenzugriffSerialisiert;
import daten.iDatenzugriff;

public class Spiel implements iBediener, Serializable {

    private final static int spielerMax = 2;
    // private Spieler[] Spieler = new Spieler[spielerMax];
    private Spieler spieler1;
    private Spieler spieler2;
    private Spieler spielerAmZug;
    private int spielerAnz = 0;
    private Spielbrett spielbrett;
    private Spieler sp;// greift nicht auf die Spielerklasse zu
    private boolean istDame = false;
    private Spielfigur figur;
    private int weissAnzahl=0;
    private int schwarzAnzahl=0;
    private Spielfigur[] figurWeiss = new Spielfigur[31];
    private Spielfigur[] figurSchwarz = new Spielfigur[31];
    private ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>();
    private boolean istZugbeginn = true;
    private boolean kannSchlagen;
    private boolean rechts = false;
    private ArrayList<String> datenSchlagen = new ArrayList<String>();

    private static final long SerialVersion = 1l;
    private static iDatenzugriff daten;

    private PrintWriter pw;

    public Spiel() {
//    	this.starteSpiel();
    }

    /**
     * 
     * Methode addSpieler()
     * 
     * Fuegt die jeweiligen Spieler ins Spiel hinzu. Der erste Spieler waehlt
     * eine Farbei, wobei der zweite Spieler die Farbe automatisch bekommt die
     * der erste Spieler nicht ausgewaehlt hat
     * 
     * @param name
     *            Name des Spielers.
     * @param farbe
     *            Farbe des Spielers
     */

    @Override
    public void addSpieler(String name, FarbEnum farbe, KI ki) {
    	
        if (spielerAnz == 2) {

            throw new RuntimeException(
                    "Es kann kein Spieler mehr hinzugefügt werden");
        }

        if (spielerAnz == 1) {
            Spieler sp = new Spieler(name, farbe, ki);

            // wenn spieler keine farbe gewählt hat
            if (sp.getFarbe() == null) {

                throw new RuntimeException(
                        "Bitte wähle eine Farbe aus du Schmock!!");

            }

            if (sp.getFarbe().equals(FarbEnum.SCHWARZ)) {
                this.spieler2 = sp;
                spielerAnz++;
            } else if (sp.getFarbe().equals(FarbEnum.WEISS)) {
                this.spieler1 = sp;
                spielerAnz++;
            }
            System.out.println("Spieler " + sp + " wurde hinzugefügt "
                    + sp.getFarbe());
        }
        if (spielerAnz == 0) {
            Spieler sp = new Spieler(name, farbe, ki);
            if (sp.getFarbe().equals(FarbEnum.WEISS)) {
                this.spieler1 = sp;
                spielerAnz++;
            } else if (sp.getFarbe().equals(FarbEnum.SCHWARZ)) {
                this.spieler2 = sp;
                spielerAnz++;
            }

            System.out.println("Spieler " + sp + " wurde hinzugefügt "
                    + sp.getFarbe());
        }
    }

    public int getSpielerAnz() {
        return spielerAnz;
    }

    @Override
    public void figurSetzen() {

        SpielerAmZug(spieler1);

        char ch = 'L';
        char ch1 = 'A';
        int x = 1;
        String str = null;
        boolean schwarz = false;
        int count = 1;

        for (int i = 0; i < spielbrett.getFelder().length; i++) {
            for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                str = "" + ch1 + x;
                x++;

                schwarz = !schwarz;

                if (x > 12) {
                    x = 1;
                    ch1++;
                    if (ch1 == ch) {
                        str = "" + ch + x;
                        schwarz = !schwarz;

                        break;

                    }
                    schwarz = !schwarz;

                }

                if (this.spielbrett.getFelder()[i][j].getId().startsWith("A")
                        || this.spielbrett.getFelder()[i][j].getId()
                                .startsWith("B")
                        || this.spielbrett.getFelder()[i][j].getId()
                                .startsWith("C")
                        || this.spielbrett.getFelder()[i][j].getId()
                                .startsWith("D")
                        || this.spielbrett.getFelder()[i][j].getId()
                                .startsWith("E")) {
                    if (this.spielbrett.getFelder()[i][j].getFarbeFeld()
                            .equals(FarbEnum.SCHWARZ)) {
                        figur = new Spielfigur(spielbrett.getFeld(str),
                                FarbEnum.SCHWARZ,false);
                        
                        if (count == 30) {
                            figurSchwarz[count] = figur;
                            figur.setId(count);
                            count = 0;
                        } else {
                            figurSchwarz[count] = figur;
                            figur.setId(count);
                        }
                        count++;

                        figur.setSpielfeld(spielbrett.getFeld(str));
                        spielbrett.getFelder()[i][j].setFigur(figur);
                        schwarzAnzahl++;

                        spieler2.setFigurArray(figurSchwarz);

                        System.out.println(figur);

                    }
                } else {

                    if (spielbrett.getFelder()[i][j].getId().startsWith("H")
                            || spielbrett.getFelder()[i][j].getId().startsWith(
                                    "I")
                            || spielbrett.getFelder()[i][j].getId().startsWith(
                                    "J")
                            || spielbrett.getFelder()[i][j].getId().startsWith(
                                    "K")
                            || spielbrett.getFelder()[i][j].getId().startsWith(
                                    "L")) {
                        if (this.spielbrett.getFelder()[i][j].getFarbeFeld()
                                .equals(FarbEnum.SCHWARZ)) {

                            figur = new Spielfigur(spielbrett.getFeld(str),
                                    FarbEnum.WEISS,false);

                            if (count == 1 && str.equals("L12")) {
                                count = 1;
                                figurWeiss[count] = figur;
                                figur.setId(count);
                            } else if (count == 1) {
                                count = 30;
                                figurWeiss[count] = figur;
                                figur.setId(count);
                            } else {
                                figurWeiss[count] = figur;
                                figur.setId(count);
                            }
                            count--;
                            figur.setSpielfeld(spielbrett.getFeld(str));
                            spielbrett.getFelder()[i][j].setFigur(figur);
                            weissAnzahl++;
                            
                            spieler1.setFigurArray(figurWeiss);
                            System.out.println(figur);
                        }
                    }
                }

            }// else schließen

        }// 1. if

    }

    public Spielfeld gebeFeld(String id) {
        Spielfeld feld = null;
        for (int i = 0; i < this.spielbrett.getFelder().length; i++) {
            for (int j = 0; j < this.spielbrett.getFelder()[i].length; j++) {
                if (this.spielbrett.getFelder()[i][j].getId().equals(id)) {
                    feld = this.spielbrett.getFelder()[i][j];
                }
            }
        }
        return feld;
    }

    public void SpielerAmZug(Spieler spieler) {
        if (spieler != null) {
            this.spielerAmZug = spieler;
        }
    }

    @Override
    public void gibKoordinate(String s) {

        for (int i = 0; i < this.spielbrett.getFelder().length; i++) {
            for (int j = 0; j < this.spielbrett.getFelder()[i].length; j++) {
                if (this.spielbrett.getFelder()[i][j].getId().equals(s)) {

                    System.out.println("i an index :" + i + "und j an index "
                            + j);
                }

            }
        }
    }

    /**
     * 
     * Methode starteSpiel()
     * 
     * Spielbrett wird erstellt und Startet das Spiel und laesst den Spieler mit
     * den weissen Spielfiguren anfangen.
     */
    @Override
    public void starteSpiel() {

        System.out.println("--- Das Spiel beginnt! ---");
        System.out.println(" ");

        System.out.println(this.toString());
        System.out.println("");
        System.out.println("---Das Spielbrett wird erstellt---");
        System.out.println("");

        if (spielerAnz < 3 && spielerAnz >= 1) {

            spielbrett = new Spielbrett();
            this.setSpielerAnz(spielerAnz);
            this.figurSetzen();
            System.out.println(spieler1.getName() + " du musst anfangen :-)");
        }
    }

    private void setSpielerAnz(int spielerAnz) {
        this.spielerAnz = spielerAnz;

    }


//    @Override
//    public String toString() {
//        String s = "";
//        s += "Es sind folgende Spieler im Spiel: \n";
//        s += "\n";
//        if (spieler1 != null) {
//            s += spieler1.toString() + " | ";
//        }
//        if (spieler2 != null) {
//            s += spieler2.toString();
//        }
//        return s;
//    }
    
    @Override
    public String toString() {
       
        return "";
    }

    

    @Override
    public void schlagen(String aktPos, String gegnerPos, String zielPos) {
        
        Spielfeld aktuell = this.gebeFeld(aktPos);
        Spielfeld gegner = this.gebeFeld(gegnerPos);
        Spielfeld ziel = this.gebeFeld(zielPos);
        Spielfigur figur = this.gebeFigur(aktuell.getFigur().getId());

        if (figur.getFarbe().equals(FarbEnum.SCHWARZ)) {

                // setzt figur, die schlägt auf neue pos
                moveFigur(figur, aktuell, ziel);
                // löscht geschlagene figur
                System.out.println("Figur "+gegner.getFigur().getId() +" auf Feld " + gegner.getId()+ " wurde von Spieler " +spielerAmZug.getName()+" geschlagen!");

                for (int i = 0; i < spielbrett.getFelder().length; i++) {
                    for (int j = 0; j < spielbrett.getFelder()[i].length;j++) {
                        if(spielbrett.getFelder()[i][j].equals(gegner)){
                        spielbrett.getFelder()[i][j].setFigur(null);
                        gegner.removeSpielfigur(gegner.getFigur());
                        return;
                        }
                    }
                }

        } else {

            // setzt figur, die schlägt auf neue pos
                moveFigur(figur, aktuell, ziel);
                // löscht geschlagene figur
                System.out.println("Figur "+gegner.getFigur().getId() +" auf Feld " + gegner.getId()+ " wurde von Spieler " +spielerAmZug.getName()+" geschlagen!");

                for (int i = 0; i < spielbrett.getFelder().length; i++) {
                    for (int j = 0; j < spielbrett.getFelder()[i].length;j++) {
                        if(spielbrett.getFelder()[i][j].equals(gegner)){
                        spielbrett.getFelder()[i][j].setFigur(null);
                        gegner.removeSpielfigur(gegner.getFigur());
                        return;
                        }
                    }
                }

            }
        }

    @Override
    public Spielfigur gebeFigur(String figurId) {
        Spielfigur f = null;
        for (Spielfigur figure : spielerAmZug.getFigurArray()) {
            if (figure == null)
                continue;
            if (figure.getId().equals(figurId)) {
                f = figure;
            }
        }
        return f;
    }

    private void moveFigur(Spielfigur figur, Spielfeld aktFeld,Spielfeld zielFeld) {
        for (int i = 0; i < spielbrett.getFelder().length; i++) {
            for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                if (spielbrett.getFelder()[i][j].equals(aktFeld)) {
                    spielbrett.getFelder()[i][j].setFigur(null);
                    aktFeld.removeSpielfigur(figur);

                    figur.setSpielfeld(spielbrett.getFeld(zielFeld.getId()));

                    zielFeld.setFigur(figur);
                }

            }
        }
    }

    @Override
    public void laufen(String aktPos, String zielPos, String figurId) {
    	
        spielbrett.getPositionen().clear();
        this.datenSchlagen.clear();
        Spielfigur figur = this.gebeFigur(figurId);
        Spielfeld aktFeld = this.gebeFeld(aktPos);
        Spielfeld zielFeld = this.gebeFeld(zielPos);
        
        
        
        if((figur.getFarbe().equals(FarbEnum.SCHWARZ)&& this.figur.getDame(figur))){
            laufeDameSchwarz(aktPos, zielPos, figurId);
            return;
        }
        else if((figur.getFarbe().equals(FarbEnum.WEISS)&& this.figur.getDame(figur))){
            laufeDameWeiss(aktPos, zielPos, figurId);
            return;
        }
        if (figur.getId().equals(aktFeld.getFigur().getId())) {
            figur = aktFeld.getFigur();
        }else{
            System.out.println("Du kannst mit dieser Figur nicht laufen!!!");
            return;
        }
        spielbrett.Umwandler(aktPos);
        spielbrett.Umwandler(zielPos);

        if (spielerAmZug.getFarbe().equals(FarbEnum.SCHWARZ)) {

            if (!kannSchlagen()) {
                // wenn figur schwarz ist
                if (figur.getFarbe().equals(FarbEnum.SCHWARZ)) {
                    if (this.spielbrett.getPositionen().get(1) < this.spielbrett
                            .getPositionen().get(3)) {
                        this.rechts = true;
                        Spielfeld f = getNachbar(aktPos, true);
                        if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
                            moveFigur(figur, aktFeld, zielFeld);
                            System.out.println(figur);
                        }

                    } else {
                        this.rechts = false;
                        Spielfeld f = getNachbar(aktPos, false);
                        if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
                            moveFigur(figur, aktFeld, zielFeld);
                            System.out.println(figur);
                        }
                    }
                }
            } else {
                System.out.println(spielerAmZug.getName()
                        + " du musst schlagen!!!");
                schlagen(datenSchlagen.get(0), datenSchlagen.get(1),datenSchlagen.get(2));

            }
        } else if (spielerAmZug.getFarbe().equals(FarbEnum.WEISS)) {
            if (!kannSchlagen()) {
                // wenn figur weiss ist
                if (figur.getFarbe().equals(FarbEnum.WEISS)) {
                    if (this.spielbrett.getPositionen().get(1) > this.spielbrett
                            .getPositionen().get(3)) {
                        this.rechts = true;
                        Spielfeld f = getNachbar(aktPos, true);
                        if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
                            moveFigur(figur, aktFeld, zielFeld);
                            System.out.println(figur);
                        }

                    } else {
                        this.rechts = false;
                        Spielfeld f = getNachbar(aktPos, false);
                        if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
                            moveFigur(figur, aktFeld, zielFeld);
                            System.out.println(figur);
                        }
                    }
                }

            } else {
                System.out.println(spielerAmZug.getName()
                        + " du musst schlagen!!!");
                schlagen(datenSchlagen.get(0),datenSchlagen.get(1),datenSchlagen.get(2));
            }

        }

        zugBeenden();

    }


    

    @Override
    public void zugBeenden() {
        if (this.spielerAmZug.equals(spieler1)) {
            SpielerAmZug(spieler2);
            System.out.println("Spieler " + spielerAmZug
                    + " ist an der Reihe!");
        } else {
            SpielerAmZug(spieler1);
            System.out.println("Spieler " + spielerAmZug
                    + " ist an der Reihe!");
        }

    }
    
    public Spielfeld getNachbar(String feld, boolean rechts) {
        Spielfeld fe = null;
        Spielfeld spielfeld = this.gebeFeld(feld);
        Spielfigur figur = spielfeld.getFigur();

        if (spielerAmZug.getFarbe().equals(FarbEnum.SCHWARZ)) {
            // laufe rechts schwarz
            if (rechts) {
                for (int i = 0; i < spielbrett.getFelder().length; i++) {
                    for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                        if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                            fe = spielbrett.getFelder()[i + 1][j + 1];
                            return fe;
                        }
                    }
                }
            } else {
                // laufe links schwarz
                for (int i = 0; i < spielbrett.getFelder().length; i++) {
                    for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

                        if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                            fe = spielbrett.getFelder()[i + 1][j - 1];
                            return fe;
                        }
                    }
                }

            }
        } else {
            // laufe rechts weiss
            if (spielerAmZug.getFarbe().equals(FarbEnum.WEISS)) {
                if (rechts) {
                    for (int i = spielbrett.getFelder().length - 1; i >= 0; i--) {
                        for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {
                            if (spielbrett.getFelder()[i][j].getId().equals(
                                    feld)) {
                                fe = spielbrett.getFelder()[i - 1][j - 1];
                                return fe;
                            }
                        }
                    }
                } else {
                    // laufe links weiss
                    for (int i = spielbrett.getFelder().length - 1; i >= 0; i--) {
                        for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {
                            if (spielbrett.getFelder()[i][j].getId().equals(
                                    feld)) {

                                fe = spielbrett.getFelder()[i - 1][j + 1];
                                return fe;
                            }
                        }
                    }
                }
            }
        }
        return fe;
    }


    public void laufeDameSchwarz(String aktPos, String zielPos, String figurId) {

        Spielfeld aktFeld = this.gebeFeld(aktPos);
        Spielfeld zielFeld = this.gebeFeld(zielPos);
        Spielfigur figur = this.gebeFigur(figurId);
        Spielfeld nachbar=null;
        Spielfeld nachbar2=null;

        spielbrett.Umwandler(aktPos);
        spielbrett.Umwandler(zielPos);

        
        for (int i = 0; i < spielbrett.getFelder().length; i++) {
            for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

                    // ObenRechts
                    if ((this.spielbrett.getPositionen().get(0) < this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) < this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, true, true);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                nachbar2=getNachbarDame(nachbar.getId(),true,true);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //ObenLinks
                    }else if ((this.spielbrett.getPositionen().get(0) < this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) > this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar =getNachbarDame(aktPos, false, true);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                nachbar2=getNachbarDame(nachbar.getId(),false,true);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //UntenRechts
                    }else if ((this.spielbrett.getPositionen().get(0) > this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) < this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, true, false);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                nachbar2=getNachbarDame(nachbar.getId(),true,false);
                                if(nachbar2.getFigur()==null){
                                aktPos=nachbar2.getId();
                                moveFigur(figur, aktFeld, nachbar2);
                                aktFeld=nachbar2;
                                nachbar.setFigur(null);
                                System.out.println(nachbar.getFigur());
                                System.out.println(nachbar2.getFigur());
                                return;
                                }else{
                                    System.out.println("Du kannst nicht schlagen weil die Figur " 
                                + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                    return;
                                }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //UntenLinks
                    }else if ((this.spielbrett.getPositionen().get(0) > this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) > this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, false, false);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                nachbar2=getNachbarDame(nachbar.getId(),false,false);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                    }
                }
            }
        }
    
    public void laufeDameWeiss(String aktPos, String zielPos, String figurId) {
        
        Spielfeld aktFeld = this.gebeFeld(aktPos);
        Spielfeld zielFeld = this.gebeFeld(zielPos);
        Spielfigur figur = this.gebeFigur(figurId);
        Spielfeld nachbar=null;
        Spielfeld nachbar2=null;

        spielbrett.Umwandler(aktPos);
        spielbrett.Umwandler(zielPos);

        
        for (int i = 0; i < spielbrett.getFelder().length; i++) {
            for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

                    // ObenRechts
                    if ((this.spielbrett.getPositionen().get(0) < this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) < this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, false, false);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                nachbar2=getNachbarDame(nachbar.getId(),false,false);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //ObenLinks
                    }else if ((this.spielbrett.getPositionen().get(0) < this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) > this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, true, false);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                nachbar2=getNachbarDame(nachbar.getId(),true, false);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //UntenRechts
                    }else if ((this.spielbrett.getPositionen().get(0) > this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) < this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, false, true);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld " + nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                nachbar2=getNachbarDame(nachbar.getId(),false, true);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                        //UntenLinks
                    }else if ((this.spielbrett.getPositionen().get(0) > this.spielbrett.getPositionen().get(2)) 
                            && (this.spielbrett.getPositionen().get(1) > this.spielbrett.getPositionen().get(3))) {
                        while (aktFeld.getId() != zielFeld.getId()) {
                            nachbar = getNachbarDame(aktPos, true, true);
                            if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.WEISS))){
                                System.out.println("Du kannst nicht weiter laufen weil die Spielfigur " + nachbar.getFigur().getId() + 
                                        " auf dem Feld "+nachbar.getId() + " deinen weg kreuzt.");
                                return;
                            }else if((nachbar.getFigur()!=null)&&(nachbar.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))){
                                nachbar2=getNachbarDame(nachbar.getId(),true,true);
                                if(nachbar2.getFigur()==null){
                                    aktPos=nachbar2.getId();
                                    moveFigur(figur, aktFeld, nachbar2);
                                    aktFeld=nachbar2;
                                    nachbar.setFigur(null);
                                    return;
                                    }else{
                                        System.out.println("Du kannst nicht schlagen weil die Figur " 
                                    + nachbar2.getFigur().getId() + " dir im Weg steht.");
                                        return;
                                    }
                            }
                            aktPos=nachbar.getId();
                            moveFigur(figur, aktFeld, nachbar);
                            aktFeld=nachbar;
                        }
                        
                    }
                }
            }
        }
    
    public Spielfeld getNachbarDame(String feld, boolean rechts,boolean oben) {
        Spielfeld fe = null;
        Spielfeld spielfeld = this.gebeFeld(feld);
        
        //rechtsOben
        if((rechts)&&(oben)){
            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                    if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                        fe = spielbrett.getFelder()[i + 1][j + 1];
                        return fe;
                    }
                }
            }
            
        //rechtsUnten
        }else if((rechts)&&((!oben))){
            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                    if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                        fe = spielbrett.getFelder()[i - 1][j + 1];
                        return fe;
                    }
                }
            }
            
        //linksOben
        }else if((!(rechts))&&(oben)){
            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

                    if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                        fe = spielbrett.getFelder()[i + 1][j - 1];
                        return fe;
                    }
                }
            }
            
        //linksUnten
        }else if((!(rechts))&&(!(oben))){
            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
                    if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
                        fe = spielbrett.getFelder()[i - 1][j - 1];
                        return fe;
                    }
                }
            }
            
        }
        return fe;
    }
    
    public boolean pruefeID1(Spielfeld feld){
        boolean a=false;
        switch (feld.getId()){
        case "A1": a=true;
        break;
        case "C1": a=true;
        break;
        case "E1": a=true;
        break;
        case "G1": a=true;
        break;
        case "I1": a=true;
        break;
        case "K1": a=true;
        break;
        default: 
            return false;

        }
        return a;
    }
    
    public boolean pruefeID2(Spielfeld feld){
        boolean a=false;
        switch (feld.getId()){
        case "B12": a=true;
        break;
        case "D12": a=true;
        break;
        case "F12": a=true;
        break;
        case "H12": a=true;
        break;
        case "J12": a=true;
        break;
        case "L12": a=true;
        break;
        default: 
            return false;

        }
        return a;
    }
    
    public boolean pruefeUnten(Spielfeld feld){
        boolean a=false;
        switch (feld.getId()){
        case "A1": a=true;
        break;
        case "A3": a=true;
        break;
        case "A5": a=true;
        break;
        case "A7": a=true;
        break;
        case "A9": a=true;
        break;
        case "A11": a=true;
        break;
        default: 
            return false;

        }
        return a;
    }
    
    public boolean pruefeOben(Spielfeld feld){
        boolean a=false;
        switch (feld.getId()){
        case "L2": a=true;
        break;
        case "L4": a=true;
        break;
        case "L6": a=true;
        break;
        case "L8": a=true;
        break;
        case "L10": a=true;
        break;
        case "L12": a=true;
        break;
        default: 
            return false;

        }
        return a;
    }
    


    public boolean kannSchlagenRechtsSchwarz(Spielfeld feld) {

        kannSchlagen = false;
        if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
            if (feld.getFigur() != null) {
            
            if (pruefeID2(feld)) {
                return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
                Spielfeld feld2 = getNachbar(feld.getId(), true);
                
                if(pruefeID2(feld2)){
                    kannSchlagen=false;
                }else{
                
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
                    Spielfeld feld3 = getNachbar(feld2.getId(), true);
                    
                    if (feld3.getFigur() == null) {
                        kannSchlagen = true;
                        datenSchlagen.add(feld.getId());
                        datenSchlagen.add(feld2.getId());
                        datenSchlagen.add(feld3.getId());
                    } else {
                        kannSchlagen = false;
                    }
                } else {
                    kannSchlagen = false;
                }
            }
        }}}
        return kannSchlagen;

    }

    public boolean kannSchlagenLinksSchwarz(Spielfeld feld) {

        kannSchlagen = false;
        if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
            if (feld.getFigur() != null) {
            
            if (pruefeID1(feld)) {
                return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
            
                Spielfeld feld2 = getNachbar(feld.getId(), false);
                
                if(pruefeID1(feld2)){
                    kannSchlagen=false;
                }else{
                
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
                    Spielfeld feld3 = getNachbar(feld2.getId(), false);
                    if (feld3.getFigur() == null) {
                        kannSchlagen = true;
                        datenSchlagen.add(feld.getId());
                        datenSchlagen.add(feld2.getId());
                        datenSchlagen.add(feld3.getId());
                    } else {
                        kannSchlagen = false;
                    }
                } else {
                    kannSchlagen = false;
                }
            }
        }}}
        return kannSchlagen;

    }

    public boolean kannSchlagenRechtsWeiss(Spielfeld feld) {

        
        kannSchlagen = false;
        if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
            if (feld.getFigur() != null) {
            
            if (pruefeID1(feld)) {
                return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
                Spielfeld feld2 = getNachbar(feld.getId(), true);
                
                if(pruefeID1(feld2)){
                    kannSchlagen=false;
                }else{
                
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe()
                                .equals(FarbEnum.SCHWARZ))) {
                    Spielfeld feld3 = getNachbar(feld2.getId(), true);
                    if (feld3.getFigur() == null) {
                        kannSchlagen = true;
                        datenSchlagen.add(feld.getId());
                        datenSchlagen.add(feld2.getId());
                        datenSchlagen.add(feld3.getId());
                    } else {
                        kannSchlagen = false;
                    }
                } else {
                    kannSchlagen = false;
                }
            }
        }}}
        return kannSchlagen;

    }

    public boolean kannSchlagenLinksWeiss(Spielfeld feld) {

        kannSchlagen = false;

        if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
            if (feld.getFigur() != null) {
                
            if (pruefeID2(feld)) {
                return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
                Spielfeld feld2 = getNachbar(feld.getId(), false);
                
                if(pruefeID2(feld2)){
                    kannSchlagen=false;
                }else{
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe()
                                .equals(FarbEnum.SCHWARZ))) {
                    Spielfeld feld3 = getNachbar(feld2.getId(), false);
                    if (feld3.getFigur() == null) {
                        kannSchlagen = true;
                        datenSchlagen.add(feld.getId());
                        datenSchlagen.add(feld2.getId());
                        datenSchlagen.add(feld3.getId());
                    } else {
                        kannSchlagen = false;
                    }
                } else {
                    kannSchlagen = false;
                }
            }
        }}}
        return kannSchlagen;

    }
    
    
    private boolean kannSchlagen() {
        FarbEnum farbe = spielerAmZug.getFarbe();
        switch (farbe) {
        case WEISS:
            for (int i = spielbrett.getFelder().length - 1; i >= 0; i--) {
                
                for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {


                        if (kannSchlagenLinksWeiss(spielbrett.getFelder()[i][j])
                                || kannSchlagenRechtsWeiss(spielbrett
                                        .getFelder()[i][j])) {

                            return true;
                        }

                }
            }
            break;
        case SCHWARZ:
            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

                    
                        if (kannSchlagenLinksSchwarz(spielbrett.getFelder()[i][j])
                                || kannSchlagenRechtsSchwarz(spielbrett
                                        .getFelder()[i][j])) {
                            return true;
                        }
                }
            }
            break;
        }
        return false;

    }
    
    

    public boolean istOK(Spielfeld feld) {

        int dx = feld.getX() - spielbrett.getSpielfeld().getX();
        int dy = feld.getY() - spielbrett.getSpielfeld().getY();

        if (Math.abs(dx) != Math.abs(dy)) {
            return false;
        } else if (Math.abs(dx) == 0 || (Math.abs(dx) > 2)) {
            return false;
        } else if (spielbrett.getSpielfeld().getFarbeFeld()
                .equals(FarbEnum.SCHWARZ)
                && (dy > 0)
                || !spielbrett.getSpielfeld().getFarbeFeld()
                        .equals(FarbEnum.SCHWARZ) && (dy < 0)) {
            return false;
        }

        return true;

    }
    @Override
    public boolean hatGewonnen(){
        if (weissAnzahl==0){
            System.out.println("Schwarz gewonnen!");
            return true;
        }
        if(schwarzAnzahl ==0){
            System.out.println("Weiss gewonnen!");
            return true;
        }
        System.out.println("Noch keiner hat gewonnen!!!");
        return false;
    }

    public boolean getZugBeginn() {
        return istZugbeginn;
    }

    public void merkeBeginn() {
        istZugbeginn = false;
    }
    
    
//    
//     @testen ob figur nach dem shclagen wirklich vom spielbrett entfernt wird
//    @Override
//    public void testen(){
//        for(int i = 0;i<spielbrett.getFelder().length;i++){
//            for(int j=0; j<spielbrett.getFelder()[i].length;j++){
//                if(spielbrett.getFelder()[i][j].getId().equals("E1")){
//                    System.out.println("Hohohohohohohohohoh" + spielbrett.getFelder()[i][j].getFigur());
//                    
//            
//                
//                
//                }
//                
//            }
//        }
//    }

    
    
    public Spieler getSpieler1(){
        return spieler1;
    }
    public Spieler getSpieler2(){
        return spieler2;
    }
    
    @Override
	public String farbePlayer(){
		System.out.println("spieler: "+spielerAmZug);
		System.out.println("farbe: "+spielerAmZug.getFarbe());
		FarbEnum player=spielerAmZug.getFarbe();
		
		String pl=null;
		switch(player){
		case SCHWARZ:
			pl="Schwarz";
			return pl;
		case WEISS:
			pl="Weiss";
			return pl;
		}
		
		return pl;
		
	}
    

    /**
     * Speichert die aktuelle Belegung des Spielbretts in CSV-Notation.
     */
    @Override
    public void belegungCSV() {

        try {
            pw = new PrintWriter(new FileWriter("speichern/belegung.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("DATEI ZUM SPEICHERN NICHT GEFUNDEN!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spielfeld[][] belegung = spielbrett.getFelder();
        char ch = 'L';
        char ch1 = 'A';
        int x = 1;
        String str = null;
        boolean schwarz = true;

        for (int i = 0; i < spielbrett.getFelder().length; i++) {

            for (int j = 0; j < belegung[i].length; j++) {
                str = "" + ch1 + x;
                x++;

                pw.print(belegung[i][j] = new Spielfeld(spielbrett, str,
                        schwarz));

                schwarz = !schwarz;

                if (x > 12) {
                    x = 1;
                    ch1++;
                    if (ch1 == ch) {
                        str = "" + ch + x;
                        schwarz = !schwarz;

                        break;

                    }
                    schwarz = !schwarz;

                }

            }
            pw.println();
        }

        pw.close();
    }

    @Override
    public void speichernCSV(String dateiname) {
        try {
            daten = new DatenzugriffCSV();
            Properties p = new Properties();
            p.setProperty("Dateiname", dateiname + ".csv");
            p.setProperty("Modus", "s");
            daten.oeffnen(p);

            String s = "";
            s += "Spieler: " + spieler1.getName() + " mit der Farbe "
                    + spieler1.getFarbe() + " \n" + "Spieler: " + spieler2.getName() + " mit der Farbe "
                    + spieler2.getFarbe()+ "";
            daten.schreiben(s + "\n");

            for (int i = 0; i < spielbrett.getFelder().length; i++) {
                for (int j = 0; j < spielbrett.getFelder()[i].length; j++)
                    daten.schreiben(spielbrett.getFelder()[i][j] + "\n");
            }

            daten.schreiben(this);

        } catch (Exception e) {
            // System.err.println("Speichern CSV fehlgeschlagen!");
            System.out.println("geladen");
        } finally {

            try {
                daten.schliessen(dateiname);
            } catch (IOException fehler) {
                System.out.println(fehler.getMessage());
            }

        }

    }

//    @Override
//    public Spiel lesenCSV(String s) {
//        try {
//            daten = new DatenzugriffCSV();
//            Properties p = new Properties();
//            daten.oeffnen(p);
//            Spiel spiel = (Spiel) daten.lesen(".csv");
//            System.out.println("Das Spiel wird geladen.");
//            daten.schliessen(p);
//            return spiel;
//        } catch (Exception e) {
//            System.err.println("Laden CSV fehlgeschlagen!");
//            return null;
//        }
//
//    }

    @Override
    public void speichernSerial(String s) {
        try {
            daten = new DatenzugriffSerialisiert();
            Properties p = new Properties();
            p.setProperty("datei", s + ".ser");
            daten.oeffnen(p);
            daten.schreiben(this);
            System.out.println("Das Spiel wurde gespeichert: "
                    + p.getProperty("datei"));
            daten.schliessen(p);
        } catch (Exception e) {
            System.out.println("Speichern serialisiert !");
        }
    }

@Override
public Spiel lesenCSV(String s) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Spiel ladenSerial(String s) {
	// TODO Auto-generated method stub
	return null;
}

    /**
     * laden serial return spiel
     */
//    @Override
//    public Spiel ladenSerial(String s) {
//        try {
//            daten = new DatenzugriffSerialisiert();
//            Properties p = new Properties();
//            daten.oeffnen(p);
//            Spiel spiel = (Spiel) daten.lesen(".ser");
//            System.out.println("Das Spiel wurde erfolgreich geladen.");
//            daten.schliessen(p);
//            return spiel;
//        } catch (Exception e) {
//            System.out.println("Laden serialisiert fehlgeschlagen!");
//            return null;
//        }
//
//    }
@Override
public Object laden(String name, String typ) throws IOException {
	typ = typ.toLowerCase();
	switch (typ) {
	case ("csv"):
		ladenCSV(name);
		break;
	default:
		throw new RuntimeException("Dateityp nicht existent");
	}
	return null;
}

@SuppressWarnings("unchecked")
public void ladenCSV(String filename) {
	iDatenzugriff load = new DatenzugriffCSV();
	Spiel s = new Spiel();
	
	
//	spielbrett = new Spielbrett();
//	try {
//		ArrayList<String> s = (ArrayList<String>) load.laden(filename, "csv");
//		for (int i = 0; i < s.size() - 1; i++) {
//			String[] args = s.get(i).split(";");
//			// i an der Stelle 0 und 1 sind Spieler
//			if (i == 0) {
//			
//				FarbEnum farbe;
//				if (args[1].equals("Schwarz")) {
//					farbe = FarbEnum.SCHWARZ;
//				} else {
//					farbe = FarbEnum.WEISS;
//				}
//				Spieler s1 = new Spieler(args[0], farbe, null);
//				this.addSpieler(s1.getName(), FarbEnum.WEISS, null);
//				
//			} else if (i == 1) {
//				
//				FarbEnum farbe;
//				if (args[1].equals("Schwarz")) {
//					farbe = FarbEnum.SCHWARZ;
//				} else {
//					farbe = FarbEnum.WEISS;
//				}
//				Spieler s2 = new Spieler(args[0], farbe, null);
//				this.addSpieler(s2.getName(), FarbEnum.WEISS, null);
//				// i an der Stelle 2 ist ActiveSpieler
//			} 
//
//			}
//		
	
//		spielbrett.getSb();
		System.out.println("lalalallaa so siehts aus");

//		if (this.spieler[0] == this.getActiveSpieler()) {
//			this.playerRotation(this.getActiveSpieler(), this.spieler[1]);
//		} else {
//			this.playerRotation(this.getActiveSpieler(), this.spieler[0]);
//		}

//	} catch (IOException e) {
//		e.printStackTrace();
//	}

}
@Override
public void speichern(Object obj, String name) throws IOException {
	Scanner s = new Scanner(System.in);
	String typ = s.next();
	switch (typ) {
	case ("csv"):
		speichernCSV(name);
		break;
	
	default:
		throw new RuntimeException("Dateityp " + " nicht existent");
	}
}


}
