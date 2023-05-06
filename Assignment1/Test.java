
import java.util.*;
   import java.io.*;

  public class Test{
    static int filterSize ;
    
    static double median(int  filterSize, double data[]){ 
	     double result;	
	
	    if(filterSize % 2 == 1){
       
		   result = data[((filterSize+1)/2)-1];
	 	
	   }else{
      
		result = (data[filterSize/2-1]+data[filterSize/2])/2;
		
	   }
     return result;
	
     }
                
      
    public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    
    String str = " ";
    
  ArrayList<String> lines = new ArrayList<String>(); 
         
         int filterSize = input.nextInt();
         input.nextLine();
             
         try{
         
         File filename = new File(input.nextLine());
         Scanner scan = new Scanner (filename);
        
        
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
            
            
            double[] result = new double[floats2.length];
            
            int j = 0;
            
                     
           System.out.println("0" +  " "+ floats2[0]);
            
         for(int i = 1; i < floats2.length; i++){
             double[] subArray = Arrays.copyOfRange(floats2, (i - (filterSize - 1) / 2 ), (i + ((filterSize - 1) / 2)+ 1));

            
                        Arrays.sort(subArray);
                        double median = median(filterSize, subArray);
                        result[i] = median;
                        
                        j++;
                          
                        String median1 = String.format("%.5f", median);
                        
                        System.out.println(j + " " + median1);
                             
              }
             System.out.println(floats2.length + " " + floats2[floats2.length - 1]);
               
            
            }

           
           }   

 