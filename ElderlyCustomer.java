
import java.util.*;

public class ElderlyCustomer extends Customer
{
   private int est;
   
   public ElderlyCustomer()
   {
      super();
      est = 0;
   }
   public ElderlyCustomer(int id, Date arriveTime, int waitTime, String teller, Task[] tasks, int est)
   {
      super(id, arriveTime, waitTime, teller, tasks);
      this.est = est;
   }

   public int getEST()
   {
      return est;
   }
   
   public void setEST(int est)
   {
   		this.est = est;
  
   }

}

