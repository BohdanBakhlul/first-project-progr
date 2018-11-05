import org.quartz.*;
import static org.quartz.JobBuilder.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


//Simplest job is a class that implements Job interface (execute method)
public class SimpleJob implements org.quartz.Job {

    public SimpleJob(){}


    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        //List<Student> students = new ArrayList<Student>();
        List<Student> students = (ArrayList)context.getMergedJobDataMap().get("students");

        List<String> cities = new ArrayList<String>();
        for (Student s:students) {
            cities.add(s.city);
        }
        Set<String> sorted = new LinkedHashSet<>(cities);
        cities = new ArrayList<String>(sorted);
        Collections.sort(cities);
        try(FileWriter writer = new FileWriter( "result.txt", false))
        {
            for (String c:cities) {
                writer.write(c + "\n");
                for (Student s:students) {
                    //	System.out.println(s.name);
                    if ( s.city.equals(c) ) {
                        //System.out.println("add: " + c);
                        String text = (s.name + " " + s.surname + " " + s.pesel +"\n");
                        writer.write(text);
                        writer.flush();

                    }
                }
            }
            //	writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
