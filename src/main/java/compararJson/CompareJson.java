package compararJson;

import org.json.JSONException;
import org.json.JSONObject;

public class CompareJson {
    public static void compareJSON(String json1, String json2) {
        try {
            JSONObject jsonObj1 = new JSONObject(json1);
            JSONObject jsonObj2 = new JSONObject(json2);

            if (jsonObj1.similar(jsonObj2)) {
                System.out.println("Todo está correcto");
            } else {
                System.out.println("Los archivos JSON no son iguales. Se encontraron las siguientes diferencias:");

                compareJSONObjects(jsonObj1, jsonObj2, "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void compareJSONObjects(JSONObject jsonObj1, JSONObject jsonObj2, String parentKey) throws JSONException {
        for (String key : jsonObj1.keySet()) {
            if (!jsonObj2.has(key)) {
                System.out.println("La clave '" + parentKey + key + "' no se encontró en el segundo archivo.");
                continue;
            }

            Object value1 = jsonObj1.get(key);
            Object value2 = jsonObj2.get(key);

            if (value1 instanceof JSONObject && value2 instanceof JSONObject) {
                compareJSONObjects((JSONObject) value1, (JSONObject) value2, parentKey + key + ".");
            } else if (!value1.equals(value2)) {
                System.out.println("La clave-valor '" + parentKey + key + "' no coincide: " + value1 + " != " + value2);
            }
        }

        for (String key : jsonObj2.keySet()) {
            if (!jsonObj1.has(key)) {
                System.out.println("La clave '" + parentKey + key + "' no se encontró en el primer archivo.");
            }
        }
    }
}
