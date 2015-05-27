import java.util.ArrayList;


public class RacingAnimation implements RacingAnimationInterface {
	
	private static final int REVERSE_DIRECTION = -1;
	
	private String fastestSmileyName;
	private String slowestSmileyName;
	
	private int fewestTicks;
	private int mostTicks;
	private double averageTicks;
	
	private static final String statisticsTitle = "Race Statistics";
	
	private ArrayList<RacingSmiley> racers;
	private RacingDisplay display;
	
	public RacingAnimation(RacingGroup g, RacingDisplay d) {
		racers = g.getRacers();
		display = d;
		display.repaint();
	}
	
	@Override
	public void animate() {
		class AnimationRunnable implements Runnable
		{
			public void run()
			{
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
		}
		Thread t = new Thread(new AnimationRunnable());
		t.start();
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
	
	private boolean isRaceDone(ArrayList<RacingSmiley> racers) {
		for (RacingSmiley racer : racers) {
			if (!racer.finishedRace()) {
				return false;
			}
		}
		return true;
	}
	
	private void computeStatistics() {
		computeAverageTicks();
	}
	
	private void computeAverageTicks() {
		double sumOfTicks = 0;
		for (RacingSmiley racer: racers) {
			sumOfTicks += racer.getTicks();
		}
		averageTicks = sumOfTicks/(double)racers.size();
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
	
	private void adjustDirection(RacingSmiley racer) {
		System.out.println(racer.getCurrentXMovement() * REVERSE_DIRECTION);
		racer.setCurrentXMovement(racer.getCurrentXMovement() * REVERSE_DIRECTION);
		racer.setCurrentDirection(racer.getCurrentDirection() * REVERSE_DIRECTION);
	}
	
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
	
	private boolean hitLeftWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getLeftEdge() <= RacingDisplay.LEFT_EDGE+5 && 
			cntSmiley.getCurrentDirection() == -1) {
			return true;
		}
		return false;
	}

	private boolean hitRightWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getRightEdge() >= RacingDisplay.RIGHT_EDGE-5 && 
			cntSmiley.getCurrentDirection() == 1) {
			return true;
		}
		return false;
	}

}
