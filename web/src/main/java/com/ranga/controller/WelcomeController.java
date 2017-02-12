package com.ranga.controller;

import com.ranga.entities.Image;
import com.ranga.entities.User;
import com.ranga.service.IImageService;
import com.ranga.service.IUserService;
import com.ranga.service.UserValidator;
import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;


@Controller
public class WelcomeController {

    private static final Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
    private IImageService imageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String printIndex(ModelMap model) {

        //3 шаг выборки, по 3 новости из БД на страницу
        List<Image> imageList = imageService.list(0, 3);
        long count = imageService.count();

        int current = 1;
        int begin = Math.max(1, current - 2);
        int result = (int) Math.ceil(count / (double) 3); //3,33 округляем до 4
        int end = Math.min(begin + 2, result);

        model.addAttribute("imageList", imageList);
        model.addAttribute("count", result);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);


        return "index";
    }

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String printPage(ModelMap model, @PathVariable Integer pageNumber) {

        List<Image> imageList = imageService.list((pageNumber - 1) * 3, 3);
        long count = imageService.count();

        int current = pageNumber;
        int begin = Math.max(1, current - 2);
        int result = (int) Math.ceil(count / (double) 3);
        int end = Math.min(begin + 2, result);

        model.addAttribute("imageList", imageList);
        model.addAttribute("count", result);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "index";
    }


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String printSingin(ModelMap model, @RequestParam(value = "login", required = false) String param) {

        if ("e".equals(param)) {
            model.addAttribute("error", "Incorrect username or password");
        }

        return "signin";
    }

    public void printUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("username " + userDetails.getUsername());
        logger.info("password " + userDetails.getPassword());
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String printRegistration(Model model) {
        User user = new User();
        model.addAttribute("userForm", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {


        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.add(userForm);

        //  securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/index";
    }


    @RequestMapping(value = {"/fileUpLoad", "pages/fileUpLoad"}, method = RequestMethod.POST)
    public String singleImageSave(HttpServletRequest request, @RequestParam("comment") String comment,
                                  @RequestParam("image") MultipartFile image, ModelMap model) throws IOException {


        String filename = "";
        //  bindingResult.hasErrors();, BindingResult bindingResult


        if (validateImage(image) == true && comment != null && !comment.isEmpty()) {

            String rootPath = request.getSession().getServletContext().getRealPath("/");
            File dir = new File(rootPath + File.separator + "WEB-INF/images");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            filename = Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
            String path = dir.getAbsolutePath() + File.separator + filename;
            File file = new File(path);
            FileUtils.writeByteArrayToFile(file, image.getBytes());

            Image imageT = new Image();
            imageT.setComment(comment);
            imageT.setFilename(filename);

            imageService.add(imageT);

            return "redirect:/";


        } else {
            model.addAttribute("message", "Заполните комментарий и выберите фото");
            return "index";
        }

    }


    @RequestMapping({"/delete/{imageId}", "pages/delete/{imageId}"})
    public String deleteImage(@PathVariable("imageId") Integer imageId) {

        if (imageId != null) {
            imageService.delete(imageId);
        }
        return "redirect:/";

    }


    /**
     * Method gets baseURL (http://localhost:80/web/)
     *
     * @param request
     * @return
     */
    public String getBaseURL(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


    /**
     * Validate image method
     *
     * @param image
     * @return true/false
     */

    private boolean validateImage(MultipartFile image) {

        if (image == null || image.isEmpty()) {
            return false;
        }

        String contentType = image.getContentType();

        if (contentType.equals("image/jpg") || contentType.equals("image/jpeg")
                || contentType.equals("image/png") || contentType.equals("image/gif")
                || contentType.equals("image/bmp") || contentType.equals("image/x-png")) {
            return true;
        }


        return false;
    }


}


