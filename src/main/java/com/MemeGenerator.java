package com;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;


/**
 * The basic GUI is set up.  There is a button that when clicked browses the uploaded memes,
 * a button for meming a preset template, and a button for uploading a meme template.  They
 * dont do much yet since those methods were not implemented yet but basic button 
 * interactivity has been made. - Evan 4/6/2022 
 *
 * No way can I remember the git commands so I'll list them here:
 * cd /d D:\Users\dudeo\Documents\Meme-Generator
 * git clone https://github.com/shehsohail/Meme-Generator.git
 * git status
 * git add .
 * git commit -m "comment"
 * git push
 * 
 * 
 */
public class MemeGenerator extends javax.swing.JFrame implements ActionListener
{
  transient BufferedImage image;
  JButton browse;
  JButton upload;
  JButton preset;
  JButton buildMeme;
  JButton delete;
  JButton deleteTemplate;
  JButton close;
  JButton submit;
  JButton theInstructions;
  JFrame preMeme;
  JLabel welcomeLabel;
  JLabel widthLabel;
  JLabel heightLabel;
  JTextField colorPreviewLabel = new JTextField("Preview your color");
  int previewing = 0;
  int red;
  int green;
  int blue;
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
  String tempMemeTemplateFolder = mainDirectory.replace(".", "") + "t3mp\\";
  String memeTemplate;
  String memeText;
  String newMemeFileName;
  String newMemeFileFormat;
  String textCaption;
  String previewedMeme;
  String browsedFile;
  String notepad = "Notepad.exe";
  String instructionsFile = "Instructions.txt";
  File blankmemet3mpFile = new File(tempMemeTemplateFolder);

  public MemeGenerator(){
    JFrame memeFrame = new JFrame();
    JPanel memePanel = new JPanel();

    //Created buttons
    browse = new JButton("Browse the Memes");
    upload = new JButton("Upload Your Own Meme Template");
    preset = new JButton("Choose a Meme");
    buildMeme = new JButton("Begin Building");
    delete = new JButton("Delete This Meme");
    deleteTemplate = new JButton("Delete This Template");
    close = new JButton("Exit Program");
    theInstructions = new JButton("Open Instructions");

    //wait for button pressed then perform action associated with that button
    browse.addActionListener(this);
    upload.addActionListener(this);
    preset.addActionListener(this);
    buildMeme.addActionListener(this);
    close.addActionListener(this);
    delete.addActionListener(this);
    deleteTemplate.addActionListener(this);
    theInstructions.addActionListener(this);

    welcomeLabel = new JLabel("Please Select from the Following Options Below:");
    welcomeLabel.setFont(new Font("ARIAL", Font.PLAIN, 17));
    


    memePanel.setBorder(BorderFactory.createTitledBorder("Meme Generation"));

    memePanel.setLayout(null);
    memePanel.add(welcomeLabel);
    memePanel.add(browse);
    memePanel.add(upload);
    memePanel.add(preset);
    memePanel.add(buildMeme);
    memePanel.add(close);
    memePanel.add(delete);
    memePanel.add(deleteTemplate);
    memePanel.add(theInstructions);

    memeFrame.setSize(495,325);
    memePanel.setSize(495,325);
    memeFrame.setLocationRelativeTo(null);
    memeFrame.getContentPane().setBackground(Color.BLUE);
    memeFrame.add(memePanel, BorderLayout.CENTER);
    memeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    memeFrame.setTitle("Much Great Meme Generator");
    

    //Set  Location
    welcomeLabel.setBounds(65,20,570,20);
    theInstructions.setBounds(75,60,150,30);
    close.setBounds(255,60,150,30);
    upload.setBounds(10,120,215,30);
    deleteTemplate.setBounds(255,120,215,30);
    preset.setBounds(75,180,150,30);
    buildMeme.setBounds(255,180,150,30);
    browse.setBounds(75,240,150,30);
    delete.setBounds(255,240,150,30);

//    welcomeLabel.setBounds(255,20,570,20);
//    theInstructions.setBounds(30,60,150,30);
//    upload.setBounds(220,60,245,30);
//    preset.setBounds(505,60,130,30);
//    buildMeme.setBounds(675,60,120,30);
//    close.setBounds(675,120,120,30);
//    delete.setBounds(505,120,130,30);
//    deleteTemplate.setBounds(220,120,245,30);
//    browse.setBounds(30,120,150,30);
    

    //Set Border
    browse.setBorder(BorderFactory.createLineBorder(Color.black));
    upload.setBorder(BorderFactory.createLineBorder(Color.black));
    preset.setBorder(BorderFactory.createLineBorder(Color.black));
    buildMeme.setBorder(BorderFactory.createLineBorder(Color.black));
    close.setBorder(BorderFactory.createLineBorder(Color.black));
    delete.setBorder(BorderFactory.createLineBorder(Color.black));
    deleteTemplate.setBorder(BorderFactory.createLineBorder(Color.black));
    theInstructions.setBorder(BorderFactory.createLineBorder(Color.black));
    memeFrame.setVisible(true);
  }

