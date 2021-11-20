package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;
import standalonecontrol.ControlloreRimuoviDomande;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class BoundaryRimuoviDomande {

    //Pannelli
    private JPanel pannelloRimuovi;
    private JPanel panelTitolo = new JPanel();
    private JPanel panelDomanda = new JPanel();
    private JPanel panelButton = new JPanel();
    private JPanel panelVisualButton= new JPanel();
    private JPanel panelTextArea = new JPanel();

    //Label
    public JLabel titolo = new JLabel();

    //Area di testo
    private JTextArea domandelista = new JTextArea();
    private JScrollPane listascroll = new JScrollPane(domandelista,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


    //Bottone
    private JButton bVisualizza;
    private JButton bRimuovi;
    private JButton bIndietro;


    // Campi e loro etichette
    private JLabel rimuoviLabel = new JLabel();
    private JTextField rimuoviF = new JTextField();

    // Ascoltatore di bottone e relative azioni
    private RimuoviAA ascoltatoreEtAzioneRimuovi;
    private VisualizzaAA ascoltatoreEtAzioneVisualizza;
    private TornaindietroAA ascoltatoreEtAzioneIndietro;

    ControlloreRimuoviDomande crf;
    private ControlloreLinguaAmministratore cl;

    public BoundaryRimuoviDomande()
    {
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

        crf = new ControlloreRimuoviDomande(domandelista);

        int border = 5;
        String fontv = "Verdana";
        String fonta = "Arial";

        pannelloRimuovi = new JPanel();

        pannelloRimuovi.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
        BoundaryAvvio.getConfine().add(pannelloRimuovi);
        pannelloRimuovi.setLayout(null);

        panelTextArea.setLayout(new BorderLayout());
        panelTextArea.setSize(BoundaryAvvio.getConfine().getWidth()*8/9, BoundaryAvvio.getConfine().getHeight()/6);
        panelTextArea.setLocation(50, 50);

        domandelista.setEditable(false);
        domandelista.setLineWrap(true);
        domandelista.setWrapStyleWord(true);
        panelTextArea.add(listascroll,BorderLayout.CENTER);


        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
        panelTitolo.setLocation(5, 5);
        panelTitolo.add(titolo);

        titolo.setFont(new Font(fontv, Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(SwingConstants.CENTER);
        titolo.setVerticalAlignment(SwingConstants.CENTER);
        titolo.setText(bundle.getString("boundaryDomande_inserisci_domanda_rimozione"));

        pannelloRimuovi.add(panelTitolo);


        // Creazione etichette campi con relativi nomi

        rimuoviLabel.setFont(new Font(fontv, Font.BOLD, 15));
        rimuoviLabel.setLocation(150, 50);
        rimuoviLabel.setSize(panelTitolo.getWidth()/2, 30);
        rimuoviLabel.setText(bundle.getString("boundaryDomande_domanda"));


        rimuoviF = new JTextField("", 60);
        rimuoviF.setFont(new Font(fontv, 0, 15));
        rimuoviF.setLocation(300,50);
        rimuoviF.setSize(panelTitolo.getWidth()/2, 30);

        // Creazione bottone
        bVisualizza = new JButton(bundle.getString("boundaryDomande_visualizza_domande"));
        bVisualizza.setLocation(300,0);
        bVisualizza.setSize(panelTitolo.getWidth()/3, 50);
        bVisualizza.setFont(new Font(fonta, 0, 20));

        bRimuovi = new JButton(bundle.getString("boundaryDomande_rimuovi_domanda"));
        bRimuovi.setLocation(200,10);
        bRimuovi.setSize(panelTitolo.getWidth()/4, 50);
        bRimuovi.setFont(new Font(fonta, 0, 20));

        bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
        bIndietro.setLocation(300+panelTitolo.getWidth()/4, 10);
        bIndietro.setSize(panelTitolo.getWidth()/4, 50);
        bIndietro.setFont(new Font(fonta, 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

        panelDomanda.setLayout(null);
        panelDomanda.setSize(BoundaryAvvio.getConfine().getWidth()*90/91, BoundaryAvvio.getConfine().getHeight()/5);
        panelDomanda.setLocation(5, 300);
        panelDomanda.setBackground(new Color(190, 190, 190));
        panelDomanda.add(rimuoviLabel);
        panelDomanda.add(rimuoviF);

        panelVisualButton.setLayout(null);
        panelVisualButton.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/6);
        panelVisualButton.setLocation(5, 190);
        panelVisualButton.add(bVisualizza);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/8);
        panelButton.setLocation(5, 480);
        panelButton.add(bRimuovi);



        pannelloRimuovi.add(panelDomanda);
        pannelloRimuovi.add(panelButton);
        pannelloRimuovi.add(panelVisualButton);
        panelButton.add(bIndietro);
        pannelloRimuovi.add(panelTextArea);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneVisualizza = new VisualizzaAA();
        ascoltatoreEtAzioneRimuovi = new RimuoviAA();
        ascoltatoreEtAzioneIndietro = new TornaindietroAA();



        // Associazione di bottone a relativo ascoltatore
        bVisualizza.addActionListener(ascoltatoreEtAzioneVisualizza);
        bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
        bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
    }
    // Fine costruttore

    //Ascoltatore

    private class RimuoviAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloRimuovi.setVisible(false);
                crf.rimuovi(rimuoviF.getText());

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

                crf.visualizzaDomandeSenzaRisposta();


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private class TornaindietroAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloRimuovi.setVisible(false);
                new BoundaryAmministrazione();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

