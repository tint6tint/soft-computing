import java.util.Random;

public class SteepestDescentMethod {
	
	double x,y;	
	public SteepestDescentMethod(double x, double y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	public SteepestDescentMethod(){
		
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
	double derivativeOfX(SteepestDescentMethod s){
		x=s.getX();
		y=s.getY();
		return (8*x-6*y-4);
	}
	double derivativeOfY(SteepestDescentMethod s){
		x=s.getX();
		y=s.getY();
		return ((-6)*x + 6*y);
	}
	double originalFunction(SteepestDescentMethod s){
		x=s.getX();
		y=s.getY();
		double result;
		result = 4*Math.pow(x, 2)+3*Math.pow(y, 2)-6*x*y-4*x;
		return result;
	}
	SteepestDescentMethod generateNewPoints(){
		SteepestDescentMethod s = new SteepestDescentMethod(0,0);
		Random rand= new Random();
		double x;
		x=rand.nextInt(21) - 10;
		double y;
		y=rand.nextInt(21) - 10;
		s.setX(x);
		s.setY(y);
		return s;
	}
	SteepestDescentMethod calculateMinusDeltaOfX(SteepestDescentMethod s){
		double resultOfNewX;		
		resultOfNewX = -derivativeOfX(s);
		double resultOfNewY = derivativeOfY(s);
		SteepestDescentMethod sNew= new SteepestDescentMethod();
		sNew.setX(resultOfNewX);   
		sNew.setY(resultOfNewY);
		return sNew;		
	}
	/**
	 * since it is only applies to this particular equation which start point
	 * from (0,0). So in order to make it not so complicated, the follow equation
	 * just assume that sfy.y=0
	 */
	
	SteepestDescentMethod getFinalResult(SteepestDescentMethod s){
		double lambda,x,y;
		x=calculateMinusDeltaOfX(s).x;
		y=calculateMinusDeltaOfX(s).y;	
		System.out.println("x= " + x);
		lambda=1/Math.pow(x,2);
		System.out.println("lambda = " + lambda);
		double resultOfX;
		resultOfX=x*lambda;
		s.setX(resultOfX);
		s.setY(y);
		System.out.println("x= " + s.x);
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Although I created the new points generator, but I didnt use it 
		 * in this particular equation.
		 */
		SteepestDescentMethod example= new SteepestDescentMethod(0,0);
		example.getFinalResult(example);
		
	}
}
