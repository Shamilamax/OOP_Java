import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewConsultationWindow extends JFrame implements ActionListener {
    JLabel labelValidate;
    JTextField TFValidate;

    Patient patient;
    Consultation consultation;

    JLabel labelHead;
    JLabel labelDateTime;
    JLabel labelDoctor;
    JLabel labelName;
    JLabel labelSurname;
    JLabel labelDoB;
    JLabel labelMobileNo;
    JLabel labelId;

    JButton buttonVerify;
    JPanel panelCenter = new JPanel();

    ViewConsultationWindow() {
        setTitle("View Consultation");
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //make the window Disappear with close button without making the program end
        setLocationRelativeTo(null);
        JPanel panelNorth = new JPanel();

        JPanel panelSouth = new JPanel();


        panelNorth.setBackground(Color.blue);
        panelCenter.setBackground(Color.red);

        panelSouth.setBackground(Color.yellow);


        panelNorth.setLayout(null);
        panelCenter.setLayout(null);

        panelNorth.setPreferredSize(new Dimension(100, 100));
        panelCenter.setPreferredSize(new Dimension(500, 100));
        panelCenter.setVisible(false);

        panelSouth.setPreferredSize(new Dimension(100, 100));


        labelValidate = new JLabel("Enter Patient NIC Number :");
        labelValidate.setBounds(100, 40, 200, 20);

        TFValidate = new JTextField();
        TFValidate.setBounds(280, 40, 150, 20);


        buttonVerify = new JButton("Verify ID");
        buttonVerify.addActionListener(this);
        buttonVerify.setBounds(500, 38, 100, 25);
        buttonVerify.setFocusable(false);

        labelHead = new JLabel("Consultation Details");
        labelHead.setBounds(250, 10, 200, 25);
        labelHead.setFont(new Font("Abadi", Font.BOLD, 20));

        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
        add(panelNorth, BorderLayout.NORTH);

        panelNorth.add(labelValidate);
        panelNorth.add(TFValidate);
        panelNorth.add(buttonVerify);
        panelCenter.add(labelHead);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonVerify) {
            int count = 0;
            for (int i = 0; i < Patient.getPatientList().size(); i++) {         // iterating through patient objects in the ArrayList
                patient = Patient.getPatientList().get(i);
                System.out.println("check1");
                if (TFValidate.getText().equals(patient.getPatientId())) { //Validating the patient ID
                    count ++;
                    System.out.println("check2");
                    labelId = new JLabel("Patient NIC Number : " + patient.getPatientId() );
                    labelId.setBounds(70, 70, 500, 20);
                    labelName = new JLabel("Patient Name : " + patient.getName() );
                    labelName.setBounds(70, 100, 500, 20);
                    labelSurname = new JLabel("Patient Surname : " + patient.getSurname() );
                    labelSurname.setBounds(70, 130, 500, 20);
                    labelDoB = new JLabel("Patient Date of Birth : " + patient.getDateOfBirth() );
                    labelDoB.setBounds(70, 160, 500, 20);
                    labelMobileNo = new JLabel("Patient Mobile Number : " + patient.getMobileNumber() );
                    labelMobileNo.setBounds(70, 190, 500, 20);
                    // Iterating through Consultation objects array list to get Doctor's Medical License Number
                    for (int j = 0; j < Consultation.getConsultationList().size(); j++){
                        consultation = Consultation.getConsultationList().get(j);
                        if(TFValidate.getText().equals(consultation.getPatient())){
                            //String MedLicNo = consultation.getDoctor();
                            labelDoctor = new JLabel("Booked Doctor's Medical License Number : " + consultation.getDoctor() );
                            labelDoctor.setBounds(70, 220, 500, 20);

                            labelDateTime = new JLabel("Date and Time of Consultation : " + consultation.getDateAndTime() );
                            labelDateTime.setBounds(70, 250, 500, 20);

                        }

                    }


                    panelCenter.add(labelId);
                    panelCenter.add(labelName);
                    panelCenter.add(labelSurname);
                    panelCenter.add(labelDoB);
                    panelCenter.add(labelMobileNo);
                    panelCenter.add(labelDoctor);
                    panelCenter.add(labelDateTime);



                    panelCenter.setVisible(true);
                }
            }
            if(count == 0){  // display warning for invalid ID
                JOptionPane.showMessageDialog(null,"Invalid patient NIC Number","Patient ID",JOptionPane.ERROR_MESSAGE);
                System.out.println("check3");
            }
        }
    }
}
