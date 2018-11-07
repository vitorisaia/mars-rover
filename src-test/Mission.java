import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.vitor.mars.Plateau;
import com.vitor.mars.Rover;

class Mission {

	@Test
	void test() throws Exception {
		// make it work
		// make it right
		// make it fast

		// avoid rovers to crash?
		// watch for plateu boundaries?

		Plateau plateau = new Plateau(5, 5);

		Rover firstRover = new Rover(1, 2, "N");
		firstRover.landOn(plateau);
		firstRover.execute("LMLMLMLMM");

		Rover secondRover = new Rover(3, 3, "E");
		secondRover.landOn(plateau);
		secondRover.execute("MMRMMRMRRM");
		// secondRover.execute("MMRMMRMRRMMMMMM");

		String firstPosition = firstRover.getCurrentPosition();
		System.out.println(firstPosition);
		assertEquals("1 3 N", firstPosition);
		String secondPosition = secondRover.getCurrentPosition();
		System.out.println(secondPosition);
		assertEquals("5 1 E", secondPosition);

		// firstRover output: 1 3 N
		// secondRover output: 5 1 E
	}
}
