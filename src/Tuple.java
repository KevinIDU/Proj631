
public class Tuple implements Comparable<Object>{
	char caractere;
	Integer frequence;
	
	public Tuple (Integer f, char c) {
		this.caractere = c;
		this.frequence=f;
	}
	
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
	
	public String toString() {
		return "c:"+this.caractere +" "+"f:"+ this.frequence;
	}
	
	

}
