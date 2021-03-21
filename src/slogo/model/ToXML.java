package slogo.model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import slogo.controller.Controller;
import java.util.List;

/**
 * Exports current user-defined variables and commands to XML file.
 *
 * @author Livia Seibert
 */
public class ToXML {

  private final Controller controller;
  private Document doc;
  private Element variableElement;
  private Element functionElement;

  /**
   * ToXML constructor requires access to the controller to retrieve user-defined variables and
   * commands.
   *
   * @param controller controller for game
   */
  public ToXML(Controller controller) {
    this.controller = controller;
  }

  /**
   * Button click on front end triggers this function, which writes the current user-defined
   * variables and commands to an XML file.
   *
   * @throws ParserConfigurationException issue configuring XML parser
   * @throws TransformerException issue writing to XML file
   */
  public void exportToXML() throws ParserConfigurationException, TransformerException {
    initializeDocumentBuilder();
    createDocument();
    exportNewFile();
  }

  /**
   * Creates DocumentBuilder and DocumentBuilderFactory to create new XML doc.
   *
   * @throws ParserConfigurationException issue with DocumentBuilder creation
   */
  private void initializeDocumentBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    doc = dBuilder.newDocument();
  }

  /**
   * Builds the new XML file.
   */
  private void createDocument() {
    Element rootElement = doc.createElement("UserDefinedData");
    doc.appendChild(rootElement);
    variableElement = doc.createElement("Variables");
    rootElement.appendChild(variableElement);
    createVariables();
    functionElement = doc.createElement("Commands");
    rootElement.appendChild(functionElement);
    createFunctions();
  }

  /**
   * Writes the user defined variables to the file.
   */
  private void createVariables() {
    List<Variable> variables = controller.getVariableHandler().getAllVariables();
    variables.forEach(variable -> {
      Element variableItem = doc.createElement("Variable");
      variableElement.appendChild(variableItem);
      Element name = doc.createElement("Name");
      name.appendChild(doc.createTextNode(variable.getName()));
      variableItem.appendChild(name);
      Element value = doc.createElement("Value");
      value.appendChild(doc.createTextNode(Double.toString(variable.getValue())));
      variableItem.appendChild(value);
    });
  }

  /**
   * Writes the user defined commands to the file.
   */
  private void createFunctions() {
    List<UserDefinedCommand> commands = controller.getUserDefinedCommandHandler().getAllCommands();
    commands.forEach(command -> {
      Element commandItem = doc.createElement("Command");
      functionElement.appendChild(commandItem);
      Element name = doc.createElement("Name");
      name.appendChild(doc.createTextNode(command.getCommandName()));
      commandItem.appendChild(name);
      Element numParameters = doc.createElement("NumberParameters");
      numParameters
          .appendChild(doc.createTextNode(Integer.toString(command.getNumberParameters())));
      commandItem.appendChild(numParameters);
      Element parameters = doc.createElement("Parameters");
      createParameters(command, parameters);
      commandItem.appendChild(parameters);
      Element body = doc.createElement("CommandBody");
      body.appendChild(doc.createTextNode(command.getBody().toString()));
      commandItem.appendChild(body);
    });
  }

  /**
   * Writes the parameters for user defined commands to the file.
   *
   * @param command    command whose parameters should be written to the file
   * @param parameters parameters parent element
   */
  private void createParameters(UserDefinedCommand command, Element parameters) {
    String[] parametersList = command.getParameters();
    Arrays.asList(parametersList).forEach(item -> {
      Element parameter = doc.createElement("Parameter");
      parameter.appendChild(doc.createTextNode(item));
      parameters.appendChild(parameter);
    });
  }

  /**
   * Creates a file name for the new XML file from the current date and time.
   *
   * @return name for new XML file
   */
  private String createFileName() {
    String pattern = "MM_dd_yy_HH_mm";
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    Date today = Calendar.getInstance().getTime();
    return dateFormat.format(today);
  }

  /**
   * Writes new XML document to file.
   *
   * @throws TransformerException issue writing to XML file
   */
  private void exportNewFile() throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("data/" + createFileName() + ".xml"));
    transformer.transform(source, result);
  }
}
