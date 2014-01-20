package route20.local.example;

import route20.local.BlockingRouter;
import route20.local.R20;
import route20.local.Router;
import route20.local.Subscriber;

public class Example {

	public static void main(String[] a) {
		simpleBlockingExampleWithDeclaredRouter();
		R20.<String>createNamedSimpleRouter("event router");
		Router<String> rf = R20.<String> fastRouter(100);
	}

	private static void simpleBlockingExampleWithDeclaredRouter() {
		Router<String> r = new BlockingRouter<>();
		Subscriber<String> s = new Subscriber<String>() {
			@Override
			public void onEvent(String e) {
				System.out.println(e);
			}
		};
		Subscriber<String> s2 = new Subscriber<String>() {
			@Override
			public void onEvent(String e) {
				System.out.println(e.toUpperCase());
			}
		};
		r.register(s);
		r.register(s2);
		for (int i = 0; i < 10; i++) {
			r.event("event " + i);
		}
	}

}
