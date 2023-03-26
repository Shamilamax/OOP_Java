import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends Person implements Serializable {

    private static ArrayList<Doctor> doctorList = new ArrayList<>(10);
    private String medicalLicenseNumber;
    private String specialisation;


    // Constructor

    public Doctor(String name, String surname, String  dateOfBirth, String  mobileNumber,String  medicalLicenseNumber,String specialisation){
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialisation = specialisation;
    }

    public Doctor(){  // Default constructor
    }

    public static void addDoctor(Doctor doctor) {
        doctorList.add(doctor);
    }

    // Setters and Getters
    public String getMedicalLicenseNumber(){      //returns the values
        return medicalLicenseNumber;
    }

    public String getSpecialisation(){
        return specialisation;
    }

    public static ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }





    public void setMedicalLicenseNumber(String medicalLicenseNumber){  //store the values
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public void setSpecialisation(String specialisation){
        this.specialisation = specialisation;
    }

    public static void setDoctorList(ArrayList<Doctor> doctorList) {
        Doctor.doctorList = doctorList;
    }

}
