import java.util.Scanner;
import java.util.*;

public class main {

	public static void main(String[] args){
		
		//Je demande a l'utlisateur quel texte il veux comprésser.
		Scanner sc = new Scanner(System.in);
		System.out.println("quel texte voulez vous compressez ? Indiquez juste le nom sans l'extension "); //Pour alice.txt -> alice
		String adresse = sc.nextLine()+".txt";
		
		//Ouverture du fichier texte et on récupere le nom du fichier
		OpenFile texte = new OpenFile(adresse);
		String nom = texte.nomFichier(adresse);
	
		
		/* On détermine : 
		 *  -> l'alphabet et la fréquence des caractères.
		 *  -> Le TreeSet pour lier et ordonner les données.
		 *  -> Les noeuds a partir du TreeSet.
		 *  -> L'arbre avec le parcours en profondeur
		 *  -> L'écriture des fichiers .bin et .txt
		 */
		texte.defAlphFreq();
		texte.defTreeSet();
		texte.defNoeud();
		texte.parcourir(texte.defArbre(), "");	
		texte.writeFileTxt(adresse);
		texte.writeFileFreq(adresse);
		
		
		System.out.println("le taux de compréssion de votre "+ nom+".txt est de :"+ texte.compression(nom+"_comp.bin", nom+".txt"));
		System.out.println("un caractère est codé en moyenne sur "+ texte.moyBitCarac() +"bits");
	}
	

}
