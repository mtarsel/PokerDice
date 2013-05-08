import java.util.Random;


public class PokerDie {
	
	public int sides = 6;
	public String topFace = "A";
	public int value = 14;
	public String[] faces = {"9","10","J","Q","K","A"};
	
	public String roll(){
		Random rand = new Random();
		int ran = rand.nextInt(sides);
		topFace = faces[ran];
		value = ran + 9;
		return topFace;
	}
	
	public String topFace(){
		return topFace;
	}
	
	public static void main(String[] args){
		boolean a = false;
		boolean b = true;
		boolean c = !a;
		System.out.println(c);
		c = !b;
		System.out.println(c);
	}
	

}