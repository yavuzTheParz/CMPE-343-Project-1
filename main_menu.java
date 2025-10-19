import java.util.Scanner;
// ascii animation play APIs
import java.io.*;
import java.nio.file.*;
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
 *   <li>Single-player vs random AI or two-players</li>
 *   <li>Win, draw, forfeit detection</li>
 * </ul>
 *
 * <p><b>Usage:</b> Compile with {@code javac ConnectFour.java} and run with {@code java ConnectFour}.
 *
 * @author Yavuz Selim Yaşar, Volkan Dinç, Kemal Ege İncereis, İdil Zeren Çoban
 * @version 1.0
 * @since 1.0
 */

class main_menu
{

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

    /**
     * Program entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args)
    {
        //writeMenu();
        playAnimation();  
    }
    public static void clearConsole() 
    {
     	System.out.print("\033[H\033[2J");
    	System.out.flush();
    }
    public static void writeMenu()
    {
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
                valid = true;
                break;
            case "B":
                valid = true;
                break;
            case "C":
                valid = true;
                break;
            case "D":
                D();
                valid = true;
                break;
            case "E":
                System.out.println("E");
                System.exit(0);
            default:
                clearConsole() ;
                System.out.println("Invalid input input, please try again with a valid input");
            
            }
            if(valid)
                break;                
        }
        
    }
    public static void playAnimation() {
    List<String> frames = getFrames();

    int fps = 10;
    long frameTime = 1000 / fps;

    // Terminali her şartta eski haline döndür
    try {
        // İmleci gizle, rengi kırmızı yap
        System.out.print(HIDE_CURSOR);
        System.out.print(ANSI_RED);

        int i = 0;
        for (String frame : frames) {
            long t0 = System.nanoTime();

            // Temizle + home (önce temizle, sonra home)
            System.out.print(CLEAR);
            System.out.print(HOME);

            if(i == 12)
                System.out.print(ANSI_CYAN);
   

            // Frame’i yaz
            System.out.print(frame);
            System.out.flush();

            long elapsedMs = (System.nanoTime() - t0) / 1_000_000;
            long sleepMs = Math.max(0, frameTime - elapsedMs);
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // animasyonu kibarca kes
            }
            i++;

        }

        // Animasyon bitti: renk resetle, imleci göster, byline normal renkte
        System.out.print(ANSI_RESET);
        System.out.print(SHOW_CURSOR);
        System.out.println("by:  Yavuz Selim Yaşar, Volkan Dinç,  Kemal Ege İncereis,  İdil Zeren Çoban");
    } finally {
        // Her koşulda terminal toparlansın (exception vs.)
        System.out.print(ANSI_RESET);
        System.out.print(SHOW_CURSOR);
        System.out.flush();
    }

    // Menüye geç
    writeMenu();
}

    



    private static List<String> getFrames() 
    {

        String banner = """
        /$$       /$$  /$$$$$$$$ /$$$       /$$$$$$    /$$$$$$ /$$      /$$ /$$$$$$$$
        | $$  /$ | $$| $$$_____/| $$       /$$__  $$ /$$__  $$| $$$    /$$$| $$_____/
        | $$ /$$$| $$| $$$      | $$      | $$  \\__/| $$     || $$ $  /$$$$| $$$$$       
        | $$/$$ $$ $$| $$$$$$   | $$      | $$      | $$  | $$| $$ $$/$$ $$| $$$$$   
        | $$$$_  $$$$| $$$__/   | $$      | $$      | $$  | $$| $$  $$$| $$| $$__/   
        | $$$/ \\   $$| $$       | $$      | $$    $$| $$  | $$| $$\\  $ | $$| $$      
        | $$/   \\   $| $$$$$$$$ | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \\/  | $$| $$$$$$$$
        |__/     \\__/|________/ |________/ \\______/  \\______/ |__/     |__/|________/
""";

        String[] f = new String[] {
            //First 7 frames
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
            //Second 7 frames
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
                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
            """,            """                                                                                                    



                                                                                                    
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                
                                                                                                                                                                                                            
                                                                                                                                                                                                   
                                                                                                                                                                                                       
                                                                                                                                                                                                 
                                                                                                                                                                                                    
                                                                                                                                                                                           
                                                                                                                                                                  
                                                                                                                                                                                           
                                                                                                                                                                                 
                                                                                                                                                                                
                                                                                                                                                                                    
                                                                                                                                                                                     
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
    * Starts the Connect Four session: asks for board size and game mode, then runs the main loop
    * until a terminal state (win/draw/forfeit) is reached.
    *
    * <p><b>Side effects:</b> Clears the console, prints the ASCII UI, and reads from {@code System.in}.
    *
    * @implNote The computer opponent plays uniformly random legal columns (no heuristic).
    */
    private static void D()
    {
        clearConsole();
        while (true) {
            System.out.print("[A] Connect Four\n[B] Return to Main Menu\n"); 
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            switch (s) {
                case "A":
                    startConnectFour();
                    break;
                case "B":
                    clearConsole();
                    //to_main_menu();
                    return;
                default:
                    System.out.println("Invalid input, try again.");
                    waitForEnter();
            }
        }
    }

