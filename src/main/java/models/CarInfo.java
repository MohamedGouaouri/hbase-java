package models;

public class CarInfo {
    public String matricule;
    public EngineInfo engineInfo;
    public GeneralInfo generalInfo;

    public CarInfo(String matricule, EngineInfo engineInfo, GeneralInfo generalInfo) {
        this.matricule = matricule;
        this.engineInfo = engineInfo;
        this.generalInfo = generalInfo;
    }
}
