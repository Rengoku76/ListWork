package lab7;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class ListWork extends JFrame {
	JPanel panel1=null;
	private static Image image;
	static File f1;
	public static void main(String[] args) {
	ListWork window = new ListWork ("Работа со списком");
	window.setVisible(true);
	window.setMinimumSize(window.getSize());
}
	public ListWork(String s) {
		super(s);
		final DefaultListModel myListModel = new DefaultListModel();
		for (int i=0;i<10;i++) {
			myListModel.addElement(" "+i);
		}
		final JList myList = new JList();
		JScrollPane myScroll = new JScrollPane(myList);
		myList.setModel(myListModel);
	
		Box myBox1=new Box(BoxLayout.Y_AXIS);
		final JTextField myText=new JTextField();
		myBox1.add(myText);
		Box box1=new Box(BoxLayout.X_AXIS);
		JButton button1=new JButton("Добавить в список");
		box1.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			myListModel.addElement(myText.getText());
		}
	});
		JButton button2 = new JButton("Убрать из списка");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			while (myListModel.contains(myText.getText())) {
				myListModel.removeElement(myText.getText());
			}
			}
		});
		box1.add(button2);
		JButton buttonClear=new JButton("Очистить список");
		buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myListModel.clear();
			}
		});
		box1.add(buttonClear);
		myBox1.add(box1);
		add(myScroll,BorderLayout.CENTER);
		add(myBox1,BorderLayout.NORTH);
	

	Box myBox2 = new Box(BoxLayout.X_AXIS);
	JButton button3=new JButton("Сохранить...");
	myBox2.add(button3);
	final FileDialog fdlg = new FileDialog(this, "");
	
	button3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fdlg.setMode(FileDialog.SAVE);
			fdlg.setTitle("Сохранить файл");
			fdlg.setVisible(true);
			FileWriter myWriter = null;
			try {
				myWriter = new FileWriter(fdlg.getDirectory()+fdlg.getFile());
				BufferedWriter myBWriter=new BufferedWriter(myWriter);
				for(int i=0;i<myListModel.getSize();i++) {
					myBWriter.write(""+myListModel.getElementAt(i));
					myBWriter.newLine();
				}
				myBWriter.close();
				myWriter.close();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	});
	myBox2.add(Box.createHorizontalGlue());
	JButton button4=new JButton("Загрузить...");
	button4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fdlg.setMode(FileDialog.LOAD);
			fdlg.setTitle("Загрузить файл");
			fdlg.setVisible(true);
			FileReader myReader = null;
			try {
				myReader = new FileReader(fdlg.getDirectory()+fdlg.getFile());
				myListModel.clear();
				BufferedReader myBReader=new BufferedReader(myReader);
				String s;
				while ((s=myBReader.readLine())!=null) {
					myListModel.addElement(s);
			}
			myBReader.close();
			myReader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	});
	myBox2.add(button4);
	add(myBox2,BorderLayout.SOUTH);
}
		myList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			String path=myHash.get(myList.getSelectedValue().toString());
			loadImage(path);
			}
	});
panel1 = new JPanel();
Box centerBox = new Box(BoxLayout.X_AXIS);
centerVox.add(myScroll);
centerVox.add(panel1);
add(centerBox,BorderLayouut.CENTER);

public void loadImage(String path) {
	try {
		if (path!=null) {
			f1=new File(path);
			image=ImageIO.read(f1);
			g.setColor(panel1.getBackground());
			g.clearRect(0,0,panel1.getWidth(), panel1.getHeight());
			g.drawImage(image,0,0,null);
		}else throw new IOException();
		catch (IOExeption e1) {
			panel1.repaint();
	}
}
public void showDialog() {
	JDialog myDialog=new JDialog();
	myDialog.setModal(true);
	myDialog.add(new JLabel("No file!!!"));
	myDialog.pack();
	myDialog.setLocation(getLocation().x+getWidth()/2, getLocation().y+getHeight()/2);
	myDialog.setVisible(true);
}
public class SampleHash {
	public void main(String[] args) {
		HashMap<String, String> myHash=new HashMap<String, String>();
		myHash.put("Иванов", "891234567");
		myHash.put("Петров", "892233345");
		myHash.put("Сидоров", "893137567");
		System.out.println(myHash);
		System.out.println(myHash.get("Иванов"));
}
	private static HashMap<String, String> myHash=new HashMap<String, String>();
	static JPopupMenu myPopup;
	myPopup = new JPopupMenu();
	JMenuItem myItem1=new JMenuItem("Связать с картинкой");
	myItem1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			loadFromFile(myList.getSelectedValue().toString());
		}
	});
	myPopup.add(myItem1);
	myList.setComponentPopupMenu(my Popup);
	myList.addMouseListener(new MouseAdapter()){
		public void mousePressed(MouseEvent e)
		myList.setSelectedIndex(myList.locationToIndex(e.getPoint()));
	
});
public void loadFromFile(String s) {
	FileDialog fdlg=new FileDialog(this, "Загрузить картинку",FileDialog.LOAD);
	fdlg.setFile("*.jpg");
	fdlg.setVisible(true);
	myHash.put(s, fdlg.getDirectory()+fdlg.getFile());
	loadImage(fdlg.getDirectory()+fdlg.getFile())
}
