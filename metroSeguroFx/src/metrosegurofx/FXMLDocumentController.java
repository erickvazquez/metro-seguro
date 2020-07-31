/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrosegurofx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 *
 * @author EHef_
 */
public class FXMLDocumentController implements Initializable {
    
    
    private MediaPlayer mediaPlayer;
    
    
   @FXML
   private MediaView mediaView;
    
    
    private String filePath;
    
    
    @FXML
   // private void handleButtonAction(ActionEvent event) throws IOException, FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
      private void handleButtonAction() throws IOException, FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.stc)", "*.stc");
           fileChooser.getExtensionFilters().add(filter);
           File file = fileChooser.showOpenDialog(null);
           filePath = file.toURI().toString();

           
        
       // KeyGenerator kgen = KeyGenerator.getInstance("AES");
        //byte key[] = {0x00,0x32,0x22,0x11,0x00,0x00,0x00,0x00,0x00,0x23,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
       // SecretKey skey = kgen.generateKey();
        //Lgo
      //  FileInputStream fis = new FileInputStream(file);
       // this.cifrarAES(skey, fis);
       // this.descifrarAES(skey, file);
        File file2 =  new File("C:\\Users\\EHef_\\Documents\\OCTAVO-SEMESTRE-ESCOM\\IS\\materialVideo\\videoplayback (3).mp4");
        filePath = file2.toURI().toString();
        System.out.println(filePath);
      //
      //  FrameGenerator frame =  new FrameGenerator(file.getPath());
       // frame.generar();
           if(filePath != null){
               
                Media media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                
                
                DoubleProperty width = mediaView.fitWidthProperty();
                DoubleProperty hight = mediaView.fitHeightProperty();
                
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                hight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "hight"));
                
                mediaPlayer.play();
           }
           
    }
    
     private  void cifrarAES(SecretKey skey, FileInputStream file ) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
        
        File outfile = new File("C:\\Users\\EHef_\\Documents\\OCTAVO-SEMESTRE-ESCOM\\IS\\video\\encVideo.stc");
        
        int read;
        
        if(!outfile.exists())
            outfile.createNewFile();
        
        Cipher encipher = Cipher.getInstance("AES");
        
         FileOutputStream fos = new FileOutputStream(outfile);
          encipher.init(Cipher.ENCRYPT_MODE, skey);
        CipherInputStream cis = new CipherInputStream(file, encipher);
        
         while((read = cis.read())!=-1)
                {
                    fos.write((char)read);
                    fos.flush();
                }   
        fos.close();
        
    }
    
    private void descifrarAES(SecretKey skey, File file ) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{
        int read;
        
        File decfile = new File("C:\\Users\\EHef_\\Documents\\OCTAVO-SEMESTRE-ESCOM\\IS\\decVideo.mp4");
        if(!decfile.exists())
            decfile.createNewFile();
        Cipher decipher = Cipher.getInstance("AES");
        FileInputStream encfis = new FileInputStream(file);
        FileOutputStream decfos = new FileOutputStream(decfile);
        
        decipher.init(Cipher.DECRYPT_MODE, skey);
        CipherOutputStream cos = new CipherOutputStream(decfos,decipher);
        
        while((read=encfis.read())!=-1)
        {
            cos.write(read);
            cos.flush();
        }
         cos.close(); 
        
    }
    
     public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }
   
    
    @FXML
    private void pauseVideo(){
         mediaPlayer.pause();
    }
    
    @FXML
    private void playVideo(){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    @FXML
    private void stopVideo(){
        mediaPlayer.stop();
    }
    
    @FXML
    private void fastVideo(){
        mediaPlayer.setRate(1.5);
    }
    
    @FXML
    private void fasterVideo(){
        mediaPlayer.setRate(2);
    }
    
    @FXML
    private void slowVideo(){
        mediaPlayer.setRate(.75);
    }
    
    @FXML
    private void slowerVideo(){
        mediaPlayer.setRate(.5);
    }
    
    @FXML
    private void exitVideo(){
        System.exit(0);
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    
}
