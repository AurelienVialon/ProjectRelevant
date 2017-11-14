package constraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import exampleWorkingAgents.ConstraintName;
import relevance.Argument;

public class ConstraintsManager 
{
	private HashMap <ConstraintName, Constraint> library;
	
	public ConstraintsManager()
	{
		this.library = new HashMap<>();
	}
	public ConstraintsManager(HashMap<ConstraintName, Constraint> arg0) 
	{
		this.library = arg0;
	}
	
	public Constraint add(Constraint arg0)
	{
		return this.library.put(arg0.getName(),arg0);
	}
	public Constraint add(ConstraintName arg0, Argument arg1)
	{
		return this.library.put(arg0,new Constraint(arg0, arg1));
	}
	public Constraint add(ConstraintName arg0, ArrayList<Argument> arg1)
	{
		return this.library.put(arg0,new Constraint(arg0, arg1));
	}
	
	public void build()
	{
		for (Map.Entry<ConstraintName, Constraint> c : this.library.entrySet()) 
		{
			((Constraint)c).build();
		}
	}
	
	public HashMap<ConstraintName, Constraint> getConstraints()
	{
		return this.library;
	}
	public Constraint getConstraint(ConstraintName arg0)
	{
		return this.library.get(arg0);
	}
	
	public ArrayList<Constraint> check()
	{
		ArrayList<Constraint> ret = new ArrayList<>();
		

		for (Map.Entry<ConstraintName, Constraint> entry : this.library.entrySet()) 
		{
			Constraint c = entry.getValue();
			if(c.test()!= null)
				ret.add(c);
		}
		return ret;
	}
}
