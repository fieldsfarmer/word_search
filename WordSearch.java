import java.util.*;
import java.io.*;
public class WordSearch{
	public static String reverse(String s){
		return new StringBuffer(s).reverse().toString();
	}
	private static boolean isOK(String wd, HashSet<Character> set){
		if(wd.equals("") || set.isEmpty()) return false;
		for(int i=0; i<wd.length(); ++i){
			if(!set.contains(wd.charAt(i)))
				return false;
		}
		return true;
	}
	public static ArrayList<String> WordsCanSpell(ArrayList<String> list, String letters){
		ArrayList<String> res = new ArrayList<>();
		if(letters.equals("") || list.isEmpty()) return res;
		HashSet<Character> set = new HashSet<>();
		for(int i=0; i<letters.length(); ++i){
			set.add(letters.charAt(i));
		}
		for(String s : list){
			if(isOK(s, set)){
				res.add(s);
			}
		}
		return res;
	}
	public static ArrayList<String> WordsWithPrefix(Trie prefix, String letters){
		ArrayList<String> res = prefix.getPrefixWords(letters.toLowerCase());
		return res;
	}
	public static ArrayList<String> WordsWithSuffix(Trie suffix, String letters){
		ArrayList<String> tmp = suffix.getPrefixWords(reverse(letters.toLowerCase()));
		ArrayList<String> res = new ArrayList<>();
		for(String s : tmp){
			res.add(reverse(s));
		}
		return res;
	}
	public static void ReadFileAndBuildData(String filename, ArrayList<String> list, Trie prefixTree, Trie suffixTree){
		Scanner scanner = null;
		try{
			scanner = new Scanner(new File("words.txt"));
		} catch(FileNotFoundException ex){
			System.out.println("The file cannot be found!");
			ex.printStackTrace();
		}
		String wd = "";
		while(scanner.hasNextLine()){
			wd = scanner.nextLine();
			list.add(wd);
			prefixTree.insert(wd);
			suffixTree.insert(reverse(wd));
		}

	}

	public static void main(String[] args) {
		String instruction = "To search words which spell with given letters, just input the letters; \n To do prefix search, start with --prefix then space, then the prefix; \n To do suffix search, start with --suffix.\n To close the program, Ctrl + c";
		System.out.println(instruction);

		ArrayList<String> list = new ArrayList<>();
		// data structure for prefix search
		Trie prefixTree = new Trie();
		// data structure for suffix search
		Trie suffixTree = new Trie();

		ReadFileAndBuildData("words.txt", list, prefixTree, suffixTree);
		Scanner scanner = new Scanner(System.in);
		// pattern to check the input letters only contains letters
		String pattern = "^[A-Za-z]+$"; 
		String input = "";
		String[] cmd;
		while(true){
			System.out.print("Please input here: ");
			input = scanner.nextLine();
			cmd = input.split(" ");
			List<String> res;
			if(cmd.length==1 && cmd[0].matches(pattern)){
				res = WordsCanSpell(list, cmd[0].toLowerCase());
				System.out.println("There are "+ res.size()+ " words can be spelled with "+cmd[0].toLowerCase()+".");
				for(String s : res)
						System.out.println(s);
			}
			else if(cmd.length==2){
				if(cmd[0].equals("--prefix") && cmd[1].matches(pattern)){
					res = WordsWithPrefix(prefixTree, cmd[1]);
					System.out.println("There are "+ res.size()+ " words starting with "+cmd[1].toLowerCase()+".");
					for(String s : res)
						System.out.println(s);
				}
				else if(cmd[0].equals("--suffix") && cmd[1].matches(pattern)){
					res = WordsWithSuffix(suffixTree, cmd[1]);
					System.out.println("There are "+ res.size()+ " words ending with "+cmd[1].toLowerCase()+".");
					for(String s : res)
						System.out.println(s);
				}
				else{
					System.out.println("Invalid input!");
				}
			}
			else{
				System.out.println("Invalid input!");
			}
		}		
	}
}