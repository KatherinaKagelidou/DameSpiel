package klassen;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class KI implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Spiel spiel;
	private Spieler spieler;
	protected boolean kannLaufen = false;
	protected boolean kannSchlagen = false;
	protected ArrayList<String> datenLaufen = new ArrayList<String>();
	protected ArrayList<String> datenSchlagen = new ArrayList<String>();
	protected ArrayList<Spielfigur> uberprufteFigur=new ArrayList<Spielfigur>();
	protected ArrayList<String> ziel=new ArrayList<String>();
	private ArrayList<Spielfigur> neuFullWeiss=new ArrayList<Spielfigur>();
	private ArrayList<Spielfigur> neuFullSchwarz=new ArrayList<Spielfigur>();
	private boolean binDrinKi=false;

	/**
	 * der Konstruktor der Klasse mit der Komposition zu Spieler
	 * 
	 * @param spieler
	 */
	


	public KI(Spiel spiel) {
//		if(spieler==null){
//			throw new RuntimeException("KI kann nicht ohne Spieler existieren !");
//		}
		this.setSpiel(spiel);
		this.setKannSchlagen(kannSchlagen);
		this.setKannLaufen(kannLaufen);
		
	}
	

	public ArrayList<String> getZiel() {
		return ziel;
	}

	public Spiel getSpiel() {
		return spiel;
	}

	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}

	public boolean getKannLaufen() {
		return kannLaufen;
	}

	public void setKannLaufen(boolean kannLaufen) {
		this.kannLaufen = kannLaufen;
	}

	public boolean getKannSchlagen() {
		return kannSchlagen;
	}

	public void setKannSchlagen(boolean kannSchlagen) {
		this.kannSchlagen = kannSchlagen;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return this.spieler;
	}
	
	public void putArrayKI(){
		if(binDrinKi==false){
			spiel.putArray();
		}
		
		for (Spielfigur each : spiel.getFigurWeiss2()){
			neuFullWeiss.add(each);	
			binDrinKi=true;
		}
		for (Spielfigur each : spiel.getFigurSchwarz2()){
			neuFullSchwarz.add(each);
			binDrinKi=true;
		}
	}
	

	public Spielfigur randomFigur() {
		Spielfigur f=null;

		if(spiel.getSpielerAmZug().getFarbe().equals(FarbEnum.WEISS)){
				int n=(int)(30 * Math.random()) +1;
				f=spiel.getFigurWeiss()[n];
				
		}else if(spiel.getSpielerAmZug().getFarbe().equals(FarbEnum.SCHWARZ)){
				int n=(int)(30 * Math.random()) +1;
				f=spiel.getFigurSchwarz()[n];
		}
		return f;
	}
	
	
	


	//------------------------------LAUFEN--------------------------
	
	public boolean kannLaufen() {
		FarbEnum farbe = spiel.getSpielerAmZug().getFarbe();
		Spielfigur figur=randomFigur();
		 if(spiel.istFigurDrin(figur.getId())==true){
			switch (farbe) {
			case WEISS:
				if (kannLaufenLinksWeiss(figur.getFeld())
						|| kannLaufenRechtsWeiss(figur.getFeld())) {
					kannLaufen=true;
					return true;
					}
				break;
				
			case SCHWARZ:
				if (kannLaufenLinksSchwarz(figur.getFeld())
						|| kannLaufenRechtsSchwarz(figur.getFeld())) {
//					System.out.println("schwarz "+datenLaufen);
					kannLaufen=true;
					return true;
				}
				break;
			}
//			System.out.println("im laufen drin"+datenLaufen);
		}
		return false;
	}

	private boolean kannLaufenRechtsSchwarz(Spielfeld feld) {
		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {
				if (spiel.pruefeOben(feld)) {
					return kannLaufen = false;
				}if(spiel.pruefeID2(feld)){ 
					return kannLaufen = false;
				}else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);
					if ((feld2.getFigur() != null)) {
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
		return kannLaufen;
		}

	private boolean kannLaufenLinksSchwarz(Spielfeld feld) {
		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {
				if (spiel.pruefeOben(feld)) {
					return kannLaufen = false;
				}if(spiel.pruefeID1(feld)){ 
					return kannLaufen = false;
				}else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);
					if ((feld2.getFigur() != null)) {
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
		return kannLaufen;
		}

	private boolean kannLaufenRechtsWeiss(Spielfeld feld) {
		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {
				if (spiel.pruefeUnten(feld)) {
					return kannLaufen = false;
				}if(spiel.pruefeID1(feld)){ 
					return kannLaufen = false;
				}else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);
					if ((feld2.getFigur() != null)) {
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
		return kannLaufen;
		}

	private boolean kannLaufenLinksWeiss(Spielfeld feld) {
		kannLaufen = false;
		if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
			if (feld.getFigur() != null) {
				if (spiel.pruefeUnten(feld)) {
					return kannLaufen = false;
				}if(spiel.pruefeID2(feld)){ 
					return kannLaufen = false;
				
				}else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
					Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);
					if ((feld2.getFigur() != null)) {
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
		return kannLaufen;
		}

    
	//------------------------------SCHLAGEN------------------------
	

		
	
	
	public boolean kannSchlagen() {
        FarbEnum farbe = spiel.getSpielerAmZug().getFarbe();
        Spielfigur figur=randomFigur();

        if(spiel.istFigurDrin(figur.getId())==true){
        	if(uberprufteFigur.size()>=1){
            	
           	 if(uberprufteFigur.contains(figur)){
//           		 System.out.println("FIGUR---"+figur.getId());
           		 for(int i=0;i<uberprufteFigur.size();i++){
    					if(figur==uberprufteFigur.get(i)){
    						uberprufteFigur.remove(uberprufteFigur.get(i));
    					}
    				}
                }
           	 uberprufteFigur.add(figur);
           }else{
           	 uberprufteFigur.add(figur);
           }
        	
        	
          
           switch (farbe) {
           case WEISS:
           	if (kannSchlagenLinksWeiss(figur.getFeld())
           			|| kannSchlagenRechtsWeiss(figur.getFeld())){
           		kannSchlagen=true;
           		return true;
           	}
           	break;
           case SCHWARZ:
           	if (kannSchlagenLinksSchwarz(figur.getFeld())
           			|| kannSchlagenRechtsSchwarz(figur.getFeld())){
           		kannSchlagen=true;
           		return true;
           	}
           	break;
           }
        }
        return false;
    }
	
	public boolean kannSchlagenRechtsSchwarz(Spielfeld feld) {
		
        kannSchlagen = false;
        if (feld.getFarbeFeld().equals(FarbEnum.SCHWARZ)) {
            if (feld.getFigur() != null) {
            
            if (spiel.pruefeID2(feld)) {
                return kannSchlagen = false;
            } else if(spiel.pruefeOben(feld)){
              	 return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
                Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);
                
                if(spiel.pruefeID2(feld2)){
                    kannSchlagen=false;
                }else{
                	if(spiel.pruefeOben(feld2)){
                		kannSchlagen=false;
                	}else
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
                    Spielfeld feld3 = spiel.getNachbar(feld2.getId(), true);
                    
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
            
            if (spiel.pruefeID1(feld)) {
                return kannSchlagen = false;
            } else if(spiel.pruefeOben(feld)){
              	 return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
            
                Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);
                
                if(spiel.pruefeID1(feld2)){
                    kannSchlagen=false;
                }else{
                	if(spiel.pruefeOben(feld2)){
                		kannSchlagen=false;
                	}else
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe().equals(FarbEnum.WEISS))) {
                    Spielfeld feld3 = spiel.getNachbar(feld2.getId(), false);
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
            
            if (spiel.pruefeID1(feld)) {
                return kannSchlagen = false;
            } else if(spiel.pruefeUnten(feld)){
              	 return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
                Spielfeld feld2 = spiel.getNachbar(feld.getId(), true);
                
                if(spiel.pruefeID1(feld2)){
                    kannSchlagen=false;
                }else{
                	if(spiel.pruefeUnten(feld2)){
                		kannSchlagen=false;
                	}else
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe().equals(FarbEnum.SCHWARZ))) {
                    Spielfeld feld3 = spiel.getNachbar(feld2.getId(), true);
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
                
            if (spiel.pruefeID2(feld)) {
                return kannSchlagen = false;
            } else if(spiel.pruefeUnten(feld)){
              	 return kannSchlagen = false;
            } else if (feld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {
                Spielfeld feld2 = spiel.getNachbar(feld.getId(), false);
                
                if(spiel.pruefeID2(feld2)){
                    kannSchlagen=false;
                }else{
                	if(spiel.pruefeUnten(feld2)){
                		kannSchlagen=false;
                	}else
                
                if ((feld2.getFigur() != null)
                        && (feld2.getFigur().getFarbe()
                                .equals(FarbEnum.SCHWARZ))) {
                    Spielfeld feld3 = spiel.getNachbar(feld2.getId(), false);
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


	
//	public abstract String gebeStart();
//	public abstract String gebeZiel();
	public abstract ArrayList<String> startZiel();



}
