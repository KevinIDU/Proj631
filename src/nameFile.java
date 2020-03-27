
public class nameFile {
	//renvoie le nom du fichier sans l'extension
	public String nomFichier(String adresse) {
		String nom =adresse.substring(0, adresse.indexOf("."));
		return nom;
	}
	
	
}
