package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.service.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Float.NaN;

@Controller
public class QuestionController {


    @Autowired @Qualifier("userService")
    private UserServiceInterface userServiceInterface;

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizServiceInterface;

    @Autowired @Qualifier("questionService")
    private QuestionServiceInterface questionService;

    @Autowired @Qualifier("optionService")
    private OptionServiceInterface optionService;

    @Autowired @Qualifier("userAnswerService")
    private UserAnswerServiceInterface userAnswerService;


    @RequestMapping("/userlist")
    public String getUserList(ModelMap modelMap){
        modelMap.addAttribute("userlist", userServiceInterface.list());
        return "userlist";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String userAddGet(ModelMap modelMap){
        modelMap.addAttribute("userattr",new User());
        return "useradd";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String quizAddPost(@ModelAttribute("userattr") User user){
        userServiceInterface.add(user);
        return "redirect:/userlist";
    }



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

        modelMap.addAttribute("question", questionService.getById(id));

        //preview question id
        if(id > 1) modelMap.addAttribute("prevQuestion",(id-1));

        //next question id
        if(id < quizServiceInterface.list().size())
            modelMap.addAttribute("nextQuestion",(id+1));

        return "question";
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String getQuestionIdByQuiz(
            @RequestParam(value = "userid",defaultValue = "0") int userid,
            @RequestParam(value = "sessionid",defaultValue = "0") int sessionId,
            @RequestParam("quizid") int quizid,
            @RequestParam(value = "qnum",defaultValue = "0") int qnum,
            ModelMap modelMap)
    {

        if(sessionId == 0)
            sessionId = Math.abs(new Random().nextInt());
        modelMap.addAttribute("sessionid",sessionId);

        //User by id
        modelMap.addAttribute("user",userServiceInterface.getById(userid));

        //Number of questions
        int qnums = quizServiceInterface.getQuizById(quizid).getQuestions().size();
        modelMap.addAttribute("qnums",qnums);

        //Quiz id, name
        modelMap.addAttribute("quiz", quizServiceInterface.getQuizByIdLazy(quizid));

        //Id of question
        modelMap.addAttribute("qnum",qnum);

        //QUESTION
        modelMap.addAttribute("question",quizServiceInterface.getQuizById(quizid).getQuestions().get(qnum));

        return "question";
    }

    // POST REQUEST FROM question.jsp
    @RequestMapping(value = "/quiz",method = RequestMethod.POST)
    public String getQuiz(HttpServletRequest request){
        int userid = Integer.parseInt(request.getParameter("userid"));
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));

        int qnum = Integer.parseInt(request.getParameter("qnum"));

        Number questionid = quizServiceInterface.getQuizById(quizid).getQuestions().get(qnum).getId();

        String option = null;
        Integer opt = 0;
        if((option = request.getParameter("option")) != null)
            opt = Integer.parseInt(option);

        //Current question mark
//        List<Integer> useranswers = new ArrayList<Integer>();
//        useranswers.add(opt);
//        mark = questionService.getResult(quizServiceInterface.getQuizById(quizid).getQuestions().get(qnum).getId(), useranswers);

        if(request.getParameter("button").equals("prevQuestion")){
            qnum--;
            questionid = quizServiceInterface.getQuizById(quizid).getQuestions().get(qnum).getId();
            userAnswerService.removeAnswerByQuestionId(questionid,sessionid);
        }

        if(request.getParameter("button").equals("nextQuestion")) {
            userAnswerService.setAnswer(new UserAnswerOptions(sessionid, userid, questionid.intValue(), opt));
            qnum++;
        }

        if(request.getParameter("button").equals("finish")) {

            return "redirect:/quizresults?sessionid=" + sessionid + "&userid=" + userid + "&quizid=" + quizid;
        }

        return "redirect:/quiz?sessionid=" + sessionid + "&userid=" + userid + "&quizid=" + quizid + "&qnum=" + qnum + "&opt=" + opt;
    }


    // RESULTS
    @RequestMapping("/quizresults")
    public String getResult(
            @RequestParam(value = "userid") int userid,
            @RequestParam(value = "sessionid") int sessionId,
            @RequestParam("quizid") int quizid,
            ModelMap modelMap)
    {
        List<Float> marks = quizServiceInterface.getResultByQuizId(quizid,sessionId);

        float summarks = 0;
        for(Float mark: marks) {
            if(mark != Float.NaN || mark != Double.NaN) { summarks += mark; }
        }
        float markfull = summarks/marks.size();

        modelMap.addAttribute("marks",marks);
        modelMap.addAttribute("markfull", markfull);

        return "quizresult";
    }

    // START PAGE FOR TEST
    @RequestMapping("/start")
    public String listQuiz(ModelMap modelMap){
        modelMap.addAttribute("users",userServiceInterface.list());
        modelMap.addAttribute("quizs",quizServiceInterface.list());
        return "quizselect";
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
