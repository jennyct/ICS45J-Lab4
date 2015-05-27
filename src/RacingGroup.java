import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

/** 
 * The group of smileys that are in the race
 * @author Stephen Em and Jenny Tang
 *
 */
public class RacingGroup implements RacingGroupInterface{
	
	/**
	 * List of 30 names for smileys
	 */
	private ArrayList<String> smileyNames = new ArrayList<String>(Arrays.asList(
			"Patricia", "Shaunna", "Cecily", "Genevieve", "Chris", "Loree",
			"Williemae", "Latia", "Adriene", "Beata", "Lesley", "Alvaro",
			"Treasa", "Chauncey", "Larita", "Debby", "Ricky", "Nicol",
			"Nick", "Neda", "Rosemary", "Toshiko", "Ute", "Matt", "Ricarda", 
			"Marx", "Margarito", "Mirian", "Bettie", "Winona"));
	
	/**
	 * List of 30 colors for smileys
	 */
	private ArrayList<Color> nameColors = new ArrayList<Color>(Arrays.asList(
			Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
			Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
			Color.RED, Color.WHITE, new Color(102,255,178), 
			new Color(135,80,80), new Color(135,80,120), new Color(93,80,135),
			new Color(80,126,135), new Color(80,135,99), new Color(113,135,80),
			new Color(135,100,80), new Color(198,47,47), new Color(193,47,198),
			new Color(47,67,198), new Color(47,173,198), new Color(47,198,92),
			new Color(132,198,47),new Color(198,132,47), new Color(242,105,132),
			new Color(164,105,242), new Color(105,242,164), new Color(178,242,105)));
	
	/**
	 * Number of smileys generated for the race
	 * Change this number to increase amount of smileys
	 */
	private static final int NUMBER_OF_SMILEYS = 5;
	private ArrayList<RacingSmiley> racingSmileys;
	
	/** The constructor builds an ArrayList of racers
	// all racing smileys, each with the same shape and
	// color, with mouths the same color as the background
	// of the screen (the passed-in parameter), moving to
	// the right to start, but each with a different name
	// and name color
	 * 
	 * @param background - background color
	 */

	public RacingGroup(Color background) {
		racingSmileys = new ArrayList<RacingSmiley>();
		int smileyBuffer = (400/NUMBER_OF_SMILEYS);
		int smileyLength = smileyBuffer-4;
		int shiftCenter = (200/NUMBER_OF_SMILEYS);
		int shiftFacePart = shiftCenter/3;
		AnimatedSmiley smileyToCopy = new AnimatedSmiley(10,0);
		smileyToCopy.getFace().setAttributes(Color.YELLOW, shiftCenter, shiftCenter, smileyLength, smileyLength);
		smileyToCopy.getLeftEye().setAttributes(Color.YELLOW, shiftCenter-shiftFacePart, shiftCenter-shiftFacePart, smileyLength/10, smileyLength/10);
		smileyToCopy.getRightEye().setAttributes(Color.RED, shiftCenter+shiftFacePart, shiftCenter-shiftFacePart, smileyLength/10, smileyLength/10);
		smileyToCopy.getSmile().setAttributes(background, smileyLength, shiftCenter+shiftFacePart, smileyLength, smileyLength/10);
		
		RacingSmiley smileyToAdd = new RacingSmiley(smileyToCopy, smileyNames.get(0), nameColors.get(0));
		for (int i=0; i<NUMBER_OF_SMILEYS; i++) {
			if (i!=0) {
				smileyToAdd = new RacingSmiley(smileyToAdd, smileyNames.get(i), nameColors.get(i));
				smileyToAdd.translate(0, smileyBuffer);
			}
			racingSmileys.add(smileyToAdd);
		}
	}
	
	/**
	 * Access the group of smileys
	 */
	@Override
	public ArrayList<RacingSmiley> getRacers() {
		return racingSmileys;
	}

}
