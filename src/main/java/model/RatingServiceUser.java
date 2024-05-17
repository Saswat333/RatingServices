//package model;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class RatingServiceUser {
//    private final Map<String, User> users; //userId, user object
//
//    public RatingServiceUser() {
//        this.users = new HashMap<>();
//    }
//
//    public void registerUser(String userId){
//        users.put(userId, new User(userId));
//    }
//
//
//    public boolean submitSurvey(String surveyId, String userId, List<Integer> selectedOptions){
//        Survey survey = getSurvey(surveyId);
//        User currentUser = getUser(userId);
//        // survey needs to exist, user needs to be registered in survey and should not had provided a response yet
//        if(survey!=null && currentUser!=null && !survey.hasSummitedResponse(userId)){
//            survey.submitSurveyResponse(userId, selectedOptions);
//            return true;
//        }
//
//        return false;
//    }
//}
