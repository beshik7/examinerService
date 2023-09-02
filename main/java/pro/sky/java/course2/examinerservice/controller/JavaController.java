package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {
    private final QuestionService service;
    public JavaController(@Qualifier("javaQuestionService") QuestionService service) {
        this.service = service;
    }
    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }
}
