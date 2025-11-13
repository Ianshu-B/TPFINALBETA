package INTERFACE;

import EXCEPTIONS.listaUsuariosVacioException;
import org.json.JSONArray;
import org.json.JSONException;

public interface IJson {
    public JSONArray backup() throws JSONException, listaUsuariosVacioException; //toJson basicamente
    //No meto fromJson aca porque hay clases hijas que implementan esta interfaz que no tendria sentido que devuelvan una collection. Porque es un solo objeto el que tiene que devolver.

}
