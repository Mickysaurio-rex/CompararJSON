package compararJson;

public class PruebaCompareJson {


    public static void main(String[] args) {
        CompareJson compareJson = new CompareJson();
        String json1 = "{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":9,\n" +
                "\"date\":\"IGNORE\"\n" +
                "}";
        String json2 = "{\n" +
                "\"Content\":\"ExampleFile\",\n" +
                "\"Icon\":99,\n" +
                "\"date\":1651564855\n" +
                "}";
        compareJson.compareJSON(json1, json2);
    }
}
