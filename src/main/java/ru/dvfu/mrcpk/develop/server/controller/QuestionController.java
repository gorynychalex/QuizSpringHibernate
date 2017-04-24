package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.service.*;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticOptionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticQuestionService;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticQuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticUserQuizSessionServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @Autowired @Qualifier("statisticUserQuizSessionService")
    private StatisticUserQuizSessionServiceInterface statisticUserQuizSessionServiceInterface;

    @Autowired @Qualifier("statisticQuestionService")
    private StatisticQuestionServiceInterface statisticQuestionService;

    @Autowired @Qualifier("statisticOptionService")
    private StatisticOptionServiceInterface statisticOptionService;

    @RequestMapping("/index")
    public String listUsers(Map<String, Object> map){
        map.put("user", new User());
        map.put("userlist", userServiceInterface.list());
        return "userlist";
    }

    @RequestMapping("/userlist")
    public String getUserList(ModelMap modelMap){
        modelMap.addAttribute("userlist", userServiceInterface.list());
        return "userlist";
    }

    @RequestMapping(value = "/user/{id}")
    public String getUserById(@PathVariable("id") int userId, ModelMap modelMap){
        modelMap.addAttribute("userattr", userServiceInterface.getById(userId));
        return "user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String userAddGet(ModelMap modelMap){
        modelMap.addAttribute("userattr",new User());
        return "useradd";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String userAddPost(@ModelAttribute("userattr") User user){
        userServiceInterface.add(user);
        return "redirect:/userlist";
    }

    @RequestMapping(value = "/user/edit/{id}")
    public String userEditGet(@PathVariable("id") int userId, ModelMap modelMap){
        modelMap.addAttribute("userattr", userServiceInterface.getById(userId));
        return "useradd";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String userEditPost(@ModelAttribute("userattr") User user){
        userServiceInterface.update(user);
        return "redirect:/userlist";
    }


    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String userRemoveById(@PathVariable("id") int userId){
        userServiceInterface.remove(userId);
        return "redirect:/userlist";
    }


    //Question request
    @RequestMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") int id, ModelMap modelMap) throws SQLException {

        modelMap.addAttribute("question", questionService.getById(id));

        return "question";
    }

    // START QUIZ
    @RequestMapping("/start")
    public String listQuiz(ModelMap modelMap){
        modelMap.addAttribute("users",userServiceInterface.list());
        modelMap.addAttribute("quizs",quizServiceInterface.list());
        return "quizselect";
    }

    //SHOW QUESTION
    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String getQuestionIdByQuiz(
            @RequestParam(value = "userid",defaultValue = "0") int userid,
            @RequestParam(value = "sessionid",defaultValue = "0") int sessionId,
            @RequestParam("quizid") int quizid,
            @RequestParam(value = "qnum",defaultValue = "0") int qnum,
            ModelMap modelMap)
    {

        // Verify that sessionId is not 0. If =0 that assign Random value.
        if(sessionId == 0) {
            sessionId = Math.abs(new Random().nextInt());

            // Add UserId and QuizId to statistics by user, quiz, session
            statisticUserQuizSessionServiceInterface.addUserQuizSession(
                    sessionId,
                    userServiceInterface.getById(userid),
                    (Quiz) quizServiceInterface.getByIdLazy(quizid));
        }


        // Pass attribute to question form
        modelMap.addAttribute("sessionid",sessionId);

        //User by id
        modelMap.addAttribute("user",userServiceInterface.getById(userid));

        //Number of questions
        modelMap.addAttribute("qnums",quizServiceInterface.getByIdLazy(quizid).getQnums());

        //Quiz id, name
        modelMap.addAttribute("quiz", quizServiceInterface.getByIdLazy(quizid));

        //Id of question
        modelMap.addAttribute("qnum",qnum);

        //QUESTION
        modelMap.addAttribute("question",quizServiceInterface.getById(quizid).getQuestions().get(qnum));

        // Statistic QUESTION
        statisticQuestionService.addStatisticQuestion(sessionId, (StatisticUserQuizSessions) statisticUserQuizSessionServiceInterface.getBySessionId(sessionId),new StatisticQuestions(quizServiceInterface.getById(quizid).getQuestions().get(qnum)));

        return "question";
    }

    // POST REQUEST FROM question.jsp
    @RequestMapping(value = "/quiz",method = RequestMethod.POST)
    public String getQuiz(HttpServletRequest request){

        //Cast to Integer RequestParams
        int userid = Integer.parseInt(request.getParameter("userid"));
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));
        int qnum = Integer.parseInt(request.getParameter("qnum"));

        // Question id
        Number questionid = quizServiceInterface.getById(quizid).getQuestions().get(qnum).getId();


        String option = null;
        Integer opt = 0;
        if((option = request.getParameter("option")) != null)
            opt = Integer.parseInt(option);

        //Current question mark
