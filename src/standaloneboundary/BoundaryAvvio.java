package standaloneboundary;

import javax.swing.*;

import standalonecontrol.ControlloreLinguaAmministratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryAvvio extends StackFrame
{

    private static final long	serialVersionUID	= 1L;

    //Pannelli
    private JPanel pannello;
    private JPanel panelTitolo;
    private JPanel panelButtons;

    //Label
    private JLabel titolo;

    //Bottoni
    private JButton bEntra;

    //Ascoltatori di bottoni e relative azioni
    private EntraAA	ascoltatoreEtAzioneEntra;

    //JFrame
    private static JFrame confine;

    // Box lingua
    private ControlloreLinguaAmministratore cl;


    public BoundaryAvvio()
    {
        this.pannello = new JPanel();
        this.panelTitolo = new JPanel();
        this.panelButtons = new JPanel();
        this.titolo = new JLabel();

        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

        this.bEntra = new JButton(bundle.getString("boundaryAvvio_entra"));

        setConfine(this);
        this.setTitle(bundle.getString("boundaryAvvio_paginaAmm"));

        getConfine().setLayout(null);
        final int BASECONFINE = 900;
        final int ALTEZZACONFINE = 600;
        setSize(BASECONFINE, ALTEZZACONFINE);
        Dimension dim = getToolkit().getScreenSize();
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pannello.setSize(getConfine().getWidth(), getConfine().getHeight());
        this.add(pannello);
        pannello.setLayout(null);

        final int BORDO = 5;
        final int ALTEZZATITOLO = 30;

        panelTitolo.setLayout(null);
        panelTitolo.setSize(this.getWidth(), ALTEZZATITOLO*3);
        panelTitolo.setLocation(BORDO, BORDO);
        panelTitolo.add(titolo);

        titolo.setFont(new Font("Verdana", 0, 20));
        titolo.setLocation(BORDO, BORDO);
        titolo.setSize(panelTitolo.getWidth(), 30);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText(bundle.getString("boundaryAvvio_pannello"));

        panelButtons.setLayout(null);
        panelButtons.setSize(getWidth(), 150);
        panelButtons.setLocation(BORDO, ALTEZZATITOLO);

        panelButtons.add(bEntra);

        bEntra.setToolTipText(bundle.getString("boundaryAvvio_pannello"));
        bEntra.setBounds((getWidth()/2)-125, 100, 250, 50);

        pannello.add(panelTitolo);
        pannello.add(panelButtons);


        // Definisci ascoltatori di bottoni e relative azioni
        ascoltatoreEtAzioneEntra	= new EntraAA();


        // Associa ascoltatori e bottoni
        bEntra.addActionListener(ascoltatoreEtAzioneEntra);

        //Rendi visibile questo frame
        this.setVisible(true);

    }

    // Fine costruttore


    // Ascoltatore

    public static JFrame getConfine() {
		return confine;
	}

	public static void setConfine(JFrame confine) {
		BoundaryAvvio.confine = confine;
	}

	private class EntraAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannello.setVisible(false);
                new BoundaryLogin();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}