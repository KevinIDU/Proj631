
public class Tuple implements Comparable<Object>{
	char caractere;
	Integer frequence;

	/**
	 * Constructeur de mes tuples.
	 * @param f fr�quence d'un caract�re.
	 * @param c caract�re de l'alphabet.
	 */
	
	public Tuple (Integer f, char c) {
		this.caractere = c;
		this.frequence=f;
	}
	/**
	 * Overide de compareTo pour ordonner mes tuples correctement selon l'aparition puis le cract�re ascii.
	 */
	@Override
	public int compareTo(Object o) {
		if( o instanceof Tuple) {
			Tuple t = (Tuple)o;
			
			if(this.frequence<t.frequence) {
				return -1;					
			}
			
			if(this.frequence>t.frequence) {
				return 1;
			}
			
			if(this.frequence.equals(t.frequence)) {
				if((int)this.caractere<(int)t.caractere){
					return -1;				
				}
				if ((int)this.caractere>(int)t.caractere) {
					return 1;
				}
			}
		}
		return 0;
	}	
	
	
	/**
	 * D�finition du toString pour un meilleur affichage.
	 */
	public String toString() {
		return "c:"+this.caractere +" "+"f:"+ this.frequence;
	}
	
	

}
