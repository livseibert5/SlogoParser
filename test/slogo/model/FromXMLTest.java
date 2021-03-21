package slogo.model;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import slogo.controller.Controller;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for FromXML class.
 *
 * @author Livia Seibert
 */
public class FromXMLTest {

  private FromXML fromXML;
  private Controller controller;

  @BeforeEach
  void setUp() {
    controller = new Controller();
    fromXML = new FromXML(controller);
  }

  @Test
  void parseStandardFile() throws ParserConfigurationException, SAXException, IOException {
    fromXML.readFile("03_21_21_12_07.xml");
    assertNotNull(controller.getVariableHandler().getVariableWithName(":x"));
    assertNotNull(controller.getVariableHandler().getVariableWithName(":y"));
    assertNotNull(controller.getUserDefinedCommandHandler().getCommand("move"));
  }
}
