
import java.math.BigDecimal;
import java.util.Random;


public class BinaryCodedGeneticAlgorithm {
	public final static int N = 30;
	public final static int row=20;
	public final static int column=20;
	public final static int numberOfParameters=2;
	public final static double lowerBoundry=0;
	public final static double upperBoundry=5;
	public final static int precision=5;
	

	public BinaryCodedGeneticAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	public int randomBinaryNumber() {
		Random rand = new Random();
		int binaryNumberDesired = rand.nextInt(2);
		return binaryNumberDesired;
	}
	
	public int[][] generateMatingPool() {
		int[][] arrayOfBinaryCode = new int[row][column];
		int i, j;
		for (i = 0; i < row; i++) {
			for (j = 0; j < column; j++) {
				arrayOfBinaryCode[i][j] = randomBinaryNumber();
			}
		}
		return arrayOfBinaryCode;
	}
	public int[][] decodedValueOfParameters(int[][] doubleArray){
		int[][] result = new int[row][numberOfParameters];
		int i, j;
		int[] dv= {712,356,128,64,32,16,8,4,2,1};
		for (i = 0; i < row; i++) {
			int temp1=0;
			int n = 0,m=0;
			for (j = 0; j < column/numberOfParameters; j++) {				
				temp1+=dv[n]*doubleArray[i][j];
				n++;
			}
			result[i][0]=temp1;
			int temp2=0;
			for (j = column/numberOfParameters; j < column; j++) {
				temp2+=dv[m]*doubleArray[i][j];
				m++;
			}
			result[i][1]=temp2;			
		}
		return result;
	}
	public double[][] returnFinalDecodedValuesOfParameters(int[][] decodedValues){
		double[][] result = new double[row][numberOfParameters];
		int power=column/numberOfParameters;
		for(int i = 0; i < row; i++) {
			result[i][0]=(lowerBoundry+(upperBoundry-lowerBoundry)*decodedValues[i][0])/Math.pow(2, power);
			result[i][1]=(lowerBoundry+(upperBoundry-lowerBoundry)*decodedValues[i][1])/Math.pow(2, power);
			
		}
		return result;
	}
	public double[] returnValueOfFunction(double[][] finalDecodedValuesOfParameters){
		double result[]=new double[row];
		int i=0;
		for (i = 0; i < finalDecodedValuesOfParameters.length; i++) {
			double valueOfXOne=finalDecodedValuesOfParameters[i][0];
			double valueOfXTwo=finalDecodedValuesOfParameters[i][1];
			//result[i]=(valueOfXOne+valueOfXTwo)-2*Math.pow(valueOfXOne, 2)-2*Math.pow(valueOfXTwo, 2);
			double powerTwoOfOne= Math.pow(valueOfXOne, 2);
			double powerTwoOfTwo= Math.pow(valueOfXTwo, 2);
			double firstPart=Math.pow((powerTwoOfOne+valueOfXTwo-11),2);
			double secondPart= Math.pow((valueOfXOne+powerTwoOfTwo-7), 2);
			result[i]=firstPart+secondPart;
		}
		
		return result;
	}
	public double[] returnValueOfCapitalFunction(double[] lowerCaseFValue){
		double result[]=new double[row];
		int i=0;
		for (i = 0; i < row; i++) {
			double temp;
			temp=1/(1+lowerCaseFValue[i]);
			result[i]=temp;
		}
		return result;
	}
	public double returnAverageValueOfCapitalFunction(double[] capitalFunctionValue){
		double result;
		int i=0;
		double temp=0;
		for (i = 0; i < capitalFunctionValue.length; i++) {			
			temp+=capitalFunctionValue[i];
		}
		result=temp/row;
		return result;
	}
	public double[] returnValueOfA(double[] decodedValue, double averageFitness){
		double result[]=new double[row];
		int i=0;	
		for (i = 0; i < decodedValue.length; i++) {
			double temp;
			temp=decodedValue[i]/averageFitness;
			result[i]=temp;
		}
		return result;
	}
	public double[] returnValueOfB(double[] valueOfA){
		double result[]=new double[row];
		int i=0;
		
		for (i = 0; i < valueOfA.length; i++) {
			double temp;
			temp=valueOfA[i]/row;
			result[i]=temp;
		}
		return result;
	}
	public double[] returnValueOfC(double[] valueOfB){
		double result[]=new double[row];
		double temp=0;
		for (int i = 0; i < valueOfB.length; i++) {		
			temp+=valueOfB[i];
			result[i]=temp;
		}
		return result;
	}
	public double[] returnValueOfD(){
		double result[]=new double[row];
		Random randDoubleValue= new Random();
		for (int i = 0; i < result.length; i++) {					
			result[i]= randDoubleValue.nextDouble();
		}
		return result;
	}
	public int[] returnValueOfE(double[] valueOfD, double[] valueOfC){
		int result[]=new int[row];
		for(int i=0; i<valueOfD.length; i++){
			for(int b=0;b<valueOfC.length;b++){
				if(valueOfD[i]>=valueOfC[b]){
					continue;
				}else{
					result[i]=b;
					break;
				}
			}
		}		
		return result;
	}
	public int[] returnValueOfF(int[] valueOfE){
		int result[]=new int[row];
		for(int i=0; i<row; i++){
			int temp=0;
			for(int b=0;b<valueOfE.length;b++){
				if(i==valueOfE[b]){
					temp++;
				}else{}
				result[i]=temp;
				
			}
		}
		return result;
	}
	/*public Set<Integer> matingPair() {
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
	}*/
	public int[][] generateParents(int[][] mattingPool, int[] valueOfE) {
		int[][] parents = new int[row][column];
		int[][] copy= copyArray(mattingPool);
		for(int i=0; i<copy.length;i++){
			parents[i]=copy[valueOfE[i]];
		}
						
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
	
	public int[][] crossover(int[][] selection) {
		double probabilityOfCrossover = 0.9;
		Random random = new Random();
/**
 * affects as the value of crossover changes, the value of parents changes too
 * Solved by add copyArray
 * array.clone only works on one dimensional array, it clone the index but can not change the sub object.
 * In the essence, do not works for two dimensional array.(which is array contains other array)
 */		
		double randomProbability;
		int i = 0;
		int crossoverDigit;
		int[][] copy= copyArray(selection);
		while (i < copy.length){
			//crossoverDigit=2;
			crossoverDigit = random.nextInt(column-1) + 1;
			randomProbability = random.nextDouble();
			//System.out.println(randomProbability);
			if(randomProbability <= probabilityOfCrossover) {			
				while (crossoverDigit < copy[0].length) {
					int h=i+1;
					swapCrossover(copy, i, crossoverDigit, h, crossoverDigit);
									
					crossoverDigit++;					
				}
			}else{}			
			i += 2;
		}

		return copy;
	}
	public int bitWise(int i){
		if(i==0){
			i=1;
		}else{
			i=0;
		}
		return i;
	}
	public int[][] mutation(int[][] crossover){
		double mutationProbability= 0.05;
		double randomProbability;
		Random rand= new Random();
		
		int[][] result= copyArray(crossover);
		for(int i=0; i<crossover.length;i++){
			for(int j=0; j<crossover[i].length;j++){
				randomProbability=rand.nextDouble();
				
				if(randomProbability<mutationProbability){
					//System.out.println(i+" "+j+" "+ randomProbability);
					result[i][j]=bitWise(result[i][j]);
				}else{}
			}
		}
		return result;
	}
	public void printDouble(double[] a){
		for (int i = 0; i < a.length; i++) {
			a[i]=round(a[i], precision, BigDecimal.ROUND_HALF_UP);
			System.out.print(" " + a[i] + ",");
			System.out.println();
		}
		System.out.println();
	}
	public void printInt(int[] a){
		for (int i = 0; i < a.length; i++) {
			System.out.print(" " + a[i] + ",");
			System.out.println();
		}
		System.out.println();
	}
	public void print(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(" " + a[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	public void printDoubleArray(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j]=round(a[i][j], precision, BigDecimal.ROUND_HALF_UP);
				System.out.print(" " + a[i][j] + ",");
			}
			System.out.println();
		}
		System.out.println();
		
	}
	public static double round(double unrounded, int precision, int roundingMode)
	{
	    BigDecimal bd = new BigDecimal(unrounded);
	    BigDecimal rounded = bd.setScale(precision, roundingMode);
	    return rounded.doubleValue();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryCodedGeneticAlgorithm test = new BinaryCodedGeneticAlgorithm();
		// System.out.println(test.randomBinaryNumber());
		int[][] testOfPool = new int[row][column];
		testOfPool = test.generateMatingPool();
		test.print(testOfPool);
		
		int[][] decodedValueOfParameter= new int[row][numberOfParameters];
		decodedValueOfParameter=test.decodedValueOfParameters(testOfPool);
		System.out.println("dv x1,x2 = ");
		//test.print(decodedValueOfParameter);
		
		double[][] finalDecodedValuesOfParameters= new double[row][numberOfParameters];
		finalDecodedValuesOfParameters=test.returnFinalDecodedValuesOfParameters(decodedValueOfParameter);
		System.out.println("dv in formula x1,x2 = ");
		//test.printDoubleArray(finalDecodedValuesOfParameters);
		
		double[] valueOfFunction= new double[row];
		valueOfFunction= test.returnValueOfFunction(finalDecodedValuesOfParameters);
		System.out.println("Value of x1,x2 = ");
		//test.printDouble(valueOfFunction);
		
		double[] valueOfCapitalFFunction= new double[row];
		valueOfCapitalFFunction= test.returnValueOfCapitalFunction(valueOfFunction);
		System.out.println("Value of F(x1,x2) = ");
		//test.printDouble(valueOfCapitalFFunction);
		
		double average;
		average=test.returnAverageValueOfCapitalFunction(valueOfCapitalFFunction);
		average=round(average, precision, BigDecimal.ROUND_HALF_UP);
		System.out.println("average f = " + average +"\n");
		
		double[] a = new double[row];
		a=test.returnValueOfA(valueOfCapitalFFunction, average);
		System.out.println("Value of A = ");
		//test.printDouble(a);
		
		double[] b = new double[row];
		b=test.returnValueOfB(a);
		System.out.println("Value of B = ");
		//test.printDouble(b);
		
		double[] c = new double[row];
		c=test.returnValueOfC(b);
		System.out.println("Value of C = ");
		//test.printDouble(c);
		
		double[] d = new double[row];
		d=test.returnValueOfD();
		System.out.println("Value of D = ");
		//test.printDouble(d);
		
		int[] e = new int[row];
		e=test.returnValueOfE(d,c);
		System.out.println("Value of E = ");
		test.printInt(e);
		
		int[] f = new int[row];
		f=test.returnValueOfF(e);
		System.out.println("Value of F = ");
		//test.printInt(f);
		
		int[][] mattingPool= new int[row][column];
		mattingPool=test.generateParents(testOfPool, e);
		System.out.println("Selection = ");
		test.print(mattingPool);
		
		int[][] crossover= new int[row][column];
		crossover=test.crossover(mattingPool);
		System.out.println("After crossover = ");
		test.print(crossover);
		
		int[][] mutation= new int[row][column];
		mutation= test.mutation(crossover);
		System.out.println("After Mutation =");
		test.print(mutation);
		
		for(int i=1; i<N;i++){
			testOfPool=test.copyArray(mutation);
			System.out.println(i+"th Generation");
			decodedValueOfParameter=test.decodedValueOfParameters(testOfPool);
			finalDecodedValuesOfParameters=test.returnFinalDecodedValuesOfParameters(decodedValueOfParameter);
			valueOfFunction= test.returnValueOfFunction(finalDecodedValuesOfParameters);
			valueOfCapitalFFunction= test.returnValueOfCapitalFunction(valueOfFunction);
			average=test.returnAverageValueOfCapitalFunction(valueOfCapitalFFunction);
			average=round(average, precision, BigDecimal.ROUND_HALF_UP);
			System.out.println("average f = " + average +"\n");
			a=test.returnValueOfA(valueOfCapitalFFunction, average);
			b=test.returnValueOfB(a);
			c=test.returnValueOfC(b);
			d=test.returnValueOfD();
			e=test.returnValueOfE(d,c);
			f=test.returnValueOfF(e);
			mattingPool=test.generateParents(testOfPool, e);
			crossover=test.crossover(mattingPool);
			mutation= test.mutation(crossover);
			
			
		}

		//testOfPool = test.crossover(testOfPool);
		//test.print(testOfPool);
		// Random random = new Random();
		// System.out.println(random.nextDouble());
	}

}
