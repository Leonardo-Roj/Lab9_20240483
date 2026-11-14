package daos;

import beans.Usuario;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao extends DaoBase{

    public boolean existeCorreo(String email)throws SQLException{
        String sql = "SELECT id_usuario FROM usuario WHERE email = ?";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,email);

            try (ResultSet rs = pstmt.executeQuery()){
                return rs.next();
            }
        }
    }

    //Nuevo user

    public boolean registrarUsuario(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (nombres, apellidos, email, password_hash, rol, estado)\n" +
                     "VALUES (?, ?, ?, ?, 'LECTOR', 'ACTIVO')\n";

                try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)){

                    pstmt.setString(1, usuario.getNombres());
                    pstmt.setString(2, usuario.getApellidos());
                    pstmt.setString(3, usuario.getEmail());
                    pstmt.setString(4, usuario.getPasswordHash());
                    return pstmt.executeUpdate() == 1;
                }
    }

    public Usuario validarUsuario(String email, String passwordHash)throws SQLException{
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario" + "WHERE email = ? AND password_hash = ?" +
                "AND estado = 'ACTIVO'";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, usuario.getNombres());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getPasswordHash());

            try (ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombres(rs.getString("nombres"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setEstado(rs.getString("estado"));
                }
            }
        }

        return usuario;
    }
}
