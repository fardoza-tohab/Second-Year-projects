
 import java.util.*;
   import java.io.*;

  /**
  *An application to read in a text file and store the data items within an Array and perform the median filter operation.
  *@author Fardoza Tohab
  */


  public class MedianFilter{
    static int filterSize ;
    
  /**
  * Finds the median of a subarray 
  *
  *@param filterSize The filter size given
  *@param data[] The raw data array
  *@return the median of a given subArray
  */
    
    
    static double median(int  filterSize, double data[]){ 
	     double result;	
	
	    if(filterSize % 2 == 1){
       
		   result = data[((filterSize+1)/2)-1];
	 	
	   }else{
      
		result = (data[filterSize/2-1]+data[filterSize/2])/2;
		
	   }
     return result;
	
     }
     
 /**
 *@param args The commandline arguments
 */
      
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
    
          String str = " ";
    
        ArrayList<String> lines = new ArrayList<String>(); 
         
   
   
         filterSize = Integer.parseInt(args[1]);
          
          
         
         try{
         
         File filename = new File(args[0]);
         Scanner scan = new Scanner (filename);
         str = scan.nextLine();
      
      
      //read data into an ArrayList
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
            
            int j = 0;
                      
         for(int i = 1; i < floats2.length; i++){
         //create subArrays from raw data
             double[] subArray = Arrays.copyOfRange(floats2, (i - (filterSize - 1) / 2 ), (i + ((filterSize - 1) / 2)+ 1));

               //sort each subArray
                        Arrays.sort(subArray);
                        double median = median(filterSize, subArray);
                        outputArray[i] = median;
                        
                        j++;
                          
                        String median1 = String.format("%.5f", median);
                        //print formatted median.
                        System.out.println(j + " " + median1);
                             
              }
                         
          
            
            for(int k = floats2.length-(filterSize-1)/2; k < floats2.length; k++){
               String fl1 = String.format("%.5f", floats2[k]);
               System.out.println((k+1)+ " " +fl1);
            
            }

            
                      
  }

}
       

 		
	

                              