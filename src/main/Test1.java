package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Test1 extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		JButton btnStart = new JButton("Start");
		buttonPanel.add(btnStart);
		
		JButton btnParameters = new JButton("Parameters");
		buttonPanel.add(btnParameters);
		
		btnParameters.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ParamFrame paramFrame = new ParamFrame("Parametry");
				
				paramFrame.setVisible(true);
				
				
			}
		});
		
		JButton btnRandomize = new JButton("Randomize");
		buttonPanel.add(btnRandomize);
		
		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		JPanel paramPanel = new JPanel();
		contentPane.add(paramPanel, BorderLayout.WEST);
	}

}
