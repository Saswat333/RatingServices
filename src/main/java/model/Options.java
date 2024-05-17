package model;

public class Options {
    private final String option;
    private float weight;

    public Options(String option, float weight) {
        this.option = option;
        this.weight = weight;
    }

    public String getOption() {
        return option;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
