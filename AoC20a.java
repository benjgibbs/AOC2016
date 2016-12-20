import java.util.*;

class AoC20a {
  public static void main(String[] args) {
    List<List<Long>> ranges = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split("-");
      long a = Long.parseLong(parts[0]);
      long b = Long.parseLong(parts[1]);

      List<Long> range = new ArrayList<>();
      range.add(a);
      range.add(b);

      ranges.add(range);
    }

    ranges.sort((a,b) -> {
      if (a.get(0) < b.get(0)) {
        return -1;
      } else if (a.get(0) > b.get(0)) {
        return 1;
      }
      return Long.signum(a.get(1) - b.get(1));
    });

    long min = Long.MAX_VALUE;
    for (List<Long> r : ranges) {
      if (r.get(0) <= min) {
        if (min == Long.MAX_VALUE || r.get(1) > min) {
          min = r.get(1) + 1;
        }
      } else {
        break;
      } 
    }
    System.out.println("Lower is: " + min);
  }
}
