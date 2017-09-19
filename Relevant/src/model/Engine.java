/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import Argument.Argument;
import Constraints.QC;
import MVC.Commande;
import MVC.Message;
import MVC.Modele;
import MeetingScheduler.parameters.Participants;
import Parameters.Parameter;

/**
 * @author Aurélien Vialon
 *
 */
public class Engine extends Modele
{
	private ArrayList<QC> qcs = new ArrayList<QC>();
	private ArrayList<QC> violated_qcs = new ArrayList<QC>();
	
	private ArrayList<Argument> arg = new ArrayList<Argument>();
	/**
	 * 
	 */

	@Override
	public void Reinit()
	{
		try {
			this.readFile("C:\\Users\\aurel\\git\\ProjectRelevant\\relevant\\QC_DECLARATION_EXAMPLE.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub	
	}

	@Override
	protected void Maj()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected <T> void executerCommande(Commande<T> c)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Commande<?> messageCommande(Message m)
	{  
		return null;
	}


	public void addQualityConstraints(QC arg0)
	{
		this.qcs.add(arg0);
	}
	public void addQualityConstraints(ArrayList<QC> arg0)
	{
		this.qcs.addAll(arg0);
	}
	
	public void addArguments(Argument arg0)
	{
		this.arg.add(arg0);
	}
	public void addArguments(ArrayList<Argument> arg0)
	{
		this.arg.addAll(arg0);
	}
	
	public boolean checkQCs()
	{
		boolean ret = true;
		this.violated_qcs.clear();
		
		for(QC qc : this.qcs)
		{
			if(qc.test() != null)
			{
				this.violated_qcs.add(qc);
				ret = false;
			}
		}
		return ret;
	}
	
	private void RelevanceConsequences(ArrayList<QC> arg0)
	{
		for(QC qcc : this.qcs)
		{
			qcc.checkRelevance();
		}
		
		for(QC qcc : arg0)
		{
			if(qcc.getExpectedSuccessRate() > qcc.getRelevance())
				qcc.setExpectedSuccessRate(qcc.getExpectedSuccessRate() - 5);
		}
		
	}
	
	private boolean readFile(String arg0) throws IOException
	{
		String qc = "", param = "";
		InputStream ips;
		try
		{
			ips = new FileInputStream(arg0);
			 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			
			String ligne;
			while ((ligne = br.readLine()) != null) 
			{
				if(ligne.contains("*/"))
				{
					while((ligne = br.readLine()) != null)
					{
						if(ligne.contains("/*"))
							break;
					}
				}
				else if(ligne.contains("param/"))
				{
					ligne = br.readLine();

					//this.param.put(ligne, new Participants(ligne));
				}
				else if(ligne.contains("qc/"))
				{
					String n="", exp ="";
					while((ligne = br.readLine()) != null)
					{
						if(ligne.contains("/qc"))
							break;
						else if(ligne.contains("name/"))
							n = ligne;
						else if(ligne.contains("expression/"))
						{
							
						}
					}
					//QualityConstraint qc = new QualityConstraint(n, exp);
					if(ligne.contains("arg/"))
					{
						String fact ="", rule= "", RI = "";
						while((ligne = br.readLine()) != null)
						{
							if(ligne.contains("/arg"))
								break;
							if(ligne.contains("fact/"))
								fact = br.readLine();
							if(ligne.contains("rule/"))
								rule = br.readLine();
							if(ligne.contains("RI/"))
								RI = br.readLine();
							
							//Argument<?> a = new Argument(this.param.get(fact), rule, RI);
						}
					}
				}
			}
			br.close();		
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String explain_violation()
	{
		String ret = "There are no violated constraints.";
		if(this.violated_qcs.size() > 0)
		{
			ret = " The following quality constraints are violated : ";
			for(QC qc : this.violated_qcs)
			{
				ret += qc.getName() + ", ";
			}
		}
		
		return ret;
	}
}
