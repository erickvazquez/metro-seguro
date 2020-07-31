/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrosegurofx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;



/**
 *
 * @author imagineanddo.com
 */

public class FrameGenerator{
    private String ruta;
    
    public FrameGenerator(String ruta){
        this.ruta = ruta;
    }
    
    public  void generar() throws IOException, Exception
    {
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(ruta);
        frameGrabber.start();
        Frame i;
        OpenCVFrameConverter.ToIplImage converterToIplImage = new OpenCVFrameConverter.ToIplImage();
        try {
        	frameGrabber.setFrameNumber(1);//puede ser cualquier frame
            Frame frame = frameGrabber.grabImage();
            System.out.println(frame);
            IplImage image = converterToIplImage.convert(frame);
            BufferedImage bi = IplImageToBufferedImage(image);
            File outputfile = new File("C:\\Users\\EHef_\\Documents\\OCTAVO-SEMESTRE-ESCOM\\IS\\video\\image.jpg");
            ImageIO.write(bi, "jpg", outputfile);
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static BufferedImage IplImageToBufferedImage(IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame,1);
    }    
    
}
