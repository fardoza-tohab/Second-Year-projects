  import java.util.*;
  import java.io.*;
  
  import java.util.concurrent.ForkJoinPool;
  
  /**
  *An application to test the runtimes of a sequential and parallel median filter operation.
  *@author Fardoza Tohab
  */
  
  public class Performance{
      public static int filterSize;
      public static double [] floats2;
      public static double [] outputArray;
      
    /**
 *@param args The commandline arguments
 */
  
   public static void main(String[] args) {
     Scanner input = new Scanner(System.in);
    
    String str = " ";
    
  ArrayList<String> lines = new ArrayList<String>(); 
         
         int filterSize = input.nextInt();
         input.nextLine();
             
         try{
         
         File filename = new File(input.nextLine());
         Scanner scan = new Scanner (filename);
        
        //reading file into an ArrayList.
          while(scan.hasNext()){
            str = scan.nextLine();
            String[] elements = str.split(" ");
            
            for (int i = 0; i < elements.length; i++){
               lines.add(elements[i]);
              
             }
                 
         } 
         
         scan.close();
                         
       
           } catch (FileNotFoundException e) {
               System.out.println("File not found!");
              e.printStackTrace();
           }
           
           String[] linesArray = lines.toArray(new String[lines.size()]);
           
           ArrayList<String> floats = new ArrayList<String>();
           
           for (int j = 0; j < linesArray.length; j+=2){
               floats.add(linesArray[j].replace(",", "."));
             

           
           }
           String [] floatsArray = floats.toArray(new String [floats.size()]);
           double[] floats2 = new double[floatsArray.length];
            
            for(int k = 1; k < floatsArray.length; k++){
                floats2[k] = Double.parseDouble(floatsArray[k]);
            
            }
            
        
            
      //run the programs 20 times.
            
     for(int i = 0; i < 20 ; i++){
  
       testForkJoin(floats2);
       testSequentially(floats2);
    
    }
       
    
    
    
     }
     
   /**
  * A method to test the runtime of the parallel fork/join program
  *
  *@param floats2 The raw data array
  */

  public static void testForkJoin(double[] floats2) {
    final long start = System.currentTimeMillis();
    
    ForkJoinPool fjp = new ForkJoinPool();
    MFArray mf = new MFArray(floats2, (filterSize - 1)/2, 0, floats2.length);
    fjp.invoke(mf);

    System.out.println("Running with the fork join framework takes " + (System.currentTimeMillis() - start) + " milliseconds");
  }
  
  
   /**
  * A method that tests the runtime of the sequential algorathim for the median filter operation.
  *
  *@param floats2 The raw data input
  */

  public static void testSequentially(double[] floats2) {
  
    final long start = System.currentTimeMillis();
    
       outputArray = new double[floats2.length];
       int j = 0;
    
      for(int i = 1; i < floats2.length; i++){
      
            //create subarrays 
             double[] subArray = Arrays.copyOfRange(floats2, (i - (filterSize - 1) / 2 ), (i + ((filterSize - 1) / 2)+ 1));
                        
                       //sort array
                        Arrays.sort(subArray);
                        double median = subArray[(subArray.length/2)];
                        outputArray[i] = median;
                        
                        j++;
                          //output the median
                        String median1 = String.format("%.5f", median);
                        System.out.println(j + " " + median1);
                                                   
              }

    
    System.out.println("Running sequentially takes : " + (System.currentTimeMillis() - start) + " milliseconds.");
  }
}