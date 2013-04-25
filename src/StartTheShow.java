import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;



public class StartTheShow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			System.setProperty("wordnet.database.dir", "/Users/galic1987/Desktop/WordNet-3.0/dict");

			
			NounSynset nounSynset; 
			NounSynset[] hyponyms; 

			WordNetDatabase database = WordNetDatabase.getFileInstance(); 
			Synset[] synsets = database.getSynsets("Pig", SynsetType.NOUN); 
			NounSynset[] ivo = database.getSynsets("Bird",SynsetType.NOUN); 
			
			
			
			
//			for (int i = 0; i < synsets.length; i++) { 
//			    nounSynset = (NounSynset)(synsets[i]); 
//			    hyponyms = nounSynset.getPartHolonyms(); 
//			   
//				for (int j = 0; j < hyponyms.length; j++) { 
//					 System.err.println(hyponyms[j].getDefinition() +""); 
//				}
//
//			    
//			}



		// TODO Auto-generated method stub
		
		// 1.Iterate the contents of the directory 
		final File folder = new File("animalsBlog");

		
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            for (File filedeeper : fileEntry.listFiles()){
	            	
						Map<String, Integer> m = getFrequencies(filedeeper);
						

	            	
	            }
	        }
		}
		
		
		// 2.Open file , interate the all tokens, save them to 
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Map<String, Integer> getFrequencies(File document) throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream(document));
		HashMap<String,Integer> freq = new HashMap<String,Integer>();
		
		//int count=0;
		while(sc.hasNext()){
			String n = sc.next().toLowerCase();
			n = n.replaceAll("[^A-Za-z0-9 ]", "");
			
	
			//System.out.println("Word " + n);
			
			if(n.length()<=1){
				continue;
			}
			
			if(freq.containsKey(n)){
				int number = freq.get(n);
				number++;
				freq.remove(n);
				freq.put(n, number);

				
			}else{
				freq.put(n, 1);
			}
			
			
		    //count++;
		}
		
	return freq;
	}
	

}
