import java.util.ArrayList;


public class RacingSmileyAnimation implements RacingAnimationInterface {

	private String fastestSmileyName;
	private String slowestSmileyName;
	
	private int fewestTicks;
	private int mostTicks;
	private int averageTicks;
	
	private String statisticsTitle;
	
	private ArrayList<RacingSmiley> racers;
	private RacingDisplay display;
	
	public RacingSmileyAnimation(RacingGroup g, RacingDisplay d) {
		display = d;
		
	}
	
	@Override
	public void animate() {
		// TODO Auto-generated method stub
		
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

}
