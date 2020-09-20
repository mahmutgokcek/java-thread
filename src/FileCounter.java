import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;
public class FileCounter {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Path searchDir = Paths.get("src", "module");
        final List<FileLineCallable> callables = Files.list(searchDir).filter(Files::isRegularFile)
                .map(FileLineCallable::new).collect(Collectors.toList());
        final List<Future<Long>> results = FileLineCounterExecutor.execute(callables);
        final long count = results.stream().mapToLong(FileLineCounterExecutor::getCount).sum();
        System.out.printf("Total Files Lines %d", count);
    }
}