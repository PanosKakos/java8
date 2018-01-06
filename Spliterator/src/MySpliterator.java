import java.util.Spliterator;
import java.util.function.Consumer;

public class MySpliterator implements Spliterator<Employee> {

    private Spliterator<String> spliterator;
    private String firstName;
    private String lastName;
    private String position;
    private int salary;

    public MySpliterator(Spliterator<String> spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Employee> action) {

        if( this.spliterator.tryAdvance(line -> this.firstName = line)
                && this.spliterator.tryAdvance(line -> this.lastName =line )
                && this.spliterator.tryAdvance(line -> this.position =line )
                && this.spliterator.tryAdvance(line -> this.salary =Integer.parseInt(line))){

            Employee employee = new Employee(firstName,lastName,position,salary);
            action.accept(employee);
            return true;
        }else{
             return false;
        }
    }

    //implement if you want to use parallel
    @Override
    public Spliterator<Employee> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return spliterator.estimateSize() / 4 ;
    }

    @Override
    public int characteristics() {
        return spliterator.characteristics();
    }
}
