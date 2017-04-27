import java.util.*;
import java.io.*;

public class PlayCraps {
    static int bank;
    static int bet;
    static int roll1=0;

    
    
    
    public static void main(String[] args) {
        String userchoice;
        System.out.println("Welcome,Lets play a Game called Craps");
        System.out.println("How many chips do you want?");
        Scanner scan=new Scanner(System.in); //Inputs the amount of chips from the user
        bank=scan.nextInt();
        do{
            System.out.println("Enter bet: ");
            Scanner scan1=new Scanner(System.in); //Inputs the bet
            bet=scan1.nextInt();
            if(bet>bank) {
                System.out.println("Please enter a valid bet");
                bet=scan1.nextInt();
            }
            Check(roll1,bet);
            System.out.println("Do you wish to continue?(Yes/No)"); // Asks the user if he wants to continue
            Scanner scan2=new Scanner(System.in);
            userchoice=scan2.next();
            
        } while(bank>0 && userchoice.equals("Yes"));
        
       
    }
    
    
    public static void Check(int roll2,int bet) {
        //Rolls the dice and generates random number between 1-6
        int Dice1,Dice2;
        Dice1=(int) (Math.random()*6)+1;
        Dice2=(int) (Math.random()*6) +1;
        roll2=Dice1+Dice2;
        System.out.println(Dice1+","+ Dice2);
        roller(roll2,bet); //Passes the roll and bet to function roller
        
        
    }
    
    public static void roller(int roll2, int bet) {
        // Runs according to the rules of the game
        // accepts the roll and the bet from the method Check
        
        if(roll2==7 || roll2==11) {
            //Checks if roll is 7 or 11
            System.out.println("You win");
            bank=bank+bet;
            System.out.println("Your Balance: "+bank);
            
            
            
        }
        else if(roll2==2 || roll2==3 || roll2==12) {
            //Checks if roll is 2 or 3 or 12
            System.out.println("You lose");
            bank=bank-bet;
            System.out.println("Your Balance: "+bank);
            
            
            
        }
        else {
            //Generates random number again by rolling the dice
            System.out.println("The point is: "+roll2);

            int Dice1=(int) (Math.random()*6)+1;
            int Dice2=(int) (Math.random()*6) +1;
            
            int roll3=Dice1+Dice2;
            System.out.println("You rolled: "+roll3);
            
            while(roll3 != 7) {
                if(roll2==roll3)
                {
                    System.out.println("You win");
                    bank=bank+bet;
                    System.out.println("Your Balance: "+bank);
                    
                    break;
                }
                //If roll is not matched to the previous roll, roll the dice again
                
                Dice1=(int) (Math.random()*6)+1;
                Dice2=(int) (Math.random()*6) +1;
                roll3=Dice1+Dice2;
                System.out.println("You rolled: "+roll3);
                
            }
            if(roll3==7)
            {
                System.out.println("You lose");
                bank=bank-bet;
                System.out.println("Your Balance: "+bank);
                
                
            }
            
        }
        
    }
    
}