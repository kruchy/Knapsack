package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ParamFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ParamFrame(String name) {
		super(name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{101, 66, 200, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblKnapsackSize = new JLabel("Knapsack size");
		GridBagConstraints gbc_lblKnapsackSize = new GridBagConstraints();
		gbc_lblKnapsackSize.anchor = GridBagConstraints.WEST;
		gbc_lblKnapsackSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnapsackSize.gridx = 1;
		gbc_lblKnapsackSize.gridy = 0;
		panel.add(lblKnapsackSize, gbc_lblKnapsackSize);
		
		JSlider knapSize = new JSlider();
		knapSize.setValue(1);
		knapSize.setMinimum(1);
		GridBagConstraints gbc_knapSize = new GridBagConstraints();
		gbc_knapSize.insets = new Insets(0, 0, 5, 0);
		gbc_knapSize.anchor = GridBagConstraints.NORTHWEST;
		gbc_knapSize.gridx = 2;
		gbc_knapSize.gridy = 0;
		panel.add(knapSize, gbc_knapSize);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
	}

}
