import java.util.Random;

public class simulatedAnnealing {
	 
	private double x, y;
	
	
	public simulatedAnnealing(double x, double y){
	      this.x=x;
	      this.y=y;
	}
	public simulatedAnnealing(){
		
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double calculate(double x2, double y2){
		double result;
		result= x2+y2-Math.pow(x2, 2)-Math.pow(y2, 2);
		return result;
	}
	public static double roundToHalf(double x) {
	    return (double) (Math.ceil(x * 2) / 2);
	}
	public simulatedAnnealing pairNewNumber(){
		simulatedAnnealing newNumber = new simulatedAnnealing();
		Random ran = new Random();
		double x1,x2;		
		x1= ran.nextDouble()*5;
		x2= ran.nextDouble()*5;
		x1=roundToHalf(x1);
		x2=roundToHalf(x2);
		newNumber.setX(x1);
		newNumber.setY(x2);
		return newNumber;
	}
	
	public simulatedAnnealing result(){
		int t=3000;
		final double terminateCriterion = 0.001;
		simulatedAnnealing n1= pairNewNumber();
		simulatedAnnealing n2= pairNewNumber();
		Random ran = new Random();
		double possibility;
		double firstResult,secondResult;
		while(t>5){			
			possibility = ran.nextInt(9)+1;
			possibility=possibility/10;
			firstResult=calculate(n1.x, n1.y);
			secondResult=calculate(n2.x, n2.y);
			double differenceBetweenFirstSecond =Math.abs((secondResult-firstResult));
			if(differenceBetweenFirstSecond>terminateCriterion){
				if(differenceBetweenFirstSecond>=0){
					n1=n2;
					n2=pairNewNumber();	
					System.out.println("The new point is ("+n2.x+" "+n2.y+")");
				}else{
					if(Math.exp(differenceBetweenFirstSecond)>possibility){
						n1=n2;
						n2=pairNewNumber();						
					}else{
						System.out.println("The terminate point is ("+n2.x+" "+n2.y+")");
					}
				}
			}else{
				System.out.println("The terminate point is ("+n2.x+" "+n2.y+")");
				break;
			}
			t=t/2;
		}
		
		
		return n2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		simulatedAnnealing example= new simulatedAnnealing();
		example.result();

	}

}