//        List<Integer> useranswers = new ArrayList<Integer>();
//        useranswers.add(opt);
//        mark = questionService.getResult(quizServiceInterface.getById(quizid).getQuestions().get(qnum).getId(), useranswers);

        // IF press the button 'PREV'
        if(request.getParameter("button").equals("prevQuestion")){
            qnum--;
//            questionid = quizServiceInterface.getById(quizid).getQuestions().get(qnum).getId();
//            userAnswerService.removeAnswerByQuestionId(questionid,sessionid);
        }

        // IF press the button 'NEXT'
        if(request.getParameter("button").equals("nextQuestion")) {
            if(userAnswerService.getByQuestionAndSession(questionid, sessionid).size() != 0){
                userAnswerService.updateByQuestionAndSession(questionid,opt,userid,sessionid);
            } else {
                userAnswerService.setAnswer(new UserAnswerOptions(sessionid, userid, questionid.intValue(), opt));
                statisticOptionService.addStatisticOption(
                        statisticQuestionService.getIdByQuestionAndSessionId(sessionid,questionid.intValue()),new StatisticOptions((Option) optionService.getById(opt.intValue())));
            }
            qnum++;
        }

        // IF press the button 'FINISH'
        if(request.getParameter("button").equals("finish")) {

            userAnswerService.setAnswer(new UserAnswerOptions(sessionid, userid, questionid.intValue(), opt));

            statisticOptionService.addStatisticOption(
                    statisticQuestionService.getIdByQuestionAndSessionId(sessionid,questionid.intValue()),new StatisticOptions((Option) optionService.getById(opt.intValue())));

            return "redirect:/quizresults?sessionid=" + sessionid + "&userid=" + userid + "&quizid=" + quizid;
        }

        // REDIRECT to quiz
        return "redirect:/quiz?sessionid=" + sessionid + "&userid=" + userid + "&quizid=" + quizid + "&qnum=" + qnum + "&opt=" + opt;
    }


    // Show QUESTION by id from QUIZ by id
    @RequestMapping("/questoptlist")
    public String editQuizByQuestion(
            @RequestParam("quizid") int quizid,
            @RequestParam("questionid") int questionid,
            ModelMap modelMap)
    {

        //Quiz id, name
        modelMap.addAttribute("quiz", quizServiceInterface.getByIdLazy(quizid));

        modelMap.addAttribute("question", questionService.getById(questionid));

        return "questoptlist";
    }

    // RESULTS
    @RequestMapping("/quizresults")
    public String getResult(
            @RequestParam(value = "userid") int userid,
            @RequestParam(value = "sessionid") int sessionId,
            @RequestParam("quizid") int quizid,
            ModelMap modelMap)
    {
        modelMap.addAttribute("quizId",quizid);
        modelMap.addAttribute("userId",userid);

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

    @RequestMapping("/userstatistics")
    public String getStatistics(@RequestParam(value = "userid") int userId,ModelMap modelMap){

        modelMap.addAttribute("user",userServiceInterface.getById(userId));

        modelMap.addAttribute("userstatisticlist",statisticUserQuizSessionServiceInterface.getStatisticByUser(userId));

        return "userstatistics";
    }

    @RequestMapping("/quizlist")
    public String quizList(ModelMap modelMap){
        modelMap.addAttribute("quizlist",quizServiceInterface.list());
        return "quizlist";
    }

    @RequestMapping(value = "/quiz/add", method = RequestMethod.GET)
    public String quizAddGet(ModelMap modelMap){
        modelMap.addAttribute("quizattr",new Quiz());
        return "quizadd";
    }

    @RequestMapping(value = "/quiz/add", method = RequestMethod.POST)
    public String quizAddPost(@ModelAttribute("quizattr") Quiz quiz){
        quizServiceInterface.addQuiz(quiz);
        return "redirect:/quizlist";
    }

    @RequestMapping(value = "/quiz/questions/{id}")
    public String getQuizById(@PathVariable("id") int id, ModelMap modelMap){
        modelMap.addAttribute("quiz",quizServiceInterface.getById(id));
        return "quizquestlist";
    }

    @RequestMapping(value = "/quiz/delete/{id}", method = RequestMethod.GET)
    public String quizRemoveById(@PathVariable("id") int quizId){
        quizServiceInterface.removeById(quizId);
        return "redirect:/quizlist";
    }

    @RequestMapping("/quiz/{quizid}/question/add")
    public String questionAddGet(@PathVariable("quizid") int quizId, ModelMap modelMap){
        modelMap.addAttribute("questionattr",new Question());
        modelMap.addAttribute("quiz", quizServiceInterface.getById(quizId));
        return "questionadd";
    }

    @RequestMapping(value = "/quiz/question/add", method = RequestMethod.POST)
    public String questionAddPost(@RequestParam("quizId") int quizId, @ModelAttribute("questionattr") Question question){

        questionService.add(quizId, question);

        return "redirect:/quiz/questions/"+quizId;
    }

    @RequestMapping("/quiz/{quizid}/question/{questionId}/edit")
    public String questionEditGet(@PathVariable("quizid") int quizId, @PathVariable("questionId") int questionId, ModelMap modelMap){
        modelMap.addAttribute("quiz", quizServiceInterface.getById(quizId));
        modelMap.addAttribute("questionattr",questionService.getById(questionId));
        return "questionadd";
    }

    @RequestMapping(value = "/quiz/{quizId}/question/edit", method = RequestMethod.POST)
    public String questionEditPost(@PathVariable("quizId") int quizId, @ModelAttribute("questionattr") Question question){
        questionService.update(question);
        return "redirect:/quiz/questions/" + quizId;
    }


    @RequestMapping(value = "/quiz/{quizId}/question/delete/{questionid}", method = RequestMethod.GET)
    public String questionRemoveById(@PathVariable("quizId") int quizId, @PathVariable("questionid") int questionId){
        questionService.remove(questionId);
        return "redirect:/quiz/questions/"+quizId;
    }

    @RequestMapping("/quiz/{quizId}/question/{questionId}/option/add")
    public String questionOptionAddGet(
            @PathVariable("quizId") int quizId,
            @PathVariable("questionId") int questionId,
            ModelMap modelMap){
        modelMap.addAttribute("optionattr",new Option());
        modelMap.addAttribute("quiz", quizServiceInterface.getById(quizId));
        modelMap.addAttribute("question", questionService.getById(questionId));
        return "questoptadd";
    }

    @RequestMapping(value = "/quiz/{quizId}/question/{questionId}/option/add", method = RequestMethod.POST)
    public String optionAddPost(@PathVariable("quizId") int quizId, @PathVariable("questionId") int questionId, @ModelAttribute("optionattr") Option option){

        optionService.add(questionId, option);

        return "redirect:/questoptlist?quizid=" + quizId + "&questionid=" + questionId;
    }

    @RequestMapping("/quiz/{quizId}/question/{questionId}/option/{optionId}/edit")
    public String questionOptionEditGet(
            @PathVariable("optionId") int optionId,
            @PathVariable("quizId") int quizId,
            @PathVariable("questionId") int questionId,
            ModelMap modelMap){
        modelMap.addAttribute("optionattr",optionService.getById(optionId));
        modelMap.addAttribute("quiz", quizServiceInterface.getById(quizId));
        modelMap.addAttribute("question", questionService.getById(questionId));
        return "questoptadd";
    }

    @RequestMapping(value = "/quiz/{quizId}/question/{questionId}/option/edit", method = RequestMethod.POST)
    public String optionEditPost(@PathVariable("quizId") int quizId, @PathVariable("questionId") int questionId, @ModelAttribute("optionattr") Option option){

        optionService.update(option);

        return "redirect:/questoptlist?quizid=" + quizId + "&questionid=" + questionId;
    }


    @RequestMapping(value = "/quiz/{quizId}/question/{questionId}/option/{optionId}/delete", method = RequestMethod.GET)
    public String optionRemoveById(@PathVariable("quizId") int quizId, @PathVariable("questionId") int questionId, @PathVariable("optionId") int optionId){
        optionService.remove(optionId);
        return "redirect:/questoptlist?quizid=" + quizId + "&questionid=" + questionId;
    }

}
