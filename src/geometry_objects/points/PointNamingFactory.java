package geometry_objects.points;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import utilities.math.MathUtilities;

/*
 * Given a pair of coordinates; generate a unique name for it;
 * return that point object.
 *
 * Names go from A..Z..AA..ZZ..AAA...ZZZ
 */
public class PointNamingFactory
{
	private static final String _PREFIX = "*_"; // Distinguishes generated names

	private static final char START_LETTER = 'A';
	private static final char END_LETTER = 'Z';

	private String _currentName = "A";
	private int _numLetters = 1;

	//
	// A hashed container for the database of points;
	// This requires the Point class implement equals
	// based solely on the individual values and not a name
	// We need a get() method; HashSet doesn't offer one.
	// Each entry is a <Key, Value> pair where Key == Value
	//
	protected Map<Point, Point> _database;

	public PointNamingFactory()
	{
		_database = new LinkedHashMap<Point, Point>();
	}

	/**
	 * 
	 * @param points -- a list of points, named or not named
	 */
	public PointNamingFactory(List<Point> points)
	{
		_database = new LinkedHashMap<Point, Point>();
		for (Point p : points) {
			this.put(p); // call own put
		}
	}

	/**
	 * @param pt -- (x, y) coordinate pair object
	 * @return a point (if it already exists) or a completely new point that
	 *         has been added to the database
	 */
	public Point put(Point pt)
	{
		_database.put(pt, pt);
		return lookupExisting(pt.getName(), pt.getX(), pt.getY());
	}

	/**
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return a point (if it already exists) or a completely new point that has been added to the database
	 */
	public Point put(double x, double y)
	{
		return this.put(new Point(x,y));
	}

	/**
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return a point (if it already exists) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */
	public Point put(String name, double x, double y)
	{
		Point pt = new Point(name, x, y);
		return this.put(pt);
	}    

	/**
	 * Strict access (read-only of the database)
	 * 
	 * @param x
	 * @param y
	 * @return stored database Object corresponding to (x, y) 
	 */
	public Point get(double x, double y)
	{
		Point pt = new Point(x, y);
		return get(pt);
	}	
	
	public Point get(Point pt)
	{
		for(Point p: getAllPoints())
		{
			if(pt.equals(p)) return p;
		}
		return null;
	}

	/**
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return a point (if it already exists) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */
	private Point lookupExisting(String name, double x, double y) // call create
	{
		for(Point p: getAllPoints())
		{
			if(p.equals(new Point(name, x, y)))
			{
				Point old = p;
				if(p._name.equals("__UNNAMED") && !name.equals(Point.ANONYMOUS)) p._name = name;
				return old;
			}
		}
		return createNewPoint(name, x, y);
	}  

	/**
	 * @param name -- the name of the point 
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return a point (if it already exists) or a completely new point that
	 *         has been added to the database.
	 *         
	 *         If the point is in the database and the name differs from what
	 *         is given, nothing in the database will be changed; essentially
	 *         this means we use the first name given for a point.
	 *         
	 *         The exception is that a valid name can overwrite an unnamed point.
	 */
	private Point createNewPoint(String name, double x, double y)
	{
		Point p = new Point(name, x, y);
		return p;
	}

	/**
	 * @param x -- single coordinate
	 * @param y -- single coordinate
	 * @return simple containment; no updating
	 */
	public boolean contains(double x, double y) 
	{ 		
		Point pt = this.get(x, y);
		return pt != null;
	}
	
	
	
	public boolean contains(Point p) { 
		
		if (p == null) return false;

		for(Point point: getAllPoints())
		{
			if(point.equals(p)) return true; // point equals
		}
		return false;
	}

	/**
	 * @return acquires and returns the next name in sequence; also
	 * generates the next name in a 'lazy list' manner 
	 */
	private String getCurrentName()
	{
        String oldName = _currentName;
        updateName();
        return oldName;
	}

	/**
	 * Advances the current generated name to the next letter in the alphabet:
	 * 'A' -> 'B' -> 'C' -> 'Z' --> 'AA' -> 'BB'
	 */
	private  void updateName()
	{
        if(_currentName.contains("Z")) return;
        
        char[] c =  _currentName.toCharArray();
        
        c[0] = (char) ((int) c[0] + 1); //Adds 1 to the ASCII code
        
        _currentName = c.toString();
        
	}

	/**
	 * @return The entire database of points.
	 */
	public  Set<Point> getAllPoints()
	{
        return _database.keySet();
	}

	public void clear() { _database.clear(); }
	public int size() { return _database.size(); }

	

	@Override
	public String toString()
	{
		if(_database.size() == 0) return "[]";
		
		String res = "[";
		
		for(Point p: getAllPoints())
		{
			res += "(" + p._name + " : " + p._x + ", " + p._y + "), ";
		}
		
		res = res.substring(0, res.length() - 2);
		return res += "]";
	}
}