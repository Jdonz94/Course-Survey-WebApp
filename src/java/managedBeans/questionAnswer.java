/************************************************************************
* Programmer: Justin Donovan
*
* Course: CSCI 4391
*
* Date: 4/15/2020
*
* Assignment: Midterm Program
*
* Environment: JSF with Netbeans IDE
*
* Files Included: firstPageBean.java, secondPageBean.java, thirdPageBean.java,
* fourthPageBean.java, resultsPageBean.java, landingPage.xhtml, page2.xhtml,
* page3.xhtml, page4.xhtml, resultsPage.xhtml, web.xml, faces-config.xml,
* HawksLogo.jpeg
*
* Purpose: The purpose of this program is to build a web application that allows
* the user to review their course and then collects this information to be stored
*
* Input: The input is collected purely through JSF components on-screen from the user
*
* Preconditions/Assumptions: N/A
*
* Output: The program prints out a summary of the data in a results page at the
* end that details the question asked, the answer and which view that came from
*
* Postconditions: (optional)
*
* Algorithm:
* Input Height
* Input Weight
* Calc BMI
* Output BMI and Chart
***********************************************************************/ 
package managedBeans;

/**
 *
 * @author Justin
 */
public class questionAnswer {
     private String value;
     private String number;
     private String question;
        
        public String getValue() {
            return value;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
        public void setValue(String value) {
            this.value = value;
        }
        
        public questionAnswer(String inNumber, String inQuestion){
            this.question = inQuestion;
            this.number = inNumber;
        }
        public String toString(){
            return(this.number + this.question +": " + this.value);
        }
}
