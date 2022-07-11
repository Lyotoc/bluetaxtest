import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 @author Lyoto
 @Description 演示completableFuture的API的使用，及各种注意事项
 @Date 2022-07-08 15:53
 @Version
 **/
public class CompletableFutureBuildDemo {

	@Test
	public void thenMethodDistinguish() {
		System.out.println("-------thenRun----------");
		System.out.println(CompletableFuture.supplyAsync(() -> "this is step 1 of thread……").thenRun(() -> {
				})
				.join());
		System.out.println("---------thenApply--------");
		System.out.println(CompletableFuture.supplyAsync(() -> "this is step 1 of thread……")
				.thenApply(result -> result.concat("this is step 2 of thread……"))
				.join());
		System.out.println("--------thenAccept-------");
		System.out.println(CompletableFuture.supplyAsync(() -> "this is step 1 of thread")
				.thenAccept(System.out::println));

	}

	@Test
	public void thenMethodOfAsync() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		System.out.println(CompletableFuture.supplyAsync(() -> {
					String slogan = "this is step 1 of thread……";
					System.out.println(slogan);
					System.out.println(Thread.currentThread().getName());
					return slogan;
				}, newFixedThreadPool)
				.thenRunAsync(() -> {
					System.out.println("i'm not depend on previous");
					System.out.println(Thread.currentThread().getName());
				}, newFixedThreadPool).join());
		System.out.println("-------------------------------");
		System.out.println(CompletableFuture.supplyAsync(() -> {
					String slogan = "this is step 1 of thread……";
					System.out.println(slogan);
					System.out.println(Thread.currentThread().getName());
					try {
						TimeUnit.SECONDS.sleep(10);
					}
					catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return slogan;
				}, newFixedThreadPool)
				.thenRun(() -> {
					//上一步骤运行过快时会直接使用main线程执行任务，触发动态调优
					System.out.println("i'm not depend on previous");
					System.out.println(Thread.currentThread().getName());
				}).join());

	}

	@Test
	public void competeEachOther() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		CompletableFuture<String> B = CompletableFuture.supplyAsync(() -> "this is B");
		CompletableFuture<String> A = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return null;
		});
		String join = A.applyToEither(B, f -> "this winner is:" + f).join();
		System.out.println(join);
	}

	@Test
	/***
	 * 两个CompletionStage处理结果交由thenCombine进行合并处理
	 */
	public void resultCombine() {
		CompletableFuture<Integer> intThread = CompletableFuture.supplyAsync(() -> {
			int seed = ThreadLocalRandom.current().nextInt(10);
			System.out.println("this seed is " + seed);
			return seed;
		});
		CompletableFuture<Integer> otherIntThread = CompletableFuture.supplyAsync(() -> {
			int otherSeed = ThreadLocalRandom.current().nextInt(5);
			System.out.println("other seed is " + otherSeed);
			return otherSeed;
		});
		System.out.println("result is: " + intThread.thenCombine(otherIntThread, Math::multiplyExact).join());
		System.out.println("-----------习惯用法---------");
//	习惯用法
		System.out.println(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			int seed = ThreadLocalRandom.current().nextInt(1, 5);
			System.out.println("this seed is : " + seed);
			return seed;
		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			int seed = ThreadLocalRandom.current().nextInt(1, 5);
			System.out.println("this seed is : " + seed);
			return seed;
		}), Math::addExact).thenCombine(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			int seed = ThreadLocalRandom.current().nextInt(1, 5);
			System.out.println("this seed is : " + seed);
			return seed;
		}), Math::multiplyExact).thenCombine(CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			int seed = ThreadLocalRandom.current().nextInt(1, 5);
			System.out.println("this seed is : " + seed);
			return seed;
		}), Math::subtractExact).join());
	}
}
