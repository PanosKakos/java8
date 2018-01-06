import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String... args) {
        Path path = Paths.get("Employee.txt");
        Comparator<Employee> comparator = (e1,e2) -> e2.getSalary() - e1.getSalary(); //sorting in reverse order based on salary

        try (Stream<String> stream = Files.lines(path)) {
            Spliterator<String> spliterator = stream.spliterator(); // getting a spliterator, similar process to getting an iterator
            MySpliterator mySpliterator = new MySpliterator(spliterator); // calling custom spliterator

            Stream<Employee> employeeStream = StreamSupport.stream(mySpliterator, false);

            employeeStream.sorted(comparator).forEach(System.out::println);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
