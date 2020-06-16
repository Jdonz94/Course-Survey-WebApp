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
import javax.inject.Named;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Justin
 */
@Named(value = "thirdPageBean")
@SessionScoped
public class thirdPageBean implements Serializable{
    ArrayList<questionAnswer> answerList1 = new ArrayList<>();
    ArrayList<questionAnswer> answerList2 = new ArrayList<>();
    ArrayList<questionAnswer> answerList3 = new ArrayList<>();
    ArrayList<questionAnswer> answerList4 = new ArrayList<>();
    private String freeResponse1;
    private String freeResponse2;

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
                String sqlStatement = "INSERT INTO page3 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            for(questionAnswer question: answerList2){
                String sqlStatement = "INSERT INTO page3 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            for(questionAnswer question: answerList3){
                String sqlStatement = "INSERT INTO page3 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            for(questionAnswer question: answerList4){
                String sqlStatement = "INSERT INTO page3 (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
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
            if(qa.getValue() == null){
                return "invalid";
            }
        }
        for(questionAnswer qa : answerList2){
            if(qa.getValue() == null){
                return "invalid";
            }
        }
        for(questionAnswer qa : answerList3){
            if(qa.getValue() == null){
                return "invalid";
            }
        }
        for(questionAnswer qa : answerList4){
            if(qa.getValue() == null){
                return "invalid";
            }
        }
        if(freeResponse1.isEmpty() || freeResponse2.isEmpty())
            return "invalid";
        
        submitDataToDb();
        return "valid";
    }

    
   
//    GETTERS AND SETTERS
    public ArrayList<questionAnswer> getAnswerList2() {
        return answerList2;
    }

    public ArrayList<questionAnswer> getAnswerList3() {
        return answerList3;
    }
    
    public ArrayList<questionAnswer> getAnswerList1() {
        return answerList1;
    }
    public ArrayList<questionAnswer> getAnswerList4() {
        return answerList4;
    }
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
    
    
    /**
     * Creates a new instance of thirdPageBean
     */
    public thirdPageBean() {
        answerList1.add(new questionAnswer("22)", "This was a worthwhile class."));
        answerList1.add(new questionAnswer("23)", "Would you recommend this course to a fellow student?"));
        answerList2.add(new questionAnswer("24)", "Overall, how do you rate your experience in this course?"));
        answerList3.add(new questionAnswer("25)", "How many hours did you spend per week on prepartion/homework for this course?"));
        answerList4.add(new questionAnswer("26)", "I contributed constructively during in-class activities."));
        answerList4.add(new questionAnswer("27)", "I feel I am achieving the learning outcomes."));
        
        
    }
    
}