  public static void main( String[] args )
  {
    new MemeGenerator();
    
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == browse){
      try {
        browsedFile = BrowseWindow("Doesnt matter", "browse");
        
      } catch (Exception BW) {
        //TODO: handle exception
        try {
          indexOfBrowsingMeme = 0;
          browsedFile = BrowseWindow("Doesnt matter", "browse");
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }  
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
    }
    else if(e.getSource() == upload){
      try {
        UploadWindow();
      } catch (Exception BW) {
        //TODO: handle exception
        System.out.println("I don't know why but you can't upload that");
      }
    }
   else if(e.getSource() == buildMeme){
     //Do the build a meme method
       //Cycle through the choose meme button
       //Once settled on one you like click the button.
      //clear out t3mp directory
     try {
       createTheMeme(memeTemplate);
     } catch (IOException e1) {
       // TODO Auto-generated catch block
       e1.printStackTrace();
     }
   }
   else if(e.getSource() == close){
    //clear out t3mp directory

    File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
    try {
      FileUtils.cleanDirectory(tempMemeToBeDeleted);
      File readMe = new File(tempMemeTemplateFolder + "ReadMe.txt");
      readMe.createNewFile();
      FileWriter readMeWriter = new FileWriter(tempMemeTemplateFolder + "filename.txt");
      /**
 * Since you mentioned being curious about this our issue was we want the t3mp folder to be empty
 * It should only have files when a meme is being made
 * 
 * git status
 * git add .
 * git commit -m "stuff"
 * git push
 * 
 * These commands caused the temp folder to be removed from github
 * If someone later on were to clone it then the project wouldnt work because the folder doesnt exist
 * You may notice it is called the t3mp folder now
 * We thought maybe github didn't want to push a "temp" folder so the name was changed
 * Eventually we figured out that a blank folder could not be pushed to github
 * These text files serve no other purpose than to keep the folder from being empty
 * The text was more of a complaint and me venting my frustrations
 * I actually kind of forgot about the files
 * It was breifly touched on during the presentation but the specifics of the text files was forgotten
 * 
 * At the time we didnt have the stonks HW assignment where you told us about the assume unchanged thing so we didnt know about it
 * Yes I also realize that we could just create the folder if it doesn't exist but that was not the first idea we had
 * And as we all know first idea best idea
 */
      readMeWriter.write("Not sure what github/s problem is with a blank folder but now the folder is never empty");
      readMeWriter.close();

    } catch (IOException le) {
      // TODO Auto-generated catch block
      le.printStackTrace();
    }
     System.exit(0);
     dispose();
     setVisible(false);
   }
   else if(e.getSource() == delete){
     try {
      Files.delete(Paths.get(mainDirectory).resolve(browsedFile));
      System.out.println("Deleted file iz: " + browsedFile);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      try {
        File deletedFile = new File(mainDirectory + "\\" + browsedFile);
        deletedFile.delete();
        System.out.println("Files path delete failed");
        System.out.println("Deleted file iz: " + browsedFile);
      } catch (Exception el) {
        //TODO: handle exception
        System.out.println("Couldn't delete: " + browsedFile);
      }
    }
   }
   else if(e.getSource() == deleteTemplate){
     //Delete the meme that you browsed to 
     try {//Not sure how to make this work
          //File still gets deleted tho so low priority
      Files.delete(Paths.get(mainDirectory).resolve("t3mp").resolve(memeTemplate));
      System.out.println("Deleted file iz: " + memeTemplate);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      try {
        File deletedFile = new File(blankMemeTemplateFolder + "\\" + memeTemplate);
        deletedFile.delete();
        System.out.println("Files path delete failed");
        System.out.println("Deleted file iz: " + memeTemplate);
      } catch (Exception el) {
        //TODO: handle exception
        System.out.println("Couldn't delete: " + memeTemplate);
      }
    }
   }
   else if(e.getSource() == theInstructions){
     //Open instructions
    try {
      openTheInstructionsFile();
    } catch (Exception otif) {
      //TODO: handle exception
      try {
        readTheInstructionsFile();
      } catch (Exception otif2) {
        //TODO: handle exception
        System.err.println("Could not show you the instructions");
      }
    }
   }
    else{System.out.println("");}
    
  }
  public String BrowseWindow(String path, String option) throws IOException {
    if(option.equals("browse")){
    File file=new File(".");
    String directory = file.getAbsolutePath();
    File generatedMemesDirectory = new File(directory);
    String backgroundImage[] = generatedMemesDirectory.list();
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
      else if(backgroundImage[indexOfBrowsingMeme].contains(".GIF")){
        readyForReturn = 1;
      }
      else if(backgroundImage[indexOfBrowsingMeme].contains(".JPG")){
        readyForReturn = 1;
      }
      else if(backgroundImage[indexOfBrowsingMeme].contains(".png")){
        readyForReturn = 1;
      }
      else if(backgroundImage[indexOfBrowsingMeme].contains(".PNG")){
        readyForReturn = 1;
      }
      else {
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
      indexOfBrowsingMeme = 6;
    add(new JLabel(new ImageIcon(backgroundImage[indexOfBrowsingMeme])));
    pack();
    setVisible(true);
    indexOfBrowsingMeme++;
    return backgroundImage[indexOfBrowsingMeme];
    }}
    else if(option.equals("preset")){
      String directory = path;
      File generatedMemesDirectory = new File(directory);
      String backgroundImage[] = generatedMemesDirectory.list();
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
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".JPG")){
          readyForReturn = 1;
        }
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".png")){
          readyForReturn = 1;
        }
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".PNG")){
          readyForReturn = 1;
        }
        else if(backgroundImage[indexOfBrowsingPresteMeme].contains(".GIF")){
          readyForReturn = 1;
        }
        else {
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
    else if(option.equals("preview")){
      
      String directory = path;
      File generatedMemesDirectory = new File(directory);
      String backgroundImage[] = generatedMemesDirectory.list();
      System.out.println("previewing: " + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
      this.setContentPane(new JPanel() {
      });
      try {
        add(new JLabel(new ImageIcon(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat)));
        pack();
        setVisible(true);
        return backgroundImage[backgroundImage.length-1];
        
      } catch (Exception e) {
        //TODO: handle exception
        System.out.println("preview error");
        indexOfBrowsingPresteMeme = 0;
      add(new JLabel(new ImageIcon(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat)));
      pack();
      setVisible(true);
      return backgroundImage[backgroundImage.length-1];
      }
    }
    else{return "else";}
 }

 public void fileDeleter(String dir, int index){
   File theDirectory = new File(dir);
   String listOfFiles[] = theDirectory.list();
   String theDeletedFileString = dir + listOfFiles[index];
   File theDeletedFile = new File(theDeletedFileString);
   System.out.println("The deleted file is " + theDeletedFileString);
   try {
    theDeletedFile.delete();
   } catch (Exception e) {
     //TODO: handle exception
     System.out.println("I couldn't delete it");
   }
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
      if(isAnImage(newMemeName)){
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
      else{
        System.out.println("Only upload images!");
        System.out.println("Please consult the instructions.");
        try {
          openTheInstructionsFile();
        } catch (Exception e) {
          try {
            readTheInstructionsFile();
          } catch (Exception doBetter) {
            System.err.println("I can't open the instructions but only upload jpg, png, or gif.");
          }
        }
      }
    }
  }

  public boolean isAnImage(String theFileName){
    if(theFileName.contains(".jpg")){
      return true;
    }
    else if(theFileName.contains(".JPG")){
      return true;
    }
    else if(theFileName.contains(".png")){
      return true;
    }
    else if(theFileName.contains(".PNG")){
      return true;
    }
    else if(theFileName.contains(".GIF")){
      return true;
    }
    else if(theFileName.contains(".gif")){
      return true;
    }
    else{
      return false;
    }
  }

  public void readTheInstructionsFile(){
    File instructions = new File(instructionsFile);
    try(FileReader fr = new FileReader(instructions);
      BufferedReader br = new BufferedReader(fr)) {
      String instructionsString;
      while((instructionsString = br.readLine()) != null){
        System.out.println(instructionsString);
      }
    } catch (Exception io) {
      //TODO: handle exception
      io.printStackTrace();
    }
  }

  public void openTheInstructionsFile() throws IOException{
    ProcessBuilder instructionsDotText = new ProcessBuilder(notepad, instructionsFile);
    instructionsDotText.start();
  }

   public int createTheMeme(String rawMeme) throws IOException{
    red = 255;
    green = 255;
    blue = 255;
    topX = 1;
    topY = 1;
    fontSize = 1;
    memeText = "";
    newMemeFileName = "default";
    textCaption = "";
    System.out.println("You are about to meme: " + rawMeme);
    String[] format = rawMeme.split("\\.");
    newMemeFileFormat = format[1];
    image = ImageIO.read(new File(blankMemeTemplateFolder + rawMeme));
    memeHeight = image.getHeight();
    memeWidth = image.getWidth();

  //Brings up the GUI for building the meme
  JFrame memeBuildingFrame = new JFrame();
  JPanel memeBuildingPanel = new JPanel();

  memeBuildingFrame.setSize(425,600);
  memeBuildingPanel.setSize(425,600);
  memeBuildingFrame.setLocationRelativeTo(null);
  memeBuildingPanel.setLayout(null);

  JLabel typingInstructions = new JLabel("<html><strong>" +
          "Type in Requested Information in the Text Fields Below.<br> After Inputting Values, Press Enter for Yellow to Disappear <br> from Each Text Field. +x is right and +y is down. </strong></html>");
  JLabel sizeOfFontLabel = new JLabel("Enter in the Font Size:");
  JTextField sizeOfFont = new JTextField("Enter in the Font Size");
  sizeOfFont.setHorizontalAlignment(SwingConstants.CENTER);

  sizeOfFont.setBackground(Color.YELLOW);
  JLabel captionLabel = new JLabel("Enter in Caption:");
  JTextField caption= new JTextField("Enter in Caption");
  caption.setHorizontalAlignment(SwingConstants.CENTER);

  caption.setBackground(Color.YELLOW);
  sizeOfFont.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
      String input = sizeOfFont.getText();
      sizeOfFont.setBackground(Color.WHITE);
      
     try {
      fontSize = Float.parseFloat(input);
     } catch (Exception fonts) {
       //TODO: handle exception
       fontSize = 1;
     }
     
    }
  });
  
  caption.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      memeText=caption.getText();
      caption.setBackground(Color.WHITE);
    }
  });

  JLabel redBoxLabel = new JLabel("Enter in R value [0,255]:");
  JTextField redBox= new JTextField("Enter in R value [0,255]");
  redBox.setHorizontalAlignment(SwingConstants.CENTER);

  redBox.setBackground(Color.YELLOW);

  redBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent r){
      String RString = redBox.getText();
      redBox.setBackground(Color.WHITE);
      try {
        red = Integer.parseInt(RString);
      } catch (Exception fonts) {
        //TODO: handle exception
        red = 4;
      }
      finally{
        try {
          colorPreviewLabel.setBackground(new Color(red, green, blue));
        } catch (Exception e) {
          //TODO: handle exception
          System.err.println("Enter integers for RGB");
        }
      }
    }
  });

  memeBuildingPanel.add(redBox); //Each button/textbox needs to be added to the label

  JLabel greenBoxLabel = new JLabel("Enter in G value [0,255]:");
  JTextField greenBox= new JTextField("Enter in G value [0,255]");
  greenBox.setHorizontalAlignment(SwingConstants.CENTER);

  greenBox.setBackground(Color.YELLOW);
  greenBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent g){
      String GString = greenBox.getText();
      greenBox.setBackground(Color.WHITE);
      try {
        green = Integer.parseInt(GString); // Ok I know this is childish but ha gstring.  Typed Rstring and followed the pattern with green but then realized what I typed and had a little laugh.
      } catch (Exception e) {
        //TODO: handle exception
        green = 2;
      }
      finally{
        try {
          colorPreviewLabel.setBackground(new Color(red, green, blue));
        } catch (Exception e) {
          //TODO: handle exception
          System.err.println("Enter integers for RGB");
        }
      }
    }
  });
  memeBuildingPanel.add(greenBox); //Each button/textbox needs to be added to the label

  JLabel blueBoxLabel = new JLabel("Enter in B value [0,255]:");
  JTextField blueBox= new JTextField("Enter in B value [0,255]");
  blueBox.setHorizontalAlignment(SwingConstants.CENTER);

  blueBox.setBackground(Color.YELLOW);
  blueBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent b){
      String BString = blueBox.getText();
      blueBox.setBackground(Color.WHITE);
      try {
        blue = Integer.parseInt(BString);
      } catch (Exception e) {
        //TODO: handle exception
      blue = 0;
      }
      finally{
        try {
          colorPreviewLabel.setBackground(new Color(red, green, blue));
        } catch (Exception e) {
          //TODO: handle exception
          System.err.println("Enter integers for RGB");
        }
      }
    }
  });
  memeBuildingPanel.add(blueBox); //Each button/textbox needs to be added to the label

  JLabel xBoxLabel = new JLabel("Enter in X value < " + String.valueOf(memeWidth) + ":");
  JTextField xBox= new JTextField("Enter in X value < " + String.valueOf(memeWidth));
  xBox.setHorizontalAlignment(SwingConstants.CENTER);

  xBox.setBackground(Color.YELLOW);
 
  xBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent x){
      String XString = xBox.getText();
      xBox.setBackground(Color.WHITE);
      
      try {
        topX = Integer.parseInt(XString);
      } catch (Exception e) {
        //TODO: handle exception
      topX = 1;
      }
    }
  });
  memeBuildingPanel.add(xBox); //Each button/textbox needs to be added to the label

  JLabel yBoxLabel = new JLabel("Enter in Y value < " + String.valueOf(memeHeight) +":");
  JTextField yBox= new JTextField("Enter in Y value < " + String.valueOf(memeHeight));
  yBox.setHorizontalAlignment(SwingConstants.CENTER);

  yBox.setBackground(Color.YELLOW);
  yBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent y){
      String YString = yBox.getText();
      yBox.setBackground(Color.WHITE);
      try {
        topY = Integer.parseInt(YString);
      } catch (Exception e) {
        //TODO: handle exception
      topY = 1;
      }
    }
  });
  memeBuildingPanel.add(yBox); //Each button/textbox needs to be added to the label

  JLabel TitleLabel = new JLabel("Enter the Name of the Meme:");
  JTextField Title= new JTextField("Enter the Name of the Meme");
  Title.setHorizontalAlignment(SwingConstants.CENTER);

  Title.setBackground(Color.YELLOW);
  // changed line 573 below from caption to Title.
  Title.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent t){
      newMemeFileName = Title.getText();
      Title.setBackground(Color.WHITE);
    }
  });
  memeBuildingPanel.add(Title); //Each button/textbox needs to be added to the label

  //JLabel colorPreviewLabel = new JLabel("Press enter to preview your color"); //Label not needed?
  colorPreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);

  colorPreviewLabel.setBackground(Color.YELLOW);
  // changed line 573 below from caption to Title.
  colorPreviewLabel.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent t){
      try {
        colorPreviewLabel.setBackground(new Color(red, green, blue));
      } catch (Exception e) {
        //TODO: handle exception
        System.err.println("Enter integers for RGB");
      }
    }
  });
  memeBuildingPanel.add(colorPreviewLabel); //Each button/textbox needs to be added to the label

   
  JButton select=new JButton("Submit");
  select.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(red, green, blue);
      g.setColor(fontColor);
      g.drawString(memeText, topX, topY);
      g.dispose();
      previewing = 0;
      try {
        ImageIO.write(image, newMemeFileFormat, new File(newMemeFileName + "." + newMemeFileFormat));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      //clear out t3mp directory

      File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
      memeBuildingFrame.dispose();
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);
        File readMe = new File(tempMemeTemplateFolder + "ReadMe.txt");
        readMe.createNewFile();
        FileWriter readMeWriter = new FileWriter(tempMemeTemplateFolder + "filename.txt");
        readMeWriter.write("Not sure what github/s problem is with a blank folder but now the folder is never empty");
        readMeWriter.close();

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
  });

  
  JButton save = new JButton("Save this Edit");
  save.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      System.out.println("the value of previewing is: " + previewing);
      System.out.println("Your caption is: " + memeText);
      System.out.println("Your color triplet (R,G,B) is: (" + Integer.toString(red) + ", " + Integer.toString(green) + ", " + Integer.toString(blue) + ") ");
      System.out.println("Your text location starting point (X, Y) is: (" + Integer.toString(topX) +  ", " + Integer.toString(topY) + ") ");
      if(previewing == 0){
        //Delete all files in t3mp if this is the first attemot of memeing
      File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      }
      if(memeText == null){memeText = "";}
      if(newMemeFileName == null){newMemeFileName = "default";}
      if(newMemeFileFormat == null){newMemeFileFormat = "png";}
      if(red > 255){red = 255;}
      if(red < 0){red = 0;}
      if(green > 255){green = 255;}
      if(green < 0){green = 0;}
      if(blue > 255){blue = 255;}
      if(blue < 0){blue = 0;}
      if(topX > memeWidth){topX = memeWidth / 2;}
      if(topX < 0){topX = 1;}
      if(topY > memeHeight){topY = memeHeight / 2;}
      if(topY < 0){topY = 1;}
      if(fontSize <= 0){fontSize = 1;}
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(red, green, blue);
      g.setColor(fontColor);
      g.drawString(memeText, topX, topY);
      g.dispose();
      previewing = previewing + 1;
      try {
        ImageIO.write(image, newMemeFileFormat, new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
        image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        try {
          ImageIO.write(image, "png", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "png"));
          image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "png"));
        } catch (Exception e2) {
          //TODO: handle exception
          try {
            ImageIO.write(image, "jpg", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "jpg"));
            image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "jpg"));
          } catch (IOException e1) {
            try {
              ImageIO.write(image, "JPG", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "JPG"));
              image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "JPG"));
            } catch (Exception e8) {
              //TODO: handle exception
              try {
                ImageIO.write(image, "PNG", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "PNG"));
                image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "PNG"));
              } catch (Exception e12) {
                //TODO: handle exception
                try {
                  ImageIO.write(image, "gif", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "gif"));
                  image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "gif"));
                } catch (Exception ex) {
                  try {
                    ImageIO.write(image, "GIF", new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "GIF"));
                    image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + "GIF"));
                  } catch (Exception eZ) {
                    //TODO: handle exception
                    eZ.printStackTrace();
                    previewing = previewing - 1;
                  }
                }
              }
            }
            // TODO Auto-generated catch block
          }
        }
      }
      try {
        BrowseWindow(tempMemeTemplateFolder, "preview");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  });

  
  JButton preview=new JButton("Instrustions");
  preview.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      try {
        //Open Instrustions txt doc
        openTheInstructionsFile();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.err.println("Couldn't open the file");
        System.err.println("Going to try and read the file and print the instructions");
        System.err.println("Install Notepad.exe in the future please");
        readTheInstructionsFile();
      }
    }
  });

  
  JButton undo=new JButton("Undo");
  undo.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      System.out.println("undo");
      sizeOfFont.setBackground(Color.YELLOW);
      redBox.setBackground(Color.YELLOW);
      greenBox.setBackground(Color.YELLOW);
      blueBox.setBackground(Color.YELLOW);
      xBox.setBackground(Color.YELLOW);
      yBox.setBackground(Color.YELLOW);
      caption.setBackground(Color.YELLOW);
      //Get length of files in t3mp directory
      File generatedMemesDirectory = new File(tempMemeTemplateFolder);
      String tempMeme[] = generatedMemesDirectory.list();
      //If 0 do nothing
      if(tempMeme.length == 0){
        System.out.println("Nothing to undo");
      }
      //If one copy meme from upload folder
      else if(tempMeme.length == 1){
        try {
          previewing = previewing + 1;
          image = ImageIO.read(new File(blankMemeTemplateFolder + rawMeme));
          System.out.println("writing: " + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
          ImageIO.write(image, newMemeFileFormat, new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
          image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
          System.out.println("read: " + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      //If more than one take second most recent and name it most recent
        //Seems to work but you cant undo twice
      else if(tempMeme.length > 1){
        System.out.println("newest file you want to delete: " + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat); //The newest file (You dont want that)
        System.out.println("second most recent file you want to go back to: " + newMemeFileName + String.valueOf(previewing-1) + "." + newMemeFileFormat); //The newest file (You want to undo back to this)
        //Make image = second most recent file
        try {
          image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing-1) + "." + newMemeFileFormat));
          File undidMeme = new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
          undidMeme.delete();
          previewing = previewing + 1;
          ImageIO.write(image, newMemeFileFormat, new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));

        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  });

  JButton closeWindow = new JButton("Exit Window");
  closeWindow.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      //clear out t3mp directory

      File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
      memeBuildingFrame.dispose();
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);
        File readMe = new File(tempMemeTemplateFolder + "ReadMe.txt");
        readMe.createNewFile();
        FileWriter readMeWriter = new FileWriter(tempMemeTemplateFolder + "filename.txt");
        readMeWriter.write("Not sure what github/s problem is with a blank folder but now the folder is never empty");
        readMeWriter.close();

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      memeBuildingFrame.dispose();
    }
  });

  JButton startFromScratch = new JButton("Restart Meme Building");
  startFromScratch.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      previewing = 0;
      indexOfBrowsingMeme = 0;
      indexOfBrowsingPresteMeme = 0;
      red = 255;
      green = 255;
      blue = 255;
      topX = 1;
      topY = 1;
      fontSize = 1;
      memeText = "";
      newMemeFileName = "default";
      textCaption = "";
      File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
      memeBuildingFrame.dispose();
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);
        memeBuildingFrame.dispose();
      } catch (IOException tmtbd) {
        // TODO Auto-generated catch block
        tmtbd.printStackTrace();
      }
      memeBuildingFrame.dispose();
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);
        File readMe = new File(tempMemeTemplateFolder + "ReadMe.txt");
        readMe.createNewFile();
        FileWriter readMeWriter = new FileWriter(tempMemeTemplateFolder + "filename.txt");
        readMeWriter.write("Not sure what github/s problem is with a blank folder but now the folder is never empty");
        readMeWriter.close();

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  });
  memeBuildingPanel.add(typingInstructions); //add instructions to type in text fields
  memeBuildingPanel.add(sizeOfFont); //Each button/textbox needs to be added to the label
  memeBuildingPanel.add(caption); //Each button/textbox needs to be added to the label
  memeBuildingPanel.add(select);  // add submit button
  memeBuildingPanel.add(save);  // add submit button
  memeBuildingPanel.add(preview);  // add submit button
  memeBuildingPanel.add(undo);  // add submit button

  memeBuildingPanel.add(redBoxLabel);
  memeBuildingPanel.add(blueBoxLabel);
  memeBuildingPanel.add(greenBoxLabel);
  memeBuildingPanel.add(xBoxLabel);
  memeBuildingPanel.add(yBoxLabel);
  memeBuildingPanel.add(TitleLabel);
  memeBuildingPanel.add(sizeOfFontLabel);
  memeBuildingPanel.add(captionLabel);
  memeBuildingPanel.add(startFromScratch);
  memeBuildingPanel.add(closeWindow);
  memeBuildingPanel.add(colorPreviewLabel);


  memeBuildingFrame.getContentPane().setBackground(Color.BLUE);
  memeBuildingFrame.add(memeBuildingPanel, BorderLayout.CENTER);
  memeBuildingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  memeBuildingFrame.setTitle("Much Great Meme Builder");
  memeBuildingFrame.setVisible(true);

  //Set Locations
  typingInstructions.setBounds(30,10,570,45);
  redBoxLabel.setBounds(30,75,200,30);
  redBox.setBounds(220,75,175,30);

  greenBoxLabel.setBounds(30,115,200,30);
  greenBox.setBounds(220,115,175,30);

  blueBoxLabel.setBounds(30,155,200,30);
  blueBox.setBounds(220,155,175,30);

  xBoxLabel.setBounds(30,195,200,30);
  xBox.setBounds(220,195,175,30);

  yBoxLabel.setBounds(30,235,200,30);
  yBox.setBounds(220,235,175,30);

  TitleLabel.setBounds(30,275,200,30);
  Title.setBounds(220,275,175,30);

  startFromScratch.setBounds(225,315,155,30);
  colorPreviewLabel.setBounds(55,315,155,30);

  sizeOfFontLabel.setBounds(30,355,200,30);
  sizeOfFont.setBounds(220,355,175,30);

  captionLabel.setBounds(30,395,200,30);
  caption.setBounds(220,395,175,30);

  undo.setBounds(55,435,150,30);
  save.setBounds(220,435,150,30);
  preview.setBounds(55,475,150,30);
  select.setBounds(220,475,150,30);
  closeWindow.setBounds(55,515,315,30);

  //Set Border
  undo.setBorder(BorderFactory.createLineBorder(Color.black));
  save.setBorder(BorderFactory.createLineBorder(Color.black));
  preview.setBorder(BorderFactory.createLineBorder(Color.black));
  select.setBorder(BorderFactory.createLineBorder(Color.black));
  startFromScratch.setBorder(BorderFactory.createLineBorder(Color.black));
  closeWindow.setBorder(BorderFactory.createLineBorder(Color.black));

  //I forgot public void was a thing so I said public int
  //We don't use this return but I dont feel like changing to void from int
  //Just wanted to add this comment because I anticipate you having this question
   return 1;
}
}
