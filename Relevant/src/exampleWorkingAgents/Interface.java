package exampleWorkingAgents;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import MVC.Message;
import MVC.Modele;
import MVC.SujetMessage;
import MVC.Vue;
import parameters.Parameter;
import sun.management.Agent;

public class Interface extends Vue
{	
	HashMap< String, JComponent > linksComponentsModels = new HashMap<>();
	
	JPanel generalImput;
	JPanel agentsImput;
	
	HashMap<ParameterName, JComponent> parametersImput = new HashMap<>();
	HashMap<String, JComponent> agentsParametersImput = new HashMap<>();
	
	public Interface() 
	{
		super();
		this.setName("Project Relevance");
		this.setTitle(" Project Relevance Demonstration ");
		this.setSize(1280, 1024);

		this.generalImput = new JPanel();
		this.agentsImput = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(this.generalImput, BorderLayout.PAGE_END);
		this.add(this.agentsImput, BorderLayout.LINE_END);
		this.generalImput.setVisible(true);
			
		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object ob) 
	{
		if(o instanceof Parameter)
		{
			if(((Parameter)o).getValue() instanceof Boolean)
			{
				((JRadioButton)this.parametersImput.get(((Parameter) o).getName())).setSelected((Boolean)((Parameter) o).getValue());
			}
			else
			{
				((JTextField)this.parametersImput.get(((Parameter) o).getName())).setText(((Parameter) o).getValue().toString());	
			}
		}
		if(o instanceof WorkingAgent)
		{
			if(ob instanceof Message)
			{
				 SujetMessage sujet =  (SujetMessage)((Message)ob).getSujet();
				 String contenu = (String)((Message)ob).attache;
			
				JTextPane aUpdate = (JTextPane)this.linksComponentsModels.get(((WorkingAgent) o).getName());
				StyledDocument doc = aUpdate.getStyledDocument();
				Style style = null;
				
				if(sujet== SujetMessage.ModifParameter )
				{
					style = aUpdate.addStyle("StyleRed", null);
					StyleConstants.setFontSize(style, 45);
					StyleConstants.setForeground(style, Color.red);
				}
				else if(sujet== SujetMessage.Reassessment)
				{
					style = aUpdate.addStyle("StyleBlue", null);
					StyleConstants.setFontSize(style, 35);
					StyleConstants.setForeground(style, Color.blue);
				}
				else if(sujet== SujetMessage.ConstraintChange)
				{
					style = aUpdate.addStyle("StyleRedPetit", null);
					StyleConstants.setFontSize(style, 35);
					StyleConstants.setForeground(style, Color.red);
				}
				else if(sujet== SujetMessage.ConstraintNoChange)
				{
					style = aUpdate.addStyle("StyleGreen", null);
					StyleConstants.setFontSize(style, 35);
					StyleConstants.setForeground(style, Color.MAGENTA);
				}
				else if(sujet== SujetMessage.ArgumentChange)
				{
					style = aUpdate.addStyle("StylePink", null);
					StyleConstants.setFontSize(style, 35);
					StyleConstants.setForeground(style, Color.PINK);
				}
				
				
				try 
				{
					doc.insertString(doc.getLength(), contenu, style);
				} 
				catch (BadLocationException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				JTextPane aUpdate = (JTextPane)this.linksComponentsModels.get(((WorkingAgent) o).getName());
				StyledDocument doc = aUpdate.getStyledDocument();
				Style style =aUpdate.getStyle("Style");
	
				try 
				{
					doc.insertString(doc.getLength(), (String)ob, style);
				} 
				catch (BadLocationException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void createNewView(Modele arg0)
	{
		if(arg0 instanceof WorkingAgent)
		{
			JTextPane Toutput = new JTextPane();
			JScrollPane  sp = new JScrollPane(Toutput);
			StyledDocument doc = Toutput.getStyledDocument();

			Style style = Toutput.addStyle("Style", null);
			Style titre = Toutput.addStyle("Titre", null);
			
			StyleConstants.setFontSize(style, 35);
			StyleConstants.setForeground(style, Color.black);
			
			StyleConstants.setFontSize(titre, 50);
			StyleConstants.setForeground(titre, Color.black);
			
			try 
			{
				doc.insertString(doc.getLength(), "Trace of " + arg0.getName(), titre);
			} 
			catch (BadLocationException e) 
			{
				e.printStackTrace();
			}
			
			Toutput.setEditable(false);
			Toutput.setVisible(true);
		
			this.linksComponentsModels.put(((WorkingAgent)arg0).getName(), Toutput);

			
			this.add(sp);
		}
	}
	
	public void addParameterInput(Parameter<?> p)
	{
		JPanel contenor = new JPanel();
		JLabel name = new JLabel(p.getName().toString());
		JComponent input = null;
		name.setText(p.getName().toString() +" : ");
		name.setFont(new Font("serif", Font.PLAIN, 35));
		
		if(p.getValue() instanceof Boolean)
		{
			input = new JRadioButton("True");
				
			((JRadioButton)input).addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JRadioButton btn = (JRadioButton) e.getSource();
		            Parameter<Boolean> param = (Parameter<Boolean>) p;
		            param.setValue(btn.isSelected());
		        }});
		}
		else
		{
			input = new JTextField(p.getValue().toString());	
						
			((JTextField)input).addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	JTextField tf = (JTextField) e.getSource();
		           
		        	if(p.getValue() instanceof String)
		    		{
		        		Parameter<String> param = (Parameter<String>) p;
		        		param.setValue(tf.getText());
		    		}
		        	else if(p.getValue() instanceof Integer)
		        	{
		        		Parameter<Integer> param = (Parameter<Integer>) p;
		        		param.setValue(Integer.valueOf(tf.getText()));
		        	}
		        	
		        }});
		}
		
