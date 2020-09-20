import java.util.List;
import java.util.concurrent.*;
public class FileLineCounterExecutor {
    public static List<Future<Long>> execute(List<FileLineCallable> fileLineCallables) throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final List<Future<Long>> futures = executorService.invokeAll(fileLineCallables);
        executorService.shutdown();
        return futures;
    }
    public static Long getCount(Future<Long> future) {
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}