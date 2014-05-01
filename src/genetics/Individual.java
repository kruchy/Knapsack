package genetics;
public class Individual {
	
	
	/*
	 * Stwierdzilem ze static tutaj by siad³
	 */
    static int defaultGeneLength = 64;
    private byte[] genes;
    public static int[] values;
    public static int[] weights;
    // Cache
    private float fitness = 0;
    public int id; 
    public Individual()
    {
    	genes = new byte[defaultGeneLength];
    }
    public Individual(int id)
    {
    	genes = new byte[defaultGeneLength];
    	this.id = id;
    }
   
    
    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
        }
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
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

    public void setValues(int[] vals)
    {
    	values = vals;
    }

    public void setWeights(int[] weis)
    {
    	weights = weis;
    }
    
    /* Public methods */
    public int size() {
        return genes.length;
    }

    public float getFitness(int id) {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this,id);
        }
        return fitness;
    }

    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}