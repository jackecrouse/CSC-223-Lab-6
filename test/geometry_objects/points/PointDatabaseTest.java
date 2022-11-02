package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PointDatabaseTest {
	
	public PointDatabase builder()
	{
		ArrayList<Point> arr = new ArrayList<Point>();
		Point p1 = new Point("A",0,0);
		Point p2 = new Point("B",0,1);
		Point p3 = new Point("C",2,0);
		Point p4 = new Point("D",5,3);
		
		arr.add(p1);
		arr.add(p2);
		arr.add(p3);
		arr.add(p4);
		
		
		PointDatabase pdb = new PointDatabase(arr); 
		
		return pdb;
	}
	
	public PointDatabase BuilderVary(int size)
	{
		ArrayList<Point> arr = new ArrayList<Point>();

		for(int i = 0; i < size; i++)
		{
			Point newP = new Point(i, i);
			arr.add(newP);
		}
		
		PointDatabase pdb = new PointDatabase(arr); 
		
		return pdb;
	}

	@Test
	void testGetPoints() 
	{
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,2);
		Point p3 = new Point(2,2);
		Point p4 = new Point(2,0);
		Point p5 = new Point(5,5);
		
		ArrayList<Point> ptArray = new ArrayList<Point>();
		
		ptArray.add(p1);
		ptArray.add(p2);
		ptArray.add(p3); 
		ptArray.add(p4); 
		
		PointDatabase pdb = new PointDatabase(ptArray); 
		
		Set<Point> set = pdb.getPoints();
		
		assertTrue(set.size() == 4);
		assertTrue(set.contains(p1));
		assertTrue(set.contains(p4));
		assertFalse(set.contains(p5));		
		
	}

	@Test
	void testSize() {
		
		assertEquals(4, BuilderVary(4).size());
		assertEquals(10, BuilderVary(10).size());
		assertEquals(200, BuilderVary(200).size());
		
		PointDatabase emptyPdb = new PointDatabase(); 
		
		assertEquals(0, emptyPdb.size());

	}

	@Test
	void testPut() 
	{
		PointDatabase pdb = new PointDatabase(); 
		
		pdb.put("A", 0, 0);
		pdb.put("B", 0, 2);
		pdb.put("C", 2, 2);
		pdb.put("D", 2, 0);
		
		assertEquals(4, pdb.size());
		
		Point pt = pdb._factory.get(0, 0);
		
		assertEquals(pt._name, "A");
		
		pdb.put(null, 5, 5); 
		
		Point nullPt = pdb._factory.get(5, 5);
		
		assertEquals("__UNNAMED", nullPt._name);

	}

	@Test
	void testGetNameDoubleDouble() {
		
		PointDatabase pdb = builder(); 
		
		assertEquals("A", pdb.getName(0, 0));
		assertEquals("B", pdb.getName(0, 1));
		
		pdb.put(null, 10, 10);
		
		assertEquals("__UNNAMED", pdb.getName(10, 10));
		
		assertEquals(null, pdb.getName(20, 20));
		
	}

	@Test
	void testGetNamePoint() 
	{
		Point p1 = new Point(0,0);
		Point p2 = new Point("B",0,2);
		Point p3 = new Point(2,2);
		Point p4 = new Point(2,0);
		
		ArrayList<Point> ptArray = new ArrayList<Point>();
		
		ptArray.add(p1);
		ptArray.add(p2);
		ptArray.add(p3);
		ptArray.add(p4);
		
		PointDatabase pdb = new PointDatabase(ptArray); 
		
		assertEquals("__UNNAMED", pdb.getName(p1));
		assertEquals("B", pdb.getName(p2));
		assertEquals("__UNNAMED", pdb.getName(p3));
		assertEquals("__UNNAMED", pdb.getName(p4));
		
	}

	@Test
	void testGetPointString() 
	{
		PointDatabase pdb = new PointDatabase(); 
		
		pdb.put("A", 0, 0);
		pdb.put("B", 0, 2);
		
		Point p1 = pdb.getPoint("A"); 
		Point p2 = pdb.getPoint("B"); 

		assertEquals(p1._x, 0);
		assertEquals(p1._y, 0);
		assertEquals(p2._x, 0);
		assertEquals(p2._y, 2);


	}

	@Test
	void testGetPointPoint() 
	{
		Point p1 = new Point(0,0);
		Point p2 = new Point("B",0,2);
		Point p3 = new Point(2,2);
		Point p4 = new Point(2,0);
		
		ArrayList<Point> ptArray = new ArrayList<Point>();
		
		ptArray.add(p1);
		ptArray.add(p2);
		ptArray.add(p3);
		ptArray.add(p4);
		
		PointDatabase pdb = new PointDatabase(ptArray); 
		
		Point pt = pdb.getPoint(p1);
		
		assertEquals(pt._x, 0);
		assertEquals(pt._y, 0);
		assertEquals(pt._name, "__UNNAMED");

		
	}

	@Test
	void testGetPointDoubleDouble() 
	{
		Point p1 = new Point("A", 0,0);
		Point p2 = new Point("B",0,2);
		Point p3 = new Point(2,2);
		Point p4 = new Point("D",2,0);
		
		ArrayList<Point> ptArray = new ArrayList<Point>();
		
		ptArray.add(p1);
		ptArray.add(p2);
		ptArray.add(p3);
		ptArray.add(p4);
		
		PointDatabase pdb = new PointDatabase(ptArray); 
	
		Point pt = pdb.getPoint(0, 0);
		assertEquals(pt._name, "A");
		
		Point pt2 = pdb.getPoint(0, 2);
		assertEquals(pt2._name, "B");

		Point pt3 = pdb.getPoint(2, 2);
		assertEquals(pt3._name, "__UNNAMED");


	}

}
