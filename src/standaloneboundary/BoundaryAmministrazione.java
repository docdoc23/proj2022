package standaloneboundary;

import constants.Constants;
import standalonecontrol.ControlloreLinguaAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryAmministrazione {
	
	// Pannelli
	private JPanel pannelloAmministrazione;	
	private JPanel panelTitolo = new JPanel();
	private JPanel panelButton = new JPanel();
	
	// Label
	private JLabel titolo = new JLabel();
	
	//Bottoni
	private JButton bRimuovi;
	private JButton bNomina;
	private JButton bIndietro;
	private JButton bRisposta;
	private JButton bProfilo;
	
	// Ascoltatore di bottone e relativa azioni
	private RimuoviAA ascoltatoreEtAzioneRimuovi;
	private NominaAA ascoltatoreEtAzioneNomina;
    private TornaIndietro ascoltatoreTornaIndietro;
	private RispondiFaqAA ascoltatoreRispostaFaq;
	private Profilo ascoltatoreProfilo;

	private ControlloreLinguaAmministratore cl;

	
	public BoundaryAmministrazione()
	{
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

		int border = 5;
		String font = "Arial";
		
		pannelloAmministrazione = new JPanel();
		
		pannelloAmministrazione.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
		BoundaryAvvio.getConfine().add(pannelloAmministrazione);
		pannelloAmministrazione.setLayout(null);
		
        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText(bundle.getString("boundaryAmministrazione_benvenuto"));
        
        pannelloAmministrazione.add(panelTitolo);	
        
		panelButton.setLayout(null);
		panelButton.setSize(BoundaryAvvio.getConfine().getWidth(),500);
		panelButton.setLocation(5, 30); 
		
		bRimuovi = new JButton(bundle.getString("boundaryAmministrazione_rimuovi"));
		bRimuovi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
		bRimuovi.setSize(panelButton.getWidth()/3,50);
		bRimuovi.setFont(new Font(font, 0, 20));
		bRimuovi.setToolTipText(bundle.getString("boundaryAmministrazione_rimuovi"));
		
		bNomina = new JButton(bundle.getString("boundaryAmministrazione_crea"));
		bNomina.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
		bNomina.setSize(panelButton.getWidth()/3,50);
		bNomina.setFont(new Font(font, 0, 20));
		bNomina.setToolTipText(bundle.getString("boundaryAmministrazione_nomina"));

		bProfilo = new JButton(bundle.getString("boundaryAmministrazione_gestione_profilo"));
		bProfilo.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6, 320);
        bProfilo.setSize(panelButton.getWidth()/3, 50);
        bProfilo.setFont(new Font(font, 0, 20));
        bProfilo.setToolTipText(bundle.getString("boundaryAmministrazione_gestione_profilo"));
        if (System.getProperty(Constants.USER_KEY)
                .equals("root"))
            bProfilo.setEnabled(false);

        bIndietro = new JButton(bundle.getString("boundaryAmministrazione_indietro"));
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,450);
		bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font(font, 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

		bRisposta = new JButton(bundle.getString("boundaryAmministrazione_domande"));
		bRisposta.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,240);
		bRisposta.setSize(panelButton.getWidth()/3, 50);
		bRisposta.setFont(new Font(font, 0, 20));
		bRisposta.setToolTipText(bundle.getString("boundaryAmministrazione_domande"));

		panelButton.add(bRimuovi);
		panelButton.add(bNomina);
        panelButton.add(bIndietro);
		panelButton.add(bRisposta);
		panelButton.add(bProfilo);
        	
		pannelloAmministrazione.add(panelButton);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneRimuovi = new RimuoviAA();
		ascoltatoreEtAzioneNomina = new NominaAA();
        ascoltatoreTornaIndietro = new TornaIndietro();
		ascoltatoreRispostaFaq = new RispondiFaqAA();
		ascoltatoreProfilo = new Profilo();


		// Associazione di bottone a relativo ascoltatore
		bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
		bNomina.addActionListener(ascoltatoreEtAzioneNomina);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);
		bRisposta.addActionListener(ascoltatoreRispostaFaq);
		bProfilo.addActionListener(ascoltatoreProfilo);

	}

	// Ascoltatori
	private class RimuoviAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloAmministrazione.setVisible(false);
				new BoundaryGestioneUtenti();
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}
	
	private class NominaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloAmministrazione.setVisible(false);
				new BoundaryCreaAmministratore();
				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}

	private class Profilo implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0) {
        	try {
                pannelloAmministrazione.setVisible(false);
                new BoundaryProfilo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private class TornaIndietro implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try {
                pannelloAmministrazione.setVisible(false);
                new BoundaryLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private class RispondiFaqAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				pannelloAmministrazione.setVisible(false);
				new BoundaryGestioneDomande();

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}