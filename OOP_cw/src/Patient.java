import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Person implements Serializable {

    private static ArrayList<Patient> patientList = new ArrayList<>();
    private String patientId;

    // Constructor
    public Patient(String name, String surname, String dateOfBirth, String  mobileNumber,String patientId){
        super(name, surname, dateOfBirth, mobileNumber);
        this.patientId = patientId;
    }
    public Patient(){  // Default constructor
    }

    public static void addPatient(Patient patient) {
        patientList.add(patient);
    }


    // Setters and Getters
    public String getPatientId(){
        return patientId;
    }

    public static ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientId(String  patientId){
        this.patientId = patientId;
    }

    public static void setPatientList(ArrayList<Patient> patientList) {
        Patient.patientList = patientList;
    }
}
