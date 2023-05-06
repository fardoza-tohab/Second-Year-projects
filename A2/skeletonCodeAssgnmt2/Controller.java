package skeletonCodeAssgnmt2;


  public class Controller{
    
    private Model model ;
    private View view ;
   
  
  Thread scoreUpdateThread ;

  Thread refreshViewThread ;

   Thread waitForGameOverThread ;
   private boolean waitingForGameOver = false ;

    public void initView () {
   /* Start refreshView and updateScore threads */
    }

   public void initController () {
   /* Give the WordRecord objects to the View */
   /* Add ActionListeners to buttons , textfield , and level selector . */
    }

   public void startGame () {
   /* Start the word threads */
   /* Start waiting for the game to finish on a new thread */
    }

   public void endGame () {
   /* Stops the WordRecord threads and resets score */
   }

   private void enterText () {
  /* Action Listener for TextBox -- checks input against WordRecords , and acts accordingly */
   }

   public String [] getDictFromFile ( String filename ) {
   /* Loads words from dictionary file into dictionary object in the Model */
    }

   public void createWords () {
 /* Creates the word threads from a sample of the dictionary */
 }
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
