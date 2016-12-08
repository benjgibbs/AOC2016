import java.util.*;

class AOC3b {

  private static int[] parse(String line) {
    String[] parts = line.trim().split("\\s+");
    int[] result = new int[parts.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = Integer.parseInt(parts[i].trim());
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int possible = 0;
    List<Integer> tri1 = new ArrayList<>();
    List<Integer> tri2 = new ArrayList<>();
    List<Integer> tri3 = new ArrayList<>();

    while (scanner.hasNextLine()) {
      int[] lens = parse(scanner.nextLine());
      tri1.add(lens[0]);
      tri2.add(lens[1]);
      tri3.add(lens[2]);
    }
    List<Integer> all = new ArrayList<>(tri1);
    all.addAll(tri2);
    all.addAll(tri3);
    
    for(int i = 0; i < all.size(); i+= 3) {
      if (all.get(i) + all.get(i+1) > all.get(i+2) && 
          all.get(i+1) + all.get(i+2) > all.get(i) && 
          all.get(i+2) + all.get(i) > all.get(i+1)) {
        possible++;
      }
    }
    System.out.println("Possible: " + possible);
  }
}
