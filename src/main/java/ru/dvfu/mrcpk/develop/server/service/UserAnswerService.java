package ru.dvfu.mrcpk.develop.server.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvfu.mrcpk.develop.server.dao.UserAnswerOptionsDAO;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.UserAnswerOptions;
import ru.dvfu.mrcpk.develop.server.model.dto.QuestionResult;
import ru.dvfu.mrcpk.develop.server.model.dto.QuizResultDTO;
import ru.dvfu.mrcpk.develop.server.service.statistics.StQuizService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gorynych on 18.03.17.
 */
@Log4j
@Service
public class UserAnswerService implements UserAnswerServiceInterface {

    @Autowired
    private QuestionServiceInterface questionService;

    @Autowired
    private UserAnswerOptionsDAO userAnswerOptionsDAO;

    @Autowired
    private StQuizService stQuizService;

    private List<Question> questions;

    // Map for useranswers
    private Map<Number, List<Integer>> useranswers;

    private Map<Question, List<Integer>> answers;

    Float result;
    List<Float> result_list;
    Map<Integer, Float> result_list1;

    Map<Question, Float> resultsmap;

    // ACTUAL Define DTO
    QuizResultDTO quizResultDTO;
    List<QuestionResult> questionResults;

    // MAIN RESULT RETURN !!!
    public QuizResultDTO getQuizResultDTO() {
        return quizResultDTO;
    }

    public void setQuizResultDTO(QuizResultDTO quizResultDTO) {
        this.quizResultDTO = quizResultDTO;
    }

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        // Iterate over list and
//        questions.forEach(x -> useranswers.put(x.getId(),new ArrayList<>()));
    }

    public void setUseranswers(Map<Number, List<Integer>> useranswers) {
        this.useranswers = useranswers;
    }


    public void setAnswers(Map<Question, List<Integer>> answers) {
        this.answers = answers;
    }

    public void setResult_list(List<Float> result_list) {
        this.result_list = result_list;
    }

    @Transactional
    public void setAnswer(UserAnswerOptions userAnswerOptions) {
        this.userAnswerOptionsDAO.setAnswer(userAnswerOptions);
    }

    @Transactional
    public void removeAnswerByQuestionId(Number questionId, String sessionId) {
        this.userAnswerOptionsDAO.removeAnswerByQuiestionId(questionId,sessionId);
    }

    @Transactional
    public List<UserAnswerOptions> getByQuestionAndSession(Number questionId, String sessionId) {
        return this.userAnswerOptionsDAO.getByQuestionAndSession(questionId,sessionId);
    }

    @Transactional
    public void updateByQuestionAndSession(Number questionId, Number optionid, String userId, String sessionId) {
        this.userAnswerOptionsDAO.updateByQuestionAndSession(questionId, optionid, userId, sessionId);
    }

    @Transactional
    public void setAnswerSimple(Number questionId, List<Integer> options){

        useranswers.put(questionId, options);
    }

    public void setAnswerMap(Question question, List<Integer> options){
        answers.put(question, options);
    }

    @Transactional
    public Map<Number, List<Integer>> getUseranswers() {
        return null;
    }

    // GET LIST RESULT
    @Transactional
    public List<Float> getResultMarkList(){

        result_list = new ArrayList<>();

        for(Map.Entry<Number,List<Integer>> entry: useranswers.entrySet()){
            result_list.add(this.questionService.getResult(entry.getKey(), entry.getValue()));
        }

        return result_list;
    }

    @Transactional
    public Map<Integer, Float> getListMarks(){

//        Map<Integer, Float> result_list1 = new HashMap<>();

        // Iterate by Questions. Result List is Map<QuestionID, Float>
//        questions
//                .forEach(k->
//                        result_list1.put(
//                                k.getId().intValue(),
//                                this.questionService.getResult(k.getId(),useranswers.get(k.getId()))));

        result_list1 = questions
                .stream()
                .collect(
                        Collectors.toMap(
                                x -> x.getId().intValue(),
                                x -> this.getResult(x, useranswers.get(x.getId()))));

        // Map result questions
        resultsmap = questions.stream()
                        .collect(Collectors.toMap(x->x,x->this.getResult(x,useranswers.get(x.getId()))));

        result = (float) result_list1.entrySet().stream().mapToDouble(x->x.getValue()).sum()/result_list1.size();

        return result_list1;
    }

