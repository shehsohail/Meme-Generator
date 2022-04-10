import java.io.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton.*;
import javax.swing.*;

import javax.imageio.ImageIO;


/**
 * The basic GUI is set up.  There is a button that when clicked browses the uploaded memes,
 * a button for meming a preset template, and a button for uploading a meme template.  They
 * dont do much yet since those methods were not implemented yet but basic button 
 * interactivity has been made. - Evan 4/6/2022 
 *
 * No way can I remember the git commands so I'll list them here:
 * cd /d D:\Users\dudeo\Documents\Meme-Generator
 * git status
 * git add .
 * git commit -m "comment"
 * git push
 * 
 * 
 */
public class MemeGenerator extends javax.swing.JFrame implements ActionListener
{
  JButton browse;
  JButton upload;
  JButton preset;
  JButton buildMeme;
  JLabel presetLable;
  JLabel browseLable;
  JLabel uploadLable;
  JLabel buildMemeLable;
  int x;
  int y;
  int z;
  int w;
  int indexOfBrowsingMeme;
  int indexOfBrowsingPresteMeme;
  File memeFile = new File(".");
  String mainDirectory = memeFile.getAbsolutePath();
  String blankMemeTemplateFolder = mainDirectory.replace(".", "") + "Blank-Templates\\";

  public MemeGenerator(){
    JFrame memeFrame = new JFrame();
    JPanel memePanel = new JPanel();

    //Created buttons
    browse = new JButton("Browse the memes");
    upload = new JButton("Upload your own meme template");
    preset = new JButton("Choose a meme");
    buildMeme = new JButton("Begin Building");

    //wait for button pressed then perform action associated with that button
    browse.addActionListener(this);
    upload.addActionListener(this);
    preset.addActionListener(this);
    buildMeme.addActionListener(this);

    //Final product might not need lable but if it does the lable exists
    browseLable = new JLabel("Browse " +String.valueOf(x));
    uploadLable = new JLabel("Upload " +String.valueOf(y));
    presetLable = new JLabel("Preset " +String.valueOf(z));
    buildMemeLable = new JLabel("Preset " +String.valueOf(z));



    memePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    //I like it better without the gridlayout
    //memePanel.setLayout(new GridLayout());

    memePanel.add(browse);
    memePanel.add(upload);
    memePanel.add(preset);
    memePanel.add(buildMeme);
    memePanel.add(browseLable);
    memePanel.add(uploadLable);
    memePanel.add(presetLable);
    memePanel.add(buildMemeLable);

    memeFrame.getContentPane().setBackground(Color.BLUE);
    memeFrame.add(memePanel, BorderLayout.CENTER);
    memeFrame.setDefaultCloseOperation(memeFrame.EXIT_ON_CLOSE);
    memeFrame.setTitle("Much Great Meme Generator");
    memeFrame.pack();
    memeFrame.setVisible(true);
  }

