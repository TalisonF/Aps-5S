package Cliente.view;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Cliente.org.netbeans.lib.awtextra.*;
import Cliente.model.Usuario;
import Cliente.model.UsuarioDao;


public class TelaLogin extends javax.swing.JFrame {

   
    public TelaLogin() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatStatic = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        botaoCadastrar = new javax.swing.JButton();
        botaoLogin = new javax.swing.JButton();
        campoSenha = new javax.swing.JPasswordField();
        loginTexto = new javax.swing.JLabel();
        chatIcone = new javax.swing.JLabel();
        senhaTexto = new javax.swing.JLabel();
        painel = new javax.swing.JPanel();
        mensagemAviso = new javax.swing.JLabel();
        planoFundo = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        botaoOpcoes = new javax.swing.JMenu();
        botaoIp = new javax.swing.JMenuItem();
        botaoSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new AbsoluteLayout());

        chatStatic.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        chatStatic.setText("Chat Catastrofico");
        getContentPane().add(chatStatic, new AbsoluteConstraints(180, 60, -1, -1));
        getContentPane().add(campoLogin, new AbsoluteConstraints(110, 200, 270, -1));

        botaoCadastrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\progamacao\\botao_cadastrar.png")); // NOI18N
        botaoCadastrar.setBorderPainted(false);
        botaoCadastrar.setContentAreaFilled(false);
        botaoCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoCadastrar, new AbsoluteConstraints(260, 370, -1, -1));

        botaoLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\progamacao\\botao-login1.png")); // NOI18N
        botaoLogin.setBorderPainted(false);
        botaoLogin.setContentAreaFilled(false);
        botaoLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });
        getContentPane().add(botaoLogin, new AbsoluteConstraints(60, 370, -1, -1));
        getContentPane().add(campoSenha, new AbsoluteConstraints(110, 250, 270, -1));

        loginTexto.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        loginTexto.setText("Login :");
        getContentPane().add(loginTexto, new AbsoluteConstraints(50, 200, -1, -1));

        chatIcone.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\progamacao\\icone-chat.png")); // NOI18N
        getContentPane().add(chatIcone, new AbsoluteConstraints(0, -20, -1, -1));

        senhaTexto.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        senhaTexto.setText("Senha :");
        getContentPane().add(senhaTexto, new  AbsoluteConstraints(50, 250, -1, -1));

        painel.setBackground(new java.awt.Color(0, 0, 0));
        painel.setBorder(javax.swing.BorderFactory.createTitledBorder("Autenticação"));
        painel.setOpaque(false);
        painel.setLayout(null);

        mensagemAviso.setForeground(new java.awt.Color(204, 0, 51));
        painel.add(mensagemAviso);
        mensagemAviso.setBounds(110, 150, 210, 20);

        getContentPane().add(painel, new AbsoluteConstraints(10, 160, 430, 180));

        planoFundo.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\Desktop\\progamacao\\fundo-login.jpg")); // NOI18N
        getContentPane().add(planoFundo, new AbsoluteConstraints(0, 0, 450, 470));

        barraMenu.setBackground(new java.awt.Color(255, 255, 255));
        barraMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        barraMenu.setOpaque(false);

        botaoOpcoes.setText("Opções");

        botaoIp.setText("Adicionar ip");
        botaoIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIpActionPerformed(evt);
            }
        });
        botaoOpcoes.add(botaoIp);

        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        botaoOpcoes.add(botaoSair);

        barraMenu.add(botaoOpcoes);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        if (evt.getSource()==botaoSair) {
            System.exit(0);
            
        }
    }//GEN-LAST:event_botaoSairActionPerformed

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
         UsuarioDao userd= new UsuarioDao();
         
         if(userd.chekLogin(campoLogin.getText(), campoSenha.getText())){
             
            JOptionPane.showMessageDialog(null, "você logou");
             
         }else {
                mensagemAviso.setText("Login e/ou Senha digitado incorreto");
         }
    }//GEN-LAST:event_botaoLoginActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        UsuarioDao userd = new UsuarioDao();
        Usuario user = new Usuario ();
        
        user.setNome(campoLogin.getText());
        user.setSenha(campoSenha.getText());
        userd.create(user);
        
        JOptionPane.showMessageDialog(null,"Usuario cadastrado com sucesso ");
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIpActionPerformed
        JOptionPane.showInputDialog(null, "Digite Seu IP", "Ip" ,1);
        
    }//GEN-LAST:event_botaoIpActionPerformed

   
    public static void main(String args[]) {
        

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JMenuItem botaoIp;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JMenu botaoOpcoes;
    private javax.swing.JMenuItem botaoSair;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel chatIcone;
    private javax.swing.JLabel chatStatic;
    private javax.swing.JLabel loginTexto;
    private javax.swing.JLabel mensagemAviso;
    private javax.swing.JPanel painel;
    private javax.swing.JLabel planoFundo;
    private javax.swing.JLabel senhaTexto;
    // End of variables declaration//GEN-END:variables
}
