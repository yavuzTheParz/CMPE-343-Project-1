import java.util.*;

/**
 * Group28HighSchool
 * <p>
 * This program provides a simple console-based high school menu system with two main functionalities:
 * <ul>
 *   <li>Statistical Information about an Array (median, arithmetic, geometric, harmonic mean)</li>
 *   <li>Distance between Two Arrays (Manhattan, Euclidean, and Cosine similarity)</li>
 * </ul>
 * 
 * The program demonstrates input validation, recursion handling, and basic mathematical computations.
 * 
 * @author 
 * @version 1.0
 */
public class C {

    /**
     * Entry point of the program. Displays the main menu and handles user navigation.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("------------------------------");
            System.out.println("       HIGH SCHOOL MENU");
            System.out.println("------------------------------");
            System.out.println("1) Statistical Information about an Array");
            System.out.println("2) Distance between Two Arrays");
            System.out.println("3) Exit to Main Menu");
            System.out.println("------------------------------");
            System.out.print("Your choice: ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                arrayStats();
            } else if (choice.equals("2")) {
                arrayDistances();
            } else if (choice.equals("3")) {
                System.out.println("Returning to Main Menu...");
                break;
            } else {
                System.out.println("Invalid choice! Press Enter to try again.");
                sc.nextLine();
            }
        }
    }

    /**
     * Prints several blank lines to simulate clearing the console screen.
     */
    public static void clearScreen() {
        for (int i = 0; i < 20; i++) System.out.println();
    }

    // ---------------------------------------------------------------
    // C1 - Statistical Information about an Array
    // ---------------------------------------------------------------

