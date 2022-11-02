package utilities.io.input;


import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;
import geometry_objects.Segment;
import input.builder.GeometryBuilder;
import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.segment.SegmentNode;
import input.parser.JSONParser;

/*
 * The InputFacade class includes methods that give main functionality to this program.
 * It allows for the easy extraction of a FigureNode out of a JSONFile using the extractFigure() method. 
 * From that FigureNode, it can convert the PointNodes and SegmentNodes into Points and Segments as a pair:
 * <PointDatabase, Set<Segment>>, which provide more meaningful geometric functionality.
 * 
 * @author Sophie Ngo
 */
public class InputFacade
{
	/**
	 * Acquire a figure from the given JSON file.
     *
	 * @param filename -- the name of a file
	 * @return a FigureNode object corresponding to the input file.
	 */
	public static FigureNode extractFigure(String filename)
	{
        if(filename == null) return null;
        
        GeometryBuilder geoBuilder = new GeometryBuilder();
		JSONParser parser = new JSONParser(geoBuilder);

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		
		return (FigureNode) parser.parse(figureStr);
        
	}
	
	/**
	 * 1) Read in a figure from a JSON file.
	 * 2) Convert the PointNode and SegmentNode objects to a Point and Segment objects 
	 *    (those classes have more meaningful, geometric functionality).
     *
	 * @param filename
	 * @return a pair <set of points as a database, set of segments>
	 */
	public static Map.Entry<PointDatabase, Set<Segment>> toGeometryRepresentation(String filename)
	{
		if(filename == null) return null;
		
		FigureNode figure = extractFigure(filename);
		PointDatabase pdb = InputFacade.getPointDatabaseFromFigure(figure);
		Set<Segment> segSet = InputFacade.getSegmentSetFromFigure(figure);
		
		return new AbstractMap.SimpleEntry<PointDatabase, Set<Segment>>(pdb, segSet);
	}
	
	/**
	 * Given a FigureNode, take its PointNodeDatabase and convert it into a PointDatabase
	 * @param figure
	 * @return PointDatabase that represents the PointNodeDatabase
	 */
	public static PointDatabase getPointDatabaseFromFigure(FigureNode figure) {
		PointDatabase pdb = new PointDatabase();
		
		// convert every PointNode to a Point, then add each to our PointDatabase
		for (PointNode pNode : figure.getPointsDatabase().getPointsSet()) {
			pdb.put(pNode.getName(), pNode.getX(), pNode.getY());
		}
		
		return pdb;
	}
	
	/**
	 * Given a FigureNode, take its SegmentNodeDatabase and convert it into a Set of Segments
	 * @param figure
	 * @return Set<Segments> that represents the SegmentNodeDatabase
	 */
	public static Set<Segment> getSegmentSetFromFigure(FigureNode figure) {
		Set<Segment> segSet = new LinkedHashSet<Segment>();
		
		// convert every SegmentNode to a Segment, then add each to our set of Segments
		for (SegmentNode sNode : figure.getSegments().asUniqueSegmentList()) {
			// must first convert each PointNode of segment to Point
			Point p1 = new Point(sNode.getPoint1().getName(), sNode.getPoint1().getX(), sNode.getPoint1().getY());
			Point p2 = new Point(sNode.getPoint2().getName(), sNode.getPoint2().getX(), sNode.getPoint2().getY());
			segSet.add(new Segment(p1, p2));
		}
		return segSet;
	}
	
}
