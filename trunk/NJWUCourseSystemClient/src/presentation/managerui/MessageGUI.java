package presentation.managerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import vo.managervo.MessageVO;
import businesslogic.managerbl.Faculty;


public class MessageGUI {
	String id;

	public static void main(String arg[]) {
		ArrayList<MessageVO> a = new Faculty().receiveMessage("10001");
		System.out.println(a.size());
	}

	public MessageGUI(String id) {
		this.id = id;
	}

	public void getMsgFrame() {

		JFrame msg = new JFrame("收件箱");
		msg.setBounds(500, 200, 400, 400);
		JPanel msgPanel = getMsgPanel(Color.black, Color.white);
		msgPanel.setSize(400, 400);
		// msgPanel.setBackground(new Color(0Xa89f69));
		// msgPanel.setBackground(new Color(0Xd8dbda));
		msg.add(msgPanel);
		msg.setVisible(true);
	}

	public JPanel getMsgPanel(Color foreColor, Color backColor) {
		JPanel msg = new JPanel();
		msg.setBackground(backColor);
		ArrayList<MessageVO> msgList = new Faculty().receiveMessage(id);
		// ArrayList<MessageVO> msgList=new ArrayList<MessageVO>();
		// msgList.add(new MessageVO("dean","llllllllllllllllllllllllllllll"));

		msg.setLayout(new MigLayout());

	//	JLabel msgLabel=new JLabel("新消息：");
	//	msgLabel.setFont(new Font("微软雅黑",1,13));
	//	msg.add(msgLabel,"wrap");
		for (int i = 0; i < msgList.size(); i++) {
			// System.out.println(msgList.get(i).getFrom_Name());
			msg.add(getButton(msgList.get(i).getContent(), msgList.get(i)
					.getFrom_Name(), foreColor), "wrap");
		}
		msg.setOpaque(false);	
		return msg;

	}

	// 消息框内的but
	public JButton getButton(final String content, final String from,
			Color txtColor) {
		JButton b = new JButton(getSubString(content, 0, 20) + "...");
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setForeground(txtColor);
		b.setFont(new Font("微软雅黑", 0, 12));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JMsgFrame("FROM:" + from + "\r\n" + content).run();

			}
		});
		return b;
	}

	class JMsgFrame {
		String content;
		Point origin;
		JFrame infoDialog;

		JMsgFrame(String content) {
			this.content = content;
			origin = new Point();
		}

		void run() {
			infoDialog = new JFrame();
			infoDialog.setUndecorated(true);
			infoDialog.setBounds(500, 300, 450, 200);
			JPanel infoPanel = new JPanel();
			infoPanel.setLayout(null);

			infoPanel.setBounds(0, 0, 450, 200);
			infoPanel.setBackground(new Color(0X8d8a78));
			JTextArea txt = new JTextArea(content, 20, 50);
			txt.setEditable(false);
			JScrollPane sp = new JScrollPane(txt);
			sp.setBounds(10, 10, 420, 180);
			infoPanel.add(sp);
			infoDialog.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					origin.x = e.getX();
					origin.y = e.getY();
				}
			});
			infoDialog.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Point p = infoDialog.getLocation();
					infoDialog.setLocation(p.x + e.getX() - origin.x,
							p.y + e.getY() - origin.y);
				}
			});

			JButton close = new JButton("X");
			close.setForeground(Color.BLACK);
			close.setFont(new Font("微软雅黑", 1, 15));

			close.setContentAreaFilled(false);
			close.setBorderPainted(false);
			close.setBounds(429, 0, 25, 25);
			close.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					infoDialog.dispose();
				}

			});
			infoPanel.add(close);
			infoDialog.add(infoPanel);
			infoDialog.setVisible(true);
		}
	}

	// 中文截取字符串
	public String getSubString(String str, int pstart, int pend) {

		pend = pend * 2;
		String resu = "";

		int beg = 0;
		int end = 0;
		int count1 = 0;

		char[] temp = new char[str.length()];
		str.getChars(0, str.length(), temp, 0);
		boolean[] bol = new boolean[str.length()];
		for (int i = 0; i < temp.length; i++) {
			bol[i] = false;
			if (temp[i] > 255) {// 说明是中文
				count1++;
				bol[i] = true;
			}
		}

		if (pstart > str.length() + count1) {
			resu = null;
		}

		if (pstart > pend) {
			resu = null;
		}

		if (pstart < 1) {
			beg = 0;
		} else {
			beg = pstart - 1;
		}

		if (pend > str.length() + count1) {
			end = str.length() + count1;
		} else {
			end = pend;// 在substring的末尾一样
		}
		// 下面开始求应该返回的字符串
		if (resu != null) {
			if (beg == end) {

				int count = 0;

				if (beg == 0) {

					if (bol[0] == true)

						resu = null;

					else

						resu = new String(temp, 0, 1);

				} else {

					int len = beg;// zheli

					for (int y = 0; y < len; y++) {// 表示他前面是否有中文,不管自己

						if (bol[y] == true)

							count++;

						len--;// 想明白为什么len--

					}

					// for循环运行完毕后，len的值就代表在正常字符串中，目标beg的上一字符的索引值

					if (count == 0) {// 说明前面没有中文

						if (temp[beg] > 255)// 说明自己是中文

							resu = null;// 返回空

						else

							resu = new String(temp, beg, 1);

					} else {// 前面有中文，那么一个中文应与2个字符相对

						if (temp[len + 1] > 255)// 说明自己是中文

							resu = null;// 返回空

						else

							resu = new String(temp, len + 1, 1);

					}

				}

			} else {// 下面是正常情况下的比较

				int temSt = beg;

				int temEd = end - 1;// 这里减掉一

				for (int i = 0; i < temSt; i++) {

					if (bol[i] == true)

						temSt--;

				}// 循环完毕后temSt表示前字符的正常索引

				for (int j = 0; j < temEd; j++) {

					if (bol[j] == true)

						temEd--;

				}// 循环完毕后temEd-1表示最后字符的正常索引

				if (bol[temSt] == true)// 说明是字符，说明索引本身是汉字的后半部分，那么应该是不能取的

				{

					int cont = 0;

					for (int i = 0; i <= temSt; i++) {

						cont++;

						if (bol[i] == true)

							cont++;

					}

					if (pstart == cont)// 是偶数不应包含,如果pstart<cont则要包含

						temSt++;// 从下一位开始

				}

				if (bol[temEd] == true) {// 因为temEd表示substring
											// 的最面参数，此处是一个汉字，下面要确定是否应该含这个汉字

					int cont = 0;

					for (int i = 0; i <= temEd; i++) {

						cont++;

						if (bol[i] == true)

							cont++;

					}

					if (pend < cont)// 是汉字的前半部分不应包含

						temEd--;// 所以只取到前一个

				}

				if (temSt == temEd) {

					resu = new String(temp, temSt, 1);

				} else if (temSt > temEd) {

					resu = null;

				} else {

					resu = str.substring(temSt, temEd + 1);

				}

			}

		}

		return resu;// 返回结果

	}

}
