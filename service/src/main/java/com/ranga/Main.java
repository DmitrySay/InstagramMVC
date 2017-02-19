package com.ranga;


import com.ranga.entities.Image;
import com.ranga.service.IImageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Deprecated
public class Main {


    public static void main(String[] args) {

       ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:beans-service.xml", "classpath*:applicationConfig.xml");

        IImageService imageService = (IImageService) context.getBean("imageService");


        System.out.println("++++++++++++++++++++++");

        Image image = new Image();
        image.setFilename("HI");

        imageService.add(image);

        //System.out.println(imageService.);
        ((ClassPathXmlApplicationContext) context).close();

    }

}

