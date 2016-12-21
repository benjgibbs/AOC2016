import java.util.*;

class AoC21a {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		char[] chars = "abcdefgh".toCharArray();
		//char[] chars = "abcde".toCharArray();
		System.out.println(new String(chars));
		while (scanner.hasNextLine()){
			String line = scanner.nextLine();
      int X = 0, Y = 0;
			String[] words = line.split(" ");
			if ("swap".equals(words[0]) && "position".equals(words[1])) {
				X = Integer.parseInt(words[2]);
				Y = Integer.parseInt(words[5]);
				char t = chars[X];
			  chars[X] = chars[Y];
				chars[Y] = t;
			} else if ("swap".equals(words[0]) && "letter".equals(words[1])) {
				char X2 = words[2].charAt(0);
				char Y2 = words[5].charAt(0);
				for (int i = 0; i < chars.length; i++){
					if (chars[i] == X2) {
						chars[i] = Y2;
					} else if (chars[i] == Y2) {
						chars[i] = X2;
					}
				}
			} else if ("rotate".equals(words[0]) && "left".equals(words[1])) {
				X = Integer.parseInt(words[2]);
				char[] tmp = new char[chars.length];
				for (int i = 0; i < chars.length; i++){
					tmp[i] = chars[(i+X) % chars.length];
				}
				chars = tmp;
			} else if ("rotate".equals(words[0]) && "right".equals(words[1])) {
				X = Integer.parseInt(words[2]);
				char[] tmp = new char[chars.length];
				for (int i = 0; i < chars.length; i++){
					tmp[(i+X) % chars.length] = chars[i];
				}
				chars = tmp;
			} else if ("rotate".equals(words[0]) && "based".equals(words[1])) {
				int i = 0;
				char c = words[6].charAt(0);
				for (; i < chars.length; i++) {
					if (chars[i] == c){
						break;
					}
				}
				X = 1 + i;
				if (i >= 4) X += 1;
				char[] tmp = new char[chars.length];
				for (i = 0; i < chars.length; i++){
					tmp[(i+X) % chars.length] = chars[i];
				}
				chars = tmp;
			} else if ("reverse".equals(words[0])) {
				X = Integer.parseInt(words[2]);
				Y = Integer.parseInt(words[4]);
				char[] tmp = new char[chars.length];
				for (int i = 0; i < chars.length; i++){
					if (i < X || i > Y) {
						tmp[i] = chars[i];
					} else {
						tmp[i] = chars[Y - i + X];
					}
				}
				chars = tmp;
				
			} else if ("move".equals(words[0]) ) {
				X = Integer.parseInt(words[2]);
				Y = Integer.parseInt(words[5]);
				StringBuilder b = new StringBuilder(new String(chars));
				char c = chars[X];
				b.deleteCharAt(X);
				b.insert(Y, c);
				chars = b.toString().toCharArray();
			}
      
			System.out.println(String.format("%50s (x=%d, y=%d) : %s", line, X, Y, new String(chars)));
		}
		
	}
	//gfadhecb
  //fdcgeahb

}