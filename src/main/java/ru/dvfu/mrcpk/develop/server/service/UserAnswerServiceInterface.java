package ru.dvfu.mrcpk.develop.server.service;

import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.dto.QuestionResult;
import ru.dvfu.mrcpk.develop.server.model.dto.QuizResultDTO;

import java.util.List;
import java.util.Map;

public interface UserAnswerServiceInterface {
    void setAnswer(UserAnswerOptions userAnswerOptions);
    void removeAnswerByQuestionId(Number questionId, String sessionId);
    List<UserAnswerOptions> getByQuestionAndSession(Number questionId, String sessionId);
    void updateByQuestionAndSession(Number questionId, Number optionid, String user, String sessionId);

    void setAnswerSimple(Number questionId, List<Integer> options);

    List<Float> getResultMarkList();

    float getResultMark();

    void setResult_list(List<Float> result_list);

    void setUseranswers(Map<Number, List<Integer>> useranswers);

    void setAnswerMap(Question question, List<Integer> options);

    void setAnswers(Map<Question, List<Integer>> answers);

    void setQuestions(List<Question> questions);

    Map<Integer, Float> getListMarks();

    QuizResultDTO getQuizResultDTO();

    void setQuizResultDTO(QuizResultDTO quizResultDTO);
    List<QuestionResult> getQuestionResults();
    void setQuestionResults(List<QuestionResult> questionResults);

    void getResultQuestion(int num);

    float getResultDTO();
}
