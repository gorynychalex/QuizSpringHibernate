package ru.dvfu.mrcpk.develop.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.service.OptionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.QuestionServiceInterface;
import ru.dvfu.mrcpk.develop.server.service.QuizServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Quiz, Question, Option Editor Controller
 * Created by gorynych on 30.04.17.
 */

@Controller
@RequestMapping("${pathadminquiz}")
public class QuizController {

    public static final Logger logger = Logger.getLogger(QuizController.class);

    public static final String PATHROOT = "/admin/quiz";

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizService;

    @Autowired @Qualifier("questionService")
    private QuestionServiceInterface questionService;

    @Autowired @Qualifier("optionService")
    private OptionServiceInterface optionService;

    @GetMapping(value = {"","list"})
    public String quizList(ModelMap modelMap){
        modelMap.addAttribute("quizlist", quizService.list());
        return "quizlist";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String quizAddGet(ModelMap modelMap){
        modelMap.addAttribute("quizattr",new Quiz());
        return "quizadd";
    }

    @RequestMapping(value = "add1", method = RequestMethod.POST)
    public String quizAddPost(@ModelAttribute("quizattr") Quiz quiz,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("filesmall") String base64img,
                              @RequestParam("thumb") String base64thumb,
                              HttpServletRequest httpServletRequest) throws IllegalStateException, IOException
    {

        logger.info("Quiz_add: this is question and file upload");


        // New instance file upload controller
        FileUploadController fileUploadController = new FileUploadController();

        logger.info("Save file is empty!");

        // Save base64 file to the root project folder
        if(!file.isEmpty()) {
            if(base64img != null) {
                logger.info("Save base64 image file!");
                fileUploadController.uploadFileBase64(base64img, file.getOriginalFilename(), ".", httpServletRequest);
            } else {
                logger.info("base64 image file is empty! Save original file!");
                fileUploadController.uploadFileHandler1(file, httpServletRequest);
            }
        }


        // Save base64 file thumb to the thumb's directory

        //stackoverflow 24218382 how to upload encoded base64 image to the server
        logger.info("File thumb length is : " + base64thumb.length());
        if(base64thumb != null){
            logger.info("Question_add Thumb file is ");
            fileUploadController.uploadFileBase64(base64thumb,file.getOriginalFilename(),"thumb",httpServletRequest);
        }


        quizService.addQuiz(quiz);
        return "redirect:" + PATHROOT + "/list";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String quizAddPost(@ModelAttribute("quizattr") Quiz quiz,
                              @RequestParam("filesmall") String base64img,
                              @RequestParam("thumb") String base64thumb,
                              HttpServletRequest httpServletRequest) throws IllegalStateException, IOException
    {
        // Object model of quiz would save
        quizService.addQuiz(quiz);

//        logger.info("Quiz_add: this is question and file upload");
//        logger.info("quiz.getPicture()= " + quiz.getPicture());
//        logger.info("quiz.getId() = " + quiz.getId());

        // New instance file upload controller
        FileUploadController fileUploadController = new FileUploadController();

//        logger.info("File thumb length is : " + base64thumb.length());

        // Save base64 file to the root project folder

        if(base64img != null) {
            logger.info("Save base64 image file!");
            fileUploadController.uploadFileBase64(base64img, quiz.getPicture(), "quiz/"+quiz.getId(), httpServletRequest);
        }


        // Save base64 file thumb to the thumb's directory

        //stackoverflow 24218382 how to upload encoded base64 image to the server
        logger.info("File thumb length is : " + base64thumb.length());
        if(base64thumb != null){
            logger.info("Question_add Thumb file is ");
            fileUploadController.uploadFileBase64(base64thumb,quiz.getPicture(),
                    "thumb/"+"quiz/"+quiz.getId(),
                    httpServletRequest);
        }

        return "redirect:" + PATHROOT + "/list";
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.GET)
    public String quizRemoveById(@PathVariable("id") int quizId){
        quizService.removeById(quizId);
        return "redirect:/quiz/list";
    }

    @RequestMapping(value = {"{id}/question/list","{id}/questions"})
    public String getQuizById(@PathVariable("id") int id,
                              ModelMap modelMap){
        modelMap.addAttribute("quiz", quizService.getById(id));
        return "quizquestlist";
    }

    @RequestMapping("{quizid}/question/add")
    public String questionAddGet(@PathVariable("quizid") int quizId,
                                 ModelMap modelMap){
        modelMap.addAttribute("questionattr",new Question());
        modelMap.addAttribute("quiz", quizService.getById(quizId));
        return "questionadd";
    }

    @RequestMapping(value = "{id}/question/add", method = RequestMethod.POST)
    public String questionAddPost(@PathVariable("id") int id,
                                  @RequestParam("quizId") int quizId,
                                  @ModelAttribute("questionattr") Question question,
                                  @RequestParam("filesmall") String base64img,
                                  @RequestParam("thumb") String base64thumb,
                                  HttpServletRequest httpServletRequest) throws IllegalStateException, IOException{

        // Save question

        questionService.add(quizId, question);

        logger.info("Question_add: this is question and file upload");

        logger.info("Question_add: qestion getId()" + question.getId());

        // New instance file upload controller
        FileUploadController fileUploadController = new FileUploadController();

        // Save base64 file to the root project folder

            if(base64img != null)
                fileUploadController.uploadFileBase64(base64img,question.getPicture(),"quiz/" + quizId + "/" + "questions/" + question.getId(),httpServletRequest);


        // Save base64 file thumb to the thumb's directory

        //stackoverflow 24218382 how to upload encoded base64 image to the server
        logger.info("File thumb length is : " + base64thumb.length());
        if(base64thumb != null){
            logger.info("Question_add Thumb file is ");
            fileUploadController.uploadFileBase64(base64thumb,question.getPicture(),"thumb/" + "quiz/" + quizId + "/" + "questions/" + question.getId(),httpServletRequest);
        }


        return "redirect:" + PATHROOT + "/" + id + "/question/list";
    }

    @RequestMapping(value = "{id}/question/add1", method = RequestMethod.POST)
    public String questionAddPost(@PathVariable("id") int id,
                                  @RequestParam("quizId") int quizId,
                                  @ModelAttribute("questionattr") Question question,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam("filesmall") String base64img,
                                  @RequestParam("thumb") String base64thumb,
                                  HttpServletRequest httpServletRequest) throws IllegalStateException, IOException{

        logger.info("Question_add: this is question and file upload");

        logger.info("Question_add: qestion getId()" + question.getId());

        // New instance file upload controller
        FileUploadController fileUploadController = new FileUploadController();

        // Save base64 file to the root project folder
        if(!file.isEmpty()) {
            if(base64img != null)
                fileUploadController.uploadFileBase64(base64img,file.getOriginalFilename(),".",httpServletRequest);
            else
                fileUploadController.uploadFileHandler1(file, httpServletRequest);
        }


        // Save base64 file thumb to the thumb's directory

        //stackoverflow 24218382 how to upload encoded base64 image to the server
        logger.info("File thumb length is : " + base64thumb.length());
        if(base64thumb != null){
            logger.info("Question_add Thumb file is ");
            fileUploadController.uploadFileBase64(base64thumb,file.getOriginalFilename(),"thumb",httpServletRequest);
        }

        // Save question

        questionService.add(quizId, question);

        return "redirect:" + PATHROOT + "/" + id + "/question/list";
    }

    @RequestMapping("{quizid}/question/{questionId}/edit")
    public String questionEditGet(@PathVariable("quizid") int quizId,
                                  @PathVariable("questionId") int questionId,
                                  ModelMap modelMap){
        modelMap.addAttribute("quiz", quizService.getById(quizId));
        modelMap.addAttribute("questionattr",questionService.getById(questionId));
        return "questionadd";
    }

    @RequestMapping(value = "{quizId}/question/edit", method = RequestMethod.POST)
    public String questionEditPost(@PathVariable("quizId") int quizId,
                                   @ModelAttribute("questionattr") Question question){
        questionService.update(question);
        return "redirect:" + PATHROOT + "/" + quizId;
    }


    @RequestMapping(value = "{quizId}/question/{questionid}/delete", method = RequestMethod.GET)
    public String questionRemoveById(@PathVariable("quizId") int quizId,
                                     @PathVariable("questionid") int questionId){
        questionService.remove(questionId);
        return "redirect:" + PATHROOT + "/" + quizId + "/question/list";
    }

    @RequestMapping("{quizId}/question/{questionId}/option/list")
    public String editQuizByQuestion(
            @PathVariable("quizId") int quizid,
            @PathVariable("questionId") int questionid,
            ModelMap modelMap)
    {

        //Quiz id, name
        modelMap.addAttribute("quiz", quizService.getByIdLazy(quizid));

        modelMap.addAttribute("question", questionService.getById(questionid));

        return "questoptlist";
    }


    @RequestMapping("{quizId}/question/{questionId}/option/add")
    public String questionOptionAddGet(
            @PathVariable("quizId") int quizId,
            @PathVariable("questionId") int questionId,
            ModelMap modelMap){
        modelMap.addAttribute("optionattr",new Option());
        modelMap.addAttribute("quiz", quizService.getById(quizId));
        modelMap.addAttribute("question", questionService.getById(questionId));
        return "questoptadd";
    }

    @RequestMapping(value = "{quizId}/question/{questionId}/option/add", method = RequestMethod.POST)
    public String optionAddPost(@PathVariable("quizId") int quizId,
                                @PathVariable("questionId") int questionId,
                                @RequestParam("filesmall") String base64img,
                                @RequestParam("thumb") String base64thumb,
                                @ModelAttribute("optionattr") Option option, HttpServletRequest httpServletRequest) throws IOException
    {

        optionService.add(questionId, option);

        // New instance file upload controller
        FileUploadController fileUploadController = new FileUploadController();

        // Save base64 file to the root project folder
        if(base64img != null)
            fileUploadController.uploadFileBase64(base64img,option.getPicture(),"quiz/" + quizId + "/" + "questions/" + questionId + "/options/" + option.getId(),httpServletRequest);

        // Save base64 file thumb to the thumb's directory

        //stackoverflow 24218382 how to upload encoded base64 image to the server
        logger.info("File thumb length is : " + base64thumb.length());
        if(base64thumb != null){
            logger.info("Question_add Thumb file is ");
            fileUploadController.uploadFileBase64(base64thumb,option.getPicture(),"thumb/" + "quiz/" + quizId + "/" + "questions/" + questionId + "/options/" + option.getId(),httpServletRequest);
        }

        return "redirect:" + PATHROOT + "/" + quizId + "/question/" + questionId + "/option/list";
    }

    @RequestMapping("{quizId}/question/{questionId}/option/{optionId}/edit")
    public String questionOptionEditGet(
            @PathVariable("optionId") int optionId,
            @PathVariable("quizId") int quizId,
            @PathVariable("questionId") int questionId,
            ModelMap modelMap){
        modelMap.addAttribute("optionattr",optionService.getById(optionId));
        modelMap.addAttribute("quiz", quizService.getById(quizId));
        modelMap.addAttribute("question", questionService.getById(questionId));
        return "questoptadd";
    }

    @RequestMapping(value = "{quizId}/question/{questionId}/option/edit", method = RequestMethod.POST)
    public String optionEditPost(@PathVariable("quizId") int quizId,
                                 @PathVariable("questionId") int questionId,
                                 @ModelAttribute("optionattr") Option option){
        optionService.update(option);
        return "redirect:" + PATHROOT + "/" + quizId + "/question/" + questionId + "/option/list";
    }


    @RequestMapping(value = "{quizId}/question/{questionId}/option/{optionId}/delete", method = RequestMethod.GET)
    public String optionRemoveById(@PathVariable("quizId") int quizId,
                                   @PathVariable("questionId") int questionId,
                                   @PathVariable("optionId") int optionId){
        optionService.remove(optionId);
        return "redirect:" + PATHROOT + "/" + quizId + "/question/" + questionId + "/option/list";
    }

}
