/**
 * PrimarySchoolOnlyApp.java
 *
 * <p>Bu program, CMPE343 Project #1 (Section A Only) kapsamında hazırlanmıştır.
 * Program iki ana fonksiyon içerir:
 * <ul>
 *   <li>Kullanıcının doğum tarihine göre yaşını ve burcunu manuel olarak hesaplar.</li>
 *   <li>Girilen metindeki kelimeleri özyinelemeli (recursive) olarak tersten yazar.</li>
 * </ul>
 *
 * <p>Program UTF-8 karakter setiyle çalışır, bu nedenle Türkçe karakter desteği mevcuttur.
 * <br>LocalDate sınıfı yalnızca bugünün tarihini okumak için kullanılır (hesaplama yapılmaz).
 *
 * <p><b>Hazırlayan:</b> Volkan Dinç  
 * <br><b>Ders:</b> CMPE343 – Project #1 (Section A Only)  
 * <br><b>Sürüm:</b> manual age calc + UTF-8 + recursive text reversal
 */

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.time.LocalDate; // For taking the current date not making any calculation

public class A {

    /** UTF-8 destekli kullanıcı girişi almak için kullanılan Scanner nesnesi. */
    private static final Scanner sc =
        new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    /**
     * Programın giriş noktası.
     * UTF-8 karakter setiyle standart çıktı akışlarını ayarlar ve ana menüyü başlatır.
     */
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        clearScreen();
        System.out.println("CMPE343 — Project #1 (Section A Only, manual age calc sürümü)");
        mainMenu();
        System.out.println("\nProgram terminated. Goodbye!");
    }

    /**
     * Ana menü ekranını oluşturur.
     * Kullanıcı burada "Primary School" alt menüsüne girebilir veya programı sonlandırabilir.
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("[A] Primary School");
            System.out.println("[E] Terminate");
            String choice = readString("Your choice: ").trim().toUpperCase();

            switch (choice) {
                case "A" -> { clearScreen(); primarySchoolMenu(); }
                case "E" -> { clearScreen(); return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Primary School alt menüsünü gösterir.
     * Kullanıcıya yaş ve burç hesaplama veya kelimeleri tersten yazdırma seçeneklerini sunar.
     */
    private static void primarySchoolMenu() {
        while (true) {
            System.out.println("=== PRIMARY SCHOOL ===");
            System.out.println("[1] Age and Zodiac Sign Detection (manual calc)");
            System.out.println("[2] Reverse the Words (recursive)");
            System.out.println("[3] Return to Main Menu");
            String ch = readString("Your choice: ").trim();

            switch (ch) {
                case "1" -> { clearScreen(); opAgeAndZodiac(); }
                case "2" -> { clearScreen(); opReverseWordsRecursive(); }
                case "3" -> { clearScreen(); return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Kullanıcının doğum tarihine göre yaşını ve burcunu hesaplar.
     * Tarih doğrulaması yapar, bugünün tarihini alır ve farkı manuel hesaplar.
     */
    private static void opAgeAndZodiac() {
        System.out.println("=== Age & Zodiac Sign Detection ===");
        int bYear  = readInt("Birth year (1-9999): ", 1, 9999);
        int bMonth = readInt("Birth month (1-12): ", 1, 12);
        int bDay   = readInt("Birth day (1-31): ", 1, 31);

        if (!isValidDate(bYear, bMonth, bDay)) {
            System.out.println("Invalid birth date!");
            pauseEnter(); clearScreen(); return;
        }

        LocalDate today = LocalDate.now();
        int tYear = today.getYear();
        int tMonth = today.getMonthValue();
        int tDay = today.getDayOfMonth();

        if (compareDates(bYear, bMonth, bDay, tYear, tMonth, tDay) > 0) {
            System.out.println("Birth date cannot be in the future!");
            pauseEnter(); clearScreen(); return;
        }

        int[] ymd = diffYMD(bYear, bMonth, bDay, tYear, tMonth, tDay);
        String zodiac = zodiacOf(bDay, bMonth);

        System.out.printf("\nToday's date: %02d/%02d/%04d%n", tDay, tMonth, tYear);
        System.out.printf("Your age: %d years, %d months, %d days%n", ymd[0], ymd[1], ymd[2]);
        System.out.println("Your zodiac sign: " + zodiac);

        pauseEnter(); clearScreen();
    }

    /**
     * İki tarih arasındaki farkı (yıl, ay, gün) cinsinden manuel olarak hesaplar.
     * @return {years, months, days}
     */
    private static int[] diffYMD(int y1, int m1, int d1, int y2, int m2, int d2) {
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

    /**
     * İki tarihi karşılaştırır.
     * @return negatif: t1 < t2, 0: eşit, pozitif: t1 > t2
     */
    private static int compareDates(int y1, int m1, int d1, int y2, int m2, int d2) {
        if (y1 != y2) return Integer.compare(y1, y2);
        if (m1 != m2) return Integer.compare(m1, m2);
        return Integer.compare(d1, d2);
    }

    /** Girilen tarihin geçerli olup olmadığını kontrol eder. */
    private static boolean isValidDate(int y, int m, int d) {
        if (y < 1 || y > 9999) return false;
        if (m < 1 || m > 12) return false;
        int dim = daysInMonth(y, m);
        return d >= 1 && d <= dim;
    }

    /** Girilen ayın gün sayısını döndürür (şubat için artık yıl kontrolü içerir). */
    private static int daysInMonth(int y, int m) {
        return switch (m) {
            case 1,3,5,7,8,10,12 -> 31;
            case 4,6,9,11 -> 30;
            case 2 -> isLeap(y) ? 29 : 28;
            default -> 0;
        };
    }

    /** Artık yıl olup olmadığını belirler. */
    private static boolean isLeap(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }

    /** Gün ve aya göre kullanıcının burcunu döndürür. */
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

    /**
     * Kullanıcıdan alınan metindeki kelimeleri özyinelemeli (recursive) olarak tersine çevirir.
     */
    private static void opReverseWordsRecursive() {
        System.out.println("=== Reverse the Words (recursive) ===");
        String input = readString("Enter text: ");
        System.out.println("\nOutput:");
        System.out.println(reverseWordsRecursive(input));
        pauseEnter(); clearScreen();
    }

    /** Metni recursive olarak tersine çevirmek için ana fonksiyon. */
    private static String reverseWordsRecursive(String s){
        return reverseHelper(s, 0, new StringBuilder(), new StringBuilder());
    }

    /** Recursive işlemi yapan yardımcı fonksiyon. */
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

    /** Bir kelimeyi tersine çevirir (kelime uzunluğu 2 veya daha fazla olmalı). */
    private static String processWord(String w){
        return (w.length() >= 2) ? reverseRec(w) : w;
    }

    /** String'i recursive olarak tersine çevirir. */
    private static String reverseRec(String s){
        if (s.length() <= 1) return s;
        return reverseRec(s.substring(1)) + s.charAt(0);
    }

    /** Türkçe harfleri de kapsayacak şekilde karakter kontrolü yapar. */
    private static boolean isLetterOrTurkish(char ch){
        if (Character.isLetter(ch)) return true;
        return "çğıöşüÇĞİÖŞÜ".indexOf(ch) != -1;
    }

    /** Ekranı temizler (ANSI escape kodları ile). */
    private static void clearScreen(){ System.out.print("\u001B[H\u001B[2J"); System.out.flush(); }

    /** Devam etmek için Enter tuşuna basılmasını bekler. */
    private static void pauseEnter(){ System.out.print("\nPress Enter to continue..."); sc.nextLine(); }

    /** Kullanıcıdan string türünde girdi alır. */
    private static String readString(String prompt){ System.out.print(prompt); return sc.nextLine(); }

    /**
     * Kullanıcıdan tamsayı girişi alır.
     * @param prompt ekranda gösterilecek metin
     * @param min kabul edilen minimum değer
     * @param max kabul edilen maksimum değer
     * @return belirtilen aralıkta girilen geçerli bir tamsayı
     */
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
}
