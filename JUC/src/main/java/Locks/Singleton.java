package Locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 @author Lyoto
 @Description 单例模式懒加载多线程弊端
 @Date 2022-07-12 17:18
 @Version
 **/
public class Singleton {
	private static Singleton instance ;
	private static volatile Singleton unInstance ;
	private Singleton (){}

	/**
	 * 多线程下懒加载可能会导致获得多个实例对象，破坏了单例模式的原则
	 * 通过synchronized（加在方法上或指定代码块上）可解决此问题，
	 * 虽然做到了线程安全，并且解决了多实例的问题，但是它并不高效。
	 * 因为在任何时候只能有一个线程调用 getInstance() 方法。
	 * 但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。
	 * @return
	 */
	public static synchronized Singleton getInstance() {
		if (instance == null) {
			try {
				//制作延迟，提高多线程出错的概率
				TimeUnit.MILLISECONDS.sleep(500);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * 通过双重检验锁解决，
	 * 第一层if解决当前判断是否为空，因为实例化对象操作只需执行一遍，因此该层if能极大提高效率，
	 * 第二层在synchronized代码块中的if
	 * 防止多个线程同时进入导致多个实例出现，另外静态对象unInstance使用volatile修饰目的是为了防止指令重排
	 * @return
	 */
	public static Singleton getInstance1() {

		if (unInstance == null){
			try {
				//制作延迟，提高多线程出错的概率
				TimeUnit.MILLISECONDS.sleep(500);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			synchronized (Singleton.class){
				if (unInstance == null){
					unInstance = new Singleton();
				}
			}
		}
		return unInstance;
	}

		public static void main(String[] args) {
			System.out.println("单加synchronized执行");
			ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(16);
			newFixedThreadPool.execute(()->{
			long startTime1 = System.currentTimeMillis();
				for (int i = 0; i < 100; i++) {
					System.out.println(getInstance());
				}
			long endTime1 = System.currentTimeMillis();
			System.out.printf("耗时：%s毫秒%n",(endTime1-startTime1));
			});

			System.out.println("双重检验锁执行");
			newFixedThreadPool.execute(()->{
			long startTime2 = System.currentTimeMillis();
			for (int i = 0; i < 100; i++) {
				System.out.println(getInstance1());
		}
			long endTime2 = System.currentTimeMillis();
			System.out.printf("耗时：%s毫秒",(endTime2-startTime2));
			});
		}
}
