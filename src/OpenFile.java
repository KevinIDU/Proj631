import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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
	
	//*******************************
	// Je créer une copie des listes alphabet et frequence parce que dans la suite je remove les elements dans alphabet et frequence 
	//mais j'en est encore besoin après. Découvert de .clone() trop tard.
	ArrayList<Integer> newFrequence = new ArrayList<Integer>();
	ArrayList<Character> new_alphabet = new ArrayList<Character>();
	ArrayList<Integer> f3 = new ArrayList<Integer>();
	ArrayList<Character> c3 = new ArrayList<Character>();
	//********************************
	
	ArrayList<noeud> Lnoeud = new ArrayList<noeud>();
	HashMap<Character, String> dico = new HashMap<Character, String >();
	
	/**
	 * Constructeur permttant d'ouvrir le fichier.
	 * @param fichier qu'on veut ouvrir.
	 * @see open
	 */
	public OpenFile(String fichier) {
		this.txt=this.open(fichier);
		
	}
	
	/**
	 * Getteur du texte.
	 * @return Le texte qu'on étudie qui est considérer comme un string.
	 */
	public String getTxt() {
		return txt;
	}

	/**
	 * Getteur de l'alphabet qui compose le texte.
	 * @return Une liste composé des caractères de l'alphabet du texte.
	 * 
	 */
	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	/**
	 * Getteur de la fréquence du texte.
	 * @return Une lite d'entier qui est la fréquence d'apparition des caractères de notre alphabet.
	 */
	public ArrayList<Integer> getFrequence() {
		return frequence;
	}


	public ArrayList<Character> getNewAlphabet() {
		return new_alphabet;
	}


	public ArrayList<Integer> getNewFrequence() {
		return newFrequence;
	}
	
	
	/**
	 * Méthode pour ouvrir un texte situé dans le répertoire du projet.
	 * @param adresse est l'adresse en format .txt de notre texte.
	 * @see OpenFile
	 * @return le texte ouvert en String.
	 */
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
	
	
	/**
	 * Méthode qui modifie les liste d'alphabet et de fréquence.
	 * Détermine l'alphabet de notre texte et sa fréquence d'apparition.
	 */
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

	
	/**
	 * Renvoie la fréquence minimal de notre liste de fréquence.
	 * @see ordAlph
	 * @return Une entier correspondant à la fréquence la plus petite.
	 */
	public int  minFreque() {
		int res = this.frequence.get(0);
		for(int j = 0; j<this.frequence.size(); j++){
		   if(this.frequence.get(j) < res)
		      res=this.frequence.get(j);
		   }
		return res;
	}
	

	/**
	 * Trie ma liste de caractere par apparition dans l'ordre croissant. Fonctionne en récursif.
	 * @param alphOrd liste de caractere qui sera mon alphabet non trié.
	 * @return Une liste de caractère.
	 */
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
	
	
	/**
	 * Trie ma liste de fréquence dans l'ordre croissant
	 * @return une liste d'entier
	 */
	public ArrayList<Integer> ordFreq(){
		Collections.sort(this.newFrequence);
		return this.newFrequence;
	}
	
	
	/**
	 * Définition d'un TreeSet afin de lier un caractère et sa fréquence associé a l'aide de la classe tuple.
	 * @see Tuple
	 */
	public void defTreeSet() {
		for(int i =0; i<this.f3.size(); i++) {
			Tuple t =new Tuple(this.f3.get(i),this.c3.get(i));
			this.tuples.add(t);
			}
	}
	
	
	/**
	 * Création de ma liste de feuille.
	 * @see noeud
	 * @return Une liste de noeud.
	 * Noeud = fréquence, caractere, fils gauche, fils droit, code binaire.
	 */
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
	
	
	
	/**
	 * Création de mon arbre a aprtir de ma liste de feuille.
	 * @see defNoeud
	 * @return Un noeud.
	 */
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
	
	
	/**
	 * Trie a bulle (trouvé sur internet) me permettant de trier ma liste tampon par fréquence dans defArbre après l'ajout d'un noeud.
	 * @see defArbre 
	 * @param list qu'on souhaite trié.
	 */
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
	
	
	/**
	 * Parcours en profondeur de mon arbre. 
	 * @see defArbre
	 * @param depart qui correspond a la racine de mon arbre obtenue avec defArbre.
	 * @param codeBin à mettre à "" au départ pour la récursivité.
	 */
	public void parcourir(noeud depart, String codeBin){
		
		if (depart.estFeuile()) {
			this.dico.put(depart.getCarac(),codeBin);
		}
		if (depart.getFgauche() != null) {
			
				this.parcourir(depart.getFgauche(), codeBin + "0");
			
		}
		if (depart.getFdroit()!= null) {
			
			this.parcourir(depart.getFdroit(), codeBin + "1");
			
			
		}
	
	}
	
	
	/**
	 * Céation du fichier nom_comp.bin.
	 * Le fichier obtenue sera le texte compréssé.
	 * On récuper dans un String ,ici chaine, tout les codes binaires.
	 * On vérifie que chiane%8 soit égale a 0 pour faire les paquets d'octets.
	 * Ensuite on ajoute dans une liste de String, ici listeOctet, les octets.
	 * Puis on écrit dans le fichier ces octets convertie en integer puis en byte.
	 * @param adresse du fichier a comprésser.
	 */
	public void writeFileTxt(String adresse) {
		//chaine est un String avec tout les codes binaires 
		String chaine = "";
			for(int i = 0; i<this.txt.length(); i++) {
				chaine = chaine + this.dico.get(this.txt.charAt(i));
			}
		while(chaine.length() %8 != 0) {
			chaine = chaine +"0";
		}
		
		ArrayList<String> listeOctet = new ArrayList<String>();
		for(int i =8; i < chaine.length(); i=i+8 ) {
			listeOctet.add(chaine.substring(i-8, i));
		}
		
		try {
			File monFichier = new File(nomFichier(adresse)+"_comp.bin");
			if(monFichier.exists()) {
				System.out.println( monFichier + " existe deja");
			}else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFichier(adresse)+"_comp.bin")));
				for(String octet : listeOctet) {
					int bit = Integer.parseInt(octet, 2);
					writer.write((byte)bit);
				}
			
				
			writer.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Création du fichier nom_freq.txt, qui sera un texte avec le nombre de caractère, chaque caractère et sa fréquence.
	 * @param adresse du fichier.
	 */
	public void writeFileFreq(String adresse) {
		try {
			File monFichier = new File(nomFichier(adresse)+"_freq.txt");
			if(monFichier.exists()) {
				System.out.println(monFichier +" existe deja");
			}else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFichier(adresse)+"_freq.txt")));
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
	
	
	/**
	 * Calcule le nombre de caractère que l'alphabet possède.
	 * @return Un entier.
	 */
	public int nbCarac() {
		int nbCarac =0;
		for(int i =0; i<this.c3.size(); i++) {
			nbCarac = nbCarac+1;
		}
		return nbCarac;
	}

	
	/**
	 * Renvoie le nom du fichier sans l'extension.
	 * @param adresse du fichier. 
	 * @return Une chaine de caractère.
	 */
	public String nomFichier(String adresse) {
		String nom =adresse.substring(0, adresse.indexOf("."));
		return nom;
	}

	
	/**
	 * Renvoie le taux de compréssion du texte( 1 moins (taille finale/taille initiale)).
	 * @param adrBin adresse du fichier final : .bin.
	 * @param adrTxt adresse du fichier initial : .txt.
	 * @return Un nombre décimal.
	 */
	public float compression(String adrBin, String adrTxt) {
		File txt = new File(adrTxt);
		File bin = new File(adrBin);
		double txtBytes =  txt.length();
		double binBytes =  bin.length();
		return (float)(1 - (binBytes/txtBytes));
 				
	}

	
	/**
	 * Renvoie le nombbre moyen de bit par caractère. 
	 * @return Un entier.
	 */
	public float moyBitCarac() {
		int res = 0;
		int nbCarac = this.nbCarac();
		for (char key:dico.keySet()) {
			int cpt = 0;
			for(int i =0; i<dico.get(key).length(); i++) {
				cpt = cpt +1;
			}
			res = res + cpt;
		}
		return res/nbCarac;
	}
	
	
	
}
		

	

