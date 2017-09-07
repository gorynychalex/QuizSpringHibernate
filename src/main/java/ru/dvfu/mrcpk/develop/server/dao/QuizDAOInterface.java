package ru.dvfu.mrcpk.develop.server.dao;

import ru.dvfu.mrcpk.develop.server.model.*;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;

import java.util.List;

public interface QuizDAOInterface {

    //Quiz methods

    List<QuizInterface> list();

    QuizInterface getQuizById(Number id);

    QuizInterface getQuizByIdLazy(Number id);

    void addQuiz(QuizInterface quiz);

    void editQuiz(QuizInterface quiz);

    void remove(Number id);

    //Question methods

    List<QuestionInterface> listQuestions();

    List<Question> listQuestionByQuizId(Number quizId);

    QuestionInterface getQuestionById(Number id);

    public void addQuestion(Number quizId, QuestionInterface question);

    public void updateQuestion(QuestionInterface question);

    public void removeQuestion(Number id);

    //Option methods

    List<OptionInterface> listOptions();

    public List<OptionInterface> listOptionsByQuizId(QuizInterface quiz);

    public List<OptionInterface> listOptionsByQuestionId(QuestionInterface question);

    public OptionInterface getOptionById(Number id);

    public void addOption(Number questionId, OptionInterface option);

    public void updateOption(OptionInterface option);

    public void removeOption(Number id);

    //SHOULD ReMOVE!!!
    void addStatistic(Number id, int sessionId, User user);
}
