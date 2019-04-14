package features.env;


public enum EnvironmentType {

    TEST("qa"),
    SIT("sit"),
    PRODUCTION("prod");

    private String envType;

    EnvironmentType(String envType) {
        this.envType = envType;
    }

    public String getEnvType() {
        return this.envType;
    }


}
