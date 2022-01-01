package hospital.hospital.blood.models;

import hospital.hospital.blood.entity.Blood;
import lombok.Data;

//TODO metoda szablonowa wzorzec projektowy
@Data
abstract class BloodMaker {
    public final BloodResults buildResults(Blood blood,String sex){
        BloodResults bloodResults = new BloodResults();
        if(sex.equals("Man")) manResults(bloodResults,blood);
        else if (sex.equals("Woman")) womanResults(bloodResults,blood);
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("leukocyty, WBC").amount(blood.getLeukocytes()).result(checkLeukocites(blood.getLeukocytes())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("Bazofile").amount(blood.getBasophils()).result(checkBazofiles(blood.getBasophils())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("Eozynofile").amount(blood.getEosinophils()).result(checkEnzofils(blood.getEosinophils())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("Neutrofile").amount(blood.getNeutrophils()).result(checkNeutrophils(blood.getNeutrophils())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("Limfocyty").amount(blood.getLymphocytes()).result(checkLymphocytes(blood.getLymphocytes())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("Monocyty").amount(blood.getMonocytes()).result(checkMonocytes(blood.getMonocytes())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("trombocyty, PLT").amount(blood.getThrombocytes()).result(checkThrombocytes(blood.getThrombocytes())).unit("x 10^9/l").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("RDW").amount(blood.getRedCellVolume()).result(checkRDW(blood.getRedCellVolume())).unit("%").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("MCH").amount(blood.getAverageHemoglobinContent()).result(checkMCH(blood.getAverageHemoglobinContent())).unit("pg").build());
        bloodResults.getBloodElements()
                .add(BloodElement.builder()
                        .name("MCHC").amount(blood.getHemoglobinConcentration()).result(checkMCHC(blood.getHemoglobinConcentration())).unit("g/dl").build());
        return bloodResults;
    }

    private void manResults(BloodResults bloodResult,Blood blood){
        bloodResult.getBloodElements().add(BloodElement.builder().name("erytrocyty, RBC").amount(blood.getErythrocytes()).result(checkErythrocytesMan(blood.getErythrocytes())).unit("x 10^12/l").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("HCT").amount(blood.getHematocrit()).result(checkHematritMan(blood.getHematocrit())).unit("%").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("HGB, Hb").amount(blood.getHemoglobin()).result(checkHemoglobinMan(blood.getHemoglobin())).unit("g/dl").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("MCV, ŚOK").amount(blood.getAverageVolumeCell()).result(checkMCVMan(blood.getAverageVolumeCell())).unit("fl").build());
    }

    private void womanResults(BloodResults bloodResult,Blood blood){
        bloodResult.getBloodElements().add(BloodElement.builder().name("erytrocyty, RBC").amount(blood.getErythrocytes()).result(checkErythrocytesWoman(blood.getErythrocytes())).unit("x 10^12/l").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("HCT").amount(blood.getHematocrit()).result(checkHematritWoman(blood.getHematocrit())).unit("%").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("HGB, Hb").amount(blood.getHemoglobin()).result(checkHemoglobinWoman(blood.getHemoglobin())).unit("g/dl").build());
        bloodResult.getBloodElements().add(BloodElement.builder().name("MCV, ŚOK").amount(blood.getAverageVolumeCell()).result(checkMCVWoman(blood.getAverageVolumeCell())).unit("fl").build());
    }

    private boolean checkHematritMan(Float hermatrit){
        return BloodUtils.numberBeetwen(42f,52f,hermatrit);
    }

    private boolean checkHematritWoman(Float hermatrit){
        return BloodUtils.numberBeetwen(37f,47f,hermatrit);
    }

    private boolean checkHemoglobinMan(Float hemoglobin){
        return BloodUtils.numberBeetwen(12.5f,18f,hemoglobin);
    }

    private boolean checkHemoglobinWoman(Float hemoglobin){
        return BloodUtils.numberBeetwen(11.5f,16f,hemoglobin);
    }

    private boolean checkLeukocites(Float leukocites){
        return BloodUtils.numberBeetwen(4f,10.8f,leukocites);
    }

    private boolean checkBazofiles(Float BASO){
        return BloodUtils.numberBeetwen(0f,0.2f,BASO);
    }

    private boolean checkEnzofils(Float eosinophils){
        return BloodUtils.numberBeetwen(0f,0.45f,eosinophils);
    }

    private boolean checkNeutrophils(Float neutrophils){
        return BloodUtils.numberBeetwen(1.8f,7.7f,neutrophils);
    }

    private boolean checkLymphocytes(Float lymphocytes){
        return BloodUtils.numberBeetwen(1f,4.5f,lymphocytes);
    }

    private boolean checkMonocytes(Float monocytes){
        return BloodUtils.numberBeetwen(0f,0.8f,monocytes);
    }

    private boolean checkErythrocytesMan(Float erythrocytes){
        return BloodUtils.numberBeetwen(4.7f,6.1f,erythrocytes);
    }

    private boolean checkErythrocytesWoman(Float erythrocytes){
        return BloodUtils.numberBeetwen(4.2f,5.4f,erythrocytes);
    }

    private boolean checkThrombocytes(Float thrombocytes){
        return BloodUtils.numberBeetwen(130f,450f,thrombocytes);
    }

    private boolean checkRDW(Float RDW){
        return BloodUtils.numberBeetwen(11.5f,14.5f,RDW);
    }

    private boolean checkMCVMan(Float MCV){
        return BloodUtils.numberBeetwen(80f,94f,MCV);
    }

    private boolean checkMCVWoman(Float MCV){
        return BloodUtils.numberBeetwen(81f,99f,MCV);
    }

    private boolean checkMCH(Float MCH){
        return BloodUtils.numberBeetwen(27f,31f,MCH);
    }

    private boolean checkMCHC(Float MCHC){
        return BloodUtils.numberBeetwen(33f,37f,MCHC);
    }
}
