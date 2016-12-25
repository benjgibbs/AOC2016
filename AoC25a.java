import java.util.*;

class AoC25a {
  
  List<Asm> prog = new ArrayList<>();
  int[] reg = new int[] { 0, 0, 0, 0 };
  
  int pc =0,  count = 0, start = 0;

  public static void main(String[] args) {
    new AoC25a().run();
  }

  void init(int i) {
    start = i; 
    reg = new int[] {i, 0 ,0 ,0};
    pc = 0;
    count = 0;
  }

  AoC25a() {
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
          case "out":
            prog.add(new Out(parts[1]));
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
    Asm1(int o) {
      this.o = o;
    }
    Asm1(String o) {
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
    Inc(int o) {
      super(o);
    }
    Inc(String x) {
      super(x);
    }
    public int apply() {
      reg[o]++;
      return pc + 1;
    }

    public Asm toggle() {
      return new Dec(o);
    }
  }
  
  class Dec extends Asm1 {
    Dec(int x) {
      super(x);
    }

    Dec(String x) {
      super(x);
    }

    public int apply() {
      reg[o]--;
      return pc + 1;
    }

    public Asm toggle() {
      return new Inc(o);
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

  abstract class AsmD1 implements Asm {
    int i;
    boolean di;
    String args;

    AsmD1(String i) {
      this.args = i;
      if (isReg(i)) {
        this.i = offset(i);
        this.di = true;
      }
    }

    public Asm toggle() {
      return new Inc(args); 
    }
  }

  class Tgl extends AsmD1  {

    Tgl(String i) {
      super(i);
    }

    public int apply() {
      int i2 = pc + (di ? reg[i] : i);
      if (i2 >= prog.size()) { 
        return pc + 1;
      }
      prog.set(i2, prog.get(i2).toggle());
      return pc + 1;
    }
  }

  class Out extends AsmD1 {
    Out(String s) {
      super(s);
    }

    public int apply() {
      return transmit( di ? reg[i] : i);
    }
  }
  
  int transmit(int i) {
    if (count > 1000) {
      System.out.println("Success! start=" + start);
      System.exit(0);
    }

    if (count++ % 2 == i) {
      return pc + 1;
    } else {
      return Integer.MAX_VALUE;
    }
  }

  void run() {
    for (int i = 0; ; i++) {
      init(i);
      while (pc < prog.size()) {
        Asm asm = prog.get(pc);
        pc = asm.apply();
      }
      //System.out.println("i: " + i + "Pc: " + pc + ", Regs: " + Arrays.toString(reg));
    }
  }

  boolean isReg(String x)  {
    return x.charAt(0) >= 'a' && x.charAt(0) <= 'd';
  }
}
