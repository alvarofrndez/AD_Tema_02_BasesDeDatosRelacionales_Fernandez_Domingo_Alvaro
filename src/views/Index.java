/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import components.JTableCourses;
import components.JTableLicenses;
import components.JTableStudents;
import components.JTableExams;
import components.ModalMessage;
import java.sql.Connection;
import java.sql.Date;
import controllers.Connect;
import controllers.Course;
import controllers.Exam;
import controllers.ExportJSON;
import controllers.ExportXML;
import controllers.License;
import controllers.Student;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import models.ExamModel;
import models.LicenseModel;
import models.StudentModel;

/**
 *
 * @author alvaro
 */
public class Index {
    // Componentss
    private JTableStudents jtable_students;
    private JTableCourses jtable_courses;
    private JTableLicenses jtable_licenses;
    private JTableExams jtable_exams;
    private ModalMessage modal_message;
    
    // Controllers
    private Connect con_ctrl;
    private Connection con;
    private Student student_ctrl;
    private Course course_ctrl;
    private License license_ctrl;
    private Exam exam_ctrl;
    
    // variables
    private JFrame frame;
    private JTextField mark_input;
    private JTextField date_input;
    private JButton json_button;
    private JButton xml_button;
    private JButton update_exam;
    
    public Index(){
        // create the conexion, refactor to create with inputs and the data of them
        this.con_ctrl = new Connect("localhost","1521","AD_TEMA02","AD_TEMA02","ACCESO_A_DATOS");
        this.con = this.con_ctrl.getConnect();
        this.modal_message = new ModalMessage();
        
        if(this.con == null){
            this.modal_message.showModal("No se ha establecido la conexión", "Error en la conexión", "error");
        }else{
            run();
        }
    }
    
