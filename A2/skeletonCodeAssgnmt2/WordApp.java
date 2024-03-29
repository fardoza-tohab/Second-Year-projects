

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.Scanner;
import java.util.concurrent.*;

public class WordApp {
//shared variables
        static int noWords=4;
        static int totalWords;

        static int frameX=1000;
        static int frameY=600;
        static int yLimit=480;




        static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

        static WordRecord[] words;
        static volatile boolean done;  //must be volatile
        static  Score score = new Score();
        static WordRecord wr = new WordRecord();

        static WordPanel w;


        static private JLabel caught;
        static private JLabel missed;
        static private JLabel scr;

  
  /*
   * A method to update the labels on the display

  */
      public static void lables(){
      caught.setText("Caught: " + score.getCaught() + "    ");
      missed.setText("Missed:" + score.getMissed()+ "    ");
      scr.setText("Score:" + score.getScore()+ "    ");
       

   }
     /*
     * A method to set up the GUI.
     * @param frameX frame x dimension
     * @param frameY frame y dimension
     * @param yLimit limit of the y dimension
     */

        public static void setupGUI(int frameX,int frameY,int yLimit) {



                // Frame init and dimensions
        JFrame frame = new JFrame("WordGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameX, frameY);

      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS));
      g.setSize(frameX,frameY);

                w = new WordPanel(words,yLimit);
                w.setSize(frameX,yLimit+100);
           g.add(w);

      JPanel txt = new JPanel();
      txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS));
            caught =new JLabel("Caught: " + score.getCaught() + "    ");
            missed =new JLabel("Missed:" + score.getMissed()+ "    ");
            scr =new JLabel("Score:" + score.getScore()+ "    ");


      txt.add(caught);
           txt.add(missed);
           txt.add(scr);

            //[snip]


           final JTextField textEntry = new JTextField("",20);
           textEntry.addActionListener(new ActionListener()
           {
              public void actionPerformed(ActionEvent evt) {
                 String text = textEntry.getText();
                 for(int i = 0; i < noWords; i++){
              if(words[i].matchWord(text)){ //Check if words match
                  score.caughtWord(text.length());
                  score.getTotal();
              //update labels on display

                  lables();

              }



            }
                 textEntry.setText("");
                 textEntry.requestFocus();
              }
           });

           txt.add(textEntry);
           txt.setMaximumSize( txt.getPreferredSize() );
           g.add(txt);

           JPanel b = new JPanel();
           
           b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
           JButton startB = new JButton("Start");;

                        // add the listener to the jbutton to handle the "pressed" event
         startB.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                   {
                      //[snip]

            


           if(done == false){
               w.start();
                done = true;

            }else {
           score.resetScore();

            for(WordRecord word: words){
               word.resetWord();


             }

              done = false;
              lables();

             }

             g.repaint();


                 textEntry.requestFocus();  //return focus to the text entry field

                   }
                });
                JButton endB = new JButton("End");;


                                // add the listener to the jbutton to handle the "pressed" event
                endB.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                   {

          try{


          TimeUnit.SECONDS.sleep(1);

          }catch(Exception y){

              y.printStackTrace();}
                          textEntry.requestFocus();

              JOptionPane.showMessageDialog(frame, "Game over!");

           //[snip]
                   }
                });


      JButton quitB = new JButton("Quit");

      quitB.addActionListener (new ActionListener () {
      public void actionPerformed (ActionEvent e) {
      System.exit(0);
      }
     });

      b.add(quitB);


                b.add(startB);
                b.add(endB);

                g.add(b);

      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      
        frame.setContentPane(g);
        //frame.pack();  // don't do this - packs it into small space
      frame.setVisible(true);
        }



        /* Get the dictionary  from file.
         * @param filename
         * @param return dictionary string
         */

   public static String[] getDictFromFile(String filename) {
                String [] dictStr = null;
                try {
                        Scanner dictReader = new Scanner(new FileInputStream(filename));
                        int dictLength = dictReader.nextInt();
                        //System.out.println("read '" + dictLength+"'");

                        dictStr=new String[dictLength];
                        for (int i=0;i<dictLength;i++) {
                                dictStr[i]=new String(dictReader.next());
                                //System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
                        }
                        dictReader.close();
                } catch (IOException e) {
                System.err.println("Problem reading file " + filename + " default dictionary will be used");
            }
                return dictStr;
        }



           /*
            *@param args The commandline arguments
            */

        public static void main(String[] args) {


                //deal with command line arguments
                totalWords=Integer.parseInt(args[0]);  //total words to fall
                noWords=Integer.parseInt(args[1]); // total words falling at any point
                assert(totalWords>=noWords); // this could be done more neatly
                String[] tmpDict=getDictFromFile(args[2]); //file of words
                if (tmpDict!=null)
                        dict= new WordDictionary(tmpDict);

                WordRecord.dict=dict; //set the class dictionary for the words.

                words = new WordRecord[noWords];  //shared array of current words

                //[snip]

                setupGUI(frameX, frameY, yLimit);
        //Start WordPanel thread - for redrawing animation

                int x_inc=(int)frameX/noWords;
                //initialize shared array of current words

                for (int i=0;i<noWords;i++) {
                        words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
                }
        }
}
