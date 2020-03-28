import java.util.Scanner;
import java.util.*;

public class main {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("quel texte voulez vous compressez ? Indiquez juste le nom sans l'extension ");
		String adresse = sc.nextLine()+".txt";
		
		
		OpenFile texte = new OpenFile(adresse);
		String nom = texte.nomFichier(adresse);
	
		

		texte.defAlphFreq();
		texte.defTreeSet();
		texte.defNoeud();
		texte.parcourir(texte.defArbre(), "");	
		texte.writeFileTxt(adresse);
		texte.writeFileFreq(adresse);
		System.out.println("Vos fichiers sont : "+nom+"_freq.txt et "+nom+"_comp.bin");
		System.out.println("le taux de compréssion de votre "+ nom+".txt est de :"+ texte.compression(nom+"_comp.bin", nom+".txt"));
		
	}
	

}
