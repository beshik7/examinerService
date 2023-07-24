package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;
    @Service("mathQuestionService")
    public class MathQuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final Random random;

    @Autowired
    public MathQuestionServiceImpl(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return questionRepository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        int num1 = random.nextInt(100) + 1;
        int num2 = random.nextInt(100) + 1;
        int mathOperation = random.nextInt(4);

        switch (mathOperation) {
            case 0:
                return new Question(num1 + "+" + num2, String.valueOf(num1 + num2));
            case 1:
                return new Question(num1 + "-" + num2, String.valueOf(num1 - num2));
            case 2:
                return new Question(num1 + "*" + num2, String.valueOf(num1 * num2));
            case 3:
                if (num2 != 0) {
                    return new Question(num1 + "/" + num2, String.valueOf(num1 / num2));
                } else {
                    return getRandomQuestion(); // avoid division by zero, get another question
                }
            default:
                throw new IllegalStateException("Unexpected value: " + mathOperation);
        }
    }
}
