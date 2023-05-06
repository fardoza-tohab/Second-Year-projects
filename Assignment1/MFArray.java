
 import java.util.*;
 import java.io.*;
 import java.util.concurrent.RecursiveAction;

 /**
  *A parallel application to smooth out a given Array using the median filter operation (using the Fork/Join framework)
  *@author Fardoza Tohab
  */
 
 
  public class MFArray extends RecursiveAction{
     int low; 
	  int high;
	  double [] floats2;
	  static final int SEQUENTIAL_CUTOFF= 4000;
     static int filterSize = 3;

	  double[] outputArray; 
     
    MFArray(double[] floats2, int filterSize, int l, int h){
         this.floats2 = floats2;
         this.low = l;
         this.high = h;
         this.filterSize = filterSize;
                
    }      
    
    
  /**
  * Finds the median of a subarray 
  *
  *@param filterSize The filter size given
  *@param data[] The raw data array
  *@return the median of a given subArray
  */
       
     public double median(int  filterSize, double data[]){ 
	     double result;	
	/*
	    if(filterSize % 2 == 1){
       
		   result = data[((filterSize+1)/2)-1];
	 	
	   }else{
      
		result = (data[filterSize/2-1 + 1]+data[filterSize/2])/2;
		
	   }*/
      //System.out.println("data array is "+data.length+" long");
      result = data[(filterSize-1)/2];
      
      //result = 5;
     return result;
	
     }

     
  /**
  * The compute function that uses divide and conquer algorithm to divide the operation into subtasks
  
  */
          
     protected void compute() {
     
       outputArray = new double[floats2.length]; 
       

        if ((high - low) < SEQUENTIAL_CUTOFF) {

                int j = 0;

                for(int i = low; i < high; i++){
                
                        //System.out.println("size of floats is :"+floats2.length);
                         double[] subArray = Arrays.copyOfRange(floats2, (i - (filterSize - 1)/2), (i + (filterSize-1)/2 +1));
                       // System.out.println("SILTER SIZE: "+filterSize+"\nSTART INDEX: "+(i - (filterSize - 1)/2)+"\nEND INDEX :"+(i + (filterSize-1)/2 +1));
                        //System.out.println("size of sub array pre sort is :"+subArray.length);
                         Arrays.sort(subArray);
                         //System.out.println("size of sub array post sort is :"+subArray.length);
                         double median = median(filterSize, subArray);

                          outputArray[i] = median;
                                              
                          j++;
                          
                          //print the median
                          
                          String median1 = String.format("%.5f", median);
                           System.out.println(j + " " + median1);

                                                     
              

                                      
                
                
        }//end of for loop

        
      } else {

           MFArray left = new MFArray(floats2, filterSize, low, (high + low) / 2);
           MFArray right = new MFArray(floats2, filterSize, (high + low) / 2, high);
            left.fork();
			   right.compute();
			   left.join();  
            
           
			  
    }
  }
}

   