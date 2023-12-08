package gestionemployes.view;

import gestionemployes.models.Employe;
import gestionemployes.ctrl.Controller;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Classe représentant la vue de l'application MVC "Gestion d'employés" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 01.12.2023
 * @version 1.0.0
 */
public class View extends javax.swing.JFrame {

       // Variables declaration - do not modify//GEN-BEGIN:variables
       private javax.swing.JButton jButtonAfficherEmployesDisponibles;
       private javax.swing.JButton jButtonAfficherEmployesEnVacances;
       private javax.swing.JButton jButtonAfficherTimbragesEmploye;
       private javax.swing.JButton jButtonAfficherTousLesEmployes;
       private javax.swing.JButton jButtonAuTravail;
       private javax.swing.JButton jButtonEnVacances;
       private javax.swing.JButton jButtonEngager;
       private javax.swing.JButton jButtonLicencier;
       private javax.swing.JButton jButtonQuitter;
       private javax.swing.JButton jButtonTimbrageArrivee;
       private javax.swing.JButton jButtonTimbrageDepart;
       private javax.swing.JLabel jLabel1;
       private javax.swing.JLabel jLabel2;
       private javax.swing.JLabel jLabel3;
       private javax.swing.JLabel jLabel4;
       private javax.swing.JLabel jLabel5;
       private javax.swing.JLabel jLabel6;
       private javax.swing.JLabel jLabelLogoLogus;
       private javax.swing.JLabel jLabelSwissSoftwareLogo;
       private javax.swing.JList<Employe> jListEmployes;
       private javax.swing.JPanel jPanel1;
       private javax.swing.JPanel jPanel2;
       private javax.swing.JPanel jPanel20;
       private javax.swing.JPanel jPanel3;
       private javax.swing.JPanel jPanel4;
       private javax.swing.JPanel jPanel5;
       private javax.swing.JPanel jPanel6;
       private javax.swing.JPanel jPanel7;
       private javax.swing.JScrollPane jScrollPane1;
       private javax.swing.JScrollPane jScrollPane2;
       private javax.swing.JSeparator jSeparator1;
       private javax.swing.JSeparator jSeparator2;
       private javax.swing.JSeparator jSeparator3;
       private javax.swing.JSeparator jSeparator5;
       private javax.swing.JTextArea jTextAreaRapport;
       private javax.swing.JTextField jTextFieldNom;
       private javax.swing.JTextField jTextFieldPrenom;
       // End of variables declaration//GEN-END:variables
   
       /**
        * La référence au contrôleur de l'application.
        */
       private Controller refCtrl;
    /**
     * Constructeur de la classe View. Ce constructeur crée la vue, l'initialise correctement et mais ne l'affiche pas
     * encore.
     */
    public View() {
        // Initialiser TOUS nos attributs
        this.refCtrl = null;

        // Dire à Java de faire ressembler cette application à celles de la plateforme sur laquelle il est en train de tourner.
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e ) {
        }

        // Initialiser les composants de notre vue.
        initComponents();

        // Notre  ne peut  être redimensionnée.
        setResizable( true );
        setMinimumSize( new Dimension( 1100, 780 ) );

        // Code spécial pour avoir un icône d'application sur le dock de Max OS/X
        try {
            // Définir l'icône de l'application.
            setIconImage( new ImageIcon( getClass().getResource( "resources/appIcon.png" ) ).getImage() );
            // <editor-fold defaultstate="collapsed" desc="Code pour avoir l'icône sur le Dock pour OS/X">                          
            // Et sur Mac, l'icône utilisée dans le Dock, mais de manière dynamique puisque cette classe n'est peut-être pas disponible (sur Windows)
            {
                try {
                    Class aClass = Class.forName( "com.apple.eawt.Application" );
                    Method getApplication = aClass.getMethod( "getApplication", ( Class[] ) null );
                    Object application = getApplication.invoke( null );
                    Method setDockIconImage = aClass.getMethod( "setDockIconImage", Image.class );
                    Image appIcon = new ImageIcon( getClass().getResource( "resources/appIcon.png" ) ).getImage();
                    setDockIconImage.invoke( application, appIcon );
                }
                catch ( Exception e ) {
                }
            }
// </editor-fold>               
        }
        catch ( Exception e ) {
        }

