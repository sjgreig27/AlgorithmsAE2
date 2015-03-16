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
		languages.remove("IT", "Italian");
		
		
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
		
		matchedValues = languages.correspondingYValues("ZZ");
		System.out.println("Matching values for ZZ: "+matchedValues.toString());
		
		// Determine whether the relation contains a given pair
		System.out.println ("languages.contains(US, English)"+languages.contains("US", "English"));
		System.out.println ("languages.contains(IT, Italian)"+languages.contains("IT", "Italian"));
		
		//languages.removeContainingX("English");
		System.out.println (languages.toString());
		
		languages.removeContainingY("English");
		System.out.println (languages.toString());
		languages.removeContainingY("French");
		System.out.println (languages.toString());
		languages.removeContainingY("Swedish");
		System.out.println (languages.toString());
		
		
		languages.removeContainingX("BE");
		System.out.println (languages.toString());
		languages.removeContainingX("IT");
		System.out.println (languages.toString());
		languages.removeContainingX("GB");
		System.out.println (languages.toString());
		
		
		// Empty the relation of the existing contents and repeat the process of determining whether the relation contains a given pair
		languages.empty();
		System.out.println ("languages.contains(US, English)"+languages.contains("US", "English"));
		System.out.println ("languages.contains(IT, Italian)"+languages.contains("IT", "Italian"));
		
		
		System.out.println("--------------------------------------------------------------------------\nTEST CASE2\n");
		
		BSTRelation<Integer, String> students = new BSTRelation<Integer, String>();
		String[] names = {"Adam", "Chloe", "John", "Tom", "Claire", "Angela", "Michelle", "Joanna", "Chris", "James",
				"Kate", "Tom", "Chloe", "Mary", "Andrew", "Marco", "Raoul", "Sara", "Colin", "Ana", "Martin",
				"Mickey", "Marunas", "Adam"};
		Integer[] studentGrade = {21, 22, 20, 19, 19, 18, 17, 22, 21, 20, 18, 15, 14, 13, 22, 21, 18, 17, 16, 22, 23, 24, 23, 24};
		for(Integer index = 0; index<24; index++){
			students.insert(studentGrade[index], names[index]);
		}
		
		System.out.println(students.toString());
		System.out.println ("Students relation contains 21, Adam: \n"+students.contains(21, "Adam"));
		System.out.println ("Students relation contains 23, Adam: \n"+students.contains(23, "Adam"));
		ArrayList<String> grade22 = students.correspondingYValues(22);
		System.out.println("Students who received grade band 22: \n"+grade22.toString());
		ArrayList<Integer> chloe = students.correspondingXValues("Chloe");
		System.out.println("Chloe's grades: \n"+chloe.toString());
		students.insert(15, "Alex");
		System.out.println("Addition of Alex's 15 grade: \n"+students.toString());
		students.remove(15, "Tom");
		System.out.println("Removal of Tom's 15 grade: \n"+students.toString());
		students.removeContainingX(22);
		System.out.println("Removal of all student's that received a grade 22: \n"+students.toString());
		students.removeContainingY("Adam");
		System.out.println("Removal of grades belonging to Adam: \n"+students.toString());
		students.empty();
		System.out.println("Emptying the relation: \n"+students.toString());
		
	}
}

