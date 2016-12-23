import java.util.*;

class AoC23a {
  
  List<String> prog = new ArrayList<String>();
  Map<String, Integer> rs = new HashMap<>();
  int pc = 0; 

  public static void main(String[] args) {
    new AoC23a().run();
  }

  AoC23a() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        prog.add(scanner.nextLine());
      }
    }
    
    rs.put("a", 7);
    rs.put("b", 0);
    rs.put("c", 0);
    rs.put("d", 0);
  }

  void run() {
    while (pc < prog.size()) {
      String line = prog.get(pc);
      String[] parts = line.split(" ");
      //System.out.println("\nPc: " + pc + ", Regs: " + rs + ", ins: " + line);
      //System.out.println(prog);
      switch (parts[0]) {
        case "cpy":
          cpy(parts);
          pc++;
          break;
        case "inc":
          inc(parts);
          pc++;
          break;
        case "dec":
          dec(parts);
          pc++;
          break;
        case "jnz":
          pc = jnz(parts);
          break;
        case "tgl":
          toggle(parts);
          pc++;
          break;
        default:
          System.err.println("unknown instruction: " + parts[0]);
          pc = Integer.MAX_VALUE;
      }
    }
    System.out.println("Pc: " + pc + ", Regs: " + rs);
  }

  boolean isReg(String x)  {
    return x.charAt(0) >= 'a' && x.charAt(0) <= 'd';
  }

  int intOrReg(String x) {
    if (isReg(x)) {
      return rs.get(x);
    }
    return  Integer.parseInt(x);
  }

  void toggle(String[] parts) {
    String x = parts[1];
    int o = intOrReg(x);
    int i = pc + o;
    if ( i >= prog.size()) 
      return;

    String ins = prog.get(i);
    switch (ins.substring(0,3)) {
      case "inc":
        prog.set(i, "dec" + ins.substring(3));
        break;
      case "dec":
      case "tgl":
        prog.set(i, "inc" + ins.substring(3));
        break;
      case "jnz":
        prog.set(i, "cpy" + ins.substring(3));
        break;
      case "cpy":
        prog.set(i, "jnz" + ins.substring(3));
        break;
      default:
        System.err.println("Unknown instruction!");
    }
  }

  void cpy(String[] parts) {
    String x = parts[1];
    String y = parts[2];
    if (isReg(y)) {
      rs.put(y, intOrReg(x));
    }
  }
  
  void inc(String[] parts) {
    String x = parts[1];
    rs.put(x, rs.get(x) + 1);
  }

  void dec(String[] parts) {
    String x = parts[1];
    rs.put(x, rs.get(x) - 1);
  }

  int jnz(String[] parts) {
    int x = intOrReg(parts[1]);
    int y = intOrReg(parts[2]);
    if (x == 0) {
      return pc + 1; 
    } 
    return pc + y;
  }
}
