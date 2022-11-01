/*
 *  Rikesh Neupane 1001906867
 */
package code3_1001906867;

/**
 *
 * @author neupa
 */
public class CokeMachine
{
    public enum Action
    {
        DISPENSECHANGE,
        INSUFFICIENTCHANGE,
        INSUFFICIENTFUNDS,
        EXACTPAYMENT,
        MAXCHANGECAPACITY
    }
    
    private String MachineName;
    private int changelevel;
    private int maxChangeCapacity = 5000;
    private int inventorylevel;
    private int maxInventoryCapacity = 100;    
    private int CokePrice;
    private int ChangeDispensed;
    private static int numberOfCokesSold = 0;
    
    CokeMachine(String MachineName, int CokePrice, int changelevel, int inventorylevel)
    {
        this.MachineName = MachineName;
        this.CokePrice = CokePrice;
        this.changelevel = changelevel;
        this.inventorylevel = inventorylevel;
        
    }
    public String getMachineName()
    {
        return MachineName;
    }   
    public int getChangeLevel()
    {
        return changelevel;
    }    
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
    }   
    public int getInventoryLevel()
    {
        return inventorylevel;
    }
    public int getMaxInventory()
    {
        return maxInventoryCapacity;
    }
    public int getCokePrice()
    {
        return CokePrice;
    }
    public int getChangeDispense()
    {
        return ChangeDispensed; 
    }
    public int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }
    public boolean incrementInventoryLevel(int amountToadd)
    {
        boolean flag;
        int temp = inventorylevel;
        inventorylevel += amountToadd;
        
        if(temp == inventorylevel || (inventorylevel > maxInventoryCapacity))
        {
            inventorylevel -= amountToadd;
            flag = false;
        }
        else
        {
            flag = true;
        }
        return flag;
    }
    public boolean incrementChangelevel(int amountToadd)
    {
        boolean flag;
        int temp =  changelevel;
        changelevel += amountToadd; 
        
        if(temp == changelevel || (changelevel >= maxChangeCapacity+1))
        {
            changelevel = 0;
            changelevel = temp - ChangeDispensed;//amountToadd;
            flag = false;
        }
        else
        {
            flag = true;
        }       
        return flag;
    }  
    public Action buyACoke(int payment)
    {
        int tempChangeDispensed = payment - CokePrice;
        if(payment == CokePrice)
        {
            changelevel += payment;
            inventorylevel--;
            numberOfCokesSold++;
            ChangeDispensed = tempChangeDispensed;
            return Action.EXACTPAYMENT;
        }
        else if(tempChangeDispensed > changelevel)
        {
            return Action.INSUFFICIENTCHANGE;
        }
        else if(payment > CokePrice)
        {
            inventorylevel--;
            changelevel -= tempChangeDispensed;
            changelevel += payment;
            numberOfCokesSold++;
            ChangeDispensed = tempChangeDispensed;
            if(!incrementChangelevel(payment))
            {
                return Action.MAXCHANGECAPACITY;
            }
            else
            {
                return Action.DISPENSECHANGE;
            }           
        }
        else if(payment < CokePrice)
        {
            ChangeDispensed = tempChangeDispensed;
            return Action.INSUFFICIENTFUNDS;
        }
        return Action.EXACTPAYMENT;
    }
    public String toString()
    {
       return String.format("Machine Name \t\t\t%-36s \nCurrent Inventory Level \t%-6d \nCurrent Change Level \t\t%-6d \nLast Change Dispensed \t\t%-7d \nInventory Capacity \t\t%-12d \nChange Capacity \t\t%-16d \nCoke Price \t\t\t%-19d\nCoke Sold \t\t\t%-19d",MachineName,inventorylevel,changelevel,ChangeDispensed,maxInventoryCapacity,maxChangeCapacity,CokePrice,numberOfCokesSold);    
    }
    
    
}
