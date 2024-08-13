package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.h2.command.dml.Insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DatoH2Odontologo implements IDao<Odontologo> {
    public static final Logger logger = Logger.getLogger(DatoH2Odontologo.class);
    public static final String InsertarRegistro = "INSERT INTO Odontologos VALUES(DEFAULT,?,?,?)";
    public static final String ListarTodos = "SELECT * FROM Odontologos";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection=null;
        Odontologo odontologoARetornar=null;
        try{
            //Creamos la conexión
            connection= H2Connection.getConnection();
            //ponemos el AutoCommit en false
            connection.setAutoCommit(false);
            //Agregar registro por parametros
            PreparedStatement preparedStatement=connection.prepareStatement(InsertarRegistro, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getNumeroMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.executeUpdate();
            //realizamos el commit
            connection.commit();
            //recuperamos el id y creamos el nuevo objeto con datos de la BD
            ResultSet resultSet=preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer id=resultSet.getInt(1);
                odontologoARetornar = new Odontologo(id,odontologo.getNumeroMatricula(),odontologo.getNombre(),odontologo.getApellido());
            }
        }catch (Exception e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                } finally {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologoDesdeBD = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ListarTodos);
            while (resultSet.next()){
                Integer idDB = resultSet.getInt(1);
                Integer numeroMatricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);
                odontologoDesdeBD = new Odontologo(idDB, numeroMatricula, nombre, apellido);
                logger.info(odontologoDesdeBD);
                listaOdontologos.add(odontologoDesdeBD);
            }
            logger.info(listaOdontologos);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return listaOdontologos;
    }
}
