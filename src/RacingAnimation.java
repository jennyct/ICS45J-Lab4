import java.util.ArrayList;

/**
 * Represents a collection of racing smiley faces that race one another, 
 * each completing a set number of laps
 * @author Stephen Em and Jenny Tang
 *
 */
public class RacingAnimation implements RacingAnimationInterface {
	
	private static final int REVERSE_DIRECTION = -1;
	
	private String fastestSmileyName;
	private String slowestSmileyName;
	
	private int fewestTicks;
	private int mostTicks;
	private double averageTicks;
	
	private String statisticsTitle = "Race Statistics";
	
	private ArrayList<RacingSmiley> racers;
	private RacingDisplay display;
	
	/** 
	 * The constructor for the class which takes in RacingGroup
	 * and RacingDisplay. Default class constructor.
	 * @param g - RacingGroup of RacingSmileys to race
	 * @param d - RacingDisplay to display
	 */
	public RacingAnimation(RacingGroup g, RacingDisplay d) {
		racers = g.getRacers();
		display = d;
		display.repaint();
	}
	
	/** 
	 * Called once in RacerFrame to show the running race when GO! is pressed
	 * Logic of how the race will be animated
	 */
	@Override
	public void animate() {
		do {
			for (RacingSmiley racer : racers) {
				if (!racer.finishedRace()) {
					moveCntSmiley(racer);
				}
			}
			display.repaint();
			pause(100);
		}
		while (!isRaceDone(racers));
		computeStatistics();
	}
	
	private void pause(int millisecs)
	{
		try
		{
			Thread.sleep(millisecs);
		}
		catch (InterruptedException e)
		{
		}
	}
	
