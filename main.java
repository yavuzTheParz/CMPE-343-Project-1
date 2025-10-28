import java.time.LocalDate;
import java.util.*;
/**
 * CMPE 343 Project 1 – Java Console Edition (ASCII UI).
 *
 * A)
 * B)
 * C)
 * 
 * D) Connect Four Game
 * <p>Features:
 * <ul>
 *   <li>Board sizes: 5x4, 6x5, 7x6</li>
 *   <li>Single-player vs Intelligent AI or two-players</li>
 *   <li>Win, draw, forfeit detection</li>
 * </ul>
 *
 * <p><b>Usage:</b> Compile with {@code javac ConnectFour.java} and run with {@code java ConnectFour}.
 *
 * @author Yavuz Selim Yaşar, Volkan Dinç, Kemal Ege İncereis, İdil Zeren Çoban
 * @version 1.0
 * @since 1.0
 */

public class main
{
    //Constant variables for console 
    private static final String HOME       = "\u001B[H";
    private static final String HIDE_CURSOR= "\u001B[?25l";
    private static final String SHOW_CURSOR= "\u001B[?25h";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String CLEAR = "\033[H\033[2J";
    private static char state = 'A';
    //constant scanner
    private static final Scanner sc =
        new Scanner(System.in);

    /**
     * Program entry point.
     *
     * @param args command-line arguments (unused)
     * 
     */
    public static void main(String[] args)
    {
        playAnimation();  
    }

    /**
    * Clears the console
    */ 
    public static void clearConsole() 
    {
     	System.out.print("\033[H\033[2J");
    	System.out.flush();
    }
    /** 
    * @see #A() 
    * @see #B()
    * @see #C()
    * @see #D()
    * Prints out the main the menu, handles the input and calls functions
    */
    public static void writeMenu()
    {

        System.out.println(ANSI_RESET);

        while(true)
        {
            boolean valid = false;
            System.out.print("[A] Primary School\n[B] Secondary School\n[C] High School,\n[D] University\n[E] Terminate"); 
            System.out.println();
            //take input
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            switch (s) 
            { 
            case "A":
                A();
                valid = true;
                break;
            case "B":
                B();
                valid = true;
                break;
            case "C":
                C();
                valid = true;
                break;
            case "D":
                D();
                valid = true;
                break;
            case "E":
                System.out.println("See you!");
                System.exit(0);
            default:
                clearConsole() ;
                System.out.println("Invalid input input, please try again with a valid input");
            
            }
            if(valid)
                break;                
        }
        
    }

    /**
     * A Menu
     */

    private static void A()
    {
        state = 'A';
        clearConsole();

        while (true) {
            System.out.println(getArt(state));
            //System.out.print(ANSI_RESET);
            System.out.println("=== PRIMARY SCHOOL ===");
            System.out.println("[1] Age and Zodiac Sign Detection");
            System.out.println("[2] Reverse the Words");
            System.out.println("[3] Return to Main Menu");
            String ch = readString("Your choice: ").trim();

            switch (ch) {
                case "1" -> { clearConsole(); opAgeAndZodiac(); }
                case "2" -> { clearConsole(); opReverseWordsRecursive(); }
                case "3" -> { clearConsole(); writeMenu(); }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            clearConsole();
        }
        
    }



    /**
     * B Menu
     * @see
     */
    private static void B()
    {
        state = 'B';
        clearConsole();
        
        while (true)
        {
            System.out.println(getArt(state));
            //System.out.print(ANSI_RESET);
            System.out.println("=== Secondary School Menu ===");
            System.out.println("[1] Prime Numbers");
            System.out.println("[2] Step-by-step Evaluation of Expression");
            System.out.println("[3] Return to Main Menu");
            System.out.print("Select an option: ");
            String c = sc.nextLine().trim();

            switch (c)
            {
                case "1":
                     primeNumbersMenu(sc);
                     break;
                case "2":
                     stepByStepMenu(sc);
                     break;
                case "3":
                     clearConsole();
                     writeMenu();
                    break;
                
                default:
                    System.out.println("Invalid option.");
                    pause(sc);
            }
            clearConsole();
        }
    }

    private static void C()
    {
        state = 'C';
        clearConsole();


        while (true) {
            System.out.println(getArt(state));
            //System.out.print(ANSI_RESET);
            System.out.println("===High School===");
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
                clearConsole();
                writeMenu();
                break;
            } else {
                System.out.println("Invalid choice! Press Enter to try again.");
                sc.nextLine();
            }
            clearConsole();
        }
    }

    /**
    * Starts the Connect Four session: asks for board size and game mode, then runs the main loop
    * until a terminal state (win/draw/forfeit) is reached.
    *
    * <p><b>Side effects:</b> Clears the console, prints the ASCII UI, and reads from {@code System.in}.
    *
    * @implNote The computer opponent plays with artificial intelligence powered by minimax algorithm (heuristic).
    */
    private static void D()
    {
        clearConsole();
        state = 'D';
        while (true) {
            System.out.println(getArt(state));
            System.out.print("[1] Connect Four\n[2] Return to Main Menu\n"); 
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            switch (s) {
                case "1":
                    startConnectFour();
                    break;
                case "2":
                    clearConsole();
                    writeMenu();
                    return;
                default:
                    System.out.println("Invalid input, try again.");
                    pause(sc);
            }
            clearConsole();

        }
    }

    //====================== A Functions ========================

    // === A1: AGE & ZODIAC (ELLE HESAPLAMA) ===
    private static void opAgeAndZodiac() {
        System.out.println("=== Age & Zodiac Sign Detection ===");
        int bYear  = readInt("Birth year (1-9999): ", 1, 9999);
        int bMonth = readInt("Birth month (1-12): ", 1, 12);
        int bDay   = readInt("Birth day (1-31): ", 1, 31);

        if (!isValidDate(bYear, bMonth, bDay)) {
            System.out.println("Invalid birth date!");
            pause(sc); clearConsole(); return;
        }

        // Bugünün tarihini sadece okumak için LocalDate.now kullanımı kabul.
        LocalDate today = LocalDate.now();
        int tYear = today.getYear();
        int tMonth = today.getMonthValue();
        int tDay = today.getDayOfMonth();

        if (compareDates(bYear, bMonth, bDay, tYear, tMonth, tDay) > 0) {
            System.out.println("Birth date cannot be in the future!");
            pause(sc); clearConsole(); return;
        }

        int[] ymd = diffYMD(bYear, bMonth, bDay, tYear, tMonth, tDay);
        String zodiac = zodiacOf(bDay, bMonth);

        System.out.printf("\nToday's date: %02d/%02d/%04d%n", tDay, tMonth, tYear);
        System.out.printf("Your age: %d years, %d months, %d days%n", ymd[0], ymd[1], ymd[2]);
        System.out.println("Your zodiac sign: " + zodiac);

        pause(sc); clearConsole();
    }

    // İki tarih arasındaki farkı (yıl, ay, gün) manuel hesaplar.
    // Dönüş: [years, months, days]
    private static int[] diffYMD(int y1, int m1, int d1, int y2, int m2, int d2) {
        // ödünç alma (borrow) mekanizması
        if (d2 < d1) {
            int pm = m2 - 1;
            int py = y2;
            if (pm == 0) { pm = 12; py--; }
            d2 += daysInMonth(py, pm);
            m2 -= 1;
        }
        if (m2 < m1) {
            m2 += 12;
            y2 -= 1;
        }
        int years = y2 - y1;
        int months = m2 - m1;
        int days = d2 - d1;
        return new int[]{years, months, days};
    }

    // Tarih karşılaştırması: t1 ? t2 (negatif: t1<t2, 0: eşit, pozitif: t1>t2)
    private static int compareDates(int y1, int m1, int d1, int y2, int m2, int d2) {
        if (y1 != y2) return Integer.compare(y1, y2);
        if (m1 != m2) return Integer.compare(m1, m2);
        return Integer.compare(d1, d2);
    }

    private static boolean isValidDate(int y, int m, int d) {
        if (y < 1 || y > 9999) return false;
        if (m < 1 || m > 12) return false;
        int dim = daysInMonth(y, m);
        return d >= 1 && d <= dim;
    }

    private static int daysInMonth(int y, int m) {
        return switch (m) {
            case 1,3,5,7,8,10,12 -> 31;
            case 4,6,9,11 -> 30;
            case 2 -> isLeap(y) ? 29 : 28;
            default -> 0;
        };
    }

    private static boolean isLeap(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }

    // Burçlar
    private static String zodiacOf(int day, int month) {
        return switch (month) {
            case 1  -> (day <= 19) ? "Capricorn"   : "Aquarius";
            case 2  -> (day <= 18) ? "Aquarius"    : "Pisces";
            case 3  -> (day <= 20) ? "Pisces"      : "Aries";
            case 4  -> (day <= 19) ? "Aries"       : "Taurus";
            case 5  -> (day <= 20) ? "Taurus"      : "Gemini";
            case 6  -> (day <= 20) ? "Gemini"      : "Cancer";
            case 7  -> (day <= 22) ? "Cancer"      : "Leo";
            case 8  -> (day <= 22) ? "Leo"         : "Virgo";
            case 9  -> (day <= 22) ? "Virgo"       : "Libra";
            case 10 -> (day <= 22) ? "Libra"       : "Scorpio";
            case 11 -> (day <= 21) ? "Scorpio"     : "Sagittarius";
            case 12 -> (day <= 21) ? "Sagittarius" : "Capricorn";
            default -> "Unknown";
        };
    }

    // === A2: REVERSE WORDS (RECURSIVE) ===
    private static void opReverseWordsRecursive() {
        System.out.println("=== Reverse the Words (recursive) ===");
        String input = readString("Enter text: ");
        System.out.println("\nOutput:");
        System.out.println(reverseWordsRecursive(input));
        pause(sc); clearConsole();
    }

