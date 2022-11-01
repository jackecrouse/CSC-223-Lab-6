package geometry_objects.points;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PointNamingFactoryTest {

	/*
	 * makes a new list of points
	 * @return arrayList<Point> arr
	 */
	
	public static List makePointList() {
		ArrayList<Point> arr = new ArrayList<Point>();
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		Point p3 = new Point(2,0);
		Point p4 = new Point(1000,1000);
		
		arr.add(p1); arr.add(p2); arr.add(p3); arr.add(p4);
		
		return arr;
	}
	
	@Test
	public void putTest() {
		Point contained = new Point(0,0);
		Point uncontained = new Point(3,3);
		Point uncontained2 = new Point(5,3);
		PointNamingFactory full = new PointNamingFactory(makePointList());
		PointNamingFactory empty = new PointNamingFactory();
		
		assertEquals(empty.put(uncontained), uncontained);
	}
	
	
}

