

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton.*;
import javax.swing.*;


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
 * 
 * 
 */
public class MemeGenerator implements ActionListener
{
  JButton browse;
  JButton upload;
  JButton preset;
  JLabel presetLable;
  JLabel browseLable;
  JLabel uploadLable;
  int x;
  int y;
  int z;

  public MemeGenerator(){
    JFrame memeFrame = new JFrame();
    JPanel memePanel = new JPanel();

    //Created buttons
    browse = new JButton("Browse the memes");
    upload = new JButton("Upload your own meme template");
    preset = new JButton("Choose a meme");

    //wait for button pressed then perform action associated with that button
    browse.addActionListener(this);
    upload.addActionListener(this);
    preset.addActionListener(this);

    //Final product might not need lable but if it does the lable exists
    browseLable = new JLabel("Browse " +String.valueOf(x));
    uploadLable = new JLabel("Upload " +String.valueOf(y));
    presetLable = new JLabel("Preset " +String.valueOf(z));



    memePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    //I like it better without the gridlayout
    //memePanel.setLayout(new GridLayout());

    memePanel.add(browse);
    memePanel.add(upload);
    memePanel.add(preset);
    memePanel.add(browseLable);
    memePanel.add(uploadLable);
    memePanel.add(presetLable);

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
      x++;
      System.out.println(277353);
      browseLable.setText("Browse" + String.valueOf(x));
    }
    else if(e.getSource() == preset){
      //Do the preset meme method
      y++;
      System.out.println(11037);
      presetLable.setText("Preset" + String.valueOf(y));
    }
    else if(e.getSource() == upload){
      //Do the upload a meme template method
      z++;
      System.out.println(177013);
      uploadLable.setText("Upload" + String.valueOf(z));
    }
    else{System.out.println("");}
    
  }
}
