package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;

public class JavaQuestionServiceTest {
    private JavaQuestionServiceImpl service;


    @BeforeEach
    void setUp() {
        service = new JavaQuestionServiceImpl();
    }

    @Test
    void whenAddQuestion_thenItIsAddedToSet() {
        Question question = new Question("hello", "goodbye");
        service.add(question);
        Assertions.assertTrue(service.getAll().contains(question));
    }
    @Test
    void whenRemoveQuestion_thenItIsRemovedFromSet() {
        Question question = new Question("phone", "iphone");
        service.add(question);
        service.remove(question);
        Assertions.assertFalse(service.getAll().contains(question));
    }
    @Test
    void whenAddQuestion_thenSizeIncreasesByOne() {
        Question question = new Question("privet", "yajava");
        Collection<Question> originalQuestions = service.getAll();
        int originalSize = originalQuestions.size();
        service.add(question);
        Collection<Question> updatedQuestions = service.getAll();
        int updatedSize = updatedQuestions.size();
        Assertions.assertEquals(originalSize + 1, updatedSize);
    }

}