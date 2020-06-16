/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Justin
 */
@Named(value = "resultsPageBean")
@SessionScoped
public class resultsPageBean implements Serializable{
    ArrayList<String> page1Answers = new ArrayList<>();

    public ArrayList<String> getPage1Answers() {
        return page1Answers;
    }

    public void setPage1Answers(ArrayList<String> page1Answers) {
        this.page1Answers = page1Answers;
    }

    public ArrayList<String> getPage2Answers() {
        return page2Answers;
    }

    public void setPage2Answers(ArrayList<String> page2Answers) {
        this.page2Answers = page2Answers;
    }

    public ArrayList<String> getPage3Answers() {
        return page3Answers;
    }

    public void setPage3Answers(ArrayList<String> page3Answers) {
        this.page3Answers = page3Answers;
    }
    ArrayList<String> page2Answers = new ArrayList<>();
    ArrayList<String> page3Answers = new ArrayList<>();
    
    
    //THIS IS THE ALGORITHM THAT CALCULATES HOW FREQUENTLY A CERTAIN ANSWER OCCURS IN THE DATA SET
    //THE INPUT IS AN ARRAY OF ANSWERS AND THE TYPE OF QUESTION THAT IT IS
    //THE OUTPUT IS A FULLY FORMATTED STRING THAT DISPLAYS THE STATISTICS OF THAT QUESTIONS DATA SET
    public String returnStats(ArrayList<String> answers,String questionFormat){
        Map<String, Integer> statsMap = new HashMap<>();
        int numberOfEntries = answers.size();
        if(questionFormat.equals("Strongly")){
            statsMap.put("Strongly Agree", 0);
            statsMap.put("Agree",0);
            statsMap.put("Neutral", 0);
            statsMap.put("Disagree", 0);
            statsMap.put("Strongly Disagree", 0);
            for(String answer: answers){
                int prevCount = statsMap.get(answer);
                statsMap.put(answer, prevCount + 1);
            }
            return("Strongly agree: " + intToDoub(statsMap.get("Strongly Agree"), numberOfEntries) + " Agree: " + intToDoub(statsMap.get("Agree"), numberOfEntries)
                    + " Neutral: " + intToDoub(statsMap.get("Neutral"), numberOfEntries) + " Disagree: " + intToDoub(statsMap.get("Disagree"), numberOfEntries)
                    + " Strongly Disagree: " + intToDoub(statsMap.get("Strongly Disagree"), numberOfEntries));
        }
        else if(questionFormat.equals("Excellent")){
            statsMap.put("Excellent", 0);
            statsMap.put("Very Good",0);
            statsMap.put("Good", 0);
            statsMap.put("Fair", 0);
            statsMap.put("Poor", 0);
            for(String answer: answers){
                int prevCount = statsMap.get(answer);
                statsMap.put(answer, prevCount + 1);
            }
            return("Excellent: " + intToDoub(statsMap.get("Excellent"), numberOfEntries) + " Very Good: " + intToDoub(statsMap.get("Very Good"), numberOfEntries)
                    + " Good: " + intToDoub(statsMap.get("Good"), numberOfEntries) + " Fair: " + intToDoub(statsMap.get("Fair"), numberOfEntries)
                    + " Poor: " + intToDoub(statsMap.get("Poor"), numberOfEntries));
        }
        else if(questionFormat.equals("A")){
            statsMap.put("A: 0-4H", 0);
            statsMap.put("B: 5-8H",0);
            statsMap.put("C: 9-12H", 0);
            statsMap.put("D: 13-16H", 0);
            for(String answer: answers){
                //else if(answersList.contains("A: 0-4H") || answersList.contains("B: 5-8H") || answersList.contains("C: 9-12H") || answersList.contains("D: 13-16H"))
                int prevCount = statsMap.get(answer);
                statsMap.put(answer, prevCount + 1);
            }
            return("A: 0-4H: " + intToDoub(statsMap.get("A: 0-4H"), numberOfEntries) + " B: 5-8H: " + intToDoub(statsMap.get("B: 5-8H"), numberOfEntries)
                    + " C: 9-12H: " + intToDoub(statsMap.get("C: 9-12H"), numberOfEntries) + " D: 13-16H: " + intToDoub(statsMap.get("D: 13-16H"), numberOfEntries));
        }
        else if(questionFormat.equals("Yes")){
            statsMap.put("Yes", 0);
            statsMap.put("No",0);
            for(String answer: answers){
                int prevCount = statsMap.get(answer);
                statsMap.put(answer, prevCount + 1);
            }
            return("Yes: " + intToDoub(statsMap.get("Yes"), numberOfEntries) + " No: " + intToDoub(statsMap.get("No"), numberOfEntries));
                    
        }
        return("");
        
    }
    
    //THIS METHOD JUST TAKES TWO INTEGERS AND FINDS THE PERCENTAGE OF NUM1/NUM2
    //INPUT IS TWO INTS
    //OUTPUT IS A STRING FORMATTED TO TWO DECIMAL PLACES
    public static String intToDoub(int num1, int num2) {
		Double result = 0.0;
		result = (num1 * 1.0)/(num2 * 1.0);
		result = result * 100;
		String resultString = String.format("%.2f", result) + "%";
		return(resultString);
    }
    
