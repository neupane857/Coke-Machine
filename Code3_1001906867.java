/*
 *  Rikesh Neupane 1001906867
 */
package code3_1001906867;

import java.util.Scanner;

/**
 *
 * @author neupa
 */
public class Code3_1001906867
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        CokeMachine MyCokeMachine = new CokeMachine("CSE 1325 Coke Machine", 50, 500, 100);
        int user_choice;
        int CokeSold = 0;
        int AmountOfCokeToAdd;
        int AmountOfChangeToAdd;
        int user_payment;
        final int PencilPrice = MyCokeMachine.getCokePrice();                  
        Scanner in = new Scanner(System.in);
           
        
        do
        {
            
            user_choice = CokeMenu();
            switch(user_choice)
            {
                case 1:
                    if(MyCokeMachine.getInventoryLevel() > 0)
                    {
                        System.out.printf("A coke costs %s\n", displaymoney(PencilPrice));
                        System.out.println("Enter payment");
                        user_payment = in.nextInt();
                        switch(MyCokeMachine.buyACoke(user_payment))
                        {
                            case DISPENSECHANGE:
                                System.out.printf("Here's your coke and your change of %s\n", displaymoney(MyCokeMachine.getChangeDispense()));
                                break;
                            case INSUFFICIENTCHANGE:
                                System.out.println("The Coke does not have enough change and cannot accept your payment\n");
                                break;
                            case INSUFFICIENTFUNDS:
                                System.out.println("Insufficient funds.... returning your payment");
                                break;
                            case EXACTPAYMENT:
                                System.out.println("Here's your Coke");
                                break;
                            case MAXCHANGECAPACITY:
                                System.out.println("You have excedded machine's max capacity - no change was added");
                                System.out.printf("Your change level is %s\n", displaymoney(MyCokeMachine.getChangeLevel()));
                                break;
                        }
                    }
                    else if(MyCokeMachine.getInventoryLevel() == 0)
                    {
                        System.out.println("No more coke in stock");
                    }    
                    break;
                case 2:
                    System.out.println("How much product are you adding to the machine");
                    AmountOfCokeToAdd = in.nextInt();
                    if(MyCokeMachine.incrementInventoryLevel(AmountOfCokeToAdd))
                    {
                        System.out.printf("Your inventory level is %d\n", MyCokeMachine.getInventoryLevel());
                    }
                    else
                    {
                        System.out.println("You have excedded the machine's max capacity - no inventory was added.");
                        System.out.printf("Your inventory level is %d\n",MyCokeMachine.getInventoryLevel());
                    }
                    break;
                case 3:
                    System.out.println("How much change are you adding to the machine");
                    AmountOfChangeToAdd = in.nextInt();
                    if(MyCokeMachine.incrementChangelevel(AmountOfChangeToAdd))
                    {
                        System.out.printf("Your change level is %s\n", displaymoney(MyCokeMachine.getChangeLevel()));
                    }
                    else if(!MyCokeMachine.incrementChangelevel(AmountOfChangeToAdd))
                    {
                        System.out.println("You have excedded machine's max capacity - no change was added");
                        System.out.printf("Your change level is %s\n", displaymoney(MyCokeMachine.getChangeLevel()));
                    }
                    break;
                case 4:
                    System.out.println(MyCokeMachine);
                    break;                   
            }
            CokeSold = MyCokeMachine.getNumberOfCokesSold();
        }while(user_choice != 0);
        if(CokeSold == 0)
        {
            System.out.println("Are you sure you aren't really thirsty and need a coke?\n");
        }
        else
        {
            System.out.println("Bye - enjoy your Coke\n");
        }
        
    }
    public static String displaymoney(int money)
    {
        int x;
        int y;
        
        x = money / 100;
        y = money % 100;
        
        String dollars = String.valueOf(x);
        String cents = String.valueOf(y);
        String TotalMoney = "$" + dollars + "." + cents;
        return TotalMoney;
    }
    public static int CokeMenu()
    {
        int user_choice = 0;
        Scanner in = new Scanner(System.in);
        
        do
        {
            if(user_choice < 0 || user_choice > 4)
            {
                System.out.println("\nInvalid menu Choice. Please choose again.");
            }
            
            System.out.println("0. Walk Away.");
            System.out.println("1. Buy a Coke.");
            System.out.println("2. Restock Machine.");
            System.out.println("3. Add change");
            System.out.println("4. Display Machine Info");
            System.out.print("Choice : ");
            try
            {
            user_choice = in.nextInt();
            }
            catch(Exception e)
            {
                user_choice = -1;
                in.nextLine();
            }
            
        }while(user_choice < 0 || user_choice > 4);
        return user_choice;
    }

    
}