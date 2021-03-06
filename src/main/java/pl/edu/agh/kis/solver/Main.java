package pl.edu.agh.kis.solver;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pl.edu.agh.kis.solver.genetics.Algorithm;
import pl.edu.agh.kis.solver.genetics.FitnessCalculator;
import pl.edu.agh.kis.solver.genetics.ParamFrame;
import pl.edu.agh.kis.solver.genetics.SchedulePopulation;
import pl.edu.agh.kis.solver.genetics.model.Schedule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Main extends JFrame {


    double tolerance = 0.05;

    ParamFrame paramFrame;
    SchedulePopulation pop;
    Knapsack knap;
    JRadioButton rdbtnChanges, rdbtnNumberOfIterations;
    ButtonGroup bg;
    ChartPanel chart;
    XYSeries minFi, maxFi, avgFi;
    XYSeriesCollection dataset = new XYSeriesCollection();
    boolean initialized = false;
    private Scanner scan;
    private JTextField size;
    private JTextField itemNum;
    private JTextField vals;
    private JTextField weis;
    private JTextField popSize;
    private JTextArea solText;
    private JTextField iterationNum;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        knap = new Knapsack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
        gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane_1.gridx = 0;
        gbc_tabbedPane_1.gridy = 0;

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenuItem mntmStart = new JMenuItem("Start");
        menuBar.add(mntmStart);

        mntmStart.addActionListener(arg0 -> start());


        JMenuItem mntmClose = new JMenuItem("Close");
        menuBar.add(mntmClose);
        mntmClose.addActionListener(arg0 -> dispose());


        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(this, popupMenu);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedMain = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedMain, BorderLayout.CENTER);

        JPanel param = new JPanel();


        solText = new JTextArea();


        tabbedMain.addTab("Parameters", null, param, null);
        chart = new ChartPanel(ChartFactory.createXYLineChart("Max/Avg/Min Fittness", "x", "y", dataset, PlotOrientation.VERTICAL,
                true, // Show Legend
                true, // Use tooltips
                false));

        tabbedMain.addTab("Max/Avg/Min Fittness", chart);

        solText.setEditable(false);


        tabbedMain.addTab("Solution", null, solText, null);


        GridBagLayout gbl_param = new GridBagLayout();
        gbl_param.columnWidths = new int[]{0, 0, 0, 0};
        gbl_param.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_param.columnWeights = new double[]{0.0, 0.0, 1.0,
                Double.MIN_VALUE};
        gbl_param.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 1.0, Double.MIN_VALUE};
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
        knapSize.setMaximum(100);
        knapSize.setMinimum(1);
        knapSize.setPaintTicks(true);
        knapSize.setPaintTrack(true);
        size = new JTextField();
        size.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_size = new GridBagConstraints();
        gbc_size.insets = new Insets(0, 0, 5, 0);
        size.setColumns(2);
        size.setText("1");
        knapSize.addChangeListener(arg0 -> size.setText("" + knapSize.getValue()));

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

        JLabel lblPopulationSize = new JLabel("PopulationImpl size");
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

        JLabel lblIterations = new JLabel("Iterations");
        GridBagConstraints gbc_lblIterations = new GridBagConstraints();
        gbc_lblIterations.insets = new Insets(0, 0, 5, 5);
        gbc_lblIterations.anchor = GridBagConstraints.EAST;
        gbc_lblIterations.gridx = 1;
        gbc_lblIterations.gridy = 6;
        param.add(lblIterations, gbc_lblIterations);

        iterationNum = new JTextField();
        iterationNum.setText("100");
        GridBagConstraints gbc_iterationNum = new GridBagConstraints();
        gbc_iterationNum.insets = new Insets(0, 0, 5, 0);
        gbc_iterationNum.fill = GridBagConstraints.HORIZONTAL;
        gbc_iterationNum.gridx = 2;
        gbc_iterationNum.gridy = 6;
        param.add(iterationNum, gbc_iterationNum);
        iterationNum.setColumns(10);

        JButton btnApply = new JButton("Apply");
        GridBagConstraints gbc_btnApply = new GridBagConstraints();
        gbc_btnApply.insets = new Insets(0, 0, 5, 5);
        gbc_btnApply.gridx = 0;
        gbc_btnApply.gridy = 7;
        param.add(btnApply, gbc_btnApply);

        itemNum.setText("0");
        vals.setText("0");
        weis.setText("0");

        btnApply.addActionListener(arg0 -> {
            applyChanges(knap, knapSize);


            initialized = true;


        });

        JButton btnReset = new JButton("Reset");
        GridBagConstraints gbc_btnReset = new GridBagConstraints();
        gbc_btnReset.insets = new Insets(0, 0, 5, 5);
        gbc_btnReset.gridx = 1;
        gbc_btnReset.gridy = 7;
        param.add(btnReset, gbc_btnReset);

        JButton btnRandom = new JButton("Random");
        GridBagConstraints gbc_btnRandom = new GridBagConstraints();
        gbc_btnRandom.insets = new Insets(0, 0, 5, 0);
        gbc_btnRandom.gridx = 2;
        gbc_btnRandom.gridy = 7;
        param.add(btnRandom, gbc_btnRandom);

        JPanel panelRadio = new JPanel();
        GridBagConstraints gbc_panelRadio = new GridBagConstraints();
        gbc_panelRadio.fill = GridBagConstraints.BOTH;
        gbc_panelRadio.gridx = 2;
        gbc_panelRadio.gridy = 8;
        param.add(panelRadio, gbc_panelRadio);
        GridBagLayout gbl_panelRadio = new GridBagLayout();
        gbl_panelRadio.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_panelRadio.rowHeights = new int[]{0, 0};
        gbl_panelRadio.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelRadio.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panelRadio.setLayout(gbl_panelRadio);

        JLabel lblStopCondition = new JLabel("Stop condition");
        GridBagConstraints gbc_lblStopCondition = new GridBagConstraints();
        gbc_lblStopCondition.insets = new Insets(0, 0, 0, 5);
        gbc_lblStopCondition.gridx = 0;
        gbc_lblStopCondition.gridy = 0;
        panelRadio.add(lblStopCondition, gbc_lblStopCondition);

        rdbtnNumberOfIterations = new JRadioButton("Number of iterations");
        GridBagConstraints gbc_rdbtnNumberOfIterations = new GridBagConstraints();
        gbc_rdbtnNumberOfIterations.insets = new Insets(0, 0, 0, 5);
        gbc_rdbtnNumberOfIterations.gridx = 2;
        gbc_rdbtnNumberOfIterations.gridy = 0;
        panelRadio.add(rdbtnNumberOfIterations, gbc_rdbtnNumberOfIterations);


        rdbtnChanges = new JRadioButton("Changes");
        GridBagConstraints gbc_rdbtnChanges = new GridBagConstraints();
        gbc_rdbtnChanges.gridx = 3;
        gbc_rdbtnChanges.gridy = 0;
        panelRadio.add(rdbtnChanges, gbc_rdbtnChanges);
        JPanel graph = new JPanel();
        bg = new ButtonGroup();
        bg.add(rdbtnNumberOfIterations);
        bg.add(rdbtnChanges);
        btnRandom.addActionListener(arg0 -> randomize());


        btnReset.addActionListener(arg0 -> {
            weis.setText("0");
            vals.setText("0");
            itemNum.setText("0");

        });


    }

    void start() {
        dataset.removeAllSeries();
        solText.setText("");
        minFi = new XYSeries("Minimum fitness");
        maxFi = new XYSeries("Maximum fitness");
        avgFi = new XYSeries("Average fitness");
        int generationCount = 0;
        Schedule fittest;
        Schedule worst;
        scan = new Scanner(iterationNum.getText());
        float breakpoint1 = 0, breakpoint2 = 1;
        int iter = scan.nextInt();

        pop = new SchedulePopulation(Integer.parseInt(popSize.getText()));
        boolean condition;
        try {
            check();
        } catch (NotValidInput ignored) {
        }

        do {
            breakpoint1 = breakpoint2;
            fittest = pop.selectFittest(new FitnessCalculator());
            worst = pop.getWorst(new FitnessCalculator());
            breakpoint2 = fittest.getFitness();
            generationCount++;
            minFi.add(generationCount, worst.getFitness());
            maxFi.add(generationCount, fittest.getFitness());
            avgFi.add(generationCount, (worst.getFitness() + fittest.getFitness()) / 2);
            pop = Algorithm.evolvePopulation(pop);
            condition = (rdbtnNumberOfIterations.isSelected() ? generationCount <= iter : Math.abs(breakpoint2 - breakpoint1) > tolerance);
        } while (condition);
        Schedule tmp = pop.selectFittest(new FitnessCalculator());

        dataset.addSeries(minFi);
        dataset.addSeries(maxFi);
        dataset.addSeries(avgFi);

        chart = new ChartPanel(ChartFactory.createXYLineChart("Max/Avg/Min Fittness", "x", "y", dataset, PlotOrientation.VERTICAL,
                true, // Show Legend
                true, // Use tooltips
                false));
        int value = 0, weight = 0;
        for (int i = 0; i < Schedule.values.length; i++) {
//            if (tmp.getGene(i) == 1) {
//                value += Schedule.values[i];
//                weight += Schedule.weights[i];
//            }
        }
        solText.append("\nSolution found!\n" + "Generation: " + generationCount + "\nGenes:" + pop.selectFittest(new FitnessCalculator()) + "\n Total worth: " + value + "\n Total weight: " + weight);

    }

    void applyChanges(final Knapsack knap, JSlider knapSize) {

        knap.values = getValues();
        knap.weights = getWeights();
        knap.maxWeight = knapSize.getValue();
//        FitnessCalculator.setMaxWeight(knap.maxWeight);
        tolerance = 0.05;
        Schedule.values = knap.values;
        Schedule.weights = knap.weights;

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

    void check() throws NotValidInput {
        if (!initialized) throw new NotValidInput("Initialize parameters first");
        if (knap.values.length != knap.weights.length) throw new NotValidInput("Not valid length");
        for (int i = 0; i < knap.weights.length; i++) {
            if (knap.weights[i] == 0) throw new NotValidInput("Weight cannot be 0");
        }
//        if (pop.size() < 1) throw new NotValidInput("Too small population");

    }

    void randomize() {
        itemNum.setText("" + (int) (Math.random() * 150));
        int itemNumber = Integer.parseInt(itemNum.getText());
        String weisText = "", valsText = "";

        for (int i = 0; i < itemNumber; i++) {
            weisText += (int) ((Math.random() * 24) + 1) + " ";
            valsText += (int) (Math.random() * 50) + " ";
        }
        weis.setText(weisText);
        vals.setText(valsText);
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
