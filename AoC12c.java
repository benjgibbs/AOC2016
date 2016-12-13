import java.util.*;

class AoC12c {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    

    StringBuilder builder = new StringBuilder();
    builder.append("final class Prog {").append("\n")
      .append("static int run() {").append("\n")
      .append("int a = 0, b = 1, c = 0, d= 0;").append("\n");

    int lineNum = 0;
    while (scanner.hasNextLine()) {
      builder.append("L").append(lineNum).append(": {"); 
      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      switch (parts[0]) {
        case "cpy":
          builder.append(parts[2]).append("=").append(parts[1]).append(";");
          break;
        case "inc":
          builder.append(parts[1]).append("++;");
          break;
        case "dec":
          builder.append(parts[1]).append("--;");
          break;
        case "jnz":
          builder.append("if (").append(parts[1]).append("!= 0) break L")
            .append(lineNum + Integer.parseInt(parts[2])).append(";");
          break;
      }
      builder.append("\n");
      lineNum++;
    }
    for (int i = 0; i < lineNum; i++) {
      builder.append("}").append("\n");
    }
    builder.append("L").append(lineNum).append(": return a;").append("\n")
      .append("}").append("\n")
      .append("}").append("\n");
    System.out.print(builder.toString());
  }
}
