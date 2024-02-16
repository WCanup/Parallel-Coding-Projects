/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package directionspage;


import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JButton;


/**
 *
 * @author Will Canup
 */
public class DirectionsPage extends EssentialScheduling{

    private boolean hasDoneTutorial = false;
    ArrayList<ImageIO> panels = new ArrayList<>();
    File imageFile;
    final File tutorialImages = new File(/*preset file will be created*/"");
    JButton goBack = new JButton();
    JButton goForward = new JButton();
    JButton showPercentageBar = new JButton();
    
    
    public void addImageFile(String fileName){
        imageFile = new File(fileName);
    }
    
    public void runTutorial(){
        if(hasDoneTutorial = false){
            /*
            run tutorial by displaying images in tutorialImages file
            and using mouse input to scroll through
            */
            this.hasDoneTutorial = true;
        }
    }
    
    public void PercentageBar(){
        showPercentageBar.addActionListener(new ActionListener({
        public void actionPerformed(ActionEvent e){
            //display percentage bar
        }
        }));
    }
    
    public void back(){
        showPercentageBar.addActionListener(new ActionListener({
        public void actionPerformed(ActionEvent e){
            //return to previous page
        }
        }));
    }
    
    public void forward(){
        showPercentageBar.addActionListener(new ActionListener({
        public void actionPerformed(ActionEvent e){
            //undoes the back function
        }
        }));
    }
    
}
