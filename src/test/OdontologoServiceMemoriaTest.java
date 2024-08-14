package test;
import dao.impl.OdontologoDaoMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceMemoriaTest {
    static Logger logger = Logger.getLogger(OdontologoServiceMemoriaTest.class);
    //private List<Odontologo> listaOdontologosMemoria = new ArrayList<>();
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoMemoria());

    @Test
    @DisplayName("Testear que un odontologo se guarde en la base de datos")
    void caso1(){
        //dado
        Odontologo  odontologo = new Odontologo(1,"122552", "juana", "cardona");
        //cuando
        Odontologo odontologoDesdeMemoria = odontologoService.guardarOdontologo(odontologo);
        // entonces
        assertNotNull(odontologoDesdeMemoria.getId());
    }

//    @Test
//    public void testListarOdontologosEnMemoria() {
//        List<Odontologo> odontologos = odontologoServiceEnMemoria.listarOdontologos();
//        assertEquals(2, odontologos.size());
//    }

}
