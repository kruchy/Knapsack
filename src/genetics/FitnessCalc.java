package genetics;

public class FitnessCalc {

    static byte[] solution = new byte[64];
    private static int maxWeight;
	public static int getMaxWeight() {
		return maxWeight;
	}
	
	
	
	public static void setMaxWeight(int maxWeight) {
		FitnessCalc.maxWeight = maxWeight;
	}
    /* Public methods */
    // Set a candidate solution as a byte array
    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    // To make it easier we can use this method to set our candidate solution 
    // with string of 0s and 1s
    public static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        // Loop through each character of our string and save it in our byte 
        // array
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    /**
     * doda³em maxWeigth, do roboty funkcja oceny
     * @param individual
     * @param id
     * @param maxWeight
     * @return
     */
    // Calculate inidividuals fittness by comparing it to our candidate solution
    static float getFitness(Individual individual,int id) {
        int fitness = 0,value = 0,weight = 0;
        // Loop through our individuals genes and compare them to our cadidates
        for (int i = 0; i < individual.size(); i++) {
            	
        	if(individual.getGene(i) == 1 ){
        		value += Individual.values[i];
            	weight += Individual.weights[i];
        
        	}
        }
        if(weight > FitnessCalc.getMaxWeight()) return 0 ;
        return value;
    }
    
    // Get optimum fitness
    public static int getMaxFitness() {
        int maxFitness = 9999;
        return maxFitness;
    }
}