
import java.util.*;

public class UnderAgeCustomer extends Customer
{
   public UnderAgeCustomer()
   {
      super();
   }

   public UnderAgeCustomer(int id, Date arriveTime, int waitTime, String teller, Task[] tasks)
   {
      super(id, arriveTime, waitTime, teller, tasks);
   }
}

