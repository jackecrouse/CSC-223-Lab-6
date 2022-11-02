package geometry_objects.points;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PointNamingFactoryTest {

	/*
	 * makes a new list of points
	 * @return arrayList<Point> arr
	 */
	
	public static List<Point> makePointList() {
		ArrayList<Point> arr = new ArrayList<Point>();
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,1);
		Point p3 = new Point(2,0);
		Point p4 = new Point(1000,1000);
		
		arr.add(p1); 
		arr.add(p2); 
		arr.add(p3); 
		arr.add(p4);
		
		return arr;
	}
	
	@Test
	public void putTestPoint() {
		Point contained = new Point(0,0);
		Point uncontained = new Point(3,3);
		Point uncontained2 = new Point(5,3);
		PointNamingFactory full = new PointNamingFactory(makePointList());
		PointNamingFactory empty = new PointNamingFactory();
		
		assertEquals(empty.put(uncontained), uncontained);
		assertEquals(empty.put(uncontained2), uncontained2);
		assertEquals(full.put(uncontained), uncontained);
		assertTrue(full.put(contained).equals(contained));
	}
	@Test
	public void putTestXY() {
		Point contained = new Point(0,0);
		PointNamingFactory full = new PointNamingFactory(makePointList());
		PointNamingFactory empty = new PointNamingFactory();
		
		Point p = full.put(0, 0);
		assertEquals(p.compareTo(contained), 0);
		
		full.put(2,6);
		assertEquals(full.size(), 6);
		
		empty.put(0,5);
		assertEquals(empty.size(), 1);
	}
	@Test
	public void putTestString() {
		
		PointNamingFactory full = new PointNamingFactory(makePointList());
		PointNamingFactory empty = new PointNamingFactory();
		
		full.put("name", 7, 0);
		assertEquals(full.size(), 5);
		
		full.put("name", 0, 0);
		assertEquals(full.size(), 6);
		
		empty.put("name", 0, 0);
		assertEquals(empty.size(), 1);
		
		empty.put(null, 1, 1);
		assertEquals(empty.size(), 2);
		
	}
	@Test
	public void containsPointTest() {
		PointNamingFactory pnf = new PointNamingFactory();
		Point a = new Point(3,3);
		Point b = new Point(0,0);
		Point c = new Point(-100,3);
		Point d = new Point(48,3);
		
		pnf.put(a);
		pnf.put(b);
		pnf.put(c);
		
		assertTrue(pnf.contains(a));
		assertTrue(pnf.contains(b));
		assertTrue(pnf.contains(c));
		assertFalse(pnf.contains(d));
	}
	@Test
	public void containsXYTest() {
		PointNamingFactory pnf = new PointNamingFactory();
		Point a = new Point(3,3);
		Point b = new Point(0,0);
		Point c = new Point(-100,3);
		
		pnf.put(a);
		pnf.put(b);
		pnf.put(c);
		
		assertTrue(pnf.contains(3, 3));
		assertTrue(pnf.contains(0, 0));
		assertTrue(pnf.contains(-100, 3));
		assertFalse(pnf.contains(47, 26));
	}
	
	@Test
	public void getAllPointsTest() {
		PointNamingFactory pnf = new PointNamingFactory();
		Point a = new Point(3,3);
		Point b = new Point(0,0);
		Point c = new Point(-100,3);
		
		pnf.put(a);
		pnf.put(b);
		pnf.put(c);
		
		Object[] arr = pnf.getAllPoints().toArray();	

		assertEquals(arr[0], a);
		
		assertEquals(arr[1], b);
		
		assertEquals(arr[2], c);
	
		pnf.put(a);
	}
	@Test
	public void toStringTest() {
		PointNamingFactory pnf = new PointNamingFactory();

		assertEquals(pnf.toString(), "[]");

		pnf.put("a", 0, 0);
		pnf.put("b", 10, 10);
		pnf.put("c", -100, -100);
		System.out.println(pnf.toString());
		assertEquals(pnf.toString(), "[(a : 0.0, 0.0), (b : 10.0, 10.0), (c : -100.0, -100.0)]");
		
		}
	
}

