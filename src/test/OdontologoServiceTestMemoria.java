package test;

import dao.impl.DaoEnMemoria;
import dao.impl.DatoH2Odontologo;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTestMemoria {
    static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new DaoEnMemoria());

    @Test
    @DisplayName("Testear listado de todos los odontologos en memoria")
    void ListadoDeTodosEnMemoria(){
        //Creamos una lista previamente
        List<Odontologo> ListaOdontologos=new ArrayList<>();
        //Agregamos registro a la lista
        Odontologo odontologo = new Odontologo(0, 123546, "moises", "rojas");
        ListaOdontologos.add(odontologo);
        //Agregamos registro a la lista
        odontologo = new Odontologo(0, 123547, "luciana", "rossi");
        ListaOdontologos.add(odontologo);
        //Agregamos registro a la lista
        odontologo = new Odontologo(0, 123548, "veronica", "valdez");
        ListaOdontologos.add(odontologo);

        //recorremos la lista y agregamos registros
        for (Odontologo odontologoRegistro:ListaOdontologos){
            odontologoService.guardarOdontologo(odontologoRegistro);
        }

        //dado
        List<Odontologo> listaOdontologos;
        //cuando
        listaOdontologos=odontologoService.listarTodos();
        //entonces
        assertFalse(listaOdontologos.isEmpty());
    }
}