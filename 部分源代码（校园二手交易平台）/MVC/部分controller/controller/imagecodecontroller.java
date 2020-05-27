package com.demo.controller;

import com.demo.model.Imagecode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;


@Controller
public class imagecodecontroller {

    private static final String SESSION_KEY="SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @GetMapping("loginindex/image")
    private String imagecode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Imagecode imagecode=createImagecode(httpServletRequest);
        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_KEY,imagecode);
        ImageIO.write(imagecode.getImage(),"JPEG",httpServletResponse.getOutputStream());
        return  null;
    }

    private Imagecode createImagecode(HttpServletRequest httpServletRequest) {


        int width=60, height=20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        for (int i=0;i<155;i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        String sRand="";
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand,13*i+6,16);
        }
        g.dispose();

        return  new Imagecode(image,sRand, 60);
    }

}

