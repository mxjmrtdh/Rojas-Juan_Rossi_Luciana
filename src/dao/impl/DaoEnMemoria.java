package dao.impl;

import dao.IDao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DaoEnMemoria implements IDao<Odontologo> {
    public static final Logger logger = Logger.getLogger(DaoEnMemoria.class);
    private List<Odontologo> listaOdontologos = new ArrayList<>();

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(listaOdontologos.size()+1);
        listaOdontologos.add(odontologo);
        logger.info("Odontologo guardado en memoria: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return listaOdontologos;
    }
}
