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

import Constraints.QualityConstraint;
import MVC.Commande;
import MVC.Message;
import MVC.Modele;
import Parameters.Parameter;

/**
 * @author Aurélien Vialon
 *
 */
public class Engine extends Modele
{
	private ArrayList<QualityConstraint> qc = new ArrayList<QualityConstraint>();
	
	private Map<String, Parameter<?>> param; 
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


	private void RelevanceConsequences(ArrayList<QualityConstraint> arg0)
	{
		for(QualityConstraint qcc : this.qc)
		{
			qcc.checkRelevance();
		}
		
		for(QualityConstraint qcc : arg0)
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

					this.param.put(ligne, new Parameter<Integer>(ligne));
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
}
