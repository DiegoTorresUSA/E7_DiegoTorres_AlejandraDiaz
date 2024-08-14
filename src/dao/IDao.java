package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao <T>{
    T guardar(T t);

    List<T> listarOdontologos();

}
