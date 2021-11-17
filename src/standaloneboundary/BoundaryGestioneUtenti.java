
package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryGestioneUtenti {

    // Pannelli
    private JPanel pannelloAmministrazione;
    private JPanel panelTitolo = new JPanel();
    private JPanel panelButton = new JPanel();

    // Label
    private JLabel titolo = new JLabel();

    //Bottoni
    private JButton bRimuovi;
    private JButton bRimuoviDati;
    private JButton bIndietro;

    // Ascoltatore di bottone e relativa azioni
    private RimuoviAA ascoltatoreEtAzioneRimuovi;
    private RimuoviDatiAA ascoltatoreEtAzioneRimuoviDati;
    private TornaIndietro ascoltatoreTornaIndietro;

    private ControlloreLinguaAmministratore cl;


    public BoundaryGestioneUtenti()
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
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setVerticalAlignment(SwingConstants.CENTER);
        titolo.setText(bundle.getString("posta_azioni"));

        pannelloAmministrazione.add(panelTitolo);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.getConfine().getWidth(),400);
        panelButton.setLocation(5, 30);

        bRimuovi = new JButton(bundle.getString("boundaryGestioneUtenti_rimuovi_utente"));
        bRimuovi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
        bRimuovi.setSize(panelButton.getWidth()/3,50);
        bRimuovi.setFont(new Font(font, 0, 20));
        bRimuovi.setToolTipText(bundle.getString("boundaryGestioneUtenti_rimuovi_utente"));

        bRimuoviDati = new JButton(bundle.getString("boundaryGestioneUtenti_rimuovi_dati"));
        bRimuoviDati.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
        bRimuoviDati.setSize(panelButton.getWidth()/3,50);
        bRimuoviDati.setFont(new Font(font, 0, 20));
        bRimuoviDati.setToolTipText(bundle.getString("boundaryGestioneUtenti_rimuovi_dati"));

        bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font(font, 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

        panelButton.add(bRimuovi);
        panelButton.add(bRimuoviDati);
        panelButton.add(bIndietro);

        pannelloAmministrazione.add(panelButton);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneRimuovi = new RimuoviAA();
        ascoltatoreEtAzioneRimuoviDati = new RimuoviDatiAA();
        ascoltatoreTornaIndietro = new TornaIndietro();


        // Associazione di bottone a relativo ascoltatore
        bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
        bRimuoviDati.addActionListener(ascoltatoreEtAzioneRimuoviDati);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);

    }
    // Fine costruttore

    // Ascoltatori

    private class RimuoviAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviUtente();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class RimuoviDatiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviDatiUtente();

            }
            catch (Exception e)
            {
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
                new BoundaryAmministrazione();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
