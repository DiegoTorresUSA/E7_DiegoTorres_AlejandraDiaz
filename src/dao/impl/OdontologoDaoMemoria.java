package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria  implements IDao <Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(odontologos.size()+1);
        odontologos.add(odontologo);
        logger.info("odontologo guardado "+ odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        if (odontologos.isEmpty()) {
            logger.info("La lista de odontólogos está vacía");
        } else {
            logger.info("Se encontraron " + odontologos.size() + " odontólogos");
        }
        return new ArrayList<>(odontologos);
    }
}

