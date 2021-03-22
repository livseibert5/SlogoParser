package slogo.model;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import slogo.controller.Controller;

/**
 * Reads user-defined variables and commands from XML files.
 *
 * @author Livia Seibert
 */
public class FromXML {

  private final Controller controller;
  private Element root;

  /**
   * Constructor for FromXML requires a controller so that it can write commands and variables to
   * their appropriate handlers.
   *
   * @param controller controller for game
   */
  public FromXML(Controller controller) {
    this.controller = controller;
  }

  /**
   * Reads variables and commands from the XML file into the game.
   *
   * @param filename file to read variables and commands from
   * @throws IOException                  issue reading from file
   * @throws SAXException                 issue with XML parser
   * @throws ParserConfigurationException issue with XML parser
   */
  public void readFile(String filename)
      throws IOException, SAXException, ParserConfigurationException {
    buildParser(filename);
    parseVariables();
    parseCommands();
  }

  /**
   * Constructs the Document to parse from.
   *
   * @param fileName file to parse
   * @throws ParserConfigurationException issue configuring XML parser
   * @throws SAXException                 issue with XML parser
   * @throws IOException                  issue reading from file
   */
  private void buildParser(String fileName)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(this.getClass().getClassLoader().getResourceAsStream(fileName));
    root = doc.getDocumentElement();
  }

  /**
   * Creates Variable objects from XML.
   */
  private void parseVariables() {
    NodeList variables = root.getElementsByTagName("Variable");
    for (int i = 0; i < variables.getLength(); i++) {
      Node currNode = variables.item(i);
      Element nodeElement = (Element) currNode;
      String name = nodeElement.getElementsByTagName("Name").item(0).getTextContent();
      double value = Double
          .parseDouble(nodeElement.getElementsByTagName("Value").item(0).getTextContent());
      controller.getVariableHandler().addVariable(new Variable(name, value));
    }
  }

  /**
   * Creates UserDefinedCommand objects from XML.
   */
  private void parseCommands() {
    NodeList commands = root.getElementsByTagName("Command");
    for (int i = 0; i < commands.getLength(); i++) {
      Node currNode = commands.item(i);
      Element nodeElement = (Element) currNode;
      String name = nodeElement.getElementsByTagName("Name").item(0).getTextContent();
      int numParameters = Integer
          .parseInt(nodeElement.getElementsByTagName("NumberParameters").item(0).getTextContent());
      String body = nodeElement.getElementsByTagName("CommandBody").item(0).getTextContent();
      String[] parameters = parseParameters(nodeElement);
      CommandBlock block = new CommandBlock(body);
      controller.getUserDefinedCommandHandler()
          .addCommand(new UserDefinedCommand(name, numParameters, parameters, block));
    }
  }

  /**
   * Determines parameters for UserDefinedCommand objects from the XML file.
   *
   * @param nodeElement element of current command entry
   * @return list of parameters
   */
  private String[] parseParameters(Element nodeElement) {
    Node parameterBlock = nodeElement.getElementsByTagName("Parameters").item(0);
    NodeList parameterItems = parameterBlock.getChildNodes();
    String[] parameters = new String[parameterItems.getLength()];
    for (int i = 0; i < parameterItems.getLength(); i++) {
      parameters[i] = parameterItems.item(i).getTextContent();
    }
    return parameters;
  }
}
