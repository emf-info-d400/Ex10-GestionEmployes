package temperature.view;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import temperature.ctrl.Controller;

/**
 * Classe représentant la vue de l'application MVC "ConvertisseurDeTemperature" de l'exercice "Convertisseur de température MVC" du module D400.
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paul Friedli</a>
 * @since 18.04.2016
 * @version 1.0.0
 */
public class View extends javax.swing.JFrame {

     // Variables declaration - do not modify//GEN-BEGIN:variables
     private javax.swing.JButton jButtonConvertirCelsiusEnFahrenheits;
     private javax.swing.JButton jButtonConvertirCelsiusEnKelvins;
     private javax.swing.JButton jButtonQuitter;
     private javax.swing.JLabel jLabel10;
     private javax.swing.JLabel jLabel11;
     private javax.swing.JLabel jLabel12;
     private javax.swing.JLabel jLabel13;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabel9;
     private javax.swing.JLabel jLabelLogoLogus;
     private javax.swing.JLabel jLabelSwissSoftwareLogo;
     private javax.swing.JLabel jLabelTempConvertie;
     private javax.swing.JLabel jLabelTempSource;
     private javax.swing.JLabel jLabelUniteConvertie;
     private javax.swing.JLabel jLabelUniteSource;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel11;
     private javax.swing.JPanel jPanel12;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JPanel jPanel20;
     private javax.swing.JPanel jPanel29;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JPanel jPanel4;
     private javax.swing.JPanel jPanel5;
     private javax.swing.JPanel jPanel7;
     private javax.swing.JSlider jSliderTempSource;
     // End of variables declaration//GEN-END:variables
 
     /**
      * La référence au contrôleur de l'application.
      */
     private Controller refCtrl;
 
     private ChangeListener tempSliderChangeListener;

    /**
     * Constructeur de la classe View. Ce constructeur crée la vue, l'initialise correctement et mais ne l'affiche pas
     * encore.
     */
    public View() {
        // Initialiser TOUS nos attributs
        this.refCtrl = null;
        this.tempSliderChangeListener = null;
        
        // Dire à Java de faire ressembler cette application à celles de la plateforme sur laquelle il est en train de tourner.
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e ) {
        }

        // Initialiser les composants de notre ihm.
        initComponents();

        // Notre fenêtre ne peut pas être redimensionnée.
        setResizable( false );

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
        setTitle( "ConvertisseurDeTemperature v1.1 - written by Paul Friedli" );

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

        // Créer un listener pour les changements du slider
        tempSliderChangeListener = new ChangeListener() {
            @Override
            public void stateChanged( ChangeEvent e ) {
                // if ( !jSliderTempSource.getValueIsAdjusting() ) {
                int temperature = jSliderTempSource.getValue();
                getRefCtrl().temperatureAConvertirModifiee( temperature );
                //  }

            }
        };