    //THIS METHOD CONNECTS TO THE DATABASE AND READS THE INFORMATION THAT HAS BEEN COMMITTED VIA THE OTHER PAGES
    //NO INPUT
    //NO OUTPUT EITHER, INFORMATION THAT IS DERIVED FROM THIS CLASS IS SENT TO ARRAYS THAT THE MANAGED BEAN READS FROM DIRECTLY
    public void pullReport(){
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/survey?autoReconnect=true&useSSL=false";
        final String USER = "root";
        final String PASS = "root";
        ArrayList<String> questionNumberList = new ArrayList<>();
        ArrayList<String> queryList = new ArrayList<>();
        ArrayList<String> answersList = new ArrayList<>();
        String tableName = "landingpage";
        String getTableQuestions = "SELECT questionNumber FROM " +tableName+ ";";


        try{
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            
            //Starting here, code would have to be duplicated for each table in the database
            //Page 1
            ResultSet questionNumbers = stmt.executeQuery(getTableQuestions);
            Statement stmt2 = con.createStatement();
            while(questionNumbers.next()){
                String number = questionNumbers.getString("questionNumber");
                if(!questionNumberList.contains(number)){
                    questionNumberList.add(number);
                    queryList.add("SELECT answer FROM " + tableName + " WHERE questionNumber='" + number + "';");
                }
            }
            for(String query: queryList){
                ResultSet answers = stmt2.executeQuery(query);
                while(answers.next()){
                    answersList.add(answers.getString("answer"));
                }
                if(answersList.contains("Yes") || answersList.contains("No"))
                    page1Answers.add(returnStats(answersList, "Yes"));
                else if(answersList.contains("Excellent") || answersList.contains("Very Good") || answersList.contains("Good") || answersList.contains("Poor"))
                    page1Answers.add(returnStats(answersList, "Excellent"));
                else if(answersList.contains("A: 0-4H") || answersList.contains("B: 5-8H") || answersList.contains("C: 9-12H") || answersList.contains("D: 13-16H"))
                    page1Answers.add(returnStats(answersList, "A"));
                else
                    page1Answers.add(returnStats(answersList, "Strongly"));
                answersList.clear();
            }
            stmt.close();
            stmt2.close();
            
            //Page2
            tableName = "page2";
            getTableQuestions = "SELECT questionNumber FROM page2;";
            Statement stmt3 = con.createStatement();
            Statement stmt4 = con.createStatement();
            questionNumbers = stmt3.executeQuery(getTableQuestions);
            queryList.clear();
            while(questionNumbers.next()){
                String number = questionNumbers.getString("questionNumber");
                if(!questionNumberList.contains(number)){
                    questionNumberList.add(number);
                    queryList.add("SELECT answer FROM " + tableName + " WHERE questionNumber='" + number + "';");
                }
            }
            for(String query: queryList){
                ResultSet answers = stmt4.executeQuery(query);
                while(answers.next()){
                    answersList.add(answers.getString("answer"));
                }
                if(answersList.contains("Yes") || answersList.contains("No"))
                    page2Answers.add(returnStats(answersList, "Yes"));
                else if(answersList.contains("Excellent") || answersList.contains("Very Good") || answersList.contains("Good") || answersList.contains("Poor"))
                    page2Answers.add(returnStats(answersList, "Excellent"));
                else if(answersList.contains("A: 0-4H") || answersList.contains("B: 5-8H") || answersList.contains("C: 9-12H") || answersList.contains("D: 13-16H"))
                    page2Answers.add(returnStats(answersList, "A"));
                else
                    page2Answers.add(returnStats(answersList, "Strongly"));
                answersList.clear();
            }
            
            stmt3.close();
            stmt4.close();
            
            //Page3
            tableName = "page3";
            getTableQuestions = "SELECT questionNumber FROM page3;";
            Statement stmt5 = con.createStatement();
            Statement stmt6 = con.createStatement();
            questionNumbers = stmt5.executeQuery(getTableQuestions);
            queryList.clear();
            while(questionNumbers.next()){
                String number = questionNumbers.getString("questionNumber");
                if(!questionNumberList.contains(number)){
                    questionNumberList.add(number);
                    queryList.add("SELECT answer FROM " + tableName + " WHERE questionNumber='" + number + "';");
                }
            }
            for(String query: queryList){
                ResultSet answers = stmt6.executeQuery(query);
                while(answers.next()){
                    answersList.add(answers.getString("answer"));
                }
                if(answersList.contains("Yes") || answersList.contains("No"))
                    page3Answers.add(returnStats(answersList, "Yes"));
                else if(answersList.contains("Excellent") || answersList.contains("Very Good") || answersList.contains("Good") || answersList.contains("Fair") || answersList.contains("Poor"))
                    page3Answers.add(returnStats(answersList, "Excellent"));
                else if(answersList.contains("A: 0-4H") || answersList.contains("B: 5-8H") || answersList.contains("C: 9-12H") || answersList.contains("D: 13-16H"))
                    page3Answers.add(returnStats(answersList, "A"));
                else
                    page3Answers.add(returnStats(answersList, "Strongly"));
                answersList.clear();
            }
            
            stmt5.close();
            stmt6.close();
            
            con.close();
            
            
            }
            catch(ClassNotFoundException e){
                e.printStackTrace(System.out);
            }
            catch(SQLException e){
            
                System.out.println("Message: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
            }
    }
    /**
     * Creates a new instance of resultsPageBean
     */
    public resultsPageBean() {
        
    }
    
}
