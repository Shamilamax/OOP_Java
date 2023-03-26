import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

interface skinConsultationManager{
    // Interface Methods
    void addDoctor(Doctor doctor);
    void deleteDoctor(ArrayList<Doctor> doctorList, String medicalLicenseNo);
    ArrayList<Doctor> printDoctor(ArrayList<Doctor> doctorList,Doctor doctor);
    void saveReport();
    void readData();
}

public class WestminsterSkinConsultationManager implements skinConsultationManager {    //Use of interface

    public static void main(String[] args) throws FileNotFoundException {       //throws keyword use to handle the exceptions

        Scanner input = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        Doctor doc = new Doctor();
        Patient pat = new Patient();
        Consultation con = new Consultation();
        String selection;
        manager.readData();

        while (true){

            System.out.println();
            System.out.println();
            // Menu Console
            System.out.println("============================");
            System.out.println(" |   MENU SELECTION    |");
            System.out.println("============================");
            System.out.println("1 : Add a Doctor\n2 : Delete a Doctor\n3 : Print the List of Doctors\n4 : Save Data into a File\n5 : Open the Graphical User Interface\n6 : End");
            System.out.println();
            System.out.println("Enter the number of the procedure you want to perform");
            selection = input.next();

            // Adding a Doctor
            if (selection.equals("1")){
                if (Doctor.getDoctorList().size() <= 9) { // Checking the number of doctors already allocated for the clinic
                    System.out.println("Enter Doctor name : ");
                    String menuName = input.next();
                    System.out.println("Enter Doctor Surname : ");
                    String menuSurname = input.next();
                    System.out.println("Enter Doctor Date of Birth : (DD/MM/YYYY)");
                    String  menuDateOfBirth = input.next();
                    System.out.println("Enter Doctor Mobile Number : ");
                    String  menuMobileNumber = input.next();
                    System.out.println("Enter Doctor Medical License Number : ");
                    String menuMedicalLicenseNumber = input.next();
                    System.out.println("Enter Doctor Specialisation : ");
                    String menuSpecialisation = input.next();
                    doc = new Doctor(menuName, menuSurname, menuDateOfBirth, menuMobileNumber, menuMedicalLicenseNumber, menuSpecialisation);
                    manager.addDoctor(doc);
                    System.out.println("-----Doctor Added to the System Successfully-----");
                    System.out.println();
                    System.out.println(Doctor.getDoctorList());
                }
                else{
                    System.out.println("-----Can Not Add More Doctors. Already there are 10 doctors allocated for the clinic.-----");
                }
            }

            // Deleting a doctor
            else if (selection.equals("2")){
                if (Doctor.getDoctorList().isEmpty()){        // Checking whether the doctorList is empty
                    System.out.println("-----No Doctors allocated for the Clinic to Delete-----");
                }
                else{
                    System.out.println("Enter the Medical License Number of the Doctor You Want to Delete : ");
                    String menuMedicalLicenseNumber = input.next();
                    manager.deleteDoctor(Doctor.getDoctorList(),menuMedicalLicenseNumber);

                }
            }

            // Print the list of doctors
            else if (selection.equals("3")){
                if (Doctor.getDoctorList().isEmpty()){        // Checking whether the doctorList is empty
                    System.out.println("-----No Doctors allocated for the Clinic to Display-----");
                }
                else{

                    manager.printDoctor(Doctor.getDoctorList(),doc);
                }
            }

            //Save information into a file
            else if (selection.equals("4")){
                manager.saveReport();

            }

            //Open GUI
            else if(selection.equals("5")){
                GUI.main(new String[] {});
            }

            // Ending the menu
            else if(selection.equals("6")){
                break;
            }

            // Handling invalid inputs
            else{
                System.out.println("-----Invalid Input-----");
            }
        }

    }






    // Implemented interface methods
    public void addDoctor(Doctor doctor){
        Doctor.addDoctor(doctor);
    }



    public void deleteDoctor(ArrayList<Doctor> doctorList, String medicalLicenseNo){
        int counter = 0;
        for (int i = 0; i < doctorList.size(); i++) {         // iterating through doctor objects in the ArrayList
            Doctor doctor = doctorList.get(i);
            if (medicalLicenseNo.equals(doctor.getMedicalLicenseNumber())) {       // checking the user input license number
                displayDoctorDetails(doctor);
                doctorList.remove(doctor);
                System.out.println();
                System.out.println("-----Doctor Has Been Deleted Successfully-----");
                System.out.println("-----There are " + Doctor.getDoctorList().size() + " Doctors Remaining in the Clinic-----");
                counter ++;
            }
        }
        if (counter ==  0){
            System.out.println("-----There is No Doctor in the Clinic with that Medical License Number-----");
        }

    }



    public ArrayList<Doctor> printDoctor(ArrayList<Doctor> doctorList, Doctor doctor){
        ArrayList<Doctor> sortedDoctorList = doctorList;
        sortedDoctorList.sort(new Comparator<Doctor>() {           // Sorting new array
            @Override
            public int compare(Doctor doc1, Doctor doc2) {          // Compare surname attribute of objects in the array
                return doc1.getSurname().compareTo(doc2.getSurname());
            }
        });
        for (int i = 0; i < sortedDoctorList.size(); i++) {         // iterating through doctor objects in the ArrayList
            displayDoctorDetails(sortedDoctorList.get(i));
        }
        return sortedDoctorList;

    }




    public void saveReport(){

        try {
            FileOutputStream fos = new FileOutputStream("Doctor Details.dat");  //writing data to a File
            ObjectOutputStream oos = new ObjectOutputStream(fos);       //writes primitive data types and graphs of Java objects to an OutputStream.
            oos.writeObject(Doctor.getDoctorList());
            oos.close();            //Close the stream
            fos.close();
        }
        catch (IOException e){
            System.out.println("An Error Occurred");
            e.printStackTrace();
        }
        System.out.println("-----Information Successfully Saved in a File-----");
    }



    public void readData() {
        Path path = Paths.get("Doctor Details.dat");
        //ArrayList<Doctor> savedDoctorList = null;
        if (Files.exists(path)) {
            try {
                FileInputStream fis = new FileInputStream("Doctor Details.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Doctor> savedDoctorList = (ArrayList<Doctor>) ois.readObject();
                ois.close();
                fis.close();

                Doctor.setDoctorList(savedDoctorList);
                // Print the data in the read lists
                System.out.println("List 1: " + savedDoctorList);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("An Error Occurred");
                e.printStackTrace();
            }
        }

    }



    public void displayDoctorDetails(Doctor doctor){
        System.out.println("Name                   = " + doctor.getName());
        System.out.println("Surname                = " + doctor.getSurname());
        System.out.println("Date Of Birth          = " + doctor.getDateOfBirth());
        System.out.println("Mobile Number          = " + doctor.getMobileNumber());
        System.out.println("Medical License Number = " + doctor.getMedicalLicenseNumber());
        System.out.println("Specialisation         = " + doctor.getSpecialisation());
        System.out.println("_________________________________________________________________");
    }

}
