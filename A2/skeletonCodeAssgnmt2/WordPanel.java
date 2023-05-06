
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;


/**
  *A class that animates the typing game and creates the GUI
  *@author Fardoza Tohab
  */

public class WordPanel extends JPanel implements Runnable {

 
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;

      private boolean running = true;
      private Thread thread;
      WordApp wa = new WordApp();
      
	WordRecord wr = new WordRecord();	
   
   public Image rainbow;
   
   public JLabel label;
   
      /*
    * A method that paints and adds features to the word panel
    * @param g An object of the graphics class
    */
   
   
      
		public void paintComponent(Graphics g) {
           
  		    int width = getWidth();
		    int height = getHeight();
          
          /* g.setColor(Color.black);
          
		    g.fillRect(0,0,width,height);
          
          
                 
          g.drawImage(rainbow, 0, 0, null);
          g.drawImage(rainbow, 100, 100, null);

          g.setColor(Color.black);
          
		    g.fillRect(0,0,width,height);

          */
          
          g.drawImage(rainbow, 0, 0, width, height, null);
          
          
          
                 
		    g.setColor(Color.yellow);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.white);
         
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	 
          	
            
          
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
            
            
               words[i].drop(words[i].getSpeed()/100);
               
               if(words[i].getY()==words[i].getMaxY()){
               
                   WordApp.score.missedWord();
                   WordApp.lables();
                   words[i].resetWord();
                  
                
                   
                   
                   
               
               }
              
		    }
		   
  
         
		  }
        
        
        
       /*
       * A constructor to initialize the word array and the maximum y value
       * @param words The words that need to appear on the screen
       * @param maxY The maximum Y dimension
       */
       		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;
         
         try{
         
         rainbow = ImageIO.read(new File("zenitsu.jpg"));
         
         }catch(IOException e){
           throw new RuntimeException(e);
         
         }
        	
		}
      
      
    /*
     * Start the thread

     */
      
      
      public synchronized void start(){
         thread = new Thread(this);
         thread.start();
      
      
      }
      
      
      
     /*
     * Stop the thread
     */
      
      public synchronized void stop(){
          
         try {  
          thread.join();
                     
         
        }
       catch ( InterruptedException e) {
         e.printStackTrace();
         System.out.println("An error occured!");
       }  
                 
 
      
      }
      
      
     		
		public void run() {
         
         while(running){
         
         try { 
          repaint(); 
          Thread.sleep(1000);
          
         
        }
       catch ( InterruptedException e) {
         System.out.println("An error occured!");
         e.printStackTrace();
       }  
      }           
	
      }
         
      }

	


