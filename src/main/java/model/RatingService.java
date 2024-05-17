/*
* API
* */
package model;

import exception.EmptyOptionsValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RatingService {
    private final Map<String, Survey> surveys; //surveyId, survery object
    private final Map<String, User> users; //userId, user object

    public RatingService() {
        this.surveys = new HashMap<>();
        this.users = new HashMap<>();
    }

    //Admin functionalities
    public String createSurvey(String surveyTitle, String surveyDescription){
        String surveyId = UUID.randomUUID().toString();
        Survey survey = new Survey(surveyId, surveyTitle, surveyDescription);
        surveys.put(surveyId, survey);
        return surveyId;
    }

    public void addQuestion(String surveyId, String question){
        Survey survey = getSurvey(surveyId);
        survey.addQuestion(question);
    }

    public void addOptions(String surveyId, int questionIndex, String optionText, float optionWeight) {
        Survey survey = getSurvey(surveyId);
        try{
            survey.addOption(questionIndex, optionText, optionWeight);
        }
        catch (EmptyOptionsValue ex){
            System.out.println(ex.toString());
        }
    }

    public void updateOptionWeight(String surveyId, int questionIndex, int optionIndex, float optionWeight){
        Survey survey = getSurvey(surveyId);
        survey.updateOptionWeight(questionIndex, optionIndex, optionWeight);
    }

    public void deleteQuestion(String surveyId, int questionIndex){
        Survey survey = getSurvey(surveyId);
        survey.deleteQuestion(questionIndex);
    }

    public float calculateSurveyRating(String surveyId){
        Survey survey = getSurvey(surveyId);
        return survey.calculateAverageSurveyRating();
    }

    public float getOverallRating(){
        float surveyRatingSum=0;
        float surveyCount =0;
        for(Survey survey: surveys.values()){
            float surveyRating = survey.calculateAverageSurveyRating();
            if(surveyRating>0){
                surveyRatingSum+=surveyRating;
                surveyCount++;
            }
        }
        return surveyCount>0 ? surveyRatingSum/surveyCount : 0;
    }

    public void shareSurvey(String surveyId, String userId){
        //when sharing a survey to any user we can add him to the map of user and surveyId
        Survey survey = getSurvey(surveyId);
        User user = getUser(userId);
        survey.addUser(user.getUserId());
    }


    //User functionality
    public void registerUser(String userId){
        users.put(userId, new User(userId));
    }


    public boolean submitSurvey(String surveyId, String userId, List<Integer> selectedOptions){
        Survey survey = getSurvey(surveyId);
        User currentUser = getUser(userId);
        // survey needs to exist, user needs to be registered in survey and should not had provided a response yet
        if(survey!=null && currentUser!=null && !survey.hasSummitedResponse(userId)){
            survey.submitSurveyResponse(userId, selectedOptions);
            return true;
        }

        return false;
    }

    private Survey getSurvey(String surveyId){
        Survey survey = surveys.get(surveyId);
        if(survey == null){
            throw new IllegalArgumentException("Survey Not found: "+surveyId);
        }
        return survey;
    }

    private User getUser(String userId){
        User user = users.get(userId);
        if(user == null){
            throw new IllegalArgumentException("User Not found: "+userId);
        }
        return user;
    }
}

/*
ADMIN
* 1. create survey
* 2. create questions
* 3. add options
* 4. define weight of each options
* 5. deletequestion
* 6. calculatesurveyrating
* 7. getOveralRating
* 8. share survey
*
* USER
9. registerUser
10. submitSurvey
* */
