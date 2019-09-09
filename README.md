# BankSimulation
this is a sample bank management system that simulate its customer service, and consequently to obtain information such as customers’ 
average waiting time and tellers’ idle time in one of its branches.
During business hours, a customer can come into the bank and request services from a teller at the
counter. If a customer cannot access a teller immediately because all tellers are busy serving other
customers, then the customer will wait. All waiting customers form a queue based on the time they
arrived. The one who arrived earliest stands at the front of the queue. The one who came in last
stands at the end of the queue. Customers in the queue move forward when a teller becomes
available and the front customer is called to be served. After being served, the customer leaves the
bank.

Customer
A customer is a person who comes to the bank, and brings a number of tasks for the teller to
complete. In the program, a customer is described by following attributes
id int a unique positive integer to identify the customer.
arriveTime Date the time the customer arrives at the bank.
waitTime int the time the customer waited in the queue.
teller String name of the teller who serviced the customer.
tasks Task[] an array of tasks being brought by the customer.
There are two types of special customers: elderly and underAge. Service time for an elderly can
be longer than normal customers. Each elderly has their own extra service time (est).
est int extra service time (in minutes) for the customer

An underAge customer can only be served with certain types of tasks. This means that tasks being
brought in by an underAge may not be processed by the teller. If a task is not processed, then it
should be not counted for the service time.


A task is a service work the customer brings to the bank and requests the teller to complete for
them. A customer may bring a sequence of tasks each time they enter the bank. A task will be one
of following types:
opening an account,  transferring a fund, general inquiry
Each task can be described by the following data attributes
type char type of the task. (‘o’, ‘t’ and i’ can be used to represent the three types)

processTime int the time (in minutes) normally required to process the task.
 If an underAge brings in a transferring a fund task, the teller will not process it.

A teller is a person who works at the counter to serve customers (ie. to complete tasks brought in
by customers). Tellers are divided into two levels: junior and senior.
When two or more tellers are available and there is only one customer waiting, then the teller who
became available at the earliest time, takes the customer. For example, if amy (junior), leanne
(junior) and chris (senior) are 3 tellers who become available at 10:42am, 10:55am and
10:59am respectively, and a customer arrives at 11:01am, amy takes the customer. If a second
customer comes in at 11:03am, leanne takes the customer. It is always the senior teller who takes
the customer when multiple tellers (junior and senior) are available at exactly the same time. For
example if amy, leanne and chris all become available at 10:42am, and there is only one customer
waiting, chris takes the customer.
Although each task has a processTime, the actual time a teller spends to complete the task is
calculated as follows
actualProcessingTime = {

processingTime × 1.2, for a junior teller
processingTime × 0.9, for a senior teller

est for elderly should remind the same regardless the level of the teller. A teller is described by
following data attributes.
name String name of the teller.
level char level of the teller (‘j” for junior and ‘s’ for senior)
idleTime int accumulated time for a teller to wait for a customer to
arrive.
A teller’s name is always just one word and is unique.

Input
The text file (input.txt) contains all required data to run the simulation. The file specifies
name of the branch, a list of tellers (name and level) who work at the counter. At the start of the
simulation, all tellers are available at the counter to serve customers,a sequence of customers (id, underAge/elderly/normal, enterTime and an array of
tasks). Note that enterTime is the time interval (in minutes) between the start of the
simulation and the time the customer arrives at the bank. Each task is described by its
type and processTime. est is appended at the end of the record for an elderly.

Finally, it outputs
 the average customers’ waiting time and
 tellers’ total idle time and
  the goal “at least 95% of customers waited less than 10
minutes” has been achieved.
