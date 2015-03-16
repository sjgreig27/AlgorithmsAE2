import java.util.ArrayList;


public class AE2Main {
	public static void main (String[] args){
		BSTRelation<String, String> languages = new BSTRelation<String, String>();
		languages.insert("FR","French");
		languages.insert("DE", "German");
		languages.insert("IT", "Italian");
		languages.insert("BE", "French");
		languages.insert("BE", "Flemish");
		languages.insert("NL", "Dutch");
		languages.insert("UK", "English");
		languages.insert("IE", "English");
		languages.insert("IE", "Irish");
		//languages.remove("IT", "Italian");
		
		
		// Determine all the XValues given Y
		ArrayList<String> matchedValues = languages.correspondingXValues("English");
		System.out.println("Matching values for English: "+matchedValues.toString());
		
		matchedValues = languages.correspondingXValues("French");
		System.out.println("Matching values for French: "+matchedValues.toString());
		
		// Determine all the YValues given X
		matchedValues = languages.correspondingYValues("IE");
		System.out.println("Matching values for IE: "+matchedValues.toString());
		
		matchedValues = languages.correspondingYValues("BE");
		System.out.println("Matching values for BE: "+matchedValues.toString());
		
		
		// Determine whether the realtion contains a given pair
		System.out.println ("languages.contains(US, English)"+languages.contains("US", "English"));
		System.out.println ("languages.contains(IT, Italian)"+languages.contains("IT", "Italian"));
		
		//languages.removeContainingX("English");
		System.out.println (languages.toString());
		
		languages.removeContainingX("English");
		
		System.out.println (languages.toString());
		
		
		// Empty the relation of the existing contents and repeat the process of determining whether the relation contains a given pair
		languages.empty();
		System.out.println ("languages.contains(US, English)"+languages.contains("US", "English"));
		System.out.println ("languages.contains(IT, Italian)"+languages.contains("IT", "Italian"));
		
		
		
	}
}

