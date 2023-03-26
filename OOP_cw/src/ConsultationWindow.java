import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
//Add consultatio button

public class ConsultationWindow extends JFrame implements ActionListener {
    SpinnerDateModel spinnerModel;
    JSpinner spinner;
    JTextField TFMedicalNO;
    JTextField TFName;
    JTextField TFSurname;
    JFormattedTextField TFDoB;
    JFormattedTextField TFMobileNo;
    JTextField TFId;
    JTextArea textAreaNotes;
    JFileChooser fileChooser;

    JLabel labelGreetingConsult;
    JLabel labelDateTime;
    JLabel labelDoctor;
    JLabel labelName;
    JLabel labelSurname;
    JLabel labelDoB;
    JLabel labelMobileNo;
    JLabel labelId;
    JLabel labelNotes;

    JButton buttonCheck;
    JButton buttonConfirmConsult;
    JButton buttonCancelConsult;
    JButton uploadButton;

    Consultation consultation = new Consultation();
    Doctor doctor = new Doctor();

    String dateString;
    String name;
    String Surname;
    String DOB;
    String  MobileNo;
    int Id;
    ConsultationWindow() throws ParseException {
        // Setting frame specifications
        setTitle("Add a Consultation");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // To make the window Disappear with close button without making the program end
        setLocationRelativeTo(null);

        //Making Panels
        setLayout(new BorderLayout(5, 5));     // Border of the layouts
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelNorth = new JPanel();

        panelEast.setBackground(Color.blue);
        panelWest.setBackground(Color.red);
        panelCenter.setBackground(Color.yellow);
        panelSouth.setBackground(Color.green);
        panelNorth.setBackground(Color.magenta);

        panelEast.setLayout(null);
        panelSouth.setLayout(null);

        panelEast.setPreferredSize(new Dimension(150, 100));
        panelWest.setPreferredSize(new Dimension(300, 100));
        panelCenter.setPreferredSize(new Dimension(50, 100));
        panelSouth.setPreferredSize(new Dimension(50, 100));
        panelNorth.setPreferredSize(new Dimension(50, 50));

        labelGreetingConsult = new JLabel("Fill in the Details Below to Add a Consultation");
        labelGreetingConsult.setFont(new Font("Abadi", Font.BOLD, 15));

        labelDateTime = new JLabel("Date and Time of Consultation");
        labelDateTime.setPreferredSize(new Dimension(240,30));

        labelDoctor = new JLabel("Select a Doctor(Enter MedicalLicenseNo)");
        labelDoctor.setPreferredSize(new Dimension(240,30));

        labelName = new JLabel("Patient Name");
        labelName.setPreferredSize(new Dimension(240,30));

        labelSurname = new JLabel("Patient Surname");
        labelSurname.setPreferredSize(new Dimension(240,30));

        labelDoB = new JLabel("Patient Date Of Birth (DD/MM/YYYY)");
        labelDoB.setPreferredSize(new Dimension(240,30));

        labelMobileNo = new JLabel("Patient Mobile Number");
        labelMobileNo.setPreferredSize(new Dimension(240,30));

        labelId = new JLabel("Patient NIC Number");
        labelId.setPreferredSize(new Dimension(240,30));

        labelNotes = new JLabel("Add Notes (Visual or Textual)");
        labelNotes.setPreferredSize(new Dimension(240,30));




        // Get time only in hours
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR);
        calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR);
        //calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR);
        //SpinnerDateModel model = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR);

        // Create a JSpinner object using the spinner model
        spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(250,30));
        // Let user input date and time in hours
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy , HH" + ":00");
        spinner.setEditor(editor);
        //String dateString = date.toString();

        // Creating a text field that only take integer
        // Create a MaskFormatter that allows only digits to be entered
        /*MaskFormatter formatterMedicalNo = new MaskFormatter("##########");
        formatterMedicalNo.setValidCharacters("0123456789");

        // Use the MaskFormatter to create a JFormattedTextField
        TFMedicalNO = new JFormattedTextField(formatterMedicalNo);
        TFMedicalNO.setPreferredSize(new Dimension(250,30));*/
        TFMedicalNO = new JTextField();
        TFMedicalNO.setPreferredSize(new Dimension(250,30));

        TFName = new JTextField();
        TFName.setPreferredSize(new Dimension(250,30));

        TFSurname = new JTextField();
        TFSurname.setPreferredSize(new Dimension(250,30));

        MaskFormatter formatterDoB = new MaskFormatter("##/##/####");
        formatterDoB.setValidCharacters("0123456789");

        TFDoB = new JFormattedTextField(formatterDoB);
        TFDoB.setPreferredSize(new Dimension(250,30));

        MaskFormatter formatterMobileNo = new MaskFormatter("###-#######");
        formatterMobileNo.setValidCharacters("0123456789");

        TFMobileNo = new JFormattedTextField(formatterMobileNo);
        TFMobileNo.setPreferredSize(new Dimension(250,30));

        TFId = new JTextField();
        TFId.setPreferredSize(new Dimension(250,30));

        textAreaNotes = new JTextArea(5,20);
        textAreaNotes.setPreferredSize(new Dimension(250,60));
        textAreaNotes.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textAreaNotes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //textAreaNotes.setLineWrap(true);
        //textAreaNotes.setWrapStyleWord(true);

        fileChooser = new JFileChooser();
        uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(this);
        uploadButton.setFocusable(false);
        uploadButton.setPreferredSize(new Dimension(250,30));



        buttonConfirmConsult = new JButton("Confirm");
        buttonConfirmConsult.addActionListener(this);
        buttonConfirmConsult.setBounds(150,30,100,30);
        buttonConfirmConsult.setFocusable(false);
        buttonCancelConsult = new JButton("Cancel");
        buttonCancelConsult.setBounds(500,30,100,30);
        buttonCancelConsult.setFocusable(false);
        buttonCancelConsult.addActionListener(this);
        buttonCheck = new JButton("Check Availability");
        buttonCheck.setBounds(5,45,140,20);
        buttonCheck.setFocusable(false);
        buttonCheck.addActionListener(this);


        //Adding all the components to the frame
        add(panelEast,BorderLayout.EAST);
        add(panelWest,BorderLayout.WEST);
        add(panelCenter,BorderLayout.CENTER);
        add(panelSouth,BorderLayout.SOUTH);
        add(panelNorth,BorderLayout.NORTH);

        panelNorth.add(labelGreetingConsult);

        panelWest.add(labelDateTime);
        panelWest.add(labelDoctor);
        panelWest.add(labelName);
        panelWest.add(labelSurname);
        panelWest.add(labelDoB);
        panelWest.add(labelMobileNo);
        panelWest.add(labelId);
        panelWest.add(labelNotes);


        panelCenter.add(spinner);
        panelCenter.add(TFMedicalNO);
        panelCenter.add(TFName);
        panelCenter.add(TFSurname);
        panelCenter.add(TFDoB);
        panelCenter.add(TFMobileNo);
        panelCenter.add(TFId);
        panelCenter.add(scrollPane);
        panelCenter.add(uploadButton);

        panelEast.add(buttonCheck);
        panelSouth.add(buttonConfirmConsult);
        panelSouth.add(buttonCancelConsult);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCancelConsult) {
            System.out.println("poo");
            dispose();
        }

        if (e.getSource() == buttonCheck){
            int count1 = 0;
            int count2 = 0;
            Object value = spinner.getValue();
            Date date = (Date) value;
            dateString = date.toString();
            System.out.println(dateString);
            for (int i = 0; i < Doctor.getDoctorList().size(); i++) {// iterating through doctor objects in the ArrayList
                doctor = Doctor.getDoctorList().get(i);
                System.out.println("check 1");
                if (TFMedicalNO.getText().equals(doctor.getMedicalLicenseNumber())){    // Validating the entered Medical License Number
                    count1++;
                    System.out.println("check 2");
                    for (int j = 0; j < Consultation.getConsultationList().size(); j++){ // iterating through Consultation objects in the ArrayList
                        System.out.println("check 4");
                        consultation = Consultation.getConsultationList().get(j);
                        if(TFMedicalNO.getText().equals(consultation.getDoctor()) && dateString.equals(consultation.getDateAndTime())) {
                            System.out.println("Check 5");
                            JOptionPane.showMessageDialog(null, "Doctor is Unavailable on that date and time\n A new Doctor will be allocated", "Doctor Availability", JOptionPane.WARNING_MESSAGE);
                            int k = 0;
                            while(k < Doctor.getDoctorList().size() ){
                                // Adding a random doctor
                                Random random = new Random();
                                int randomInt = random.nextInt(10); //creating a random number from 0 to 9
                                doctor = Doctor.getDoctorList().get(randomInt);
                                TFMedicalNO.setText(doctor.getMedicalLicenseNumber());    //Store Medical License number of the random doctor
                                // Checking whether the random doctor is available in given specific date and time
                                if( !(TFMedicalNO.getText().equals(consultation.getDoctor()) && dateString.equals(consultation.getDateAndTime()))){
                                    count2 ++;
                                    break;
                                }
                            }
                            if(count2 == 0){
                                JOptionPane.showMessageDialog(null, "All the Doctors are not available on that date and time", "Doctor Availability", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Doctor is available\n", "Doctor Availability", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            }
            if (count1 == 0){
                JOptionPane.showMessageDialog(null,"Invalid Medical License Number","Doctor Availability",JOptionPane.WARNING_MESSAGE);
            }
            System.out.println("poo");
        }

        if (e.getSource() == buttonConfirmConsult) {
            // Validating whether user have entered necessary information
            if (TFMedicalNO.getText().isEmpty() || TFId.getText().isEmpty() || TFName.getText().isEmpty() || TFMobileNo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Fill all the details to book a consultation","Consultation",JOptionPane.ERROR_MESSAGE);
            }
            else{
                Object value = spinner.getValue();
                Date date = (Date) value;
                // Adding data to the consultation list
                consultation = new Consultation(TFId.getText(),TFMedicalNO.getText(),dateString);
                Consultation.addConsultation(consultation);
                for (int i = 0; i < Consultation.getConsultationList().size(); i++){
                    consultation = Consultation.getConsultationList().get(i);
                    System.out.println(consultation.getPatient());
                    System.out.println(consultation.getDoctor());
                    System.out.println(consultation.getDateAndTime());
                }

                System.out.println(Consultation.getConsultationList());
                // Adding data to the patient list
                Patient patient = new Patient(TFName.getText(),TFSurname.getText(),(String) TFDoB.getValue(),(String) TFMobileNo.getValue(),TFId.getText());
                Patient.addPatient(patient);
                System.out.println("poo");
                System.out.println(Patient.getPatientList());
                dispose();
            }

        }
        if(e.getSource() == uploadButton){
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Do something with the selected file (e.g. display the image in a label)
                File file = fileChooser.getSelectedFile();
        }
    }
}
}
