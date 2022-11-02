package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class PointTest {
	
	@Test
	void test_LexicographicOrdering_p1_x_less() {
		Point p1 = new Point(0.0, 10);
		Point p2 = new Point(1.0, 0);
		
		int expected = -1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_LexicographicOrdering_p1_x_lessbutclose() {
		Point p1 = new Point(0.9, 10);
		Point p2 = new Point(1.0, 0);
		
		int expected = -1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void test_LexicographicOrdering_p1_x_greater() {
		Point p1 = new Point(1.0, 10);
		Point p2 = new Point(0.0, 0);
		
		int expected = 1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_LexicographicOrdering_p1_x_greaterbutclose() {
		Point p1 = new Point(1.0, 10);
		Point p2 = new Point(0.9, 0);
		
		int expected = 1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
//
	@Test
	void test_LexicographicOrdering_p1_y_less() {
		Point p1 = new Point(0.0, 0.0);
		Point p2 = new Point(0.0, 1.0);
		
		int expected = -1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_LexicographicOrdering_p1_y_lessbutclose() {
		Point p1 = new Point(0.0, 0.9);
		Point p2 = new Point(0.0, 1.0);
		
		int expected = -1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void test_LexicographicOrdering_p1_y_greater() {
		Point p1 = new Point(0.0, 1.0);
		Point p2 = new Point(0.0, 0.0);
		
		int expected = 1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_LexicographicOrdering_p1_y_greaterbutclose() {
		Point p1 = new Point(0.0, 1.0);
		Point p2 = new Point(0.0, 0.9);
		
		int expected = 1;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_LexicographicOrdering_equal() {
		Point p1 = new Point(0.0, 0.0);
		Point p2 = new Point(0.0, 0.0);
		
		int expected = 0;
		int actual = Point.LexicographicOrdering(p1, p2);
		
		assertEquals(expected, actual);
	}
}
