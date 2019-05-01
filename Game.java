import java.util.Random;
import java.util.Scanner;

class Game{

    char[][] board;
    int numOfSymbol; //the number of symbol on the board
    char turnOfPlayer; //who's turn

    public Game(){
        board = new char[3][3];
        numOfSymbol = 0;
        turnOfPlayer = 'X';
    }

    public void draw(){
        System.out.print("\n");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '\0')
                    System.out.print(" ");
                else
                    System.out.print(board[i][j]);

                if(j != board[0].length - 1)
                    System.out.print("|");
            }

            if(i != board.length - 1)
                System.out.print("\n-----\n");
        }
        System.out.print("\n");
    }

    /*
    put()
    1.check the coordinate is on the board
    2.check the coordinate is empty
    3.set the coordinate and change the turn
    
    */
    public void put(char symbol, int i, int j){

        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length){
            if(turnOfPlayer == 'X')
                System.out.println("This place is not on the board");
            return;
        }

        if(board[i][j] != '\0'){
            if(turnOfPlayer == 'X')
                System.out.println("This place alreadly have a symbol");
            return;
        }
        else{
            board[i][j] = symbol;
            numOfSymbol++;
            if(symbol == 'X') turnOfPlayer = 'O';
            else turnOfPlayer = 'X';

            System.out.println("Player " + turnOfPlayer + " put the symbol at "+ i +" " + j);
        }
    }

    /*
    hasWon()
    This game is relatively simple, so I use brute force. 
    If the board is large, I should use DFS with backtracking
    */
    public boolean hasWon(char symbol){
        for(int i = 0; i < board.length; i++){
            if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
        }

        for(int j = 0; j < board.length; j++){
            if(board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol)
                return true;
        }

        if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;

        return false;
        
    }

    /*
    isFull()
    Just check the number of symbol. No need to trave the board
    */
    public boolean isFull(){
        if(numOfSymbol == board.length * board[0].length)
            return true;
        return false;
    }

    public void play(){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int x = 0, y = 0;

        while(true){
            if(isFull()){
                System.out.println("The board is full, please proceed to the next game.");
                break;
            }

            if(turnOfPlayer == 'X'){
                draw();
                System.out.println("It is your turn, please input the coordinate");
                

                while (true) {
                    try {
                        System.out.println("Your x is: ");
                        x = sc.nextInt();
                        System.out.println("Your y is: ");
                        y = sc.nextInt();
                        break;
                    } catch (Exception e) {
                        sc = new Scanner(System.in);
                        System.out.println("Please input digit.");
                    }
                }

                System.out.println(x);
                System.out.println(y);

                put('X', x, y);
            }
            else{
                x = rand.nextInt(3);
                y = rand.nextInt(3);
                put('O', x, y);
            }

            if(numOfSymbol >= 5)
                if(hasWon(turnOfPlayer)){
                    System.out.println("The player " + turnOfPlayer + " has won!");
                    break;
                }
        }
    }
}