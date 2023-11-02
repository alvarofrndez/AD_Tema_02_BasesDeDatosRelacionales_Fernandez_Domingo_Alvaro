/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import models.LicenseModel;
import models.StudentModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author alvaro
 */
public class ExportXML {
    private Transformer former;
    private DocumentBuilder builder;
    private DocumentBuilderFactory factory;
    
    private ArrayList<StudentModel> students;
    private ArrayList<ArrayList<LicenseModel>> licenses;
    private String path;
    
    public ExportXML(ArrayList<StudentModel> students, ArrayList<ArrayList<LicenseModel>> licenses, String path){
        this.students = students;
        this.licenses = licenses;
        this.path = path;
    }
    
    public Boolean extracXML(){
        try {
            this.former = TransformerFactory.newInstance().newTransformer();
            this.former.setOutputProperty(OutputKeys.INDENT, "yes");
            this.former.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            this.factory = DocumentBuilderFactory.newInstance();
            this.builder = this.factory.newDocumentBuilder();
            
            Document doc = this.builder.newDocument();
            
            Element root = doc.createElement("alumnos");
            doc.appendChild(root);
            
            for(int i=0; i < students.size(); i++){
                Element student = doc.createElement("alumno");
                root.appendChild(student);
                
                Element cod_alu = doc.createElement("codalu");
                cod_alu.appendChild(doc.createTextNode(students.get(i).getcCodAlu()));
                student.appendChild(cod_alu);
                
                Element nom_alu = doc.createElement("nomalu");
                nom_alu.appendChild(doc.createTextNode(students.get(i).getcNomAlu()));
                student.appendChild(nom_alu);
                
                Element licenses_elements = doc.createElement("matriculas");
                student.appendChild(licenses_elements);
                
                for(LicenseModel license : licenses.get(i)){
                    Element license_element = doc.createElement("matricula");
                    licenses_elements.appendChild(license_element);
                    
                    Element cod_curso = doc.createElement("codcurso");
                    cod_curso.appendChild(doc.createTextNode(license.getCodCurso()));
                    license_element.appendChild(cod_curso);
                    
                    Element avg_mark = doc.createElement("notamedia");
                    avg_mark.appendChild(doc.createTextNode(String.valueOf(license.getNotaMedia())));
                    license_element.appendChild(avg_mark);
                }
            }
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path + ".xml"));
            this.former.transform(source, result);
            return true;
        } catch (TransformerConfigurationException | ParserConfigurationException ex) {
            return false;
        }catch(TransformerException e){
            return false;
        }
    }
}
