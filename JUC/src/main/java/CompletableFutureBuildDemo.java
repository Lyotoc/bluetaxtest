import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 @author Lyoto
 @Description
 @Date 2022-07-08 15:53
 @Version
 **/
public class CompletableFutureBuildDemo {

	public static void main(String[] args) throws InterruptedException {
		buildThread();
		//主线程等待，避免子线程还未执行完毕，无法获取内部信息。
		TimeUnit.SECONDS.sleep(100);
	}

	public static void buildThread() {
		int tickets = 100;


		Thread thread1 = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}, "testMachine");
		thread1.start();
		System.out.println("Aaaaa");
		Runnable runnable = () -> {
			//int i = ThreadLocalRandom.current().nextInt(10);
			synchronized (CompletableFutureBuildDemo.class){
				for (int i = tickets; i > 0; i--) {
					System.out.println("已卖：" + i);
					try {
						TimeUnit.SECONDS.sleep(1);
					}
					catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
			System.out.println("this is my function");
		};
		//CompletableFuture.runAsync(runnable).whenComplete();
		CompletableFuture.runAsync(runnable);

	}
}
