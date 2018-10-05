package ru.dvfu.mrcpk.develop.server.dao.statistic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestionsInterface;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gorynych on 18.04.17.
 */
@Repository
public class StatisticQuestionsDAO implements StatisticQuestionsDAOInterface{

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addStatisticQuestion(String sessionId, StatisticUserQuizSessions suqs, StatisticQuestions statisticQuestion) {

        currentSession().save(statisticQuestion);

        StatisticUserQuizSessions currentSuqs = currentSession().get(StatisticUserQuizSessions.class, sessionId);

        currentSuqs.getStatisticQuestionsList().add(statisticQuestion);

        currentSession().save(currentSuqs);

//                suqs.getStatisticQuestionsList().add(statisticQuestion);
//
//                currentSession().save(suqs);
    }

    @Override
    public StatisticQuestionsInterface getById(int id) {
        return currentSession().get(StatisticQuestions.class,id);
    }

    @Override
    public int getIdByQuestionAndSessionId(String sessionId, int questionId) {

        StatisticUserQuizSessionsInterface statistics = currentSession().get(StatisticUserQuizSessions.class,sessionId);

//        for(StatisticQuestions statisticQuestion: statistics.getStatisticQuestionsList()){
//            if(statisticQuestion.getQuestion().getId().equals(questionId))
//                return statisticQuestion.getId();
//        }
//
//        return 0;
//
        return statistics.getStatisticQuestionsList().stream().
                filter(x -> x.getQuestion().getId().equals(questionId)).
                    mapToInt(StatisticQuestions::getId).
                        findFirst().orElse(0);
    }

    @Override
    public StatisticQuestionsInterface getStatisticQuestionById(int id) {
        return currentSession().get(StatisticQuestions.class,id);
    }

    @Override
    public StatisticUserQuizSessionsInterface getStatisticsByQuestion(Question question) {
        return null;
    }

    @Override
    public List<StatisticQuestions> getStatisticQuestionList() {
        return currentSession().createQuery("from StatisticQuestions").list();
    }

    @Override
    public List<StatisticQuestions> getStatisticQuestionListBySessionId(String sessionId) {

        return null;
    }

    @Override
    public List<Float> getResultsBySessionId(String sessionId) {

        List<Float> marks = new ArrayList<>();

        List<StatisticQuestionsInterface> statisticQuestionsList = currentSession().createQuery("from StatisticQuestions").list();

        for(StatisticQuestionsInterface squestion : statisticQuestionsList){
//            marks.add(squestion.getResult());
        }

        return marks;
    }
}