	/**
	 * Determine if race is done
	 * @param racers - RacingSmiley ArrayList
	 * @return true if race is done, false otherwise
	 */
	private boolean isRaceDone(ArrayList<RacingSmiley> racers) {
		for (RacingSmiley racer : racers) {
			if (!racer.finishedRace()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Compute the necessary statistics to display
	 */
	private void computeStatistics() {
		if (racers.size() == 0) {
			statisticsTitle = "No smileys to race!";
		}
		else {
			computeAverageTicks();
			computeFewestTicks();
			computeMostTicks();
		}
	}
	
	/**
	 * Compute the average ticks
	 */
	private void computeAverageTicks() {
		double sumOfTicks = 0;
		for (RacingSmiley racer: racers) {
			sumOfTicks += racer.getTicks();
		}
		averageTicks = sumOfTicks/(double)racers.size();
	}
	
	/**
	 * Compute the fastest RacingSmiley and obtain
	 * the amount of fewest ticks
	 */
	public void computeFewestTicks() {
		fewestTicks = Integer.MAX_VALUE;
		fastestSmileyName = "No smiley racers to race.";
		for (RacingSmiley racer: racers) {
			if (racer.getTicks() < fewestTicks) {
				fewestTicks = racer.getTicks();
				fastestSmileyName = racer.getSmileyName();
			}
		}
	}
	
	/**
	 * Compute the slowest RacingSmiley and obtain
	 * the amount of most ticks
	 */
	public void computeMostTicks() {
		slowestSmileyName = "No smiley racers to race.";
		mostTicks = Integer.MIN_VALUE;
		for (RacingSmiley racer: racers) {
			if (racer.getTicks() > mostTicks) {
				mostTicks = racer.getTicks();
				slowestSmileyName = racer.getSmileyName();
			}
		}
	}


	@Override
	public ArrayList<RacingSmiley> getRacers() {
		return racers;
	}

	@Override
	public String getStatisticsTitle() {
		return statisticsTitle;
	}

	@Override
	public double getAverageTicks() {
		return averageTicks;
	}

	@Override
	public int getFewestTicks() {
		return fewestTicks;
	}

	@Override
	public int getMostTicks() {
		return mostTicks;
	}

	@Override
	public String getFastestSmileyName() {
		return fastestSmileyName;
	}

	@Override
	public String getSlowestSmileyName() {
		return slowestSmileyName;
	}
	
	/**
	 * Move RacingSmiley and perform actions if wall is hit:
	 * Adjust direction, adjust speed, and increment lap
	 * @param racer - a RacingSmiley
	 */
	private void moveCntSmiley(RacingSmiley racer) {
		if (hitLeftWall(racer) || hitRightWall(racer)) {
			adjustDirection(racer);
			adjustSpeed(racer);
			racer.changeSmileyProfile();
			racer.incrementLap();
		}
		else {
			racer.raceForOneTick();
		}
	}
	
	/**
	 * Reverse direction of RacingSmiley
	 * @param racer - a RacingSmiley
	 */
	private void adjustDirection(RacingSmiley racer) {
		racer.setCurrentXMovement(racer.getCurrentXMovement() * REVERSE_DIRECTION);
		racer.setCurrentDirection(racer.getCurrentDirection() * REVERSE_DIRECTION);
	}
	
	/** Adjust speed of RacingSmiley according to strategy
	 *  Racing Strategy:
	 *  0 - constant speed
	 *  1 - decrease speed according to RacingSmiley speed adjustment
	 *  2 - increase speed according to RacingSmiley speed adjustment
	 * @param racer - a RacingSmiley
	 */
	private void adjustSpeed(RacingSmiley racer) {
		switch(racer.getStrategy()) {
		case 0:
			break;
		case 1:
			if (racer.getCurrentXMovement() > 1) {
				if ((racer.getCurrentXMovement() - racer.getSpeedAdjustment()) < 1) {
					racer.setCurrentXMovement(1);
				}
				else {
					racer.setCurrentXMovement(racer.getCurrentXMovement() - racer.getSpeedAdjustment());
				}
			}
			else if (racer.getCurrentXMovement() < -1) {
				if ((racer.getCurrentXMovement() + racer.getSpeedAdjustment()) > -1) {
					racer.setCurrentXMovement(-1);
				}
				else {
					racer.setCurrentXMovement(racer.getCurrentXMovement() + racer.getSpeedAdjustment());
				}
			}
			break;
		case 2:
			if (racer.getCurrentDirection() > racer.getBaseSpeed() * -2 && racer.getCurrentDirection() == -1) {
				if ((racer.getCurrentXMovement() - racer.getSpeedAdjustment()) < racer.getBaseSpeed() * -2) {
					racer.setCurrentXMovement(racer.getBaseSpeed() * -2);
				}
				else {
					racer.setCurrentXMovement(racer.getCurrentXMovement() - racer.getSpeedAdjustment());
				}
			}
			else if (racer.getCurrentDirection() < racer.getBaseSpeed() * 2 && racer.getCurrentDirection() == 1) {
				if ((racer.getCurrentXMovement() + racer.getSpeedAdjustment()) > racer.getBaseSpeed() * 2) {
					racer.setCurrentXMovement(racer.getBaseSpeed() * 2);
				}
				else {
					racer.setCurrentXMovement(racer.getCurrentXMovement() + racer.getSpeedAdjustment());
				}
			}
			break;
		default:
			break;
		}
	}
	
	/** Detect if left wall was hit
	 * 
	 * @param cntSmiley - a RacingSmiley
	 * @return true if cntSmiley hit left wall, else false
	 */
	private boolean hitLeftWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getLeftEdge() <= RacingDisplay.LEFT_EDGE+5 && 
			cntSmiley.getCurrentDirection() == -1) {
			return true;
		}
		return false;
	}

	/** Detect if right wall was hit
	 * 
	 * @param cntSmiley - a RacingSmiley
	 * @return true if cntSmiley hit right wall, else false
	 */
	private boolean hitRightWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getRightEdge() >= RacingDisplay.RIGHT_EDGE-5 && 
			cntSmiley.getCurrentDirection() == 1) {
			return true;
		}
		return false;
	}

}