//
//    public QuizResultDTO getQuizResultOld(){
//
//        List<QuestionResult> questionResults = questions.stream()
//                .map(x-> new QuestionResult(x.getId().intValue(),
//                        x,
//                        this.getResult(x,useranswers.get(x.getId())))).collect(Collectors.toList());
//
//
//
//
//        return new QuizResultDTO(questionResults,
//                ((float)questionResults.stream().mapToDouble(x->x.getMark()).sum()/questionResults.size()));
//    }

    public float getResultMark(){

//        float summarks = 0;
//        for(Float mark: result_list) {
//            if(!Float.isNaN(mark)) { summarks += mark; }
//        }
//        float markfull = summarks/result_list.size();
//
//        return markfull;

        return result;
    }


    public Float getResult(Question question, List<Integer> userAnswers) {

//        logger.info("getResultMark() byQuestionId = " + questionid.intValue());

        // Test empty options
        if(userAnswers == null || question.getOptions().size() == 0 || userAnswers.size() == 0) return 0.0f;

        List<Option> options = question.getOptions();

//        logger.info("getResultMark() options size: " + options.size());

        /*
            ОЦЕНКА = КВП/ОКП/(КВН + 1)
            КВП - количество выбранных правильных вариантов
            КВН - количество выбранных неверных вариантов
            ОКП - общее количество правильных вариантов в вопросе

            mark = userAnswersTrue/optionsTrue/(userAnswersFalse + 1)
         */


//        int sumOptionsTrue=0,sumAnsTrue=0,sumAnsFalse=0;

//
//        for (Option option : options) {
//
//            // Sum Options True
//            if (option.isCorrect()) sumOptionsTrue++;
//
//            // ForEach userAnswer
//            for(Integer userans: userAnswers) {
////                logger.info("i = " + option.getId() + "; userans = " + userans);
////                if (option.isCorrect() & option.getId().equals(userans)) {
//
//                // Sum Answers True
//                if (option.isCorrect() & option.getId() == userans) {
//                    sumAnsTrue++;
//                }
////                if (!option.isCorrect() & option.getId().equals(userans)) {
//
//                // Sum Answers False
//                if (!option.isCorrect() & option.getId() == userans) {
//                    sumAnsFalse++;
//                }
//            }
//        }

//        log.info("sumOptionsTrue: " + options.stream().filter(x->x.isCorrect()).count() + ", " + sumOptionsTrue);
//        log.info("sumAnsTrue: " + options.stream().filter(o->userAnswers.stream().allMatch(a->a.equals(o.getId()) && o.isCorrect())).count() + ", " + sumAnsTrue);
//        log.info("sumAnsFalse: " + options.stream().filter(o->userAnswers.stream().allMatch(a->a.equals(o.getId()) && !o.isCorrect())).count() + ", " + sumAnsFalse);


//        float mark = (float)sumAnsTrue/(float)sumOptionsTrue/((float)sumAnsFalse+1);
//
//        return mark;

        return (float)options.stream().filter(o->userAnswers.stream().allMatch(a->a.equals(o.getId()) && o.isCorrect())).count()/
                (float)options.stream().filter(x->x.isCorrect()).count()/
                (float) (options.stream().filter(o -> userAnswers.stream().allMatch(a -> a.equals(o.getId()) && !o.isCorrect())).count() + 1);
    }


    /** NOT ACTUAL!
     * Get Result Score, true items, false items
     *
     * Serial question num
     * @param num
     */
    public void getResultQuestion(int num){

        // Define all correct options
        questionResults.get(num).setOptionsTrue((int) questionResults.get(num).getQuestion().getOptions().stream()
                .filter(Option::isCorrect).count());

        // Define true answers
        questionResults.get(num).setAnsTrue(
                (int) questionResults.get(num).getQuestion().getOptions().stream()
                        .filter(o->questionResults.get(num).getOptions().stream()
                                .allMatch(x->x.equals(o.getId()) && o.isCorrect())).count()
        );

        // Define false answers
        questionResults.get(num).setAnsFalse(
                (int) questionResults.get(num).getQuestion().getOptions().stream()
                        .filter(o->questionResults.get(num).getOptions().stream()
                                .allMatch(x->x.equals(o.getId()) && !o.isCorrect())).count()
        );

        /*
            ОЦЕНКА = КВП/ОКП/(КВН + 1)
            КВП - количество выбранных правильных вариантов
            КВН - количество выбранных неверных вариантов
            ОКП - общее количество правильных вариантов в вопросе

            mark = userAnswersTrue/optionsTrue/(userAnswersFalse + 1)
         */


        // Set mark in float
        questionResults.get(num).setMark(
                        ((float)questionResults.get(num).getAnsTrue())/
                        ((float)questionResults.get(num).getOptionsTrue())/
                        ((float) questionResults.get(num).getAnsFalse()+1)
        );

        // Set Score in int
        questionResults.get(num).setScore((int) (100*questionResults.get(num).getMark()));


    }

    // Get Result at DTO
    public float getResultDTO(){

        quizResultDTO.setQuestionsresult(questionResults);

        int optTrueAll = (int) quizResultDTO.getQuiz().getQuestions().stream()
                .filter(x->x.getOptions().stream().allMatch(Option::isCorrect)).count();

        quizResultDTO.setOptionsTrue((int) quizResultDTO.getQuiz().getQuestions().stream()
                .map(x-> x.getOptions().stream().allMatch(Option::isCorrect)).count());

        quizResultDTO.setAnsTrue(questionResults.stream().mapToInt(QuestionResult::getAnsTrue).sum());

        //        int answered = (int) quizResultDTO.getQuiz().getQuestions().stream()
//                .filter(x->quizResultDTO.getQuestionsresult().stream().allMatch(o->x.equals(o.getQuestion()))).count();

        // Unanswered question equal as FALSE OPTION
        int unanswered = (int) (quizResultDTO.getQuiz().getQnums()
                            - (quizResultDTO.getQuestionsresult().size()
                                - quizResultDTO.getQuestionsresult().stream()
                                    .filter(x->x.getAnsTrue() == 0 && x.getAnsFalse() == 0).count()));

        quizResultDTO.setQuestionsUnAttempted(unanswered);

        quizResultDTO.setQuestionsAttempted(quizResultDTO.getQuiz().getQnums() - unanswered);

        quizResultDTO.setAnsFalse(questionResults.stream().mapToInt(QuestionResult::getAnsFalse).sum() + unanswered);

        float score = (float) quizResultDTO.getQuestionsresult().stream().mapToDouble(x->x.getMark()).sum()/ quizResultDTO.getQuiz().getQnums();

        quizResultDTO.setScore(score);

        quizResultDTO.setMark(Math.round(5*score));

        if(quizResultDTO.getUsr() != null)
            stQuizService.addQuizResult(quizResultDTO);

        return quizResultDTO.getMark();
    }

}