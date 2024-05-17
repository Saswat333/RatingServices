import model.RatingService;

import java.util.ArrayList;
import java.util.List;

public class RatingServiceMain {
    public static void main(String[] args) {
        RatingService ratingService = new RatingService();


        //admin functionality
        String surveyId = ratingService.createSurvey("Resturant Experience Survey","Rate your recent dining experience.");
        int questionNumber = 0;
        ratingService.addQuestion(surveyId, "How was the food quality ? ");
        ratingService.addOptions(surveyId,0,"Excellent", 5); // surveyId, optionIndex,, optionText, weight
        ratingService.addOptions(surveyId,0,"Good", 4);
        ratingService.addOptions(surveyId,0,"Average", 3);
        ratingService.addOptions(surveyId,0,"Below Average", 2);
        ratingService.addOptions(surveyId,0,"Poor", 1);

        ratingService.addQuestion(surveyId, "How was the service ? ");
        ratingService.addOptions(surveyId,1,"Excellent", 5); // surveyId, optionIndex,, optionText, weight
        ratingService.addOptions(surveyId,1,"Good", 4);
        ratingService.addOptions(surveyId,1,"Average", 3);
        ratingService.addOptions(surveyId,1,"Below Average", 2);
        ratingService.addOptions(surveyId,1,"Poor", 1);

        //admin share survey
        ratingService.registerUser("user1");
        ratingService.shareSurvey(surveyId, "user1");

        ratingService.registerUser("user2");
        ratingService.shareSurvey(surveyId, "user2");

        //user functionality
        String userId1 = "user1";
        List<Integer> selectedOption1 = new ArrayList<>();
        selectedOption1.add(0);
        selectedOption1.add(3);

        String userId2 = "user2";
        List<Integer> selectedOption2 = new ArrayList<>();
        selectedOption2.add(1);
        selectedOption2.add(2);

        boolean submitted1 = ratingService.submitSurvey(surveyId, userId1, selectedOption1);
        if(submitted1)
            System.out.println("Survey submitted successfully. user:"+userId1);
        else
            System.out.println("Submission failed");


        boolean submitted2 = ratingService.submitSurvey(surveyId, userId2, selectedOption2);
        if(submitted2)
            System.out.println("Survey submitted successfully. user:"+userId2);
        else
            System.out.println("Submission failed");


        float avgSurveyRating = ratingService.calculateSurveyRating(surveyId);
        System.out.println("Average survey Rating: "+avgSurveyRating +" of survey "+surveyId);


        float overallRating = ratingService.getOverallRating();
        System.out.println("Overall Survey Rating: "+overallRating);


    }
}
/*
* Using this rating service administrator should be able to create a new survey.
* Inside this survey, the admin should be able to add, update and delete questions.
* A question can have multiple options, where options have weights assigned.
* The average of answers’ weight is the rating of the survey, an average of all ratings is the overall survey rating.
* A survey can be shared with registered users.
* Below functionality should be available Admin :
* 1. Create a survey
* 2. Create questions and options for surveys
* 3. Define the weightage for each answer
* 4. Survey response should be rated, overall rating should be calculated, and shown to admin
* 5. Admin should be able to see the average rating of each question
*
* User
* 1. Users should be able to respond to the provided survey,
* 2. Users should not be allowed to re-submit a survey
*
* */