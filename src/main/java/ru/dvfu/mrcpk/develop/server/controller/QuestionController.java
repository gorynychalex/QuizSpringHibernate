package ru.dvfu.mrcpk.develop.server.controller;

/**
 * Question Controller for start Main process
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.service.*;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticOptionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticQuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticUserQuizSessionServiceInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class QuestionController {


    protected static final Logger logger = Logger.getLogger(QuestionController.class);

    @Autowired @Qualifier("userService")
    private UserServiceInterface userService;

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizService;

    @Autowired @Qualifier("questionService")
    private QuestionServiceInterface questionService;

    @Autowired @Qualifier("optionService")
    private OptionServiceInterface optionService;

    @Autowired @Qualifier("userAnswerService")
    private UserAnswerServiceInterface userAnswerService;

    @Autowired @Qualifier("statisticUserQuizSessionService")
    private StatisticUserQuizSessionServiceInterface statisticUserQuizSessionService;

    @Autowired @Qualifier("statisticQuestionService")
    private StatisticQuestionServiceInterface statisticQuestionService;

    @Autowired @Qualifier("statisticOptionService")
    private StatisticOptionServiceInterface statisticOptionService;

    @RequestMapping("/index")
    public String listUsers(Map<String, Object> map){
        map.put("user", new User());
        map.put("userlist", userService.list());
        return "userlist";
    }



    //Question request
    @RequestMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") int id, ModelMap modelMap) throws SQLException {

        modelMap.addAttribute("question", questionService.getById(id));

        return "question";
    }

    @RequestMapping(value = "/loginds", method = RequestMethod.GET)
    public String UserSession(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        modelMap.addAttribute("username", name);
        return "hellos " + name;
    }

    // START QUIZ
    @RequestMapping("/start")
    public String listQuiz(@RequestParam(value = "sessionid", defaultValue = "0") long sessionid, ModelMap modelMap){
        modelMap.addAttribute("users", userService.list());
        modelMap.addAttribute("quizs", quizService.list());

        if(sessionid != 0)
            modelMap.addAttribute("user", statisticUserQuizSessionService.getUser());

        return "quizselect";
    }

    /**
     * SHOW QUESTION and ADD STATISTICS
     * @param userid
     * @param sessionId
     * @param quizid
     * @param qnum
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String getQuestionIdByQuiz(
            @RequestParam(value = "userid",defaultValue = "0") int userid,
            @RequestParam(value = "sessionid",defaultValue = "0") int sessionId,
            @RequestParam("quizid") int quizid,
            @RequestParam(value = "qnum",defaultValue = "0") int qnum,
            ModelMap modelMap)
    {
        // If QUIZ is empty - return to page Quiz Select
        if(quizService.getByIdLazy(quizid).getQnums() == 0) {
            return "redirect:/start";
        }



        // Verify that sessionId is not 0. If =0 that assign Random value.
        if(sessionId == 0) {
            sessionId = Math.abs(new Random().nextInt());

            // Add UserId and QuizId to statistics by user, quiz, session
            statisticUserQuizSessionService.addUserQuizSession(
                    sessionId,
                    userService.getById(userid),
                    (Quiz) quizService.getByIdLazy(quizid));


//            //User by id
//            modelMap.addAttribute("user", userService.getById(userid));
//            logger.info("QuestionController.class: getQuestionId: userService.getById(userid): " + userService.getById(userid));

        }

//        else {
//            //User by id
//            modelMap.addAttribute("user", statisticUserQuizSessionService.getUser());
//            logger.info("QuestionController.class: getQuestionId: statisticUserQuizSessionService.getUser(): " + statisticUserQuizSessionService.getUser());
//        }


        // Pass attribute to question form
        modelMap.addAttribute("sessionid",sessionId);


        //Number of questions
        modelMap.addAttribute("qnums", quizService.getByIdLazy(quizid).getQnums());

        //User by id
        modelMap.addAttribute("user", userService.getById(userid));
        logger.info("QuestionController.class: getQuestionId: userid = " + userid);
        logger.info("QuestionController.class: getQuestionId: userService.getById(userid): " + userService.getById(userid));

        //Quiz id, name
        modelMap.addAttribute("quiz", quizService.getByIdLazy(quizid));

        //Id of question
        modelMap.addAttribute("qnum",qnum);
        //QUESTION
        modelMap.addAttribute("question", quizService.getById(quizid).getQuestions().get(qnum));

        // Statistic QUESTION
        statisticQuestionService.addStatisticQuestion(sessionId, (StatisticUserQuizSessions) statisticUserQuizSessionService.getBySessionId(sessionId), new StatisticQuestions(quizService.getById(quizid).getQuestions().get(qnum)));

        return "question";
    }

    // POST REQUEST FROM question.jsp
    @RequestMapping(value = "/quiz",method = RequestMethod.POST)
    public String getQuiz(HttpServletRequest request){

        logger.info("sessionId: " + request.getRequestedSessionId());

        logger.info("session.getId(): " + request.getSession().getId());

        logger.info("userid: " + request.getParameter("userid"));

        //Cast to Integer RequestParams
        int userid = Integer.parseInt(request.getParameter("userid"));
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));
        int qnum = Integer.parseInt(request.getParameter("qnum"));

        // Question id
        Number questionid = quizService.getById(quizid).getQuestions().get(qnum).getId();


        String option = null;
        Integer opt = 0;
        if((option = request.getParameter("option")) != null)
            opt = Integer.parseInt(option);

        //Current question mark
//        List<Integer> useranswers = new ArrayList<Integer>();
//        useranswers.add(opt);
//        mark = questionService.getResult(quizService.getById(quizid).getQuestions().get(qnum).getId(), useranswers);

        // IF press the button 'PREV'
        if(request.getParameter("button").equals("prevQuestion")){
            qnum--;
//            questionid = quizService.getById(quizid).getQuestions().get(qnum).getId();
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

            return "redirect:/statistic/quiz?sessionid=" + sessionid + "&userid=" + userid + "&quizid=" + quizid;
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
        modelMap.addAttribute("quiz", quizService.getByIdLazy(quizid));

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


        List<Float> marks = statisticQuestionService.getResultsBySessionId(sessionId);
//
//        List<Float> marks = quizService.getResultByQuizId(quizid,sessionId);

        float summarks = 0;
        for(Float mark: marks) {
            if(mark != Float.NaN || mark != Double.NaN) { summarks += mark; }
        }
        float markfull = summarks/marks.size();

        modelMap.addAttribute("marks",marks);
        modelMap.addAttribute("markfull", markfull);

        return "quizresult";
    }


}
