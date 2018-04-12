/* CRITTERS GUI <Critter2.java>
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Prachi Surbhi
 * ps28324
 * 15470
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;

import java.util.List;

import assignment5.Critter.CritterShape;

public class Critter2 extends Critter.TestCritter 
{
	static int runCount = 0;
	
	/**
	 * Attempts to reproduce every time if it has enough energy
	 */
	public void doTimeStep() 
	{
		Critter2 child = new Critter2();
		
		reproduce(child, 1);
		if (look(2, true) != null && look(2, true).equals("@"))
		{
			run(2);
		}
	}

	
	@Override
	/**
	 * Tries to run from fight. Does not want to fight anyone so always returns false
	 * @param opponent The opponent it is going to fight
	 */
	public boolean fight(String opponent) 
	{
		run(getRandomInt(7));
		runCount++;
		return false;
			
	}
	
	/**
	 * Object is represented as a "2" on map
	 * @return Gives a String representation of the object
	 */
	public String toString() 
	{
		return "2";
	}
	
	/**
	 * Prints how many Critter2s there are in the world and how many times they tried to run away in a fight
	 * @param critters List of Critter2 objects from the current population
	 * @return Returns the string with stats of the critter
	 */
	public static String runStats(List<Critter> critters) 
	{
		return "" + critters.size() + " critter2s as follows -- " + "tried to run " + runCount + " times during fight.";
		
	}
	
	@Override
	public CritterShape viewShape() 
	{ 
		return CritterShape.DIAMOND; 
	}
}
