package model;

import exception.EmptyOptionsValue;

import java.util.*;

public class Survey {
    private final String surveyId;
    private final String surveyTitle;
    private final String surveyDescription;
    private final List<Question> questions;
    // question and the responses
    private final Map<String, List<Integer>> userResponses; // Stores user IDs and their selected options
    private final HashSet<String> registeredUserInSurvey;


    public Survey(String surveyId, String surveyTitle, String surveyDescription) {
        this.surveyId = surveyId;
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.questions = new ArrayList<>();
        this.userResponses = new HashMap<>();
        this.registeredUserInSurvey = new HashSet<>();
    }

    public void addQuestion(String question){
        questions.add(new Question(question));
    }

    public void addOption(int questionIndex, String optionText, float weight) throws EmptyOptionsValue {
        if(questionIndex>=0 && questionIndex < questions.size())
            questions.get(questionIndex).addOptions(optionText, weight);
        else
            throw new IllegalArgumentException("Invalid question index");
    }

    public void updateOptionWeight(int questionIndex, int optionIndex, float weight){
        if(questionIndex>=0 && questionIndex < questions.size() && optionIndex>=0 && optionIndex < questions.get(questionIndex).getSurveyOptions().size())
            questions.get(questionIndex).addOptionsWeight(optionIndex, weight);
        else
            throw new IllegalArgumentException("Invalid question or option index");
    }

    public float calculateAverageSurveyRating(){
        if(userResponses.isEmpty())
            return 0;


        float questionRating = 0;
        float totalQuestionRating = 0;
        int totalResponses=userResponses.size();
        System.out.println(userResponses);
        System.out.println("Questions size: "+questions.size());
        for(List<Integer> selectedOptions: userResponses.values()){
            int questionIndex = 0;
            for(int optionIndex: selectedOptions){

                questionRating += questions.get(questionIndex).getSurveyOptions().get(optionIndex).getWeight();
                questionIndex++;
            }
            totalQuestionRating = questionRating;
        }
        System.out.println("totalQuestionRating : "+totalQuestionRating+"  totalResponses:"+totalResponses);
        return totalQuestionRating/totalResponses;
    }

    public void deleteQuestion(int questionIndex){
        if(questionIndex >= 0 && questionIndex<questions.size()){
            questions.remove(questionIndex);
        }
        else{
            throw new IllegalArgumentException("Invalid question index");
        }
    }

    public void submitSurveyResponse(String userId, List<Integer> selectedOptions){
        //if user already submitted survey don't let him submit the survey again
        if(userResponses.containsKey(userId)){
            throw new IllegalArgumentException("User already submitted the survey.");
        }
        userResponses.put(userId, selectedOptions);
    }

    public void addUser(String userId) {
        if (registeredUserInSurvey.contains(userId)) {
            throw new IllegalArgumentException("User already added to the survey");
        }
        registeredUserInSurvey.add(userId);
    }

    public boolean hasSummitedResponse(String userId){
        return userResponses.containsKey(userId);
    }
}
/*Inside a survey
1. add question
2. add option for the question
3. add option weights
4. delete question
5. calculate rating
6. submit response
7.
*
* */