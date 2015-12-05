package klassen;

import gui.GuiSpielbrett;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

abstract class KI implements Serializable {
	private static final long serialVersionUID = 1L;

	private Spiel spiel;
	private Spieler spieler;
	private Spielfigur figur;
	private Spielbrett spielbrett;
	private boolean kannLaufen = false;
	private boolean kannSchlagen = false;
	private ArrayList<String> datenLaufen = new ArrayList<String>();
	private ArrayList<String> datenSchlagen = new ArrayList<String>();
	private GuiSpielbrett gui;
	private FarbEnum farbe;
	private Spielfeld feld;
	private boolean willSchlagenKI = false;

	public KI() {

	}

	/**
	 * der Konstruktor der Klasse mit der Komposition zu Spieler
	 * 
	 * @param spieler
	 */

	public KI(Spiel spiel) {
		this.spiel = spiel;

	}

	/**
	 * Konstruktor der abstakten Klasse KI
	 */

	public KI(Spieler spieler, Spielbrett spielbrett) {

		this.spieler = spieler;
		this.spielbrett = spielbrett;
	}

	public Spieler getSpieler() {
		return this.spieler;
	}

	// KI-denken

	public void KIdenken() {

	}

	//entweder die methode zum generieren der figuren in random oder in der 
	//methode laufen selbst implementiert -noch nichts getestet  und was sinvoller ist 
	public void generateFigurToInt(String s) {
		Random random = new Random();

		for (int i = 0; i < spiel.getFigurWeiss().length; i++) {

			int n = random.nextInt(i);

			spiel.getFigurWeiss()[n] = figur;

			s = figur.getId();
		}
	}

	// LAUFEN

