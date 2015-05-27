import java.util.ArrayList;


public class RacingAnimation implements RacingAnimationInterface {
	
	private static final int REVERSE_DIRECTION = -1;
	
	private String fastestSmileyName;
	private String slowestSmileyName;
	
	private int fewestTicks;
	private int mostTicks;
	private int averageTicks;
	
	private String statisticsTitle;
	
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
			System.out.println("HIT SOME SHIT");
			adjustDirection(racer);
		}
		else {
			racer.raceForOneTick();
		}
	}
	
	private void adjustDirection(RacingSmiley racer) {
		if (hitLeftWall(racer)) {
			System.out.println("lap");
		}
		System.out.println(racer.getCurrentXMovement() * REVERSE_DIRECTION);
		racer.setCurrentXMovement(racer.getCurrentXMovement() * REVERSE_DIRECTION);
	}
	
	private boolean hitLeftWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getLeftEdge() <= display.LEFT_EDGE && 
			cntSmiley.getCurrentDirection() == -1) {
			return true;
		}
		return false;
	}

	private boolean hitRightWall(RacingSmiley cntSmiley) {
		if (cntSmiley.getRightEdge() >= display.RIGHT_EDGE && 
			cntSmiley.getCurrentDirection() == 1) {
			return true;
		}
		return false;
	}

}
