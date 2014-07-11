
Exercise 10
===========

Part 1
------
You are going to write an aspect that prints to log before a method is started.

1. Write a **Pointcut** that joints to all the methods inside *"springcourse.exercises.exercise10"* packages including all sub packages.<br/>
   Write another **Pointcut** that joints to all methods belonging to *controllers*.

2. Write a **Before Advice** that prints to log every time a method is started with the following information
    - Target class name
    - Target method name
    - Target method arguments<br/>
   ALSO<br/>
    - The **advice** should be executed on **Pointcut** 1A **excluding** 1B<br/>
      Meaning executed on all of this project directories and sub directories **EXCEPT** methods belonging to controllers!!!

3. Once you put it all together (do not forget the configuration) then execute any of the **LibraryControllerTest** tests.<br/>
   Check the log file for your new logging lines and make sure you see them on all methods except the controller methods.

Part 2
------

4. Alas... after a long battle with the DBA team (and we lost) a decision has been made...<br/>
   The good old **'BookInMemoryDao'** is going to be **decommissioned** :-(<br/>
   This means that the moody **'BookUsuallyInMemoryDao2'** is all we have right now and this is a huge problem!<br/>
   You all remember its moods and occasional: **MoodyException**("I am really not in the mood to do that...")?<br/>
   What do we do now???<br/>

5. **Be a hero**, and write an aspect that intercepts **MoodyException** and retries the failed operation :-)<br/>
   (limit the retries to 5, then throw the last received **MoodyException**)

6. Change the **LibraryControllerTest @ActiveProfiles** to **"dev"** in order for the **'BookUsuallyInMemoryDao2'** to kick in.

7. Check that all of the **LibraryControllerTest** pass successfully

