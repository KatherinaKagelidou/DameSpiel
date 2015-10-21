package klassen;



/**
 * Klasse SPielbrett
 * 
 * @author B2
 *
 */
public class Spielbrett {


		
		Spielfeld spielfeld;



		Spielbrett sb;
	/**
	 * Konstruktor der Klasse Spielbrett
	 */

	private Spielfeld[][] feld;
	private boolean schwarz=true;

	public Spielbrett() {

		feld = new Spielfeld[12][12];
		getSpielbrett(feld);
	}
	
	
	
	public void getSpielbrett(Spielfeld[][] feld) {

		char ch = 'L';
		char ch1 = 'A';
		int x = 1;
		String str=null;
	

			 for (int i = 0; i <feld.length; i++) {

		            for (int  j =0; j <feld[i].length; j++) {
		            	str=""+ch+x;
		            	x++;
		           
		           
		            	
		            	Spielfeld spielfeld = new Spielfeld(sb, str,true);
		            	spielfeld.setFarbeFeld(FarbEnum.SCHWARZ);
		            	System.out.print(spielfeld);
		            	
		            	
		            	  
		            	  if(x>12){
			            		x=1;
			            		ch--;
			            		if(ch==ch1){
			            			str=""+ch1+x;
			            		
			            			break;
			            			
			            		}
			            		 
		            }
		            	  }
		            }
			 
			 }
	
		
	
	public boolean getFarbe(){
		return schwarz;
	
	}
		
public static void main(String[] args) {

		Spielbrett s = new Spielbrett();

		System.out.println(s);
	}


}
