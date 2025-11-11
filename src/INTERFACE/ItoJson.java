package INTERFACE;

import EXCEPTIONS.listaUsuariosVacioException;
import org.json.JSONArray;
import org.json.JSONException;

public interface ItoJson  {
    public JSONArray backup() throws JSONException, listaUsuariosVacioException;
}
