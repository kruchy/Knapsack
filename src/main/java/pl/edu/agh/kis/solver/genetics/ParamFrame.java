package pl.edu.agh.kis.solver.genetics;

import pl.edu.agh.kis.solver.Knapsack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ParamFrame extends JFrame {

	private JPanel contentPane;
	Knapsack knap;
	private JTextField numItem;
	private JTextField valItem;
	private JTextField weightItems;
	private Scanner scan;
	private JTextField size;

	/**
	 * Create the frame.
	 */
	public ParamFrame(String name, final Knapsack knap) {
		super(name);
		
		this.knap = knap;
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
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblKnapsackSize = new JLabel("Knapsack size");
		GridBagConstraints gbc_lblKnapsackSize = new GridBagConstraints();
		gbc_lblKnapsackSize.anchor = GridBagConstraints.WEST;
		gbc_lblKnapsackSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnapsackSize.gridx = 1;
		gbc_lblKnapsackSize.gridy = 0;
		panel.add(lblKnapsackSize, gbc_lblKnapsackSize);
		
		final JSlider knapSize = new JSlider();
		knapSize.setValue(1);
		knapSize.setMinimum(1);
		GridBagConstraints gbc_knapSize = new GridBagConstraints();
		gbc_knapSize.insets = new Insets(0, 0, 5, 0);
		gbc_knapSize.anchor = GridBagConstraints.NORTHWEST;
		gbc_knapSize.gridx = 2;
		gbc_knapSize.gridy = 0;
		panel.add(knapSize, gbc_knapSize);
	//	knapSize.setPaintLabels(true);
		knapSize.setPaintTicks(true);
		knapSize.setMajorTickSpacing(2);
		knapSize.setMinorTickSpacing(2);
		knapSize.setSnapToTicks(true);
		knapSize.setMaximum(100);
		knapSize.setMinimum(1);
		
		knapSize.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				JSlider zrodlo = (JSlider) arg0.getSource();

				size.setText(Integer.toString(knapSize.getValue()));
			}
		});
		
		
		size = new JTextField();
		GridBagConstraints gbc_size = new GridBagConstraints();
		gbc_size.insets = new Insets(0, 0, 5, 0);
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.gridx = 2;
		gbc_size.gridy = 1;
		panel.add(size, gbc_size);
		size.setColumns(10);
		size.setText(Integer.toString(knapSize.getMinimum()));
		
		JLabel lblNewLabel = new JLabel("Number of items");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		numItem = new JTextField();
		numItem.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_numItem = new GridBagConstraints();
		gbc_numItem.insets = new Insets(0, 0, 5, 0);
		gbc_numItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_numItem.gridx = 2;
		gbc_numItem.gridy = 2;
		panel.add(numItem, gbc_numItem);
		numItem.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Values");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		valItem = new JTextField();
		GridBagConstraints gbc_valItem = new GridBagConstraints();
		gbc_valItem.insets = new Insets(0, 0, 5, 0);
		gbc_valItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_valItem.gridx = 2;
		gbc_valItem.gridy = 3;
		panel.add(valItem, gbc_valItem);
		valItem.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Weights");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		weightItems = new JTextField();
		GridBagConstraints gbc_weightItems = new GridBagConstraints();
		gbc_weightItems.insets = new Insets(0, 0, 5, 0);
		gbc_weightItems.fill = GridBagConstraints.HORIZONTAL;
		gbc_weightItems.gridx = 2;
		gbc_weightItems.gridy = 4;
		panel.add(weightItems, gbc_weightItems);
		weightItems.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		/* APPLY BUTTON */
		JButton btnApply_1 = new JButton("Apply");
		GridBagConstraints gbc_btnApply_1 = new GridBagConstraints();
		gbc_btnApply_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply_1.gridx = 0;
		gbc_btnApply_1.gridy = 16;
		panel.add(btnApply_1, gbc_btnApply_1);
		
		btnApply_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				applyChanges(knap, knapSize);
				
				
				
			}

			void applyChanges(final Knapsack knap, JSlider knapSize) {
				knap.maxWeight = knapSize.getValue();
				knap.values = getValues();
				knap.weights = getWeights();
				dispose();
			}

			int[] getValues() {
				scan = new Scanner(valItem.getText());
				int num = Integer.parseInt(numItem.getText());
 				int tmpVals[] = new int[ num ];
				for(int i = 0 ; i < num; i++)
				{
					tmpVals[i] = scan.nextInt();
				}
				
				return tmpVals;
			}

			int[] getWeights() {
				scan = new Scanner(weightItems.getText());
				int num = Integer.parseInt(numItem.getText());		 				
				int tmpWeis[] = new int[ num];
				for(int i = 0 ; i < num; i++)
				{
					tmpWeis[i] = scan.nextInt();
				}
				scan.close();
				return tmpWeis;
			}
		});
		
		/*END APPLY BUTTON */ 
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 16;
		panel.add(btnReset, gbc_btnReset);
		
		JButton btnClose = new JButton("Close");
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 16;
		panel.add(btnClose, gbc_btnClose);
		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
	}

}