    private static String reverseWordsRecursive(String s){
        return reverseHelper(s, 0, new StringBuilder(), new StringBuilder());
    }
    private static String reverseHelper(String s, int i, StringBuilder token, StringBuilder out){
        if (i == s.length()){
            if (token.length() > 0) out.append(processWord(token.toString()));
            return out.toString();
        }
        char ch = s.charAt(i);
        if (isLetterOrTurkish(ch)){
            token.append(ch);
        } else {
            if (token.length() > 0){
                out.append(processWord(token.toString()));
                token.setLength(0);
            }
            out.append(ch);
        }
        return reverseHelper(s, i + 1, token, out);
    }
    private static String processWord(String w){
        return (w.length() >= 2) ? reverseRec(w) : w;
    }
    private static String reverseRec(String s){
        if (s.length() <= 1) return s;
        return reverseRec(s.substring(1)) + s.charAt(0);
    }
    private static boolean isLetterOrTurkish(char ch){
        if (Character.isLetter(ch)) return true;
        return "çğıöşüÇĞİÖŞÜ".indexOf(ch) != -1;
    }

    // === UTILITIES ===
    private static String readString(String prompt){ System.out.print(prompt); return sc.nextLine(); }
    private static int readInt(String prompt, int min, int max){
        while(true){
            System.out.print(prompt);
            try{
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v >= min && v <= max) return v;
                System.out.printf("Please enter a value between %d and %d.%n", min, max);
            } catch(NumberFormatException e){
                System.out.println("Invalid number. Try again.");
            }
        }
    }


    //==================== B Functions =====================
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

    // Eratosthenes
    List<Long> e = null;
    double eT = 0;
    try {
        long t1 = System.nanoTime();
        e = sieveEratosthenes(n);
        long t2 = System.nanoTime();
        eT = (t2 - t1) / 1e6;
    } catch (OutOfMemoryError oom) {
        System.out.println("Eratosthenes: Not enough heap memory. Try a smaller n or run with a larger -Xmx.");
    }

    // Sundaram
    List<Long> s = null;
    double sT = 0;
    try {
        long t1 = System.nanoTime();
        s = sieveSundaram(n);
        long t2 = System.nanoTime();
        sT = (t2 - t1) / 1e6;
    } catch (OutOfMemoryError oom) {
        System.out.println("Sundaram: Not enough heap memory. Try a smaller n or run with a larger -Xmx.");
    }

    // Atkin
    List<Long> a = null;
    double aT = 0;
    try {
        long t1 = System.nanoTime();
        a = sieveAtkin(n);
        long t2 = System.nanoTime();
        aT = (t2 - t1) / 1e6;
    } catch (OutOfMemoryError oom) {
        System.out.println("Atkin: Not enough heap memory. Try a smaller n or run with a larger -Xmx.");
    }

    print("Eratosthenes", e, eT);
    print("Sundaram", s, sT);
    print("Atkin", a, aT);
    pause(sc);
}

