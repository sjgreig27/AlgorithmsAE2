
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
		System.out.println ("languages.contains(US, English)"+languages.contains("US", "English"));
		System.out.println ("languages.contains(IT, Italian)"+languages.contains("IT", "Italian"));
	}
}

