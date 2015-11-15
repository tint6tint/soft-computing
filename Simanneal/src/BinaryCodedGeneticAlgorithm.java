import java.util.Iterator;
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
	int[][] decodedValueOfFunction(int[][] doubleArray){
		int[][] result = new int[6][2];
		int i, j;
		int[] dv= {4,2,1};
		for (i = 0; i < 6; i++) {
			int temp1=0;
			for (j = 0; j < 3; j++) {				
				temp1+=dv[j]*doubleArray[i][j];
			}
			result[i][0]=temp1;
			int temp2=0;
			for (j = 3; j < 6; j++) {
				temp2+=dv[j]*doubleArray[i][j];
			}
			result[i][1]=temp2;			
		}
		return result;
	}
	double[] returnValueOfFunction(int[][] decodedValue){
		double result[]=new double[6];
		int i=0;
		for (i = 0; i < 6; i++) {
			int valueOfXOne=decodedValue[i][0];
			int valueOfXTwo=decodedValue[i][1];
			result[i]=valueOfXOne+valueOfXTwo-2*Math.pow(valueOfXOne, 2)-2*Math.pow(valueOfXTwo, 2);
		}
		return result;
	}
	double[] returnValueOfCapitalFunction(double[] lowerCaseFValue){
		double result[]=new double[6];
		int i=0;
		for (i = 0; i < 6; i++) {
			double temp;
			temp=1/(1+lowerCaseFValue[i]);
			result[i]=temp;
		}
		return result;
	}
	double returnAverageValueOfCapitalFunction(double[] capitalFunctionValue){
		double result;
		int i=0;
		double temp=0;
		for (i = 0; i < 6; i++) {			
			temp+=capitalFunctionValue[i];
		}
		result=temp/20;
		return result;
	}
	double[] returnValueOfA(int[] decodedValue, double averageFitness){
		double result[]=new double[6];
		return result;
	}
	double[] returnValueOfB(int[][] decodedValue){
		double result[]=new double[6];
		return result;
	}
	double[] returnValueOfC(int[][] decodedValue){
		double result[]=new double[6];
		return result;
	}
	double[] returnValueOfD(int[][] decodedValue){
		double result[]=new double[6];
		return result;
	}
	double[] returnValueOfE(int[][] decodedValue){
		double result[]=new double[6];
		return result;
	}
	double[] returnValueOfF(int[][] decodedValue){
		double result[]=new double[6];
		return result;
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
			Integer next = rand.nextInt(6);
			generated.add(next);
		}
		return generated;
	}
	void swap(int[][] result, int[][] original, int j[], int i) {
		int incrementBytwo = 1;
		while (incrementBytwo < 6) {
			int[] temp1= new int[6];
			i--;
			int[] temp2 = new int[6];
			temp2=original[incrementBytwo-1];
			result[incrementBytwo-1]=temp2;
			temp1= original[j[i]];
			result[incrementBytwo]=temp1;
			incrementBytwo += 2;
		}
	}
	public int[][] generateParents(int[][] mattingPool, Set<Integer> generatedMatingPair) {
		int[][] parents = new int[6][6];
		int[][] copy= copyArray(mattingPool);
		int i = 0;
		int[] j = new int[3];
		Iterator<Integer> it = generatedMatingPair.iterator();
		do {
			j[i] = it.next();
			System.out.println(j[i]);
			i++;
		} while (i < 3);
		swap(parents, copy,j, i);				
		return parents;
	}
	void swapCrossover(int[][] array, int row1, int col1, int row2, int col2) {
	    int temp = array[row1][col1];
	    array[row1][col1] = array[row2][col2];
	    array[row2][col2] = temp;
	}
	
	private int[][] copyArray(int [][] source) {
		int [][] result = new int[source.length][source[0].length];
		for (int i = 0; i < result.length; i ++) {
			for (int j = 0; j < result[i].length; j++) {
				result [i][j] = source[i][j];
			}
		}
		return result;
	}
	
	public int[][] crossover(int[][] crossover) {
		double probabilityOfCrossover = 0.9;
		Random random = new Random();
		int[][] new1;
		//ects as the value of crossover changes, the value of
		// parents changes too
		 new1=crossover;
		double randomProbability;
		int i = 0;
		int crossoverDigit;
		int[][] copy= copyArray(crossover);
		while (i < 6){
			crossoverDigit=2;
			//int crossoverDigit = random.nextInt(5) + 1;
			randomProbability = random.nextDouble();
			System.out.println(randomProbability);
			if(randomProbability <= probabilityOfCrossover) {			
				while (crossoverDigit < 6) {
					int h=i+1;
					swapCrossover(copy, i, crossoverDigit, h, crossoverDigit);
									
					crossoverDigit++;					
				}
			}else{}			
			i += 2;
		}

		return copy;
	}

	public void print(int[][] a) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(" " + a[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryCodedGeneticAlgorithm test = new BinaryCodedGeneticAlgorithm();
		// System.out.println(test.randomBinaryNumber());
		int[][] testOfPool = new int[6][6];
		testOfPool = test.generateMatingPool();
		test.print(testOfPool);
		testOfPool = test.crossover(testOfPool);
		test.print(testOfPool);
		Set<Integer> generated = new LinkedHashSet<Integer>();
		generated=test.matingPair();
		System.out.println(generated);
		testOfPool = test.generateParents(testOfPool, generated);
		test.print(testOfPool);
		testOfPool = test.crossover(testOfPool);
		test.print(testOfPool);
		// Random random = new Random();
		// System.out.println(random.nextDouble());
	}

}
