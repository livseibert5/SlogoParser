package slogo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles listeners for observable pattern. Based off of Duvall's Observable lab example.
 *
 * @author Jessica Yang, Robert C. Duvall
 */
public class Observable<T> {
  private final List<PropertyChangeListener> myListeners;

  public Observable() {
    myListeners = new ArrayList<>();
  }

  /**
   * Used by other classes to add their own listeners to the Observable subclass.
   *
   * @param newListeners list of listeners
   */
  public void addMultipleListeners(List<PropertyChangeListener> newListeners) {
    for (PropertyChangeListener l : newListeners) {
      if (l != null) {
        myListeners.add(l);
      }
    }
  }

  /**
   * Userd by other classes to add a single listner to the Observable subclass.
   *
   * @param newListener propertychangelistener
   */
  public void addListener(PropertyChangeListener newListener) {
    if (newListener != null) {
      myListeners.add(newListener);
    }
  }

  /**
   * Used by subclasses to notify listeners of property changes.
   *
   * @param property to be updated in listeners
   * @param oldValue previous value of property
   * @param newValue new value of property
   */
  protected void notifyListeners(String property, T oldValue, T newValue) {
    for (PropertyChangeListener l : myListeners) {
      l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }
}
