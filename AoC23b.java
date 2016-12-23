
import java.util.*;

class AoC23b {
  
  List<Asm> prog = new ArrayList<>();
  //int[] reg = new int[] { 12, 0, 0, 0 };
  int[] reg = new int[] { 7, 0, 0, 0 };
  int pc = 0; 

  public static void main(String[] args) {
    new AoC23b().run();
  }

  AoC23b() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        switch (parts[0]) {
          case "cpy":
            prog.add(new Cpy(parts[1], parts[2]));
            break;
          case "inc":
            prog.add(new Inc(parts[1]));
            break;
          case "dec":
            prog.add(new Dec(parts[1]));
            break;
          case "jnz":
            prog.add(new Jnz(parts[1], parts[2]));
            break;
          case "tgl":
            prog.add(new Tgl(parts[1]));
            break;
          default:
           System.err.println("unknown instruction: " + parts[0]);
        }
      }
    }
  }

  interface Asm {
    int apply();
    Asm toggle();
  }
   
  int offset(String x) {
    return x.charAt(0) - 'a';
  }

  abstract class Asm1 implements Asm {
    int o;
    String args;
    Asm1(int o, String args) {
      this.o = o;
      this.args = args;
    }
    Asm1(String o) {
      this.args = o;
      this.o = offset(o);
    }
  }
  
  abstract class Asm2 implements Asm {
    int x, y;
    boolean dx, dy;
    
    Asm2(int x, int y, boolean dx, boolean dy) {
      this.x = x; 
      this.y = y;
      this.dx = dx;
      this.dy = dy;
    }

    Asm2(String x, String y) {
      if (isReg(x)) {
        this.x = offset(x);
        this.dx = true;
      } else {
        this.x = Integer.parseInt(x);
      }
      
      if (isReg(y)) {
        this.y = offset(y);
        this.dy = true;
      } else {
        this.y = Integer.parseInt(y);
      }
    }
  }

  class Inc extends Asm1 {
    Inc(int o, String args) {
      super(o, args);
    }
    Inc(String x) {
      super(x);
    }
    public int apply() {
      reg[o]++;
      return pc + 1;
    }

    public Asm toggle() {
      return new Dec(o, args);
    }
  }
  
  class Dec extends Asm1 {
    Dec(int x, String args) {
      super(x, args);
    }

    Dec(String x) {
      super(x);
    }

    public int apply() {
      reg[o]--;
      return pc + 1;
    }

    public Asm toggle() {
      return new Inc(o, args);
    }
  }
  
  class Cpy extends Asm2 {
    Cpy(String x, String y) {
      super(x,y);
    }

    Cpy(int x, int y, boolean dx, boolean dy) {
      super(x,y,dx,dy);
    }

    public int apply() {
      if (dy) {
        reg[y] = dx ? reg[x] : x; 
      }
      return pc + 1;
    }

    public Asm toggle() {
      return new Jnz(x,y,dx,dy);
    }
  }

  class Jnz extends Asm2 {
    
    Jnz(int x, int y, boolean dx, boolean dy) {
      super(x,y,dx,dy);
    }

    Jnz(String x, String y) {
      super(x,y);
    }

    public int apply() {
      if ((dx ? reg[x] : x)  == 0) {
        return pc + 1; 
      } 
      return pc + (dy ? reg[y] : y);
    }
    
    public Asm toggle() {
      return new Cpy(x,y,dx,dy);
    }
  }

  class Tgl implements Asm  {
    int i;
    boolean di;
    String args;

    Tgl(String i) {
      this.args = i;
      if (isReg(i)) {
        this.i = offset(i);
        this.di = true;
      }
    }

    public int apply() {
      int i2 = pc + (di ? reg[i] : i);
      if (i2 >= prog.size()) { 
        return pc + 1;
      }
      prog.set(i2, prog.get(i2).toggle());
      return pc + 1;
    }

    public Asm toggle() {
      return new Inc(args); 
    }
  }

  void run() {
    while (pc < prog.size()) {
      Asm asm = prog.get(pc);
      pc = asm.apply();
    }
    System.out.println("Pc: " + pc + ", Regs: " + Arrays.toString(reg));
  }

  boolean isReg(String x)  {
    return x.charAt(0) >= 'a' && x.charAt(0) <= 'd';
  }
}
