package spike;

import static org.junit.Assert.*;

import org.junit.Test;

public class SpikeTest {

	@Test
	public void test() {
		assertEquals("hello", new Spike().spike());
	}

}
