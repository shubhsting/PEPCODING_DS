import java.util.ArrayList;
import java.util.HashMap;

public class frequencymap {
    public static void main(String[] args) {
        freqMap("aabbbcccc");
        freqmap2("aabbbcccc");
        freqMap3("aabbbcccc");
    }

    public static void freqMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else
                map.put(ch, 1);
        }
        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void freqmap2(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    public static void freqMap3(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            ArrayList<Integer> ap = map.get(ch);
            ap.add(i);
        }
        for (char ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }
}