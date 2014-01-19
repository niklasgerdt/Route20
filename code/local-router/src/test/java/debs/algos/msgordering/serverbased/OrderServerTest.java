package debs.algos.msgordering.serverbased;

import static org.junit.Assert.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

public class OrderServerTest {

	@Test
	public void firstReqGiversOrder1() {
		OrderServer.init();
		assertEquals(1, OrderServer.getOrder());
	}

	@Test
	public void secondReqGiversOrder2() {
		OrderServer.init();
		OrderServer.getOrder();
		assertEquals(2, OrderServer.getOrder());
	}

	@Test
	public void twoThreadsNeverGetsTheSameValue() {
		Executor e = Executors.newFixedThreadPool(2);
		e.execute(new TestRunnable("aa"));
		e.execute(new TestRunnable("bb"));
	}

	class TestRunnable implements Runnable {
		private String id;

		public TestRunnable(String id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.println(id);
		}
	}
}
