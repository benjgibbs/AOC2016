import java.util.*;

class AOC4b {
  private static int getSectorId(String lastPart) {
    int idx = lastPart.indexOf("[");
    return Integer.parseInt(lastPart.substring(0,idx));
  }
  
  private static String getFreqs(String lastPart) {
    int idx = lastPart.indexOf("[");

    return lastPart.substring(idx+1, lastPart.length()-1);
  }

  private static Map<Integer, List<Character>> count(String s) {
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : s.toCharArray()) {
      Integer i = counts.getOrDefault(c, 0);
      i++;
      counts.put(c, i);
    }
    Map<Integer, List<Character>> freqs = new TreeMap<>((a,b) -> b - a);
    for (Map.Entry<Character, Integer> e : counts.entrySet()) {
      List<Character> r = freqs.get(e.getValue());
      if (r == null) {
        r = new ArrayList<>();
        freqs.put(e.getValue(), r);
      }
      r.add(e.getKey());
    } 


    return freqs;
  }

  public static String decrypt(String msg, int sectorId) {
    StringBuilder builder = new StringBuilder(msg.length());
    for (char c : msg.toCharArray()) {
      if (c == '-') builder.append(" ");
      else {
         builder.append((char)('a' + (((c - 'a') + sectorId) % 26)));
      }
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    Scanner scanner  = new Scanner(System.in);
    int sumSect = 0;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      int brk = line.lastIndexOf("-");
      String msg = line.substring(0, brk);
      String a = msg.replace("-",""); 
      String b = line.substring(brk+1); 

      int sectorId = getSectorId(b);
      char[] freqs = getFreqs(b).toCharArray();

      //System.out.println("a=" + a + ", b=" + b + ", id=" + sectorId + ", freqs=" + freqs);
      boolean valid = true;
      Iterator<Map.Entry<Integer, List<Character>>> iter = count(a).entrySet().iterator();
      List<Character> next = iter.next().getValue(); 
      for (int fi = 0; fi < 5; fi++) {
        Character c = freqs[fi];
        if (next.isEmpty()) {
          next = iter.next().getValue();
        }
        // System.out.println("next: " + next);
        if (!next.remove(c)) {
          valid = false;
          break;
        }
      }
      if (valid) {
        System.out.println(sectorId + ": " + decrypt(msg,sectorId));
        sumSect += sectorId;
      }
    }
    System.out.println("sumSect: " + sumSect);
  }
}
