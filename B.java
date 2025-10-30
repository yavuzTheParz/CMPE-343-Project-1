import java.util.*;
import java.util.regex.*;

/**
 * GroupXX class — A console-based interactive program.
 *
 * <p>Provides two main modules:
 * <ul>
 *   <li><b>Prime Numbers:</b> Generates and compares prime lists using three sieve algorithms.</li>
 *   <li><b>Step-by-step Expression Evaluation:</b> Evaluates arithmetic expressions step by step.</li>
 * </ul>
 *
 * <p>Includes robust input validation, error handling, and timing analysis for algorithms.
 */
public class GroupXX
{
    /**
     * Main entry point — Displays the main menu and handles user choices.
     *
     * <p><b>How it works:</b>
     * Uses an infinite loop to display options to the user.
     * Depending on the selected option, the corresponding submenu is executed.
     * Exits the loop when the user chooses to return to the main menu.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            clear();
            System.out.println("=== Secondary School Menu ===");
            System.out.println("[1] Prime Numbers");
            System.out.println("[2] Step-by-step Evaluation of Expression");
            System.out.println("[3] Return to Main Menu");
            System.out.print("Select an option: ");
            String c = sc.nextLine().trim();

            switch (c)
            {
                case "1" -> primeNumbersMenu(sc);
                case "2" -> stepByStepMenu(sc);
                case "3" ->
                {
                    System.out.println("Returning to Main Menu...");
                    pause(sc);
                    return;
                }
                default ->
                {
                    System.out.println("Invalid option.");
                    pause(sc);
                }
            }
        }
    }

    /* ----------------------------------------------------------
       PRIME NUMBERS PART
       ---------------------------------------------------------- */

    /**
     * Displays the prime number generation menu and compares
     * the performance of three sieve algorithms.
     *
     * <p><b>How it works:</b>
     * <ul>
     *   <li>Prompts the user for an integer n ≥ 12.</li>
     *   <li>Runs Sieve of Eratosthenes, Sundaram, and Atkin separately.</li>
     *   <li>Measures execution time for each algorithm.</li>
     *   <li>Handles NumberFormatException and OutOfMemoryError safely.</li>
     * </ul>
     *
     * @param sc Scanner object for user input
     */
    private static void primeNumbersMenu(Scanner sc)
    {
        System.out.print("Enter an integer n (n >= 12): ");
        String input = sc.nextLine().trim();

        int n;
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Overflow or invalid number! Please enter a valid integer within range.");
            pause(sc);
            return;
        }

        if (n < 12) {
            System.out.println("n must be ≥ 12.");
            pause(sc);
            return;
        }

        List<Long> e = null, s = null, a = null;
        double eT = 0, sT = 0, aT = 0;

        try {
            long t1 = System.nanoTime();
            e = sieveEratosthenes(n);
            long t2 = System.nanoTime();
            eT = (t2 - t1) / 1e6;
        } catch (OutOfMemoryError oom) {
            System.out.println("Eratosthenes: Not enough heap memory.");
        }

        try {
            long t1 = System.nanoTime();
            s = sieveSundaram(n);
            long t2 = System.nanoTime();
            sT = (t2 - t1) / 1e6;
        } catch (OutOfMemoryError oom) {
            System.out.println("Sundaram: Not enough heap memory.");
        }

        try {
            long t1 = System.nanoTime();
            a = sieveAtkin(n);
            long t2 = System.nanoTime();
            aT = (t2 - t1) / 1e6;
        } catch (OutOfMemoryError oom) {
            System.out.println("Atkin: Not enough heap memory.");
        }

