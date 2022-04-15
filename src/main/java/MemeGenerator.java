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
import java.nio.file.Files;

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
  BufferedImage image;
  JButton browse;
  JButton upload;
  JButton preset;
  JButton buildMeme;
  JButton close;
  JButton Submit;
  JLabel presetLabel;
  JLabel browseLabel;
  JLabel uploadLabel;
  JLabel buildMemeLabel;
  JLabel widthLabel;
  JLabel heightLabel;
  int x;
  int y;
  int z;
  int w;
  int Red;
  int Green;
  int Blue;
  int topX;
  int topY;
  int memeHeight;
  int memeWidth;
  int indexOfBrowsingMeme;
  int indexOfBrowsingPresteMeme;
  File memeFile = new File(".");
  float fontSize;
  String mainDirectory = memeFile.getAbsolutePath();
  String blankMemeTemplateFolder = mainDirectory.replace(".", "") + "Blank-Templates\\";
  String memeTemplate;
  String memeText;
  String newMemeFileName;
  String newMemeFileFormat;
  String TextCaption;

  public MemeGenerator(){
    JFrame memeFrame = new JFrame();
    JPanel memePanel = new JPanel();

    //Created buttons
    browse = new JButton("Browse the memes");
    upload = new JButton("Upload your own meme template");
    preset = new JButton("Choose a meme");
    buildMeme = new JButton("Begin Building");
    close = new JButton("Exit Program");

    //wait for button pressed then perform action associated with that button
    browse.addActionListener(this);
    upload.addActionListener(this);
    preset.addActionListener(this);
    buildMeme.addActionListener(this);
    close.addActionListener(this);

    //Final product might not need lable but if it does the label exists
    browseLabel = new JLabel("Browse " +String.valueOf(x));
    uploadLabel = new JLabel("Upload " +String.valueOf(y));
    presetLabel = new JLabel("Preset " +String.valueOf(z));
    buildMemeLabel = new JLabel("Preset " +String.valueOf(z));



    memePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    //I like it better without the gridlayout
    //memePanel.setLayout(new GridLayout());

    memePanel.add(browse);
    memePanel.add(upload);
    memePanel.add(preset);
    memePanel.add(buildMeme);
    memePanel.add(browseLabel);
    memePanel.add(uploadLabel);
    memePanel.add(presetLabel);
    memePanel.add(buildMemeLabel);
    memePanel.add(close);

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
      //4/10/22 Found issue where memes that were created during the program are not shown while browsing
      //Do the browse method
      System.out.println(277353);
      browseLabel.setText("Browse" + String.valueOf(indexOfBrowsingMeme));

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
        browseLabel.setText("Browse " + String.valueOf(indexOfBrowsingMeme));
        
      } catch (Exception BW) {
        //TODO: handle exception
        System.out.println(BW.getStackTrace());
      }
      //Want to pop up window with picture

    }
    else if(e.getSource() == preset){
      //Do the preset meme method
      try {
        memeTemplate = BrowseWindow(blankMemeTemplateFolder, "preset");
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      y++;
      System.out.println(11037);
      presetLabel.setText("Preset " + String.valueOf(indexOfBrowsingPresteMeme));
    }
    else if(e.getSource() == upload){
      //Do the upload a meme template method
      z++;
      System.out.println(177013);
      try {
        UploadWindow();
        uploadLabel.setText("Upload" + String.valueOf(z));
      } catch (Exception BW) {
        //TODO: handle exception
        System.out.println(BW.getStackTrace());
      }
    }
   else if(e.getSource() == buildMeme){
     //Do the build a meme method
       //Cycle through the choose meme button
       //Once settled on one you like click the button.
     w++;
     System.out.println("Use " + (indexOfBrowsingPresteMeme-1) + " for building the meme unless it is negative 1");
     try {
       createTheMeme(memeTemplate);
     } catch (IOException e1) {
       // TODO Auto-generated catch block
       e1.printStackTrace();
     }
     buildMemeLabel.setText("Build " + String.valueOf(indexOfBrowsingPresteMeme));
   }
    else if(e.getSource() == close){
      System.exit(0);
    }
    else{System.out.println("");}
    
  }
  public String BrowseWindow(String path, String option) throws IOException {
    if(option == "browse"){
    File file=new File(".");
    String directory = file.getAbsolutePath();
    System.out.println(directory);
    File generatedMemesDirectory = new File(directory);
    String backgroundImage[] = generatedMemesDirectory.list();
    System.out.println(backgroundImage.length);
    System.out.println(indexOfBrowsingMeme);
    int readyForReturn = 0;
    if(indexOfBrowsingMeme+1 >= backgroundImage.length){
      
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
      return backgroundImage[indexOfBrowsingMeme-1];
      
    } catch (Exception e) {
      //TODO: handle exception
      indexOfBrowsingMeme = 0;
    add(new JLabel(new ImageIcon(backgroundImage[indexOfBrowsingMeme])));
    pack();
    setVisible(true);
    indexOfBrowsingMeme++;
    return backgroundImage[indexOfBrowsingMeme];
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
        return backgroundImage[indexOfBrowsingPresteMeme-1];
        
      } catch (Exception e) {
        //TODO: handle exception
        indexOfBrowsingPresteMeme = 0;
      add(new JLabel(new ImageIcon(path + backgroundImage[indexOfBrowsingPresteMeme])));
      pack();
      setVisible(true);
      indexOfBrowsingPresteMeme++;
      return backgroundImage[indexOfBrowsingPresteMeme-1];
      }
    }
    else{return "else";}
 }

  public void UploadWindow() {
    //Store current working directory of project
    File workingDirectory = new File("");
    String currentPath = workingDirectory.getAbsolutePath();

    //Create directory (user_meme_templates) to store user uploaded meme templates if does not already exist
      //For consistency I changed the upload destination to the already made Blank-Template folder -ESE
    String userMemeTemplates = "Blank-Templates\\";
    File newMemeDirectory = new File(userMemeTemplates);

    if (!newMemeDirectory.exists()) {
      newMemeDirectory.mkdirs();
    }

    //Let user choose file to upload, copy image to user_meme_templates directory
      //For consistency I changed the upload destination to the already made Blank-Template folder -ESE
    JFileChooser newTemplate = new JFileChooser();
    int open = newTemplate.showOpenDialog(null);

    if (open == JFileChooser.APPROVE_OPTION) {
      File newMeme = newTemplate.getSelectedFile();
      String newMemePath = newMeme.getAbsolutePath();

      int index = newMemePath.lastIndexOf("\\");
      String newMemeName = newMemePath.substring(index + 1);

      String destinationPath = currentPath + "\\Blank-Templates\\";
      File destinationFile = new File(destinationPath + newMemeName);

      try {
        Files.copy(newMeme.toPath(), destinationFile.toPath());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      this.setContentPane(new JPanel() {
      });

      add(new JLabel(new ImageIcon(destinationPath + newMemeName)));
      pack();
      setVisible(true);

    }
  }

   public int createTheMeme(String rawMeme) throws IOException{
    image = ImageIO.read(new File(blankMemeTemplateFolder + rawMeme));
    memeHeight = image.getHeight();
    memeWidth = image.getWidth();

  //Brings up the GUI for building the meme
  JFrame memeBuildingFrame = new JFrame();
  JPanel memeBuildingPanel = new JPanel();
  //This is how you do text field.  Just like with buttons an action listener is needed
  JTextField sizeOfFont = new JTextField("Type in the font size");
  JTextField caption= new JTextField("Type in Caption");
  sizeOfFont.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
      String input = sizeOfFont.getText();
     fontSize = Float.parseFloat(input);
     
    }
  });
  
  caption.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      memeText=caption.getText();
      System.out.println(memeText);
    }
  });

  JTextField redBox= new JTextField("Type in R value [0,255]");
  redBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent r){
      String RString = redBox.getText();
      Red = Integer.parseInt(RString);
    }
  });
  memeBuildingPanel.add(redBox); //Each button/textbox needs to be added to the lable

  JTextField greenBox= new JTextField("Type in G value [0,255]");
  greenBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent g){
      String GString = greenBox.getText();
      Red = Integer.parseInt(GString); // Ok I know this is childish but ha gstring.  Typed Rstring and followed the pattern with green but then realized what I typed and had a little laugh.
    }
  });
  memeBuildingPanel.add(greenBox); //Each button/textbox needs to be added to the lable

  JTextField blueBox= new JTextField("Type in B value [0,255]");
  blueBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent b){
      String BString = blueBox.getText();
      Blue = Integer.parseInt(BString);
    }
  });
  memeBuildingPanel.add(blueBox); //Each button/textbox needs to be added to the lable

  JTextField xBox= new JTextField("Type in x value of text location less than " + String.valueOf(memeWidth));
  xBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent x){
      String XString = xBox.getText();
      topX = Integer.parseInt(XString);
    }
  });
  memeBuildingPanel.add(xBox); //Each button/textbox needs to be added to the lable

  JTextField yBox= new JTextField("Type in y value of text location less than " + String.valueOf(memeHeight));
  yBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent y){
      String YString = yBox.getText();
      topY = Integer.parseInt(YString);
    }
  });
  memeBuildingPanel.add(yBox); //Each button/textbox needs to be added to the lable

  JTextField Title= new JTextField("Type in the name of the new meme");
  caption.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent t){
      newMemeFileName = Title.getText();
    }
  });
  memeBuildingPanel.add(Title); //Each button/textbox needs to be added to the lable

  JTextField File= new JTextField("Type in the file format png or jpg");
  caption.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      newMemeFileFormat = File.getText();
    }
  });
  memeBuildingPanel.add(File); //Each button/textbox needs to be added to the lable

  
  JButton select=new JButton("Submit");
  select.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(Red, Green, Blue);
      g.setColor(fontColor);
      g.drawString(memeText, topX, topY);
      g.dispose();
      try {
        ImageIO.write(image, newMemeFileFormat, new File(newMemeFileName + "." + newMemeFileFormat));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  });

  
  JButton save=new JButton("Store Partial");
  save.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(Red, Green, Blue);
      g.setColor(fontColor);
      g.drawString(memeText, topX, topY);
      g.dispose();
      try {
        ImageIO.write(image, newMemeFileFormat, new File(blankMemeTemplateFolder + newMemeFileName + "." + newMemeFileFormat));
        image = ImageIO.read(new File(blankMemeTemplateFolder + newMemeFileName + "." + newMemeFileFormat));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  });
  memeBuildingPanel.add(sizeOfFont); //Each button/textbox needs to be added to the lable
  memeBuildingPanel.add(caption); //Each button/textbox needs to be added to the lable
  memeBuildingPanel.add(select);  // add submit button
  memeBuildingPanel.add(save);  // add submit button

  memeBuildingFrame.getContentPane().setBackground(Color.BLUE);
  memeBuildingFrame.add(memeBuildingPanel, BorderLayout.CENTER);
  memeBuildingFrame.setDefaultCloseOperation(memeBuildingFrame.EXIT_ON_CLOSE);
  memeBuildingFrame.setTitle("Much Great Meme Builder");
  memeBuildingFrame.pack();
  memeBuildingFrame.setVisible(true);


  //read the image 
    //blankMemeTemplateFolder is where the meme templates are
   //System.out.println(memeHeight);
   //System.out.println(memeWidth);
   //get the Graphics object
   //set font
     //fontSize variable
   //System.out.println(fontSize);
   //fontSize = 25f;
   //display the text at the coordinates(x=50, y=150)
   //added color of text
     //Added RGB variables
     //GUI should have input field for them
   //Red = 204;
   //Green = 204;
   //Blue = 255;
   //Added coordinents of XY for text box corner
     //GUI should have input field for them
   //topX = 50;
   //topY = 150;
   //Added variable for the text
     //GUI should have input field for it
   //memeText = "Is this what we need? Yeah pretty much";
   //write the image
   //Added variables for naming the new meme and file format
     //For now lets limit to png and jpg
     //Gif will technically work but will produce a static gif image
   //newMemeFileName = "imageafter";
   //newMemeFileFormat = "png";

  //This method is what overlays text to the image
   //Im thinking pop up a window and that window has a box for text, a box for x cooridinate,
   //a box for y, an enter button that takes the info and adds it to the image, and a save
   //button that saves the new meme, oh and a box for entering the name of the new meme
   return 1;
}
}