        // Définir le titre de l'application
        setTitle( "Gestion d'employés v1.0 - written by Paul Friedli" );

        // Ne pas fermer la fenêtre automatiquement, nous appellerons dispose() nous-même.
        setDefaultCloseOperation( javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE );

        // Centrer la fenêtre à l'écran
        setLocationRelativeTo( null );

        // S'assurer qu'on soit informé lorsque l'utilisateur voudra quitter cette fenêtre
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent we ) {
                // Signalons au contrôleur que l'utilisateur veut quitter.
                viewExiting();
            }
        } );

        // Créer un listener pour écouter les changements dans un champ d'édition
        DocumentListener listenerNomPrenom = new DocumentListener() {
            @Override
            public void insertUpdate( javax.swing.event.DocumentEvent e ) {
                textChanged();
            }

            @Override
            public void removeUpdate( javax.swing.event.DocumentEvent e ) {
                textChanged();
            }

            @Override
            public void changedUpdate( javax.swing.event.DocumentEvent e ) {
                textChanged();
            }

            public void textChanged() {
                jButtonEngager.setEnabled( !jTextFieldNom.getText().isEmpty() && !jTextFieldPrenom.getText().isEmpty() );
            }
        };

        // Etre informés lorsque le contenu de nos deux champs d'édition sont modifiés
        jTextFieldNom.getDocument().addDocumentListener( listenerNomPrenom );
        jTextFieldPrenom.getDocument().addDocumentListener( listenerNomPrenom );

        // Etre informés lorsque la sélection courante est modifiée dans notre liste d'employés
        jListEmployes.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent e ) {
                if ( !e.getValueIsAdjusting() ) {
                    Employe employeSelectionne = jListEmployes.getSelectedValue();
                    getRefCtrl().nouvelEmployeCourant( employeSelectionne );
                    activerBoutons();
                }
            }
        } );
    }

    /**
     * Cette méthode est appelée par le contrôleur de l'application pour démarrer la vue. En l'occurrence ici on ne fait
     * que de rendre visible notre fenêtre car tout a déjà été préparé par le constructeur.
     */
    public void viewStart() {
        // Afficher de la vue
        setVisible( true );
    }

    /**
     * Méthode appelée par le contrôleur afin d'afficher une liste d'employés.
     *
     * @param employes la liste d'employé à afficher
     */
    public void afficherEmployes( final Employe[] employes ) {
        jListEmployes.setModel( new AbstractListModel<Employe>() {
            @Override
            public int getSize() {
                return employes.length;
            }

            @Override
            public Employe getElementAt( int index ) {
                return employes[ index ];
            }
        } );
        activerBoutons();
    }

    /**
     * Méthode appelée par le contrôleur afin de débuter un nouveau rapport.
     *
     * @param titre le titre du nouveau rapport
     */
    public void debutNouveauRapport( String titre ) {
        jTextAreaRapport.setText( "" );
        insererTexteDansRapport( "----------------------------------------------" );
        insererTexteDansRapport( titre );
        insererTexteDansRapport( "----------------------------------------------" );
    }

    /**
     * Méthode appelée par le contrôleur afin d'ajouter une ligne au rapport.
     *
     * @param ligne la ligne à rajouter au rapport
     */
    public void nouvelleLigneDeRapport( String ligne ) {
        insererTexteDansRapport( ligne );
    }

    /**
     * Méthode appelée par le contrôleur afin de terminer un rapport.
     */
    public void finNouveauRapport() {
    }

    /**
     * Cette méthode permet d'afficher un message d'information à l'utilisateur.
     *
     * @param message le message d'information à afficher
     */
    public void messageInformation( String message ) {
        ImageIcon informationIcon = new ImageIcon( getClass().getResource( "resources/icon-fine-64.png" ) );
        JOptionPane.showMessageDialog( this, message, "Information", JOptionPane.INFORMATION_MESSAGE, informationIcon );
    }

    /**
     * Cette méthode permet d'afficher un message d'erreur à l'utilisateur.
     *
     * @param message le message d'erreur à afficher
     */
    public void messageErreur( String message ) {
        ImageIcon warningIcon = new ImageIcon( getClass().getResource( "resources/icon-error-64.png" ) );
        JOptionPane.showMessageDialog( this, message, "Information", JOptionPane.INFORMATION_MESSAGE, warningIcon );
    }

    /**
     * Cette méthode permet d'afficher une question de type oui/non à l'utilisateur.
     *
     * @param question la question à afficher dont la réponse doit être de type oui/non
     *
     * @return vrai pour oui
     */
    public boolean messageQuestion( String question ) {
        ImageIcon questionIcon = new ImageIcon( getClass().getResource( "resources/icon-question-64.png" ) );
        return ( JOptionPane.showConfirmDialog( getRootPane(), question, "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, questionIcon ) == JOptionPane.YES_OPTION );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Méthodes internes (privées)
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Cette méthode est appelée pour indiquer que l'application est en train de se fermer. Cela permet d'informer le
     * contrôleur de cet état de fait.
     */
    private void viewExiting() {
        ImageIcon questionIcon = new ImageIcon( getClass().getResource( "resources/icon-question-64.png" ) );
        if ( JOptionPane.showConfirmDialog( getRootPane(), "Voulez-vous vraiment quitter ?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, questionIcon ) == JOptionPane.YES_OPTION ) {
            // Informer le contrôleur qu'on veut quitter
            getRefCtrl().viewExiting();

            // Arrêter ce qui doit être arrêté côté vue (timer, threads, ..)
            // rien à faire...
            // Fermer la vue
            dispose();
        }
    }

    /**
     * Méthode interne qui active/désactive les boutons selon l'état actuel de la vue.
     */
    private void activerBoutons() {
        Employe employeSelectionne = jListEmployes.getSelectedValue();
        jButtonEnVacances.setEnabled( ( employeSelectionne != null ) && !employeSelectionne.isEnVacances() && !employeSelectionne.isEnTrainDeTravailler() );
        jButtonAuTravail.setEnabled( ( employeSelectionne != null ) && employeSelectionne.isEnVacances() );
        jButtonTimbrageArrivee.setEnabled( ( employeSelectionne != null ) && !employeSelectionne.isEnVacances() && !employeSelectionne.isEnTrainDeTravailler() );
        jButtonTimbrageDepart.setEnabled( ( employeSelectionne != null ) && !employeSelectionne.isEnVacances() && employeSelectionne.isEnTrainDeTravailler() );
        jButtonLicencier.setEnabled( ( employeSelectionne != null ) && !employeSelectionne.isEnVacances() );
        jButtonAfficherTimbragesEmploye.setEnabled( employeSelectionne != null );
    }

    /**
     * Méthode interne permettant d'insérer une ligne de texte à la fin de notre TextArea.
     *
     * @param texte la ligne de texte à insérer à la fin de notre TextArea
     */
    private void insererTexteDansRapport( String texte ) {
        jTextAreaRapport.append( texte + System.lineSeparator() );
    }

    /**
     * Getter de la référence au contrôleur de l'application.
     *
     * @return la référence au contrôleur de l'application
     */
    public Controller getRefCtrl() {
        return refCtrl;
    }

    /**
     * Setter de la référence au contrôleur de l'application.
     *
     * @param ctrl la nouvelle référence au contrôleur de l'application
     */
    public void setRefCtrl( Controller ctrl ) {
        this.refCtrl = ctrl;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Construction des composants Swing par Matisse.
    ///////////////////////////////////////////////////////////////////////////
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButtonQuitter = new javax.swing.JButton();
        jLabelSwissSoftwareLogo = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelLogoLogus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListEmployes = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPrenom = new javax.swing.JTextField();
        jButtonEngager = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonLicencier = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonEnVacances = new javax.swing.JButton();
        jButtonAuTravail = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonTimbrageArrivee = new javax.swing.JButton();
        jButtonTimbrageDepart = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButtonAfficherTousLesEmployes = new javax.swing.JButton();
        jButtonAfficherEmployesDisponibles = new javax.swing.JButton();
        jButtonAfficherTimbragesEmploye = new javax.swing.JButton();
        jButtonAfficherEmployesEnVacances = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaRapport = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButtonQuitter.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jButtonQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/door-32.png"))); // NOI18N
        jButtonQuitter.setText("Quitter");
        jButtonQuitter.setToolTipText("Pour quitter l'application");
        jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitterActionPerformed(evt);
            }
        });

        jLabelSwissSoftwareLogo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSwissSoftwareLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSwissSoftwareLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/swiss-made-software.png"))); // NOI18N
        jLabelSwissSoftwareLogo.setToolTipText("Battons-nous pour la qualité !");
        jLabelSwissSoftwareLogo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelSwissSoftwareLogo.setOpaque(true);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonQuitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelSwissSoftwareLogo)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonQuitter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSwissSoftwareLogo))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/appIcon-128.png"))); // NOI18N
        jLabel2.setText("<html>Gestion d'employés&nbsp;&nbsp;<div align=\"right\"><font size=\"2\" color=\"gray\" face=\"Arial\">Écrit par Paul FRIEDLI, Copyrights © 2016&nbsp;&nbsp;</font></div></html>");

        jLabelLogoLogus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLogoLogus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogoLogus.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/EMF_Info.jpg"))); // NOI18N
        jLabelLogoLogus.setToolTipText("Ca, c'est nous !");
        jLabelLogoLogus.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabelLogoLogus.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabelLogoLogus)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLogoLogus)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Les employés de l'entreprise ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/workers-24.png"))); // NOI18N
        jLabel1.setText("Les employés de");

        jListEmployes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListEmployes);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/appIcon-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("NOM");

        jTextFieldNom.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextFieldNom.setMinimumSize(new java.awt.Dimension(60, 20));

        jLabel4.setText("Prénom");

        jTextFieldPrenom.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jTextFieldPrenom.setMinimumSize(new java.awt.Dimension(60, 20));

        jButtonEngager.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/user-add-32.png"))); // NOI18N
        jButtonEngager.setText("Engagement d'un employé");
        jButtonEngager.setEnabled(false);
        jButtonEngager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEngagerActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/worker-32.png"))); // NOI18N

        jButtonLicencier.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/user-remove-32.png"))); // NOI18N
        jButtonLicencier.setText("Licenciement d'un employé");
        jButtonLicencier.setEnabled(false);
        jButtonLicencier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonLicencier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLicencierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonEngager, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLicencier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEngager)
                            .addComponent(jButtonLicencier))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonEnVacances.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/holiday-start-32.png"))); // NOI18N
        jButtonEnVacances.setText("Début vacances d'un employé");
        jButtonEnVacances.setEnabled(false);
        jButtonEnVacances.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonEnVacances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnVacancesActionPerformed(evt);
            }
        });

        jButtonAuTravail.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/holiday-end-32.png"))); // NOI18N
        jButtonAuTravail.setText("Fin vacances d'un employé");
        jButtonAuTravail.setEnabled(false);
        jButtonAuTravail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAuTravail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAuTravailActionPerformed(evt);
            }
        });

        jButtonTimbrageArrivee.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/time-start-32.png"))); // NOI18N
        jButtonTimbrageArrivee.setText("Timbrage d'arrivée d'un employé");
        jButtonTimbrageArrivee.setToolTipText("");
        jButtonTimbrageArrivee.setEnabled(false);
        jButtonTimbrageArrivee.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTimbrageArrivee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimbrageArriveeActionPerformed(evt);
            }
        });

        jButtonTimbrageDepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/time-stop-32.png"))); // NOI18N
        jButtonTimbrageDepart.setText("Timbrage de départ d'un employé");
        jButtonTimbrageDepart.setEnabled(false);
        jButtonTimbrageDepart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonTimbrageDepart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimbrageDepartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEnVacances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAuTravail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jButtonTimbrageArrivee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTimbrageDepart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonEnVacances)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAuTravail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTimbrageArrivee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTimbrageDepart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonAfficherTousLesEmployes.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/workers-detail-32.png"))); // NOI18N
        jButtonAfficherTousLesEmployes.setText("Afficher tous les employés et status");
        jButtonAfficherTousLesEmployes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAfficherTousLesEmployes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherTousLesEmployesActionPerformed(evt);
            }
        });

        jButtonAfficherEmployesDisponibles.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/workers-32.png"))); // NOI18N
        jButtonAfficherEmployesDisponibles.setText("Afficher tous les employés disponibles");
        jButtonAfficherEmployesDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAfficherEmployesDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherEmployesDisponiblesActionPerformed(evt);
            }
        });

        jButtonAfficherTimbragesEmploye.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/user-timings-32.png"))); // NOI18N
        jButtonAfficherTimbragesEmploye.setText("Afficher timbrages d'un employé");
        jButtonAfficherTimbragesEmploye.setEnabled(false);
        jButtonAfficherTimbragesEmploye.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAfficherTimbragesEmploye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherTimbragesEmployeActionPerformed(evt);
            }
        });

        jButtonAfficherEmployesEnVacances.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/holidays-32.png"))); // NOI18N
        jButtonAfficherEmployesEnVacances.setText("Afficher tous les employés en vacances");
        jButtonAfficherEmployesEnVacances.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAfficherEmployesEnVacances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAfficherEmployesEnVacancesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAfficherTousLesEmployes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAfficherEmployesDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAfficherTimbragesEmploye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAfficherEmployesEnVacances, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAfficherTimbragesEmploye)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAfficherTousLesEmployes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAfficherEmployesDisponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAfficherEmployesEnVacances)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Rapport d'activité ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18), new java.awt.Color(0, 82, 147))); // NOI18N

        jTextAreaRapport.setEditable(false);
        jTextAreaRapport.setBackground(new java.awt.Color(0, 0, 0));
        jTextAreaRapport.setColumns(20);
        jTextAreaRapport.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextAreaRapport.setForeground(new java.awt.Color(0, 255, 0));
        jTextAreaRapport.setRows(5);
        jTextAreaRapport.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane2.setViewportView(jTextAreaRapport);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitterActionPerformed
        viewExiting();
    }//GEN-LAST:event_jButtonQuitterActionPerformed

    private void jButtonEngagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEngagerActionPerformed
        getRefCtrl().employeEngager( jTextFieldNom.getText(), jTextFieldPrenom.getText() );
        jTextFieldNom.setText( "" );
        jTextFieldPrenom.setText( "" );
        activerBoutons();
    }//GEN-LAST:event_jButtonEngagerActionPerformed

    private void jButtonLicencierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLicencierActionPerformed
        getRefCtrl().employeLicencier();
        activerBoutons();
    }//GEN-LAST:event_jButtonLicencierActionPerformed

    private void jButtonEnVacancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnVacancesActionPerformed
        getRefCtrl().employeEnVacances();
        activerBoutons();
    }//GEN-LAST:event_jButtonEnVacancesActionPerformed

    private void jButtonAuTravailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAuTravailActionPerformed
        getRefCtrl().employeAuTravail();
        activerBoutons();
    }//GEN-LAST:event_jButtonAuTravailActionPerformed

    private void jButtonTimbrageArriveeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimbrageArriveeActionPerformed
        getRefCtrl().employeTimbrageArrivee();
        activerBoutons();
    }//GEN-LAST:event_jButtonTimbrageArriveeActionPerformed

    private void jButtonTimbrageDepartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimbrageDepartActionPerformed
        getRefCtrl().employeTimbrageDepart();
        activerBoutons();
    }//GEN-LAST:event_jButtonTimbrageDepartActionPerformed

    private void jButtonAfficherTimbragesEmployeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherTimbragesEmployeActionPerformed
        getRefCtrl().afficherTimbragesEmploye();
        activerBoutons();
    }//GEN-LAST:event_jButtonAfficherTimbragesEmployeActionPerformed

    private void jButtonAfficherTousLesEmployesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherTousLesEmployesActionPerformed
        getRefCtrl().afficherTousLesEmploye();
        activerBoutons();
    }//GEN-LAST:event_jButtonAfficherTousLesEmployesActionPerformed

    private void jButtonAfficherEmployesDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherEmployesDisponiblesActionPerformed
        getRefCtrl().afficherEmployesDisponibles();
        activerBoutons();
    }//GEN-LAST:event_jButtonAfficherEmployesDisponiblesActionPerformed

    private void jButtonAfficherEmployesEnVacancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAfficherEmployesEnVacancesActionPerformed
        getRefCtrl().afficherEmployesEnVacances();
        activerBoutons();
    }//GEN-LAST:event_jButtonAfficherEmployesEnVacancesActionPerformed

 
}
