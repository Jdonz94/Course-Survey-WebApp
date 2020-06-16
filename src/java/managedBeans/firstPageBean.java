package managedBeans;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.sql.*;
@Named(value = "firstPageBean")
@SessionScoped
public class firstPageBean implements Serializable{
    private boolean initial = true;
    private String course;
    private String instructor;
    private String term;
    ArrayList<questionAnswer> qaList = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    private String freeResponse;
    questionAnswer question9;
    questionAnswer question10; 
    
//    PAGE VALIDATION
    //IF THE PAGE IS VALIDATED SUCCESSFULLY, WE SEND THE INFORMATION ON THE PAGE TO THE DATABASE
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
            for(questionAnswer question: qaList){
                String sqlStatement = "INSERT INTO landingpage (questionNumber, question, answer) VALUES ('" + question.getNumber() +"', '" + question.getQuestion() +"', '" +question.getValue()+ "');";
                stmt.executeUpdate(sqlStatement);
            }
            String sqlStatement = "INSERT INTO landingpage (questionNumber, question, answer) VALUES ('" + question9.getNumber() +"', '" + question9.getQuestion() +"', '" +question9.getValue()+ "');";
            stmt.executeUpdate(sqlStatement);
            sqlStatement = "INSERT INTO landingpage (questionNumber, question, answer) VALUES ('" + question10.getNumber() +"', '" + question10.getQuestion() +"', '" +question10.getValue()+ "');";
            stmt.executeUpdate(sqlStatement);
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
        for(questionAnswer qa : qaList){
                
                if(qa.getValue() == null)
                    return "invalid";
        }
        if((question9.getValue() == null) || (question10.getValue() == null) || (course.isEmpty()) || (instructor.isEmpty()) || (term.isEmpty()) || freeResponse.isEmpty()){
            return "invalid";
        }
        submitDataToDb();
        return "valid";
    }
    
    
//    GETTERS AND SETTERS
    public questionAnswer getQuestion9() {
        return question9;
    }

    public void setQuestion9(questionAnswer question9) {
        this.question9 = question9;
    }

    public questionAnswer getQuestion10() {
        return question10;
    }

    public void setQuestion10(questionAnswer question10) {
        this.question10 = question10;
    }
    
    public ArrayList<questionAnswer> getQaList() {
        return qaList;
    }

    public void setQaList(ArrayList<questionAnswer> qaList) {
        this.qaList = qaList;
    }
    
    

    public String getFreeResponse() {
        return freeResponse;
    }

    public void setFreeResponse(String firstAnswer) {
        this.freeResponse = firstAnswer;
    }
    
    public void setCourse(String inCourse){
        this.course = inCourse;
    }
    
    public String getCourse(){
        return(this.course);
    }
    
    public void setInstructor(String inInstructor){
        this.instructor = inInstructor;
    }
    
    public String getInstructor(){
        return this.instructor;
    }
    
    public void setTerm(String inTerm){
        this.term = inTerm;
    }
    
    public String getTerm(){
        return this.term;
    }
    
    public boolean getInitial(){
        return(this.initial);
    }
    
  
    public void setUserInfo(){
        initial = false;
        
    }
    
    public String getUserInfo(){
        return(this.course + " " + this.instructor + " " + this.term);
    }
    
    /**
     * Creates a new instance of firstPageBean
     */
    public firstPageBean() {
       qaList.add(new questionAnswer("1)","The instructor stimulated my interest in the subject."));
       qaList.add(new questionAnswer("2)","The instructor managed classroom time and pace well."));
       qaList.add(new questionAnswer("3)","The instructor was organized and prepared for every class."));
       qaList.add(new questionAnswer("4)","The instructor encouraged discussions and responded to questions."));
       qaList.add(new questionAnswer("5)","The instructor demonstrated in-depth knowledge of the subject."));
       qaList.add(new questionAnswer("6)","The instructor appeared enthustiastic and interested."));
       qaList.add(new questionAnswer("7)","The instructor used a variety of instructional methods to reach the course objectives (e.g. group discussions, student presentations, etc.)"));
       qaList.add(new questionAnswer("8)","The instrutor challenged students to do their best work."));
       question9 =  new questionAnswer("9)", "The instructor was accessible outside of class.");
       question10 = new questionAnswer("10)", "Did the instructor actively attempt to prevent cheating in this course?");
    }
    
    
}
