/* CRITTERS GUI <Critter.java>
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Prachi Surbhi
 * ps28324
 * 15470
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.List;

import assignment5.InvalidCritterException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.geometry.HPos;
import javafx.geometry.Insets; 
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.lang.reflect.Method;
import java.util.List;

import assignment5.Params;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	//private static String[][] map = new String[Params.world_height][Params.world_width];
	private boolean moved = false;
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	/**
	 * Lets the critter see what is at a specific location
	 * @param direction The direction in which the critter wants to move
	 * @param steps Tells whether the critter wants to move 1 step or 2 steps
	 * @return Returns the string representation of the object at the coordinate the critter is trying to move to
	 */
	protected final String look(int direction, boolean steps) 
	{
		energy -= Params.look_energy_cost;
		
		//gets the new coordinates that the critter wants to move to
		int x_temp = x_coord;
		int y_temp = y_coord;
		if (steps)
		{
			
			if (direction == 0)
			{
				x_temp = (x_temp + 2) % Params.world_width;
			}
			else if (direction == 1)
			{
				x_temp = (x_temp + 2) % Params.world_width;
				if (y_coord == 0)
				{
					y_temp = Params.world_height - 2;
					
				}
				else if (y_coord == 1)
				{
					y_temp = Params.world_height - 1;
				}
				else
				{
					y_temp = (y_temp - 2) % Params.world_height;
				}
			}
			else if (direction == 2)
			{
				if (y_temp == 0)
				{
					y_temp = Params.world_height - 2;
					
				}
				else if (y_coord == 1)
				{
					y_temp = Params.world_height - 1;
				}
				else
				{
					y_temp = (y_temp - 2) % Params.world_height;
				}
			}
			else if (direction == 3)
			{
				if (x_temp == 0)
				{
					x_temp = Params.world_width - 2;
					
				}
				else if (y_temp == 1)
				{
					x_temp = Params.world_width - 1;
				}
				else
				{
					x_temp = (x_coord - 2) % Params.world_width;
				}
				if (y_temp == 0)
				{
					y_temp = Params.world_height - 2;
					
				}
				else if (y_coord == 1)
				{
					y_temp = Params.world_height - 1;
				}
				else
				{
					y_temp = (y_temp - 2) % Params.world_height;
				}
				
			}
			else if (direction == 4)
			{
				if (x_temp == 0)
				{
					x_temp = Params.world_width - 2;
				}
				else if (y_coord == 1)
				{
					x_temp = Params.world_width - 1;
				}
				else
				{
					x_temp = (x_temp - 2) % Params.world_width;
				}
			}
			else if (direction == 5)
			{
				if (x_temp == 0)
				{
					x_temp = Params.world_width - 2;
				}
				else if (y_coord == 1)
				{
					x_temp = Params.world_width - 1;
				}
				else
				{
					x_temp = (x_temp - 2) % Params.world_width;
				}
				y_temp = (y_temp + 2) % Params.world_height;
			}
			else if (direction == 6)
			{
				y_temp = (y_temp + 2) % Params.world_height;
			}
			else if (direction == 7)
			{
				x_temp = (x_temp + 2) % Params.world_width;
				y_temp = (y_temp + 2) % Params.world_height;
			}
		}
		else
		{
			if (direction == 0)
			{
				x_temp = (x_temp + 1) % Params.world_width;
			}
			else if (direction == 1)
			{
				x_temp = (x_temp + 1) % Params.world_width;
				if (y_temp != 0)
				{
					y_temp = (y_temp - 1) % Params.world_height;
				}
				else
				{
					y_temp = Params.world_height - 1;
				}
			}
			else if (direction == 2)
			{
				if (y_temp != 0)
				{
					y_temp = (y_temp - 1) % Params.world_height;
				}
				else
				{
					y_temp = Params.world_height - 1;
				}
			}
			else if (direction == 3)
			{
				if (x_temp != 0)
				{
					x_temp = (x_temp - 1) % Params.world_width;
				}
				else
				{
					x_temp = Params.world_width - 1;
				}
				if (y_temp != 0)
				{
					y_temp = (y_temp - 1) % Params.world_height;
				}
				else
				{
					y_temp = Params.world_height - 1;
				}
			}
			else if (direction == 4)
			{
				if (x_temp != 0)
				{
					x_temp = (x_temp - 1) % Params.world_width;
				}
				else
				{
					x_temp = Params.world_width - 1;
				}
			}
			else if (direction == 5)
			{
				if (x_temp != 0)
				{
					x_temp = (x_temp - 1) % Params.world_width;
				}
				else
				{
					x_temp = Params.world_width - 1;
				}
				y_temp = (y_temp + 1) % Params.world_height;
			}
			else if (direction == 6)
			{
				y_temp = (y_temp + 1) % Params.world_height;
			}
			else if (direction == 7)
			{
				x_temp = (x_temp + 1) % Params.world_width;
				y_temp = (y_temp + 1) % Params.world_height;
			}
		}
		
		//if there is another critter at that spot, it returns the string representation of that critter
		for (int i = 0; i < population.size(); i++)
		{
			if (population.get(i).x_coord == x_temp && population.get(i).y_coord == y_temp)
			{
				return population.get(i).toString();
			}
		}
		return null;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	/**
	 * Gets a random number with the given mx
	 * @param max Maximum number that random number can be
	 * @return Random number generated
	 */
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	/**
	 * Sets the seed to the given value
	 * @param new_seed The seed it is going to be set to
	 */
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	/**
	 * Moves up, down, left, right and diagonally once based on direction, can only walk once per world step
	 * @param direction Gives the direction be moved to
	 */
	protected final void walk(int direction) 
	{
		//clears map coordinate initially
				//map[y_coord][x_coord] = " ";
				//moves only if it hasn't moved already
				if (!moved)
				{
					//moves according to direction provided
					if (direction == 0)
					{
						x_coord = (x_coord + 1) % Params.world_width;
						moved = true;
					}
					else if (direction == 1)
					{
						moved = true;
						x_coord = (x_coord + 1) % Params.world_width;
						if (y_coord != 0)
						{
							y_coord = (y_coord - 1) % Params.world_height;
						}
						else
						{
							y_coord = Params.world_height - 1;
						}
					}
					else if (direction == 2)
					{
						moved = true;
						if (y_coord != 0)
						{
							y_coord = (y_coord - 1) % Params.world_height;
						}
						else
						{
							y_coord = Params.world_height - 1;
						}
					}
					else if (direction == 3)
					{
						moved = true;
						if (x_coord != 0)
						{
							x_coord = (x_coord - 1) % Params.world_width;
						}
						else
						{
							x_coord = Params.world_width - 1;
						}
						if (y_coord != 0)
						{
							y_coord = (y_coord - 1) % Params.world_height;
						}
						else
						{
							y_coord = Params.world_height - 1;
						}
					}
					else if (direction == 4)
					{
						moved = true;
						if (x_coord != 0)
						{
							x_coord = (x_coord - 1) % Params.world_width;
						}
						else
						{
							x_coord = Params.world_width - 1;
						}
					}
					else if (direction == 5)
					{
						moved = true;
						if (x_coord != 0)
						{
							x_coord = (x_coord - 1) % Params.world_width;
						}
						else
						{
							x_coord = Params.world_width - 1;
						}
						y_coord = (y_coord + 1) % Params.world_height;
					}
					else if (direction == 6)
					{
						moved = true;
						y_coord = (y_coord + 1) % Params.world_height;
					}
					else if (direction == 7)
					{
						moved = true;
						x_coord = (x_coord + 1) % Params.world_width;
						y_coord = (y_coord + 1) % Params.world_height;
					}
				}
				//puts the string of the object at its location
			//	map[y_coord][x_coord] = toString();
				//takes off walk_energy_cost every time
				energy -= Params.walk_energy_cost;
	}
	
	/**
	 * Moves up, down, left, right and diagonally twice based on direction, can only walk once per world step
	 * @param direction Gives the direction be moved to
	 */
	protected final void run(int direction) 
	{
		//clears map coordinate initially
			//	map[y_coord][x_coord] = " ";
				//moves only if it hasn't moved already
				if (!moved)
				{
					//moves according to direction provided
					if (direction == 0)
					{
						moved = true;
						x_coord = (x_coord + 2) % Params.world_width;
					}
					else if (direction == 1)
					{
						moved = true;
						x_coord = (x_coord + 2) % Params.world_width;
						if (y_coord == 0)
						{
							y_coord = Params.world_height - 2;
							
						}
						else if (y_coord == 1)
						{
							y_coord = Params.world_height - 1;
						}
						else
						{
							y_coord = (y_coord - 2) % Params.world_height;
						}
					}
					else if (direction == 2)
					{
						moved = true;
						if (y_coord == 0)
						{
							y_coord = Params.world_height - 2;
							
						}
						else if (y_coord == 1)
						{
							y_coord = Params.world_height - 1;
						}
						else
						{
							y_coord = (y_coord - 2) % Params.world_height;
						}
					}
					else if (direction == 3)
					{
						moved = true;
						if (x_coord == 0)
						{
							x_coord = Params.world_width - 2;
							
						}
						else if (y_coord == 1)
						{
							x_coord = Params.world_width - 1;
						}
						else
						{
							x_coord = (x_coord - 2) % Params.world_width;
						}
						if (y_coord == 0)
						{
							y_coord = Params.world_height - 2;
							
						}
						else if (y_coord == 1)
						{
							y_coord = Params.world_height - 1;
						}
						else
						{
							y_coord = (y_coord - 2) % Params.world_height;
						}
						
					}
					else if (direction == 4)
					{
						moved = true;
						if (x_coord == 0)
						{
							x_coord = Params.world_width - 2;
						}
						else if (y_coord == 1)
						{
							x_coord = Params.world_width - 1;
						}
						else
						{
							x_coord = (x_coord - 2) % Params.world_width;
						}
					}
					else if (direction == 5)
					{
						moved = true;
						if (x_coord == 0)
						{
							x_coord = Params.world_width - 2;
						}
						else if (y_coord == 1)
						{
							x_coord = Params.world_width - 1;
						}
						else
						{
							x_coord = (x_coord - 2) % Params.world_width;
						}
						y_coord = (y_coord + 2) % Params.world_height;
					}
					else if (direction == 6)
					{
						moved = true;
						y_coord = (y_coord + 2) % Params.world_height;
					}
					else if (direction == 7)
					{
						moved = true;
						x_coord = (x_coord + 2) % Params.world_width;
						y_coord = (y_coord + 2) % Params.world_height;
					}
				}
				//puts the string of the object at its location
				//map[y_coord][x_coord] = toString();
				//takes off run_energy_cost every time
				energy -= Params.run_energy_cost;
	}
	
	/**
	 * Creates a child of the object if it has more than min_reproduce_energy, child gets half of parents energy, child moves to its position
	 * @param offspring Child to be born potentially
	 * @param direction Direction in which the child will be placed
	 */
	protected final void reproduce(Critter offspring, int direction) 
	{
		if (energy <= Params.min_reproduce_energy)
		{
			return;
		}
		offspring.x_coord = x_coord;
		offspring.y_coord = y_coord;
		offspring.energy = energy/2;
		energy = energy/2 + (energy % 2);
		offspring.walk(direction);
		offspring.energy -= Params.walk_energy_cost;
		babies.add(offspring);
	}

	/**
	 * Carries out everything an object does in its time step
	 */
	public abstract void doTimeStep();
	
	/**
	 * Critter figures out how to respond to fighting
	 * @param oponent The opponent the critter will if it fights
	 * @return Whether or not the critter wants to fight
	 */
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 */
	public static void worldTimeStep() 
	{
		//sets each critter's moved to false
				for (int i = 0; i < population.size(); i++)
				{
					population.get(i).moved = false;
				}
				
				
				//carries out doTimeStep for every living critter, if it dies during the process, it is removed
				for (int i = 0; i < population.size(); i++)
				{
					
					if (population.get(i).energy > 0 && i < population.size())
					{
						//map[population.get(i).y_coord][population.get(i).x_coord] = null;
						(population.get(i)).doTimeStep();
						if (population.get(i).energy > 0)
						{
							//map[population.get(i).y_coord][population.get(i).x_coord] = population.get(i).toString();
						}
						else
						{
							//map[population.get(i).y_coord][population.get(i).x_coord] = null;
							population.remove(population.get(i));
							i--;
						}
					}
					else
					{
						//map[population.get(i).y_coord][population.get(i).x_coord] = null;
						population.remove(population.get(i));
						i--;
					}
				}
				
				 //List of critters to be removed
				List<Critter> rem = new java.util.ArrayList<Critter>();
				
				//handles encouters
				for (int i = 0; i < population.size(); i++)
				{
					for (int j = i+1; j < population.size(); j++)
					{
						//2 critters found on the same coordinate
						if (population.get(i).x_coord == population.get(j).x_coord && population.get(i).y_coord == population.get(j).y_coord)
						{
							int Ax = population.get(i).x_coord;
							int Ay = population.get(i).y_coord;
							int Bx = population.get(j).x_coord;
							int By = population.get(j).y_coord;
							boolean Afight = population.get(i).fight(population.get(j).toString());
							boolean Bfight = population.get(j).fight(population.get(i).toString());
							
							//if critters moved to a space that is already occupied, it is moved back to where it was before
							if (population.get(i).moved)
							{
								for (int k = 0; k < population.size(); k++)
								{
									if (population.get(k) != population.get(i) && population.get(k) != population.get(j))
									{
										if (population.get(k).x_coord == population.get(i).x_coord && population.get(k).y_coord == population.get(i).y_coord)
										{
											population.get(i).x_coord = Ax;
											population.get(i).y_coord = Ay;
										}
									}
								}
							}
							if (population.get(j).moved)
							{
								for (int k = 0; k < population.size(); k++)
								{
									if (population.get(k) != population.get(j) && population.get(k) != population.get(i))
									{
										if (population.get(k).x_coord == population.get(j).x_coord && population.get(k).y_coord == population.get(j).y_coord)
										{
											population.get(j).x_coord = Bx;
											population.get(j).y_coord = By;
										}
									}
								}
							}
							
							//if the both critters are alive, they fight
							if (population.get(i).energy > 0 && population.get(j).energy > 0)
							{
								if (population.get(i).x_coord == population.get(j).x_coord && population.get(i).y_coord == population.get(j).y_coord)
								{							
									int Anum;
									int Bnum;
									if (Afight)
									{
										Anum = getRandomInt(population.get(i).energy);
									}
									else
									{
										Anum = 0;
									}
									if (Bfight)
									{
										Bnum = getRandomInt(population.get(j).energy);
									}
									else
									{
										Bnum = 0;
									}
									
									//loser is added to rem List
									if (Anum > Bnum)
									{
										population.get(i).energy += population.get(j).energy;
										//map[population.get(j).y_coord][population.get(j).x_coord] = " ";
										rem.add(population.get(j));							
									}
									else if (Bnum > Anum)
									{
										population.get(j).energy += population.get(i).energy;
										//map[population.get(i).y_coord][population.get(i).x_coord] = " ";
										rem.add(population.get(i));
									}
									else
									{
										population.get(i).energy += population.get(j).energy;
										//map[population.get(j).y_coord][population.get(j).x_coord] = " ";
										rem.add(population.get(j));
									}
								}
							}
						}		
					}
				}
				
				//removes everything in rem from population and clears rem
				for (int i = 0; i < rem.size(); i++)
				{
					population.remove(rem.get(i));
					//map[population.get(i).y_coord][population.get(i).x_coord] = null;
				}
				rem.clear();
				
				//decreases resting cost from critters
				for (int i = 0; i < population.size(); i++)
				{
					population.get(i).energy -= Params.rest_energy_cost;
				}
				
				//removes dead critters from population
				for (int i = 0; i < population.size(); i++)
				{
					if (population.get(i).energy <= 0)
					{
						//map[population.get(i).y_coord][population.get(i).x_coord] = null;
						population.remove(i);
						i--;
					}
				}
				
				//adds babies if there are any
				if (babies.size() != 0)
				{
					for (int i = 0; i < babies.size(); i++)
					{
						population.add(babies.get(i));
					}
					babies.clear();
				}
				
				//adds refresh_algae_count number of algaes to population
				for (int i = 0; i < Params.refresh_algae_count; i++)
				{
					try 
					{
						makeCritter("Algae");
					}
					catch (InvalidCritterException e)
			        {
			        		System.out.println(e);
			        }
				}
				
				
				
				
	}
	
	/**
	 * Shows the critters in a grid
	 * @param pane grid in which critters will be shown
	 */
	public static void displayWorld(Object pane) 
	{
		Main.grid.getChildren().clear();
		for (int i = 0; i < population.size(); i++)
		{
			CritterShape r = population.get(i).viewShape();
			if (r == CritterShape.CIRCLE)
			{
				Shape s = new Circle(14);
				s.setFill(Color.GREEN);
				s.setStrokeWidth(3);
				s.setStroke(Color.BROWN);
				Main.grid.add(s, population.get(i).x_coord, population.get(i).y_coord);
				GridPane.setHalignment(s, HPos.CENTER);
			}
			else if (r == CritterShape.SQUARE)
			{
				Shape s = new Rectangle(25, 25);
				s.setFill(Color.AQUA);
				s.setStrokeWidth(3);
				s.setStroke(Color.DARKSLATEBLUE);
				Main.grid.add(s, population.get(i).x_coord, population.get(i).y_coord);
				GridPane.setHalignment(s, HPos.CENTER);
			}
			else if (r == CritterShape.DIAMOND)
			{
				Shape s = new Rectangle(20,20);
				s.setRotate(45);
				s.setFill(Color.AQUA);
				s.setStroke(Color.DARKSLATEBLUE);
				Main.grid.add(s, population.get(i).x_coord, population.get(i).y_coord);
				GridPane.setHalignment(s, HPos.CENTER);
			}
			else if (r == CritterShape.TRIANGLE)
			{
				Polyline polyline = new Polyline();
				polyline.getPoints().addAll(new Double[]{        
						   1.0, 25.0, 
						   25.0, 50.0, 
						   1.0, 50.0,          
						}); 
				polyline.setFill(Color.MIDNIGHTBLUE);
				polyline.setStrokeWidth(2);
				polyline.setStroke(Color.TURQUOISE);
				Main.grid.add(polyline, population.get(i).x_coord, population.get(i).y_coord);
				GridPane.setHalignment(polyline, HPos.CENTER);
			}
			else if (r == CritterShape.STAR)
			{
				Polygon polygon = new Polygon();
				polygon.getPoints().addAll(new Double[]{
						2.0, 2.0, 
						   15.0, 22.0, 
						   22.0, 0.0,          
						   2.0, 20.0,
						   22.0, 20.0,
						   2.0,2.0, }); 
				polygon.setFill(Color.MIDNIGHTBLUE);
				polygon.setStrokeWidth(2);
				polygon.setStroke(Color.TURQUOISE);
				Main.grid.add(polygon, population.get(i).x_coord, population.get(i).y_coord);
				//GridPane.setHalignment(polygon, HPos.CENTER);
			}
		}
		
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * @param critter_class_name Class of the critter to be made
	 * @throws InvalidCritterException If class name is not valid, throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException 
	{
		try
		{
			//creates a new critter if class name is valid
			Class<?> c = Class.forName(myPackage + "." + critter_class_name);
			Critter v = (Critter) c.newInstance();
			population.add(v);
			//sets x, y and energy of the new critter to Params values
			v.x_coord = getRandomInt(Params.world_width);
			v.y_coord = getRandomInt(Params.world_height);
			v.energy = Params.start_energy;
			//adds it to map
			//map[v.y_coord][v.x_coord] = v.toString();
		}
		catch  (Exception | NoClassDefFoundError e)
		{
			throw new InvalidCritterException(critter_class_name);
		}
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException If class name is not valid, throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException 
	{
		List<Critter> result = new java.util.ArrayList<Critter>();
		//adds crtters of the class given into result
		for (Critter c: population) 
		{
			try 
			{
				if (c.getClass().getName().equalsIgnoreCase(myPackage + "." + critter_class_name))
				{
					result.add(c);			
				}
			}
			catch  (Exception e)
			{
				throw new InvalidCritterException(critter_class_name);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 * @return Returns the string with the critter's stats
	 */
	public static String runStats(List<Critter> critters) 
	{
		String message = "";
		message += "" + critters.size() + " critters as follows -- \n";
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			message += prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		message += "\n";
		return message;
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/**
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 * @return Returns the population
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() 
	{
		babies.clear();
		population.clear();
		// Complete this method.
		for (int i = 0; i < population.size(); i++)
		{
			population.remove(population.get(i));
		}
		for (int i = 0; i < Params.world_height; i++)
		{
			for (int j = 0; j < Params.world_width; j++)
			{
				//map[i][j] = null;
			}
		}
	}
	
	
}
