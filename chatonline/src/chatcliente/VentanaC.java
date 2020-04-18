package chatcliente;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import javax.swing.JFileChooser; 
import javax.swing.GroupLayout;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class VentanaC extends JFrame {

   
 

	public VentanaC() throws NumberFormatException, UnknownHostException, IOException {
    	getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
    	getContentPane().setBackground(Color.GRAY);
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String ip_puerto_nombre[]=getIP_Puerto_Nombre();
        String ip=ip_puerto_nombre[0];
        String puerto=ip_puerto_nombre[1];
        String nombre=ip_puerto_nombre[2];
        cliente=new Cliente(this, ip, Integer.valueOf(puerto), nombre);
       
    }

    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        cmbContactos = new javax.swing.JComboBox();
        btnEnviar = new javax.swing.JButton();
        btnBasededatos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jLabel1.setText("Usuario a recibir:");
        
        JButton btnArchivo = new JButton("Archivo");
        btnArchivo.setActionCommand("Archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoactionPerformed(evt);
            }

        	
        	 
			private void btnArchivoactionPerformed(ActionEvent evt) {
				
		       
				
			}
			
        });

        JButton btnBasededatos = new JButton("Base de datos");
        txtHistorial = new javax.swing.JTextArea();
        
                txtHistorial.setEditable(false);
                txtHistorial.setColumns(20);
                txtHistorial.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(txtHistorial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(48))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(cmbContactos, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(txtMensaje, 223, 223, 223)
        						.addComponent(btnBasededatos, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(btnEnviar, Alignment.TRAILING)
        						.addComponent(btnArchivo, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(txtHistorial, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cmbContactos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel1))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtMensaje, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnEnviar))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnArchivo)
        				
        				.addComponent(btnBasededatos))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }
    
    
    
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        
        if(cmbContactos.getSelectedItem()==null){
            JOptionPane.showMessageDialog(this, "Debe escoger un destinatario válido, si no \n"
                                                 + "hay uno, espere a que otro usuario se conecte\n"
                                                 + "para poder chatear con él.");        
            return;
        }
        String cliente_receptor=cmbContactos.getSelectedItem().toString();
        String mensaje=txtMensaje.getText();
        cliente.enviarMensaje(cliente_receptor, mensaje);
        txtHistorial.append(""+cliente_receptor+ " : \n" + mensaje+"\n");
        txtMensaje.setText("");
    }
    
    
    	
    
	private void btnBasededatos(java.awt.event.ActionEvent evt) {
		
		String cliente_receptor=cmbContactos.getSelectedItem().toString();
        String mensaje=txtMensaje.getText();
		

		final String url = "jdbc:postgresql://localhost:5432/chatonline";
		final String user = "postgres";
		final String password = "superman96";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado a PostgreSQL exitosamente");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO chatonline VALUES('" + cliente_receptor +"','" + mensaje+"')");
			System.out.println("Se insertaron exitosamente los datos");
			conn.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("No se pudo conectar a PostgreSQL");
		}
        
       
	}
	
	
    private void formWindowClosed(java.awt.event.WindowEvent evt) {
    }
    
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        cliente.confirmarDesconexion();
    }
    
    
    
    public static void main(String args[]) {
    
     
    	
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new VentanaC().setVisible(true);
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
            }
        });
    }

    // declaración de variables
    
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnBasededatos;
    private javax.swing.JComboBox cmbContactos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextField txtMensaje;
   
    
    
    private final String DEFAULT_PORT="3000";
    
    
    private final String DEFAULT_IP="127.0.0.1";
    
    
    private final Cliente cliente;
    
    
    void addContacto(String contacto) {
        cmbContactos.addItem(contacto);
    }
    
    
    
    void addMensaje(String emisor, String mensaje) {
        txtHistorial.append(""+emisor + ": \n" + mensaje+"\n");
    }
   
    
    
    void sesionIniciada(String identificador) {
        this.setTitle("Chat Uru \n "+identificador+"");
    }
   
    
    
    private String[] getIP_Puerto_Nombre() {
    	
    	
        String s[]=new String[3];
        
        s[0]=DEFAULT_IP;
        s[1]=DEFAULT_PORT;
       
        
       
        JTextField ip = new JTextField(15);
        JTextField puerto = new JTextField(15);
        JTextField usuario = new JTextField(15);
        ip.setText(DEFAULT_IP);
        puerto.setText(DEFAULT_PORT);
        usuario.setText("Nombre");
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(3, 2));
        myPanel.add(new JLabel("IP del Server:"));
        myPanel.add(ip);
        myPanel.add(new JLabel("Puerto de la conexión:"));
        myPanel.add(puerto);
        myPanel.add(new JLabel("Nombre del usuario:"));
        myPanel.add(usuario);        
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Configuraciones para establecer contacto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
              
                
           
                s[0]=ip.getText();
                s[1]=puerto.getText();
                s[2]=usuario.getText();
        }else{
            System.exit(0);
        }
        return s;
    }    
    
    
    
    void eliminarContacto(String identificador) {
        for (int i = 0; i < cmbContactos.getItemCount(); i++) {
            if(cmbContactos.getItemAt(i).toString().equals(identificador)){
                cmbContactos.removeItemAt(i);
                return;
            }
        }
    }
}