    /**
     * Handles the "Statistical Information about an Array" feature.
     * <p>
     * Prompts the user for array size and elements, then calculates:
     * <ul>
     *   <li>Median</li>
     *   <li>Arithmetic mean</li>
     *   <li>Geometric mean</li>
     *   <li>Harmonic mean (recursively, with memory check)</li>
     * </ul>
     */
    public static void arrayStats() {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        // Get valid array size
        while (n <= 0) {
            System.out.print("Enter array size (>0): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) System.out.println("Size must be positive!");
            } catch (Exception e) {
                System.out.println("Please enter a valid integer!");
            }
        }

        // Recursion memory warning for large arrays
        if (n > 10000) {
            System.out.println("Warning: Large array size may cause memory issues due to recursion!");
            System.out.println("Press Enter to continue or Ctrl+C to stop...");
            sc.nextLine();
        }

        double[] arr = new double[n];

        // Get array elements with validation
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Enter element " + (i + 1) + ": ");
                try {
                    double val = Double.parseDouble(sc.nextLine());
                    if (Math.abs(val) > 1e9) {
                        System.out.println("Value too large! Please enter between -1e9 and 1e9.");
                    } else {
                        arr[i] = val;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
        }

        Arrays.sort(arr);

        // Compute statistics
        double median = findMedian(arr);
        double arithmetic = avg(arr);
        double geometric = geoMean(arr);
        double harmonic;

        try {
            harmonic = n / harmonicSum(arr, 0);
        } catch (StackOverflowError | OutOfMemoryError e) {
            System.out.println("Memory Error: Recursive harmonic mean calculation failed!");
            harmonic = 0;
        }

        // Prevent invalid results
        if (Double.isInfinite(arithmetic) || Double.isNaN(arithmetic)) arithmetic = 0;
        if (Double.isInfinite(geometric) || Double.isNaN(geometric)) geometric = 0;
        if (Double.isInfinite(harmonic) || Double.isNaN(harmonic)) harmonic = 0;

        // Display results
        System.out.println("\n--- Statistical Results ---");
        System.out.printf("Median: %.4f\n", median);
        System.out.printf("Arithmetic Mean: %.4f\n", arithmetic);
        System.out.printf("Geometric Mean: %.4f\n", geometric);
        System.out.printf("Harmonic Mean: %.4f\n", harmonic);

        System.out.println("\nPress Enter to return...");
        sc.nextLine();
    }

    /**
     * Finds the median of a sorted array.
     *
     * @param arr Sorted array of doubles
     * @return Median value
     */
    public static double findMedian(double[] arr) {
        int n = arr.length;
        if (n % 2 == 1)
            return arr[n / 2];
        else
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
    }

    /**
     * Calculates the arithmetic mean of a numeric array.
     *
     * @param arr Array of doubles
     * @return Arithmetic mean
     */
    public static double avg(double[] arr) {
        double sum = 0;
        for (double x : arr) sum += x;
        return sum / arr.length;
    }

    /**
     * Calculates the geometric mean of a numeric array.
     *
     * @param arr Array of doubles
     * @return Geometric mean
     */
    public static double geoMean(double[] arr) {
        double prod = 1;
        for (double x : arr) prod *= x;
        return Math.pow(prod, 1.0 / arr.length);
    }

    /**
     * Recursively calculates the harmonic sum (used in harmonic mean calculation).
     *
     * @param arr Array of doubles
     * @param i   Current index
     * @return Partial harmonic sum
     */
    public static double harmonicSum(double[] arr, int i) {
        if (i == arr.length) return 0;
        double term = 1.0 / arr[i];
        return term + harmonicSum(arr, i + 1);
    }

    // ---------------------------------------------------------------
    // C2 - Distance between Two Arrays
    // ---------------------------------------------------------------

    /**
     * Handles the "Distance between Two Arrays" feature.
     * <p>
     * Prompts the user to enter two arrays of equal size, then calculates:
     * <ul>
     *   <li>Manhattan Distance</li>
     *   <li>Euclidean Distance</li>
     *   <li>Cosine Similarity</li>
     * </ul>
     */
    public static void arrayDistances() {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        // Get valid array size
        while (n <= 0) {
            System.out.print("Enter array size (>0): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) System.out.println("Size must be positive!");
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
            }
        }

        int[] a1 = new int[n];
        int[] a2 = new int[n];

        System.out.println("Enter FIRST array (0-9):");
        for (int i = 0; i < n; i++) {
            a1[i] = readInt(sc, i);
        }

        System.out.println("Enter SECOND array (0-9):");
        for (int i = 0; i < n; i++) {
            a2[i] = readInt(sc, i);
        }

        double manhattan = 0, euclidean = 0;
        double dot = 0, mag1 = 0, mag2 = 0;

        // Compute distances
        for (int i = 0; i < n; i++) {
            manhattan += Math.abs(a1[i] - a2[i]);
            euclidean += Math.pow(a1[i] - a2[i], 2);
            dot += a1[i] * a2[i];
            mag1 += a1[i] * a1[i];
            mag2 += a2[i] * a2[i];
        }

        euclidean = Math.sqrt(euclidean);

        // Display results
        System.out.println("\n--- Distance Results ---");
        System.out.printf("Manhattan Distance: %.4f\n", manhattan);
        System.out.printf("Euclidean Distance: %.4f\n", euclidean);

        if (mag1 == 0 || mag2 == 0) {
            System.out.println("Cosine similarity undefined (zero magnitude)");
        } else {
            double cosine = dot / (Math.sqrt(mag1) * Math.sqrt(mag2));
            System.out.printf("Cosine Similarity: %.4f\n", cosine);
        }

        System.out.println("\nPress Enter to return...");
        sc.nextLine();
    }

    /**
     * Reads an integer input between 0 and 9 from the user with validation.
     *
     * @param sc Scanner for user input
     * @param i  Index of the current element
     * @return Valid integer between 0 and 9
     */
    public static int readInt(Scanner sc, int i) {
        while (true) {
            System.out.print("Element " + (i + 1) + ": ");
            try {
                int val = Integer.parseInt(sc.nextLine());
                if (val < 0 || val > 9) {
                    System.out.println("Please enter between 0 and 9!");
                } else {
                    return val;
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }
}