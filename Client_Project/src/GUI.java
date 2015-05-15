import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	
	///Fields, so that GUI Components can be used throughout the class
	private JList scrollList;
	private ArrayList<InventoryObject> database;
	private JTabbedPane tabs;
	private JPanel inventoryTab, checkOutTab, modInventoryTab, viewBooks;
	private DefaultListModel filter;
	private JTextField search, viewNum, viewPrice, viewISBN, viewRoom; 
	private JTextField modNum, modPrice, modISBN, modRoom; 

	private GUIHandler handler;

//----------------------------------------------------Program GUI-----------------------------------------------------\\
	public GUI() throws IOException{
		super("Book Inventory");
		
		handler = new GUIHandler();
		
		//Read the database
		FileReader fileReader = new FileReader("Database/database.txt");
		BufferedReader bufferReader = new BufferedReader(fileReader);
		database = new ArrayList<InventoryObject>();
        String line;
		while((line = bufferReader.readLine()) != null)
            database.add(new InventoryObject(line));
      
        bufferReader.close();

        //Initialize all the fields
        this.setLayout(new GridLayout(1, 2));
        filter = new DefaultListModel();
      
        for(InventoryObject i:database)
            filter.addElement(i);
        
		scrollList = new JList(filter);
		
		tabs = new JTabbedPane();
		inventoryTab = new JPanel(); viewBooks = new JPanel(); viewBooks = new JPanel();
		checkOutTab = new JPanel();
		modInventoryTab = new JPanel();
		search = new JTextField(); viewNum = new JTextField(4); viewPrice = new JTextField(4); 
		viewISBN = new JTextField(10); viewRoom = new JTextField(4);
		modNum = new JTextField(4); modPrice = new JTextField(4); 
		modISBN = new JTextField(10); modRoom = new JTextField(4);

		setInventoryTab();
		setModifyTab();
		add(viewBooks);
		add(tabs);

	}

	//Panel Setup
	public void setInventoryTab() {		
		JPanel viewData = new JPanel();
		JLabel label1 = new JLabel("# of Books: ");
		JLabel label2 = new JLabel("Stored in Room: ");
		JLabel label3 = new JLabel("Price: ");
		JLabel label4 = new JLabel("ISBN: ");

		//Adding selectionlisteners
		scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane list = new JScrollPane(scrollList, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Setting layout for the first tab with GridBagLayout
		viewBooks.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST; 

		c.fill = GridBagConstraints.HORIZONTAL;
		setGrid(c, 0, 1, 0, .01);
		
		//Adding Labels for search and JList
		search.getDocument().addDocumentListener(handler);
		viewBooks.add(search,c);
		setGrid(c, 0, 0, 0, 0);
		viewBooks.add(new JLabel("Search"),c);

		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);
		viewBooks.add(list, c);

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Uses GridBagLayout and GroupLayout		
		viewData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		viewData.add(panelTitle, c);
		
		viewNum.setEditable(false);
		viewRoom.setEditable(false);
		viewPrice.setEditable(false);
		viewISBN.setEditable(false);

		//GroupLayout to set the information
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3)
                .addComponent(label4))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(viewNum)
                .addComponent(viewRoom)
                .addComponent(viewPrice)
                .addComponent(viewISBN))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(viewNum))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(viewRoom))
            .addGroup(layout.createParallelGroup()
                .addComponent(label3)
                .addComponent(viewPrice))
            .addGroup(layout.createParallelGroup()
                    .addComponent(label4)
                    .addComponent(viewISBN))
        );

        //Add information to GUI and pad
		setGrid(c, 0, 1, 1, 0);
        viewData.add(information, c);
		setGrid(c, 0, 2, 0, 1);
		viewData.add(new JLabel(""), c);
		inventoryTab.setLayout(new GridLayout(1, 2));		
		inventoryTab.add(viewData);
		
		//Layouts for the other tabs WIP		
		tabs.addTab("Inventory", inventoryTab);
						
	}

	public void setModifyTab(){
		JPanel modData = new JPanel();
		JLabel label1 = new JLabel("# of Books: ");
		JLabel label2 = new JLabel("Stored in Room: ");
		JLabel label3 = new JLabel("Price: ");
		JLabel label4 = new JLabel("ISBN: ");
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		setGrid(c, 0, 2, 1, 1);

		//Display Information. The next blocks of code is just adding JLabels and JTextFields. Uses GridBagLayout and GroupLayout		
		modData.setLayout(new GridBagLayout());
		JLabel panelTitle = new JLabel(" Modify Book Information");
		panelTitle.setFont((new Font("", Font.PLAIN, 20)));
		setGrid(c, 0, 0, 0, 0);
		modData.add(panelTitle, c);
		
        modNum.addActionListener(handler);
		
		//GroupLayout to set the information
		JPanel information = new JPanel();
		GroupLayout layout = new GroupLayout(information);
		information.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3)
                .addComponent(label4))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(modNum)
                .addComponent(modRoom)
                .addComponent(modPrice)
                .addComponent(modISBN))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(label1)
                .addComponent(modNum))
            .addGroup(layout.createParallelGroup()
                .addComponent(label2)
                .addComponent(modRoom))
            .addGroup(layout.createParallelGroup()
                .addComponent(label3)
                .addComponent(modPrice))
            .addGroup(layout.createParallelGroup()
                    .addComponent(label4)
                    .addComponent(modISBN))
        );
                
        //Add information to GUI and pad
		setGrid(c, 0, 1, 1, 0);
        modData.add(information, c);
		setGrid(c, 0, 2, 0, 1);
		modData.add(new JLabel(""), c);
		modInventoryTab.setLayout(new GridLayout(1, 2));		
		modInventoryTab.add(modData);
		
		tabs.addTab("Modify Inventory Tab", modInventoryTab);
				
	}
	
	public void setGrid(GridBagConstraints c, int gridx, int gridy, double weightx, double weighty){
		c.weightx = weightx; c.weighty = weighty; c.gridx = gridx; c.gridy = gridy;
	}
		
	//Function searches through the database and adds them to the list model
	public void searchJList(){
        filter.clear();
		scrollList.removeListSelectionListener(handler);
        for(InventoryObject i : database)
			if(i.getName().toUpperCase().contains(search.getText().toUpperCase()))
				filter.addElement(i);
        refresh();
        scrollList.addListSelectionListener(handler);
		scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
	}
	
	public void refresh(){
		viewNum.setText(""); modNum.setText("");
		viewRoom.setText(""); modRoom.setText("");
		viewPrice.setText(""); modPrice.setText("");
		viewISBN.setText(""); modISBN.setText("");

	}
	
//-----------------------------------------Nested Class for Event Listeners-------------------------------------------\\

public class GUIHandler implements ActionListener, DocumentListener, ListSelectionListener{

	public void actionPerformed(ActionEvent event) {
		database.get(scrollList.getSelectedIndex()).setNum(modNum.getText());
		viewNum.setText(modNum.getText());
	}
	//Action listeners for the search bar
	public void changedUpdate(DocumentEvent event) {
		searchJList();
	}

	public void insertUpdate(DocumentEvent event) {
		searchJList();
	}

	public void removeUpdate(DocumentEvent event) {
		searchJList();
	}

	//Update the information panel on the right 
	public void valueChanged(ListSelectionEvent event) {
		if(!event.getValueIsAdjusting() && !scrollList.isSelectionEmpty()){
			viewNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			viewRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			viewPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			viewISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());

			modNum.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getNum());
			modRoom.setText(((InventoryObject) filter.elementAt(((scrollList.getSelectedIndex())))).getRoom());
			modPrice.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getPrice());
			modISBN.setText(((InventoryObject) filter.elementAt((scrollList.getSelectedIndex()))).getISBN());

		}
	}
 
}

}


