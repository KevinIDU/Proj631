import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class main {

	public static void main(String[] args){
		OpenFile alice = new OpenFile("alice.txt");
		alice.defAlphFreq();
		ArrayList<Character> alphOrd = new ArrayList<Character>();
		ArrayList<Integer> freqOrd = new ArrayList<Integer>();
		TreeSet<Tuple> tuple = new TreeSet<Tuple>();
		
		
		freqOrd = alice.ordFreq();
		alphOrd = alice.ordAlph(alphOrd);
		
		alice.defTreeSet();
		alice.defNoeud();
		alice.parcourir(alice.defArbre(), "");
		System.out.println(alice.tuples);
		System.out.println(alice.Lnoeud);
		System.out.println(alice.defArbre());
		System.out.println(alice.dico);
		
		//alice.writeFileAlice();
		//alice.writeFileFreq();
		
	}
	

}
