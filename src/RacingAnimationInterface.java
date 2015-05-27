// SmileyAnimationInterface.java
// 
// ICS 45J : Lab Assignment 4
// 
// Written for ICS45J Fall 2012, by Norman Jacobson, August 2012.

import java.util.ArrayList;

public interface RacingAnimationInterface
{
	void animate();
	
	public ArrayList<RacingSmiley> getRacers();
	public String getStatisticsTitle();	
	public double getAverageTicks();	
	public int getFewestTicks();	
	public int getMostTicks();	
	public String getFastestSmileyName();
	public String getSlowestSmileyName();
	
}
