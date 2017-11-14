package Constraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import logic.Term;

public class ConstraintsManager 
{
	private Map <String, Constraint> library;
	
	public ConstraintsManager()
	{
		this.library = new HashMap<>();
	}
	public ConstraintsManager(Map<String, Constraint> arg0) 
	{
		this.library = arg0;
	}
	
	public Constraint builds(String arg0, Term<?> arg1)
	{
		return this.library.put(arg0,new Constraint(arg0, arg1));
	}
	
	public Map getConstraints()
	{
		return this.library;
	}
	public Constraint getConstraint(String arg0)
	{
		return this.library.get(arg0);
	}
	
	public ArrayList<Constraint> check()
	{
		ArrayList<Constraint> ret = new ArrayList<>();
		

		for (Map.Entry<String, Constraint> entry : this.library.entrySet()) 
		{
			Constraint c = entry.getValue();
			if(c.test()!= null)
				ret.add(c);
		}
		return ret;
	}
}
