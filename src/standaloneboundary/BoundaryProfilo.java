package standaloneboundary;

import constants.Constants;
import entity.Utente;
import standalonecontrol.ControlloreLinguaAmministratore;
import standalonecontrol.ControlloreProfiloAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;


public class BoundaryProfilo {

    //Pannelli
    private JPanel pannelloWrapper;

    private JPanel panelTitolo;
    private JPanel panelUsername;
    private JPanel panelOldPassword;
    private JPanel panelNewPassword;
    private JPanel panelEmail;
    private JPanel panelButtonSelect;
    private JPanel panelBox;
    private JPanel panelResultOp;

    private JComboBox<String> box;

    //Label
    private JLabel titolo;

    //Bottoni
    private JButton bSalva;
    private JButton bIndietro;

    // Campi e loro etichette
    private JLabel usernameLabel;
    private JTextField usernameF;
    private JLabel oldPasswordLabel;
    private JPasswordField oldPasswordF;
    private JLabel newPasswordLabel;
    private JPasswordField newPasswordF;
    private JLabel emailLabel;
    private JTextField emailF;
    private JLabel boxLabel;
    private JLabel resultLabel;


    // Ascoltatore di bottone e relativa azioni
    private Salva ascoltatoreBSalva;
    private TornaIndietroAA ascoltatoreBIndietro;
    private BoxSelectItem ascoltatoreBox;

    private ControlloreProfiloAmministratore cp;
    private ControlloreLinguaAmministratore cl;
    private Utente utente;

