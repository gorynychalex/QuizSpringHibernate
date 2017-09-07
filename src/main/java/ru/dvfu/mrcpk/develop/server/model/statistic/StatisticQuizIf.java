package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gorynych on 20.06.17.
 */
public interface StatisticQuizIf extends Serializable{

    int getSessionId();

    List<StatisticQuestionsInterface> getStatisticQuestionsList();

    void setStatisticQuestionsList(List<StatisticQuestionsInterface> statisticQuestions);

    QuizInterface getQuiz();

    void setQuiz(QuizInterface quiz);

    UserInterface getUser();

    void setUser(UserInterface user);

}
