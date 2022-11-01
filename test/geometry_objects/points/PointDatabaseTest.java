package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PointDatabaseTest {
	
	
	public PointDatabase Builder()
	{
		ArrayList<Point> arr = new ArrayList<Point>();
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		Point p3 = new Point(2,0);
		Point p4 = new Point(5,3);
		
		
		PointDatabase pdb = new PointDatabase(arr); 
		
		return pdb;
	}

	@Test
	void testGetPoints() {
		fail("Not yet implemented");
	}

	@Test
	void testPointDatabase() {
		fail("Not yet implemented");
	}

	@Test
	void testPointDatabaseListOfPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testPut() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNameDoubleDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNamePoint() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointPoint() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPointDoubleDouble() {
		fail("Not yet implemented");
	}

}
