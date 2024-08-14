package test;

import dao.impl.OdontologoDaoH2;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void crearTabla() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        }catch (Exception e) {
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testeamos el insert a la Base de Datos")
    void testInsert() {
        //dado
        Odontologo odontologo = new Odontologo("b1", "Alejandra", "Osorio");
        //cuando
        Odontologo odontologoDB = odontologoService.guardarOdontologo(odontologo);
        //Entonces
        assertNotNull(odontologoDB.getMatricula());

    }

    @Test
    @DisplayName("Testear la consulta de todos los odontologos en BD")
    void caso2(){
        //dado
        List<Odontologo> listaOdontologos = new ArrayList<>();
        // cuando
        listaOdontologos = odontologoService.listarOdontologo();
        logger.info(listaOdontologos);
        // entonces
        assertNotNull(listaOdontologos);
    }
}