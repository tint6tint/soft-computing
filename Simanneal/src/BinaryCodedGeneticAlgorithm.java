import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class BinaryCodedGeneticAlgorithm {
	int N = 30;

	public BinaryCodedGeneticAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	public int randomBinaryNumber() {
		Random rand = new Random();
		int binaryNumberDesired = rand.nextInt(2);
		return binaryNumberDesired;
	}

	public int[][] generateMatingPool() {
		int[][] arrayOfBinaryCode = new int[6][6];
		int i, j;
		for (i = 0; i < 6; i++) {
			for (j = 0; j < 6; j++) {
				arrayOfBinaryCode[i][j] = randomBinaryNumber();
			}
		}
		return arrayOfBinaryCode;
	}

	public Set<Integer> matingPair() {
		Random rand = new Random();
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < 6) {
			Integer next = rand.nextInt(6) + 1;
			generated.add(next);
		}
		return generated;
	}

	public int[][] generateParents(int[][] mattingPool, Set<Integer> generatedMatingPair) {
		int[][] parents = new int[6][6];
		int i = 0;
		int[] j = new int[3];
		for (int n : generatedMatingPair) {
			if (i == 3) {
				break;
			}
			j[i] = n;
			i++;
		}
		int[][] temp = mattingPool; 
		int incrementBytwo=1;
		while (incrementBytwo < 6) {
			i--;
			mattingPool[incrementBytwo]=temp[j[i]];
			incrementBytwo+=2;
		}
		parents=mattingPool;
		return parents;
	}

	public void print(int[][] a) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(" " + a[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryCodedGeneticAlgorithm test = new BinaryCodedGeneticAlgorithm();
		//System.out.println(test.randomBinaryNumber());
		int[][] testOfPool;
		testOfPool=test.generateMatingPool();
		test.print(testOfPool);
		System.out.print(test.matingPair());
		test.print(test.generateParents(testOfPool, test.matingPair()));
	}

}
