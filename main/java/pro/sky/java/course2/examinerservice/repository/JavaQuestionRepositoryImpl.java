package pro.sky.java.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository("javaQuestionRepository")
public class JavaQuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

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
}
