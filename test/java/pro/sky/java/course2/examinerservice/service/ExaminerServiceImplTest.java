package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.NotEnoughQuestionsException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {
    @Mock
    private QuestionService javaQuestionService;
    @Mock
    private QuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }
    @Test
    void whenGetQuestions_thenCorrectAmountReturned() {
        Question javaQuestion = new Question("test1", "answer1");
        Question mathQuestion = new Question("test2", "answer2");

        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestion);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion);

        Collection<Question> questions = examinerService.getQuestions(2);

        Assertions.assertEquals(2, questions.size());
        Assertions.assertTrue(questions.contains(javaQuestion));
        Assertions.assertTrue(questions.contains(mathQuestion));
    }
    @Test
    void whenGetQuestions_thenExceptionThrown() {
        Question question = new Question("test", "answer");
        List<Question> questionList = Collections.singletonList(question);

        when(javaQuestionService.getAll()).thenReturn(questionList);
        when(mathQuestionService.getAll()).thenReturn(questionList);

        Assertions.assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(3));
    }
    @Test
    void whenGetQuestions_thenQuestionServiceIsCalled() {
        Question question1 = new Question("test1", "answer1");
        Question question2 = new Question("test2", "answer2");

        when(javaQuestionService.getRandomQuestion()).thenReturn(question1);
        when(mathQuestionService.getRandomQuestion()).thenReturn(question2);

        examinerService.getQuestions(2);

        verify(javaQuestionService, times(1)).getRandomQuestion();
        verify(mathQuestionService, times(1)).getRandomQuestion();
    }
}