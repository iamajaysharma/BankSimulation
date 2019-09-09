
import java.util.*;

public class Teller
{
     private String name;
     private char level;
     private int idleTime;
     private boolean isIdle;
     private Customer currentCustomer;
     private Task currentTask[];
     private int numberOfTask;
     private int processedTime;

     public Teller()
     {
        name = "";
        level = '*';
        idleTime = 0;
        isIdle = true;
        currentCustomer = null;
     }

     public Teller(String name, char level, int idleTime)
     {
        this.name = name;
        this.level = level;
        this.idleTime = idleTime;
        isIdle = true;
        currentCustomer = null;
     }
    
     public String getName()
     {
        return name;
     }

     public char getLevel()
     {
        return level;
     }

     public void setLevel(char level)
     {
        this.level = level;
     }

     public int getIdleTime()
     {
        return idleTime;
     }

     public void setIdleTime(int idleTime)
     {
        this.idleTime = idleTime;
     }

     public boolean getIdleStatus()
     {
        return isIdle;
     }

     public void setIdle(boolean isIdle)
     {
        this.isIdle = isIdle;
     }

     public Customer getCurrentCustomer()
     {
        return currentCustomer;
     }

     public void setCurrentCustomer(Customer setCustomer)
     {
        this.currentCustomer = setCustomer;
     }

     
     public Task [] getCurrentTask()
     {
        return currentTask;
     }

     public void setCurrentTask(Task [] currentTask)
     {
        this.currentTask = currentTask;
     }

     public int getNumberOfTask()
     {
        return numberOfTask;
     }

     public void setNumberOfTask(int numberOfTask)
     {
        this.numberOfTask = numberOfTask;
     }

     public int getProcessedTime()
     {
        return processedTime;
     }

     public void setProcessedTime(int processedTime)
     {
        this.processedTime = processedTime;
     }

     public String toString()
     {
         String description = "";
         description = getClass().getName()
                       + "[Name: " +name
                       + "\nLevel: " +level
                       + "\nidleTime: " +idleTime
                       + "]";
         return description;
    }
 }




