package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    private static String SELECT_ALL = "select * from ODONTOLOGOS";


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("Odontologo guardado exitosamente");
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer Id = null;
            if (resultSet.next()) {
                Id = resultSet.getInt(1);
                odontologoARetornar = new Odontologo(Id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }
            logger.info(odontologoARetornar);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
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
    public List<Odontologo> listarOdontologos() {
        Connection connection = null;
        Odontologo consultaOdontologo = null;
        List<Odontologo> odontologos = new ArrayList<Odontologo>();

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Integer Id = resultSet.getInt(1);
                String matriculaOdontologo = resultSet.getString(2);
                String nombreOdontologo = resultSet.getString(3);
                String apellidoOdontologo = resultSet.getString(4);
                consultaOdontologo = new Odontologo(Id, matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
                odontologos.add(consultaOdontologo);
                //logger.info(productos);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return odontologos;
    }
}
