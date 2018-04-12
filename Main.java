/* CRITTERS GUI <Main.java>
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Prachi Surbhi
 * ps28324
 * 15470
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;

import javafx.application.Application;
import javafx.concurrent.Task;
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
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.lang.reflect.Method;
import java.util.List;

//import java.awt.Insets;
import assignment5.Critter;
import assignment5.InvalidCritterException;
import assignment5.Painter;

public class Main extends Application implements EventHandler<ActionEvent>
{
	protected static GridPane grid = new GridPane();
	private static String myPackage;
	
	 static 
	 {
		 myPackage = Critter.class.getPackage().toString().split(" ")[1];
	 }
	//Button button;
	public static void main(String[] args) 
	{
		 launch(args);
	}
	
	@Override
	/**
	 * Called by the application. Runs as the "main" method
	 */
	public void start(Stage primaryStage) 
	{
		Stage window = primaryStage;
		try
		{
			primaryStage.setTitle("Critter World");
			primaryStage = new Stage();
			
			
			//sets up the make button and its 2 text fields
			Button make = new Button("Make");
			TextField makeInput = new TextField();
			makeInput.setPromptText("Critter name");
			TextField makeInt = new TextField();
			makeInt.setPromptText("Number of Critters");
			make.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
					
					if (!makeInt.getText().equals(""))
					{
						if (isInt(makeInt.getText()))
						{
							int num = Integer.parseInt(makeInt.getText());
							for (int i = 0; i < num; i++)
	        					{
	        						try 
	        						{
	        							Critter.makeCritter(makeInput.getText());
	        							//grid.getChildren().clear();
	        							Critter.displayWorld(grid);
	        							paintGridLines(grid);
	        							window.show();
	        						}
	        						catch (InvalidCritterException k)
	        						{
	        							//create a pop up window
	        						}
	        					}
						}		
					}
					else
					{
						
						try 
						{
							Critter.makeCritter(makeInput.getText());
							Critter.displayWorld(grid);
							paintGridLines(grid);
							window.show();
							
						}
						catch (InvalidCritterException i)
						{
							
						}
						
					}
					makeInt.clear();
					makeInput.clear();
					Critter.displayWorld(grid);
					paintGridLines(grid);
					window.show();
				}
			});
			//makes the horizontal block for the make box
			HBox makeBox = new HBox();
			makeBox.getChildren().addAll(make, makeInput, makeInt);
			
			//sets up the make button and its text field
			Button step = new Button("Step");
			TextField stepInput = new TextField();
			stepInput.setPromptText("Number of Steps");
			step.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
					if (isInt(stepInput.getText()))
					{
						try 
	    					{
	    						int num = Integer.parseInt(stepInput.getText());
	        					for (int i = 0; i < num; i++)
	        					{
	        						Critter.worldTimeStep();
	        						Critter.displayWorld(grid);
	        						paintGridLines(grid);
	        						window.show();
	        					}
	    					}
	    					catch (Exception k)
	    					{
	    						
	    					}
						
					}
					else if (stepInput.getText().equals(""))
					{
						Critter.worldTimeStep();
						Critter.displayWorld(grid);
						paintGridLines(grid);
						window.show();
					}
					stepInput.clear();
				}
			});
			//makes the horizontal block for step
			HBox stepBox = new HBox(10);
			stepBox.getChildren().addAll(step, stepInput);
			
			//sets up the quit button
			Button quit = new Button("QUIT");
			quit.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
			        System.exit(0);
			       
				}
			});
			
			//sets up the clear button
			Button clear = new Button("CLEAR");
			clear.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
					grid.getChildren().clear();
					paintGridLines(grid);
					window.show();
					Critter.clearWorld();
				}
			});
			
			//makes the block with the quit and clear buttons
			VBox quitBox = new VBox(20);
			quitBox.getChildren().addAll(quit, clear);
			quitBox.autosize();
			
			//sets up the stats button and its text field
			Button stats = new Button("Stats");
			TextField statsInput = new TextField();
			statsInput.setPromptText("Critter name");
			TextArea statsText = new TextArea();
			statsText.setPrefColumnCount(20);
			statsText.setPrefRowCount(20);
			stats.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
					try
					{
						if (!statsInput.getText().equals("Critter"))
						{
							String input = statsInput.getText();
							List<Critter> cr = Critter.getInstances(input);
							//System.out.println(myPackage);
        						Class<?> c = Class.forName(myPackage + "." + input);
        						Method method = c.getMethod("runStats", cr.getClass().getInterfaces()[0]);
        						//Text t = new Text();
        						String s = (String) method.invoke(c, cr);
        						//t.setText(s);
        						statsText.setText(s);
        						statsInput.clear();
        						statsText.autosize();
						}
						else
						{
							//pop up window
						}
					}
					catch (Exception l)
					{
						//pop up window
					}
					statsInput.clear();
				}
			});
			statsText.setWrapText(true);
			HBox statsBox0 = new HBox(3);
			statsBox0.getChildren().addAll(stats, statsInput);
			//makes the stats block and the text block in it
			VBox statsBox = new VBox();
			statsBox.getChildren().addAll(statsBox0, statsText);
			
			//sets up the seed button and its text field
			Button seed = new Button("Seed");
			TextField seedInput = new TextField();
			seedInput.setPromptText("Number");
			seed.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle (ActionEvent e)
				{
					try
					{
						Critter.setSeed(Long.parseLong(seedInput.getText()));
						seedInput.clear();
					}
					catch (Exception k)
					{
						
					}
				}
			});
			HBox seedBox = new HBox(10);
			seedBox.getChildren().addAll(seed, seedInput);
			
			//makes the block with make block, step block and seed block
			HBox top = new HBox(100);
			top.getChildren().addAll(makeBox, stepBox, seedBox);
			
			
			grid.setGridLinesVisible(true);
			
			//sets the layout for the scene
			BorderPane border = new BorderPane();
			quitBox.setPadding(new Insets(10,10,10,10));
			quitBox.autosize();
			border.setRight(quitBox);
			grid.autosize();
			border.setCenter(grid);
			statsBox.autosize();
			statsBox.setPadding(new Insets(10,10,10,10));
			border.setLeft(statsBox);
			top.setPadding(new Insets(10,10,10,10));
			top.autosize();
			border.setTop(top);
			border.centerProperty();
			
			//sets up the grid to be displayed
			grid.setMinSize(Params.world_width, Params.world_height); 
		    grid.setGridLinesVisible(true);
		    paintGridLines(grid);
		    border.setPadding(new Insets(10, 10, 10,10));
		    border.autosize();
		    grid.autosize();
		    
		    //sets up and shows the scene
			Scene scene = new Scene(border, 1100, 700);
			window.setScene(scene);
			paintGridLines(grid);
			window.show();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	public void handle (ActionEvent event)
	{
		
	}
	
	/**
	 * Paints the grid lines for grid
	 * @param grid GridPane for which the lines are to be made
	 */
	private static void paintGridLines(GridPane grid) {
		for (int r = 0; r < Params.world_width; r++)
			for (int c = 0; c < Params.world_height; c++) {
				Shape s = new Rectangle(30, 30);
				s.setFill(null);
				s.setStroke(Color.BLUEVIOLET);
				grid.add(s, c, r);
			}

	}
	
	/**
	 * Checks to see if the text input is an integer
	 * @param message The String that will be checked
	 * @return whether or not the string is an integer
	 */
	private boolean isInt(String message)
	{
		try
		{
			int num = Integer.parseInt(message);
			return true;
		}
		catch (NumberFormatException e)
		{
			
			return false;
		}
	}
}
