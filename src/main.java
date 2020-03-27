import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class main {

	public static void main(String[] args){
		OpenFile alice = new OpenFile("alice.txt");
		alice.defAlphFreq();
		//System.out.println(alice.getAlphabet());
		//System.out.println(alice.getFrequence());
		ArrayList<Character> alphOrd = new ArrayList<Character>();
		ArrayList<Integer> freqOrd = new ArrayList<Integer>();
		//*HashMap<String, Integer> dico = new HashMap<String, Integer>();
		TreeSet<Tuple> tuple = new TreeSet<Tuple>();
		
		
		freqOrd = alice.ordFreq();
		alphOrd = alice.ordAlph(alphOrd);
		//dico = alice.dico(alphOrd, freqOrd);
		alice.defTreeSet();
		System.out.println(alice.tuples);
		
		
		//System.out.println(alphOrd.get(16));
		//Collections.sort(alphOrd);
		//System.out.println(alphOrd);
		
	
	}

}
