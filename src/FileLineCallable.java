import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
public class FileLineCallable implements Callable<Long> {

    private final Path path;

    public FileLineCallable(Path path)
    {
        this.path=path;
    }
    @Override
    public Long call() throws Exception{
        final long count= Files.lines(path).count();
        System.out.printf("Path %s lines %d %n", path.getFileName(),count);
        return count;
    }
}