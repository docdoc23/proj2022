package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreCreaAmministratore;
import standalonecontrol.ControlloreLinguaAmministratore;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class BoundaryCreaAmministratore {


	//Pannelli
	private JPanel pannelloCreazione;
	private JPanel panelTitolo = new JPanel();
	private JPanel panelUsername = new JPanel();
	private JPanel panelPassword = new JPanel();
	private JPanel panelButtonCrea = new JPanel();

	//Label
	private JLabel titolo = new JLabel();

	//Bottoni
	private JButton bNomina;
	private JButton bIndietro;

	// Campi e loro etichette
	private JLabel usernameLabel = new JLabel();
	private JTextField usernameF = new JTextField();
	private JLabel passwordLabel = new JLabel();
	private JPasswordField passwordF = new JPasswordField();

	// Ascoltatore di bottone e relativa azioni
	private CreaAA ascoltatoreEtAzioneCrea;
	private tornaindietroAa ascoltatoreEtAzioneIndietro;
	private ControlloreLinguaAmministratore cl;


	public BoundaryCreaAmministratore()
	{
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

		int border = 5;
		String font = "Verdana";

		pannelloCreazione = new JPanel();
		pannelloCreazione.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
		BoundaryAvvio.getConfine().add(pannelloCreazione);
		pannelloCreazione.setLayout(null);


		
		panelTitolo.setLayout(null);
		panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font(font, Font.BOLD, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		titolo.setVerticalAlignment(SwingConstants.CENTER);
		titolo.setText(bundle.getString("boundaryCreaAmministratore_completa_campi"));

		pannelloCreazione.add(panelTitolo);


		// Creazione etichette campi con relativi nomi

		usernameLabel.setFont(new Font(font, Font.BOLD, 15));
		usernameLabel.setLocation(150, 50);
		usernameLabel.setSize(panelTitolo.getWidth()/2, 30);
		usernameLabel.setText(bundle.getString("index_nomeUtente"));


		usernameF = new JTextField("", 60);
		usernameF.setFont(new Font(font, 0, 15));
		usernameF.setLocation(300,50);
		usernameF.setSize(panelTitolo.getWidth()/2, 30);

		passwordLabel.setFont(new Font(font, Font.BOLD, 15));
		passwordLabel.setLocation(150, 50);
		passwordLabel.setSize(panelTitolo.getWidth()/2, 30);
		passwordLabel.setText(bundle.getString("index_password"));


		passwordF = new JPasswordField("", 60);
		passwordF.setLocation(300,50);
		passwordF.setSize(panelTitolo.getWidth()/2, 30);
		passwordF.setFont(new Font(font, 0, 15));

		// Creazione bottoni
		bNomina = new JButton(bundle.getString("boundaryAmministrazione_crea"));
		bNomina.setLocation(150,50);
		bNomina.setSize(panelTitolo.getWidth()/3, 50);
		bNomina.setFont(new Font("Arial", 0, 20));

		bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));
		bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
		bIndietro.setSize(panelTitolo.getWidth()/4, 50);
		bIndietro.setFont(new Font("Arial", 0, 20));
		bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));


		panelUsername.setLayout(null);
		panelUsername.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/5);
		
		panelUsername.setLocation(5, 70);
		panelUsername.setBackground(new Color(190, 190, 190));
		panelUsername.add(usernameLabel);
		panelUsername.add(usernameF);


		panelPassword.setLayout(null);
		panelPassword.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/5);
		
		panelPassword.setLocation(5, 200);
		panelPassword.setBackground(new Color(190, 190, 190));
		panelPassword.add(passwordLabel);
		panelPassword.add(passwordF);
		

		panelButtonCrea.setLayout(null);
		panelButtonCrea.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/5);
		panelButtonCrea.setLocation(5, 430);

		panelButtonCrea.add(bNomina);
		panelButtonCrea.add(bIndietro);


		pannelloCreazione.add(panelUsername);
		pannelloCreazione.add(panelPassword);
		pannelloCreazione.add(panelButtonCrea);



		// Ascoltatori dei bottoni e relativa azione
		ascoltatoreEtAzioneCrea = new CreaAA();
		ascoltatoreEtAzioneIndietro = new tornaindietroAa();


		// Associazione di bottone a relativo ascoltatore
		bNomina.addActionListener(ascoltatoreEtAzioneCrea);
		bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);

	}
	// Fine costruttore


	// Ascoltatore

	private class CreaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
                ResourceBundle bundle = cl.getBundleFromProp();

				pannelloCreazione.setVisible(false);
				if (!usernameF.getText()
                        .equals("root")) {
                    ControlloreCreaAmministratore cna = new ControlloreCreaAmministratore();
                    cna.creaAmministratore(usernameF.getText(), passwordF.getPassword());
                    return;
                }

                new BoundaryFallimento(bundle.getString("boundaryCreaAmministratore_user_invalid"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private class tornaindietroAa implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				pannelloCreazione.setVisible(false);
				new BoundaryAmministrazione();

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}