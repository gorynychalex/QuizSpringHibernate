package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.QuestionInterface;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.service.QuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.QuizServiceInterface;

import javax.enterprise.inject.Produces;
import java.util.List;

/**
 * Created by gorynych on 20.09.17.
 */

@RestController
@RequestMapping("/rest")
public class RestApiController {

    @Autowired
    private QuestionServiceInterface questionService;

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizService;

    @GetMapping("quiz/list")
    public List<QuizInterface> getQuizList(){
        return quizService.list();
    }

    @GetMapping("quiz/{id}")
    public List<QuestionInterface> getQuestionList(@PathVariable("id") int id){
        return questionService.list(id);
    }

    @RequestMapping("quiz/{id}/question/{qid}")
    public QuestionInterface getQuestionById(@PathVariable("qid") int id){
        return questionService.getById(id);
    }
}
