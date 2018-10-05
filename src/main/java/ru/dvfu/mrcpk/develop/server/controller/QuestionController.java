package ru.dvfu.mrcpk.develop.server.controller;

/**
 * Question Controller for start Main process
 */

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;
import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.service.*;
import ru.dvfu.mrcpk.develop.server.service.statistics.StQuizService;
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
@RequestMapping("/start")
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

    @Autowired
    private StQuizService stQuizService;

    @RequestMapping("/index")
    public String listUsers(Map<String, Object> map){
        map.put("user", new User());
        map.put("userlist", userService.list());
        return "userlist";
    }

    @GetMapping("/startvue")
    public String getQuestionsVue(){ return "redirect:/templates/index.html"; }

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
    @GetMapping
    public String listQuiz(@RequestParam(value = "sessionid", defaultValue = "0") long sessionid, ModelMap modelMap){

        logger.info("isAuth: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        modelMap.addAttribute("users", userService.list());

        modelMap.addAttribute("quizs", quizService.list());

        if(sessionid != 0)
            modelMap.addAttribute("user", statisticUserQuizSessionService.getUser());

        return "quizselect";
    }

    // START QUIZ
    @RequestMapping("auth")
    public String startAuth(
            HttpSession session,
            ModelMap modelMap)
    {
        logger.info("isAuth: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        modelMap.addAttribute("user_login", name);
        modelMap.addAttribute("users", userService.list());
        modelMap.addAttribute("quizs", quizService.list());

        if(!session.getId().isEmpty())
            modelMap.addAttribute("user",
                    statisticUserQuizSessionService.getUser());

        return "quizselect";
    }

    /**
     * SHOW QUESTION and ADD STATISTICS
     * @param quizid
     * @param qnum
     * @param modelMap
     * @param session
     * @return
     */
    @RequestMapping(value = "quiz", method = RequestMethod.GET)
    public String getQuestionByNumber(
            @RequestParam("quizid") int quizid,
            @RequestParam(value = "qnum",defaultValue = "0") int qnum,
            HttpSession session,
            ModelMap modelMap)
    {
        // If QUIZ is empty - return to page Quiz Select
        if(quizService.getByIdLazy(quizid).getQnums() == 0) {
            return "redirect:/start";
        }

        if(SecurityContextHolder.getContext().getAuthentication().getName() != null){
            // Add UserId and QuizId to statistics by user, quiz, session
//            statisticUserQuizSessionService.addUserQuizSession(
//                    Integer.parseInt(session.getId()),
//                    userAuthDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()),
//                    (Quiz) quizService.getByIdLazy(quizid));

//            //User by id
//            modelMap.addAttribute("user", userService.getById(userid));
//            logger.info("QuestionController.class: getQuestionId: userService.getById(userid): " + userService.getById(userid));

            if(!stQuizService.isExistSession(session.getId())) {
                stQuizService.addQuiz(session.getId(),
                        SecurityContextHolder.getContext().getAuthentication().getName(),
                        (Quiz) quizService.getByIdLazy(quizid));
            }
        }

//        else {
//            //User by id
//            modelMap.addAttribute("user", statisticUserQuizSessionService.getUser());
//            logger.info("QuestionController.class: getQuestionId: statisticUserQuizSessionService.getUser(): " + statisticUserQuizSessionService.getUser());
//        }


        // Pass attribute to question form
        modelMap.addAttribute("sessionid1",session.getId());

        //Number of questions
        modelMap.addAttribute("qnums", quizService.getByIdLazy(quizid).getQnums());

        //User by id
        modelMap.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        logger.info("QuestionController.class: getQuestionId: userService.getById(userid): " + userService.getById(userid));

        //Quiz id, name
        modelMap.addAttribute("quiz", quizService.getByIdLazy(quizid));

        //Id of question
        modelMap.addAttribute("qnum",qnum);

        //QUESTION
        modelMap.addAttribute("question", quizService.getById(quizid).getQuestions().get(qnum));

        // Statistic QUESTION
//        statisticQuestionService.addStatisticQuestion(Integer.parseInt(session.getId()), (StatisticUserQuizSessions) statisticUserQuizSessionService.getBySessionId(sessionId), new StatisticQuestions(quizService.getById(quizid).getQuestions().get(qnum)));

        return "question";
    }

    // POST REQUEST FROM question.jsp
    @RequestMapping(value = "/quiz",method = RequestMethod.POST)
    public String getQuiz(HttpServletRequest request){

        // Quiz Id
        int quizid = Integer.parseInt(request.getParameter("quizid"));

        // Order Question number
        int qnum = Integer.parseInt(request.getParameter("qnum"));

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        // Question id
        int questionid = (int) quizService.getById(quizid).getQuestions().get(qnum).getId();

        String option = null;
        int opt = 0;
        if((option = request.getParameter("option")) != null)
            opt = Integer.parseInt(option);

        //Current question mark
//        List<Integer> useranswers = new ArrayList<Integer>();
//        useranswers.add(opt);
//        mark = questionService.getResult(quizService.getById(quizid).getQuestions().get(qnum).getId(), useranswers);


        stQuizService.addQuestion(quizid, (Question) questionService.getById(questionid));

        // IF press the button 'PREV'
        if(request.getParameter("button").equals("prevQuestion")){
            qnum--;
//            questionid = quizService.getById(quizid).getQuestions().get(qnum).getId();
//            userAnswerService.removeAnswerByQuestionId(questionid,sessionid);
        }

        // IF press the button 'NEXT'
        if(request.getParameter("button").equals("nextQuestion")) {
//            if(userAnswerService.getByQuestionAndSession(questionid, request.getSession().getId()).size() != 0){
//                userAnswerService.updateByQuestionAndSession(questionid,opt,
//                        SecurityContextHolder.getContext().getAuthentication().getName(), request.getSession().getId());
//            } else {
//                userAnswerService.setAnswer(new UserAnswerOptions(request.getSession().getId(),
//                        SecurityContextHolder.getContext().getAuthentication().getName(),
//                        questionid.intValue(),
//                        opt));
//                statisticOptionService.addStatisticOption(
//                        statisticQuestionService.getIdByQuestionAndSessionId(request.getSession().getId(),
//                                questionid),
//                        new StatisticOptions((Option) optionService.getById(opt)));
//            }
            qnum++;
        }

        // IF press the button 'FINISH'
        if(request.getParameter("button").equals("finish")) {

//            userAnswerService.setAnswer(new UserAnswerOptions(sessionid, userid, questionid.intValue(), opt));
//
//            statisticOptionService.addStatisticOption(
//                    statisticQuestionService.getIdByQuestionAndSessionId(sessionid,questionid.intValue()),new StatisticOptions((Option) optionService.getById(opt.intValue())));

            return "redirect:/statistic/quiz?sessionid=" + request.getRequestedSessionId() + "&userid=" + user + "&quizid=" + quizid;
        }

        // REDIRECT to quiz
        return "redirect:/start/quiz?" + "quizid=" + quizid + "&qnum=" + qnum + "&opt=" + opt;
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
            HttpSession session,
            ModelMap modelMap)
    {
        modelMap.addAttribute("quizId",quizid);
        modelMap.addAttribute("userId",userid);


        List<Float> marks = statisticQuestionService.getResultsBySessionId(session.getId());
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
