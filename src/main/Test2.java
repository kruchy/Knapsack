// parowki sa smaczniejsze, gdy zastapimy je czyms innym
// zdecyduj siê czy zastêpujesz je innym daniem czy czymœ innym
package main;

import genetics.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class Test2 extends JFrame {

    private JPanel contentPane, buttonPanel;
    ParamFrame paramFrame;
    Population pop;
    Knapsack knap;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // utworzenie przeciążonej ramki Test1 w kolejce zdarzeń
        EventQueue.invokeLater(new Runnable() {
          
            public void run() {
                Test2 frame = new Test2();
                frame.initContentPane();
                frame.initButtonPanel();
                frame.prepareWorkspace();
            }
        });
    }

    /**
     * przeciążona ramka - menu główne aplikacji
     */
    public Test2() {
        // inicjacja plecaka
        knap = new Knapsack();

        //ustawienie parametrów ramki
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setVisible(true);
    }

    void initContentPane() {
        // ustawienie parametrów panelu z zawartością
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // dołączenie panelu z przyciskami 
        buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
    }

    void initButtonPanel() {
        JButton startButton = new JButton("Start");
        buttonPanel.add(startButton);
        startButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });

        JButton paramButton = new JButton("Parametry");
        buttonPanel.add(paramButton);
        paramButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent arg0) {
                paramFrame = new ParamFrame("Parametry", knap);
                paramFrame.setVisible(true);
            }
        });


        JButton randomizeButton = new JButton("Randomize");
        buttonPanel.add(randomizeButton);
        randomizeButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent arg0) {
                //TODO dodać akcję
            }
        });
    }

    void prepareWorkspace() {
        JPanel eastPanel = new JPanel();
        contentPane.add(eastPanel, BorderLayout.EAST);

        JPanel paramPanel = new JPanel();
        contentPane.add(paramPanel, BorderLayout.WEST);

        JEditorPane editorPane = new JEditorPane();
        contentPane.add(editorPane, BorderLayout.CENTER);
    }
}