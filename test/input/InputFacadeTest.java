package input;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import input.visitor.UnparseVisitor;

public class InputFacadeTest {

	@Test
	void test_toGeometryRepresentation_null() {
		Map.Entry<PointDatabase, Set<Segment>> expected = null;
		Map.Entry<PointDatabase, Set<Segment>> actual = InputFacade.toGeometryRepresentation(null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getPointDatabaseFromFigure_onePoint() {
		PointNodeDatabase pdb = new PointNodeDatabase(Arrays.asList(new PointNode(0.0, 1.0)));
		FigureNode figure = new FigureNode("desc", pdb, null);
		
		PointDatabase expected = new PointDatabase(Arrays.asList(new Point(0.0, 1.0)));
		PointDatabase actual = InputFacade.getPointDatabaseFromFigure(figure);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getPointDatabaseFromFigure_manyPoints() {
		PointNodeDatabase pdb = new PointNodeDatabase(Arrays.asList(
				new PointNode(0.0, 1.0),
				new PointNode(1.0, 1.0),
				new PointNode(1.0, 0.0)));
		FigureNode figure = new FigureNode("desc", pdb, null);
		
		PointDatabase expected = new PointDatabase(Arrays.asList(
				new Point(0.0, 1.0),
				new Point(1.0, 1.0),
				new Point(1.0, 0.0)));
		PointDatabase actual = InputFacade.getPointDatabaseFromFigure(figure);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getSegmentSetFromFigure_oneSegment() {
		Map<PointNode, Set<PointNode>> adj = new HashMap<PointNode, Set<PointNode>>();
		// putting [ "A" : "B" ]
		adj.put(new PointNode("A",0,0), new HashSet(Arrays.asList(new PointNode("B",1,1))));
		
		SegmentNodeDatabase sdb = new SegmentNodeDatabase(adj);
		FigureNode figure = new FigureNode("desc", null, sdb);
		
		Set<Segment> expected = new HashSet<Segment>(Arrays.asList(
				new Segment(new Point("A",0,0), new Point("B",1,1))));
		PointDatabase actual = InputFacade.getPointDatabaseFromFigure(figure);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_getSegmentSetFromFigure_manySegments() {
		Map<PointNode, Set<PointNode>> adj = new HashMap<PointNode, Set<PointNode>>();
		// putting [ "A" : "B" , "C" ]
		adj.put(new PointNode("A",0,0), new HashSet(Arrays.asList(
				new PointNode("B",1,1),
				new PointNode("C",2,2))));
		
		SegmentNodeDatabase sdb = new SegmentNodeDatabase(adj);
		FigureNode figure = new FigureNode("desc", null, sdb);
		
		Set<Segment> expected = new HashSet<Segment>(Arrays.asList(
				new Segment(new Point("A",0,0), new Point("B",1,1)),
				new Segment(new Point("A",0,0), new Point("C",2,2))));
		PointDatabase actual = InputFacade.getPointDatabaseFromFigure(figure);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_extractFigure_null() {
		FigureNode expected = null;
		FigureNode actual = InputFacade.extractFigure(null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void test_extractFigure_single_triangle() {
		FigureNode actual = InputFacade.extractFigure("JSON/single_triangle.json");
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		String JSONString = (String) unparser.visitFigureNode((FigureNode)actual, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		System.out.println(JSONString);
	}
	
	@Test
	void test_extractFigure_crossing_symmetric_triangle() {
		FigureNode actual = InputFacade.extractFigure("JSON/crossing_symmetric_triangle.json");
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		String JSONString = (String) unparser.visitFigureNode((FigureNode)actual, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		System.out.println(JSONString);
	}
	
	@Test
	void test_extractFigure_fully_connected_irregular_polygon() {
		FigureNode actual = InputFacade.extractFigure("JSON/fully_connected_irregular_polygon.json");
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		String JSONString = (String) unparser.visitFigureNode((FigureNode)actual, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		System.out.println(JSONString);
	}
	
	@Test
	void test_extractFigure_square() {
		FigureNode actual = InputFacade.extractFigure("JSON/square.json");
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		String JSONString = (String) unparser.visitFigureNode((FigureNode)actual, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		System.out.println(JSONString);
	}
	
	@Test
	void test_extractFigure_third_quadrant_square() {
		FigureNode actual = InputFacade.extractFigure("JSON/third_quadrant_square.json");
		
		StringBuilder sb = new StringBuilder();
		UnparseVisitor unparser = new UnparseVisitor();
		String JSONString = (String) unparser.visitFigureNode((FigureNode)actual, new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));
		System.out.println(JSONString);
	}
	
}
