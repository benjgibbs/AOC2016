import java.util.*;

class AoC19a {
  
  public static void main(String[] args) { 
    run(5);
    run(3014603);
  }

  static void run(int ne) { 
    int[] presents = new int[ne];
    
    for (int i = 0; i < ne; i++){
      presents[i] = 1;
    }
    
    int i = 0;
    while (true) {
      if (presents[i] != 0) {
        int j = (i + 1) % ne;
        while (presents[j] == 0) {
          j = (j+1) % ne;
        }
        if (i == j) {
          System.out.println((i+1) + " has all the presents");
          break;
        }
        presents[i] += presents[j];
        presents[j] = 0;
      }
      i = (i + 1) % ne;
    }  
  }
}
