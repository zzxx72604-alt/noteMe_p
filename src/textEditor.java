import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class textEditor extends JFrame implements ActionListener {
    JPanel textPanel;
    JTextArea textArea;
    JLabel font;
    JMenuBar menuBar;
    //file
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem importItem;
    //edit
    JMenu editMenu;
    JMenuItem Theme;
    JMenuItem Background;
    //Playlist
    JMenu playlist;

    textEditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //off ah ng appliection still run
        this.setTitle("NoteMe");
        this.setSize(500,500);
        this.setLayout(new BorderLayout());   // decide where the componet go
        this.setLocationRelativeTo(null); //window spawn nv kondal

        textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(
                BorderFactory.createEmptyBorder(10,10,10,10)
        );
        textArea = new JTextArea(); //like declear the verible like using object in Jtext to verible text
        textArea.setPreferredSize(new Dimension(450,450));
        textArea.setFont(new Font("Arial",Font.PLAIN,20));

        textPanel.add(textArea, BorderLayout.CENTER);
        //bar
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);  //add the open item to the menu
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
        this.add(textPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==openItem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Textfiles","text");
            fileChooser.setFileFilter(filter);
            //fileChooser.setFileFilter(new FileNameExtensionFilter("Textfiles","text"));
            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;
                try{
                    fileIn = new Scanner(file);
                    if(file.isFile()){
                        while(fileIn.hasNextLine()){
                            String line = fileIn.nextLine()+"\n";
                            textArea.append(line); //append add something already have
                        }
                    }
                }
                catch(FileNotFoundException e1){
                    e1.printStackTrace();
                }
                finally{
                    if(fileIn != null ){
                        fileIn.close();
                    }
                }
            }
        }
        if(e.getSource()==saveItem){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                File file ;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                }
                catch(FileNotFoundException e1){
                    e1.printStackTrace();  //explain the error detail
                }
                finally{
                    if(fileOut != null){
                        fileOut.close();
                    }
                }
            }
        }
        if(e.getSource()==exitItem){
            System.exit(0);
        }
    }
}
