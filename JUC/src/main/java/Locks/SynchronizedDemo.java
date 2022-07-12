package Locks;

/**
 @author Lyoto
 @Description
 @Date 2022-07-11 12:14
 @Version
 **/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 题目:谈谈你对多线程的理解，8锁案例说明<br>
 * 口诀：线程 操作 资源类<br>
 * 8锁案例说明<br>
 * 1 标准访问a、b两个线程，是先打印邮件还是短信？<br>
 * 2 sendEmail中暂停3秒钟，是先打印邮件还是短信？<br>
 * 3 添加一个普通的sayHello方法，请问是先打印邮件还是短信？<br>
 * 4 有两部手机，是先打印邮件还是短信？<br>
 * 5 有两个静态同步方法，有1部手机，是先打印邮件还是短信？<br>
 * 6 有两个静态同步方法，有2部手机，是先打印邮件还是短信？<br>
 * 7 有一个静态同步方法，有一个普通同步方法，有1部手机，是先打印邮件还是短信？<br>
 * 8 有一个静态同步方法，有一个普通同步方法，有2部手机，是先打印邮件还是短信？<br>
 * 关键点：看是否使用的是同一把锁<br>
 */
public class SynchronizedDemo {
	@Test
	public void testCase1() throws InterruptedException {
		Phone phone = new Phone();
		new Thread(phone::sendEmail, "a").start();
		//确保线程启动的先后顺序
		TimeUnit.SECONDS.sleep(2);
		new Thread(phone::sendSMS, "b").start();
		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void testCase2() throws InterruptedException {
		Phone phone = new Phone();
		new Thread(() ->
		{
			try {
				phone.sendEmail1();
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, "a").start();
		TimeUnit.SECONDS.sleep(2);
		new Thread(phone::sendSMS).start();
		//此时主线程等待是为了防止子线程还未处理完，主线程就结束了无法获取子线程打印内容
		TimeUnit.SECONDS.sleep(3);
	}
	@Test
	public void testCase3() throws InterruptedException {
		Phone phone = new Phone();

		new Thread(() ->
		{
			try {
				phone.sendEmail1();
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		},"a").start();

		new Thread(phone::sayHello,"b").start();
		TimeUnit.SECONDS.sleep(3);

	}

	@Test
	public void testCase4() throws InterruptedException {
		Phone phone = new Phone();
		Phone phone1 = new Phone();
		new Thread(() ->
		{
			try {
				phone.sendEmail1();
			}
			catch(InterruptedException e) {
				throw new RuntimeException(e);
			}
		},"a").start();
		TimeUnit.MILLISECONDS.sleep(200);
		new Thread(phone1::sendSMS,"b").start();
		TimeUnit.SECONDS.sleep(1);
	}
	@Test
	public void testCase5() throws InterruptedException {
		Phone phone = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail2();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		},"a").start();
		TimeUnit.MILLISECONDS.sleep(200);
		new Thread(()->phone.sendSMS1(),"b").start();
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void testCase6() throws InterruptedException {
		Phone phone = new Phone();
		Phone phone1 = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail2();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		},"a").start();
		TimeUnit.MILLISECONDS.sleep(200);
		new Thread(()->phone1.sendSMS1(),"b").start();
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void testCase7() throws InterruptedException {
		Phone phone = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail2();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		},"a").start();
		TimeUnit.MILLISECONDS.sleep(200);
		new Thread(phone::sendSMS,"b").start();
		TimeUnit.SECONDS.sleep(1);
	}

	@Test
	public void testCase8() throws InterruptedException {
		Phone phone = new Phone();
		Phone phone1 = new Phone();
		new Thread(()->{
			try {
				phone.sendEmail2();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		},"a").start();
		TimeUnit.MILLISECONDS.sleep(200);
		new Thread(phone1::sendSMS,"b").start();
		TimeUnit.SECONDS.sleep(1);
	}

	public static void main(String[] args) {
		SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
		synchronizedDemo2.deadLockCase();
	}

	/**
	 * 死锁问题演示
	 */
	@Test
	public void deadLockCase(){
		System.out.println("死锁演示");
		new Thread(SynchronizedDemo::methodA,"a").start();
		new Thread(SynchronizedDemo::methodB,"b").start();
	}
	public static SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
	public static SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
	public static void methodA() {
			synchronized (synchronizedDemo) {
				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " come in 1 and "+Thread.currentThread().getName());
				try {
					TimeUnit.SECONDS.sleep(2);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				synchronized (synchronizedDemo1) {
					System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " come in 2 and "+Thread.currentThread().getName());
				}
			}
	}
	public static void methodB(){
		synchronized (synchronizedDemo1){

			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()+"come in 1 and "+Thread.currentThread().getName());
			synchronized (synchronizedDemo){
				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " come in 2 and "+Thread.currentThread().getName());
			}
		}
	}
}

/**
 * 资源
 */
class Phone {
	public synchronized void sendEmail() {
		System.out.println("-------sendEmail");
	}

	public synchronized void sendEmail1() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(300);
		System.out.println("-------sendEmail");
	}
	public static synchronized void sendEmail2() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(300);
		System.out.println("-------sendEmail");
	}

	public synchronized void sendSMS() {
		System.out.println("------sendSMS");
	}
	public static synchronized void sendSMS1() {
		System.out.println("------sendSMS");
	}

	public void sayHello() {
		System.out.println("hello");
	}

}