public class NaiveGaussian {
    private int n;
    private double[][] coefficients;
    private double[] constants;
    private double[] solution;

    /**
     * Constructor for NaiveGaussian
     * @param n # of variables in the system of linear equations
     * @param coefficients matrix consisting of all coefficients from the system of linear equations
     * @param constants array consisting of all constants on right hand side of the system of linear equations
     */
    public NaiveGaussian(int n, double[][] coefficients, double[] constants) {
        this.n = n;
        this.coefficients = coefficients;
        this.constants = constants;
        solution = new double[n];
    }

    /**
     * Goes through forward elimination algorithm
     */
    public void forwardElimination() {
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double mult = coefficients[i][k] / coefficients[k][k];
                for (int j = k; j < n; j++) {
                    coefficients[i][j] = coefficients[i][j] - mult * coefficients[k][j];
                }
                constants[i] = constants[i] - mult * constants[k];
            }
        }
    }

    /**
     * Goes through back substitution algorithm assuming forward elimination was already done
     * @return array of solutions
     */
    public double[] backSubstitution() {
        solution[n-1] = constants[n-1] / coefficients[n-1][n-1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = constants[i];
            for (int j = i + 1; j < n; j++) {
                sum = sum - coefficients[i][j] * solution[j];
            }
            solution[i] = sum / coefficients[i][i];
        }
        return solution;
    }
}
