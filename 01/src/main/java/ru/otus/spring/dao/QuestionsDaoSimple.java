package ru.otus.spring.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.spring.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

public class QuestionsDaoSimple implements QuestionsDao {

    private final String fileName;
    private List<Question> container = null;
    private Iterator<Question> it = null;

    public QuestionsDaoSimple(String fileName) {
        this.fileName = fileName;
    }

    public Question getNext() {
        if (container == null) {
            try {
                container = new CsvToBeanBuilder(new FileReader(fileName))
                        .withType(Question.class)
                        .build()
                        .parse();
                it = container.iterator();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        if (it.hasNext()) {
            return it.next();
        }

        return null;
    }
}
