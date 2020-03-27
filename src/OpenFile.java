import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class OpenFile  {
	String txt;
	TreeSet<Tuple> tuples = new TreeSet<Tuple>();
	ArrayList<Character> alphabet = new ArrayList<Character>();
	ArrayList<Integer> frequence = new ArrayList<Integer>();
	ArrayList<Integer> newFrequence = new ArrayList<Integer>();
	ArrayList<Character> new_alphabet = new ArrayList<Character>();
	ArrayList<Integer> f3 = new ArrayList<Integer>();
	ArrayList<Character> c3 = new ArrayList<Character>();
	ArrayList<noeud> Lnoeud = new ArrayList<noeud>();
	HashMap<Character, String> dico = new HashMap<Character, String >();
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
		return newFrequence;
	}
	//ouverture du fichier texte .txt
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
	// création de 3fois la même liste parce que je l'écrase dans d'autre fonction.
	// optimisation possible je pense avec le .clone()
	public void defAlphFreq() {
		for (int i = 0; i<this.txt.length(); i++)
			{
				if (this.alphabet.contains(this.txt.charAt(i)) == false)
					{
					 	Character str = this.txt.charAt(i);
					 	this.alphabet.add(str);
					 	this.new_alphabet.add(str);
					 	this.frequence.add(1);
					 	this.newFrequence.add(1);
					 	this.c3.add(str);
					 	this.f3.add(1);
					} 
				else
					{
					 	int rang = alphabet.indexOf(this.txt.charAt(i));
					 	this.frequence.set(rang, this.frequence.get(rang)+1);
					 	this.newFrequence.set(rang, this.newFrequence.get(rang)+1);
					 	this.f3.set(rang, this.newFrequence.get(rang)+1);
					}		 						 
			 }
	}

	// retourne la frequence minimum
	public int  minFreque() {
		int res = this.frequence.get(0);
		for(int j = 0; j<this.frequence.size(); j++){
		   if(this.frequence.get(j) < res)
		      res=this.frequence.get(j);
		   }
		return res;
	}
	

	//retourne la liste de caracter ordonnée par la fréquence associé.
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
	// trie la liste de fréquence dans l'ordre croissant.
	public ArrayList<Integer> ordFreq(){
		Collections.sort(this.newFrequence);
		return this.newFrequence;
	}
	
	//création d'un treeset pour associé character et fréquence et etre trié par frequence et ascii
	public void defTreeSet() {
		for(int i =0; i<this.f3.size(); i++) {
			Tuple t =new Tuple(this.f3.get(i),this.c3.get(i));
			this.tuples.add(t);
			}
	}
	//Création d'une liste de noeud ou chaque noeud à sa fréquence et son caractere associé et un code bianire  par défaut
	public ArrayList<noeud> defNoeud(){
		TreeSet<Tuple> tampon = (TreeSet<Tuple>)this.tuples.clone(); 
		while(tampon.size()>0){
			int f= tampon.first().frequence;
			char c = tampon.first().caractere;
			tampon.remove(tampon.first());
			this.Lnoeud.add(new noeud(f,c,null,null,"")); // (fréquence, caractere, fils gauche, fils droit, code binaire)
		}
		return this.Lnoeud;
	}
	//création de l'arbre qui est en faite un noeud avec des fils qui ont des fils etc.
	// Problème : ajout dans tampon sans être retrié après : à modifier
	public noeud defArbre() {	
		ArrayList<noeud> tampon =(ArrayList<noeud>) Lnoeud.clone();
		while(tampon.size()>1) {
			noeud n1 = tampon.get(0);
			tampon.remove(tampon.get(0));
			noeud n2 = tampon.get(0);
			tampon.remove(tampon.get(0));
			noeud nadd = new noeud(n1.getFrequence()+n2.getFrequence(),'£',n1,n2, "");
			tampon.add(nadd);
			this.triNoeuds(tampon);
			
		}
		return tampon.get(0);
	}
	// tri de ma liste de noeud, inspiré d'un tri à bulle trouvé sur internet 
	public void triNoeuds(ArrayList<noeud> list) {
		for (int i= 1; i<list.size(); i++) {
			int freq_now = list.get(i).getFrequence();
			char caract_now = list.get(i).getCarac();
			noeud fd_now = list.get(i).getFdroit();
			noeud fg_now = list.get(i).getFgauche();
			String codebin_now = list.get(i).getCodeb();
			
			int j=i;
			while(j>0 && list.get(j-1).getFrequence()>freq_now) {
				list.get(j).setFrequence(list.get(j-1).getFrequence());
				list.get(j).setCarac(list.get(j-1).getCarac());
				list.get(j).setFdroit(list.get(j-1).getFdroit());
				list.get(j).setFgauche(list.get(j-1).getFgauche());
				list.get(j).setCodeb(list.get(j-1).getCodeb());
				j=j-1;		
				
			}
			list.get(j).setFrequence(freq_now);
			list.get(j).setCarac(caract_now);
			list.get(j).setFdroit(fd_now);
			list.get(j).setFgauche(fg_now);
			list.get(j).setCodeb(codebin_now);
		}
	}
	// parcours en profondeur de mon arbre que je stock dans un hashmap
	public void parcourir(noeud depart, String codeBin){
		
		if (depart.estFeuile()) {
			this.dico.put(depart.getCarac(),codeBin);
		}
		if (depart.getFgauche() != null) {
			if (depart.getCodeb() =="") {
				this.parcourir(depart.getFgauche(), codeBin + "0");
			} 
		}
		if (depart.getFdroit()!= null) {
			if(depart.getCodeb()=="") {
				this.parcourir(depart.getFdroit(), codeBin + "1");
			}
		}
	
	}
	
	//crée le fichier _comp.bin avec les format demandé
	public void writeFileAlice() {
		try {
			File monFichier = new File("Alice_comp.bin");
			if(monFichier.exists()) {
				System.out.println("Fichier Alice_comp.bin existe deja");
			}else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Alice_comp.bin")));
				for (int i= 0; i<this.txt.length(); i++) {
					char carac = this.txt.charAt(i);
					for(char key : dico.keySet()) {
						if(key == carac) {
							writer.write(dico.get(key));
						}
					}
				}
			
			writer.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// créer le fichier _freq.txt avec le format demandé
	public void writeFileFreq() {
		try {
			//String name = this.nomFichier(adresse); //adresse en param // ?
			File monFichier = new File("Alice_freq.txt");
			if(monFichier.exists()) {
				System.out.println("Fichier Alice_freq.txt existe deja");
			}else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Alice_freq.txt")));
				writer.write(""+this.nbCarac() + "\n");
				for (Tuple t: this.tuples) {
					writer.write(""+t.caractere+" "+t.frequence+"\n");
				}
				writer.close();
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// calcule le nombre de caractere que l'alphabet possede
	public int nbCarac() {
		int nbCarac =0;
		for(int i =0; i<this.c3.size(); i++) {
			nbCarac = nbCarac+1;
		}
		return nbCarac;
	}

	//renvoie le nom du fichier sans l'extension
	public String nomFichier(String adresse) {
		String nom =adresse.substring(0, adresse.indexOf("."));
		return nom;
	}
	
	
	
}
		

	

