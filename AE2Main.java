import java.util.ArrayList;

/**
 * Class to test the BST implementation of the Relation ADT
 */
public class AE2Main {
	public static void main (String[] args){

		System.out.println ("TEST CASE 1\n");

		// Instantiate a new BSTRelation
		BSTRelation<String, String> languages = new BSTRelation<String, String>();

		// Add the binary relations to the BSTRelation
		languages.insert("FR","French");
		languages.insert("DE", "German");
		languages.insert("IT", "Italian");
		languages.insert("BE", "French");
		languages.insert("BE", "Flemish");
		languages.insert("NL", "Dutch");
		languages.insert("UK", "English");
		languages.insert("IE", "English");
		languages.insert("IE", "Irish");
		languages.insert("ES", "Spanish");

		// Test displaying the relation in the format of a String
		System.out.println (languages.toString());

		// Test whether the relation contains a given pair (x,y)
		boolean containsDutch = languages.contains("NL", "Dutch");
		boolean containsFinnish = languages.contains("FN", "Finnish");
		System.out.println ("containsDutch = "+containsDutch);
		System.out.println ("containsFinnish = "+containsFinnish);

		// Given x, determine all values y such that the relation contains (x,y)
		ArrayList<String> belgiumLanguages = languages.correspondingYValues("BE");
		ArrayList<String> irishLanguages = languages.correspondingYValues("IE");
		ArrayList<String> swedenLanguages = languages.correspondingYValues("SW");
		System.out.println(belgiumLanguages.toString());
		System.out.println(irishLanguages.toString());
		System.out.println(swedenLanguages.toString());

		// Given y, determine all values x such that the relation contains (x,y)
		ArrayList<String> englishSpeaking = languages.correspondingXValues("English");
		ArrayList<String> frenchSpeaking = languages.correspondingXValues("French");
		ArrayList<String> swedishSpeaking = languages.correspondingXValues("Swedish");
		System.out.println(englishSpeaking.toString());
		System.out.println(frenchSpeaking.toString());
		System.out.println(swedishSpeaking.toString());

		// Add a given pair (x,y) to the relation
		languages.insert("UK","Welsh");
		System.out.println ("Addition UK, Welsh: "+languages.toString());
		languages.insert("HE","Greek");
		System.out.println ("Addition HE, Greek: "+languages.toString());
		languages.insert("UK", "English");
		System.out.println ("Addition UK, English: "+languages.toString());

		// Remove a given pair (x,y) to the relation
		languages.remove("UK","Welsh");
		System.out.println ("Remove UK, Welsh: "+languages.toString());
		languages.remove("FR","French");
		System.out.println ("Remove FR, French: "+languages.toString());
		languages.remove("SW", "Swedish");
		System.out.println ("Remove SW, Swedish: "+languages.toString());

		// Given x, remove all pairs (x,y) from the relation
		languages.removeContainingX("UK");
		System.out.println ("Remove UK: "+languages.toString());
		languages.removeContainingX("DE");
		System.out.println ("Remove DE: "+languages.toString());
		languages.removeContainingX("SW");
		System.out.println ("Remove SW: "+languages.toString());

		// Given y, remove all pairs (x,y) from the relation
		languages.removeContainingY("Dutch");
		System.out.println ("Remove Dutch: "+languages.toString());
		languages.removeContainingY("Spanish");
		System.out.println ("Remove Spanish: "+languages.toString());
		languages.removeContainingX("Swedish");
		System.out.println ("Remove Swedish: "+languages.toString());

		// Make the relation empty
		languages.empty();
		System.out.println(languages.toString());
		
		
		System.out.println ("\nTEST CASE 2\n");

		// Instantiate a new BSTRelation
		BSTRelation<Integer, String> grades = new BSTRelation<Integer, String>();
		Integer[] marks = {13,14,11,10,16,18,14,12};
		String[] classes = {"Maths", "English", "Geography", "Latin", "Biology", "Physics", "Physics", "Maths"};
		for (int index = 0; index<classes.length; index++){
			grades.insert(marks[index], classes[index]);
		}

		// Test displaying the relation in the format of a String
		System.out.println (grades.toString());

		// Test whether the relation contains a given pair (x,y)
		boolean containsMathsGrade = languages.contains("13", "Maths");
		boolean containsLatinGrade = languages.contains("14", "Latin");
		System.out.println ("containsMathsGrade = "+containsDutch);
		System.out.println ("containsLatinGrade = "+containsFinnish);

		// Given x, determine all values y such that the relation contains (x,y)
		ArrayList<String> grade14s = grades.correspondingYValues(14);
		ArrayList<String> grade18s = grades.correspondingYValues(18);
		ArrayList<String> grade20s = grades.correspondingYValues(20);
		System.out.println(grade14s.toString());
		System.out.println(grade18s.toString());
		System.out.println(grade20s.toString());

		// Given y, determine all values x such that the relation contains (x,y)
		ArrayList<Integer> mathGrades = grades.correspondingXValues("Maths");
		ArrayList<Integer> physicsGrades = grades.correspondingXValues("Physics");
		ArrayList<Integer> germanGrades = grades.correspondingXValues("German");
		System.out.println(mathGrades.toString());
		System.out.println(physicsGrades.toString());
		System.out.println(germanGrades.toString());

		// Add a given pair (x,y) to the relation
		grades.insert(20,"Physical Education");
		System.out.println ("Addition 20, Physical Education: "+grades.toString());
		grades.insert(17,"Home Economics");
		System.out.println ("Addition 17, Home Economics: "+grades.toString());
		grades.insert(13, "Maths");
		System.out.println ("Addition 13, Maths: "+grades.toString());

		// Remove a given pair (x,y) to the relation
		grades.remove(20,"Physical Education");
		System.out.println ("Remove 20, Physical Education: "+grades.toString());
		grades.remove(17,"Home Economics");
		System.out.println ("Remove 17, Home Economics: "+grades.toString());
		grades.remove(14, "Math");
		System.out.println ("Remove 14, Math: "+grades.toString());

		// Given x, remove all pairs (x,y) from the relation
		grades.removeContainingX(14);
		System.out.println ("Remove 14: "+grades.toString());
		grades.removeContainingX(15);
		System.out.println ("Remove 15: "+grades.toString());
		grades.removeContainingX(16);
		System.out.println ("Remove 16: "+grades.toString());

		// Given y, remove all pairs (x,y) from the relation
		grades.removeContainingY("Geography");
		System.out.println ("Remove Geography: "+grades.toString());
		grades.removeContainingY("Latin");
		System.out.println ("Remove Latin: "+grades.toString());
		grades.removeContainingY("Philosophy");
		System.out.println ("Remove Philosophy: "+grades.toString());

		// Make the relation empty
		grades.empty();
		System.out.println(grades.toString());
		
		
		System.out.println ("\nTEST CASE 3\n");

		// Instantiate a new BSTRelation
		BSTRelation<Integer, Integer> playerScores = new BSTRelation<Integer, Integer>();
		Integer[] scores = {1000,301,301,423,555,698,555,121};
		Integer[] player = {1,3,2,4,5,1,3,3};
		for (int index = 0; index<classes.length; index++){
			playerScores.insert(scores[index], player[index]);
		}

		// Test displaying the relation in the format of a String
		System.out.println (playerScores.toString());

		// Test whether the relation contains a given pair (x,y)
		boolean contains1000 = playerScores.contains(1000, 1);
		boolean contains301 = playerScores.contains(301, 5);
		System.out.println ("containsMathsGrade = "+contains1000);
		System.out.println ("containsLatinGrade = "+contains301);

		// Given x, determine all values y such that the relation contains (x,y)
		ArrayList<Integer> score1000 = playerScores.correspondingYValues(1000);
		ArrayList<Integer> score301 = playerScores.correspondingYValues(301);
		ArrayList<Integer> score999 = playerScores.correspondingYValues(999);
		System.out.println(score1000.toString());
		System.out.println(score301.toString());
		System.out.println(score999.toString());

		// Given y, determine all values x such that the relation contains (x,y)
		ArrayList<Integer> player1 = playerScores.correspondingXValues(1);
		ArrayList<Integer> player3 = playerScores.correspondingXValues(3);
		ArrayList<Integer> player7 = playerScores.correspondingXValues(7);
		System.out.println(player1.toString());
		System.out.println(player3.toString());
		System.out.println(player7.toString());

		// Add a given pair (x,y) to the relation
		playerScores.insert(2000,7);
		System.out.println ("Addition 2000, 7: "+playerScores.toString());
		playerScores.insert(170,4);
		System.out.println ("Addition 170, 4: "+playerScores.toString());
		playerScores.insert(1300,1);
		System.out.println ("Addition 1300, 1: "+playerScores.toString());

		// Remove a given pair (x,y) to the relation
		playerScores.remove(2000,7);
		System.out.println ("Remove 2000, 7: "+playerScores.toString());
		playerScores.remove(170,4);
		System.out.println ("Remove 170, 4: "+playerScores.toString());
		playerScores.remove(1300, 1);
		System.out.println ("Remove 1300, 1: "+playerScores.toString());

		// Given x, remove all pairs (x,y) from the relation
		playerScores.removeContainingX(555);
		System.out.println ("Remove 555: "+playerScores.toString());
		playerScores.removeContainingX(301);
		System.out.println ("Remove 301: "+playerScores.toString());
		playerScores.removeContainingX(121);
		System.out.println ("Remove 121: "+playerScores.toString());

		// Given y, remove all pairs (x,y) from the relation
		playerScores.removeContainingY(4);
		System.out.println ("Remove 4: "+playerScores.toString());
		playerScores.removeContainingY(3);
		System.out.println ("Remove 3: "+playerScores.toString());
		playerScores.removeContainingY(7);
		System.out.println ("Remove 7: "+playerScores.toString());

		// Make the relation empty
		grades.empty();
		System.out.println(grades.toString());
		
	}
}


