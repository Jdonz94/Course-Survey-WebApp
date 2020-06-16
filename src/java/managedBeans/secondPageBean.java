package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Justin
 */
@Named(value = "secondPageBean")
@SessionScoped
public class secondPageBean implements Serializable{
    ArrayList<questionAnswer> answerList1 = new ArrayList<>();
    ArrayList<questionAnswer> answerList2 = new ArrayList<>();
    ArrayList<questionAnswer> answerList3 = new ArrayList<>();
    private String freeResponse1;
    private String freeResponse2;
    private String freeResponse3;
    
    
    public void submitDataToDb(){
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/survey?autoReconnect=true&useSSL=false";
        final String USER = "root";
        final String PASS = "root";
        String studentName;
        
        
        try{
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            for(questionAnswer question: answerList1){
                String sqlStatement = "INSERT INTO page2 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            for(questionAnswer question: answerList2){
                String sqlStatement = "INSERT INTO page2 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            for(questionAnswer question: answerList3){
                String sqlStatement = "INSERT INTO page2 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            stmt.close();
            con.close();
        }catch(ClassNotFoundException e){
            e.printStackTrace(System.out);
        }
        catch(SQLException e){
            
                System.out.println("Message: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Vendor Error: " + e.getErrorCode());
             
        }
    }
    public String validatePage(){
        for(questionAnswer qa : answerList1){
            
            if(qa.getValue() == null)
                return "invalid";
        }
        for(questionAnswer qa : answerList2){
            
            if(qa.getValue() == null)
                return "invalid";
        }
        for(questionAnswer qa : answerList3){
            
            if(qa.getValue() == null)
                return "invalid";
        }
        if(freeResponse1.isEmpty() || freeResponse2.isEmpty() || freeResponse3.isEmpty())
            return "invalid";
        
        submitDataToDb();
        return "valid";
    }
    
//    GETTERS AND SETTERS
    public String getFreeResponse1() {
        return freeResponse1;
    }

    public void setFreeResponse1(String freeResponse1) {
        this.freeResponse1 = freeResponse1;
    }

    public String getFreeResponse2() {
        return freeResponse2;
    }

    public void setFreeResponse2(String freeResponse2) {
        this.freeResponse2 = freeResponse2;
    }

    public String getFreeResponse3() {
        return freeResponse3;
    }

    public void setFreeResponse3(String freeResponse3) {
        this.freeResponse3 = freeResponse3;
    }
    public ArrayList<questionAnswer> getAnswerList2() {
        return answerList2;
    }

    public void setAnswerList2(ArrayList<questionAnswer> answerList2) {
        this.answerList2 = answerList2;
    }

    public ArrayList<questionAnswer> getAnswerList3() {
        return answerList3;
    }

    public void setAnswerList3(ArrayList<questionAnswer> answerList3) {
        this.answerList3 = answerList3;
    }
    

    public ArrayList<questionAnswer> getAnswerList1() {
        return answerList1;
    }

    public void setAnswerList1(ArrayList<questionAnswer> answerList1) {
        this.answerList1 = answerList1;
    }
    
    public secondPageBean() {
        answerList1.add(new questionAnswer("11)", "Information about the assessment was communicated clearly"));
        answerList1.add(new questionAnswer("12)", "Feedback was provided within the stated timeframe"));
        answerList1.add(new questionAnswer("13)", "Feedback showed how to improve my work (e.g. corrections including comments)"));
        answerList2.add(new questionAnswer("14)", "The course was supported by adequate library resources."));
        answerList2.add(new questionAnswer("15)", "Blackboard resources for the course were useful."));
        answerList2.add(new questionAnswer("16)", "Instructor gave guidance on where to find resources"));
        answerList3.add(new questionAnswer("17)", "The syllabus was explained at the beginning of the course."));
        answerList3.add(new questionAnswer("18)", "The course was delivered as outlined in the syllabus"));
        answerList3.add(new questionAnswer("19)", "Instructor explained the grading criteria of the course."));
        answerList3.add(new questionAnswer("20)", "Exams related to the course learning outcomes."));
        answerList3.add(new questionAnswer("21)", "Projects/assignments related to the course learning outcomes."));
    }
    
}
