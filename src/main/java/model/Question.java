package model;

import constant.ErrorMessageConstant;
import exception.EmptyOptionsValue;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private final String question;
    private final List<Options> surveyOptions;

    public Question(String question) {
        this.question = question;
        this.surveyOptions = new ArrayList<>();
    }

    public List<Options> getSurveyOptions(){
        return this.surveyOptions;
    }

    public void addOptions(String optionText, float optionWeight) throws EmptyOptionsValue {
        if(!optionText.isEmpty()){
            surveyOptions.add(new Options(optionText, optionWeight));
        }
        else{
            throw new EmptyOptionsValue(ErrorMessageConstant.EMPTY_OPTIONS_ERROR_MSG);
        }
    }

    public void addOptionsWeight(int optionIndex, float optionWeight){
        if(optionIndex >= 0 && optionIndex < surveyOptions.size()){
            surveyOptions.get(optionIndex).setWeight(optionWeight);
        }
        else{
            throw new IllegalArgumentException(ErrorMessageConstant.INVALID_OPTION_INDEX);
        }
    }
}
