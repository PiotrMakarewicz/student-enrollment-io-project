package pl.edu.agh.niebieskiekotki.utility;

import java.io.File;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pl.edu.agh.niebieskiekotki.DataBaseMock;
import pl.edu.agh.niebieskiekotki.entitites.Questionnaire;
import pl.edu.agh.niebieskiekotki.entitites.Student;
import pl.edu.agh.niebieskiekotki.entitites.Term;
import pl.edu.agh.niebieskiekotki.views.QuestionnaireResults;

public class FileCreator {

    private static Element createStudent(Student student, List<Term> terms, Document document) {
        // child element
        Element student_el = document.createElement("student");

        // firstname Element
        Element firstName = document.createElement("firstName");
        firstName.appendChild(document.createTextNode(student.getFirstName()));
        student_el.appendChild(firstName);

        // lastName element
        Element lastName = document.createElement("lastName");
        lastName.appendChild(document.createTextNode(student.getLastName()));
        student_el.appendChild(lastName);

        // email element
        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(student.getEmailAddress()));
        student_el.appendChild(email);

        // index
        Element index = document.createElement("index");
        index.appendChild(document.createTextNode(String.valueOf(student.getIndexNumber())));
        student_el.appendChild(index);

        //choices
        Element choices = document.createElement("choices");
        student_el.appendChild(choices);
        for (Term term : terms) {
            Element choice = document.createElement("term");
            choice.appendChild(document.createTextNode(term.toString()));
            choices.appendChild(choice);
        }

        return student_el;
    }

    public static void createFileWithPreferences(Questionnaire questionnaire) throws ParserConfigurationException, TransformerException {
        
        QuestionnaireResults results = new QuestionnaireResults(DataBaseMock.votes, questionnaire);
        
        // 1.Create a DocumentBuilder instance
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbuilder = dbFactory.newDocumentBuilder();

        // 2. Create a Document from the above DocumentBuilder.
        Document document = dbuilder.newDocument();

        // 3. Create the elements you want using the Element class and its appendChild method.

        // root element
        Element students = document.createElement("students");
        document.appendChild(students);

        for (QuestionnaireResults.QuestionnaireResultsRow row : results.getRows()) {
            Element student_el = createStudent(row.getStudent(), row.getTermList(), document);
            students.appendChild(student_el);
        }
        
        // write content into xml file

        // 4. Create a new Transformer instance and a new DOMSource instance.
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        // 5. Create a new StreamResult to the output stream you want to use.
        Random random = new Random();
        int randomNumber = random.nextInt(100000000 - 10000000) + 10000000;
        StreamResult result = new StreamResult(new File("src/main/resources/questionnaire-results/questionnaire-"
                + questionnaire.getId() + "-" + randomNumber + ".xml"));
        // StreamResult result = new StreamResult(System.out); // to print on console

        // 6. Use transform method to write the DOM object to the output stream.
        transformer.transform(source, result);

        System.out.println("File created successfully");
    }

}
