import java.io.*;
import java.util.Scanner;

public class Gaussian {
    public static void main(String[] args) throws IOException {
        // Go through command line arguments
        String arg;
        String fileName = "";
        Boolean sppFlag = false;

        // Look for .lin file
        int k = 0;
        while (k < args.length) {
            arg = args[k++];
            if (arg.endsWith(".lin")) {
                fileName = arg;
                break;
            }
            else {
                System.out.println("ERROR: File not found");
                System.exit(0);
            }
        }

        // Look for SPP flag
        k = 0;
        while (k < args.length) {
            arg = args[k++];
            if (arg.equals("-spp")) {
                sppFlag = true;
                break;
            }
        }

        // Read file
        Scanner sc = new Scanner(new File(fileName));
        int n = Integer.parseInt(sc.nextLine());
        double[][] coefficients = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coefficients[i][j] = Double.parseDouble(sc.next());
            }
        }
        double[] constants = new double[n];
        for (int i = 0; i < n; i++) {
            constants[i] = Double.parseDouble(sc.next());
        }

        // Run chosen algorithm
        double[] solution;
        if (sppFlag) {
            System.out.println("Running scaled partial pivoting");
            SPPGaussian spp = new SPPGaussian(n, coefficients, constants);
            spp.forwardElimination();
            solution = spp.backSubstitution();
        }
        else {
            System.out.println("Running naive gaussian elimination (Default)");
            NaiveGaussian naive = new NaiveGaussian(n, coefficients, constants);
            naive.forwardElimination();
            solution = naive.backSubstitution();
        }

        // Create output file
        String outFileName = fileName.substring(0, fileName.lastIndexOf('.')) + ".sol";
        FileWriter fstream = new FileWriter(outFileName);
        BufferedWriter out = new BufferedWriter(fstream);
        for (int i = 0; i < n; i++) {
            out.write(String.valueOf(solution[i]));
            out.write(" ");
        }
        out.close();
        System.out.println(".sol file created");
    }
}