        print("Eratosthenes", e, eT);
        print("Sundaram", s, sT);
        print("Atkin", a, aT);
        pause(sc);
    }

    /**
     * Prints the results of a prime generation algorithm.
     *
     * <p><b>How it works:</b>
     * Displays the first three and last two primes from the list.
     * If the list is null, prints an appropriate message.
     * Also displays the total execution time in milliseconds.
     *
     * @param name name of the sieve algorithm
     * @param p list of primes (may be null)
     * @param time execution time in ms
     */
    private static void print(String name, List<Long> p, double time)
    {
        System.out.println("\n--- " + name + " ---");
        if (p == null) {
            System.out.println("Result skipped due to insufficient memory.");
        } else if (p.size() >= 5) {
            System.out.println("First 3 primes: " + p.subList(0, 3));
            System.out.println("Last 2 primes:  " + p.subList(p.size() - 2, p.size()));
        } else {
            System.out.println(p);
        }
        System.out.printf("Execution time: %.3f ms%n", time);
    }

    /**
     * Generates prime numbers up to n using the Sieve of Eratosthenes.
     *
     * <p><b>How it works:</b>
     * Initializes a boolean array representing potential primes.
     * Marks multiples of each prime as non-prime.
     * Returns all indices still marked as true.
     *
     * @param n upper limit
     * @return list of primes up to n
     */
    private static List<Long> sieveEratosthenes(long n)
    {
        boolean[] f = new boolean[(int) (n + 1)];
        Arrays.fill(f, true);
        f[0] = f[1] = false;
        for (long i = 2; i * i <= n; i++)
        {
            if (f[(int) i])
            {
                for (long j = i * i; j <= n; j += i)
                    f[(int) j] = false;
            }
        }
        List<Long> p = new ArrayList<>();
        for (long i = 2; i <= n; i++)
            if (f[(int) i])
                p.add(i);
        return p;
    }

    /**
     * Generates primes using the Sieve of Sundaram.
     *
     * <p><b>How it works:</b>
     * Marks numbers of the form i + j + 2ij as non-prime.
     * Remaining unmarked numbers correspond to odd primes.
     *
     * @param n upper limit
     * @return list of primes up to n
     */
    private static List<Long> sieveSundaram(long n)
    {
        long m = (n - 2) / 2;
        boolean[] marked = new boolean[(int) (m + 1)];
        for (long i = 1; i <= m; i++)
        {
            for (long j = i; (i + j + 2 * i * j) <= m; j++)
                marked[(int) (i + j + 2 * i * j)] = true;
        }
        List<Long> p = new ArrayList<>();
        p.add(2L);
        for (long i = 1; i <= m; i++)
            if (!marked[(int) i])
                p.add(2 * i + 1);
        return p;
    }

    /**
     * Generates primes using the Sieve of Atkin.
     *
     * <p><b>How it works:</b>
     * Applies quadratic rules for residues modulo 12
     * to mark candidate numbers as primes, then eliminates
     * multiples of squares.
     *
     * @param limit upper bound
     * @return list of primes up to limit
     */
    private static List<Long> sieveAtkin(long limit)
    {
        boolean[] s = new boolean[(int) (limit + 1)];
        long r = (long) Math.sqrt(limit);
        for (long x = 1; x <= r; x++)
        {
            for (long y = 1; y <= r; y++)
            {
                long n = 4 * x * x + y * y;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5))
                    s[(int) n] ^= true;
                n = 3 * x * x + y * y;
                if (n <= limit && n % 12 == 7)
                    s[(int) n] ^= true;
                n = 3 * x * x - y * y;
                if (x > y && n <= limit && n % 12 == 11)
                    s[(int) n] ^= true;
            }
        }
        for (long i = 5; i <= r; i++)
            if (s[(int) i])
                for (long j = i * i; j <= limit; j += i * i)
                    s[(int) j] = false;

        List<Long> p = new ArrayList<>(List.of(2L, 3L));
        for (long i = 5; i <= limit; i++)
            if (s[(int) i])
                p.add(i);
        return p;
    }

    /* ----------------------------------------------------------
       STEP-BY-STEP EXPRESSION EVALUATION
       ---------------------------------------------------------- */

    /**
     * Handles the menu for expression evaluation.
     *
     * <p><b>How it works:</b>
     * Prompts the user to enter an arithmetic expression.
     * Performs validation (digits, parentheses, operators).
     * If valid, normalizes and evaluates the expression step by step.
     *
     * @param sc Scanner for user input
     */
    private static void stepByStepMenu(Scanner sc) {
        while (true) {
            System.out.print("Enter a mathematical expression (digits and + - x : ( ) allowed): ");
            String raw = sc.nextLine();
            if (raw.trim().isEmpty()) {
                System.out.println("re-enter a valid expression.");
                continue;
            }

            String expr = raw.replaceAll("x", "*").replaceAll("X", "*")
                    .replaceAll(":", "/").replaceAll("\\s+", "");

            if (!expr.matches("[0-9+\\-*/().]+") ||
                    !balanced(expr) ||
                    expr.matches(".*[+\\-*/]{2,}.*") ||
                    expr.matches("^[+*/].*") ||
                    expr.contains("+0") || expr.contains("-0") ||
                    expr.matches(".*\\(\\).*") ||
                    expr.matches(".*\\)\\d.*") || expr.matches(".*\\d\\(.*"))
            {
                System.out.println("re-enter a valid expression.");
                continue;
            }

            try {
                System.out.println("Normalized: " + pretty(expr));
                evaluateStep(expr);
                break;
            } catch (Exception e) {
                System.out.println("re-enter a valid expression.");
            }
        }
        pause(sc);
    }

    /**
     * Checks whether parentheses in the expression are balanced.
     *
     * <p><b>How it works:</b>
     * Iterates through each character and increments/decrements
     * a counter for '(' and ')'. Returns true if the counter ends at zero.
     *
     * @param s expression string
     * @return true if parentheses are balanced
     */
    private static boolean balanced(String s) {
        int b = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') b++;
            if (c == ')') b--;
            if (b < 0) return false;
        }
        return b == 0;
    }

    /**
     * Evaluates a mathematical expression step by step, printing each simplification.
     *
     * <p><b>How it works:</b>
     * - Normalizes operators (+-, --, etc.)  
     * - Simplifies innermost parentheses first  
     * - Repeatedly applies arithmetic operations with precedence  
     * - Prints the expression after each step until fully simplified
     *
     * @param expr normalized arithmetic expression
     */
    private static void evaluateStep(String expr) {
        expr = normalize(expr);
        System.out.println(pretty(expr));

        while (!expr.matches("[+\\-]?\\d+")) {
            int open = expr.lastIndexOf('(');
            if (open != -1) {
                int close = findMatchingParen(expr, open);
                String inner = expr.substring(open + 1, close);

                String cur = inner;
                while (!cur.matches("[+\\-]?\\d+")) {
                    String next = stepSimplify(cur);
                    if (next.equals(cur)) break;
                    expr = expr.substring(0, open + 1) + next + expr.substring(close);
                    expr = normalize(expr);
                    System.out.println(pretty(expr));
                    cur = next;
                    close = findMatchingParen(expr, open);
                }

                String res = fullySimplify(cur);
                expr = expr.substring(0, open) + res + expr.substring(close + 1);
                expr = normalize(expr);
                System.out.println(pretty(expr));
            } else {
                String next = stepSimplify(expr);
                if (next.equals(expr)) break;
                expr = normalize(next);
                System.out.println(pretty(expr));
            }
        }

        expr = normalize(expr);
        if (expr.startsWith("+")) expr = expr.substring(1);
        System.out.println("= " + pretty(expr));
    }

    /**
     * Finds the matching closing parenthesis for a given open parenthesis index.
     *
     * @param s expression string
     * @param openIndex index of '('
     * @return index of corresponding ')'
     */
    private static int findMatchingParen(String s, int openIndex) {
        int depth = 0;
        for (int i = openIndex; i < s.length(); i++) {
            if (s.charAt(i) == '(') depth++;
            else if (s.charAt(i) == ')') depth--;
            if (depth == 0) return i;
        }
        return s.length() - 1;
    }

    /**
     * Simplifies one operation in the expression according to operator precedence.
     *
     * @param expr expression string
     * @return simplified expression
     * @throws ArithmeticException if division by zero occurs
     */
    private static String stepSimplify(String expr) {
        List<String> tok = tokenize(expr);
        int opIndex = findOp(tok);
        if (opIndex == -1) return expr;
        long a = Long.parseLong(tok.get(opIndex - 1));
        long b = Long.parseLong(tok.get(opIndex + 1));
        long r = switch (tok.get(opIndex)) {
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            case "+" -> a + b;
            case "-" -> a - b;
            default -> throw new RuntimeException();
        };
        tok.set(opIndex - 1, String.valueOf(r));
        tok.remove(opIndex);
        tok.remove(opIndex);
        return rebuild(tok);
    }

    /**
     * Fully simplifies an expression until no operations remain.
     *
     * @param expr expression to simplify
     * @return final numeric result as a string
     */
    private static String fullySimplify(String expr) {
        String cur = expr;
        while (true) {
            String next = stepSimplify(cur);
            if (next.equals(cur)) return cur;
            cur = normalize(next);
        }
    }

    /**
     * Finds the next operator to process, respecting operator precedence.
     *
     * @param t list of tokens
     * @return index of next operator or -1 if none found
     */
    private static int findOp(List<String> t) {
        for (int i = 0; i < t.size(); i++)
            if (t.get(i).equals("*") || t.get(i).equals("/")) return i;
        for (int i = 0; i < t.size(); i++)
            if (t.get(i).equals("+") || t.get(i).equals("-")) return i;
        return -1;
    }

    /**
     * Tokenizes an expression string into numbers and operators.
     *
     * @param s expression string
     * @return list of tokens
     */
    private static List<String> tokenize(String s) {
        List<String> t = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if ((c == '+' || c == '-') && (i == 0 || "+-*/(".indexOf(s.charAt(i - 1)) >= 0)) {
                int j = i + 1;
                while (j < s.length() && (Character.isDigit(s.charAt(j)))) j++;
                t.add(s.substring(i, j));
                i = j;
            } else if ("*/+-".indexOf(c) >= 0) {
                t.add(String.valueOf(c));
                i++;
            } else if (Character.isDigit(c)) {
                int j = i;
                while (j < s.length() && (Character.isDigit(s.charAt(j)))) j++;
                t.add(s.substring(i, j));
                i = j;
            } else i++;
        }
        return t;
    }

    /**
     * Rebuilds a string from a list of tokens.
     *
     * @param t token list
     * @return reconstructed expression
     */
    private static String rebuild(List<String> t) {
        StringBuilder sb = new StringBuilder();
        for (String s : t) sb.append(s);
        return sb.toString();
    }

    /**
     * Normalizes expressions by replacing redundant operators.
     *
     * <p>Examples: "++" → "+", "+-" → "-", "--" → "+", etc.
     *
     * @param s expression
     * @return normalized expression
     */
    private static String normalize(String s) {
        String old;
        do {
            old = s;
            s = s.replaceAll("\\+\\+", "+")
                    .replaceAll("\\+-", "-")
                    .replaceAll("-\\+", "-")
                    .replaceAll("--", "+");
        } while (!s.equals(old));
        return s;
    }

    /**
     * Adds spacing for better readability.
     *
     * @param s expression
     * @return pretty-printed string
     */
    private static String pretty(String s) {
        return s.replaceAll("([+\\-*/()])", " $1 ").replaceAll("\\s+", " ").trim();
    }

    /**
     * Clears the console by printing new lines.
     */
    private static void clear() {
        System.out.print("\n".repeat(30));
    }

    /**
     * Pauses the console until user presses Enter.
     *
     * @param sc scanner object
     */
    private static void pause(Scanner sc) {
        System.out.println("\nPress ENTER to continue...");
        sc.nextLine();
    }
}
