package klassen;

public interface iBediener {

	public void addSpieler(String name, FarbEnum farbe,KI ki);
	public void starteSpiel();
//	public void FigurAnSpieler(Spieler spieler);
	public void setzeFigurAufPos(Spielfigur sf, String ID);

}
