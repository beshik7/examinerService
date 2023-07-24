package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.NotEnoughQuestionsException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        new ExaminerServiceImpl(questionService);
    }
    @Test
    void whenGetQuestions_thenCorrectAmountReturned() {
        Question question1 = new Question("tikak", "normalek");
        Question question2 = new Question("test2", "answer2");
        List<Question> questions = Arrays.asList(question1, question2);

        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(question1, question2);
        Assertions.assertEquals(2, examinerService.getQuestions(2).size());
        verify(questionService, times(2)).getRandomQuestion();
    }
    @Test
    void whenGetQuestions_thenExceptionThrown() {

        Question question = new Question("oop", "java");
        List<Question> questionList = Arrays.asList(question);

        when(questionService.getAll()).thenReturn(questionList);

        Assertions.assertThrows(NotEnoughQuestionsException.class, () -> examinerService.getQuestions(2));

    }
    @Test
    void whenGetQuestions_thenQuestionServiceIsCalled() {
        Question question = new Question("test", "answer");
        List<Question> questionList = Arrays.asList(question);

        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion()).thenReturn(question);

        examinerService.getQuestions(1);

        verify(questionService, times(1)).getRandomQuestion();
    }




}