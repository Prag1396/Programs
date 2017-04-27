import java.util.Scanner;

public class MyTicTacToe {
    public static Scanner keyboard =new Scanner(System.in);
    public static int[][][] Board= new int[4][4][4];
    public static boolean WINNER_FOUND= false;
    public static int current_player=0;
    public static boolean game_winner= false;
    public static boolean is_draw= false;
    
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe");
        InitializeBoard();
        PrintBoard();
        current_player= 1;
        do {
            if(current_player == 1)
                PrompInputandSet(current_player);
            else
                ComputerPromptInputandSet(current_player);
            game_winner= CheckWinner(current_player);
            if( game_winner == true && is_draw == false) {
                System.out.println("Congrats player" + current_player);
                System.exit(0);
                
            }
            if(is_draw)
            {
                System.out.println("Its a draw");
                System.exit(0);
                
            }
            if(current_player == 1)
                current_player=2;
            else if(current_player == 2)
                current_player=1;
        } while(WINNER_FOUND != true);
    }
    
    
    
    private static void InitializeBoard() {
        for(int level=0; level<=3; level++) {
            for (int row = 0; row <= 3; row++ ) {
                for (int col = 0; col <=3; col++ ) {
                    Board[level][row][col] = 0;
                }
                System.out.println();
            }
        }
    }
    
    private static void PrintBoard() {
        for(int level=0; level<=3; level++) {
            for(int row=0;row<=3;row++) {
                for(int col=0;col<=3;col++) {
                    if (Board[level][row][col] == 1)
                        System.out.print(" X " + " ");
                    if (Board[level][row][col] == 2)
                        System.out.print(" 0 " + " ");
                    if (Board[level][row][col] != 2 && Board[level][row][col] != 1)
                        System.out.print(" - " + " ");
                    
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    private static void ComputerPromptInputandSet(int current_player2) {
        System.out.println("Computer's Chance");
        int possible_row= -1;
        int possible_col= -1;
        int col_correctness=0;
        int row_correctness=0;
        
        int opp_currentplayer = 1;
        for(int level=0; level<=3; level++) {
            for(int row=0;row<=3;row++) {
                row_correctness=0;
                for(int col=0;col<=3;col++) {
                    if(Board[level][row][col] != opp_currentplayer) {
                        possible_row=row;
                        possible_col=col;
                    }
                    else {
                        row_correctness++;
                        
                    }
                }
                if(row_correctness == 3 && Board[level][possible_row][possible_col] != current_player2) {
                    UpdateBoard(level,possible_row, possible_col, current_player2);
                    return;
                }
            }
        }
        
        possible_row= -1;
        possible_col= -1;
        
        for(int level=0;level<=3;level++) {
            for(int row=0; row<=3; row++) {
                col_correctness = 0;
                for(int col=0; col<=3; col++) {
                    if(Board[level][col][row] != opp_currentplayer) {
                        possible_row = col;
                        possible_col = row;
                    }
                    else {
                        col_correctness++;
                    }
                }
                
                if(col_correctness ==3 && Board[level][possible_row][possible_col] != current_player2) {
                    UpdateBoard(level,possible_row, possible_col, current_player2);
                    return;
                }
            }
        }
        
        int left_diagnol_count = 0;
        for(int level=0; level<=3; level++) {
            if(Board[level][0][0] == opp_currentplayer)
                left_diagnol_count++;
            else {
                possible_row = 0;
                possible_col = 0;
            }
            if(Board[level][1][1] == opp_currentplayer)
                left_diagnol_count++;
            else {
                possible_row=1;
                possible_col=1;
            }
            if(Board[level][2][2] == opp_currentplayer)
                left_diagnol_count++;
            else {
                possible_row=2;
                possible_col=2;
            }
            if(Board[level][3][3] == opp_currentplayer)
                left_diagnol_count++;
            else  {
                possible_row=3;
                possible_col=3;
            }
            
            
            if( left_diagnol_count == 3 && Board[level][possible_row][possible_col] != current_player2) {
                UpdateBoard(level,possible_row, possible_col, current_player2);
                return;
            }
        }
        
        int right_diagnol_count = 0;
        for(int level=0;level<=3;level++) {
            if(Board[level][0][3] == opp_currentplayer)
                right_diagnol_count++;
            else {
                possible_row = 0;
                possible_col = 3;
            }
            if(Board[level][1][2] == opp_currentplayer)
                right_diagnol_count++;
            else {
                possible_row=1;
                possible_col=2;
            }
            if(Board[level][2][1] == opp_currentplayer)
                right_diagnol_count++;
            else {
                possible_row=2;
                possible_col=1;
            }
            
            if(Board[level][3][0] == opp_currentplayer)
                right_diagnol_count++;
            
            else {
                possible_row=3;
                possible_col=0;
            }
            
            if( right_diagnol_count == 3 && Board[level][possible_row][possible_col] != current_player2) {
                UpdateBoard(level,possible_row, possible_col, current_player2);
                return;
            }
        }
        
        
        
        
        
        for(int level=0;level<=3;level++) {
            for(int row=0; row<=3; row++) {
                row_correctness = 0;
                for(int col=0; col<=3; col++) {
                    if(Board[level][row][col] != current_player) {
                        possible_row = row;
                        possible_col = col;
                    }
                    else {
                        row_correctness++;
                    }
                }
                if(row_correctness == 3 && Board[level][possible_row][possible_col] == 0) {
                    UpdateBoard(level,possible_row, possible_col, current_player2);
                    return;
                }
            }
        }
        possible_row = -1;
        possible_col = -1;
        
        
        for(int level=0;level<=3;level++) {
            for(int row=0; row<=3; row++) {
                col_correctness = 0;
                for(int col=0;col<=3;col++) {
                    if(Board[level][col][row] != current_player) {
                        possible_row= col;
                        possible_col= row;
                    }
                    else {
                        col_correctness++;
                    }
                }
                if(col_correctness == 3 && Board[level][possible_row][possible_col] == 0) {
                    UpdateBoard(level,possible_row, possible_col, current_player2);
                    return;
                }
            }
        }
        
        
        for(int level=0; level<=3; level++) {
            int left_diagnol_count1 = 0;
            if(Board[level][0][0] == current_player2)
                left_diagnol_count1++;
            else {
                possible_row = 0;
                possible_col = 0;
            }
            if(Board[level][1][1] == current_player2)
                left_diagnol_count1++;
            else {
                possible_row=1;
                possible_col=1;
            }
            if(Board[level][2][2] == current_player2)
                left_diagnol_count1++;
            else {
                possible_row=2;
                possible_col=2;
            }
            if(Board[level][3][3] == current_player2)
                left_diagnol_count1++;
            else  {
                possible_row=3;
                possible_col=3;
            }
            
            
            if( left_diagnol_count1 == 3 && Board[level][possible_row][possible_col] == 0 ) {
                UpdateBoard(level,possible_row, possible_col, current_player2);
                return;
            }
        }
        
        
        for(int level=0;level<=3;level++) {
            int right_diagnol_count1 = 0;
            if(Board[level][0][3] == current_player2)
                right_diagnol_count1++;
            else {
                possible_row = 0;
                possible_col = 3;
            }
            if(Board[level][1][2] == current_player2)
                right_diagnol_count1++;
            else {
                possible_row=1;
                possible_col=2;
            }
            if(Board[level][2][1] == current_player2)
                right_diagnol_count1++;
            else {
                possible_row=2;
                possible_col=1;
            }
            
            if(Board[level][3][0] == current_player2)
                right_diagnol_count1++;
            
            else {
                possible_row=3;
                possible_col=0;
            }
            
            if( right_diagnol_count1 == 3 && Board[level][possible_row][possible_col] == 0 ) {
                UpdateBoard(level,possible_row, possible_col, current_player2);
                return;
            }
        }
        
        for(int level=0;level<=3;level++) {
            for(int row=0; row<=3; row++) {
                for(int col=0; col<=3; col++) {
                    if(Board[level][row][col] != 1 && Board[level][row][col] != 2) {
                        UpdateBoard(level,row,col, current_player2);
                        return;
                    }
                }
            }
        }
        System.out.println("Computer moved");
        
    }
    
    private static void UpdateBoard(int level,int row, int col, int cpr) {
        if(cpr == 1)
            Board[level][row][col] =1;
        if(cpr == 2)
            Board[level][row][col] =2;
        PrintBoard();
        
    }
    
    
    private static boolean CheckWinner(int current_player) {
        boolean row_check = true;
        int scol;
        for(int level=0;level<=3;level++) {
            for(int row=0;row<=3;row++) {
                row_check= true;
                for(scol=0;scol<=3;scol++) {
                    if(Board[level][row][scol] != current_player) {
                        row_check = false;
                        
                    }
                }
                if(row_check != false && scol == 4) {
                    System.out.println("Row Strike");
                    WINNER_FOUND = true;
                    return true;
                }
            }
        }
        
        for(int level=0; level<=3; level++) {
            for(int row=0;row<=3;row++) {
                row_check = true;
                for(int col=0;col<=3;col++) {
                    if(Board[level][col][row] != current_player) {
                        row_check = false;
                    }
                }
                if(row_check != false) {
                    System.out.println("Coloumn Strike");
                    WINNER_FOUND = true;
                    return true;
                }
                
            }
        }
        for(int level=0;level<=3;level++) {
            if(Board[level][0][0] == current_player && Board[level][1][1] == current_player && Board[level][2][2] == current_player && Board[level][3][3] == current_player) {
                WINNER_FOUND = true;
                return true;
                
            }
        }
        
        for(int level=0;level<=3;level++) {
            if(Board[level][0][3] == current_player && Board[level][1][2] == current_player && Board[level][2][1] == current_player && Board[level][3][0] == current_player) {
                WINNER_FOUND = true;
                return true;
                
            }
        }
        
        for(int level=0;level<=3;level++) {
            for(int row=0; row<=3; row++) {
                for(int col=0;col<=3; col++) {
                    if(Board[level][row][col] != 1 || Board[level][row][col] != 2) {
                        
                        return false;
                    }
                }
                if(row == 3)
                    is_draw = true;
                return true;
            }
        }
        
        return false;
    }
    
    
    
    private static void PrompInputandSet(int cp) {
        System.out.println("Chance: Player " + cp + " Enter the row and col as number 0 to 3. ");
        boolean valid_entry= false;
        do {
            System.out.print("Level: ");
            int level = keyboard.nextInt();
            System.out.print("Row: ");
            int row = keyboard.nextInt();
            System.out.print("Column: ");
            int col = keyboard.nextInt();
            
            if(level>=0 && level<=3 && row>=0 && row<=3 && col >=0 && col<=3 && Board[level][row][col] == 0) {
                valid_entry = true;
                UpdateBoard(level,row,col,cp);
                
            } else {
                System.out.println("Values entered are not valid");
            }
        }    while(valid_entry == false);
    }
}

