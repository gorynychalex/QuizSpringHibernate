package ru.dvfu.mrcpk.develop.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticOptionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticQuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.statistics.StatisticUserQuizSessionServiceInterface;

/**
 * Created by gorynych on 29.04.17.
 */

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    @Qualifier("statisticUserQuizSessionService")
    private StatisticUserQuizSessionServiceInterface statisticUserQuizService;

    @Autowired @Qualifier("statisticQuestionService")
    private StatisticQuestionServiceInterface statisticQuestionService;

    @Autowired @Qualifier("statisticOptionService")
    private StatisticOptionServiceInterface statisticOptionService;

    @RequestMapping("/quiz")
    public String getStatisticUserQuiz(@RequestParam("sessionid") int sessionId, ModelMap modelMap){

        modelMap.addAttribute("sessionId", sessionId);

        modelMap.addAttribute("user",statisticUserQuizService.getUser());

        modelMap.addAttribute("userquizservice",statisticUserQuizService.getBySessionId(sessionId));

        modelMap.addAttribute("squestions",statisticUserQuizService.getStatisticQuestionsBySessionId(sessionId));

//        modelMap.addAttribute("resultByQuiz", statisticUserQuizService.getResult(sessionId));

        modelMap.addAttribute("resultByQuiz", statisticUserQuizService.getResultBySessionId(sessionId));

        modelMap.addAttribute("resultEntirely",statisticUserQuizService.getResultBySessionId(sessionId).stream().mapToDouble(i -> i.floatValue()).sum());

        return "statisticQuiz";
    }


    // Statistic by user

    @RequestMapping("/user")
    public String getStatistics(
            @RequestParam(value = "userid") int userId,
                                ModelMap modelMap){

//        modelMap.addAttribute("user",userServiceInterface.getById(userId));

        if(statisticUserQuizService.getStatisticByUser(userId).isEmpty()){
            return "redirect:/start";
        }

        modelMap.addAttribute("userstatisticlist",statisticUserQuizService.getStatisticByUser(userId));


        return "statisticsbyuser";
    }
}
