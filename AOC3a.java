import java.util.*;

class AOC3a {

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
    while (scanner.hasNextLine()) {
      int[] lens = parse(scanner.nextLine()); 
      if (lens[0] + lens[1] > lens[2] && 
          lens[1] + lens[2] > lens[0] && 
          lens[2] + lens[0] > lens[1]) {
        possible++;
      }
    }
    System.out.println("Possible: " + possible);
  }
}
