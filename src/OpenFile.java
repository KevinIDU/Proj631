import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class OpenFile  {
	String txt;
	TreeSet<Tuple> tuples = new TreeSet<Tuple>();
	ArrayList<Character> alphabet = new ArrayList<Character>();
	ArrayList<Integer> frequence = new ArrayList<Integer>();
	ArrayList<Integer> new_frequence = new ArrayList<Integer>();
	ArrayList<Character> new_alphabet = new ArrayList<Character>();
	ArrayList<Integer> f3 = new ArrayList<Integer>();
	ArrayList<Character> c3 = new ArrayList<Character>();
	ArrayList<noeud> Lnoeud = new ArrayList<noeud>();
	
	public OpenFile(String fichier) {
		this.txt=this.open(fichier);
		
	}
	
	
	public String getTxt() {
		return txt;
	}


	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}


	public ArrayList<Integer> getFrequence() {
		return frequence;
	}

	public ArrayList<Character> getNewAlphabet() {
		return new_alphabet;
	}


	public ArrayList<Integer> getNewFrequence() {
		return new_frequence;
	}

	public String open(String adresse) {
		String texte = " ";
		  try{
			   InputStream ips=new FileInputStream(adresse); 
			   InputStreamReader ipsr=new InputStreamReader(ips);
			   BufferedReader br=new BufferedReader(ipsr);
			   String ligne;
			   
			   while ((ligne=br.readLine())!=null){
				   texte += ligne;
			   }
			   br.close(); 
			   
			  }  
			  catch (Exception e){
			   System.out.println(e.toString());
			  }
		  return texte;
		  
	}
	
	public void defAlphFreq() {
		for (int i = 0; i<this.txt.length(); i++)
			{
				if (this.alphabet.contains(this.txt.charAt(i)) == false)
					{
					 	Character str = this.txt.charAt(i);
					 	this.alphabet.add(str);
					 	this.new_alphabet.add(str);
					 	this.frequence.add(1);
					 	this.new_frequence.add(1);
					 	this.c3.add(str);
					 	this.f3.add(1);
					} 
				else
					{
					 	int rang = alphabet.indexOf(this.txt.charAt(i));
					 	this.frequence.set(rang, this.frequence.get(rang)+1);
					 	this.new_frequence.set(rang, this.new_frequence.get(rang)+1);
					 	this.f3.set(rang, this.new_frequence.get(rang)+1);
					}		 						 
			 }
	}

	
	public int  minFreque() {
		int res = this.frequence.get(0);
		for(int j = 0; j<this.frequence.size(); j++){
		   if(this.frequence.get(j) < res)
		      res=this.frequence.get(j);
		   }
		return res;
	}
	

	
	public ArrayList<Character> ordAlph(ArrayList<Character> alphOrd){
		if (this.alphabet.isEmpty()) {
			 return alphOrd;
		}
		int min_freq = minFreque();
		for(int i = 0; i<this.frequence.size(); i++) {
			if(this.frequence.get(i) == min_freq) {
				alphOrd.add(this.alphabet.get(i));
				this.frequence.remove(i);
				this.alphabet.remove(i);
				
			}
		}
		return ordAlph(alphOrd);
	}
	
	public ArrayList<Integer> ordFreq(){
		Collections.sort(this.new_frequence);
		return this.new_frequence;
	}
	
	public void defTreeSet() {
		for(int i =0; i<this.f3.size(); i++) {
			Tuple t =new Tuple(this.f3.get(i),this.c3.get(i));
			this.tuples.add(t);
			}
	}
	
	public ArrayList<noeud> defNoeud(){
		TreeSet<Tuple> tampon = (TreeSet<Tuple>)this.tuples.clone(); 
		while(tampon.size()>0){
			int f= tampon.first().frequence;
			char c = tampon.first().caractere;
			tampon.remove(tampon.first());
			this.Lnoeud.add(new noeud(f,c,null,null,""));
		}
		return this.Lnoeud;
	}
	
	public noeud defArbre() {		
		TreeSet<noeud> tampon = new TreeSet<noeud>();
		for (noeud n: this.Lnoeud) {
			tampon.add(n);
		}
		while(tampon.size()>1) {
			noeud n1 = tampon.first();
			tampon.remove(tampon.first());
			noeud n2 = tampon.first();
			tampon.remove(tampon.first());
			tampon.add(new noeud(n1.getFrequence()+n2.getFrequence(),'£',n1,n2, ""));
		}
		return tampon.first();
	}
	

		
	
	
	
	
	
	
	
	
}
		

	