    public void run(){
        frame = new JFrame("Gestión academia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        
        JPanel global_container = new JPanel();
        global_container.setLayout(new BoxLayout(global_container, BoxLayout.Y_AXIS));
        global_container.setSize(1200,900);
        
        JPanel first_container = new JPanel();
        first_container.setLayout(new BoxLayout(first_container, BoxLayout.X_AXIS));
        first_container.setPreferredSize(new Dimension(1200, 300));

        JPanel second_container = new JPanel();
        second_container.setLayout(new BoxLayout(second_container, BoxLayout.Y_AXIS));
        second_container.setPreferredSize(new Dimension(1200, 300));
        
        JPanel third_container = new JPanel();
        third_container.setLayout(new BoxLayout(third_container, BoxLayout.X_AXIS));
        third_container.setPreferredSize(new Dimension(1200, 300));
        
        createFirstContainer(first_container);
        createSecondContainer(second_container);
        createThirdContainer(third_container);
        
        global_container.add(first_container);
        global_container.add(second_container);
        global_container.add(third_container);
        
        frame.add(global_container);
        frame.setVisible(true);
    }
    
    public void createFirstContainer(JPanel first_container){
        this.student_ctrl = new Student(this.con);
        this.course_ctrl = new Course(this.con);

        // see the comment in this method
        this.jtable_students = new JTableStudents(this.student_ctrl.getAll());
        
        this.jtable_students.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    if (e.getClickCount() == 1) {
                        int selected_row = jtable_students.getTable().getSelectedRow();
            
                        String cod = (String) jtable_students.getTable().getValueAt(selected_row, 0);
                        String nom = (String) jtable_students.getTable().getValueAt(selected_row, 1);
                        
                        student_ctrl.setSelected(cod, nom);
                            
                        fillLicenseTable();
                        
                        date_input.setText("");
                        mark_input.setText("");
                        
                        jtable_exams.getModel().setRowCount(0);
                        
                        update_exam.setEnabled(false);
                        json_button.setEnabled(false);
                        xml_button.setEnabled(false);
                    }
                }catch(Exception ex){
                    return;
                }
            }
        });
        
        this.jtable_courses = new JTableCourses(this.course_ctrl.getAll());
        
        this.jtable_courses.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    if (e.getClickCount() == 1) {
                        int selected_row = jtable_courses.getTable().getSelectedRow();
                        
                        String cod = (String) jtable_courses.getTable().getValueAt(selected_row, 0);
                        String nom = (String) jtable_courses.getTable().getValueAt(selected_row, 1);
                        int num_exa = (int) jtable_courses.getTable().getValueAt(selected_row, 2);
                        
                        course_ctrl.setSelected(cod, nom, num_exa);                        
                    }
                }catch(Exception ex){
                    return;
                }
            }
        });
        
        first_container.add(new JScrollPane(this.jtable_students.getTable()), BorderLayout.CENTER);
        first_container.add(new JScrollPane(this.jtable_courses.getTable()), BorderLayout.CENTER);
    }
    
    public void createSecondContainer(JPanel second_container){
        this.license_ctrl = new License(this.con);

        // see the comment in this method
        this.jtable_licenses = new JTableLicenses();
        
        this.jtable_licenses.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try{
                    if (e.getClickCount() == 1) {
                        int selected_row = jtable_licenses.getTable().getSelectedRow();
                        
                        String cod_alu = (String) jtable_licenses.getTable().getValueAt(selected_row, 0);
                        String cod_curso = (String) jtable_licenses.getTable().getValueAt(selected_row, 2);
                        double nota_media = (double) jtable_licenses.getTable().getValueAt(selected_row, 4);

                        license_ctrl.setSelected(cod_alu, cod_curso, nota_media);
                        
                        date_input.setText("");
                        mark_input.setText("");
                        
                        fillExamsTable();
                        
                        json_button.setEnabled(true);
                        xml_button.setEnabled(true);
                    }
                }catch(Exception ex){
                    return;
                }
            }
        });
        
        JButton button = new JButton("Matricular alumno en el curso");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(student_ctrl.getSelected() != null && course_ctrl.getSelected() != null){
                    if(license_ctrl.insert(student_ctrl.getSelected().getcCodAlu(), course_ctrl.getSelected().getcCodCurso())){
                        fillLicenseTable();
                    }else{
                        modal_message.showModal("Error al matricular el alumno", "Error matriculación", "error");
                    }
                }else{
                    modal_message.showModal("Seleccione el alumno y el curso primero", "Matricular alumno", "succes");
                }
            }
        });
        
        second_container.add(button);
        second_container.add(new JScrollPane(this.jtable_licenses.getTable()), BorderLayout.CENTER);
    }
    
    public void createThirdContainer(JPanel third_container){
        this.exam_ctrl = new Exam(this.con);

        // see the comment in this method
        this.jtable_exams = new JTableExams();
        
        this.jtable_exams.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Error of right click
                try{
                    if (e.getClickCount() == 1) {
                        int selected_row = jtable_exams.getTable().getSelectedRow();
                        
                        int num_exa = (int) jtable_exams.getTable().getValueAt(selected_row, 0);
                        Date fec_exa = (Date) jtable_exams.getTable().getValueAt(selected_row, 1);
                        double nota_exa = (double) jtable_exams.getTable().getValueAt(selected_row, 2);
                        String cod_alu = exam_ctrl.getCodAlu();
                        String cod_curso = exam_ctrl.getCodCurso();

                        exam_ctrl.setSelected(cod_alu, cod_curso, num_exa, fec_exa, nota_exa);
                        
                        if(String.valueOf(fec_exa).equals("null")){
                            date_input.setText("");
                        }else{
                            date_input.setText(String.valueOf(fec_exa));
                        }
                        mark_input.setText(String.valueOf(nota_exa));
                        
                        update_exam.setEnabled(true);
                    }
                }catch(Exception ex){
                    return;
                }
            }
        });
        
        JPanel inputs_container = new JPanel();
        inputs_container.setPreferredSize(new Dimension(300,300));
        inputs_container.setLayout(new BoxLayout(inputs_container, BoxLayout.Y_AXIS));
        
        JPanel date_container = new JPanel();
        date_container.setLayout(new FlowLayout());
        date_container.setPreferredSize(new Dimension(300, 75));

        JLabel date_label = new JLabel("Fecha(yyyy-MM-dd)");
        date_label.setPreferredSize(new Dimension(190, 30));
        date_input = new JTextField();
        date_input.setPreferredSize(new Dimension(100, 30));

        date_container.add(date_label);
        date_container.add(date_input);
        
        JPanel mark_container = new JPanel();
        mark_container.setLayout(new FlowLayout());
        mark_container.setPreferredSize(new Dimension(300, 75));

        JLabel mark_label = new JLabel("Nota");
        mark_label.setPreferredSize(new Dimension(190, 30));
        mark_input = new JTextField();
        mark_input.setPreferredSize(new Dimension(100, 30));
        
        mark_container.add(mark_label);
        mark_container.add(mark_input);

        this.update_exam = new JButton("actualizar");
        this.update_exam.setEnabled(false);
        this.update_exam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(exam_ctrl.updateExam(exam_ctrl.getCodAlu(), exam_ctrl.getCodCurso(), date_input.getText(), mark_input.getText(), exam_ctrl.getSelected().getNumExa())){
                    modal_message.showModal("Cambios realizados", "Examen actualizado", "succes");
                    fillLicenseTable();
                    fillExamsTable();
                }else{
                    modal_message.showModal("Error de conexión o datos invalidos", "Error actualizar examen", "error");
                }
            }
        });
        
        JPanel export_container = new JPanel();
        export_container.setPreferredSize(new Dimension(300, 75));
        export_container.setLayout(new BoxLayout(export_container, BoxLayout.Y_AXIS));
        
        this.json_button = new JButton("exportar a JSON");
        this.json_button.setEnabled(false);
        this.json_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<ExamModel> exams = exam_ctrl.getAll(license_ctrl.getSelected().getCodAlu(), license_ctrl.getSelected().getCodCurso());
                
                if(exams.size() == 0){
                    modal_message.showModal("No hay datos para exportar o hubo un erro con la conexión", "Fallo al exportar", "error");
                }else{
                    ExportJSON json_ctrl = new ExportJSON(exams);
                    String path = JOptionPane.showInputDialog("En que fichero quiere guardar guardar los datos");
                    if(json_ctrl.extractData(path)){
                        modal_message.showModal("Datos exportados correctamente a " + path, "Exportar a json", "succes");
                    }else{
                        modal_message.showModal("Ocurrio un error al intentar exportar los datos", "Fallo al exportar", "error");
                    }
                }
            }
        });
        
        this.xml_button = new JButton("exportar a XML");
        this.xml_button.setEnabled(false);
        this.xml_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<StudentModel> students = student_ctrl.getAll();
                ArrayList<ArrayList<LicenseModel>> licenses = new ArrayList<>();
                for(StudentModel student : students){
                    ArrayList<LicenseModel> license = license_ctrl.getLicensesOfStudent(student.getcCodAlu());
                    licenses.add(new ArrayList<>(license));
                }
                
                String path = JOptionPane.showInputDialog("En que fichero quiere guardar guardar los datos");
                ExportXML xml_ctrl = new ExportXML(students, licenses, path);
                if(xml_ctrl.extracXML()){
                    modal_message.showModal("Datos exportados correctamente a " + path, "Exportar a xml", "succes");
                }else{
                    modal_message.showModal("Ocurrio un error al intentar exportar los datos", "Fallo al exportar", "error");
                }
            }
        });

        export_container.add(json_button);
        export_container.add(xml_button);
        
        inputs_container.add(date_container);
        inputs_container.add(mark_container);
        inputs_container.add(update_exam);
        inputs_container.add(export_container);
        
        third_container.add(new JScrollPane(this.jtable_exams.getTable()), BorderLayout.CENTER);
        third_container.add(inputs_container);
    }
    
    public void fillLicenseTable(){
        ArrayList<LicenseModel> licenses = license_ctrl.getLicensesOfStudent(student_ctrl.getSelected().getcCodAlu());
                    
        jtable_licenses.getModel().setRowCount(0);

        for(LicenseModel license : licenses){
            jtable_licenses.addRow(student_ctrl.getSelected().getcCodAlu(), student_ctrl.getSelected().getcNomAlu(), license.getCodCurso(), course_ctrl.getCourseName(license.getCodCurso()), license.getNotaMedia());
        }
    }
    
    public void fillExamsTable(){
        ArrayList<ExamModel> exams = exam_ctrl.getAll(license_ctrl.getSelected().getCodAlu(), license_ctrl.getSelected().getCodCurso());
        
        jtable_exams.getModel().setRowCount(0);
        
        for(ExamModel exam : exams){
            jtable_exams.addRow(exam.getNumExa(), exam.getFecExam(), exam.getNotaExam());
        }
    }
}