		input.setName(p.getName().toString());
		
		input.setFont(new Font("serif", Font.PLAIN, 35));
		name.setFont(new Font("serif", Font.PLAIN, 35));
		
		this.parametersImput.put(p.getName(), input);
		p.addObserver(this);
		
		contenor.add(name);
		contenor.add(input);
		this.generalImput.add(contenor);
		contenor.setVisible(true);
	}
	public void addAgentInternalParameterImput(String an, Parameter<?> p)
	{
		JPanel pa;
		if(this.agentsParametersImput.containsKey(an))
		{
			pa = (JPanel) this.agentsParametersImput.get(an);
		}
		else
		{
			pa = new JPanel();
			pa.setName(an);
			pa.setVisible(true);
			JLabel l = new JLabel(an+ " : ");
			l.setText(" Internal Parameters of " + an+ " : ");
			l.setFont(new Font("serif", Font.PLAIN, 35));
			
			pa.setLayout(new GridLayout(0,1));
			pa.add(l);
			this.agentsParametersImput.put(an,  pa);
			this.agentsImput.add(pa);
		}
		JPanel contenor = new JPanel();
		
		JLabel name = new JLabel(p.getName().toString());
		JComponent input = null;
		name.setText(p.getName().toString() +" : ");
		name.setFont(new Font("serif", Font.PLAIN, 35));
		
		if(p.getValue() instanceof Boolean)
		{
			input = new JRadioButton("True");

			((JRadioButton)input).addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JRadioButton btn = (JRadioButton) e.getSource();
		            Parameter<Boolean> param = (Parameter<Boolean>) p;
		            param.setValue(btn.isSelected());
		        }});
		}
		else
		{
			input = new JTextField(p.getValue().toString());	

			((JTextField)input).addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	JTextField tf = (JTextField) e.getSource();
		           
		        	if(p.getValue() instanceof String)
		    		{
		        		Parameter<String> param = (Parameter<String>) p;
		        		param.setValue(tf.getText());
		    		}
		        	else if(p.getValue() instanceof Integer)
		        	{
		        		Parameter<Integer> param = (Parameter<Integer>) p;
		        		param.setValue(Integer.valueOf(tf.getText()));
		        	}
		        	
		        }});
		}
		
		
		input.setName(p.getName().toString() + "of" + an);
		
		input.setFont(new Font("serif", Font.PLAIN, 35));
		name.setFont(new Font("serif", Font.PLAIN, 35));
		
		this.parametersImput.put(p.getName(), input);
		p.addObserver(this);
		
		contenor.add(name);
		contenor.add(input);
		pa.add(contenor);
		contenor.setVisible(true);
	}
}
