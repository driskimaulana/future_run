package view.userintefaces;

/**
 * Filename : GameMenu.java
 * Programmer : D'Riski Maulana
 * Date : 10 June 2022
 * Email : driskimaulana@upi.edu
 * Website : driskimaulana.c120.me
 * Deskripsi : JFrame class to display menu
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import presenter.ProsesTexperiences;
import view.entities.PlayerProfile;
import view.utils.Constants.GameState.STATE;

public class GameMenu extends JPanel implements ActionListener {
    
    // button
    public Rectangle playButton = new Rectangle(250, 300, 100, 50);
    public Rectangle exitButton = new Rectangle(250, 400, 100, 50);

    // table
    private static final String[] COL_NAMES = {"USERNAME", "ADAPT", "FALL"};
    private DefaultTableModel model = new DefaultTableModel(COL_NAMES, 0);
    private JTable table = new JTable(model);

    GameWindow gw;

    // data from database
    private ProsesTexperiences texperiences;

    public GameMenu()
    {
        // use boxlayout for layout manager
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // create new frame
        gw = new GameWindow();


        // create title
        JLabel labelTitle = new JLabel("The Survive Hop");
        labelTitle.setFont(new Font("Courier New", Font.BOLD, 32));

        labelTitle.setAlignmentX(this.CENTER_ALIGNMENT);
        add(labelTitle);

        // get data from database
        texperiences = new ProsesTexperiences();
        
        try {
            texperiences.prosesData();
            for (int i = 0; i < texperiences.getSize(); i++) {
                String username = texperiences.getUsername(i);
                int adapt = texperiences.getAdapt(i);
                int fall = texperiences.getFall(i);

                // insert data from database to table
                model.addRow(new String[]{username, Integer.toString(adapt), Integer.toString(fall)});
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        // show tabel texperiences
        table.setPreferredScrollableViewportSize(new Dimension(20, 50));
        JScrollPane jsp = new JScrollPane(table);
        jsp.setMaximumSize(new Dimension(300, 100));
        jsp.setViewportView(table);
        
        add(jsp);

        // input username
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Courier New", Font.BOLD, 16));
        labelUsername.setAlignmentX(this.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(10));
        add(labelUsername);
        add(Box.createVerticalStrut(5));

        // textfield for username
        JTextField textfieldUsername = new JTextField();
        textfieldUsername.setSize(new Dimension(50, 50));
        textfieldUsername.setPreferredSize(new Dimension(200, 25));
        textfieldUsername.setMaximumSize(textfieldUsername.getPreferredSize());
        add(textfieldUsername);

        // warning message
        JLabel labelEmptyWarning = new JLabel("username tidak boleh kosong!");
        labelEmptyWarning.setForeground(Color.red);
        labelEmptyWarning.setFont(new Font("Arial", Font.ITALIC, 8));
        labelEmptyWarning.setAlignmentX(this.CENTER_ALIGNMENT);

        labelEmptyWarning.setVisible(false);
        add(labelEmptyWarning);

        // button menu
        JPanel buttonPanel = new JPanel();

        // create button
        buttonPanel.setLayout(new GridLayout(1, 0, 5, 5));

        JButton buttonPlay = new JButton("Play");
        buttonPlay.setPreferredSize(new Dimension(100, 50));
        buttonPlay.setMaximumSize(buttonPlay.getPreferredSize());
        // button action
        buttonPlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(textfieldUsername.getText().length() == 0)
                {
                    labelEmptyWarning.setVisible(true);
                }else {
                    labelEmptyWarning.setVisible(false);

                    PlayerProfile player;

                    // searching for inputted username
                    boolean isExist = false;
                    texperiences = new ProsesTexperiences();

                    try {
                        texperiences.prosesData();
                        
                        int i = 0;
                        while (i < texperiences.getSize() && isExist == false) {
                            if(textfieldUsername.getText().equals(texperiences.getUsername(i)))
                            {
                                // if username is exist, than load game with that username data
                                player = new PlayerProfile(texperiences.getId(i), texperiences.getUsername(i), texperiences.getFall(i), texperiences.getAdapt(i));
                                gw.setVisible(false);
                                gw.dispose();
                                GameScreen.state = STATE.PLAYING;
                                GameScreen gs = new GameScreen(player);
                                isExist = true;
                            }

                            i++;
                        }

                        if(isExist == false)
                        {
                            // if username is new, than create new profile
                            player = new PlayerProfile(textfieldUsername.getText());
                            System.out.println("new");
                            gw.setVisible(false);
                            gw.dispose();
                            GameScreen.state = STATE.PLAYING;
                            GameScreen gs = new GameScreen(player);
                        }
    
                        
                        // System.out.println(username);
                    } catch (Exception ex) {
                        //TODO: handle exception
                        System.err.println(ex);
                    }
                    
                }
            }
        });

        // quit button
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.setPreferredSize(new Dimension(100, 50));
        buttonQuit.setMaximumSize(buttonQuit.getPreferredSize());
        buttonQuit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                // close on click
                System.exit(0);
            }
        });

        buttonPanel.add(buttonPlay);
        buttonPanel.add(buttonQuit);

        buttonPanel.setMaximumSize(new Dimension(200, 30));

        add(Box.createVerticalStrut(10));
        
        add(buttonPanel);
        
        JLabel labelSprites = new JLabel("All sprite assets belong to Brackeys.com");
        labelSprites.setFont(new Font("Courier New", Font.BOLD, 8));
        JLabel labelSound = new JLabel("Sound effects obtained from https://www.zapsplat.com");
        labelSound.setFont(new Font("Courier New", Font.BOLD, 8));
        labelSprites.setAlignmentX(this.CENTER_ALIGNMENT);
        labelSound.setAlignmentX(this.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(labelSprites);
        add(labelSound);
        add(Box.createVerticalStrut(20));
        gw.setVisible(true);
        gw.add(this);

        
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
    
    

}
