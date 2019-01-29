package model;

public class ParametarCustom extends Parametar{
	
	private String vrsta = "";
	private String ime = "";
	private String button1 = "";
	private String button2 = "";
	private String checkBoxIme = "";
	private String textFieldText = "";
	private int selected = -1;
	private boolean selectedBox = false;

	public ParametarCustom(Parametar parametar) {
		super(parametar);
		vrsta = ((ParametarCustom)parametar).getVrsta();
		ime = ((ParametarCustom)parametar).getIme();
		button1 = ((ParametarCustom)parametar).getButton1();
		button2 = ((ParametarCustom)parametar).getButton2();
		checkBoxIme = ((ParametarCustom)parametar).getCheckBoxIme();
		textFieldText = ((ParametarCustom)parametar).getTextFieldText();
		selected = ((ParametarCustom)parametar).getSelected();
		selectedBox = ((ParametarCustom)parametar).isSelectedBox();
	}

	public boolean isSelectedBox() {
		return selectedBox;
	}
	
	public void setSelectedBox(boolean selectedBox) {
		this.selectedBox = selectedBox;
	}
	
	public String getVrsta() {
		return vrsta;
	}
	
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	
	public ParametarCustom(String name, Node parent) {
		super(name,parent);
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getButton1() {
		return button1;
	}

	public void setButton1(String button1) {
		this.button1 = button1;
	}

	public String getButton2() {
		return button2;
	}

	public void setButton2(String button2) {
		this.button2 = button2;
	}

	public String getCheckBoxIme() {
		return checkBoxIme;
	}

	public void setCheckBoxIme(String checkBoxIme) {
		this.checkBoxIme = checkBoxIme;
	}

	public String getTextFieldText() {
		return textFieldText;
	}

	public void setTextFieldText(String textFieldText) {
		this.textFieldText = textFieldText;
	}

	public int getSelected() {
		return selected;
	}
	
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	public void ispis() {
		System.out.println("ParametarCustom [vrsta=" + vrsta + ", ime=" + ime + ", button1=" + button1 + ", button2=" + button2
				+ ", checkBoxIme=" + checkBoxIme + ", textFieldText=" + textFieldText + "]");
	}
	
}
