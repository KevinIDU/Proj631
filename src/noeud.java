import java.util.*;

public class noeud {
	private int frequence;
	private char carac = '§';
	private noeud fgauche =null;
	private noeud fdroit= null;
	private String codeb ="";

	
	public noeud(int freq, char caract, noeud l, noeud r, String codebin) {
		super();
		this.frequence = freq;
		this.carac = caract;
		this.fgauche=l;
		this.fdroit = r;
		this.codeb = codebin;
	}
	
	@Override
	public String toString() {
		return "Noeud(freq= " + this.frequence + ", car= "+this.carac+ ", fg= "+this.fgauche +", fd= "
				+this.fdroit +", cb= "+ this.codeb +")";
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public char getCarac() {
		return carac;
	}

	public void setCarac(char carac) {
		this.carac = carac;
	}

	public noeud getFgauche() {
		return fgauche;
	}

	public void setFgauche(noeud fgauche) {
		this.fgauche = fgauche;
	}

	public noeud getFdroit() {
		return fdroit;
	}

	public void setFdroit(noeud fdroit) {
		this.fdroit = fdroit;
	}

	public String getCodeb() {
		return codeb;
	}

	public void setCodeb(String codeb) {
		this.codeb = codeb;
	}
	
	public boolean estFeuile() {
		return(this.fdroit == null && this.fgauche == null);
	}
}
	