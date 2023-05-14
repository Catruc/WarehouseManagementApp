package BusinessLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public abstract class TableUpgrade {

    public <T> void updateTable(List<T> items, String[] columnNames, JTable table) {
        Object[][] data = new Object[items.size()][columnNames.length];
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            for (int j = 0; j < columnNames.length; j++) {
                String columnName = columnNames[j];
                Object value = null;
                try {
                    String getterMethodName = "get" + columnName;
                    Method getter = item.getClass().getMethod(getterMethodName);
                    value = getter.invoke(item);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                data[i][j] = value;
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }



}
