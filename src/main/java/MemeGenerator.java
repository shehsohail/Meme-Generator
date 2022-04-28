import java.io.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Files;

import javax.swing.AbstractButton.*;
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
  JButton delete;
  JButton deleteTemplate;
  JButton close;
  JButton Submit;
  JFrame PreMeme;
  JLabel presetLabel;
  JLabel browseLabel;
  JLabel uploadLabel;
  JLabel welcomeLabel;
  JLabel buildMemeLabel;
  JLabel widthLabel;
  JLabel heightLabel;
  int x;
  int y;
  int z;
  int w;
  int previewing = 0;
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
  String tempMemeTemplateFolder = mainDirectory.replace(".", "") + "t3mp\\";
  String memeTemplate;
  String memeText;
  String newMemeFileName;
  String newMemeFileFormat;
  String TextCaption;
  String previewedMeme;
  String browsedFile;
  File blankmemet3mpFile = new File(tempMemeTemplateFolder);

  public MemeGenerator(){
    JFrame memeFrame = new JFrame();
    JPanel memePanel = new JPanel();

    //Created buttons
    browse = new JButton("Browse the Memes");
    upload = new JButton("Upload Your Own Meme Template");
    preset = new JButton("Choose a Meme");
    buildMeme = new JButton("Begin Building");
    delete = new JButton("Delete this meme");
    deleteTemplate = new JButton("Delete this template");
    close = new JButton("Exit Program");

    //wait for button pressed then perform action associated with that button
    browse.addActionListener(this);
    upload.addActionListener(this);
    preset.addActionListener(this);
    buildMeme.addActionListener(this);
    close.addActionListener(this);
    delete.addActionListener(this);
    deleteTemplate.addActionListener(this);

    //Final product might not need label but if it does the label exists
    browseLabel = new JLabel("Browse " +String.valueOf(x));
    uploadLabel = new JLabel("Upload " +String.valueOf(y));
    presetLabel = new JLabel("Preset " +String.valueOf(z));
    buildMemeLabel = new JLabel("Preset " +String.valueOf(z));
    welcomeLabel = new JLabel("Please Select from the Following Options Below:");



//    memePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    memePanel.setBorder(BorderFactory.createTitledBorder("Meme Generation"));
    memePanel.setLayout(null);
    //I like it better without the gridlayout
    //memePanel.setLayout(new GridLayout());

    memePanel.add(welcomeLabel);
    memePanel.add(browse);
    memePanel.add(upload);
    memePanel.add(preset);
    memePanel.add(buildMeme);
    memePanel.add(browseLabel);
    memePanel.add(uploadLabel);
    memePanel.add(presetLabel);
    memePanel.add(buildMemeLabel);
    memePanel.add(close);
    memePanel.add(delete);
    memePanel.add(deleteTemplate);

    memeFrame.setSize(850,200);
    memePanel.setSize(850,200);
    memeFrame.setLocationRelativeTo(null);
    memeFrame.getContentPane().setBackground(Color.BLUE);
    memeFrame.add(memePanel, BorderLayout.CENTER);
    memeFrame.setDefaultCloseOperation(memeFrame.EXIT_ON_CLOSE);
    memeFrame.setTitle("Much Great Meme Generator");

    //Set  Location
    welcomeLabel.setBounds(285,20,570,15);
    browse.setBounds(30,60,150,30);
    upload.setBounds(220,60,245,30);
    preset.setBounds(505,60,130,30);
    buildMeme.setBounds(675,60,120,30);
    close.setBounds(675,120,120,30);
    delete.setBounds(505,120,120,30);
    deleteTemplate.setBounds(220,120,245,30);

    //Set Border
    browse.setBorder(BorderFactory.createLineBorder(Color.black));
    upload.setBorder(BorderFactory.createLineBorder(Color.black));
    preset.setBorder(BorderFactory.createLineBorder(Color.black));
    buildMeme.setBorder(BorderFactory.createLineBorder(Color.black));
    close.setBorder(BorderFactory.createLineBorder(Color.black));
    delete.setBorder(BorderFactory.createLineBorder(Color.black));
    deleteTemplate.setBorder(BorderFactory.createLineBorder(Color.black));
//    memeFrame.pack();
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
      browseLabel.setText("Browse " + String.valueOf(indexOfBrowsingMeme));

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
        browsedFile = BrowseWindow("Doesnt matter", "browse");  
        browseLabel.setText("Browse " + String.valueOf(indexOfBrowsingMeme));
        
      } catch (Exception BW) {
        //TODO: handle exception
        try {
          indexOfBrowsingMeme = 0;
          browsedFile = BrowseWindow("Doesnt matter", "browse");
          browseLabel.setText("Browse " + String.valueOf(indexOfBrowsingMeme));
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
      //clear out t3mp directory

      File tempMemeToBeDeleted = new File(tempMemeTemplateFolder);
      try {
        FileUtils.cleanDirectory(tempMemeToBeDeleted);

      } catch (IOException ejn) {
        // TODO Auto-generated catch block
        ejn.printStackTrace();
      }
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
   else if(e.getSource() == delete){
     //System.exit(0);
     //Delete the meme that you browsed to 
     File deletedFile = new File(mainDirectory + "\\" + browsedFile);
     deletedFile.delete();
     System.out.println("Deleted file iz: " + browsedFile);
   }
   else if(e.getSource() == deleteTemplate){
     //System.exit(0);
     //Delete the meme that you browsed to 
     File deletedFile = new File(blankMemeTemplateFolder + "\\" + memeTemplate);
     deletedFile.delete();
     System.out.println("Deleted file iz: " + memeTemplate);
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
      indexOfBrowsingMeme = 6;
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
    else if(option == "preview"){
      String directory = path;
      System.out.println(directory);
      File generatedMemesDirectory = new File(directory);
      String backgroundImage[] = generatedMemesDirectory.list();
      System.out.println(backgroundImage.length);
      System.out.println("previewing: " + backgroundImage[backgroundImage.length - 1]);
      this.setContentPane(new JPanel() {
      });
      try {
        add(new JLabel(new ImageIcon(path + backgroundImage[backgroundImage.length - 1])));
        pack();
        setVisible(true);
        return backgroundImage[backgroundImage.length-1];
        
      } catch (Exception e) {
        //TODO: handle exception
        System.out.println("preview error");
        indexOfBrowsingPresteMeme = 0;
      add(new JLabel(new ImageIcon(path + backgroundImage[backgroundImage.length - 1])));
      pack();
      setVisible(true);
      return backgroundImage[backgroundImage.length-1];
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
    System.out.println("raw = " + rawMeme);
    String[] format = rawMeme.split("\\.");
    newMemeFileFormat = format[1];
    System.out.println(newMemeFileFormat);
    image = ImageIO.read(new File(blankMemeTemplateFolder + rawMeme));
    memeHeight = image.getHeight();
    memeWidth = image.getWidth();

  //Brings up the GUI for building the meme
  JFrame memeBuildingFrame = new JFrame();
  JPanel memeBuildingPanel = new JPanel();

  memeBuildingFrame.setSize(425,580);
  memeBuildingPanel.setSize(425,580);
  memeBuildingFrame.setLocationRelativeTo(null);
  memeBuildingPanel.setLayout(null);

  JLabel typingInstructions = new JLabel("<html><strong>" +
          "Type in Requested Information in the Text Fields Below<br> (Press Enter After Inputting Information in Each Field Entry)</strong></html>",JLabel.LEFT);
  //JLabel typingInstructions = new JLabel("Type in the Requested Information in the Text Fields Below:");
  //This is how you do text field.  Just like with buttons an action listener is needed
  JLabel sizeOfFontLabel = new JLabel("Enter in the Font Size:");
  JTextField sizeOfFont = new JTextField("Enter in the Font Size:");
  sizeOfFont.setBackground(Color.YELLOW);
  JLabel captionLabel = new JLabel("Enter in Caption:");
  JTextField caption= new JTextField("Enter in Caption:");
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
      System.out.println(memeText);
    }
  });

  JLabel redBoxLabel = new JLabel("Enter in R value [0,255]:");
  JTextField redBox= new JTextField("Enter in R value [0,255]:");
  redBox.setBackground(Color.YELLOW);

  redBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent r){
      String RString = redBox.getText();
      redBox.setBackground(Color.WHITE);
      try {
        Red = Integer.parseInt(RString);
      } catch (Exception fonts) {
        //TODO: handle exception
        Red = 4;
      }
    }
  });

  memeBuildingPanel.add(redBox); //Each button/textbox needs to be added to the label

  JLabel greenBoxLabel = new JLabel("Enter in G value [0,255]:");
  JTextField greenBox= new JTextField("Enter in G value [0,255]:");
  greenBox.setBackground(Color.YELLOW);
  greenBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent g){
      String GString = greenBox.getText();
      greenBox.setBackground(Color.WHITE);
      try {
        Green = Integer.parseInt(GString); // Ok I know this is childish but ha gstring.  Typed Rstring and followed the pattern with green but then realized what I typed and had a little laugh.
      } catch (Exception e) {
        //TODO: handle exception
        Green = 2;
      }
    }
  });
  memeBuildingPanel.add(greenBox); //Each button/textbox needs to be added to the label

  JLabel blueBoxLabel = new JLabel("Enter in B value [0,255]:");
  JTextField blueBox= new JTextField("Enter in B value [0,255]:");
  blueBox.setBackground(Color.YELLOW);
  blueBox.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent b){
      String BString = blueBox.getText();
      blueBox.setBackground(Color.WHITE);
      try {
        Blue = Integer.parseInt(BString);
      } catch (Exception e) {
        //TODO: handle exception
      Blue = 0;
      }
    }
  });
  memeBuildingPanel.add(blueBox); //Each button/textbox needs to be added to the label

  JLabel xBoxLabel = new JLabel("Enter in X value < " + String.valueOf(memeWidth) + ":");
  JTextField xBox= new JTextField("Enter in X value < " + String.valueOf(memeWidth) + ":");
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
  JTextField yBox= new JTextField("Enter in Y value < " + String.valueOf(memeHeight) +":");
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

  JLabel TitleLabel = new JLabel("Enter in the Name of the Meme:");
  JTextField Title= new JTextField("Enter in the Name of the Meme:");
  Title.setBackground(Color.YELLOW);
  caption.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent t){
      newMemeFileName = Title.getText();
      Title.setBackground(Color.WHITE);
    }
  });
  memeBuildingPanel.add(Title); //Each button/textbox needs to be added to the label

  // JLabel FileLabel = new JLabel("Enter File Format png or jpg:");
  // JTextField File= new JTextField("File Format png or jpg:");
  // caption.addActionListener(new ActionListener(){
  //   public void actionPerformed(ActionEvent f){
  //     System.out.println(newMemeFileFormat);
  //   }
  // });
  // memeBuildingPanel.add(File); //Each button/textbox needs to be added to the label

  
  JButton select=new JButton("Submit");
  select.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(Red, Green, Blue);
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

  
  JButton save = new JButton("Store Partial");
  save.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
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
      if(Red > 255){Red = 255;}
      if(Red < 0){Red = 0;}
      if(Green > 255){Green = 255;}
      if(Green < 0){Green = 0;}
      if(Blue > 255){Blue = 255;}
      if(Blue < 0){Blue = 0;}
      if(topX > memeWidth){topX = memeWidth / 2;}
      if(topX < 0){topX = 1;}
      if(topY > memeHeight){topY = memeHeight / 2;}
      if(topY < 0){topY = 1;}
      if(fontSize <= 0){fontSize = 1;}
      Graphics g = image.getGraphics();
      g.setFont(g.getFont().deriveFont(fontSize));
      Color fontColor = new Color(Red, Green, Blue);
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
            // TODO Auto-generated catch block
            e1.printStackTrace();
            previewing = previewing - 1;
          }
        }
      }
    }
  });

  
  JButton preview=new JButton("Preview the Meme");
  preview.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      // if(previewing == 1){
      //   //setVisible(false);
      //   //remove(new JLabel(new ImageIcon(tempMemeTemplateFolder + newMemeFileName + "." + newMemeFileFormat)));
      //   PreMeme.dispose();
      //   System.out.println(previewing);
      // }
      // PreMeme = new JFrame();
      // PreMeme.setDefaultCloseOperation(PreMeme.DISPOSE_ON_CLOSE);
      // previewing = previewing+1;
      // PreMeme.setContentPane(new JPanel());
      // add(new JLabel(new ImageIcon(tempMemeTemplateFolder + newMemeFileName + "." + newMemeFileFormat)));
      // pack();
      // setVisible(true);
      try {
        BrowseWindow(tempMemeTemplateFolder, "preview");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
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
      Title.setBackground(Color.YELLOW);
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
          System.out.println("writing: " + tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
          ImageIO.write(image, newMemeFileFormat, new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
          image = ImageIO.read(new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));
          System.out.println("read: " + tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      //If more than one take second most recent and name it most recent
        //Seems to work but you cant undo twice
      else if(tempMeme.length > 1){
        //Get list of all files in directory 
        String[] partialMemes = blankmemet3mpFile.list();
        System.out.println(partialMemes[partialMemes.length-1]); //The newest file (You dont want that)
        System.out.println(partialMemes[partialMemes.length-2]); //The newest file (You want to undo back to this)
        //Make image = second most recent file
        try {
          image = ImageIO.read(new File(tempMemeTemplateFolder + partialMemes[partialMemes.length-2]));
          File undidMeme = new File(partialMemes[partialMemes.length-1]);
          undidMeme.delete();
          previewing = previewing + 1;
          ImageIO.write(image, newMemeFileFormat, new File(tempMemeTemplateFolder + newMemeFileName + String.valueOf(previewing) + "." + newMemeFileFormat));

        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        //Delete most recent file

        //Increment previewing
        previewing = previewing + 1;
        //System.out.println("This feature is not working");
      }
    }
  });

  close = new JButton("Exit Window");
  close.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      memeBuildingFrame.dispose();
    }
  });

  JButton startFromScratch = new JButton("Restart meme building");
  startFromScratch.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent f){
      previewing = 0;
      Red = 255;
      Green = 255;
      Blue = 255;
      topX = 1;
      topY = 1;
      indexOfBrowsingMeme = 0;
      indexOfBrowsingPresteMeme = 0;
      fontSize = 1;
      memeText = "";
      newMemeFileName = "default";
      TextCaption = "";
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
  //memeBuildingPanel.add(FileLabel);
  memeBuildingPanel.add(sizeOfFontLabel);
  memeBuildingPanel.add(captionLabel);
  memeBuildingPanel.add(startFromScratch);
  memeBuildingPanel.add(close);


  memeBuildingFrame.getContentPane().setBackground(Color.BLUE);
  memeBuildingFrame.add(memeBuildingPanel, BorderLayout.CENTER);
  memeBuildingFrame.setDefaultCloseOperation(memeBuildingFrame.EXIT_ON_CLOSE);
  memeBuildingFrame.setTitle("Much Great Meme Builder");
//  memeBuildingFrame.pack();
  memeBuildingFrame.setVisible(true);

  //Set Location
  typingInstructions.setBounds(30,20,570,35);
  redBoxLabel.setBounds(30,60,200,30);
  redBox.setBounds(225,60,150,30);

  greenBoxLabel.setBounds(30,100,200,30);
  greenBox.setBounds(225,100,150,30);

  blueBoxLabel.setBounds(30,140,200,30);
  blueBox.setBounds(225,140,150,30);

  xBoxLabel.setBounds(30,180,200,30);
  xBox.setBounds(225,180,150,30);

  yBoxLabel.setBounds(30,220,200,30);
  yBox.setBounds(225,220,150,30);

  TitleLabel.setBounds(30,260,200,30);
  Title.setBounds(225,260,150,30);

  // FileLabel.setBounds(30,300,200,30);
  startFromScratch.setBounds(225,300,150,30);

  sizeOfFontLabel.setBounds(30,340,200,30);
  sizeOfFont.setBounds(225,340,150,30);

  captionLabel.setBounds(30,380,200,30);
  caption.setBounds(225,380,150,30);

  undo.setBounds(55,420,150,30);
  save.setBounds(220,420,150,30);
  preview.setBounds(55,460,150,30);
  select.setBounds(220,460,150,30);
  close.setBounds(55,500,315,30);

  //Set Border
  undo.setBorder(BorderFactory.createLineBorder(Color.black));
  save.setBorder(BorderFactory.createLineBorder(Color.black));
  preview.setBorder(BorderFactory.createLineBorder(Color.black));
  select.setBorder(BorderFactory.createLineBorder(Color.black));
  startFromScratch.setBorder(BorderFactory.createLineBorder(Color.black));
  close.setBorder(BorderFactory.createLineBorder(Color.black));

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
