package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Service
public class JavaQuestionServiceImpl implements QuestionService {
    private Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    public Question add(Question question) {
        questions.add(question);
        return question;
    }


    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }


    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int size = questions.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (Question question : questions) {
            if (i == item)
                return question;
                i++;
            }

            return null;
        }
    }

