package klassen;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

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
	private Spielfigur[] figurWeiss = new Spielfigur[31];
	private Spielfigur[] figurSchwarz = new Spielfigur[31];
	private ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>();
	private boolean istZugbeginn = true;
	private boolean kannSchlagen;
	private boolean rechts = false;

	private static final long SerialVersion = 1l;
	private static iDatenzugriff daten;

	private PrintWriter pw;

	public Spiel() {

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

				// if
				// (this.spielbrett.getFelder()[i][j].getFarbeFeld().equals(FarbEnum.WEISS))
				// {

				// if (spielFigur.getFarbe().equals(FarbEnum.SCHWARZ)) {
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
						// this.spielbrett.getFelder()[i][j].setFigur(spielFigur);
						// this.Spieler[0].getFigurArray()[i].setSpielfeld(this.spielbrett.getFelder()[i][j]);
						// spielbrett.getFelder()[i][j].setFigur(this.Spieler[1].getFigurArray()[v]);
						// v++;
						figur = new Spielfigur(spielbrett.getFeld(str),
								FarbEnum.SCHWARZ);
						//
						// if (count == 30) {
						// spieler2.getFigurArray()[count] = figur;
						// figur.setId(count);
						// count = 0;
						// } else {
						// spieler2.getFigurArray()[count] = figur;
						// figur.setId(count);
						// }
						//
						// }
						// count++;
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
									FarbEnum.WEISS);

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

	// @Override
	// public void setzeFigurAufPos(Spielfigur sf, String ID) {
	//
	// String getID = ID;
	// spielfeld.setId(getID);
	// }

	@Override
	public String toString() {
		String s = "";
		s += "Es sind folgende Spieler im Spiel: \n";
		s += "\n";
		if (spieler1 != null) {
			s += spieler1.toString() + " | ";
		}
		if (spieler2 != null) {
			s += spieler2.toString();
		}
		return s;
	}

	@Override
	public void werdeDame(Spielfigur figur) {

		if (figur.getId().contains("W")) {
			if (figur.getPosition().contains("A")) {

				istDame = true;
			}

		}
		if (figur.getId().contains("S")) {
			if (figur.getPosition().contains("L")) {
				istDame = true;

			}

		}

	}

	@Override
	public void schlagen(Spielfeld aktPos, Spielfeld gegnerPos,
			Spielfigur spielfigur) {

		aktPos.removeSpielfigur(aktPos.getFigur());
		gegnerPos.setFigur(spielfigur);
		String aktFeld = aktPos.getId();

		if (spielfigur.getFarbe().equals(FarbEnum.SCHWARZ)) {

			if (kannSchlagenRechtsSchwarz(aktPos)) {

				Spielfeld zielPos = this.getNachbar(gegnerPos.getId(), rechts);
				// setzt figur, die schlägt auf neue pos
				moveFigur(spielfigur, aktPos, zielPos);
				// löscht geschlagene figur
				for (int i = 0; i < spielbrett.getFelder().length; i++) {
					for (int j = 0; j < spielbrett.getFelder()[i].length;) {
						spielbrett.getFelder()[i][j].setFigur(null);
						gegnerPos.removeSpielfigur(gegnerPos.getFigur());
					}
				}

			} else if (kannSchlagenLinksSchwarz(aktPos)) {

				Spielfeld zielPos = this.getNachbar(gegnerPos.getId(), !rechts);

				moveFigur(spielfigur, aktPos, zielPos);

				for (int i = 0; i < spielbrett.getFelder().length; i++) {
					for (int j = 0; j < spielbrett.getFelder()[i].length;) {
						spielbrett.getFelder()[i][j].setFigur(null);
						gegnerPos.removeSpielfigur(gegnerPos.getFigur());
					}
				}
			}
		} else {

			if (kannSchlagenRechtsWeiss(aktPos)) {

				Spielfeld zielPos = this.getNachbar(gegnerPos.getId(), rechts);

				moveFigur(spielfigur, aktPos, zielPos);

				for (int i = 0; i < spielbrett.getFelder().length; i++) {
					for (int j = 0; j < spielbrett.getFelder()[i].length;) {
						spielbrett.getFelder()[i][j].setFigur(null);
						gegnerPos.removeSpielfigur(gegnerPos.getFigur());
					}
				}

			} else if (kannSchlagenLinksWeiss(aktPos)) {
				Spielfeld zielPos = this.getNachbar(gegnerPos.getId(), !rechts);

				moveFigur(spielfigur, aktPos, zielPos);

				for (int i = 0; i < spielbrett.getFelder().length; i++) {
					for (int j = 0; j < spielbrett.getFelder()[i].length;) {
						spielbrett.getFelder()[i][j].setFigur(null);
						gegnerPos.removeSpielfigur(gegnerPos.getFigur());
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
		// Spielfigur f = null;
		// if (figurId.startsWith("b")) {
		// for (int i = 1; i < this.figurSchwarz.length; i++) {
		// if (this.figurSchwarz[i].getId().equals(figurId)) {
		// f = this.figurSchwarz[i];
		// System.out.println(f);
		// }
		// }
		// } else if (figurId.startsWith("w")) {
		// for (int i = 1; i < this.figurWeiss.length; i++) {
		// if (this.figurWeiss[i].getId().equals(figurId)) {
		// f = this.figurWeiss[i];
		// }
		// }
		//
		// }
		// return f;
	}

	private void moveFigur(Spielfigur figur, Spielfeld aktFeld,
			Spielfeld zielFeld) {
		for (int i = 0; i < spielbrett.getFelder().length; i++) {
			for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
				// sucht aktFeld
				if (spielbrett.getFelder()[i][j].equals(aktFeld)) {
					// löscht figur auf aktFeld
					spielbrett.getFelder()[i][j].setFigur(null);
					aktFeld.removeSpielfigur(figur);
					// setzt figur auf zielfeld
					figur.setSpielfeld(spielbrett.getFeld(zielFeld.getId()));
					zielFeld.setFigur(figur);
				}

			}
		}
	}

	@Override
	public void laufen(String aktPos, String zielPos, String figurId) {
		spielbrett.getPositionen().clear();
		Spielfigur figur = this.gebeFigur(figurId);
		Spielfeld aktFeld = this.gebeFeld(aktPos);
		Spielfeld zielFeld = this.gebeFeld(zielPos);

		if (figur.getId().equals(aktFeld.getFigur().getId())) {
			figur = aktFeld.getFigur();
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
							System.out.println("Figur " + figur);
							System.out.println(aktFeld);
						}

					} else {
						this.rechts = false;
						Spielfeld f = getNachbar(aktPos, false);
						if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
							moveFigur(figur, aktFeld, zielFeld);
							System.out.println("Figur " + figur);
							System.out.println(aktFeld);
						}
					}
				}
			} else {
				System.out.println(spielerAmZug.getName()
						+ " du musst schlagen!!!");
				schlagen(aktFeld, zielFeld, figur);

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
							System.out.println("Figur " + figur);
							System.out.println(aktFeld);
						}

					} else {
						this.rechts = false;
						Spielfeld f = getNachbar(aktPos, false);
						if (f.equals(zielFeld) && zielFeld.getFigur() == null) {
							moveFigur(figur, aktFeld, zielFeld);
							System.out.println("Figur " + figur);
							System.out.println(aktFeld);
						}
					}
				}

			} else {
				System.out.println(spielerAmZug.getName()
						+ " du musst schagen!!!");
				schlagen(aktFeld, zielFeld, figur);
			}

		}

		zugBeenden();

	}

	@Override
	public void zugBeenden() {
		if (this.spielerAmZug.equals(spieler1)) {
			SpielerAmZug(spieler2);
			System.out.println(" Spieler " + spielerAmZug
					+ " ist an der Reihe!");
		} else {
			SpielerAmZug(spieler1);
			System.out.println(" Spieler " + spielerAmZug
					+ " ist an der Reihe!");
		}

	}

	public Spielfeld getNachbar(String feld, boolean rechts) {
		Spielfeld fe = null;
		Spielfeld spielfeld = this.gebeFeld(feld);
		Spielfigur figur = spielfeld.getFigur();

		if (figur.getFarbe().equals(FarbEnum.SCHWARZ)) {
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
			if (figur.getFarbe().equals(FarbEnum.WEISS)) {
				if (rechts) {
					for (int i = spielbrett.getFelder().length - 1; i < spielbrett
							.getFelder().length; i--) {
						for (int j = spielbrett.getFelder().length - 1; j < spielbrett
								.getFelder()[i].length; j--) {
							if (spielbrett.getFelder()[i][j].getId().equals(
									feld)) {
								fe = spielbrett.getFelder()[i - 1][j - 1];
								return fe;
							}
						}
					}
				} else {
					// laufe links weiss
					for (int i = spielbrett.getFelder().length - 1; i < spielbrett
							.getFelder().length; i--) {
						for (int j = spielbrett.getFelder().length - 1; j < spielbrett
								.getFelder()[i].length; j--) {
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

	public void laufeDame(String aktPos, String zielPos, String figurId) {

		Spielfeld aktFeld = this.gebeFeld(aktPos);
		Spielfeld zielFeld = this.gebeFeld(zielPos);
		Spielfigur figur = this.gebeFigur(figurId);

		spielbrett.Umwandler(aktPos);
		spielbrett.Umwandler(zielPos);

		// ObenRechts

		for (int i = 0; i < spielbrett.getFelder().length; i++) {
			for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

				if (figur.getFarbe().equals(FarbEnum.SCHWARZ)) {
					if ((this.spielbrett.getPositionen().get(0) < this.spielbrett
							.getPositionen().get(2))
							&& (this.spielbrett.getPositionen().get(1) < this.spielbrett
									.getPositionen().get(3))) {
						while (aktFeld.getId() != zielPos) {
							aktFeld = spielbrett.getFelder()[i++][j++];
						}
						aktFeld.removeSpielfigur(figur);
						figur.setSpielfeld(zielFeld);
						aktFeld.setFigur(figur);
					}
				}

			}
		}
	}

	public boolean kannSchlagenRechtsSchwarz(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			while (feld.getFigur() == null) {
				continue;
			}
			if (pruefeID2(feld)) {
				return kannSchlagen = false;
			} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
				Spielfeld feld2 = getNachbar(feld.getId(), rechts);
				if ((feld2.getFigur() != null)
						&& (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
					Spielfeld feld3 = getNachbar(feld2.getId(), rechts);
					if (feld3 == null) {
						kannSchlagen = true;
					} else {
						kannSchlagen = false;
					}
				} else {
					kannSchlagen = false;
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenLinksSchwarz(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			while (feld.getFigur() == null) {
				continue;
			}
			if (pruefeID1(feld)) {
				return kannSchlagen = false;
			} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
				Spielfeld feld2 = getNachbar(feld.getId(), !rechts);
				if ((feld2.getFigur() != null)
						&& (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
					Spielfeld feld3 = getNachbar(feld2.getId(), !rechts);
					if (feld3 == null) {
						kannSchlagen = true;
					} else {
						kannSchlagen = false;
					}
				} else {
					kannSchlagen = false;
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenRechtsWeiss(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			while (feld.getFigur() == null) {
				continue;
			}
			if (pruefeID1(feld)) {
				return kannSchlagen = false;
			} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
				Spielfeld feld2 = getNachbar(feld.getId(), rechts);
				if ((feld2.getFigur() != null)
						&& (feld2.getFigur().getFarbe()
								.equals(FarbEnum.SCHWARZ))) {
					Spielfeld feld3 = getNachbar(feld2.getId(), rechts);
					if (feld3 == null) {
						kannSchlagen = true;
					} else {
						kannSchlagen = false;
					}
				} else {
					kannSchlagen = false;
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenLinksWeiss(Spielfeld feld) {

		kannSchlagen = false;

		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			while (feld.getFigur() == null) {
				continue;
			}
			if (pruefeID2(feld)) {
				return kannSchlagen = false;
			} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
				Spielfeld feld2 = getNachbar(feld.getId(), !rechts);
				if ((feld2.getFigur() != null)
						&& (feld2.getFigur().getFarbe()
								.equals(FarbEnum.SCHWARZ))) {
					Spielfeld feld3 = getNachbar(feld2.getId(), !rechts);
					if (feld3 == null) {
						kannSchlagen = true;
					} else {
						kannSchlagen = false;
					}
				} else {
					kannSchlagen = false;
				}
			}
		}
		return kannSchlagen;

	}

	private boolean kannSchlagen() {
		FarbEnum farbe = spielerAmZug.getFarbe();
		switch (farbe) {
		case WEISS:
			for (int i = spielbrett.getFelder().length - 1; i < spielbrett
					.getFelder().length; i--) {
				for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {
					
					if((!pruefeID1(spielbrett.getFelder()[i][j])
							&& (!pruefeUnten(spielbrett.getFelder()[i][j])))){
					if (kannSchlagenLinksWeiss(spielbrett.getFelder()[i][j])){
					// || kannSchlagenRechtsWeiss(spielbrett.getFelder()[i][j]))
					// {
					
						return true;
					}}

				}
			}
			break;
		case SCHWARZ:
			for (int i = 0; i < spielbrett.getFelder().length; i++) {
				for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
					if (kannSchlagenLinksSchwarz(spielbrett.getFelder()[i][j])
							|| kannSchlagenRechtsSchwarz(spielbrett.getFelder()[i][j])) {
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

	public boolean getZugBeginn() {
		return istZugbeginn;
	}

	public void merkeBeginn() {
		istZugbeginn = false;
	}

	public void laufenDame(String aktPos, String zielPos, String figurId) {
		Spielfeld feld = null;

		Spielfigur figur = this.gebeFigur(figurId);
		Spielfeld aktFeld = this.gebeFeld(aktPos);
		Spielfeld zielFeld = this.gebeFeld(zielPos);

		if (figur.getId().equals(aktFeld.getFigur().getId())) {
			figur = aktFeld.getFigur();
		}
		spielbrett.Umwandler(aktPos);
		spielbrett.Umwandler(zielPos);

		if (istDame == true) {
			for (int i = 0; i < spielbrett.getFelder().length; i++) {
				for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
					while (j > 0 && j > 13) {
						if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
							feld = spielbrett.getFelder()[i + 1][j + 1];
						}
						if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
							feld = spielbrett.getFelder()[i + 1][j - 1];
						}
						if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
							feld = spielbrett.getFelder()[i - 1][j + 1];
						}
						if (spielbrett.getFelder()[i][j].getId().equals(feld)) {
							feld = spielbrett.getFelder()[i - 1][j - 1];
						}

					}

				}

			}
		}

	}
	public boolean pruefeOben(Spielfeld feld) {
		boolean a = false;
		switch (feld.getId()) {
		case "L2":
			a = true;
			break;
		case "L4":
			a = true;
			break;
		case "L6":
			a = true;
			break;
		case "L8":
			a = true;
			break;
		case "L10":
			a = true;
			break;
		case "L12":
			a = true;
			break;
		default:
			return false;
		}
		return a;
	}
	
	public boolean pruefeUnten(Spielfeld feld) {
		boolean a = false;
		switch (feld.getId()) {
		case "A1":
			a = true;
			break;
		case "A3":
			a = true;
			break;
		case "A5":
			a = true;
			break;
		case "A7":
			a = true;
			break;
		case "A9":
			a = true;
			break;
		case "A11":
			a = true;
			break;
		default:
			return false;
		}
		return a;
	}



	public boolean pruefeID1(Spielfeld feld) {
		boolean a = false;
		switch (feld.getId()) {
		case "A1":
			a = true;
			break;
		case "C1":
			a = true;
			break;
		case "E1":
			a = true;
			break;
		case "G1":
			a = true;
			break;
		case "I1":
			a = true;
			break;
		case "K1":
			a = true;
			break;
		default:
			return false;
		}
		return a;
	}

	public boolean pruefeID2(Spielfeld feld) {
		boolean a = false;
		switch (feld.getId()) {
		case "B12":
			a = true;
			break;
		case "D12":
			a = true;
			break;
		case "F12":
			a = true;
			break;
		case "H12":
			a = true;
			break;
		case "J12":
			a = true;
			break;
		case "L12":
			a = true;
			break;
		default:
			return false;
		}
		return a;
	}

	/**
	 * Speichert die aktuelle Belegung des Spielbretts in CSV-Notation.
	 * Speicherort: savegame/savegame.csv
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
						schwarz, i, j));

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
					+ spieler1.getFarbe() + " \n" + "Spieler: "
					+ spieler2.getName() + " mit der Farbe "
					+ spieler2.getFarbe() + "";
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

	@Override
	public Spiel ladenCSV(String s) {
		try {
			daten = new DatenzugriffCSV();
			Properties p = new Properties();
			daten.oeffnen(p);
			Spiel spiel = (Spiel) daten.lesen();
			System.out.println("Das Spiel wird geladen.");
			daten.schliessen(p);
			return spiel;
		} catch (Exception e) {
			System.err.println("Laden CSV fehlgeschlagen!");
			return null;
		}

	}

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

	/**
	 * laden serial return spiel
	 */
	@Override
	public Spiel ladenSerial(String s) {
		try {
			daten = new DatenzugriffSerialisiert();
			Properties p = new Properties();
			daten.oeffnen(p);
			Spiel spiel = (Spiel) daten.lesen();
			System.out.println("Das Spiel wurde erfolgreich geladen.");
			daten.schliessen(p);
			return spiel;
		} catch (Exception e) {
			System.out.println("Laden serialisiert fehlgeschlagen!");
			return null;
		}

	}

}