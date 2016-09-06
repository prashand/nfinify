/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nfinity.server.ui;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Prashan
 */
public class ProgressRenderer extends JProgressBar implements TableCellRenderer{

    public ProgressRenderer(){
        setMaximum(100);
        setStringPainted(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(value==null){
            setValue(0);
            setString("0%");
        }else{
            Integer val = (Integer)value;
            setValue(val);
            setString(val+"%");
        }
        return this;
    }
    
}
