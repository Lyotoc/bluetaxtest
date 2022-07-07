import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 @author Lyoto
 @Description
 @Date 2022-06-30 13:53
 @Version
 **/
public class MyThread {

	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.futureTaskTest();
	}

	public void daemonTest(){
		Thread t1 = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"\t 开始运行，"+(Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
			while (true){

			}
		},"t1");
		t1.setDaemon(true);
		t1.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"\t -----end 主线程");
	}
	public void futureTaskTest(){
		FutureTask<String> futureTask = new FutureTask<>(()->{
			System.out.println("come in call()");
			return "hello Callable";
		});
		Thread thread = new Thread(futureTask,"t1");
		thread.start();
		try {
			System.out.println(futureTask.get());
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
	}
public static void m1(){
		long startTime = System.currentTimeMillis();
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	try {
		TimeUnit.MILLISECONDS.sleep(300);
	}
	catch (InterruptedException e) {
		throw new RuntimeException(e);
	}
	try {
		TimeUnit.MILLISECONDS.sleep(300);
	}
	catch (InterruptedException e) {
		throw new RuntimeException(e);
	}
	long endTime = System.currentTimeMillis();
	System.out.println("耗时：" + (endTime - startTime) + "毫秒");
	System.out.println(Thread.currentThread().getName() + "end");
}
}
