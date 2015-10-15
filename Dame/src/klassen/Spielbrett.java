package klassen;
/**
 * Klasse SPielbrett
 * @author B2
 *
 */
public class Spielbrett {

		
		 private Spielfeld [][] feld;
		


		 public Spielbrett(){
			 
			 
			 
			 feld= new Spielfeld[12][12];
		

			 for (int i = 0; i < feld.length; i++) {

		            for (int  j =0; j <feld[i].length; j++) {
		            	
		            	
		            	
//		            	feld[i][j]= new Spielfeld();

		    
		                
		            System.out.println(feld[i][j]);	
		            	  
		            }}
		 }
		
	public static void main(String[] args) {
		
		Spielbrett s = new Spielbrett();
		
		System.out.println(s);
	}
		
		
		
		
		
		
		
		
		


}
