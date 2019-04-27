package features.properties.enums;

public enum EndpointPropNames {

    currentDateTime("currentDateTime", "http://worldclockapi.com/api/json/est/now");

    private String prop;
    private String endPoint;


    EndpointPropNames(String prop, String endPoint) {
        this.prop = prop;
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
    public String getValue() {
        return prop;
    }
}
