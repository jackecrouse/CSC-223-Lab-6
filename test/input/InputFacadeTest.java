package input;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.components.FigureNode;
import input.visitor.UnparseVisitor;

public class InputFacadeTest {

	@Test
	void test_toGeometryRepresentation_null() {
		Map.Entry<PointDatabase, Set<Segment>> expected = null;
		Map.Entry<PointDatabase, Set<Segment>> actual = InputFacade.toGeometryRepresentation(null);
		
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
