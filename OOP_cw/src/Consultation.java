import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Consultation implements Serializable {

    private static ArrayList<Consultation> consultationList = new ArrayList<>();
    private String dateAndTime;
    private double cost;
    private String note;
    private String  doctorId;
    private String patientId;

    //Constructor
    public Consultation(String patient,String doctor,String dateAndTime){
        this.patientId = patient;
        this.doctorId = doctor;
        this.dateAndTime = dateAndTime;

    }
    public Consultation(String doctor,String  dateAndTime) {
        this.doctorId = doctor;
        this.dateAndTime = dateAndTime;
    }

    public Consultation(){  // Default constructor
    }


    public static void addConsultation(Consultation consultation) {
        consultationList.add(consultation);
    }

    // Setters and Getters


    public String  getDateAndTime() {
        return dateAndTime;
    }

    public double getCost(){
        return cost;
    }

    public String getNote(){
        return note;
    }

    public String getDoctor(){
        return doctorId;
    }

    public String  getPatient(){
        return patientId;
    }

    public static ArrayList<Consultation> getConsultationList() {
        return consultationList;
    }

    public void setDateAndTime(String  dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public void setNote(String note){
        this.note = note;
    }

    public void setDoctor(String doctor){
        this.doctorId = doctor;
    }

    public void setPatient(String  patient){
        this.patientId = patient;
    }

    public static void setConsultationList(ArrayList<Consultation> consultationList) {
        Consultation.consultationList = consultationList;
    }
}
