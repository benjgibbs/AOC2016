import java.util.*;
import java.util.regex.*;

class AoC10a {
  Map<Integer,List<Integer>> bots = new HashMap<>();
  Map<Integer, Integer> outputs = new HashMap<>();

  int min(List<Integer> xs) {
    int min = Integer.MAX_VALUE;
    for (Integer i : xs) {
      if (i < min) min = i;
    }
    return min;
  }
  
  int max(List<Integer> xs) {
    int max = Integer.MIN_VALUE;
    for (Integer i : xs) {
      if (i > max) max = i;
    }
    return max;
  }

  void add(int bot, int val) {
    List<Integer> res = bots.get(bot);
    if (res == null) {
      res = new ArrayList<>();
      bots.put(bot, res);
    }
    res.add(val);
  }

  public static void main(String [] args) {
    new AoC10a();
  }

  AoC10a() {
    Scanner scanner = new Scanner(System.in);
    List<String> instructions = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String instruct = scanner.nextLine();
      instructions.add(instruct);
    }

    for (String i : instructions) {
      if (i.startsWith("value ")) {
        String[] bits = i.split(" ");
        int val = Integer.parseInt(bits[1]);
        int bot = Integer.parseInt(bits[5]);
        add(bot, val);
      }
    }
    List<String> remaining = instructions;
    while(remaining.size() > 0) {
      remaining = runWhatWeCan(remaining);
    }
    
    int lcmp = 17;
    int hcmp = 61;
    
    for (Map.Entry<Integer, List<Integer>> bot : bots.entrySet()) {
      int l = min(bot.getValue());
      int h = max(bot.getValue());
      if (l == lcmp && h == hcmp) {
        System.out.println("Found: " + bot.getKey());
        System.out.println("Product: " + 
            (outputs.get(0) * outputs.get(1) * outputs.get(2)));
        System.exit(0);
      }
    }
  }

  List<String> runWhatWeCan(List<String> instructions) {
    List<String> defered = new ArrayList<>();
    for (String i : instructions) {
      if (i.startsWith("bot ")) {
        String[] bits = i.split(" ");
        int b = Integer.parseInt(bits[1]);
        List<Integer> blist = bots.get(b);
        if (blist == null || blist.size() != 2)  {
          defered.add(i);
          continue; 
        }
        
        int l = min(blist);
        int h = max(blist);
        int ol = Integer.parseInt(bits[6]);
        if ("bot".equals(bits[5])) {
          add(ol, l);
        } else if ("output".equals(bits[5])) {
          outputs.put(ol, l);
        } else {
          System.err.println("LOW ERROR");
        }

        int oh = Integer.parseInt(bits[11]);
        if ("bot".equals(bits[10])) {
          add(oh, h);
        } else if ("output".equals(bits[10])) {
          outputs.put(oh, h);
        } else {
          System.err.println("HIGH ERROR");
        }
      }
    }
    return defered;
  }

}
