package com.demo.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Imagecode {
    private BufferedImage image;
    private String code;
    private LocalDateTime createtime;


    public Imagecode(BufferedImage image,String code,LocalDateTime createtime){
        this.image=image;
        this.code=code;
        this.createtime=createtime;
    }

    public Imagecode(BufferedImage image,String code,int createin){
        this.image=image;
        this.code=code;
        this.createtime= LocalDateTime.now().plusSeconds(createin);
    }



    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
