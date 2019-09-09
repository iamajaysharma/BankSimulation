
import java.util.*;

public class Customer
{
   private int id;
   private Date arriveTime;
   private int waitTime;
   private String teller;
   private Task[] tasks;

   public Customer()
   {
      id = 0;
      arriveTime = new Date();
      waitTime = 0;
      teller = "";
      tasks = new Task[10];
   }
   public Customer(int id, Date arriveTime, int waitTime,String teller, Task[] tasks)
   {
      this.id = id;
      this.arriveTime = arriveTime;
      this.waitTime = waitTime;
      this.teller = teller;
      this.tasks = tasks;
   }

   public int getID()
   {
      return id;
   }

   public Date getArriveTime()
   {
      return arriveTime;
   }

   public void setArriveTime(Date arriveTime)
   {
      this.arriveTime = arriveTime;
   }

   public int getWaitTime()
   {
      return waitTime;
   }

   public void setWaitTime(int waitTime)
   {
      this.waitTime = waitTime;
   }

   public String getTeller()
   {
      return teller;
   }

   public void setTeller(String teller)
   {
      this.teller = teller;
   }

   public Task[] getTasks()
   {
      return tasks;
   }

   public void setTasks(Task[] tasks)
   {
      this.tasks  = tasks;
   }

   public String toString()
   {
      String description = "";
      description = getClass().getName()
                    + "[id: " +id
                    + "\narriveTime: " +arriveTime
                    + "\nwaitTime: " +waitTime
                    + "\nteller: " +teller
                    + "\ntasks: " +Arrays.toString(tasks)
                    + "]";
      return description;
   }

}

