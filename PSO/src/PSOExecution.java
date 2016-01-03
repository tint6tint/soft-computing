import java.util.Random;
import java.util.Vector;

public class PSOExecution {
	final private int SWARM_SIZE = 30;
	final private int MAX_ITERATION = 300;
	final private double C1 = 2.0;
	final private double C2 = 2.0;
	final private double W_UP = 1.0;
	final private double W_LO = 0.0;
	private Vector<Particle> swarm = new Vector<Particle>();
	private double[] pBest = new double[SWARM_SIZE];
	private Vector<Position> pBestLoc = new Vector<Position>();
	private double gBest;
	private Position gBestLoc;
	private double[] fitnessList = new double[SWARM_SIZE];

	private void initializeSwarm() {
		Particle p;
		Random generator = new Random();

		for (int i = 0; i < SWARM_SIZE; i++) {
			p = new Particle();
			double posX = generator.nextDouble() * 3.0 + 1.0;
			double posY = generator.nextDouble() * 2.0 - 1.0;
			p.setLocation(new Position(posX, posY));

			double velX = generator.nextDouble() * 2.0 - 1.0;
			double velY = generator.nextDouble() * 2.0 - 1.0;
			p.setVelocity(new Velocity(velX, velY));

			swarm.add(p);
		}
	}
	public static int getMinPos(double[] list) {
		int pos = 0;
		double minValue = list[0];
		
		for(int i=0; i<list.length; i++) {
			if(list[i] < minValue) {
				pos = i;
				minValue = list[i];
			}
		}
		
		return pos;
	}
	
	public void calculateAllFitness(){
		for(int i=0; i<SWARM_SIZE; i++) {
			fitnessList[i] = swarm.get(i).getFitness();
		}
	}
	public void execute() {
		Random generator = new Random();
		initializeSwarm();

		int t = 0;
		double w;

		while (t < MAX_ITERATION) {
			// calculate corresponding f(i,t) corresponding to location x(i,t)
			calculateAllFitness();

			// update pBest
			if (t == 0) {
				for (int i = 0; i < SWARM_SIZE; i++) {
					pBest[i] = fitnessList[i];
					pBestLoc.add(swarm.get(i).getLocation());
				}
			} else {
				for (int i = 0; i < SWARM_SIZE; i++) {
					if (fitnessList[i] < pBest[i]) {
						pBest[i] = fitnessList[i];
						pBestLoc.set(i, swarm.get(i).getLocation());
					}
				}
			}

			int bestIndex = getMinPos(fitnessList);
			// update gBest
			if (t == 0 || fitnessList[bestIndex] < gBest) {
				gBest = fitnessList[bestIndex];
				gBestLoc = swarm.get(bestIndex).getLocation();
			}

			w = W_UP - (((double) t) / MAX_ITERATION) * (W_UP - W_LO);

			for (int i = 0; i < SWARM_SIZE; i++) {
				// update particle Velocity
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				double lx = swarm.get(i).getLocation().getX();
				double ly = swarm.get(i).getLocation().getY();
				double vx = swarm.get(i).getVelocity().getX();
				double vy = swarm.get(i).getVelocity().getY();
				double pBestX = pBestLoc.get(i).getX();
				double pBestY = pBestLoc.get(i).getY();
				double gBestX = gBestLoc.getX();
				double gBestY = gBestLoc.getY();

				double newVelX = (w * vx) + (r1 * C1) * (pBestX - lx) + (r2 * C2) * (gBestX - lx);
				double newVelY = (w * vy) + (r1 * C1) * (pBestY - ly) + (r2 * C2) * (gBestY - ly);
				swarm.get(i).setVelocity(new Velocity(newVelX, newVelY));

				// update particle Location
				double newPosX = lx + newVelX;
				double newPosY = ly + newVelY;
				swarm.get(i).setLocation(new Position(newPosX, newPosY));
			}
			System.out.println("ITERATION " + t + ": ");
			System.out.println("     Best X: " + gBestLoc.getX());
			System.out.println("     Best Y: " + gBestLoc.getY());
			t++;
		}
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println("     Best X: " + gBestLoc.getX());
		System.out.println("     Best Y: " + gBestLoc.getY());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PSOExecution ex= new PSOExecution();
		ex.execute();
	}

}
