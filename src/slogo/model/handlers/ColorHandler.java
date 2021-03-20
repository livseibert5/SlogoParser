package slogo.model.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.model.Color;


public class ColorHandler {
  private final Map<Integer, Color> colorMap;

  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  private final ResourceBundle colors = ResourceBundle
      .getBundle(RESOURCE_FOLDER + "Colors");

  public ColorHandler() {
    colorMap = new HashMap<>();
    initializeColors();
  }

  private void initializeColors() {
    for(int i = 0; i < colors.keySet().size(); i++){
      String rgb = colors.getString(String.valueOf(i));
      Color color = new Color(makeColor(rgb.substring(0, 2)), makeColor(rgb.substring(2, 4)), makeColor(rgb.substring(4)));
      colorMap.put(i, color);
    }
  }

  private int makeColor(String hex){
    return Integer.parseInt(hex, 16);
  }

  /**
   * Adds a color and index to the color map
   *
   * @param index for the color to be mapped to
   * @param color specific color to be added
   */
  public void addColor(int index, Color color) {
    colorMap.put(index, color);
  }

  /**
   * Allows the frontend to access the color at the particular index.
   *
   * @param index index of color
   * @return color corresponding to the index
   */
  public Color getColor(double index) {return colorMap.get((int) index);}
}
