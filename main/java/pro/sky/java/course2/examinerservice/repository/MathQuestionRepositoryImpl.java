package pro.sky.java.course2.examinerservice.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.domain.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository("mathQuestionRepository")
public class MathQuestionRepositoryImpl implements QuestionRepository {
    private Set<Question> questions;

    public MathQuestionRepositoryImpl() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
    @PostConstruct
    public void init() {
        add(new Question("5 + 5", "10"));
        add(new Question("10 * 10", "100"));
    }
}
