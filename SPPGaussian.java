public class SPPGaussian {
    private int n;
    private double[][] coefficients;
    private double[] constants;
    private double[] solution;
    private int[] index;

    /**
     * Constructor for SPPGausian
     * @param n # of variables in the system of linear equations
     * @param coefficients matrix consisting of all coefficients from the system of linear equations
     * @param constants array consisting of all constants on right hand side of the system of linear equations
     */
    public SPPGaussian(int n, double[][] coefficients, double[] constants) {
        this.n = n;
        this.coefficients = coefficients;
        this.constants = constants;
        solution = new double[n];
        index = new int[n];

        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
    }

    /**
     * Goes through forward elimination algorithm
     */
    public void forwardElimination() {
        // Initialize scaling vector
        double[] scaling = new double[n];
        for (int i = 0; i < n; i++) {
            double scalingMax = 0;
            for (int j = 0; j < n; j++) {
                scalingMax = Math.max(scalingMax, Math.abs(coefficients[i][j]));
            }
            scaling[i] = scalingMax;
        }

        // Find greatest ratio
        for (int k = 0; k < n - 1; k++) {
            double ratioMax = 0;
            int maxInd = k;

            for (int i = k; i < n; i++) {
                double ratio = Math.abs(coefficients[index[i]][k] / scaling[index[i]]); // Calculate ratio
                if (ratio > ratioMax) {
                    ratioMax = ratio;
                    maxInd = i;
                }
            }

            // Swap(index[maxInd], index[k])
            int temp = index[maxInd];
            index[maxInd] = index[k];
            index[k] = temp;

            for (int i = k + 1; i < n; i++) {
                double mult = coefficients[index[i]][k] / coefficients[index[k]][k];
                for (int j = k + 1; j < n; j++) {
                    coefficients[index[i]][j] = coefficients[index[i]][j] - mult * coefficients[index[k]][j];
                }
                constants[index[i]] = constants[index[i]] - mult * constants[index[k]];
            }
        }
    }

    /**
     * Goes through back substitution algorithm assuming forward elimination was already done
     * @return array of solutions
     */
    public double[] backSubstitution() {
        solution[n-1] = constants[index[n-1]] / coefficients[index[n-1]][n-1];

        for (int i = n - 2; i >= 0; i--) {
            double sum = constants[index[i]];
            for (int j = i + 1; j < n; j++) {
                sum = sum - coefficients[index[i]][j] * solution[j];
            }
            solution[i] = sum / coefficients[index[i]][i];
        }
        return solution;
    }
}
