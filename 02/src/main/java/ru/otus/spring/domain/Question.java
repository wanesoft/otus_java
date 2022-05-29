package ru.otus.spring.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Question {

    @CsvBindByPosition(position = 0)
    private final String question = null;

    @CsvBindByPosition(position = 1)
    private final String option1 = null;

    @CsvBindByPosition(position = 2)
    private final String option2 = null;

    @CsvBindByPosition(position = 3)
    private final String option3 = null;

    @CsvBindByPosition(position = 4)
    private final String option4 = null;

    @CsvBindByPosition(position = 5)
    private final String answer = null;

    public String getAnswer() {
        return this.answer;
    }

    public String toString() {
        return question + "\n1) " + option1 + " 2) " + option2 + " 3) " + option3 + " 4) " + option4;
    }
}
