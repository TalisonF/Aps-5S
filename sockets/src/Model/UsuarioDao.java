package Model;

import Model.Usuario;
import connectionfactory.Connectionfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class UsuarioDao extends Usuario {
    public  void salvar(){
    	Connection con = Connectionfactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
        	stmt =con.prepareStatement("insert into usuario (nome, login , senha) values (?,?,?)");
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getLogin());
            stmt.setString(3, this.getSenha());
        
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null , "Salvo com sucesso");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null , "Erro de SQL"+ex);
        }finally{
            Connectionfactory.closeConnection(con, stmt);
        }
    }
    
    public boolean validar(){
        Connection con = Connectionfactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean chek = false;
        try {
            stmt=con.prepareStatement("select * from usuario  where login=? and senha =?");
            stmt.setString(1, this.getLogin());
            stmt.setString(2, this.getSenha());
            rs=stmt.executeQuery();
            if(rs.next()){
                chek=true;
           }
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        	Connectionfactory.closeConnection(con,stmt,rs);
        }
        return chek;
    }
    
    
}