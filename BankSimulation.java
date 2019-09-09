
import java.util.*;
import java.io.*;
import java.text.*;

public class BankSimulation
{
   public static Teller[] teller;
   public static void main(String args[])throws IOException
   {
         
      
       Customer [] customer;
       Customer [] customerQueue;
       int queueCounter = 0;
       int end = -1;
    
       SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
       Date startTime = new Date();
       String startTimeS = format1.format(startTime);
   
          Calendar calender = Calendar.getInstance();
          BankSimulation bankSimulation = new BankSimulation();
      
          BufferedReader bufferedReader = null;
          PrintWriter out = null;
          
          int tellerNumber = 3;
          int customerNumber = 17;
      
          teller = new Teller[tellerNumber];
          customer = new Customer[customerNumber];
          customerQueue = new Customer[customerNumber];
          try
          {
         
            try
            {
               File fileRead = new File(args[0]);
               bufferedReader = new BufferedReader(new FileReader(fileRead));
            }
            catch(FileNotFoundException e)
            {
               System.out.println("input file not found");
               return;
            }
            
            try
            {
               File fileWrite = new File(args[1]);
               out = new PrintWriter(new FileWriter(fileWrite));
            }
            catch(FileNotFoundException e)
            {
               System.out.println("Ouput file not found");
            }

            String branchName = bufferedReader.readLine();
            String inputLine = "";
            String custData = "";
            String taskString = "";
            
            
            out.println("Customer Service Simulation\n");
            out.println( "Branch: " +branchName);

            int customerID = 0;
            int est = 0;
            int enterTime = 0;
            String customerType = "";
            Task[] task = new Task[10];
         
            Date arriveTime = null;  
            out.println("Starting Time:" +startTimeS +"\n");


			for(int i = 0; i < tellerNumber ; ++i)
           {
                inputLine = bufferedReader.readLine().trim();
                String tellerName = inputLine.substring(0,(inputLine.indexOf(" ")+1));
                char  level = inputLine.substring(inputLine.indexOf(" ")+1).charAt(0);            
                teller[i] = new Teller(tellerName, level, 0);
                out.print(" " +tellerName+ " (");
                
                if(level == 's')
                {
                   out.print("senior)");
                }
                else
                {
                   out.print("junior)");
                }
           }        
           out.println("\n");

        
          for(int i = 0; i < customerNumber; ++i)
          {

            inputLine =  bufferedReader.readLine().trim();
            
           custData = inputLine.substring(0,inputLine.indexOf("["));
           taskString = inputLine.substring(inputLine.indexOf("[")).trim();
           
           customerID =Integer.parseInt(custData.substring(0,custData.indexOf(" ")).trim());
           customerType = custData.substring(custData.indexOf(" "),custData.lastIndexOf(" ")).trim();            
           enterTime = Integer.parseInt(custData.substring(custData.lastIndexOf(" ")).trim());            
           
           
                try{
                
                while(taskString.indexOf("]")!=0)
                {
                	int y = 0;
                	taskString = taskString.substring(taskString.indexOf("("));
                	char taskType = taskString.charAt(1);
                	int taskProcessingTime = Integer.parseInt(taskString.substring(taskString.indexOf(",")+1,taskString.indexOf(")")).trim());
                	
                	 task[y]= new Task(taskType,taskProcessingTime);
                	taskString = taskString.substring(taskString.indexOf(")")-1).trim();
                	y=y+1;
                }}catch(StringIndexOutOfBoundsException e)
                {
                }
            
            calender.setTime(startTime);
            calender.add(Calendar.MINUTE, enterTime);
            arriveTime = calender.getTime();


            if (customerType.equals("normal"))
            {
               customer[i] = new Customer(customerID, arriveTime, 0, "",  task);
              
            }
            else if (customerType.equals("underAge"))
            {
               customer[i] = new UnderAgeCustomer(customerID, arriveTime, 0, "",  task);
               
            }
            else if (customerType.equals("elderly"))
            {
               
               est = Integer.parseInt(taskString.substring(taskString.lastIndexOf(" ")).trim());     
               customer[i]  = new ElderlyCustomer(customerID, arriveTime, 0, "", task, est);
                 
            }

         } 
         
         calender.setTime(startTime);
         
         while(end != 0)
         {
            Date currentTime = calender.getTime();
            String time = format2.format(currentTime);
            out.println(time);

            for(int i = 0; i < teller.length; ++i)
            {
               
               if(teller[i].getCurrentCustomer() != null)
               {
                  int taskNumber = teller[i].getNumberOfTask();
                  int processedTime = teller[i].getProcessedTime();
                  int cTask = teller[i].getCurrentTask().length;
                  Task custTask = teller[i].getCurrentTask()[taskNumber];

                  if(custTask.getProcessTime() == processedTime)
                  {
                     if(taskNumber < (cTask - 1))
                     {
                        teller[i].setNumberOfTask(taskNumber + 1);
                        teller[i].setProcessedTime(0);
                     }
                     else
                     {
                        teller[i].setCurrentTask(null);
                        teller[i].setCurrentCustomer(null);
                        teller[i].setIdle(true);
                     }
                  }
                  else
                  {
                     teller[i].setProcessedTime(processedTime - 1);
                  }
               }
            }

            int a = 0;
            while(customerQueue[a] != null)
            {
                Teller t = bankSimulation.idleTeller();
               if(t != null)
               {
                  t.setIdle(false);
                  customerQueue[a].setTeller(t.getName());

                  t.setCurrentCustomer(customerQueue[a]);
                  Task tasks[] = customerQueue[a].getTasks();

                  t.setCurrentTask(tasks);
                  t.setNumberOfTask(0);

                  t.setProcessedTime(1);

                  for(int i = 0; i < queueCounter; ++i)
                  {
                     customerQueue[i] = customerQueue[i+1];
                  }
                 --queueCounter;
               }
               else
               {
                  break;
               }
               ++a;
            }

            for(int i = 0; i < customer.length; ++i)
            {
               if(customer[i].getArriveTime().equals(currentTime))
               {
                  end = 1;
                  Teller t = bankSimulation.idleTeller();
                  if(t != null)
                  {
                      t.setIdle(false);
                      customer[i].setTeller(t.getName());
                      t.setCurrentCustomer(customer[i]);
                      
                      Task tasks[] = customer[i].getTasks();
                      t.setCurrentTask(tasks);
                      t.setNumberOfTask(0);

                      t.setProcessedTime(1);
                  }
                  else
                  {
                     customerQueue[++queueCounter] = customer[i];
                  }
               }
            }

            int counter  = 0;
            for(int i = 0; i < teller.length; ++i)
            {
               Teller t = teller[i];
               out.print(t.getName());
               if(t.getIdleStatus())
               {
                  out.println(" idle");
                  ++counter;
                  t.setIdleTime(t.getIdleTime() + 1);
               }
               else
               {
                  Customer c = t.getCurrentCustomer();
                  out.print(" customer" +c.getID()+ "/");
                  int taskNumber = t.getNumberOfTask();

                  Task [] tasks  = t.getCurrentTask();
                  Task ta = t.getCurrentTask()[taskNumber];
                  out.println(" task" +ta.getType());
               }
            }

            if(counter == teller.length && end == 1)
            {
               end = 0;
            }

            out.print("\nQueue (number of waiting " +queueCounter+ ")");
            a = 0;

            while(customerQueue[a] != null)
            {
               out.print(" /customer " +customerQueue[a].getID()); 
            }

            out.println();
            out.println("------------------------------------------");
            calender.add(Calendar.MINUTE, 1); // add one minute 
         }
        
         System.out.println("Output file generated successfully !!");

      }
      catch(IOException e)
      {
         System.out.println("Null caught");
      }
      catch(NullPointerException e)
      {
         System.out.println("xxx");
      }
      bufferedReader.close();
      out.close();
   }

//to find the idle teller from the teller array
   public Teller idleTeller()
   {
      Teller seniorT = seniorIdleTeller();
      if(seniorT != null)
      {
         return seniorT;
      }
      for(int i = 0; i < teller.length; ++i)
      {
         Teller t = teller[i];
         if(t.getIdleStatus())
         {
            return t;
         }
      }
      return null;
   }

// finding senior idle teller
   private Teller seniorIdleTeller()
   {
      for(int i = 0; i < teller.length; ++i)
      {
         Teller t = teller[i];
         if(t.getLevel() == 's')
         {
            if(t.getIdleStatus())
            {
               return t;
            }
         }
      }
      return null;
   }

  
   

}

