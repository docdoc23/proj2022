
package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


class BoundaryGestioneDomande {

    // Pannelli
    private JPanel pannelloAmministrazione;
    private JPanel panelTitolo = new JPanel();
    private JPanel panelButton = new JPanel();

    // Label
    private JLabel titolo = new JLabel();

    //Bottoni
    private JButton bRispondi;
    private JButton bRimuoviDati;
    private JButton bIndietro;

    // Ascoltatore di bottone e relative azioni
    private RispondiAA ascoltatoreEtAzioneRispondi;
    private RimuoviFaqAA ascoltatoreEtAzioneRimuoviFaq;
    private TornaIndietro ascoltatoreTornaIndietro;

    private ControlloreLinguaAmministratore cl;



    BoundaryGestioneDomande()
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
        titolo.setText(bundle.getString("posta_azioni"));

        pannelloAmministrazione.add(panelTitolo);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.getConfine().getWidth(),400);
        panelButton.setLocation(5, 30);

        bRispondi = new JButton(bundle.getString("boundaryDomande_rispondi_domande"));
        bRispondi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
        bRispondi.setSize(panelButton.getWidth()/3,50);
        bRispondi.setFont(new Font(font, 0, 20));
        bRispondi.setToolTipText(bundle.getString("boundaryDomande_domande_utenti"));

        bRimuoviDati = new JButton(bundle.getString("boundaryDomande_rimuovi"));
        bRimuoviDati.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
        bRimuoviDati.setSize(panelButton.getWidth()/3,50);
        bRimuoviDati.setFont(new Font(font, 0, 20));
        bRimuoviDati.setToolTipText(bundle.getString("boundaryDomande_inappropriate"));

        bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font(font, 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

        panelButton.add(bRispondi);
        panelButton.add(bRimuoviDati);
        panelButton.add(bIndietro);

        pannelloAmministrazione.add(panelButton);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneRispondi = new RispondiAA();
        ascoltatoreEtAzioneRimuoviFaq = new RimuoviFaqAA();
        ascoltatoreTornaIndietro = new TornaIndietro();


        // Associazione di bottone a relativo ascoltatore
        bRispondi.addActionListener(ascoltatoreEtAzioneRispondi);
        bRimuoviDati.addActionListener(ascoltatoreEtAzioneRimuoviFaq);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);

    }
    // Fine costruttore

    // Ascoltatori

    private class RispondiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRispostaDomande();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class RimuoviFaqAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviDomande();

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

