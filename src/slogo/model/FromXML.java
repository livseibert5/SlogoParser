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

public class FromXML {

  private Controller controller;
  private Element root;

  public FromXML(Controller controller) {
    this.controller = controller;
  }

  public void readFile(String filename)
      throws IOException, SAXException, ParserConfigurationException {
    buildParser(filename);
    parseVariables();
    parseCommands();
  }

  private void buildParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(this.getClass().getClassLoader().getResourceAsStream(fileName));
    root = doc.getDocumentElement();
  }

  private void parseVariables() {
    NodeList variables = root.getElementsByTagName("Variable");
    for (int i = 0; i < variables.getLength(); i++) {
      Node currNode = variables.item(i);
      Element nodeElement = (Element) currNode;
      String name = nodeElement.getElementsByTagName("Name").item(0).getTextContent();
      Double value = Double.parseDouble(nodeElement.getElementsByTagName("Value").item(0).getTextContent());
      controller.getVariableHandler().addVariable(new Variable(name, value));
    }
  }

  private void parseCommands() {
    NodeList commands = root.getElementsByTagName("Command");
    for (int i = 0; i < commands.getLength(); i++) {
      Node currNode = commands.item(i);
      Element nodeElement = (Element) currNode;
      String name = nodeElement.getElementsByTagName("Name").item(0).getTextContent();
      int numParameters = Integer.parseInt(nodeElement.getElementsByTagName("NumberParameters").item(0).getTextContent());
      String body = nodeElement.getElementsByTagName("CommandBody").item(0).getTextContent();
      String[] parameters = parseParameters(nodeElement);
      CommandBlock block = new CommandBlock(body);
      controller.getUserDefinedCommandHandler().addCommand(new UserDefinedCommand(name, numParameters, parameters, block));
    }
  }

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
