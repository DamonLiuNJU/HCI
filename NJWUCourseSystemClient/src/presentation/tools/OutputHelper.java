package presentation.tools;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OutputHelper {
	  /**
     * 向弹出对话框输出数据
     * 
     * @param s
     */
    public void outputToDialog(String s) {
        JOptionPane.showMessageDialog(null, s);
    }
    
    public int ouputToQuesDialog(String s){
    	return JOptionPane.showConfirmDialog(null, s, null, 0);
    }

    /**
     * 
     * @param content
     * @param columnTitle
     * @param table
     */
    public void outputToTable(Vector<Vector<String>> content, String[] columnTitle, JTable table) {
        DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
        tablemodel.setRowCount(0);// 清除原有行
        tablemodel.setColumnIdentifiers(columnTitle);
       for(Vector<String> line : content){
    	   tablemodel.addRow(line);
       }
        table.updateUI();
    }

    /**
     * 向一块有格式文本框输出数据,不同信息存于ArrayList的不同项
     * 
     * @param info
     * @param t
     */
    public void outputToTextFields(ArrayList<String> info, JTextField[] t) {
        for (int i = 0; i < info.size(); i++) {
            t[i].setText(info.get(i));
        }
    }
    
    /**
     * 向一文本区域输出数据,不同信息存于ArrayList的不同项
     * 
     * @param info
     * @param t
     */
    public void outputToATextArea(ArrayList<String> info, JTextArea t) {
    	StringBuilder sb=new StringBuilder();
    	for (int i = 0; i < info.size(); i++) {
            sb.append(info.get(i)+"\r\n");
        }
    	t.setText(sb.toString());
    }
}
