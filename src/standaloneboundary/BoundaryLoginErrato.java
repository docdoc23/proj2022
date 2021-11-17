package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryLoginErrato extends JFrame {
	
private static final long	serialVersionUID	= 1L;
	

	// Pannelli
	private JPanel pannelloLoginErrato;	
	private JPanel panelTitolo = new JPanel();
	private JPanel panelButtonIndietro = new JPanel();
	
	//Label
	private JLabel titolo = new JLabel();
	
	//Bottone
	private JButton bIndietro; 
	
	// Ascoltatore di bottone e relativa azioni
	private IndietroAA ascoltatoreEtAzioneIndietro;

	private ControlloreLinguaAmministratore cl;

	
	public BoundaryLoginErrato()
	{
	    this.cl = new ControlloreLinguaAmministratore();
	    ResourceBundle bundle = this.cl.getBundleFromProp();

		int border = 5;
		
		pannelloLoginErrato = new JPanel();
		
		pannelloLoginErrato.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
		BoundaryAvvio.getConfine().add(pannelloLoginErrato);
		pannelloLoginErrato.setLayout(null);
		
        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setVerticalAlignment(SwingConstants.CENTER);
        titolo.setText(bundle.getString("boundaryLoginerrato_parametri_errati"));
        
        pannelloLoginErrato.add(panelTitolo);	
        
		panelButtonIndietro.setLayout(null);
		panelButtonIndietro.setSize(BoundaryAvvio.getConfine().getWidth(), 150);
		panelButtonIndietro.setLocation(5, 30); 
		
		
		
		bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
		bIndietro.setLocation(300,100);
		bIndietro.setSize(panelTitolo.getWidth()/4,50);
		bIndietro.setFont(new Font("Arial", 0, 20));
		bIndietro.setToolTipText(bundle.getString("visualizzaPosta_indietro"));
		
		panelButtonIndietro.add(bIndietro);
        	
		pannelloLoginErrato.add(panelButtonIndietro);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneIndietro = new IndietroAA();
		
		
		// Associazione di bottone a relativo ascoltatore
		bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
		
	}
	// Fine costruttore
	
	
	//Ascoltatore
	private class IndietroAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
                pannelloLoginErrato.setVisible(false);
                new BoundaryLogin();
				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}		
}