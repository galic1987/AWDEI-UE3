import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.WordSense;
import edu.smu.tspell.wordnet.impl.file.ReferenceSynset;
import edu.smu.tspell.wordnet.impl.file.synset.AdjectiveReferenceSynset;
import edu.smu.tspell.wordnet.impl.file.synset.AdverbReferenceSynset;
import edu.smu.tspell.wordnet.impl.file.synset.NounReferenceSynset;



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
			
			

			
			/*for (int i = 0; i < synsets.length; i++) { 
				//System.out.println("--- " + synsets[i].getClass().toString());
				System.out.println("--- " + synsets[i].toString());

				//System.out.println("--- " + synsets[i].getDefinition());
				
				NounReferenceSynset r1 = (NounReferenceSynset) synsets[i];
				
				 NounSynset[] r = r1.getMemberHolonyms();
				 
				for (int n = 0; n < r.length; n++) { 
					
					NounSynset q = r[n];
					
					System.out.println("***** " + q.getDefinition());
				}

				
				

				
			}
			*/
			
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
		final File folder = new File("AWDEIData");

		Map<String, Map> directoryMap = new HashMap<String,Map>();

		
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	    		int numberOfWordsInDir = 0;
	    		int numberOfAdjectivesInDir = 0;
	    		int numberOfNounsInDir = 0;
	    		
	            for (File filedeeper : fileEntry.listFiles()){
	            	
						Map<String, Integer> m = getFrequencies(filedeeper);
						Iterator<Entry<String, Integer>> it = m.entrySet().iterator();
		                while (it.hasNext()) {
		            		//one word from text 
		                	numberOfWordsInDir++;
		                	
		                    Entry<String, Integer> pairs = it.next();

		                    // possible usage: we could extract tag cloud for some webiste regarding some thema (animals, plants etc)
		                    // Holonym hierarchical search for NOUNS 
		        			String oneWordFromText = pairs.getKey();
		        			String seraching = "europe"; // animalia, plantae, europe, man
		        			
		        			Synset[] synsets = database.getSynsets(oneWordFromText, SynsetType.NOUN); 
		        			
		        			if(synsets.length>0){
		        				numberOfNounsInDir++;
		        			}
		        			
		        			
		        			if(checkIfWordIsPartOf(oneWordFromText,seraching,synsets)){
		        				System.out.println("found "+ seraching + " "+ oneWordFromText + " in "+  fileEntry);
		        			}
		        			
		        			
		        			/*
		        			// we can recognize maybe if this text is 
		        			// Adjective antonyms search 
		        			Synset[] syn2 = database.getSynsets(oneWordFromText, SynsetType.ADJECTIVE); 

		        			if(syn2.length>0){
		        				numberOfAdjectivesInDir++;
		        			}
		        			
		        			
		        			for (int i = 0; i < syn2.length; i++) { 
		        				
		        				WordSense [] w1 = syn2[i].getAntonyms(oneWordFromText);
		        				if(w1.length > 0)
		        				System.out.println("Found antonym for word " +oneWordFromText + " --- " +w1[0].getWordForm());

		        			}
		        			
		        			
		        			*/
		        			
		        			
		        			
		                	
		                }

	            	
	            }
	            
	            double literatureRatio = ((double)((double)numberOfAdjectivesInDir)/numberOfWordsInDir)*100;
	            double nounRation = ((double)((double)numberOfNounsInDir)/numberOfWordsInDir)*100;

			/*	System.out.println(fileEntry + " --- adjective Ratio " + literatureRatio  );
				System.out.println(fileEntry + " --- noun Ratio" + nounRation );
*/
	            
	        }
		}
		
		
		// 2.Open file , interate the all tokens, save them to 
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static boolean checkIfWordIsPartOf(String word, String partOf, Synset[] synsets){
		for (int i = 0; i < synsets.length; i++) {
			NounReferenceSynset r1 = (NounReferenceSynset) synsets[i];
			//System.out.println("***** "+ r1.toString() );
		
			String [] wordForms = r1.getWordForms();
			for (int n = 0; n < wordForms.length; n++) {
				if(wordForms[n].toLowerCase().equals(partOf.toLowerCase())){
					//System.out.println("YES" );

					return true;
				}
			}

			NounSynset[] r = r1.getMemberHolonyms();
			//for (int n = 0; n < r.length; n++) { 
		  return checkIfWordIsPartOf( word,  partOf, r);
			//}
			
		}
		return false;
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
