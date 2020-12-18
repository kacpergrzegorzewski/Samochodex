package edu.bada.samochodex.model;

public class Hidden {

    private final Integer hiddenId;
    private final String hiddenSentence;

    public Hidden(Integer hiddenId, String hiddenSentence) {
        this.hiddenId = hiddenId;
        this.hiddenSentence = hiddenSentence;
    }

    public Integer getHiddenId() {
        return hiddenId;
    }

    public String getHiddenSentence() {
        return hiddenSentence;
    }
}