    public BoundaryProfilo() {
        this.cp = new ControlloreProfiloAmministratore();
        this.cl = new ControlloreLinguaAmministratore();

        String user = System.getProperty(Constants.USER_KEY, "");

        this.utente = this.cp.ottieniUtente(user);

        String email = this.utente != null ?
                utente.getEmail() :
                "";
        ResourceBundle bundle = this.cl.getBundleFromProp();


        // Pannello wrapper
        this.pannelloWrapper = new JPanel();

        // Pannelli contenuti nel wrapper
        this.panelTitolo = new JPanel();
        this.panelUsername = new JPanel();
        this.panelNewPassword = new JPanel();
        this.panelOldPassword = new JPanel();
        this.panelEmail = new JPanel();
        this.panelButtonSelect = new JPanel();
        this.panelBox = new JPanel();
        this.panelResultOp = new JPanel();

        // Bottoni di interazione
        this.bSalva = new JButton(bundle.getString("modificaProfiloUtente_salva"));
        this.bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));

        // Labels
        this.titolo = new JLabel();
        this.usernameLabel = new JLabel();
        this.usernameF = new JTextField(user, 60);
        this.oldPasswordLabel = new JLabel();
        this.oldPasswordF = new JPasswordField();
        this.newPasswordLabel = new JLabel();
        this.newPasswordF = new JPasswordField();
        this.emailLabel = new JLabel();
        this.emailF = new JTextField(email);
        this.resultLabel = new JLabel();

        // Box scelta lingua sistema
        this.box = new JComboBox<>(Constants.LANGS);
        this.boxLabel = new JLabel();



        // Inizializzazione
        // Composizione Wrapper
        int border = 5;
        String font = "Verdana";
        this.pannelloWrapper.setSize(BoundaryAvvio.getConfine()
                .getWidth(), BoundaryAvvio.getConfine().getHeight());
        BoundaryAvvio.getConfine().add(pannelloWrapper);
        this.pannelloWrapper.setLayout(null);

        this.panelTitolo.setLayout(null);
        this.panelTitolo.setSize(BoundaryAvvio.getConfine()
                .getWidth(), 45);
        this.panelTitolo.setLocation(border, border);
        this.panelTitolo.add(this.titolo);

        // Composizione titolo
        this.titolo.setFont(new Font(font, Font.BOLD, 20));
        this.titolo.setLocation(border, border);
        this.titolo.setSize(panelTitolo.getWidth(), 35);
        this.titolo.setHorizontalAlignment(JLabel.CENTER);
        this.titolo.setVerticalAlignment(JLabel.CENTER);
        this.titolo.setText(bundle.getString("boundaryProfilo_modifica_dati"));

        // Composizione pannello username
        this.panelUsername.setLayout(null);
        this.panelUsername.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
        this.panelUsername.setLocation(5, 60);
        this.panelUsername.setBackground(new Color(190, 190, 190));
        this.panelUsername.add(usernameLabel);
        this.panelUsername.add(usernameF);

        this.usernameLabel.setFont(new Font(font, Font.BOLD, 15));
        this.usernameLabel.setLocation(130, 20);
        this.usernameLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.usernameLabel.setText(bundle.getString("index_nomeUtente"));

        this.usernameF.setEditable(false);
        this.usernameF.setFont(new Font(font, 0, 15));
        this.usernameF.setLocation(300,15);
        this.usernameF.setSize(panelTitolo.getWidth()/2, 30);

        // Composizione pannello vecchia password
        this.panelOldPassword.setLayout(null);
        this.panelOldPassword.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
        this.panelOldPassword.setLocation(5, 125);
        this.panelOldPassword.setBackground(new Color(190, 190, 190));
        this.panelOldPassword.add(oldPasswordLabel);
        this.panelOldPassword.add(oldPasswordF);

        this.oldPasswordLabel.setFont(new Font(font, Font.BOLD, 15));
        this.oldPasswordLabel.setLocation(130, 20);
        this.oldPasswordLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.oldPasswordLabel.setText(bundle.getString("modificaProfiloUtente_vecchiaPsw"));

        this.oldPasswordF.setFont(new Font(font, 0, 15));
        this.oldPasswordF.setLocation(300,15);
        this.oldPasswordF.setSize(panelTitolo.getWidth()/2, 30);

        // Composizione pannello nuova password
        this.panelNewPassword.setLayout(null);
        this.panelNewPassword.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
        this.panelNewPassword.setLocation(5, 190);
        this.panelNewPassword.setBackground(new Color(190, 190, 190));
        this.panelNewPassword.add(newPasswordLabel);
        this.panelNewPassword.add(newPasswordF);

        this.newPasswordLabel.setFont(new Font(font, Font.BOLD, 15));
        this.newPasswordLabel.setLocation(130, 20);
        this.newPasswordLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.newPasswordLabel.setText(bundle.getString("modificaProfiloUtente_nuovaPsw"));

        this.newPasswordF.setFont(new Font(font, 0, 15));
        this.newPasswordF.setLocation(300,15);
        this.newPasswordF.setSize(panelTitolo.getWidth()/2, 30);

        // Composizione pannello email
        this.panelEmail.setLayout(null);
        this.panelEmail.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
        this.panelEmail.setLocation(5, 255);
        this.panelEmail.setBackground(new Color(190, 190, 190));
        this.panelEmail.add(emailLabel);
        this.panelEmail.add(emailF);

        this.emailLabel.setFont(new Font(font, Font.BOLD, 15));
        this.emailLabel.setLocation(130, 20);
        this.emailLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.emailLabel.setText(bundle.getString("index_email"));

        this.emailF.setFont(new Font(font, 0, 15));
        this.emailF.setLocation(300,15);
        this.emailF.setSize(panelTitolo.getWidth()/2, 30);

        // Composizione pannello risultato operazione
        this.panelResultOp.setLayout(null);
        this.panelResultOp.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/10);
        this.panelResultOp.setLocation(5, 400);
        this.panelResultOp.add(resultLabel);

        this.resultLabel.setFont(new Font(font, Font.BOLD, 20));
        this.resultLabel.setLocation(300, 20);
        this.resultLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.resultLabel.setVisible(false);

        // Composizione box selettore lingua
        this.panelBox.setLayout(new FlowLayout());
        this.panelBox.setSize(BoundaryAvvio.getConfine().getWidth(), BoundaryAvvio.getConfine().getHeight()/12);
        this.panelBox.setLocation(5, 350);
        this.panelBox.add(boxLabel);
        this.panelBox.add(box);

        this.boxLabel.setFont(new Font(font, Font.BOLD, 15));
        this.boxLabel.setLocation(450, 20);
        this.boxLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.boxLabel.setText(bundle.getString("modificaProfiloUtente_linguaCorrente"));

        // Composizione button
        this.panelButtonSelect.setLayout(null);
        this.panelButtonSelect.setSize(BoundaryAvvio.getConfine().getWidth(),
                BoundaryAvvio.getConfine().getHeight()/5);
        this.panelButtonSelect.setLocation(5, 430);
        this.panelButtonSelect.add(bSalva);
        this.panelButtonSelect.add(bIndietro);

        this.bSalva.setLocation(150,50);
        this.bSalva.setSize(panelTitolo.getWidth()/3, 50);
        this.bSalva.setFont(new Font("Arial", 0, 20));

        this.bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
        this.bIndietro.setSize(panelTitolo.getWidth()/4, 50);
        this.bIndietro.setFont(new Font("Arial", 0, 20));
        this.bIndietro.setToolTipText(bundle.getString("boundaryProfilo_indietro"));

        this.pannelloWrapper.add(panelTitolo);
        this.pannelloWrapper.add(panelUsername);
        this.pannelloWrapper.add(panelOldPassword);
        this.pannelloWrapper.add(panelNewPassword);
        this.pannelloWrapper.add(panelEmail);
        this.pannelloWrapper.add(panelBox);
        this.pannelloWrapper.add(panelResultOp);
        this.pannelloWrapper.add(panelButtonSelect);

        //Listeners per interazione utente con button
        this.ascoltatoreBSalva = new Salva(this.getClass());
        this.ascoltatoreBIndietro = new TornaIndietroAA();
        this.ascoltatoreBox = new BoxSelectItem(this.getClass());
        this.bSalva.addActionListener(ascoltatoreBSalva);
        this.bIndietro.addActionListener(ascoltatoreBIndietro);
        this.box.addActionListener(ascoltatoreBox);
    }

    public BoundaryProfilo(int result) {
        this();

        ResourceBundle bundle = this.cl.getBundleFromProp();

        switch (result) {
            case -1:
                resultLabel.setText(bundle.getString("boundaryProfilo_errore_ser_deser"));
                resultLabel.setForeground(Color.RED);
                break;

            case 0:
                resultLabel.setText(bundle.getString("modificaProfiloUtente_modificaCorretta"));
                resultLabel.setForeground(Color.GREEN);
                break;

            case 1:
                resultLabel.setText(bundle.getString("modificaProfiloUtente_vecchiaPswErrore"));
                resultLabel.setForeground(Color.RED);
                break;

            case 3:
                resultLabel.setText(bundle.getString("modificaProfiloUtente_nuovaPswMancante"));
                resultLabel.setForeground(Color.RED);
                break;

            default:
                resultLabel.setText(bundle.getString("boundaryProfilo_errore"));
                resultLabel.setForeground(Color.RED);
                break;
        }

        resultLabel.setVisible(true);
    }



    public class BoxSelectItem implements ActionListener {
        // Reflection
        private Class<?> aClass;

        BoxSelectItem(Class<?> aClass) {
            this.aClass = aClass;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource();

            if (combo.getSelectedIndex() != -1 &&
                    utente != null) {
                String selectedLang = combo
                        .getSelectedItem()
                        .toString();

                System.setProperty(Constants.LINGUA_KEY, selectedLang);

                pannelloWrapper.setVisible(false);

                try {
                    this.aClass
                            .newInstance();
                } catch (IllegalAccessException |
                        InstantiationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class Salva implements ActionListener {
        // Reflection
        private Class<?> aClass;

        Salva(Class<?> aClass) {
            this.aClass = aClass;
        }

        public void actionPerformed(ActionEvent arg0) {
            pannelloWrapper.setVisible(false);
            int response = cp.modificaProfilo(usernameF.getText(),
                    emailF.getText(),
                    new String(oldPasswordF.getPassword()),
                    new String(newPasswordF.getPassword()),
                    new String(newPasswordF.getPassword()),
                    new Locale(System.getProperty(
                            Constants.LINGUA_KEY, ""),
                            "")
            );

            try {
                this.aClass.getDeclaredConstructor(int.class).newInstance(response);
                        //.getConstructor(int.class)
                        //.newInstance(response)
            } catch (IllegalAccessException |
                    InstantiationException |
                    NoSuchMethodException |
                    InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class TornaIndietroAA
            implements ActionListener {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                System.setProperty(Constants.LINGUA_KEY,
                        utente.getLingua().getLanguage());

                pannelloWrapper.setVisible(false);
                new BoundaryAmministrazione();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}