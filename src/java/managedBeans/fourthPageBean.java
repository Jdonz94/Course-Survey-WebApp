/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Justin
 */
@Named(value = "fourthPageBean")
@SessionScoped
public class fourthPageBean implements Serializable{
    ArrayList<questionAnswer> answerList = new ArrayList<>();

    public ArrayList<questionAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<questionAnswer> answerList) {
        this.answerList = answerList;
    }
    public String validatePage(){
        for(questionAnswer qa : answerList){
            if(qa.getValue() == null)
                return "invalid";
        }
        return "valid";
    }
    public fourthPageBean() {
        answerList.add(new questionAnswer("28)", "What changes would you recommend to improve this course?"));
        answerList.add(new questionAnswer("29)", "What did you like best about your instructors teaching?"));
        answerList.add(new questionAnswer("30)", "What did you like least about your instructor's teaching?"));
        answerList.add(new questionAnswer("31)", "Any further constructive comments?"));
    }
    
}
