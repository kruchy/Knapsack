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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Canvas;
import java.awt.GridBagLayout;

public class Test1 extends JFrame {

	private JPanel contentPane;

	ParamFrame paramFrame;
	Population pop;
	Knapsack knap;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 frame = new Test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test1() {
		knap = new Knapsack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		JButton btnStart = new JButton("Start");
		buttonPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JButton btnParameters = new JButton("Parameters");
		buttonPanel.add(btnParameters);
		
		btnParameters.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				paramFrame = new ParamFrame("Parametry",knap);
				
				paramFrame.setVisible(true);
				
				
			}
		});
		
		
		JButton btnRandomize = new JButton("Randomize");
		buttonPanel.add(btnRandomize);
		
		
		
		
		btnRandomize.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	
		JButton btnClose = new JButton("Close");
		buttonPanel.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(paramFrame != null) paramFrame.dispose();
				dispose();
			}
		});
		
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		JPanel paramPanel = new JPanel();
		contentPane.add(paramPanel, BorderLayout.WEST);
		
		JEditorPane editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		
	
	}

}