  public static void main( String[] args ) throws IOException
  {
    new MemeGenerator();
    
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == browse){
      //Do the browse method
      System.out.println(277353);
      browseLable.setText("Browse" + String.valueOf(indexOfBrowsingMeme));

      //Pops up a blue pic
      // JFrame oldMemesViewer = new JFrame();
      // oldMemesViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // oldMemesViewer.setPreferredSize(new Dimension(550, 300));
      // oldMemesViewer.getContentPane().setBackground(Color.BLUE);
      // oldMemesViewer.pack();
      // oldMemesViewer.setVisible(true);
      //Pops up a blue window

      //Want to pop up window with picture
        //This works.  For now I added some memes to try.
        //For now lets say memes are saved in \Meme-Generator
        //and templates are saved somewhere else
      try {
        BrowseWindow("Doesnt matter", "browse");  
        browseLable.setText("Browse " + String.valueOf(indexOfBrowsingMeme));
        
      } catch (Exception BW) {
        //TODO: handle exception
        System.out.println(BW.getStackTrace());
      }
      //Want to pop up window with picture

    }
    else if(e.getSource() == preset){
      //Do the preset meme method
      try {
        BrowseWindow(blankMemeTemplateFolder, "preset");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      y++;
      System.out.println(11037);
      presetLable.setText("Preset " + String.valueOf(indexOfBrowsingPresteMeme));
    }
    else if(e.getSource() == upload){
      //Do the upload a meme template method
      z++;
      System.out.println(177013);
      uploadLable.setText("Upload " + String.valueOf(z));
    }
    else if(e.getSource() == buildMeme){
      //Do the build a meme method
        //Cycle through the choose meme button
        //Once settled on one you like click the button.
      w++;
      System.out.println("Use " + (indexOfBrowsingPresteMeme-1) + " for building the meme unless it is negative 1");
      try {
        createTheMeme();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      buildMemeLable.setText("Build " + String.valueOf(indexOfBrowsingPresteMeme));
    }
    else{System.out.println("");}
    
  }
  public int BrowseWindow(String path, String option) throws IOException {
    if(option == "browse"){
    File file=new File(".");
    String directory = file.getAbsolutePath();
    System.out.println(directory);
    File generatedMemesDirectory = new File(directory);
    String backgroundImage[] = generatedMemesDirectory.list();
    System.out.println(backgroundImage.length);
    System.out.println(indexOfBrowsingMeme);
    int readyForReturn = 0;
    if(indexOfBrowsingMeme == backgroundImage.length){
      
      indexOfBrowsingMeme = 0;
    }
    while(readyForReturn == 0){
      if(backgroundImage[indexOfBrowsingMeme].contains(".jpg")){
        readyForReturn = 1;
      }
      else if(backgroundImage[indexOfBrowsingMeme].contains(".gif")){
        readyForReturn = 1;
      }
      else if(backgroundImage[indexOfBrowsingMeme].contains(".png")){
        readyForReturn = 1;
      }
      else {
        System.out.println("inc");
        indexOfBrowsingMeme = indexOfBrowsingMeme + 1;
      }
    }
    System.out.println(backgroundImage[indexOfBrowsingMeme]);
    this.setContentPane(new JPanel() {
    });
    try {
      add(new JLabel(new ImageIcon(backgroundImage[indexOfBrowsingMeme])));
      pack();
      setVisible(true);
      indexOfBrowsingMeme++;
      return 1;
      
    } catch (Exception e) {
      //TODO: handle exception
      indexOfBrowsingMeme = 0;
    add(new JLabel(new ImageIcon(backgroundImage[indexOfBrowsingMeme])));
    pack();
    setVisible(true);
    indexOfBrowsingMeme++;
    return 1;
    }}
    else if(option == "preset"){
      String directory = path;
      System.out.println(directory);
      File generatedMemesDirectory = new File(directory);
      String backgroundImage[] = generatedMemesDirectory.list();
      System.out.println(backgroundImage.length);
      System.out.println(indexOfBrowsingPresteMeme);
      int readyForReturn = 0;
      if(indexOfBrowsingPresteMeme == backgroundImage.length){
        
        indexOfBrowsingPresteMeme = 0;
      }
      while(readyForReturn == 0){
        if(backgroundImage[indexOfBrowsingPresteMeme].contains(".jpg")){
          readyForReturn = 1;
        }
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".gif")){
          readyForReturn = 1;
        }
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".png")){
          readyForReturn = 1;
        }
        else {
          System.out.println("inc");
          indexOfBrowsingPresteMeme = indexOfBrowsingPresteMeme + 1;
        }
      }
      System.out.println(backgroundImage[indexOfBrowsingPresteMeme]);
      this.setContentPane(new JPanel() {
      });
      try {
        add(new JLabel(new ImageIcon(path + backgroundImage[indexOfBrowsingPresteMeme])));
        pack();
        setVisible(true);
        indexOfBrowsingPresteMeme++;
        return 1;
        
      } catch (Exception e) {
        //TODO: handle exception
        indexOfBrowsingPresteMeme = 0;
      add(new JLabel(new ImageIcon(path + backgroundImage[indexOfBrowsingPresteMeme])));
      pack();
      setVisible(true);
      indexOfBrowsingPresteMeme++;
      return 1;
      }
    }
    else{return 3;}
 }
 public int createTheMeme() throws IOException{
 
   //read the image
    BufferedImage image = ImageIO.read(new File("imagebefore.jpg"));
    //get the Graphics object
    Graphics g = image.getGraphics();
    //set font
    g.setFont(g.getFont().deriveFont(25f));
    //display the text at the coordinates(x=50, y=150)
    //added color of text
    g.setColor(Color.black);
    g.drawString("Is this what we need?", 50, 150);
    g.dispose();
    //write the image
    //ImageIO.write(image, "png", new File("imageafter.png"));
    
   //This method is what overlays text to the image
    //Im thinking pop up a window and that window has a box for text, a box for x cooridinate, 
    //a box for y, an enter button that takes the info and adds it to the image, and a save 
    //button that saves the new meme, oh and a box for entering the name of the new meme
    return ImageIO.write(image, "png", new File("imageafter.png"));
 }
}
