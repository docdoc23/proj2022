package standaloneboundary;

import javax.swing.*;
import java.io.Serializable;
import java.util.Stack;

public class StackFrame extends JFrame implements Serializable
{	
	private static final long serialVersionUID	= 1L;
	//Variabili di classe
	protected static Stack <StackFrame> pilaCornici;
	
	public StackFrame()
	{
		super();
		pilaCornici = new Stack<>();
	}
	
	public static void rimuoviDalTop(StackFrame c) 
	{
		while  (!StackFrame.pilaCornici.empty() && StackFrame.pilaCornici.peek().getClass().equals(c.getClass()))
		{
			StackFrame.pilaCornici.pop();
		}
	}
}
