
package Cliente.model;

import Servidor.connectionfactory.Connectionfactory;
import Cliente.model.Usuario;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class UsuarioDao  {
    public  void create (Usuario user){
            Connection con = Connectionfactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt =con.prepareStatement("INSERT INTO usuario (login , senha) VALUES (?,? )");
            
                stmt.setString(1, user.getNome());
                stmt.setString(2, user.getSenha());
        
                    stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null , "Salvo com sucesso");
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null , "Erro de SQL"+ex);
        }finally{
            Connectionfactory.closeConnection(con, stmt);
        }
        
    }
    
    
        
    public boolean chekLogin (String login , String senha){
        Connection con = Connectionfactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean chek = false;
        try {
            stmt=con.prepareStatement("SELECT * FROM usuario  WHERE login=? AND senha =?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            rs=stmt.executeQuery();
            
            
            if(rs.next()){
                
                chek=true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
         Connectionfactory.closeConnection(con,stmt,rs);
        }
    
        return chek;
    }
    
    
}
