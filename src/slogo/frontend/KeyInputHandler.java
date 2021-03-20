package slogo.frontend;

import slogo.Observable;

/**
 * Class for sending changes from front-end to back end.
 *
 * @author Jessica Yang
 */
public class KeyInputHandler extends Observable<Object> {

  public KeyInputHandler() {
  }

  public void pressL() {
    notifyListeners("frontEndOrientation", 0, 1.0);
  }

  public void pressR() {
    notifyListeners("frontEndOrientation", 0, -1.0);
  }

  public void pressW() {
    notifyListeners("backEndYCoordinate", 0, 1.0);
  }

  public void pressS() {
    notifyListeners("backEndYCoordinate", 0, -1.0);
  }

  public void pressA() {
    notifyListeners("backEndXCoordinate", 0, -1.0);
  }

  public void pressD() {
    notifyListeners("backEndXCoordinate", 0, 1.0);
  }
}
