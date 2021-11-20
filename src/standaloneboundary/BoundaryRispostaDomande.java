package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;
import standalonecontrol.ControlloreRispondiDomande;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryRispostaDomande {


        ControlloreRispondiDomande crf = new ControlloreRispondiDomande();

        //Pannelli
        private JPanel pannelloFaqRisposta;
        private JPanel panelTitolo = new JPanel();
        private JPanel panelRisposta = new JPanel();
        private JPanel panelButtons = new JPanel();
        private JPanel panelBox = new JPanel();
        private JPanel panelEtichetta = new JPanel();


        //Label
        private JLabel titolo = new JLabel();

        //Text area
        JTextArea rislista = new JTextArea();
        JScrollPane listascroll = new JScrollPane(rislista,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        private JLabel rispondiLabel = new JLabel();


        //Bottone

        private JButton bRispondi;
        private JButton bIndietro;

        //Combo box
        private JComboBox box;
        private String[] domande; //array di domande senza risposta
        private String domanda; // domada a cui si vuole rispondere



        // Ascoltatore di bottone e relative azioni
        private RispondiAA ascoltatoreEtAzioneRispondi;
        private tornaindietroAA ascoltatoreEtAzioneIndietro;

        private ControlloreLinguaAmministratore cl;

        public BoundaryRispostaDomande()
        {
            this.cl = new ControlloreLinguaAmministratore();
            ResourceBundle bundle = this.cl.getBundleFromProp();

            int border = 5;
            //pannello esterno che contiene tutto
            pannelloFaqRisposta = new JPanel();

            pannelloFaqRisposta.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight());
            BoundaryAvvio.getConfine().add(pannelloFaqRisposta);
            pannelloFaqRisposta.setLayout(null);

            panelBox.setLayout(new FlowLayout());
            panelBox.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/5);
            panelBox.setLocation(5, 50);

            this.costrusciComboBox();

            panelEtichetta.setLayout(null);
            panelEtichetta.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
            panelEtichetta.setLocation(5, 280);
            panelEtichetta.add(rispondiLabel);


            panelTitolo.setLayout(null);
            panelTitolo.setSize(BoundaryAvvio.getConfine().getWidth(), 45);
            panelTitolo.setLocation(5, 5);
            panelTitolo.add(titolo);

            titolo.setFont(new Font("Verdana", Font.BOLD, 20));
            titolo.setLocation(border, border);
            titolo.setSize(panelTitolo.getWidth(), 35);
            titolo.setHorizontalAlignment(JLabel.CENTER);
            titolo.setVerticalAlignment(JLabel.CENTER);
            titolo.setText(bundle.getString("boundaryDomande_rispondi_domande_utente"));




            // Creazione etichette campi con relativi nomi

            rispondiLabel.setFont(new Font("Verdana", Font.BOLD, 15));
            rispondiLabel.setLocation(75,30);
            rispondiLabel.setSize(panelTitolo.getWidth()/2, 30);
            rispondiLabel.setText(bundle.getString("boundaryDomande_inserisci_risposta"));



            bRispondi = new JButton(bundle.getString("visualizzaPosta_rispondi"));
            bRispondi.setLocation(150,50);
            bRispondi.setSize(panelTitolo.getWidth()/4, 50);
            bRispondi.setFont(new Font("Arial", 0, 20));

            bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
            bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
            bIndietro.setSize(panelTitolo.getWidth()/4, 50);
            bIndietro.setFont(new Font("Arial", 0, 20));
            bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

            panelRisposta.setLayout(new BorderLayout());
            panelRisposta.setSize(BoundaryAvvio.getConfine().getWidth()*5/6, BoundaryAvvio.getConfine().getHeight()/6);
            panelRisposta.setLocation(80, 350);
            panelRisposta.setBackground(new Color(190, 190, 190));

            rislista.setSize(panelTitolo.getWidth()/2, 50);
            rislista.setEditable(true);
            rislista.setLineWrap(true);
            rislista.setWrapStyleWord(true);
            panelRisposta.add(listascroll,BorderLayout.CENTER);



            panelButtons.setLayout(null);
            panelButtons.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/5);
            panelButtons.setLocation(5, 420);
            panelButtons.add(bRispondi);
            panelButtons.add(bIndietro);



            //aggiunta pannelli a quello piuù esterno
            pannelloFaqRisposta.add(panelRisposta);
            pannelloFaqRisposta.add(panelButtons);
            pannelloFaqRisposta.add(panelBox);
            pannelloFaqRisposta.add(panelTitolo);
            pannelloFaqRisposta.add(panelEtichetta);




            // Ascoltatore di bottone e relativa azione
            ascoltatoreEtAzioneRispondi = new RispondiAA();
            ascoltatoreEtAzioneIndietro = new tornaindietroAA();



            // Associazione di bottone a relativo ascoltatore
            bRispondi.addActionListener(ascoltatoreEtAzioneRispondi);
            bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);

        }
        // Fine costruttore

        //metodo privato chiamato dalla BoundaryRispostaDomande stessa, a cui viene delegata la corretta costruzione della ComboBox, usando opportuni controlli

        private void costrusciComboBox(){
            ResourceBundle bundle = this.cl.getBundleFromProp();

            domande = crf.ritornaDomandeSenzaRisposta();
            if (domande==null || domande.length==0){
                pannelloFaqRisposta.setVisible(false);
                new BoundaryFallimento(bundle.getString("boundaryDomande_domande_nonpresenti"));
                return;
            }
            box = new JComboBox(domande);
            //aggiunge la box al pannello di competenza
            panelBox.add(box);

            //aggiunge l'ascoltatore relativo
            box.addActionListener(e -> {
                        int index;
                        index = box.getSelectedIndex();
                        domanda= domande[index];


                    }

            );

        }

        //Ascoltatore

        private class RispondiAA implements ActionListener
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    pannelloFaqRisposta.setVisible(false);
                    crf.rispondiComeSuperUtente(domanda, rislista.getText());

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }




        private class tornaindietroAA implements ActionListener
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    pannelloFaqRisposta.setVisible(false);
                    new BoundaryAmministrazione();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

}
