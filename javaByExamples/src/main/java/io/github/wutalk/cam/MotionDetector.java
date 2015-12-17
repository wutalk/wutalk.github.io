/*
 * @(#) 2015年12月17日
 * Copyright (c) 2015 @wutalk on github. All rights reserved.
 */
package io.github.wutalk.cam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

/**
 * use api from https://github.com/sarxos/webcam-capture.git
 * 
 * @author owu
 */
public class MotionDetector implements WebcamMotionListener {

    private static final String TEMP_DIR = "D:/tmp/image-cache";

    public MotionDetector() {
        Webcam cam = Webcam.getDefault();
        Dimension size = new Dimension(640, 480);
        cam.setViewSize(size);
        WebcamMotionDetector detector = new WebcamMotionDetector(cam);
        detector.setInterval(500);
        detector.addMotionListener(this);
        detector.start();
    }

    @Override
    public void motionDetected(WebcamMotionEvent wme) {
        File imageFile = new File(TEMP_DIR, System.currentTimeMillis() + "md-cur.png");
        System.out.println("Detected motion, current snapshot saved to " + imageFile.getName());
        BufferedImage currentImage = wme.getCurrentImage();
        BufferedImage previousImage = wme.getPreviousImage();
        try {
            // ImageIO.write(previousImage, "PNG",
            // new File(imgPrefix, System.currentTimeMillis() + "md-pre.png"));
            ImageIO.write(currentImage, "PNG", imageFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // new MotionDetector();
        // System.in.read(); // keep program open

        snapshot();
    }

    private static void snapshot() throws IOException {
        Webcam cam = Webcam.getDefault();
        Dimension size = new Dimension(640, 480);
        cam.setViewSize(size);
        cam.open();
        String imageName = System.currentTimeMillis() + "test.png";
        RenderedImage image = cam.getImage();
        ImageIO.write(image, "PNG", new File(TEMP_DIR, imageName));
    }
}
