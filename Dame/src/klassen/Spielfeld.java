package klassen;
/**
 * Klasse Spielfeld
 * @author Brankica
 *
 */
public class Spielfeld {
	private int id;
	private Spielfigur figur;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * @param id ist das Feld auf dem sich die einzelnen Spielfiguren befinden
	 * @param figur ist die Spielfigur die das Spielfeld besitzt
	 */
	public Spielfeld(int id){
		this.setId(id);
		this.setFigur(figur);
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		if(id<0||id>100){
			throw new RuntimeException("ungültiges Spielfeld");
		}
		this.id=id;
	}
	public Spielfigur getFigur(){
		return figur;
	}
	public void setFigur(Spielfigur figur){
		this.figur=figur;
	}
	@Override
	public String toString(){
		return ("Id: "+getId()+
				"Figur: "+getFigur());
	}
}
