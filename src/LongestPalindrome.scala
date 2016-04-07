import scala.math.min

/**
 * @author Rahul Anand Akkina
 * 
 * Snipetts is inspired from the article 
 * (Ref :
 *       http://articles.leetcode.com/longest-palindromic-substring-part-i 
 *       http://articles.leetcode.com/longest-palindromic-substring-part-ii
 * )
 */
class LongestPalindrome(val originalString: String) {
  
  private val palindromeLengths = new Array[Int]((originalString.length() * 2) + 3)
  private val reString = new Array[Char]((originalString.length() * 2) + 3)
  
  preCompute()
  
  /**
   * 
   */
  private def preCompute() = {    
     reString(0) = '$'
     reString(originalString.length() * 2 + 2) = '@'     
     List(0 to (originalString.length() - 1) by 1).flatten
                                                  .foreach{i => {
                                                                 reString(2 * i + 1) = '#' 
                                                                 reString(2 * i + 2) = originalString.charAt(i)
                                                                 }
                                                  }
     reString(originalString.length() * 2 + 1) = '#'
     
     var (center,right) = (0,0)
     
     for(i <- 1 until (reString.size - 2)){
         var mirror = 2 * center - i
         if (right > i) 
           palindromeLengths(i) = Math.min(right - i, palindromeLengths(mirror))
         
         while(reString(i + (1 + palindromeLengths(i))) == reString(i - (1 + palindromeLengths(i))))
           palindromeLengths(i) += 1
         
         if (i + palindromeLengths(i) > right){
           center = i
           right = i + palindromeLengths(i)
         }
         
     }
     
  }
  
 
  /**
   * 
   */
  private def longestPalindromicSubstring(index : Int): String = {
      val (length,center) = (palindromeLengths(index + 2),index + 2)    
      return originalString.substring((center - 1 - length) / 2, (center - 1 + length) / 2)
  }
  
  /**
  *  Prints top 3 palindrome sub-strings in the original string 
  *  with algorithmic time complexity on O(n), where n = (originalString.length() * 2) + 3
  *  please note it is not essentially unique.)
  */
  def printTop3PalindromicSubstrings() = {
      var (l1,c1,l2,c2,l3,c3) = (0,0,0,0,0,0) 
      for (i <-  0 until (palindromeLengths.length - 1)) {
            if (palindromeLengths(i) > l1) { 
                l3 = l2; l2 = l1; l1 = palindromeLengths(i)
                c3 = c2; c2 = c1; c1 = i                
            }else if (palindromeLengths(i) > l2) {
                l3 = l2; l2 = palindromeLengths(i)
                c3 = c2; c2 = i
            } else if (palindromeLengths(i) > l3) {
                l3 = palindromeLengths(i)
                c3 = i
            }
      }
      
      var palindromeStr = originalString.substring((c1 - 1 - l1) / 2, (c1 - 1 + l1) / 2)
      println("Text: " + palindromeStr + ", Index: " + originalString.indexOf(palindromeStr) + ", Length: " + palindromeStr.length())
      palindromeStr = originalString.substring((c2 - 1 - l2) / 2, (c2 - 1 + l2) / 2)
      println("Text: " + palindromeStr + ", Index: " + originalString.indexOf(palindromeStr) + ", Length: " + palindromeStr.length())
      palindromeStr = originalString.substring((c3 - 1 - l3) / 2, (c3 - 1 + l3) / 2)
      println("Text: " + palindromeStr + ", Index: " + originalString.indexOf(palindromeStr) + ", Length: " + palindromeStr.length())      
  }
  
  /**
  * Prints top limit no. of unique palindrome sub-strings in the original string.
  * with nearest efficiency ~ O(nlogn), where n = (originalString.length() * 2) 
  */
  def printTop3UniquePalindromicSubstrings(limit : Int) = {
     val list = List(0 to (2 * originalString.length()) by 1).flatten
                    .map( i => longestPalindromicSubstring(i)).distinct
                    .sortWith(_.length > _.length)         
     list.slice(0,min(limit ,list.size)).foreach{s => println("Text: " + s + ", Index: " + originalString.indexOf(s) + ", Length: " + s.length())}    
  }

}

object Main {
  def main(args: Array[String]) {
     val longestPalindrome = new LongestPalindrome(args(0))
     
     println("-------------Top 3 Palindrome Sub-strings-------------");
     //Time Complexity : O(N)
     longestPalindrome.printTop3PalindromicSubstrings()    
     println("-------------Top N Unique Palindrome Sub-strings-------------");
     //Time Complexity ~ O(3N) - since we identify distinct substring elements.
     longestPalindrome.printTop3UniquePalindromicSubstrings(5)
  }
}
