public class Task
{
     private char type;
     private int processTime;
     
     public Task()
     {
        type = '*';
        processTime = 0;
     }

     public Task(char type, int processTime)
     {
        this.type = type;
        this.processTime = processTime;
     }

     public char getType()
     {
        return type;
     }

     public void setType(char type)
     {
        this.type = type;
     }

     public int getProcessTime()
     {
        return processTime;
     }

     public void setProcessTime(int processTime)
     {
        this.processTime = processTime;
     }
                                        
     public String toString()
     {
        String description = "";
        description = getClass().getName()
                      + "[type: " +type
                      + "\nprocessTime: " +processTime
                      + "]";
        return description;
     }  
                                        
}

