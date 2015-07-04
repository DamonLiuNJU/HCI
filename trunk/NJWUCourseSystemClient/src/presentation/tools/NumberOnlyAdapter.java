package presentation.tools;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberOnlyAdapter extends KeyAdapter {
	public NumberOnlyAdapter(){
		super();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {  
        int keyChar = e.getKeyChar();                 
        if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
              
        }else{  
            e.consume(); //关键，屏蔽掉非法输入  
        }  
    }  
}