// 2) print: null güvenli
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
       STEP-BY-STEP PART
       ---------------------------------------------------------- */
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

            // --- Validation Section ---
            if (!expr.matches("[0-9+\\-*/().]+") ||
                    !balanced(expr) ||
                    expr.matches(".*[+\\-*/]{2,}.*") ||
                    expr.matches("^[+*/].*") ||
                    expr.contains("+0") || expr.contains("-0") ||              // NEW: +0/-0 engeli
                    expr.matches(".*\\(\\).*") ||                              // NEW: boş parantez engeli
                    expr.matches(".*\\)\\d.*") || expr.matches(".*\\d\\(.*"))  // NEW: ()3 veya 3() engeli
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

    private static boolean balanced(String s) {
        int b = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') b++;
            if (c == ')') b--;
            if (b < 0) return false;
        }
        return b == 0;
    }

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

    private static int findMatchingParen(String s, int openIndex) {
        int depth = 0;
        for (int i = openIndex; i < s.length(); i++) {
            if (s.charAt(i) == '(') depth++;
            else if (s.charAt(i) == ')') depth--;
            if (depth == 0) return i;
        }
        return s.length() - 1;
    }

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

    private static String fullySimplify(String expr) {
        String cur = expr;
        while (true) {
            String next = stepSimplify(cur);
            if (next.equals(cur)) return cur;
            cur = normalize(next);
        }
    }

    private static int findOp(List<String> t) {
        for (int i = 0; i < t.size(); i++)
            if (t.get(i).equals("*") || t.get(i).equals("/")) return i;
        for (int i = 0; i < t.size(); i++)
            if (t.get(i).equals("+") || t.get(i).equals("-")) return i;
        return -1;
    }

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

    private static String rebuild(List<String> t) {
        StringBuilder sb = new StringBuilder();
        for (String s : t) sb.append(s);
        return sb.toString();
    }

    private static String normalize(String s) {
        return s.replaceAll("\\+\\+", "+").replaceAll("\\+\\-", "-")
                .replaceAll("\\-\\+", "-").replaceAll("\\-\\-", "+")
                .replaceAll("\\+0", "0").replaceAll("\\-0", "0");
    }

    private static String pretty(String expr) {
        return expr.replace("*", "x").replace("/", ":");
    }

    // ---------------------------------------------------------------
    // C1 - Statistical Information about an Array
    // ---------------------------------------------------------------
    public static void arrayStats() {
        int n = 0;

        while (n <= 0) {
            System.out.print("Enter array size (>0): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) System.out.println("Size must be positive!");
            } catch (Exception e) {
                System.out.println("Please enter a valid integer!");
            }
        }

        double[] arr;
        try
        {
            arr = new double[n];
        }
        catch (OutOfMemoryError e)
        {
            System.out.println("\nMemory Error: Not enough heap space for array of size " + n + ".");
            System.out.println("Try using a smaller size or increase JVM memory (-Xmx).");
            System.out.println("Press Enter to return...");
            sc.nextLine();
            return; // menüye dön
        }
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

        double median = findMedian(arr);
        double arithmetic = avg(arr);
        double geometric = geoMean(arr);
        double harmonic = 0;

        try {
            harmonic = n / harmonicSum(arr, 0);
        } catch (StackOverflowError | OutOfMemoryError e) {
            System.out.println("Memory Error: Recursive harmonic mean calculation failed!");
            harmonic = 0;
        }

        if (Double.isInfinite(arithmetic) || Double.isNaN(arithmetic)) arithmetic = 0;
        if (Double.isInfinite(geometric) || Double.isNaN(geometric)) geometric = 0;
        if (Double.isInfinite(harmonic) || Double.isNaN(harmonic)) harmonic = 0;

        System.out.println("\n--- Statistical Results ---");
        System.out.printf("Median: %.4f\n", median);
        System.out.printf("Arithmetic Mean: %.4f\n", arithmetic);
        System.out.printf("Geometric Mean: %.4f\n", geometric);
        System.out.printf("Harmonic Mean: %.4f\n", harmonic);

        System.out.println("\nPress Enter to return...");
        sc.nextLine();
    }

    // finds median (array is already sorted)
    public static double findMedian(double[] arr) {
        int n = arr.length;
        if (n % 2 == 1)
            return arr[n / 2];
        else
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
    }

    // calculates arithmetic mean
    public static double avg(double[] arr) {
        double sum = 0;
        for (double x : arr) sum += x;
        return sum / arr.length;
    }

    // calculates geometric mean
    public static double geoMean(double[] arr) {
        double prod = 1;
        for (double x : arr) prod *= x;
        return Math.pow(prod, 1.0 / arr.length);
    }

    // recursive helper for harmonic mean (kept as recursion)
    public static double harmonicSum(double[] arr, int i) {
        if (i == arr.length) return 0;
        double term = 1.0 / arr[i];
        return term + harmonicSum(arr, i + 1);
    }

    // ---------------------------------------------------------------
    // C2 - Distance between Two Arrays
    // ---------------------------------------------------------------
    public static void arrayDistances() {
        int n = 0;

        while (n <= 0) {
            System.out.print("Enter array size (>0): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) System.out.println("Size must be positive!");
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
            }
        }

        int[] a1, a2;
        try
        {
            a1 = new int[n];
            a2 = new int[n];
        }
        catch (OutOfMemoryError e)
        {
            System.out.println("\nMemory Error: Arrays too large for current heap.");
            System.out.println("Press Enter to return...");
            sc.nextLine();
            return;
        }


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

        for (int i = 0; i < n; i++) {
            manhattan += Math.abs(a1[i] - a2[i]);
            euclidean += Math.pow(a1[i] - a2[i], 2);
            dot += a1[i] * a2[i];
            mag1 += a1[i] * a1[i];
            mag2 += a2[i] * a2[i];
        }

        euclidean = Math.sqrt(euclidean);

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

    // checks integer input in range [0-9]
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

    /** 
    * Prints a set of string with a constant delay to play animation
    */
    private static void playAnimation() 
    {
        List<String> frames = getFrames();

        int fps = 10;
        long frameTime = 1000 / fps;

        
        try 
        {
            //Hide the cursor, make the color red
            System.out.print(HIDE_CURSOR);
            System.out.print(ANSI_RED);

            int i = 0;
            for (String frame : frames) {
                long t0 = System.nanoTime();

                System.out.print(CLEAR);
                System.out.print(HOME);

                if(i == 17)
                    System.out.print(ANSI_CYAN);

                //print the frame
                System.out.print(frame);
                System.out.flush();

                long elapsedMs = (System.nanoTime() - t0) / 1_000_000;
                long sleepMs = Math.max(0, frameTime - elapsedMs);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // break from the animation
                }
                i++;

            }

                // Animation is over
                System.out.print(ANSI_RESET);
                System.out.print(SHOW_CURSOR);
                System.out.println("by:  Yavuz Selim Yaşar, Volkan Dinç,  Kemal Ege İncereis,  İdil Zeren Çoban");
            } 
            finally
            {
                // Clean the terminal no matter what
                System.out.print(ANSI_RESET);
                System.out.print(SHOW_CURSOR);
                System.out.flush();
            }

            
            writeMenu();
    }




    private static String getArt(char c)
    {
        String s = "STRING";
        if(c == 'A')
        {
            System.out.print("\u001B[95m");
            s = 
            """
            
                 '                        *   '*
            *          .                           *
                   *       '                             *
              *                *                                 *
                                                        *
                                                              *

         .                      .
         .                      ;
         :                  - --+- -
         !           .          !
         |        .             .
         |_         +
      ,  | `.
--- --+-<#>-+- ---  --  -
      `._|_,'
         T
         |
         !
         :         . : 
         .       *
            """;
        }
        else if(state == 'B')
        {
            System.out.print(ANSI_CYAN);
            s = 
            """
                 oo   k
             x   __  x
            e  = \\  ---
                 /_  k!
                 k=1
            """;
        }
        else if(state == 'C')
        {
            System.out.print(ANSI_GREEN);
            s= 
            """
⠀⠀⠀⠀⠀⠀⢀⣤⣶⣶⣶⣶⣦⣤⣀⠀⠀⠀⠀⠀
⠀⠀⢀⣤⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀
⠀⢠⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⠿⠿⢿⣿⣿⣷⡀⠀  ___“Whoa.”_____
⠀⢸⣿⡿⠋⠁⠀⠀⠀⠉⠉⠀⠀⠀⠀⠈⢹⣿⡇⠀  /
⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⣿⡇⠀ /
⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀
⠀⢸⣿⣠⣤⣶⣶⣶⣦⣀⣀⣴⣶⣶⣶⣤⣄⣿⡇⡀
⣿⣿⣿⠻⣿⣿⣿⣿⣿⠟⠻⣿⣿⣿⣿⣿⠟⣿⣿⣿
⣿⣿⣿⠀⠈⠉⠛⠋⠉⠀⠀⠉⠙⠛⠉⠁⠀⣿⣿⣿
⠙⢿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠃
⠀⠸⣿⣧⠀⠀⠀⢀⣀⣀⣀⣀⡀⠀⠀⠀⣼⣿⠇⠀
⠀⠀⠙⢿⣷⣄⠀⠈⠉⠉⠉⠉⠁⠀⣠⣾⡿⠃⠀⠀
⠀⠀⠀⢸⣿⣿⣷⣤⣀⣀⣀⣀⣤⣾⣿⣿⡅⠀⠀⠀
⠀⠀⢰⣿⣿⣿⣿⣿⣿⡿⠿⢿⣿⣿⣿⣿⣿⡄⠀⠀
⠀⠀⠻⠿⠿⠿⠿⠿⠿⠷⠴⠿⠿⠿⠿⠿⠿⠇
            """;
        }
        else if (state == 'D')
        {
            StringBuilder banner = new StringBuilder();
            banner.append("  /$$$$$$   /$$$$$$  /$$   /$$ /$$   /$$ /$$$$$$$$  /$$$$$$  /$$$$$$$$       /$$$$$$$$ /$$$$$$  /$$   /$$ /$$$$$$$ \n");
            banner.append(" /$$__  $$ /$$__  $$| $$$ | $$| $$$ | $$| $$_____/ /$$__  $$|__  $$__/      | $$_____//$$__  $$| $$  | $$| $$__  $$\n");
            banner.append("| $$  \\__/| $$  \\ $$| $$$$| $$| $$$$| $$| $$      | $$  \\__/   | $$         | $$     | $$  \\ $$| $$  | $$| $$  \\ $$\n");
            banner.append("| $$      | $$  | $$| $$ $$ $$| $$ $$ $$| $$$$$   | $$         | $$         | $$$$$  | $$  | $$| $$  | $$| $$$$$$$/\n");
            banner.append("| $$      | $$  | $$| $$  $$$$| $$  $$$$| $$__/   | $$         | $$         | $$__/  | $$  | $$| $$  | $$| $$__  $$\n");
            banner.append("| $$    $$| $$  | $$| $$\\  $$$| $$\\  $$$| $$      | $$    $$   | $$         | $$     | $$  | $$| $$  | $$| $$  \\ $$\n");
            banner.append("|  $$$$$$/|  $$$$$$/| $$ \\  $$| $$ \\  $$| $$$$$$$$|  $$$$$$/   | $$         | $$     |  $$$$$$/|  $$$$$$/| $$  | $$\n");
            banner.append(" \\______/  \\______/ |__/  \\__/|__/  \\__/|________/ \\______/    |__/         |__/      \\______/  \\______/ |__/  |__/\n");
            s = banner.toString();
        }
        
        return s;
    }


    /**
     * @return Returns an array of strings to print 
     * @see #playAnimation()
     * 
     */
    private static List<String> getFrames() 
    {

        String[] f = new String[] {
            //First 7 frames
            """
                                                                                                
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                
                         
%@#=:                      
#%@%%%%+--                 
#######**%#*.              
...:---:-=.=-              
%%%#+----                                               
                                                      
                                                      
                                                      
                                                  
                                               
                                                  
                                              
                                                
                                                                
                                                                   
                                                               
                                                                 
                                                                    
                                                                          
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
            """,
            """                                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                               
                                      
.                              
+-.                               
@@%+-.                       
@%++@@%@@@@@@@@%#%+=-                 
@%%*%%#######%%###++#%#               
@%#*%%%#%##%%*==--:-=--:              
@#  .%@@@%%%%#------                           
                                
                                                       
                                                         
                                                           
                                                             
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
                                                                                    
            """,
            """                                                                                                    
                                                    
                                                    
    @                                                 
    .@@%                                               
    .@@@@+                                             
    %@@@@@@-                                           
-@@@@@@@@@*                                          
-@@@@@@@@@%%@%:                                        
+@@@@@@@@@@@%%%%%.                                       
=%@@@@@%%%%%%%%%%%%%%%.                                      
*@@@@@@@%%%%%%%%%%%%%#                                      
#@@@@@@@@@%%%%##%%%%%*                                     
.%@@@@@@@%@@@@@%%%%%%%%+   #+                               
-@@@@%%%%%%%%%%%%@%%%%%%-+#%#                               
=@@@@@@@%%%%%%##%%%%@@%%%@%#+++                              
-*@@@@@@@@@@@@@@@@%%%%%%%%%*=--                              
:%@@@%@@%%%%%%%%@@@@@@@@%*+=-:                             
.%@@%%%%@@@@@@@@@@@@@@@#+==-:                             
+@@@@@@@@@@@@@@@%%@@@%+---+:                             
.*@@@@@@@@@@%%%@@@@@#==-=:                              
#@@@@@@@@%%%%@@@@@+=+=:                               
-@@@@@@%%%%%%%@@@%+++-                                
@@@@%%%%%%%%%@@%%*=+                                 
.@@@@%%%%%%%%%%@%@*=-                                 
*@@@%%%%%%%%%%%%%%#+.    ***=                         
-+*%=.  .  ..:=========++=:: +@@@@%#%#=--                  
=@@@@@@@@@@@@@@@@%%%##*+##%%%%%@@@@@%%%%%%%%@#*:               
.:---::::.        %%%%%%%%%%%%%%%%#*--..:-%-              
+*%%%%%*#%#%%@%%#*##%%%%%+-                         
=%%*#%%@+   +######*%%*++=.                           
            :@%@#*+*.                                 
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
            """,
            """                                                                                                    
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                -.                                      
                                -#@@.                                     
                            :#@@@@:                                     
                        .=*%%@@@@@@:                                     
                    .=*%@@@@@@@@@@@=                                     
                :+#@@@@@@@@@@@@@@@@#                                     
            *@@@@@@@@@@@@@@@@@@@@@@@          ..                         
            .@@@@@@@@@@%%%%%%%%@@@@@    .:-=*#%=                         
            %@@@@@@@%%%%%%%%%%%%@@@. =*######%=                         
            @@@@@@@@@%%%%%%%%%%%@@@+-+++*****#=                         
            =%@@@%%%%%%%%%%%%%%%%%@@*===++++++*:                         
            @@@@@@%%%%%%%%%%@%%%%%%%@@*+========+.                         
            %@@@%%%####%%%%%%%%%%%%%@*++=======-                          
            :@@@@@@@@@%%%%%%%%@%%%%%*===+=====-                          
            .@@%%%%%%%%%@@@@@%%@@%%@*=======-=:                          
            :@@@@%%%%%%%%%%%@@@@@@%@*+======--                           
                :%@@@@@@@@@@@@@@@@@@%#*+======:                           
                    %@@@@@@@@@@@@@@@@#+=====++-                            
                    *@@@@@@@@@@@@@@@@#+=++++- =#=                          
.==.           -===-=+***+#@@@@@@@%@@@@@@@@#++++*@@@@@%#**-:.                  
.*@@@@@@@@@@@@@@@@%%%%%%%%%#%#%%%%%%%@@@@@@@%%@%%+%@@@@@%##%%%%#*%#*.               
:-===++++==--::.:::::::::::.::-+**#%%%%%%%@@%#%###++*#####+=:-...:*-              
                        +*%%%@+=+#%%@%%#*#%###*+-:                         
                    .=%%###*.   .*#%%****:                                
                                .%%@:                                     
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
                                                                        
            """,
            """                                                                                                    
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                    +*%@*.                                 
                        -#%@@@@@@@@@@@@%#*##*==+++*%*--=%#*+:+%@%=                        
                    .+@@@@@@%%%@@@@@@@@@@@#%*=+=++*%%##%#%%%%%@@*+#*--                  
        :=%@*--:-+%##%@@@@@@@@@@@@@@@%%@@@@@@@@#**=+@@#+#%%##**++*%%%%%##%:.              
    .%@@@@@@%%%%%#####*-:..*@@%*+====+*%%@%%%%@@@%*%@%##%%*+==++++=:---:::=%#              
                                        @@@##**%@@#%%%@%   :*+                             
                                    +#-         #%*.                                    
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
                                                                                        
            """,
            
            """                                                                                                    
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                        .                                   
                                                                    =%#++%@.                                
                                                        .+**#@#*+**==*=-++*#%#*+*@@-                        
                                                +**#%%#*++======++**#*%%######%%@@@*:                      
                                            =+@@%%#*+%%#**++=====+****%%%###+*####%%%%%##=-                 
                                    .=@@%%#**+=:.:#@%#*+++++++*#*#%@@@@+:--+**####%##*+%#*               
                        .-@@@@#+%@@@%%*#+:::       *@%#+===++**##%@@@@@@            : .:::*               
                    .*@@@@@%%%%%+:::-.           .#@%#*++++++*###%@@@@@-                                  
                                                .*@%%#**+++++*###%@@@@@@-                                  
                                            =#@@@%#*+++****#*##@@@@@@@#                                  
                                        %@@@@@@@%%**********##*%@@@@@@@@                                  
                                        @%%%#*+=--=++++****###%@@@@@@@@#                                 
                                        +@@@%%#######**+++++*###%@@@@@@@@@#.                               
                                    =@@@@@@%#**+++++++++**######%@@@@@@@%:                                
                                :-#@@@%%##************#####**#%%%%@%%%%%.                                
                                    #@@%%%##########*++===+++*#%%%%%%%%%%                                 
                                    #@@@%%%%####**+===+++++**#%@@%%%%%%%%                                 
                                    :@@@@@@%##***+++++++++**##%@@@@@@%%%%+                                 
                                    .@@@@@@%##*****+++******###*@@@@@@@@%%                                  
                                    %@@@@@%%%###***********##*  =%@@@@@@@-                                  
                                :%@@@@@@%%%%%########**####-    +@@@@@#                                   
                                :-===+*##%%%%%%%%%%########+      :%@@@                                    
                                        -*#%@@@%%%%%######        =@@+                                    
                                            -*@@@@@@@%%####.         *@                                     
                                            =*=+@@@@%%##%          -.                                     
                                                +@@@@@%%#.                                                 
                                                +%@@@@%#                                                  
                                                -#@@@%                                                   
                                                    -#@@.                                                   
                                                    =@@                                                    
                                                    #*                                                    
                                                    .                                                     
                                                                                                    
            """,
            """                                                                                                    
                                                                                                            
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                                
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                                    
                                                                                                                    
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                +@%*                                  
                                                                        -#=   =++#*=-+#**-:.#@=                         
                                                            -++#%%@%%##*+*%%#*++*%%**###%@@@%@#=:                      
                                                    :+=+#@%%%%%%%#*++++==+#+#%%@%%***%%%##**#%@%%%%+--                 
                                        :--..:-::*@@%%%**%#*=:::#%%#==---=*%%@@@%%@@@%#**#%########**%#*.              
                                    :*@@@@@%%%%##*=::-         *%#*+===++==#@@@@@@@@@    .....:---:-=.=-              
                                #@%%%%%#+----                =@%%##*#*#*+@@@@@@@@@@@                                 
                                                            =%%%###%#+#*@@@@@@@@@@@@                                 
                                                            +%%%%%%**%%*%@@@@@@@@@@@-                                 
                                                            +%%%%%%##%%#*%@@@@@@@@@@%%                                  
                                                        #@@%%%%%%#%%%#*%@%@@%%%@%%%%%                                  
                                                    @@@@@@%%%%##%%%%*%@@@@@@@@%@@@%:                                  
                                                    -@@@%%%%%#%%%%%##.+%@@@@@@@@@@@                                   
                                                    @@@%%%%%%%%%%##:   -@@@@@@@@#:                                   
                                                    %@@@@@%%%%%%%%##+    .#+:     :                                    
                                                :#%%@@@@@@@%%%%%%@@                                                   
                                                    :=+@@@@@@@@@@@#                                                   
                                                        @@@@@@@@@@@@                                                    
                                                    *%#+====++*@@                                                    
                                                    :          -@.                                                    
                                                                *                                                     
                                                                                                                        
                                                                                                                    
                                                                                                                    
                                                                                                                
                                                                                                            
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
            """,
            """                                                                                                    
                                                                                                    
                                                                                        
                                                                                        
                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                        
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                            
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                                                                        
                                                                            -+-                                      
                                                                        +#%@-                                        
                                                                        .*%%@@@-       -=.                              
                                                                    *#%%%@@@@@@.   :-+-.                               
                                                            -:+=+++@@@@@@@@@@@@@@*:=**==   @@%+-.                       
                                                -===#@@@@%%%%%%@@@@@@@@@@@@@@@@@@@@%++@@%@@@@@@@@%#%+=-                 
                                    =@@@@@@@%%%%%%#**#%%%##%%#%@@@@@@@@@@@@@@@@@@%%*%%#######%%###++#%#               
                                :@@@@@%*==-----:.             =@@@@#%@@@@@@@@@@@@%#*%%%#%##%%*==--:-=--:              
                                .                            .%@@@@@@@@@#  .%@@@%%%%#------                           
                                                            +@@*##*        .%@@@#                                    
                                                            :%@-                                                       
                                                        -@@#                                                         
                                                        +*@#                                                           
                                                        .#                                                             
                                                                                                                        
                                                                                                                        
                                                                                                                                
                                                                                                                                        
                                                                                                                                        
                                                                                                                                        
                                                                                                                                        
                                                                                                        
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
          
            """                                                                                                    
                                                                                                    
                                                                    
                                                                                
                                                                                                                                                        
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                            .=                                                     
                                                                            #%+                                                   
                                                                            :@@@%=                                                 
                                                                            .%@@@@%=                                               
                                                                            -%@@@@@@@#.                                             
                                                                        =@@@@@@@@@@@-                                            
                                                                        -@@@@@@@@@@@@@*                                           
                                                                        =%@@@@@%%%%@@@@@@%.                                         
                                                                    :%@@@@@@%%%%%%%%@@@@@=       :                                
                                                                    :*@@@@@@@@@@@%%%%%@@@*     :++                               
                                                                        =%@@@@%%@@@@@@@@@%@@#   =*+=.                              
                                                                        *@@@%%%%%%%%%%%%@@@@@@*+====                              
                                                                        :#@@%%%%%%%%@@@@@@@@%*++===+.                             
                                                                        -@@%%@@@@@@@@@@@@@@@#+===+=                               
                                                                        -@@@@@@@@@@@@@@@@@@@@#+==++                                
                                                                        :*%%@@@@@@@@@@@@@@@@@@@*+=+                                 
                                                                        :%@@@@@@@@@@@@@@@@@@#+=+                                 
                                                                            .@@@@@@@@@@@@@@@@@@#+:    +**=                         
                                                -+:      .::=============+#@@@@@@@@@@@@@@@@@@@#=   +@@@@##%%=--                  
                                            :%@@@@@%%%%%%%%%%%%##**==*#%%@@@@@@@@@@@@@@@@@@@@%%@*%%@@@@@%##%%%%%@#*:               
                                                                    ..:-+===*%%%%%%@@%%%%%%@@%%%#@%%%%%#%%#*+--..::#-              
                                                                                =%%%%@@##@%%%%%%%*#%%%%%%*-                         
                                                                            +%%%#%%%+  .%##%%%###%*=+=                            
                                                                                        :@%@=-..                                  
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                        
                                                                                                                                    
                                                                                                                                    
                                                                                    
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
            """
                                                                                                                
                                            
                                                            
                                                                                                                                            
                                                                                                =.                                       
                                                                                                =@@%                                       
                                                                                            *@@@@@#                                      
                                                                                        .*#+@@@@@@@=                                     
                                                                                    .*%@@@@@@@%%%%@@.       :                            
                                                                                    =%@@@@@@@@@@%%%%%%%%     =%%-                           
                                                                                +@@@@@@@@@@@@%%%%%%%%%%: :+%%%%+                           
                                                                                -@@@@@@@%%%%%%%%%%%%%%%#*%#%###=                           
                                                                                =@@@@@@@@@%%%###%%%%%%%%%#***+++.                          
                                                                                #@@@%%%%%%%%%%%####%%%%%%**+++++:                          
                                                                                #@@@@%%%%%%%%%%%%%####%%%%++=++++:                          
                                                                            +@@@@@@@%%%####%%%%%%%##%%%%+====++:                          
                                                                            -@@@@@@@@%%%%%%####%%%%%%%%+==++=+-                          
                                                                                .@@@@@%%%%%%%%%%%%%%%%%%%%+===+++:                          
                                                                                =@@@@%%%%%%%####%%%%%%%%%+=====+:                          
                                                                                .@@@@@@@@@%%%%%%%%%%%%%%%%##+====:                          
                                                                                .@@@@@@@@@%%%%%%%%%%%%*+++====-                           
                                                                                    :@@@@@@@@%%%%%%%%%%%+=======.                           
                                                                                    *@@@@@@@@%%%%%%@@%#++==++-                             
                                                                                    %@@@@@@@%%%%%%@%%*+=+=+-                              
                                                                                    #@@@@@@@%%%%@@%%%*++++=                               
                                                                                    :@@@@@@@@%%%%%%@@%%+=+=   -#+:                         
                                                        .=**-.        .-=====-=====*@@@@@@@%%%%%%%%%@@@%*+- .+@@@@@%#*-::                  
                                                    -%@@%%%%%%%%@@@%%%%%%%#######%%%%%%%%%%%%%%%%%%%%%%%*#%@%%%%###%%%##%##:               
                                                        .::::::::.....::.....:::::::-+*###*#*%%%%%%%%%#%###*#####%%+:.-...:#-              
                                                                                        .*##%%%+-=*#%%###*#%%%##+-.                         
                                                                                    -#%#*##*-   .+*%%%%%%*==:                             
                                                                                                *%@:                                     
                                                                                                                                            
                                                                                                                                            
                                                                                                                                    
                                                        
                                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
            """                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                                                                                                                            
                                                                                                                                                                                                            
                                                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                        
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                            
                                                                                                    .:===++  :==+                                         
                                                                                            .-+#%@@@@@@@@@@@@@@@=                                        
                                                                                        :*#%@@@@@@@@@@@@@@@@@@@@%. .::.                                  
                                                                                        -@@@@@@@@@@@@@@@%@@@@@@@@%###########**+.                         
                                                                                        #@@@@@@@@@%%%%%%%%%%%@@@@*==++++++****=                          
                                                                                        .@@@@@@@@@@@@@@@%%%%%%%%%%*=---===--=+=.                          
                                                                                    .+*@@@@@@@@@@@@@%%%%@@@%%%%%%#*#@%+==-==#@%-                         
                                                                                        .-@@@@@@@@@@@@@@%@@@@@@%%%#-==-*%@@@@@@@@@@@%=-.                  
                                                                    .::*@@@%*=--=##@@%%%%%%%%%%%%%%@@@@@@@@@@@@%@%%@%###%*##%%%#*#%%%##%%::              
                                                                    =####%######%####*+-:..:..        .-=*%@@@@@@@@@@@%#*%%######*+++=:::.-##              
                                                                                                        =%%@@@=  -###%%%%#=::::--:                         
                                                                                                    =%#*        .+%@=                                    
                                                                                                                                                            
                                                                                                                                                            
                                                                                                                                                        
                                                                                                                                                        
                                                                                                                                                        
                                            
                                                                        
                                                                        
                                                                        
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
            """                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                        
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                    
                                                                                                                                        
                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                -                                    
                                                                                                                                :#@@%+                                 
                                                                                                                        +*:  :**+*==-=#@##*-*%#                         
                                                                                                            .+*##%%%#+==**+==++*****###%%@@@@@+:                      
                                                                                                        -=+#%%%#%%#*+++========+*++%%##%#%##**%@@@#%+-:                 
                                                                                        .:      .::-%@@%%##**+-..+%#*+=-----==+*=+%%%@%*++++***#####++**=               
                                                                                    :+@@@@@@@@@%%###*+-::..      *#++=======+**+%%%@@@@     .:--:::.::-.+:              
                                                                                .++++++=-:::::::.              *%*==---==+*###%@@@@@@@                                 
                                                                                                            =@%%*=----===#%#*#@@@@@@@@                                 
                                                                                                    :%@@@@%%%%%%#**+++===+++*%@@@@@@@%%-                               
                                                                                                --%@@@%%%%%%%%%%%%%%%%%%%%#**:.=#%@@@@@%*                              
                                                                                                *@@@@@@@%%##**+========+++**=    :=%@@@*                              
                                                                                                    .-*@@@@%##*+===----=*=         -@@+                              
                                                                                                            -##*####*=-=*+            .%                               
                                                                                                                :%%%%##*#-              .                               
                                                                                                                =%@@%%:                                               
                                                                                                                .#@@+                                                
                                                                                                                    %@                                                 
                                                                                                                    :.                                                 
                                                                                                                    :                                                  
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
            //welcome frames
            """                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                                                    
                                                                                                                
                                                                                                                                                                            
                                                                                                                                                                            
                                                                                                                                                                            
                                                                                                                                                                            
                                                                                                                                                                            
                                                                                                                                                                            
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                .                                   
                                                                                                                                            =%#++%@.                                
                                                                                                                                .+**#@#*+**==*=-++*#%#*+*@                        
                                                                                                                        +**#%%#*++======++**#*%%######%%@@                      
                                                                                                                    =+@@%%#*+%%#**++=====+****%%%###+*####                 
                                                                                                            .=@@%%#**+=:.:#@%#*+++++++*#*#%@@@@+:--+**####               
                                                                                                .-@@@@#+%@@@%%*#+:::       *@%#+===++**##%@@@@@@                         
                                                                                            .*@@@@@%%%%%+:::-.           .#@%#*++++++*###%@@@@@-                                  
                                                                                                                        .*@%%#**+++++*###%@@@@@@-                                  
                                                                                                                    =#@@@%#*+++****#*##@@@@@@@#                                  
                                                                                                                %@@@@@@@%%**********##*%@@@@@@@@                                  
                                                                                                                @%%%#*+=--=++++****###%@@@@@@@@#                                 
                                                                                                                +@@@%%#######**+++++*###%@@@@@@@@@#.                               
                                                                                                            =@@@@@@%#**+++++++++**######%@@@@@@@%:                                
                                                                                                        :-#@@@%%##************#####**#%%%%@%%%%%.                                
                                                                                                            #@@%%%##########*++===+++*#%%%%%%%%%%                                 
                                                                                                            #@@@%%%%####**+===+++++**#%@@%%%%%%%%                                 
                                                                                                            :@@@@@@%##***+++++++++**##%@@@@@@%%%%+                                 
                                                                                                            .@@@@@@%##*****+++******###*@@@@@@@@%%                                  
                                                                                                            %@@@@@%%%###***********##*  =%@@@@@@@-                                  
                                                                                                        :%@@@@@@%%%%%########**####-    +@@@@@#                                   
                                                                                                        :-===+*##%%%%%%%%%%########+      :%@@@                                    
                                                                                                                -*#%@@@%%%%%######        =@@+                                    
                                                                                                                    -*@@@@@@@%%####.         *@                                     
                                                                                                                    =*=+@@@@%%##%          -.                                     
                                                                                                                        +@@@@@%%#.                                                 
                                                                                                                        +%@@@@%#                                                  
                                                                                                                        -#@@@%                                                   
                                                                                                                            -#@@.                                                   
                                                                                                                            =@@                                                    
                                                                                                                            #*                                                    
                                                                                                                                                .                                                     
                                                                                                    
            """,
            """                                                                                                    
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                                                                                                                    
                                                                                                                                                                                                    
                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                                                            
                                                                                                                                                    +@%*                                  
                                                                                                                                            -#=   =++#*=-+                         
                                                                                                                                -++#%%@%%##*+*%%#*++*%%**#                      
                                                                                                                        :+=+#@%%%%%%%#*++++==+#+#%%@%%***%                 
                                                                                                            :--..:-::*@@%%%**%#*=:::#%%#==---=*%%@@@%%@@@%              
                                                                                                        :*@@@@@%%%%##*=::-         *%#*+===++==#@@@@@@@@@               
                                                                                                    #@%%%%%#+----                =@%%##*#*#*+@@@@@@@@@@@                                 
                                                                                                                                =%%%###%#+#*@@@@@@@@@@@@                                 
                                                                                                                                +%%%%%%**%%*%@@@@@@@@@@@-                                 
                                                                                                                                +%%%%%%##%%#*%@@@@@@@@@@%%                                  
                                                                                                                            #@@%%%%%%#%%%#*%@%@@%%%@%%%%%                                  
                                                                                                                        @@@@@@%%%%##%%%%*%@@@@@@@@%@@@%:                                  
                                                                                                                        -@@@%%%%%#%%%%%##.+%@@@@@@@@@@@                                   
                                                                                                                        @@@%%%%%%%%%%##:   -@@@@@@@@#:                                   
                                                                                                                        %@@@@@%%%%%%%%##+    .#+:     :                                    
                                                                                                                    :#%%@@@@@@@%%%%%%@@                                                   
                                                                                                                        :=+@@@@@@@@@@@#                                                   
                                                                                                                            @@@@@@@@@@@@                                                    
                                                                                                                        *%#+====++*@@                                                    
                                                                                                                        :          -@.                                                    
                                                                                                                                    *                                                     
                                                                                                                                                                                            
                                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                
                                                                                                                                                                                                                    
                                                                                                                        
                                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
                                                                                                        
            """,
            """                                                                                                    
                                                                                                    
                                                                                                    


                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                            
                                                                                                                                                                                                            
                                                                                                                                                                                                            
                                                                                                                                                                                                            
                                                                                                                                                                                                
                                                                                                                                                                                                 
                                                                                                                                                                                        
                                                                                                                                                        *#                      
                                                                                                                                                -:+=+++@@@                  
                                                                                                                                    -===#@@@@%%%%%%@@@@@@@                
                                                                                                                        =@@@@@@@%%%%%%#**#%%%##%%#%@@@@@@@             
                                                                                                                    :@@@@@%*==-----:.             =@@@@#%@              
                                                                                                                    .                            .%@@@@@@@                           
                                                                                                                                                +@@*##*                                      
                                                                                                                                                :%@-                                                       
                                                                                                                                            -@@#                                                         
                                                                                                                                            +*@#                                                           
                                                                                                                                            .#                                                             
                                                                                                                                                                                                            
                                                                                                                                                                                    
                                                                                                                                                                                    
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        
                                                                                                                                                                        









                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,

                      """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
                                                                                                                                                              
                                                                                                                                                                                      
                                                                                                                                                                            
                                                                                                                                                                                       
                                                                                                                                                                            
                                                                                                                                                                                         
                                                                                                                                            -+:      .::==         
                                                                                                                                        :%@@@@@%%%%%%%%%%%          
                                                                                                                                                                      
                                                                                                                                                                               
                                                                                                                                                                                     
                                                                                                                                                                                           
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
                      
            """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
-   /$$ /$$$$$$$$
-   /$$$| $$_____/
$  /$$$$| $$$$$       
$/$$ $$| $$$$$   
$$$| $$| $$__/   
$\\  $ | $$| $$      
$ \\/  | $$| $$$$$$$$
__/     |__/|________/                                                                      
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,          
            """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
   /$$$$$$ /$$      /$$ /$$$$$$$$
 /$$__  $$| $$$    /$$$| $$_____/
__/| $$     || $$ $  /$$$$| $$$$$       
   | $$  | $$| $$ $$/$$ $$| $$$$$   
   | $$  | $$| $$  $$$| $$| $$__/   
 $$| $$  | $$| $$\\  $ | $$| $$      
$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$
___/  \\______/ |__/     |__/|________/                                                                      
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,
                        """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
$$$$$ /$$$       /$$$$$$    /$$$$$$ /$$      /$$ /$$$$$$$$
____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/
     | $$      | $$  \\__/| $$     || $$ $  /$$$$| $$$$$       
$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$   
__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/   
       | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$      
$$$$$ | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$
____/ |________/ \\______/  \\______/ |__/     |__/|________/                                                                  
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,            
            """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
        /$$       /$$  /$$$$$$$$ /$$$       /$$$$$$    /$$$$$$ /$$      /$$ /$$$$$$$$
        | $$  /$ | $$| $$$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/
        | $$ /$$$| $$| $$$      | $$      | $$  \\__/| $$     || $$ $  /$$$$| $$$$$       
        | $$/$$ $$ $$| $$$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$   
        | $$$$_  $$$$| $$$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/   
        | $$$/ \\   $$| $$       | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$      
        | $$/   \\   $| $$$$$$$$ | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$
        |__/     \\__/|________/ |________/ \\______/  \\______/ |__/     |__/|________/                                                                      
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,            

        };
        return Arrays.asList(f);
    }


        /**
         * Connect Four (ASCII UI)
         *
         * Features:
         *  - Option D in the main menu starts Connect Four [9].
         *  - Board size selection: 5x4, 6x5, or 7x6 (Cols x Rows).
         *  - Game mode: Single-player (vs. intelligent computer) or Two-players.
         *  - Players drop discs into columns; full columns must be reselected.
         *  - After each move, the screen refreshes and shows the updated board.
         *  - Game ends on a connect-four, a full board (draw), or if a player quits (forfeit).
         *  - Single-player AI plays  (minimax yet).
         *
         * Compile:  javac ConnectFour.java
         * Run:      java ConnectFour
         */
        

        // Symbols
        private static final char EMPTY = '.';
        private static final char P1 = 'X';
        private static final char P2 = 'O';

        private static final Scanner SC = new Scanner(System.in);
        private static final Random RNG = new Random();

        /**
         * Initials variables, calls required functions and creates a game loop
         * @see #pickBoardSize
         * @see #pickStarter
         * @see #clearAndBanner
         * @see #printGameHeader(int, int, char, char, String)
         * @see #printBoard(char[][])
         * @see #hasConnectFour(char[][], char)
         * @see #pause(sc)
         * @see #switchPlayer(char)
         *
         */

        private static void startConnectFour() 
        {
            int[] size = pickBoardSize(); // [cols, rows]
            int cols = size[0], rows = size[1];
            char[][] board = new char[rows][cols];
            for (int r = 0; r < rows; r++) Arrays.fill(board[r], EMPTY);

            char mode = pickGameMode();
            boolean humanStarts = true;
            if (mode == 's') {
                humanStarts = pickStarter();
            }

            char current = humanStarts ? P1 : P2;
            boolean single = (mode == 's');

            String lastInfo = "Game has started!";
            while (true) {
                clearAndBanner();
                printGameHeader(cols, rows, mode, current, lastInfo);
                printBoard(board);

                // Check terminal state (win or draw) before next move (in case of re-entry)
                if (hasConnectFour(board, P1)) {
                    boolean[][] mask = findWinningFour(board, P1);
                    clearAndBanner();
                    printGameHeader(cols, rows, mode, current, lastInfo);

                    printBoard(board, mask);
                    pause(sc);
                    return;
                }
                if (hasConnectFour(board, P2)) {
                    boolean[][] mask = findWinningFour(board, P2);
                    clearAndBanner();
                    printGameHeader(cols, rows, mode, current, lastInfo);
                    printBoard(board, mask);

                    pause(sc);
                    return;
                }
                if (isBoardFull(board)) {
                    System.out.println(ANSI_MAGENTA + ANSI_BOLD + "Draw, no more legal moves!" + ANSI_RESET);
                    pause(sc);
                    return;
                }

                if (single && current == P2 && !humanStarts) {
                    // Computer turn when computer is P2 and started OR when current is computer side
                    // runs minmax algorithm
                    int aiCol = chooseAIMove(board);
                    int row = dropDiscAnimated(board, aiCol, P2, cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column "+ (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }
                if (single && current == P2 && humanStarts) {
                    // In single-player where human is P1, P2 is computer
                    int aiCol = chooseAIMove(board);
                    int row = dropDiscAnimated(board, aiCol, P2, cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column "+ (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }

                // Human move (either in 2P or it's the human's side in single)
                String who = (current == P1) ? "P1 (X)" : (single ? "Computer" : "P2 (O)");
                if (single && current == P2) {
                    // If somehow we're here, it means humanStarts==false but we already handled AI. Just in case.
                    int aiCol = chooseAIMove(board);
                    int row = dropDiscAnimated(board, aiCol, P2,cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column " + (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }

                System.out.println(ANSI_BOLD + who + ANSI_RESET + ", make your move. Column (1-" + cols + ") or Q=Forfeit:");
                String in = prompt("> ").trim();
                if (in.equalsIgnoreCase("Q")) {
                    System.out.println(forfeitLine(current));
                    pause(sc);
                    return;
                }

                Integer col = tryParseCol(in, cols);
                if (col == null) {
                    lastInfo = "Invalid input. Please choose a column between 1-" + cols + " or Q.";
                    continue;
                }
                if (isColumnFull(board, col)) {
                    lastInfo = "This column is full, choose a different column.";
                    continue;
                }

                int row = dropDiscAnimated(board, col, current,cols, rows, mode, current, lastInfo);
                lastInfo = ((current == P1) ? "P1 " : (single ? "You" : "P2 "))  + "played column " + (col + 1) + ".";

                // Check win/draw immediately after the move
                if (isWinningMove(board, row, col, current)) {
                    clearAndBanner();
                    boolean[][] mask = findWinningFour(board, current);
                    printGameHeader(cols, rows, mode, current, lastInfo);
                    printBoard(board, mask);
                    pause(sc);
                    return;
                }
                if (isBoardFull(board)) {
                    clearAndBanner();
                    printGameHeader(cols, rows, mode, current, lastInfo);
                    printBoard(board);
                    System.out.println(ANSI_MAGENTA + ANSI_BOLD + "Draw, no more legal moves!" + ANSI_RESET);
                    pause(sc);
                    return;
                }

                current = switchPlayer(current);
            }
        }


        private static int[] pickBoardSize() {
            while (true) {
                clearAndBanner();
                System.out.println(ANSI_CYAN + ANSI_BOLD + "Pick a board size" + ANSI_RESET);
                System.out.println("1) 5 x 4   (Column x Rows)");
                System.out.println("2) 6 x 5");
                System.out.println("3) 7 x 6");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();

                switch (s) {
                    case "1": return new int[]{5, 4};
                    case "2": return new int[]{6, 5};
                    case "3": return new int[]{7, 6};
                    default:
                        System.out.println("Invalid input, please try again.");
                        pause(sc);
                }
            }
        }


        private static char pickGameMode() {
            while (true) {
                clearAndBanner();
                System.out.println(ANSI_CYAN + ANSI_BOLD + "Pick game mode" + ANSI_RESET);
                System.out.println("1) Single Player (Against intelligent computer)");
                System.out.println("2) Two Players");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();

                switch (s) {
                    case "1": return 's';
                    case "2": return 't';
                    default:
                        System.out.println("Invalid input, please try again.");
                        pause(sc);
                }
            }
        }

        private static boolean pickStarter() {
            while (true) {
                clearAndBanner();
                System.out.println(ANSI_CYAN + ANSI_BOLD + "Who will starts?" + ANSI_RESET);
                System.out.println("1) Human (X)");
                System.out.println("2) Pc (O)");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();

                switch (s) {
                    case "1": return true;
                    case "2": return false;
                    default:
                        System.out.println("Invalid input, please try again.");
                        pause(sc);
                }
            }
        }

        // ====== Board + Gameplay ======
        private static boolean isColumnFull(char[][] board, int col) {
            return board[0][col] != EMPTY;
        }

        private static int dropDisc(char[][] board, int col, char player) {
            for (int r = board.length - 1; r >= 0; r--) {
                if (board[r][col] == EMPTY) {
                    board[r][col] = player;
                    return r;
                }
            }
            return -1; // should never happen if checked with isColumnFull
        }

        private static boolean isBoardFull(char[][] board) {
            for (int c = 0; c < board[0].length; c++) {
                if (!isColumnFull(board, c)) return false;
            }
            return true;
        }

        private static boolean isWinningMove(char[][] board, int row, int col, char p) {
            // Check in four directions around the last placed disc
            return (countLine(board, row, col, 1, 0, p) + countLine(board, row, col, -1, 0, p) - 1 >= 4) || // horizontal
                (countLine(board, row, col, 0, 1, p) + countLine(board, row, col, 0, -1, p) - 1 >= 4) || // vertical
                (countLine(board, row, col, 1, 1, p) + countLine(board, row, col, -1, -1, p) - 1 >= 4) || // diag ↘↖
                (countLine(board, row, col, 1, -1, p) + countLine(board, row, col, -1, 1, p) - 1 >= 4);   // diag ↗↙
        }

        private static int countLine(char[][] board, int r, int c, int dr, int dc, char p) {
            int rows = board.length, cols = board[0].length;
            int cnt = 0;
            int rr = r, cc = c;
            while (rr >= 0 && rr < rows && cc >= 0 && cc < cols && board[rr][cc] == p) {
                cnt++;
                rr += dr; cc += dc;
            }
            return cnt;
        }

        private static boolean hasConnectFour(char[][] board, char p) {
            int rows = board.length, cols = board[0].length;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (board[r][c] != p) continue;
                    if (isWinningMove(board, r, c, p)) return true;
                }
            }
            return false;
        }

        /**
         * if no column is a better choice to play than the other,
         * pick a random one
         */
        private static int randomLegalColumn(char[][] board) {
            List<Integer> legal = new ArrayList<>();
            for (int c = 0; c < board[0].length; c++) if (!isColumnFull(board, c)) legal.add(c);
            return legal.get(RNG.nextInt(legal.size()));
        }

        private static char switchPlayer(char p) { return (p == P1) ? P2 : P1; }

        // ====== Rendering ======
        private static void printBoard(char[][] b) {
            int rows = b.length, cols = b[0].length;
            // Column indices (1..cols)
            System.out.print("    ");
            for (int c = 1; c <= cols; c++) {
                System.out.print("  " + c + " ");
            }
            System.out.println("");

            // Top border
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┬───");
            System.out.println("┬");

            for (int r = 0; r < rows; r++) {
                System.out.print("    ");
                for (int c = 0; c < cols; c++) {
                    char ch = b[r][c];
                    String cell;
                    if (ch == P1) cell = ANSI_RED + "X" + ANSI_RESET;
                    else if (ch == P2) cell = ANSI_YELLOW + "O" + ANSI_RESET;
                    else cell = " ";
                    System.out.print("│ " + cell + " ");
                }
                System.out.println("│");

                System.out.print("    ");
                for (int c = 0; c < cols; c++) System.out.print("├───");
                System.out.println("┤");
            }

            // Bottom border (visual)
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┴───");
            System.out.println("┴");
        }

        private static void printGameHeader(int cols, int rows, char mode, char current, String info) {
            System.out.println(ANSI_CYAN + ANSI_BOLD +
                    "╔══════════════════════════════════════════════╗\n" +
                    "║                CONNECT FOUR                  ║\n" +
                    "╠══════════════════════════════════════════════╣\n" +
                    "║  Board: " + cols + " x " + rows + "   Mode: " + (mode == 's' ? "Single Player " : "Two Players   ") + "         ║\n" +
                    "║  Symbols -> " + ANSI_RED + "X" + ANSI_RESET + " = P1   " + ANSI_YELLOW + "O" + ANSI_RESET + " = P2                  ║\n" +
                    "╚══════════════════════════════════════════════╝" + ANSI_RESET);
            System.out.println((info == null || info.isEmpty()) ? "" : (ANSI_GREEN + info + ANSI_RESET));
            System.out.println("");
        }

        private static void clearAndBanner() {
            clearConsole();
            StringBuilder banner = new StringBuilder();
            banner.append("  /$$$$$$   /$$$$$$  /$$   /$$ /$$   /$$ /$$$$$$$$  /$$$$$$  /$$$$$$$$       /$$$$$$$$ /$$$$$$  /$$   /$$ /$$$$$$$ \n");
            banner.append(" /$$__  $$ /$$__  $$| $$$ | $$| $$$ | $$| $$_____/ /$$__  $$|__  $$__/      | $$_____//$$__  $$| $$  | $$| $$__  $$\n");
            banner.append("| $$  \\__/| $$  \\ $$| $$$$| $$| $$$$| $$| $$      | $$  \\__/   | $$         | $$     | $$  \\ $$| $$  | $$| $$  \\ $$\n");
            banner.append("| $$      | $$  | $$| $$ $$ $$| $$ $$ $$| $$$$$   | $$         | $$         | $$$$$  | $$  | $$| $$  | $$| $$$$$$$/\n");
            banner.append("| $$      | $$  | $$| $$  $$$$| $$  $$$$| $$__/   | $$         | $$         | $$__/  | $$  | $$| $$  | $$| $$__  $$\n");
            banner.append("| $$    $$| $$  | $$| $$\\  $$$| $$\\  $$$| $$      | $$    $$   | $$         | $$     | $$  | $$| $$  | $$| $$  \\ $$\n");
            banner.append("|  $$$$$$/|  $$$$$$/| $$ \\  $$| $$ \\  $$| $$$$$$$$|  $$$$$$/   | $$         | $$     |  $$$$$$/|  $$$$$$/| $$  | $$\n");
            banner.append(" \\______/  \\______/ |__/  \\__/|__/  \\__/|________/ \\______/    |__/         |__/      \\______/  \\______/ |__/  |__/\n");

            System.out.println(ANSI_BOLD + "\n" + banner + "\n" + ANSI_RESET);
        }

        private static String winLine(char p) {
            return ANSI_GREEN + ANSI_BOLD + (p == P1 ? "P1 won!" : "P2 won!") + ANSI_RESET;
        }

        private static String forfeitLine(char current) {
            char winner = (current == P1) ? P2 : P1;
            return ANSI_MAGENTA + ANSI_BOLD + "Forfeit! " + (winner == P1 ? "P1" : "P2") + " won." + ANSI_RESET;
        }


        private static String prompt(String s) {
            System.out.print(s);
            return SC.nextLine();
        }

        private static Integer tryParseCol(String in, int cols) {
            try {
                int v = Integer.parseInt(in) - 1;
                if (v >= 0 && v < cols) return v;
            } catch (NumberFormatException ignored) { }
            return null;
        }

        // Geçici düşen taşı overlay olarak çiz
        private static void printBoardWithFalling(char[][] b, Integer fallRow, Integer fallCol, Character fallCh) {
            int rows = b.length, cols = b[0].length;

            // Sütun numaraları
            System.out.print("    ");
            for (int c = 1; c <= cols; c++) System.out.print("  " + c + " ");
            System.out.println("");

            // Üst kenar
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┬───");
            System.out.println("┬");

            for (int r = 0; r < rows; r++) {
                System.out.print("    ");
                for (int c = 0; c < cols; c++) {
                    boolean isFalling = fallRow != null && fallCol != null && fallCh != null
                                        && r == fallRow && c == fallCol;
                    char ch = b[r][c];
                    String cell;

                    if (isFalling) 
                    {
                        // Geçici taşın rengi
                        if (fallCh == P1) 
                            cell = ANSI_RED + "X" + ANSI_RESET;
                        else if 
                            (fallCh == P2) cell = ANSI_YELLOW + "O" + ANSI_RESET;
                        else 
                        cell = String.valueOf(fallCh);
                    } else 
                    {
                        if (ch == P1) 
                            cell = ANSI_RED + "X" + ANSI_RESET;
                        else if (ch == P2) 
                            cell = ANSI_YELLOW + "O" + ANSI_RESET;
                        else 
                            cell = " ";
                    }
                    System.out.print("│ " + cell + " ");
                }
                System.out.println("│");

                System.out.print("    ");
                for (int c = 0; c < cols; c++) System.out.print("├───");
                System.out.println("┤");
            }

            // Alt kenar
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┴───");
            System.out.println("┴");
        }
        private static int dropDiscAnimated(char[][] board, int col, char player, int cols, int rows, char mode, char current, String info) 
        {
            
            // Sütun dolu mu?
            if (isColumnFull(board, col)) return -1;

            // Hedef satırı bul (alttan yukarı doluluğu kontrol)
            int targetRow = -1;
            for (int r = board.length - 1; r >= 0; r--) {
                if (board[r][col] == EMPTY) {
                    targetRow = r;
                    break;
                }
            }
            if (targetRow == -1) return -1;

            // Üstten hedefe kadar "düşen taş" overlay ile çiz
            int delayMs = 50; // animasyon hızı (istersen 30–80 arası dene)
            for (int r = 0; r <= targetRow; r++) {
                clearAndBanner();
                printGameHeader(cols, rows, mode, current, info);
                // Ekranı temizle + başlık + overlay'li tahta
                // İstersen burada kendi header’ını da çağırabilirsin:
                // printGameHeader(cols, rows, mode, current, info);  --> parametrelerin elinde olmayabilir
                printBoardWithFalling(board, r, col, player);

                try { Thread.sleep(delayMs); }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            // Son olarak taşı tahtaya yerleştir
            board[targetRow][col] = player;
            return targetRow;
        }

        // 4 yön: yatay, dikey, iki diyagonal
        private static final int[][] DIRS = {{1,0},{0,1},{1,1},{1,-1}};

        /** Tahtada oyuncu p için herhangi bir 4'lüyü bulur; varsa true hücrelerle mask döner, yoksa null. */
        private static boolean[][] findWinningFour(char[][] board, char p) 
        {
            int rows = board.length, cols = board[0].length;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (board[r][c] != p) continue;
                    for (int[] d : DIRS) {
                        int rr = r, cc = c;
                        // 4 adet ardışık hücre p mi?
                        boolean ok = true;
                        for (int k = 0; k < 4; k++) {
                            int r2 = rr + d[0] * k, c2 = cc + d[1] * k;
                            if (r2 < 0 || r2 >= rows || c2 < 0 || c2 >= cols || board[r2][c2] != p) {
                                ok = false; break;
                            }
                        }
                        if (ok) {
                            boolean[][] mask = new boolean[rows][cols];
                            for (int k = 0; k < 4; k++) {
                                int r2 = rr + d[0] * k, c2 = cc + d[1] * k;
                                mask[r2][c2] = true;
                            }
                            return mask;
                        }
                    }
                }
            }
            return null;
        }

        // Highlight destekli yeni sürüm:
        private static void printBoard(char[][] b, boolean[][] highlight) {
            int rows = b.length, cols = b[0].length;
            char winning = 'a';
            // Sütun numaraları
            System.out.print("    ");
            for (int c = 1; c <= cols; c++) System.out.print("  " + c + " ");
            System.out.println("");

            // Üst kenar
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┬───");
            System.out.println("┬");

            for (int r = 0; r < rows; r++) {
                System.out.print("    ");
                for (int c = 0; c < cols; c++) {
                    char ch = b[r][c];
                    boolean hi = (highlight != null && highlight[r][c]);
                    String cell;
                    if (ch == P1) winning = (hi ? P1 : P2);
                    if (ch == P1) 
                        cell = (hi ? ANSI_GREEN + ANSI_BOLD : ANSI_RED) + "X" + ANSI_RESET;
                    else if 
                        (ch == P2) cell = (hi ? ANSI_GREEN + ANSI_BOLD : ANSI_YELLOW) + "O" + ANSI_RESET;
                    else cell = " ";
                    System.out.print("│ " + cell + " ");
                }
                System.out.println("│");

                System.out.print("    ");
                for (int c = 0; c < cols; c++) System.out.print("├───");
                System.out.println("┤");
            }

            // Alt kenar
            System.out.print("    ");
            for (int c = 0; c < cols; c++) System.out.print("┴───");
            System.out.println("┴");

            System.out.print(winLine(winning));
        }




    //AI
    private static final int MAX_DEPTH_5x4 = 8;
    private static final int MAX_DEPTH_6x5 = 8;
    private static final int MAX_DEPTH_7x6 = 7;

    private static int pickDepthForSize(int cols, int rows)
    {
        if (cols == 5 && rows == 4) 
            return MAX_DEPTH_5x4;
        if (cols == 6 && rows == 5) 
            return MAX_DEPTH_6x5;
        return MAX_DEPTH_7x6;
    }

    /**
     * Prioritize columns near to center, makes pruning faster
     * @see #isColumnFull(char[][], char)
     * @return List<Integer>
     * @param char[][] board  
    */
    private static List<Integer> getValidMovesOrdered(char[][] board)
    {
        int cols = board[0].length;
        List<Integer> valid = new ArrayList<>();
        for (int c = 0; c < cols; c++)
        {
            if (!isColumnFull(board, c)) valid.add(c);
        }
        valid.sort((a, b) ->
        {
            int center = cols / 2;
            int da = Math.abs(a - center);
            int db = Math.abs(b - center);
            return Integer.compare(da, db);
        });
        return valid;
    }

    /** one move simulation
     * @return row to play
     * @param char[][] int char
     * 
     */
    private static int makeMove(char[][] board, int col, char p)
    {
        for (int r = board.length - 1; r >= 0; r--)
        {
            if (board[r][col] == EMPTY)
            {
                board[r][col] = p;
                return r;
            }
        }
        return -1;
    }
    /**
     * undos move
     */
    private static void undoMove(char[][] board, int row, int col)
    {
        board[row][col] = EMPTY;
    }

    /**
     * check if the game is over
     */
    private static boolean isTerminalPosition(char[][] board)
    {
        return hasConnectFour(board, P1) || hasConnectFour(board, P2) || isBoardFull(board);
    }

    // HEURISTIC EVALUATION 
    /**
     * 4 cell is a win, so an arbitrary high number, 3 cell is also has a high score, but 2 cell is not that important
     * @return score of the window
     * 
     */
    private static int scoreWindow(int xCount, int oCount, int empty)
    {
        // X attacking, O defending
        if (xCount == 4) 
            return 100000;
        if (xCount == 3 && empty == 1) 
            return 500;
        if (xCount == 2 && empty == 2) 
            return 30;

        if (oCount == 4) 
            return -100000;
        if (oCount == 3 && empty == 1) 
            return -700;   // harsher response to enemy's 3 cell threat
        if (oCount == 2 && empty == 2) 
            return -35;

        return 0;
    }

    /**
     * By using the scoreWindow fucntion we created earlier, we can evaluate each window of the board and assign a value to it
     * @see #scoreWindow(int, int, int)
     */
    private static int evaluateBoard(char[][] b, char ai, char human)
    {
        int rows = b.length, cols = b[0].length;
        int score = 0;

        // center bonus (strategical advantage)
        int center = cols / 2;
        int centerCountAi = 0, centerCountHu = 0;
        for (int r = 0; r < rows; r++)
        {
            if (b[r][center] == ai) 
                centerCountAi++;
            else if (b[r][center] == human) 
                centerCountHu++;
        }
        score += centerCountAi * 8;
        score -= centerCountHu * 8;

        // scan all windows with the size 4
        // Horizontal
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c <= cols - 4; c++)
            {
                int x = 0, o = 0, e = 0;
                for (int k = 0; k < 4; k++)
                {
                    char ch = b[r][c + k];
                    if (ch == ai) 
                        x++;
                    else if (ch == human) 
                        o++;
                    else 
                        e++;
                }
                score += scoreWindow(x, o, e);
            }
        }
        // Vertical
        for (int c = 0; c < cols; c++)
        {
            for (int r = 0; r <= rows - 4; r++)
            {
                int x = 0, o = 0, e = 0;
                for (int k = 0; k < 4; k++)
                {
                    char ch = b[r + k][c];
                    if (ch == ai) 
                        x++;
                    else if (ch == human) 
                        o++;
                    else 
                        e++;
                }
                score += scoreWindow(x, o, e);
            }
        }
        // Diagonal(to bottom)
        for (int r = 0; r <= rows - 4; r++)
        {
            for (int c = 0; c <= cols - 4; c++)
            {
                int x = 0, o = 0, e = 0;
                for (int k = 0; k < 4; k++)
                {
                    char ch = b[r + k][c + k];
                    if (ch == ai) 
                        x++;
                    else if (ch == human) 
                        o++;
                    else 
                        e++;
                }
                score += scoreWindow(x, o, e);
            }
        }
        // Diagonal (to top)
        for (int r = 3; r < rows; r++)
        {
            for (int c = 0; c <= cols - 4; c++)
            {
                int x = 0, o = 0, e = 0;
                for (int k = 0; k < 4; k++)
                {
                    char ch = b[r - k][c + k];
                    if (ch == ai) 
                        x++;
                    else if (ch == human) 
                        o++;
                    else 
                        e++;
                }
                score += scoreWindow(x, o, e);
            }
        }

        return score;
    }

    
    /**
     * Minimax algorithm with alpha beta pruning
     * @return int[], col and score
     * @see #evaluateBoard(char[][], char, char)
     * @see #hasConnectFour(char[][], char)
     * @see #getValidMovesOrdered(char[][])
     * @see #makeMove(char[][], int, char)
     * @see #undoMove(char[][], int, int)
     */
    private static int[] minimax(char[][] board,
                                int depth,
                                int alpha,
                                int beta,
                                boolean maximizing,
                                char aiPlayer)
    {
        char human = (aiPlayer == P1) ? P2 : P1;

        //check if we hit a leaf node
        if (depth == 0 || isTerminalPosition(board))
        {
            int val;
            if (hasConnectFour(board, aiPlayer)) 
                val = 1_000_000 + depth;
            else if (hasConnectFour(board, human)) 
                val = -1_000_000 - depth;
            else 
                val = evaluateBoard(board, aiPlayer, human);
            return new int[] { -1, val };
        }

        List<Integer> moves = getValidMovesOrdered(board);
        //we should never got here
        if (moves.isEmpty())
        {
            int v = evaluateBoard(board, aiPlayer, human);
            return new int[] { -1, v };
        }

        if (maximizing)
        {
            int bestScore = Integer.MIN_VALUE;
            int bestCol = moves.get(0);
            for (int col : moves)
            {
                int row = makeMove(board, col, aiPlayer);
                //recursion is happening here
                int[] child = minimax(board, depth - 1, alpha, beta, false, aiPlayer);
                undoMove(board, row, col);

                if (child[1] > bestScore)
                {
                    bestScore = child[1];
                    bestCol = col;
                }
                //alpha beta pruning
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) 
                    break;
            }
            return new int[] { bestCol, bestScore };
        }
        else //opposite of maximizing
        {
            int bestScore = Integer.MAX_VALUE;
            int bestCol = moves.get(0);
            for (int col : moves)
            {
                int row = makeMove(board, col, human);
                int[] child = minimax(board, depth - 1, alpha, beta, true, aiPlayer);
                undoMove(board, row, col);

                if (child[1] < bestScore)
                {
                    bestScore = child[1];
                    bestCol = col;
                }
                beta = Math.min(beta, bestScore);
                //alpha beta pruning
                if (alpha >= beta) break;
            }
            return new int[] { bestCol, bestScore };
        }
    }

    /**
     * Called from the game, functions like a some kind of interface,
     * @return move to play as a column
     */
    private static int chooseAIMove(char[][] board)
    {
        int cols = board[0].length, rows = board.length;
        int depth = pickDepthForSize(cols, rows);
        char ai = P2;
        char human = (ai == P1) ? P2 : P1;

        // 1-ply: kazan ya da blokla
        for (int col : getValidMovesOrdered(board))
        {
            int row = makeMove(board, col, ai);
            boolean win = isWinningMove(board, row, col, ai);
            undoMove(board, row, col);
            if (win) return col;
        }
        for (int col : getValidMovesOrdered(board))
        {
            int row = makeMove(board, col, human);
            boolean win = isWinningMove(board, row, col, human);
            undoMove(board, row, col);
            if (win) return col;
        }

        int[] res = minimax(board, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true, ai);
        if (res[0] < 0 || isColumnFull(board, res[0]))
        {
            return randomLegalColumn(board);
        }
        return res[0];
    }



    private static void pause(Scanner sc) {
        System.out.println("Press Enter to continue...");
        sc.nextLine();
    }
}