	public boolean kannLaufen() {
		FarbEnum farbe = spiel.getSpielerAmZug().getFarbe();
		switch (farbe) {
		case WEISS:
			for (int i = spielbrett.getFelder().length - 1; i >= 0; i--) {

				for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {

					if (kannLaufenLinksWeiss(spielbrett.getFelder()[i][j])
							|| kannLaufenRechtsWeiss(spielbrett.getFelder()[i][j])) {

						return true;
					}

				}
			}
			break;
		case SCHWARZ:
			for (int i = 0; i < spielbrett.getFelder().length; i++) {
				for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

					if (kannLaufenLinksSchwarz(spielbrett.getFelder()[i][j])
							|| kannLaufenRechtsSchwarz(spielbrett.getFelder()[i][j])) {
						return true;
					}
				}
			}
			break;
		}
		return false;

	}

	private boolean kannLaufenRechtsSchwarz(Spielfeld feld) {

		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID1(feld)) {
					return kannLaufen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {

					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);

					if (spiel.pruefeID1(feld2)) {
						kannLaufen = false;
					} else {
						if (spiel.pruefeOben(feld2)) {
							kannLaufen = false;
						} else if ((feld2.getFigur() != null)) {
							kannLaufen = false;
						}

						if (feld2.getFigur() == null) {
							kannLaufen = true;
							datenLaufen.add(feld.getId());
							datenLaufen.add(feld2.getId());

						}
					}
				}
			}
		}
		return kannLaufen;

	}

	private boolean kannLaufenLinksSchwarz(Spielfeld feld) {

		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID1(feld)) {
					return kannLaufen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {

					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);

					if (spiel.pruefeID1(feld2)) {
						kannLaufen = false;
					} else {
						if (spiel.pruefeOben(feld2)) {
							kannLaufen = false;
						} else if ((feld2.getFigur() != null)) {
							kannLaufen = false;
						}
						if (feld2.getFigur() == null) {
							kannLaufen = true;
							datenLaufen.add(feld.getId());
							datenLaufen.add(feld2.getId());

						}
					}
				}
			}
		}
		return kannLaufen;

	}

	private boolean kannLaufenRechtsWeiss(Spielfeld feld) {

		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID1(feld)) {
					return kannLaufen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);

					if (spiel.pruefeID1(feld2)) {
						kannLaufen = false;
					} else {
						if (spiel.pruefeUnten(feld2)) {
							kannLaufen = false;
						} else

						if ((feld2.getFigur() != null)) {
							kannLaufen = false;
						} else if (feld2.getFigur() == null) {
							kannLaufen = true;
							datenLaufen.add(feld.getId());
							datenLaufen.add(feld2.getId());

						}
					}
				}
			}
		}
		return kannLaufen;

	}

	private boolean kannLaufenLinksWeiss(Spielfeld feld) {

		kannLaufen = false;

		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID2(feld)) {
					return kannLaufen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);

					if (spiel.pruefeID2(feld2)) {
						kannLaufen = false;
					} else {
						if (spiel.pruefeUnten(feld2)) {
							kannLaufen = false;
						} else

						if ((feld2.getFigur() != null)) {
							kannLaufen = false;

						}

						else if (feld2.getFigur() == null) {
							kannLaufen = true;
							datenLaufen.add(feld.getId());
							datenLaufen.add(feld2.getId());

						}
					}
				}
			}
		}
		return kannLaufen;

	}

	// SCHLAGEN

	public boolean kannSchlagenRechtsSchwarz(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID2(feld)) {
					return kannSchlagen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);

					if (spiel.pruefeID2(feld2)) {
						kannSchlagen = false;
					} else {
						if (spiel.pruefeOben(feld2)) {
							kannSchlagen = false;
						} else

						if ((feld2.getFigur() != null)
								&& (feld2.getFigur().getFarbe()
										.equals(FarbEnum.WEISS))) {
							Spielfeld feld3 = spiel.getNachbar(feld2.getId(),
									true);

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
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenLinksSchwarz(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID1(feld)) {
					return kannSchlagen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {

					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);

					if (spiel.pruefeID1(feld2)) {
						kannSchlagen = false;
					} else {
						if (spiel.pruefeOben(feld2)) {
							kannSchlagen = false;
						} else

						if ((feld2.getFigur() != null)
								&& (feld2.getFigur().getFarbe()
										.equals(FarbEnum.WEISS))) {
							Spielfeld feld3 = spiel.getNachbar(feld2.getId(),
									false);
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
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenRechtsWeiss(Spielfeld feld) {

		kannSchlagen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID1(feld)) {
					return kannSchlagen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);

					if (spiel.pruefeID1(feld2)) {
						kannSchlagen = false;
					} else {
						if (spiel.pruefeUnten(feld2)) {
							kannSchlagen = false;
						} else

						if ((feld2.getFigur() != null)
								&& (feld2.getFigur().getFarbe()
										.equals(FarbEnum.SCHWARZ))) {
							Spielfeld feld3 = spiel.getNachbar(feld2.getId(),
									true);
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
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagenLinksWeiss(Spielfeld feld) {

		kannSchlagen = false;

		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {

				if (spiel.pruefeID2(feld)) {
					return kannSchlagen = false;
				} else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);

					if (spiel.pruefeID2(feld2)) {
						kannSchlagen = false;
					} else {
						if (spiel.pruefeUnten(feld2)) {
							kannSchlagen = false;
						} else

						if ((feld2.getFigur() != null)
								&& (feld2.getFigur().getFarbe()
										.equals(FarbEnum.SCHWARZ))) {
							Spielfeld feld3 = spiel.getNachbar(feld2.getId(),
									false);
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
				}
			}
		}
		return kannSchlagen;

	}

	public boolean kannSchlagen() {
		FarbEnum farbe = spiel.getSpielerAmZug().getFarbe();
		switch (farbe) {
		case WEISS:
			for (int i = spielbrett.getFelder().length - 1; i >= 0; i--) {

				for (int j = spielbrett.getFelder().length - 1; j >= 0; j--) {

					if (kannSchlagenLinksWeiss(spielbrett.getFelder()[i][j])
							|| kannSchlagenRechtsWeiss(spielbrett.getFelder()[i][j])) {

						return true;
					}

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

	//laufMethode da als parameter keine randomFigur übergeben werden kann wird auch keine übergeben
	//beim ausführen kommt immer eine nullppointer exception bei der ersten for schleife
	public void laufKI() {

//		String randomFigur = null;
//		String zielPos = null;
//
//		for (int i = 0; i < spielbrett.getFelder().length; i++) {
//			for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
//
//				randomFigur = (spielbrett.getFelder()[i][j].getFigur().getId());
//				// Spielfeld feld = spielbrett.getFelder()[i][j];
//			}
//		}
//
//		Spielfeld aktFeld = spiel.gebeFeld(randomFigur);
//
//		Spielfeld zielFeld = spiel.gebeFeld(zielPos);
//		Spielfigur figur = spiel.gebeFigur(aktFeld.getFigur().getId());
//
//		if (spiel.getSpielerAmZug().equals(FarbEnum.WEISS)) {
//			if (aktFeld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
//
//				spielbrett.Umwandler(randomFigur);
//				spielbrett.Umwandler(zielPos);
//				System.out.println("Random Figur " + randomFigur
//						+ "--------------------------------");
//			}
//		}
	}

}
