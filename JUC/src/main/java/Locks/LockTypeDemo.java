package Locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 @author Lyoto
 @Description
 @Date 2022-07-12 15:02
 @Version
 **/

public class LockTypeDemo {
	private volatile Integer tickets;
	ReentrantLock unfairLock = new ReentrantLock();
	ReentrantLock fairLock = new ReentrantLock(true);

	public static void main(String[] args) {

	}

	/**
	 * 公平与非公平锁
	 */
	public void fairAndUnfair(Lock lock) {
		//默认false

		while(tickets > 0) {
			try {
				lock.lock();
				int ticket = tickets - 1;
				System.out.printf("%s---->第%s张卖出，还剩%s张。%n", Thread.currentThread().getName(), tickets, ticket);
				TimeUnit.MILLISECONDS.sleep(200);
				tickets = ticket;
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			finally {
				lock.unlock();
			}
		}

	}

	@Test
	public void fairUnFairTest() throws InterruptedException {
		System.out.println("---------非公平锁---------");
		LockTypeDemo lockTypeDemo = new LockTypeDemo();
		lockTypeDemo.tickets = 50;
		new Thread(()->lockTypeDemo.fairAndUnfair(unfairLock),"t1").start();
		TimeUnit.MILLISECONDS.sleep(100);
		new Thread(()->lockTypeDemo.fairAndUnfair(unfairLock),"t2").start();
		//System.out.println("---------公平锁---------");
		//new Thread(() -> lockTypeDemo.fairAndUnfair(fairLock), "t3").start();
		//new Thread(() -> lockTypeDemo.fairAndUnfair(fairLock), "t4").start();
		TimeUnit.SECONDS.sleep(30);
	}

}
