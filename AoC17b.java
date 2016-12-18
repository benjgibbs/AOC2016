import java.util.*;
import java.security.*;
import java.math.*;

class AoC17b {
	
	private MessageDigest md;
	
	private AoC17b() throws Exception {
		md = MessageDigest.getInstance("MD5");
	}

	private String hash(String input) throws Exception {
		byte[]  bytes = (input).getBytes("UTF-8");
        byte[] dg = md.digest(bytes);
        BigInteger bi = new BigInteger(1, dg);
        String s = bi.toString(16);
		int len = s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = len; i < 32; i++){
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}
	
	private boolean canGo(Character c){
		return c >= 'b' && c <='f';
	}
	
	boolean canGoUp(String str){
		return canGo(str.charAt(0));
	}
	
	boolean canGoDown(String str){
		return canGo(str.charAt(1));
	}
	
	boolean canGoLeft(String str){
		return canGo(str.charAt(2));
	}
	
	boolean canGoRight(String str){
		return canGo(str.charAt(3));
	}
	
	class Move {
		final String passCode;
		final int x;
		final int y;
		Move(String passCode, int x, int y){
			this.passCode = passCode; this.x = x; this.y = y;
		}
	}
	
	void run() throws Exception {
		check("hijkl");
		check("ihgpwlah");
		check("kglvqrro");
		check("ulqzkmiv");
		check("veumntbg");
	}
	void check(String passCode) throws Exception {
		
		Queue<Move> q = new LinkedList<>();
		q.add(new Move(passCode, 0, 0));
		
		int longest = 0;
		while (!q.isEmpty()){
			Move m = q.poll();
			String hash = hash(m.passCode);

			if (m.x == 3 && m.y == 3){
				String path = m.passCode.substring(passCode.length());
				if (longest < path.length()) {
					longest = path.length();
				}
			} else {
				if (canGoUp(hash) && m.y != 0){
					q.add(new Move(m.passCode + "U", m.x, m.y-1));
				}
				if (canGoDown(hash) && m.y != 3) {
					q.add(new Move(m.passCode + "D", m.x, m.y+1));
				}
				if (canGoLeft(hash) && m.x != 0) {
					q.add(new Move(m.passCode + "L", m.x-1, m.y));
				}
				if (canGoRight(hash) && m.x != 3) {
					q.add(new Move(m.passCode + "R", m.x+1, m.y));
				}
			}
		}
		
		System.out.println(passCode + ": Longest=" + longest);
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("AoC17b");
		new AoC17b().run();
	}
}