package chatservidor;



import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class VentanaS extends JFrame {
    private final String DEFAULT_PORT="3000";
    private final Servidor servidor;
   
    
    public VentanaS() throws IOException {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String puerto=getPuerto();
        servidor=new Servidor(puerto,this);
    }

    
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtClientes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Servidor URU");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("-----Servidor establecido-----"));

        txtClientes.setEditable(false);
        txtClientes.setColumns(40);
        txtClientes.setRows(15);
        jScrollPane1.setViewportView(txtClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
    
    public static void main(String args[]) {
        

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new VentanaS().setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }

    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtClientes;
    
    
    
    void agregarLog(String texto) {
        txtClientes.append(texto);
    }
    
    
    
    private String getPuerto() {
        String p=DEFAULT_PORT;
        JTextField puerto = new JTextField(20);
        puerto.setText(p);
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(2, 1));
        myPanel.add(new JLabel("Puerto de la conexión a establecer:"));
        myPanel.add(puerto);
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Configuraciones del chat", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
                p=puerto.getText();
        }else{
            System.exit(0);
        }
        return p;
    }
   
    
    
    void addServidorIniciado() {
        txtClientes.setText("En espera de instrucciones:");
    }        
}
