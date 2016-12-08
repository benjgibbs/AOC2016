

import java.util.*;

class AoC6a {

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
      int maxCount = 0;
      char maxChar = ' ';
      for (Map.Entry<Character, Integer> e : map.entrySet()) {
        if (e.getValue() > maxCount) {
          maxChar = e.getKey();
          maxCount = e.getValue();
        }
      }
      System.out.print(maxChar);
    }
    System.out.println();
  }
}
