package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.NotEnoughQuestionsException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random;
    @Autowired
    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }
    @Override
    public Collection<Question> getQuestions(int amount) {
        int javaQuestionsCount = random.nextInt(amount);
        int mathQuestionsCount = amount - javaQuestionsCount;
        Set<Question> questions = new HashSet<>();

        while (questions.size() < javaQuestionsCount) {
            questions.add(javaQuestionService.getRandomQuestion());
        }

        while (questions.size() < mathQuestionsCount) {
            questions.add(mathQuestionService.getRandomQuestion());
        }

        return questions;

    }


}
