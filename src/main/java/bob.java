import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.*;
import java.io.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


class main {

    public static void main(String[] args){

        List<Student> students = new ArrayList<Student>();
        JobDataMap map=new JobDataMap();
        map.put("students", students);
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our SimpleJob class
            JobDetail job = newJob(SimpleJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData(map)
                    .build();

            JobDetail job2 = newJob(SimpleJob2.class)
                    .withIdentity("job2", "group1")
                    .build();

            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .withSchedule(cronSchedule("0/60 * * * * ? *"))
                    .build();


            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule("0/30 0/1 * 1/1 * ? *"))
                    .build();


            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2, trigger2);

            // and start it off
            scheduler.start();



        } catch (SchedulerException se) {
            se.printStackTrace();
        }




        int[] check = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};


        //Date now;
        //now = new Date(); // initialize date
        //System.out.println("Time is :" + now); // Display current time

        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Please enter your info: ");
                String city = input.readLine();
                String data = input.readLine();
                String name = data.split(" ")[0].replaceAll("\\s","");
                String surname = data.split(" ")[1].replaceAll("\\s","");
                String pesel = data.split(" ")[2].replaceAll("\\s","");
                String peselArr[] = pesel.split("");

                int numpesel[] = new int[peselArr.length];
                for (int i = 0; i < peselArr.length; i++) {
                    numpesel[i] = Integer.parseInt(peselArr[i]);
                }

                if (numpesel.length != 11){
                    System.out.println("Pesel is too short");
                } else {
                    int temp = 0;
                    for(int i = 0; i < 10; i ++) {
                        temp += numpesel[i] * check[i];
                    }
                    temp = temp % 10;
                    if (temp != 0) {
                        temp = 10 - temp;
                    }
                    if (temp != numpesel[10]) {
                        System.out.println("Pesel is wrong");
                    } else {

                        Student myStudent = new Student( city , name, surname , pesel );

                        for (Student s:students){
                           if(myStudent.pesel.equals(s.pesel)){
                               students.remove(s);
                               break;
                            }
                        }
                        students.add(myStudent);

                    }
                }
                //System.out.println(students.size());

            }
            // for (int h=0; h < myStudent.length; h++) {
            // System.out.println (myStudent[h].city +
            // ", " + myStudent[h].name + ", "
            // + myStudent[h].surname+ ", " + myStudent[h].pesel);
            // }


        } catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
