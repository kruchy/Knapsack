// parowki sa smaczniejsze, gdy zastapimy je czyms innym
// zdecyduj siê czy zastêpujesz je innym daniem czy czymœ innym
package main;

import genetics.*;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import java.awt.GridBagConstraints;
import java.util.Scanner;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.prism.Graphics;

import java.awt.BorderLayout;

public class Test1 extends JFrame {

	ParamFrame paramFrame;
	Population pop;
	Knapsack knap;
	private Scanner scan;
	private JTextField size;
	private JTextField itemNum;
	private JTextField vals;
	private JTextField weis;
	private JTextField popSize;

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
		setBounds(100, 100, 600, 400);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 0;

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		JMenuItem mntmStart = new JMenuItem("Start");
		mntmStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		popupMenu.add(mntmStart);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			}
		});
		popupMenu.add(mntmSave);

		JMenuItem mntmRandomize = new JMenuItem("Randomize");
		mntmRandomize.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

			}
		});
		popupMenu.add(mntmRandomize);

		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// "czy na pewno chcesz zamkn¹æ czy coœ"
				dispose();
			}
		});
		popupMenu.add(mntmClose);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedMain = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedMain, BorderLayout.CENTER);

		JPanel param = new JPanel();
		tabbedMain.addTab("Parameters", null, param, null);
		GridBagLayout gbl_param = new GridBagLayout();
		gbl_param.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_param.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_param.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_param.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		param.setLayout(gbl_param);

		JLabel lblKnapsackSize = new JLabel("Knapsack size");
		GridBagConstraints gbc_lblKnapsackSize = new GridBagConstraints();
		gbc_lblKnapsackSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblKnapsackSize.gridx = 1;
		gbc_lblKnapsackSize.gridy = 0;
		param.add(lblKnapsackSize, gbc_lblKnapsackSize);

		final JSlider knapSize = new JSlider();
		knapSize.setValue(1);
		GridBagConstraints gbc_knapSize = new GridBagConstraints();
		gbc_knapSize.insets = new Insets(0, 0, 5, 0);
		gbc_knapSize.gridx = 2;
		gbc_knapSize.gridy = 0;
		param.add(knapSize, gbc_knapSize);
		knapSize.setMajorTickSpacing(1);
		knapSize.setMinorTickSpacing(1);
		knapSize.setMaximum(50);
		knapSize.setPaintTicks(true);
		knapSize.setPaintTrack(true);
		size = new JTextField();
		size.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_size = new GridBagConstraints();
		gbc_size.insets = new Insets(0, 0, 5, 0);
		size.setColumns(2);
		
		gbc_size.fill = GridBagConstraints.HORIZONTAL;
		gbc_size.gridx = 2;
		gbc_size.gridy = 1;
		param.add(size, gbc_size);

		JLabel lblNumberOfItems = new JLabel("Number of items");
		GridBagConstraints gbc_lblNumberOfItems = new GridBagConstraints();
		gbc_lblNumberOfItems.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfItems.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfItems.gridx = 1;
		gbc_lblNumberOfItems.gridy = 2;
		param.add(lblNumberOfItems, gbc_lblNumberOfItems);

		itemNum = new JTextField();
		GridBagConstraints gbc_itemNum = new GridBagConstraints();
		gbc_itemNum.insets = new Insets(0, 0, 5, 0);
		gbc_itemNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_itemNum.gridx = 2;
		gbc_itemNum.gridy = 2;
		param.add(itemNum, gbc_itemNum);
		itemNum.setColumns(10);

		JLabel lblValues = new JLabel("Values");
		GridBagConstraints gbc_lblValues = new GridBagConstraints();
		gbc_lblValues.insets = new Insets(0, 0, 5, 5);
		gbc_lblValues.anchor = GridBagConstraints.EAST;
		gbc_lblValues.gridx = 1;
		gbc_lblValues.gridy = 3;
		param.add(lblValues, gbc_lblValues);

		vals = new JTextField();
		GridBagConstraints gbc_vals = new GridBagConstraints();
		gbc_vals.insets = new Insets(0, 0, 5, 0);
		gbc_vals.fill = GridBagConstraints.HORIZONTAL;
		gbc_vals.gridx = 2;
		gbc_vals.gridy = 3;
		param.add(vals, gbc_vals);
		vals.setColumns(10);

		JLabel lblWeis = new JLabel("Weights");
		GridBagConstraints gbc_lblWeis = new GridBagConstraints();
		gbc_lblWeis.anchor = GridBagConstraints.EAST;
		gbc_lblWeis.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeis.gridx = 1;
		gbc_lblWeis.gridy = 4;
		param.add(lblWeis, gbc_lblWeis);

		weis = new JTextField();
		GridBagConstraints gbc_weis = new GridBagConstraints();
		gbc_weis.insets = new Insets(0, 0, 5, 0);
		gbc_weis.fill = GridBagConstraints.HORIZONTAL;
		gbc_weis.gridx = 2;
		gbc_weis.gridy = 4;
		param.add(weis, gbc_weis);
		weis.setColumns(10);
		
		JLabel lblPopulationSize = new JLabel("Population size");
		GridBagConstraints gbc_lblPopulationSize = new GridBagConstraints();
		gbc_lblPopulationSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblPopulationSize.anchor = GridBagConstraints.EAST;
		gbc_lblPopulationSize.gridx = 1;
		gbc_lblPopulationSize.gridy = 5;
		param.add(lblPopulationSize, gbc_lblPopulationSize);
		
		popSize = new JTextField();
		popSize.setText("50");
		GridBagConstraints gbc_popSize = new GridBagConstraints();
		gbc_popSize.insets = new Insets(0, 0, 5, 0);
		gbc_popSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_popSize.gridx = 2;
		gbc_popSize.gridy = 5;
		param.add(popSize, gbc_popSize);
		popSize.setColumns(10);

		JButton btnApply = new JButton("Apply");
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 0;
		gbc_btnApply.gridy = 6;
		param.add(btnApply, gbc_btnApply);

		
		
		itemNum.setText("0");
		vals.setText("0");
		weis.setText("0");
		
		btnApply.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				applyChanges(knap, knapSize);

			}
		});
		
		JButton btnReset = new JButton("Reset");
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 6;
		param.add(btnReset, gbc_btnReset);
		
		JButton btnRandom = new JButton("Random");
		GridBagConstraints gbc_btnRandom = new GridBagConstraints();
		gbc_btnRandom.gridx = 2;
		gbc_btnRandom.gridy = 6;
		param.add(btnRandom, gbc_btnRandom);
		JPanel graph = new JPanel();
		tabbedMain.addTab("Graph", null, graph, null);

		btnReset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				weis.setText("0");
				vals.setText("0");
				itemNum.setText("0");
				
			}
		});
		
		
	}

	/*
	 * KONIEC KONSTRUKTORA
	 * TODO konstruktor
	 */
	
	
	
	
	
	
	
	void applyChanges(final Knapsack knap, JSlider knapSize) {
		knap.maxWeight = knapSize.getValue();
		
		knap.values = getValues();
		knap.weights = getWeights();
		FitnessCalc.setMaxWeight(100);
        int maxWeight = FitnessCalc.getMaxWeight();
        double tolerance = 0.05;
        Individual.setDefaultGeneLength(knap.values.length);
        Individual.values = knap.values ;
        Individual.weights = knap.weights;
        Population myPop = new Population(Integer.parseInt(popSize.getText()), true);
        
	}

	int[] getValues() {
		scan = new Scanner(vals.getText());
		int num = Integer.parseInt(itemNum.getText());
		int tmpVals[] = new int[num];
		for (int i = 0; i < num && scan.hasNext(); i++) {
			tmpVals[i] = scan.nextInt();
		}

		return tmpVals;
	}

	int[] getWeights() {
		scan = new Scanner(weis.getText());
		int num = Integer.parseInt(itemNum.getText());
		int tmpWeis[] = new int[num];
		for (int i = 0; i < num && scan.hasNext(); i++) {
			tmpWeis[i] = scan.nextInt();
		}
		scan.close();
		return tmpWeis;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
