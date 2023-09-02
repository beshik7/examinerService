package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceImplTest {
    @Mock
    private QuestionRepository questionRepository;
    private MathQuestionServiceImpl mathQuestionService;
    private Random random;

    @BeforeEach
    public void init() {
        mathQuestionService = new MathQuestionServiceImpl(questionRepository);

    }

    @Test
    public void testAddQuestion() {
        Question question = new Question("1+1", "2");

        when(questionRepository.add(eq(question))).thenReturn(question);

        Question result = mathQuestionService.add("1+1", "2");

        assertEquals(question, result);
        verify(questionRepository, times(1)).add(eq(question));
    }

    @Test
    public void testRemoveQuestion() {
        Question question = new Question("2+2", "4");

        when(questionRepository.remove(eq(question))).thenReturn(question);

        Question result = mathQuestionService.remove(question);

        assertEquals(question, result);
        verify(questionRepository, times(1)).remove(eq(question));
    }



    @ParameterizedTest
    @CsvSource({"2,2,0,2+2,4"})
    public void testGetRandomQuestions(Integer num1, Integer num2, Integer mathOperation, String expectedQuestion, String expectedAnswer) {
        when(random.nextInt(anyInt()))
                .thenReturn(num1)
                .thenReturn(num2);
        when(random.nextInt(anyInt()))
                .thenReturn(mathOperation);

        Question actual = mathQuestionService.getRandomQuestion();
        Assertions.assertEquals(expectedQuestion, actual.getQuestion());
        Assertions.assertEquals(expectedAnswer, actual.getAnswer());

    }
}
