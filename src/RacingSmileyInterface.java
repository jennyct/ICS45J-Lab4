// RacingSmileyInterface.java
// 
// ICS 45J : Lab Assignment 4
// 
// Written for ICS45J Fall 2012, by Norman Jacobson, August 2012.
// Revised to more clearly state constructor requirements,
//   by Norman Jacobson, November 2012.

import java.awt.Color;

// A SmileyRacer is a specialized animated smiley that is capable of
// participating in a smiley race and tracking its own progress.

public interface RacingSmileyInterface
{
	
	public boolean finishedRace();
	public void raceForOneTick();
	public int getTicks();
	public String getSmileyName();
	public Color getSmileyNameColor();
	public int getLapsCompleted();
	
}
