package standaloneboundary;


import standalonecontrol.ControlloreLinguaAmministratore;
import standalonecontrol.ControlloreRimuoviDatiUtente;
import standaloneutils.StampaStringhe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class BoundaryRimuoviDatiUtente {

    //Pannelli
    private JPanel pannelloRimuoviDati;
    private JPanel panelTitolo = new JPanel();
    private JPanel panelUser = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelVisualButton= new JPanel();
    private JPanel panelTextArea = new JPanel();

    //Label
    private JLabel titolo = new JLabel();

    //Area di testo
    JTextArea utentilista = new JTextArea();
    JScrollPane listascroll = new JScrollPane(utentilista,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    //Bottone
    private JButton bVisualizza;
    private JButton bRimuovi;
    private JButton bIndietro;


    // Campi e loro etichette
    private JLabel rimuoviLabel = new JLabel();
    private JTextField rimuoviF = new JTextField();

    // Ascoltatore di bottone e relative azioni
    private RimuoviDatiAA ascoltatoreEtAzioneRimuoviDati;
    private VisualizzaAA ascoltatoreEtAzioneVisualizza;
    private tornaindietroAa ascoltatoreEtAzioneIndietro;

    private ControlloreLinguaAmministratore cl;

    public BoundaryRimuoviDatiUtente()
    {
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

        int border = 5;
        String fontv = "Verdana";
        String fonta = "Arial";

        pannelloRimuoviDati = new JPanel();

        pannelloRimuoviDati.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
        BoundaryAvvio.getConfine().add(pannelloRimuoviDati);
        pannelloRimuoviDati.setLayout(null);

        panelTextArea.setLayout(new BorderLayout());
        panelTextArea.setSize(BoundaryAvvio.getConfine().getWidth()*8/9, BoundaryAvvio.getConfine().getHeight()/6);
        panelTextArea.setLocation(50, 50);

        utentilista.setEditable(false);
        utentilista.setLineWrap(true);
        utentilista.setWrapStyleWord(true);
        panelTextArea.add(listascroll,BorderLayout.CENTER);


        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
        panelTitolo.setLocation(5, 5);
        panelTitolo.add(titolo);

        titolo.setFont(new Font(fontv, Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText(bundle.getString("boundaryRimuoviDati_utente_da_rimuovere"));

        pannelloRimuoviDati.add(panelTitolo);


        // Creazione etichette campi con relativi nomi

        rimuoviLabel.setFont(new Font(fontv, Font.BOLD, 15));
        rimuoviLabel.setLocation(150, 50);
        rimuoviLabel.setSize(panelTitolo.getWidth()/2, 30);
        rimuoviLabel.setText(bundle.getString("index_nomeUtente"));


        rimuoviF = new JTextField("", 60);
        rimuoviF.setFont(new Font(fontv, 0, 15));
        rimuoviF.setLocation(300,50);
        rimuoviF.setSize(panelTitolo.getWidth()/2, 30);

        // Creazione bottone
        bVisualizza = new JButton(bundle.getString("boundaryRimuoviUtente_visualizza_utenti"));
        bVisualizza.setLocation(330,0);
        bVisualizza.setSize(panelTitolo.getWidth()/4, 50);
        bVisualizza.setFont(new Font(fonta, 0, 20));

        bRimuovi = new JButton(bundle.getString("boundaryRimuoviDati_rimuovi_dati"));
        bRimuovi.setLocation(150,10);
        bRimuovi.setSize(panelTitolo.getWidth()/3, 50);
        bRimuovi.setFont(new Font(fonta, 0, 20));

        bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));
        bIndietro.setLocation(300+panelTitolo.getWidth()/4, 10);
        bIndietro.setSize(panelTitolo.getWidth()/4, 50);
        bIndietro.setFont(new Font(fonta, 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

        panelUser.setLayout(null);
        panelUser.setSize(BoundaryAvvio.getConfine().getWidth()*90/91, BoundaryAvvio.getConfine().getHeight()/5);
        panelUser.setLocation(5, 300);
        panelUser.setBackground(new Color(190, 190, 190));
        panelUser.add(rimuoviLabel);
        panelUser.add(rimuoviF);

        panelVisualButton.setLayout(null);
        panelVisualButton.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/6);
        panelVisualButton.setLocation(5, 190);
        panelVisualButton.add(bVisualizza);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/8);
        panelButton.setLocation(5, 480);
        panelButton.add(bRimuovi);



        pannelloRimuoviDati.add(panelUser);
        pannelloRimuoviDati.add(panelButton);
        pannelloRimuoviDati.add(panelVisualButton);
        panelButton.add(bIndietro);
        pannelloRimuoviDati.add(panelTextArea);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneVisualizza = new VisualizzaAA();
        ascoltatoreEtAzioneRimuoviDati = new RimuoviDatiAA();
        ascoltatoreEtAzioneIndietro = new tornaindietroAa();



        // Associazione di bottone a relativo ascoltatore
        bVisualizza.addActionListener(ascoltatoreEtAzioneVisualizza);
        bRimuovi.addActionListener(ascoltatoreEtAzioneRimuoviDati);
        bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
    }
    // Fine costruttore

    //Ascoltatore

    private class RimuoviDatiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloRimuoviDati.setVisible(false);
                ControlloreRimuoviDatiUtente crd = new ControlloreRimuoviDatiUtente();
                crd.rimuovi(rimuoviF.getText());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class VisualizzaAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                StampaStringhe vu = new StampaStringhe(utentilista);
                vu.visualizzaUtenti();
                vu.visualizzaUtentiRimossiMaConDati();
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
                pannelloRimuoviDati.setVisible(false);
                new BoundaryAmministrazione();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


}
