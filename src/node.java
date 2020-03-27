import java.util.*;

public class node {
	int value;
	node left;
	node right;

	public node(int value) {		
		this.value = value;
	    right = null;
	    left = null;	    
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public node getLeft() {
		return left;
	}

	public void setLeft(node left) {
		this.left = left;
	}

	public node getRight() {
		return right;
	}

	public void setRight(node right) {
		this.right = right;
	}
	
	/*public ArrayList<node> feuille(ArrayList<String> alph, ArrayList<Integer> freq) {
		ArrayList<node> Feuille = new ArrayList<node>();
		for(int i = 0; i<alph.size(); i++) {
			node noeud = new node(freq.get(i), alph.get(i));
			Feuille.add(noeud);
		}
		return Feuille;
		
		
	}*/
}
