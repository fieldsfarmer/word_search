import java.util.*;
// data structure for prefix and suffix search
public class Trie{
	private TrieNode root;
	public Trie(){
		root = new TrieNode();
	}
	public void insert(String wd){
		TrieNode tmp = root;
		for(int i=0; i<wd.length(); ++i){
			char c = wd.charAt(i);
			if(!tmp.children.containsKey(c)){
				tmp.children.put(c, new TrieNode());
			}
			tmp = tmp.children.get(c);
		}
		tmp.isEnd = true;
	}
	public ArrayList<String> getPrefixWords(String prefix){
		ArrayList<String> res = new ArrayList<>();
		if(prefix.equals("")) return res;
		TrieNode node = getNodeOfLastChar(prefix);

		if(node==null) return res;

		res = getWordsStartingFromNode(node);

		for(int i=0; i<res.size(); ++i){
			res.set(i, prefix+res.get(i));
		}		
		return res;
	}
	private TrieNode getNodeOfLastChar(String prefix){
		TrieNode tmp = root;
		for(int i=0; i<prefix.length(); ++i){
			char c = prefix.charAt(i);
			if(!tmp.children.containsKey(c)){
				return null;
			}
			tmp = tmp.children.get(c);
		}
		return tmp;
	}
	private ArrayList<String> getWordsStartingFromNode(TrieNode node){
		ArrayList<String> res = new ArrayList<>();
		String s = "";
		dfs(node, s, res);
		return res;
	}
	private void dfs(TrieNode node, String s, ArrayList<String> res){
		if(node == null) return;
		if(node.isEnd) res.add(s);
		for(Character c : node.children.keySet()){
			String t = s + c;
			dfs(node.children.get(c), t, res);
		}
	}
}
class TrieNode{
	public boolean isEnd;
	public Map<Character, TrieNode> children;
	public TrieNode(){
		isEnd = false;
		children = new HashMap<Character, TrieNode>();
	};
}