        // Associer le listener à notre slider
        jSliderTempSource.addChangeListener( tempSliderChangeListener );
    }

    /**
     * Cette méthode est appelée par le contrôleur de l'application pour démarrer l'ihm. En l'occurrence ici on ne fait
     * que de rendre visible notre fenêtre car tout a déjà été préparé par le constructeur.
     */
    public void viewStart() {
        // Afficher l'ihm
        setVisible( true );
    }

    /**
     * Méthode appelée par le contrôleur de l'application pour afficher une nouvelle température à convertir. La
     * nouvelle température sera affichée et le slider sera repositionné correctement.
     *
     * @param temperature la nouvelle température à convertir
     */
    public void afficherTemperatureAConvertir( int temperature ) {
        // Changer la position du slider ( mais d'abord éviter qu'il ne génère des notifications de changement au Ctrl durant cette opération)
        jSliderTempSource.removeChangeListener( tempSliderChangeListener );
        jSliderTempSource.setValue( temperature );
        jSliderTempSource.addChangeListener( tempSliderChangeListener );

        // Changer la valeur affichée
        jLabelTempSource.setText( "" + temperature );
    }

    /**
     * Méthode appelée par le contrôleur de l'application pour afficher une température convertie.
     *
     * @param strTemperature le texte représentant la valeur de la température convertie
     * @param strUnite       le texte représentant l'unité de mesure dans laquelle la température a été convertie
     */
    public void afficherTemperatureConvertie( String strTemperature, String strUnite ) {
        jLabelTempConvertie.setText( strTemperature );
        jLabelUniteConvertie.setText( "°" + strUnite );
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

            // Arrêter ce qui doit être arrêté côté Ihm (timer, threads, ..)
            // rien à faire...
            // Fermer l'Ihm
            dispose();
        }
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
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelLogoLogus = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSliderTempSource = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabelUniteSource = new javax.swing.JLabel();
        jLabelTempSource = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jButtonConvertirCelsiusEnFahrenheits = new javax.swing.JButton();
        jButtonConvertirCelsiusEnKelvins = new javax.swing.JButton();
        jLabelUniteConvertie = new javax.swing.JLabel();
        jLabelTempConvertie = new javax.swing.JLabel();

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(jButtonQuitter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSwissSoftwareLogo)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/appIcon-128.png"))); // NOI18N
        jLabel2.setText("<html>Convertisseur&nbsp;&nbsp;&nbsp;<div align=\"right\"><font size=\"2\" color=\"gray\" face=\"Arial\">Écrit par Paul FRIEDLI, Copyrights © 2016&nbsp;&nbsp;</font></div></html>");

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Température source ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(0, 82, 147))); // NOI18N

        jSliderTempSource.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jSliderTempSource.setMajorTickSpacing(25);
        jSliderTempSource.setMaximum(150);
        jSliderTempSource.setMinimum(-250);
        jSliderTempSource.setMinorTickSpacing(5);
        jSliderTempSource.setPaintLabels(true);
        jSliderTempSource.setPaintTicks(true);
        jSliderTempSource.setSnapToTicks(true);

        jPanel2.setLayout(new java.awt.GridLayout(1, 5));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/cold-64.png"))); // NOI18N
        jPanel2.add(jLabel9);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/temp-low-64.png"))); // NOI18N
        jPanel2.add(jLabel11);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/temp-med-64.png"))); // NOI18N
        jPanel2.add(jLabel13);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/temp-high-64.png"))); // NOI18N
        jPanel2.add(jLabel12);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/hot-64.png"))); // NOI18N
        jPanel2.add(jLabel10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderTempSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderTempSource, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelUniteSource.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelUniteSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/temp-logo-32.png"))); // NOI18N
        jLabelUniteSource.setText("°C");

        jLabelTempSource.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabelTempSource.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTempSource.setText("120");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelTempSource, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUniteSource, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUniteSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTempSource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Température convertie ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 24), new java.awt.Color(0, 82, 147))); // NOI18N

        jPanel29.setLayout(new java.awt.GridLayout(2, 1));

        jButtonConvertirCelsiusEnFahrenheits.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonConvertirCelsiusEnFahrenheits.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/convert-48.png"))); // NOI18N
        jButtonConvertirCelsiusEnFahrenheits.setText("Convertir  °C en °F     ");
        jButtonConvertirCelsiusEnFahrenheits.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonConvertirCelsiusEnFahrenheits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertirCelsiusEnFahrenheitsActionPerformed(evt);
            }
        });
        jPanel29.add(jButtonConvertirCelsiusEnFahrenheits);

        jButtonConvertirCelsiusEnKelvins.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jButtonConvertirCelsiusEnKelvins.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/convert-48.png"))); // NOI18N
        jButtonConvertirCelsiusEnKelvins.setText("Convertir  °C en °K     ");
        jButtonConvertirCelsiusEnKelvins.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonConvertirCelsiusEnKelvins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertirCelsiusEnKelvinsActionPerformed(evt);
            }
        });
        jPanel29.add(jButtonConvertirCelsiusEnKelvins);

        jLabelUniteConvertie.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelUniteConvertie.setIcon(new javax.swing.ImageIcon(getClass().getResource("resources/temp-logo-32.png"))); // NOI18N
        jLabelUniteConvertie.setText("°C");

        jLabelTempConvertie.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabelTempConvertie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTempConvertie.setText("120");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTempConvertie, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUniteConvertie, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelUniteConvertie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTempConvertie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(18, 18, 18)
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

    private void jButtonConvertirCelsiusEnFahrenheitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertirCelsiusEnFahrenheitsActionPerformed
        getRefCtrl().convertirCelsiusEnFahrenheits();
    }//GEN-LAST:event_jButtonConvertirCelsiusEnFahrenheitsActionPerformed

    private void jButtonConvertirCelsiusEnKelvinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertirCelsiusEnKelvinsActionPerformed
        getRefCtrl().convertirCelsiusEnKelvins();
    }//GEN-LAST:event_jButtonConvertirCelsiusEnKelvinsActionPerformed

   
}
