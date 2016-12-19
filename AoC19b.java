import java.util.*;

class AoC19b {
  
  public static void main(String[] args) { 
    run(5);
    run(3014603);
  }

  static void run(int ne) { 
    List<Integer> elves = new ArrayList<Integer>();
    
    for (int i = 1; i <= ne; i++){
      elves.add(i);
    }
    
    int pi = 0; 
    while (elves.size() > 1) {
      //System.out.println("Elves: " + elves);
      int i = elves.get(pi);
      int pj = (pi + elves.size()/2) % elves.size();
      int j = elves.get(pj); 

      elves.remove(pj);
      //System.out.println(i + " steals from " + j);
      if (elves.size() == 1)  break;
      if (elves.size() % 10000 == 0) System.out.print(".");
      do {
        pi = (pi + 1) % elves.size();
      } while(i == elves.get(pi));
    }

    System.out.println("\n" + elves.get(0) + " has all the presents");
  }
}
