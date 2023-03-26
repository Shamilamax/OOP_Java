import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.*;

public class GUI extends JFrame implements ActionListener {

    JButton buttonSort;
    JButton buttonAddConsultation;
    JButton buttonViewConsult;
    JTable tableDoctor;

    DefaultTableModel model;            //uses a Vector of Vectors to store the cell value objects.
    JScrollPane scrollPane;
    Doctor doctor = new Doctor();
    WestminsterSkinConsultationManager WSM = new WestminsterSkinConsultationManager();

    public GUI(Doctor doctor) {

        setTitle("Westminster Skin Consultation Centre");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // To make the program end with close button in the window
        setLocationRelativeTo(null);       //To make window appear in center of the screen
        setLayout(new BorderLayout(5, 5));     // Border of the layouts

        // Making panel Objects
        JPanel panelNorth = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelWest = new JPanel();

        panelNorth.setBackground(Color.green);
        panelCenter.setBackground(Color.red);
        panelEast.setBackground(Color.blue);
        panelSouth.setBackground(Color.yellow);
        panelWest.setBackground(Color.pink);

        panelNorth.setPreferredSize(new Dimension(100, 100));
        panelCenter.setPreferredSize(new Dimension(500, 100));
        panelEast.setPreferredSize(new Dimension(5, 100));
        panelSouth.setPreferredSize(new Dimension(100, 100));
        panelWest.setPreferredSize(new Dimension(120, 50));

        panelSouth.setLayout(null);

        JLabel labelGreeting = new JLabel("Welcome to Westminster Skin Consultation Center");
        labelGreeting.setFont(new Font("Abadi", Font.BOLD, 25));

        JLabel labelTable = new JLabel("Given below doctors available in our center");
        labelTable.setFont(new Font("Abadi", Font.BOLD, 15));

        //Displaying the Doctor Table
        model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Date of Birth");
        model.addColumn("MobileNo");
        model.addColumn("MedicalLicenseNo");
        model.addColumn("Specialisation");

        // Creating a new table object
        tableDoctor = new JTable(model);
        tableDoctor.setEnabled(false);   // Avoid user editing the table
        // Add data to the table from doctorList in Doctor class
        for (int i = 0; i < Doctor.getDoctorList().size(); i++) {
            doctor = Doctor.getDoctorList().get(i);
            model.addRow(new Object[]{doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getMedicalLicenseNumber(), doctor.getSpecialisation()});
        }
        tableDoctor.setModel(model);

        // Using a JScrollPane to hold the JTable
        scrollPane = new JScrollPane(tableDoctor);
        scrollPane.setPreferredSize(new Dimension(600,300));

        // Button to sort the doctor table
        buttonSort = new JButton("Sort the Table");
        buttonSort.addActionListener(this);
        buttonSort.setFocusable(false);

        // Button to add a consultation for the patient
        buttonAddConsultation = new JButton("Add Consultation");
        buttonAddConsultation.addActionListener(this);
        buttonAddConsultation.setBounds(200,30,150,30);
        buttonAddConsultation.setFocusable(false);

        buttonViewConsult = new JButton("View Consultation Details");
        buttonViewConsult.addActionListener(this);
        buttonViewConsult.setBounds(450,30,200,30);
        buttonViewConsult.setFocusable(false);

        // Adding all the components to the frame
        add(panelNorth, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelEast, BorderLayout.EAST);
        add(panelSouth, BorderLayout.SOUTH);
        add(panelWest, BorderLayout.WEST);

        panelNorth.add(labelGreeting);
        panelNorth.add(labelTable);
        panelCenter.add(scrollPane);


        panelSouth.add(buttonAddConsultation);
        panelSouth.add(buttonViewConsult);
        panelWest.add(buttonSort);


    }

    public static void main(String[] args) {
        Doctor doctor = new Doctor();
        GUI gui = new GUI(doctor);
        gui.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSort) {
            ArrayList<Doctor> sortList = Doctor.getDoctorList();   // Copy doctorList into a new array
            sortList.sort(new Comparator<Doctor>() {           // Sorting new array
                @Override
                public int compare(Doctor doc1, Doctor doc2) {          // Compare surname attribute of objects in the array
                    return doc1.getSurname().compareTo(doc2.getSurname());
                }
            });
            model.setRowCount(0);   // Deleting data existed in the table before adding sorted data
            for (int i = 0; i < sortList.size(); i++) {      // iterating through doctor objects in the ArrayList and adding data to the table
                doctor = sortList.get(i);
                model.addRow(new Object[]{doctor.getName(), doctor.getSurname(), doctor.getDateOfBirth(), doctor.getMobileNumber(), doctor.getMedicalLicenseNumber(), doctor.getSpecialisation()});
            }
        }

        if (e.getSource() == buttonAddConsultation) {
            ConsultationWindow ConsultationWindow = null;
            try {
                ConsultationWindow = new ConsultationWindow();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            ConsultationWindow.setVisible(true);

        }

        if (e.getSource() == buttonViewConsult){
            ViewConsultationWindow ViewConsult = new ViewConsultationWindow();
            ViewConsult.setVisible(true);
        }
    }
}













