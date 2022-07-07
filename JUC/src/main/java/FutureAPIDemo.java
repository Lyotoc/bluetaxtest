import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 @author Lyoto
 @Description
 @Date 2022-07-04 11:41
 @Version
 **/
public class FutureAPIDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		FutureTask<String> futureTask = new FutureTask<>(()->{
			System.out.println(Thread.currentThread().getName()+"-------------come in");
			TimeUnit.SECONDS.sleep(5);
			return "task over";
		});

		Thread t1 = new Thread(futureTask, "t1");
		t1.start();
		System.out.println(Thread.currentThread().getName()+"忙其它任务了");
		//System.out.println(futureTask.get(6,TimeUnit.SECONDS));

	}
}
