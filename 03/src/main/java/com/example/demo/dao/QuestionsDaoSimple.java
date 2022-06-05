package com.example.demo.dao;

import com.example.demo.config.ApplicationProperty;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

@Repository
public class QuestionsDaoSimple implements QuestionsDao {

    ApplicationProperty props;
    private List<Question> container = null;
    private Iterator<Question> it = null;

    public QuestionsDaoSimple(ApplicationProperty props) {
        this.props = props;
    }

    public Question getNext() {
        if (container == null) {
            try {
                container = new CsvToBeanBuilder(new FileReader(props.getCvcPath()))
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
