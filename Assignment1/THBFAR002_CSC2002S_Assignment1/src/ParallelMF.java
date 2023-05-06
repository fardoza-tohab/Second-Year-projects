import java.util.*;
import java.io.*;

import java.util.concurrent.ForkJoinPool;

 /**
  *An application to read in a text file and store the data items within an Array and invoke the parallel program
  *@author Fardoza Tohab
  */

public class ParallelMF{

   static int filterSize = 0;
   
   // static instance of the ForkJoinPool class.
	static final ForkJoinPool fjPool = new ForkJoinPool();
   
   
 /**
 *@param args The commandline arguments
 */
 		
	public static void main(String[] args) {
		    Scanner input = new Scanner(System.in);
    
          String str = " ";
    
        ArrayList<String> lines = new ArrayList<String>(); 
         
   
   
         filterSize = Integer.parseInt(args[1]);
             
         try{
         
         File filename = new File(args[0]);
         Scanner scan = new Scanner (filename);
         str = scan.nextLine();
      
      //read in data 
          while(scan.hasNext()){
            str = scan.nextLine();
            //System.out.println(str);
            String[] elements = str.split(" ");
            
            //for (int i = 0; i < elements.length; i++){
               lines.add(elements[1].replace(",", "."));
              
             //}
                 
         } 
         
         scan.close();
                         
       
           } catch (FileNotFoundException e) {
               System.out.println("File not found!");
              e.printStackTrace();
           }
           
           /*String[] linesArray = lines.toArray(new String[lines.size()]);
           
           ArrayList<String> floats = new ArrayList<String>();
           
           for (int j = 0; j < linesArray.length; j+=2){
               floats.add(linesArray[j].replace(",", "."));
             

           
           }*/
           //System.out.println("floats size: "+floats.size());
           
           //String [] floatsArray = floats.toArray(new String [floats.size()]);
           double[] floats2 = new double[lines.size()];
            
            for(int k = 0; k < lines.size(); k++){
                floats2[k] = new Double(lines.get(k));
            
            }
            
            for(int i = 0; i < (filterSize-1)/2; i++){
            
                 String fl = String.format("%.5f", floats2[i]);

                 System.out.println((i)+" " + fl);
            
            }
            
            double[] outputArray = new double[floats2.length];
            
            //Create instance of parallel class.   
            MFArray mf = new MFArray(floats2, filterSize, (filterSize-1)/2, floats2.length-(filterSize-1)/2);
            //invoke parallel class
            fjPool.invoke(mf);
            
            
            for(int k = floats2.length-(filterSize-1)/2; k < floats2.length; k++){
              String fl1 = String.format("%.5f", floats2[k]);
               System.out.println((k+1)+ " " +fl1);

                          
            }

            
                      
  }

}
       

 		
	

              