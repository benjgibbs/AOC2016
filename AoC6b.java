
import java.util.*;

class AoC6b {

  public static void main(String[] argsf) {
    Scanner scanner = new Scanner(System.in);
  
    List<Map<Character, Integer>> allFreqs = new ArrayList<>();    

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      char[] cs = line.toCharArray();
      for (int i = 0; i < cs.length; i++) {
        while (i >= allFreqs.size()) {
          allFreqs.add(new HashMap<>());
        }
        Map<Character, Integer> freqs = allFreqs.get(i);
        int c = freqs.getOrDefault(cs[i], 0);
        freqs.put(cs[i], c+1);
      }
    }
    for (Map<Character, Integer> map : allFreqs) {
      int minCount = Integer.MAX_VALUE;
      char minChar = ' ';
      for (Map.Entry<Character, Integer> e : map.entrySet()) {
        if (e.getValue() < minCount) {
          minChar = e.getKey();
          minCount = e.getValue();
        }
      }
      System.out.print(minChar);
    }
    System.out.println();
  }
}
