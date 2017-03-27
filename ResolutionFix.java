package Assignment3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.UIManager;

/**
 * ResolutionFix performs 2 major functions: 
 * 
 * 1) Updates JSwing components to use relative sizes based on resolution instead of fixed size.
 * 	  - Simply instantiating a ResolutionFix object will automatically make the necessary changes.
 * 
 * 2) Provide font methods to quickly use with any GUI creation that are relative to screen size.
 * 	   Includes:
 * 			NON EDITABLE VALUES
 * 			-width 				- Width of full screen
 * 			-height 			- Height of full screen
 * 			-defaultFont		- Default Text Size
 * 
 * 			Editable Values
 * 			-screenX			- Window Width
 * 			-screenY			- Window Height			
 * 			-FontSize			- Text Size
 * 			-H1					- Header 1 Size
 * 			-H2					- Header 2 Size
 * 			-H3					- Header 3 Size
 * 			-H4    				- Header 4 Size
 * 
 * 
 * @author Jordan Reardon-Smith
 *
 */

public class ResolutionFix {
	private  int width,height,defaultFontSize;
	private  int screenX,screenY,workingFontSize,h1,h2,h3,h4;
	
	/**
	 * constructor sets all default variables
	 */
	public ResolutionFix(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setWidth((int) Math.floor(screenSize.getWidth()));
		setHeight((int) Math.floor(screenSize.getHeight()));		
		setDefaultFontSize((int) (width*0.01)); 		
		setscreenX((int) (width *.4));
		setscreenY((int) (height *.4));
		setfontSize(defaultFontSize);
		
		jSwingAdjust(); //Calls adjuster for Java Swing objects
		

	}

	
	
	/**
	 * Adjusts Jswing component sizes
	 */
	private void jSwingAdjust(){
		
		Font font = UIManager.getFont("OptionPane.font");//Swings Default settings
		
		//change JOptionSize
		UIManager.put("OptionPane.minimumSize",new Dimension(screenX/3, screenY/4)); 	
		
		//change JOption Message Size
		UIManager.put("OptionPane.messageFont", new Font(font.getName(), font.getStyle(), workingFontSize));
		
		//change JOption Button Size
		UIManager.put("OptionPane.buttonFont", new Font(font.getName(), font.getStyle(), workingFontSize));
	}
	
	
	
	
	
	//getters
	public int width(){return this.width;} //screen width (not changeable)
	public int height(){return this.height;}//screen height (not changeable)
	public int defaultFont(){return this.defaultFontSize;} //default font size (not changeable)
	public int screenX(){return this.screenX;}//window width
	public int screenY(){return this.screenY;}//window height
	public int fontSize(){return this.workingFontSize;}//working font size
	public int h1(){return this.h1;} //header 1
	public int h2(){return this.h2;} //header 2
	public int h3(){return this.h3;}// header 3
	public int h4(){return this.h4;}// header 4
	
	
	
	//Setters ------------------------------
	
	//fullscreen size (not changeable)
	private void setWidth(int w){this.width = w;} 
	private void setHeight(int h){this.height = h;}
	
	//working screen size
	public void setscreenX(int x){this.screenX = x;}
	public void setscreenY(int y){this.screenY = y;}

	//default font size (not changeable)
	private void setDefaultFontSize(int fS){this.defaultFontSize = fS;}

	//working font size and headers	
	public void setfontSize(int fs){
		this.workingFontSize =fs;
		setH1(fs*3);
		setH2((int) (fs*2.5));
		setH3(fs*2);
		setH4((int) (fs*1.5));
		
	}
	
	//header font sizes
	public void setH1(int h){this.h1 = h;}
	public void setH2(int h){this.h2= h;}
	public void setH3(int h){this.h3= h;}
	public void setH4(int h){this.h4= h;}
		
	
}
