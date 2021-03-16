package slogo.model;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;

/**
 * Tests for ToXML class.
 *
 * @author Livia Seibert
 */
public class ToXMLTest {

  private ToXML toXML;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    toXML = new ToXML(controller);
  }

  @Test
  void writeVariables() throws TransformerException, ParserConfigurationException {
    controller.getVariableHandler().addVariable(new Variable(":x", 5));
    controller.getVariableHandler().addVariable(new Variable(":y", 10));
    toXML.exportToXML();
  }

  @Test
  void writeCommands() throws TransformerException, ParserConfigurationException {
    CommandBlock block = new CommandBlock("fd 50");
    UserDefinedCommand command = new UserDefinedCommand("move", 2, new String[]{"sum", "difference"}, block);
    controller.getUserDefinedCommandHandler().addCommand(command);
    toXML.exportToXML();
  }

  @Test
  void writeVariablesAndCommands() throws TransformerException, ParserConfigurationException {
    controller.getVariableHandler().addVariable(new Variable(":x", 5));
    controller.getVariableHandler().addVariable(new Variable(":y", 10));
    CommandBlock block = new CommandBlock("fd 50");
    UserDefinedCommand command = new UserDefinedCommand("move", 2, new String[]{"sum", "difference"}, block);
    controller.getUserDefinedCommandHandler().addCommand(command);
    toXML.exportToXML();
  }
}
