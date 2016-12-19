import java.util.*;

class AoC19a {
  
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
    bruteForce(ne);
    josephus(ne);
    josephus2(ne);
  }

  static void josephus(int ne) {
    int i = 1;
    while (i < ne) {
      i *= 2;
    }
    i /= 2;
    int l = ne - i;
    System.out.println((2 * l + 1) + " by Josepheus");
  }
  
  static void josephus2(int ne) {
    String s = Integer.toString(ne, 2);
    int i = s.indexOf("1");
    s = s.substring(i+1) + "1";
    System.out.println(Integer.parseInt(s, 2) + " by Josepheus bit trick");
  }

  static void bruteForce(int ne) {
    Elf h = new Elf(1);
    Elf n = h; 
    for (int i = 2; i <= ne; i++) {
      n.n = new Elf(i);
      n = n.n;
    }
    n.n = h;
    
    n = h; 
    while (n != n.n) {
      n.n = n.n.n;
      n = n.n;
    }

    System.out.println(n.id + " has all the presents");
  }
}
