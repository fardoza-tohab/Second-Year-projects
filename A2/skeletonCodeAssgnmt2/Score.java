
 /**
  *An application that  keep a running total of words “caught” (typed correctly), “missed” and a total score.
  *@author Fardoza Tohab
  */


public class Score {

//variables to keep a record of.
        private int missedWords;
        private int caughtWords;
        private int gameScore;


  //constructor to initialize all variables

        Score() {
                missedWords=0;
                caughtWords=0;
                gameScore=0;
        }

        // all getters and setters must be synchronized


   /*
   * Get number of words missed
   *@return number of words missed


   */
        public synchronized int getMissed() {
                return missedWords;
        }

   /*
   * Get number of words caught
   *@return number of words caught


   */
   
   
        public synchronized int getCaught() {
                return caughtWords;
        }


   /*
   * Get total words missed and caught
   *@return total


   */

        public synchronized int getTotal() {
                return (missedWords+caughtWords);
        }

   /*
   * Get the score
   *@return game score


   */

        public synchronized int getScore() {
                return gameScore;
        }


   /*
   * Increment missed counter if word is not typed in correctly


   */

        public synchronized void missedWord() {
                missedWords++;
        }



   /*
   * Increment the caught counter if word is typed in correctly.
   * Update the score
   * @param length of the word


   */
        public synchronized void caughtWord(int length) {
                caughtWords++;
                gameScore+=length;
        }



   /*
   * Resets the score to zero


   */
   
   
        public synchronized void resetScore() {
                caughtWords=0;
                missedWords=0;
                gameScore=0;
        }


 }