        /**
         * Connect Four – Java Console Edition (ASCII UI)
         *
         * Features required by the assignment:
         *  - Option D in the main menu starts Connect Four [9].
         *  - Board size selection: 5x4, 6x5, or 7x6 (Cols x Rows).
         *  - Game mode: Single-player (vs. random-move computer) or Two-players.
         *  - Players drop discs into columns; full columns must be reselected.
         *  - After each move, the screen refreshes and shows the updated board.
         *  - Game ends on a connect-four, a full board (draw), or if a player quits (forfeit).
         *  - Single-player AI plays random legal moves (no heuristic/minimax yet).
         *
         * Compile:  javac ConnectFour.java
         * Run:      java ConnectFour
         */
        // ====== ANSI helpers (colors + clear screen) ======
        

        // Board symbols
        private static final char EMPTY = '.';
        private static final char P1 = 'X';
        private static final char P2 = 'O';

        private static final Scanner SC = new Scanner(System.in);
        private static final Random RNG = new Random();

        // ====== Game bootstrap ======
        private static void startConnectFour() {
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
                    waitForEnter();
                    return;
                }
                if (hasConnectFour(board, P2)) {
                    boolean[][] mask = findWinningFour(board, P2);
                    clearAndBanner();
                    printGameHeader(cols, rows, mode, current, lastInfo);
                    printBoard(board, mask);

                    waitForEnter();
                    return;
                }
                if (isBoardFull(board)) {
                    System.out.println(ANSI_MAGENTA + ANSI_BOLD + "Draw, no more legal moves!" + ANSI_RESET);
                    waitForEnter();
                    return;
                }

                if (single && current == P2 && !humanStarts) {
                    // Computer turn when computer is P2 and started OR when current is computer side
                    int aiCol = randomLegalColumn(board);
                    int row = dropDiscAnimated(board, aiCol, P2, cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column "+ (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }
                if (single && current == P2 && humanStarts) {
                    // In single-player where human is P1, P2 is computer
                    int aiCol = randomLegalColumn(board);
                    int row = dropDiscAnimated(board, aiCol, P2, cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column "+ (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }

                // Human move (either in 2P or it's the human's side in single)
                String who = (current == P1) ? "P1 (X)" : (single ? "Computer" : "P2 (O)");
                if (single && current == P2) {
                    // If somehow we're here, it means humanStarts==false but we already handled AI. Just in case.
                    int aiCol = randomLegalColumn(board);
                    int row = dropDiscAnimated(board, aiCol, P2,cols, rows, mode, current, lastInfo);
                    lastInfo = "Computer chose " + "column " + (aiCol + 1) + ".";
                    current = switchPlayer(current);
                    continue;
                }

                System.out.println(ANSI_BOLD + who + ANSI_RESET + ", make your move. Column (1-" + cols + ") or Q=Forfeit:");
                String in = prompt("> ").trim();
                if (in.equalsIgnoreCase("Q")) {
                    System.out.println(forfeitLine(current));
                    waitForEnter();
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
                    waitForEnter();
                    return;
                }
                if (isBoardFull(board)) {
                    clearAndBanner();
                    printGameHeader(cols, rows, mode, current, lastInfo);
                    printBoard(board);
                    System.out.println(ANSI_MAGENTA + ANSI_BOLD + "Draw, no more legal moves!" + ANSI_RESET);
                    waitForEnter();
                    return;
                }

                current = switchPlayer(current);
            }
        }

        // ====== Menu helpers ======
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
                        waitForEnter();
                }
            }
        }


        private static char pickGameMode() {
            while (true) {
                clearAndBanner();
                System.out.println(ANSI_CYAN + ANSI_BOLD + "Pick game mode" + ANSI_RESET);
                System.out.println("1) Single Player (Against computer – bilgisayar rastgele oynar)");
                System.out.println("2) Two Players");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();

                switch (s) {
                    case "1": return 's';
                    case "2": return 't';
                    default:
                        System.out.println("Invalid input, please try again.");
                        waitForEnter();
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
                        waitForEnter();
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

        // ====== IO utils ======
        private static void waitForEnter() {
            System.out.println("\nPress Enter for continue…");
            SC.nextLine();
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

                    if (isFalling) {
                        // Geçici taşın rengi
                        if (fallCh == P1) cell = ANSI_RED + "X" + ANSI_RESET;
                        else if (fallCh == P2) cell = ANSI_YELLOW + "O" + ANSI_RESET;
                        else cell = String.valueOf(fallCh);
                    } else {
                        if (ch == P1) cell = ANSI_RED + "X" + ANSI_RESET;
                        else if (ch == P2) cell = ANSI_YELLOW + "O" + ANSI_RESET;
                        else cell = " ";
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
                    if (ch == P1) cell = (hi ? ANSI_GREEN + ANSI_BOLD : ANSI_RED) + "X" + ANSI_RESET;
                    else if (ch == P2) cell = (hi ? ANSI_GREEN + ANSI_BOLD : ANSI_YELLOW) + "O" + ANSI_RESET;
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

    }



