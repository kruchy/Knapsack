package pl.edu.agh.kis.solver.genetics;

public class Individual {


    static int defaultGeneLength = 64;
    private byte[] genes;

    public static int[] values;
    public static int[] weights;
    // Cache
    private float fitness = 0;
    public int id;

    public Individual() {
        genes = new byte[defaultGeneLength];
    }

    public Individual(int id) {
        genes = new byte[defaultGeneLength];
        this.id = id;
    }

    public void generateIndividual() {

        java.util.Arrays.fill(genes, (byte) Math.round(Math.random()));
    }

    public void generateZeroIndividual() {

        java.util.Arrays.fill(genes, (byte) 0);
    }

    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
        values = new int[defaultGeneLength];
        weights = new int[defaultGeneLength];
    }

    public byte getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public void setValues(int[] vals) {
        values = vals;
    }

    public void setWeights(int[] weis) {
        weights = weis;
    }

    public int size() {
        return genes.length;
    }

    public float getFitness(int id) {
//        if (fitness == 0) {
//            fitness = FitnessCalc.getFitness(this);
//        }
        return 0;
    }

    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}