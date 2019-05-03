package features.properties.enums;

public enum  OperationSystems {
    WINDOWS("windows"),
    LINUX("linux");

    private String operationSystem;

    public String getOperationSystem() {
        return operationSystem;
    }

    OperationSystems(String operationSystem) {
        this.operationSystem = operationSystem;
    }
}
