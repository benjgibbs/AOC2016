import java.util.*;

class AoC19b {
  
  public static void main(String[] args) { 
    run(5);
    run(3014603);
  }

  static class Elf {
    final int id;
    Elf n;
    Elf(int id) { this.id = id; } 
  }

  static void run(int ne) { 
    Elf h = new Elf(1);
    Elf n = h;
    Elf m  = null;
    for (int i = 2; i <= ne; i++){
      n.n = new Elf(i);
      if (i == ne/2) {
        m = n.n;
      }
      n = n.n;
    }
    n.n = h;
   
    n = h; 
    int count = 0;
    while (n.n != n) {
      m.n = m.n.n;
      if (count++ % 2 == 0) {
        m = m.n;
      }
      n = n.n;
    }

    System.out.println("\n" + n.id + " has all the presents");
  }
}
