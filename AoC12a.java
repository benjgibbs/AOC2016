import java.util.*;

class AoC12a {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    List<String> prog = new ArrayList<String>();
    while (scanner.hasNextLine()) {
      prog.add(scanner.nextLine());
    }
    
    Map<String, Integer> rs = new HashMap<>();
    rs.put("a", 0);
    rs.put("b", 0);
    rs.put("c", 1);
    rs.put("d", 0);
    int pc = 0; 
    while (pc < prog.size()) {
      String line = prog.get(pc);
      String[] parts = line.split(" ");
      //System.out.println("Pc: " + pc + ", Regs: " + rs + ", ins: " + line);
      switch (parts[0]) {
        case "cpy":
          copy(rs, parts);
          pc++;
          break;
        case "inc":
          inc(rs, parts);
          pc++;
          break;
        case "dec":
          dec(rs, parts);
          pc++;
          break;
        case "jnz":
          pc = jump(rs, parts, pc);
          break;
        default:
          System.err.println("unknown instruction: " + parts[0]);
          pc = Integer.MAX_VALUE;
      }
    }
    System.out.println("Pc: " + pc + ", Regs: " + rs);
  }

  static int intOrReg(Map<String, Integer>  rs, String x) {
    if (x.charAt(0) >= 'a' && x.charAt(0) <= 'd') {
      return rs.get(x);
    }
    return  Integer.parseInt(x);
  }

  static void copy(Map<String, Integer>  rs, String[] parts) {
    String x = parts[1];
    String y = parts[2];
    rs.put(y, intOrReg(rs, x));
  }
  
  static void inc(Map<String, Integer>  rs, String[] parts) {
    String x = parts[1];
    rs.put(x, rs.get(x) + 1);
  }

  static void dec(Map<String, Integer>  rs, String[] parts) {
    String x = parts[1];
    rs.put(x, rs.get(x) - 1);
  }

  static int jump(Map<String, Integer>  rs, String[] parts, int pc) {
    int x = intOrReg(rs, parts[1]);
    int y = Integer.parseInt(parts[2]);
    if (x == 0) {
      return pc + 1; 
    } 
    return pc + y;
  }
}
