package ru.dvfu.mrcpk.develop.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.*;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.service.QuizServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.ImageObserver;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * Upload file's controller
 */

@Controller
@RequestMapping("/file")
public class FileUploadController
{

public static final Logger logger = Logger.getLogger(FileUploadController.class);

//    @Autowired
//    Environment environment;

//    @Value("${local.server.address}")
//    private String serverAddress;
//
//    @Value("${local.server.port}")
//    private String serverPort;

    public static final String DIR_PATH = "resources/images";

    @Autowired @Qualifier("quizService")
    private QuizServiceInterface quizService;

    private static final ImageObserver DUMMY_OBSERVER = ((img, infoflags, x, y, width, height) -> true);

    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileHandler1(@RequestParam("file")MultipartFile file,
                                     HttpServletRequest httpServletRequest) throws IllegalStateException, IOException{

        String uploadedfile = file.getOriginalFilename();

        logger.info("ServletPath: " + httpServletRequest.getSession().getServletContext().getRealPath("/"));

        if(!file.isEmpty()){

            try {

                byte[] bytes = file.getBytes();

//                String rootPath = System.getProperty("catalina.home");
                String rootPath = httpServletRequest.getSession().getServletContext().getRealPath("/");

                File dir = new File(rootPath + File.separator + DIR_PATH);

                if(!dir.exists()){
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(bytes);

                stream.close();

                uploadedfile = File.separator + DIR_PATH + File.separator + file.getOriginalFilename();

                logger.info("Server file location : " + serverFile.getAbsolutePath());

            } catch (IOException e){
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "Failed upload " + file.getOriginalFilename() + " because the file was empty.";
        }


                return uploadedfile;
    }

    @RequestMapping(value = "/upbase64", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileBase64(
            @RequestParam("thumb") String base64,
            @RequestParam("filename") String filename,
            @RequestParam("folder") String folder,
            HttpServletRequest httpServletRequest) throws IllegalStateException, IOException {

        if(!base64.isEmpty()){
            try {

                byte[] scanByte = Base64.getDecoder().decode(base64.split(",")[1]);

//                String rootPath = System.getProperty("catalina.home");
                String rootPath = httpServletRequest.getSession().getServletContext().getRealPath("/");

                File dir = new File(rootPath + File.separator + DIR_PATH + File.separator + folder);

                if(!dir.exists()){
                    dir.mkdirs();
                }

                logger.info("Folder for saved base64 files: " + dir);

                File scanFile = new File(dir.getAbsolutePath() + File.separator + filename);

                BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));
                scanStream.write(scanByte);
                scanStream.close();


            } catch (Exception e){
                logger.warn(e.getMessage());
            }
        } else {
            return "Failed to upload Base64 Image File, because it's empty";
        }

        return filename;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file")MultipartFile file,
                                    @RequestParam("quizId") int quizId,
                                    ModelMap modelMap){

        String fileName = file.getOriginalFilename();

//        modelMap.addAttribute("file",file);
//        return "questionadd";

        /**
         * Upload single file
         */
        if(!file.isEmpty()){

            try {

                byte[] bytes = file.getBytes();

                String rootPath = System.getProperty("catalina.home");

                File dir = new File(rootPath + File.separator + "webapps/pictures");

                if(!dir.exists()){
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(bytes);

                stream.close();

                logger.info("Server file location : " + serverFile.getAbsolutePath());

                modelMap.addAttribute("picurl", serverFile.getAbsolutePath());
                modelMap.addAttribute("questionattr",new Question());
                modelMap.addAttribute("quiz", quizService.getById(quizId));
//                return "You successfuly uploaded file : " + name;
                return "questionadd";

            } catch (IOException e){
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "Failed upload " + fileName + " because the file was empty.";
        }
    }
}
