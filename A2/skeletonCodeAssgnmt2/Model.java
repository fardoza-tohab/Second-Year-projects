package skeletonCodeAssgnmt2;


public class Model {
    int noWords = 4 ; // Words on the screen at a point in time
    int totalWords ; // Total words that will fall

  // Dictionary of words
  WordDictionary dict = new WordDictionary () ;
 // Actual array of words ( threads )
  WordRecord [] words ;
  // Shared indicator of program status
  static volatile boolean done ; // must be volatile
   // Object to keep track of scores
   Score score = new Score () ;
   
   public int getNoWords () {
      return noWords;
   
   }
   
           
	

   
   public void setNoWords ( int noWords ) {
      this.noWords = noWords;
   
   }
   
   public int getTotalWords(){
    return totalWords;
   
   }
   
   public void setTotalWords (int totalWords){
       this.totalWords = totalWords;
   
   }
   
   public WordDictionary getDict(){
      return dict;
   
   }
   
   public void setDict (WordDictionary dict) {
       this.dict = dict;
   
   }
   
   public WordRecord [] getWords(){
        return words;
   }
   
   public void setWords ( WordRecord [] words ){
      this.words = words;
   
   }

  synchronized public Score getScore (){
       return score;
  
  }
     
     
    }
    
 