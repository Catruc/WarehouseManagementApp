package BusinessLogic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

public abstract class TableUpgrade {


    public <T> String[] getHeader(List<T> list){
        T t = list.get(0);
        Field[] fields = t.getClass().getDeclaredFields();
        String[] header = new String[fields.length];
        int index = 0;
        for(Field field: fields){
            header[index] = field.getName();
            index++;
        }
        return header;

    }

    public <T> Object[][] getContent(List<T> list){
        if (list == null || list.isEmpty()) {
            return new Object[0][0];
        }

        T t = list.get(0);
        Field[] fields = t.getClass().getDeclaredFields();

        Object[][] content = new Object[list.size()][fields.length];

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                try {
                    Object value = field.get(item);
                    content[i][j] = value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return content;
    }

}
