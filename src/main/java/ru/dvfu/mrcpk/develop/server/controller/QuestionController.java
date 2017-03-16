package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.dao.QuestionDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.QuizDAOInterface;
import ru.dvfu.mrcpk.develop.server.dao.UserDAO;
import ru.dvfu.mrcpk.develop.server.dao.UserDAOInterface;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.service.QuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.QuizServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.UserServiceInterface;

import java.sql.SQLException;

@Controller
public class QuestionController {


    @Autowired @Qualifier("userService")
    private UserServiceInterface userServiceInterface;

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizServiceInterface;

    @Autowired @Qualifier("questionService")
    private QuestionServiceInterface questionDAOInterface;


//    @RequestMapping("/quiz/question/{id}")
//    public String getQuestionQuiz(@PathVariable("id") int id, ModelMap model) throws SQLException {
//        model.addAttribute("question",questionDAOInterface.getById(id));
//        return "quiz";
//    }

//
//    @RequestMapping("/questions")
//    public String getQuestionTest(ModelMap modelMap) throws SQLException {
//        modelMap.addAttribute("message", "Pass from getQuestionTest(ModelMap modelMap)");
//        modelMap.addAttribute("questions",quizDAOInterface.questionList(1));
//        return "question";
//    }
//
//    @RequestMapping("question/add")
//    public String addQuestion(ModelMap modelMap){
//        modelMap.addAttribute("message", "add Question");
//        questionDAOInterface.add(null);
//        return "redirect:/questions";
//    }

    //Question request
    @RequestMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") int id, ModelMap modelMap) throws SQLException {

        modelMap.addAttribute("question", questionDAOInterface.getById(id));

        //preview question id
        if(id > 1) modelMap.addAttribute("prevQuestion",(id-1));

        //next question id
        if(id < quizServiceInterface.list().size())
            modelMap.addAttribute("nextQuestion",(id+1));

        return "question";
    }

    @RequestMapping("/quiz")
    public String getQuiz(@RequestParam("quizId") int id, ModelMap modelMap) throws SQLException {
//        modelMap.addAttribute("question", questionDAOInterface.getById(0));
//        modelMap.addAttribute("quiz", quizDAOInterface.getQuiz(id));
        modelMap.addAttribute("questionList", questionDAOInterface.list(id));
        return "quiz";
    }

    @RequestMapping("/start")
    public String listQuiz(ModelMap modelMap){
        modelMap.addAttribute("users",userServiceInterface.list());
        modelMap.addAttribute("quizs",quizServiceInterface.list());
        return "quizselect";
    }

    @RequestMapping("/user/{id}")
    public String getUserById(@PathVariable("id") int id, ModelMap modelMap){
        modelMap.addAttribute("user", userServiceInterface.getById(id));
        return "user";
    }

    @RequestMapping(value = "/quiz/add", method = RequestMethod.GET)
    public String quizAddGet(ModelMap modelMap){
        modelMap.addAttribute("quizattr",new Quiz());
        return "quizadd";
    }

    @RequestMapping(value = "/quiz/add", method = RequestMethod.POST)
    public String quizAddPost(@ModelAttribute("quizattr") Quiz quiz){
        quizServiceInterface.addQuiz(quiz);
        return "redirect:/quizs";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String quizRemoveById(@PathVariable("id") int quizId){
        quizServiceInterface.removeById(quizId);
        return "redirect:/quizs";
    }
}
