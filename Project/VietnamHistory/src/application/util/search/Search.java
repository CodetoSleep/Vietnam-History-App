package application.util.search;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;

import model.thoiky.ThoiKy;
import model.nhanvatlichsu.NhanVatLichSu;
import model.nhanvatlichsu.Vua;

public class Search<T> { // Generic class with type parameter <T>
	public ObservableList<T> searchList(ObservableList<T> data, TextField textField, Class<T> type) {
		// searchList method takes three parameters: 
		// + data (the ObservableList to be searched)
		// + textField (the TextField that contains the filter text)
		// + type (the class object representing the type of objects in the ObservableList).
	
		// Create a FilteredList using the data ObservableList and a predicate that initially matches all elements. 
        FilteredList<T> filteredData = new FilteredList<>(data, b -> true); 
        
        // Add a listener to the textField's textProperty to react to changes in the filter text.
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
        	
        	// Define filtering logic
            filteredData.setPredicate(obj -> {
            	
            	// filter text is null or empty, all elements are considered a match and true.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // otherwise, the filter text is transformed to lowercase and trimmed
                String lowerCaseFilter = newValue.toLowerCase().trim();
                
                // Compare first name and last name of every person with filter text.
                Method[] methods = type.getMethods();
                for (Method m : methods) {
                	if (m.getName().startsWith("get")) {
                        try {
                            if (m.getReturnType() == String.class) {
                                try {
                                    String objField;
                                    objField = (String) m.invoke(obj);
                                    if (objField == null) {
                                        continue;
                                    } else if (objField.toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                        return true;
                                    } else {
                                        continue;
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            } else if (m.getReturnType() == ArrayList.class && type == NhanVatLichSu.class) {
                                try {
                                    ArrayList<ThoiKy> objField;
                                    objField = (ArrayList<ThoiKy>) m.invoke(obj);
                                    for (int i = 0; i < objField.size(); i++) {
                                        if (objField.get(i).getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                            return true;
                                        } else {
                                            continue;
                                        }
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            } else if (m.getReturnType() == LinkedList.class && type == ThoiKy.class) {
                                try {
                                    LinkedList<Vua> objField;
                                    objField = (LinkedList<Vua>) m.invoke(obj);
                                    for (int i = 0; i < objField.size(); i++) {
                                        if (objField.get(i).getTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                            return true;
                                        } else {
                                            continue;
                                        }
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (IllegalArgumentException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                continue;
                            }
                        } catch (ArithmeticException e) {
                            System.out.println("Error!");
                        }
                    }
                }
                return false;
            });
        });
        return filteredData;
    }
}

