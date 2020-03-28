import java.util.*;
// classe noeud servirant a créer l'arbre

public class noeud {
	private int frequence;
	private char carac = '§';
	private noeud fgauche =null;
	private noeud fdroit= null;
	private String codeb ="";

	/**
	 * Constructeur de noeud.
	 * @param freq est la fréquence d'un caractère.
	 * @param caract est un caractère de l'alphabet.
	 * @param l fils gauche de mon noeud.
	 * @param r fils droit de mon noeud. 
	 * @param codebin est un code composé de 1 et 0 qui sera determiné en fonction du chemin de la racin a la feuille. 
	 * @see defArbre.
	 * @see defNoeud.
	 */
	public noeud(int freq, char caract, noeud l, noeud r, String codebin) {
		super();
		this.frequence = freq;
		this.carac = caract;
		this.fgauche=l;
		this.fdroit = r;
		this.codeb = codebin;
	}
	
	
	/**
	 * Overide de toString pour un meilleur affichage.
	 */
	@Override
	public String toString() {
		return "Noeud(freq= " + this.frequence + ", car= "+this.carac+ ", fg= "+this.fgauche +", fd= "
				+this.fdroit +", cb= "+ this.codeb +")";
	}
	
	/**
	 * Geteur de la frequence du noeud.
	 * @return Un entier. 
	 */
	public int getFrequence() {
		return frequence;
	}

	/**
	 * Setteur de la fréquence noeud.
	 * @param frequence voulu.
	 */
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	/**
	 * Getteur du caractère du noeud.
	 * @return Un caractere.
	 */
	public char getCarac() {
		return carac;
	}

	/**
	 * Seteur du caractère du noeud.
	 * @param carac voulu.
	 */
	public void setCarac(char carac) {
		this.carac = carac;
	}

	/**
	 * Getteur du fils gauche du noeud.
	 * @return Un noeud.
	 */
	public noeud getFgauche() {
		return fgauche;
	}

	/**
	 * Setteur du fils gauche du noeud.
	 * @param fgauche voulu.
	 */
	public void setFgauche(noeud fgauche) {
		this.fgauche = fgauche;
	}

	/**
	 * Getteur du fils droit du noeud.
	 * @return Un noeud.
	 */
	public noeud getFdroit() {
		return fdroit;
	}

	/**
	 * Setteur du fils droit du noeud.
	 * @param fdroit voulu.
	 */
	public void setFdroit(noeud fdroit) {
		this.fdroit = fdroit;
	}

	/**
	 * Getteur du code binaire du noeud.
	 * @return Un String.
	 */
	public String getCodeb() {
		return codeb;
	}

	/**
	 * Setteur du code binaire du noeud.
	 * @param codeb voulu.
	 */
	public void setCodeb(String codeb) {
		this.codeb = codeb;
	}
	
	/**
	 * Permet de dire si un noeud est une feuille.
	 * @return Vrai si mon noeud n'a pas de descendant Faux sinon.
	 */
	public boolean estFeuile() {
		return(this.fdroit == null && this.fgauche == null);
	}
}
	