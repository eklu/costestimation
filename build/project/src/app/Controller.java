package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;

public class Controller implements Initializable {
	private String selectedFKey, selectedCKey, selectedBKey, selectedSKey;
	private ObservableList<String> fOList, cOList, bOList, sOList;

	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// init
		ArrayList<Integer> list = new ArrayList<Integer>(
				DataUtil.sizeFactorMap.keySet());
		Collections.sort(list);
		ObservableList<Integer> sizeList = FXCollections.observableList(list);
		fSize.setItems(sizeList);
		cSize.setItems(sizeList);
		bTopSize.setItems(sizeList);
		bTopSupportSize.setItems(sizeList);
		bBottomSize.setItems(sizeList);
		sXSize.setItems(sizeList);
		sYSize.setItems(sizeList);
		
		fOList = FXCollections.observableList(new ArrayList<String>());
		fList.setItems(fOList);
		
		cOList = FXCollections.observableList(new ArrayList<String>());
		cList.setItems(cOList);
		
		bOList = FXCollections.observableList(new ArrayList<String>());
		bList.setItems(bOList);
		
		sOList = FXCollections.observableList(new ArrayList<String>());
		sList.setItems(sOList);
		
		

		fList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> ov,
							String old_val, String new_val) {
						System.out.println(old_val);
						System.out.println(new_val);
						System.out.println("");
						fListSelect(new_val);
					}
				});

		cList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> ov,
							String old_val, String new_val) {
						cListSelect(new_val);
					}
				});

		bList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> ov,
							String old_val, String new_val) {
						bListSelect(new_val);
					}
				});

		sList.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> ov,
							String old_val, String new_val) {
						sListSelect(new_val);
					}
				});

	}

	// FOUNDATION

	@FXML
	private ListView<String> fList;

	// outputs
	@FXML
	private Label fVolCost;
	@FXML
	private Label fReCost;
	@FXML
	private Label fFormCost;
	@FXML
	private Label fTotalCost;

	// inputs
	@FXML
	private TextField fBase;
	@FXML
	private TextField fDepth;
	@FXML
	private ChoiceBox<Integer> fSize;
	@FXML
	private TextField fSpacing;
	@FXML
	private TextField fLengthOfBar;
	@FXML
	private TextField fQuantity;

	// button
	@FXML
	private Button fCalcButton;
	@FXML
	private Button fDelBtn;
	@FXML
	private Button fNewBtn;

	// actions
	@FXML
	private void calcFoundation(ActionEvent e) {
		if (fCalcButton.getText().equals("Re-Calculate")){
			DataUtil.foundations.remove(selectedFKey);
			fOList.remove(selectedFKey);
		}
		Foundation f = new Foundation(Integer.parseInt(fQuantity.getText()),
				Integer.parseInt(fBase.getText()), Integer.parseInt(fDepth
						.getText()), fSize.getSelectionModel()
						.getSelectedItem(),
				Integer.parseInt(fSpacing.getText()),
				Integer.parseInt(fLengthOfBar.getText()));
 
		fVolCost.setText("" + f.getVolCost());
		fReCost.setText("" + f.getReCost());
		fFormCost.setText("" + f.getFormCost());

		String key = f.getQuantity() + " - " + f.getTotalCost();
		DataUtil.foundations.put(key, f);
		
		fOList.add(key);
		
		fTotalCost.setText("" + DataUtil.getTotalFCost());
		updateSummary();

		resetF();
	}

	@FXML
	private void delF(ActionEvent e){
		DataUtil.foundations.remove(selectedFKey);
		fOList.remove(selectedFKey);
		resetF();
	}
	
	@FXML
	private void newF(ActionEvent e){
		resetF();
	}
		
	private void fListSelect(String selected) {
		if (DataUtil.foundations.isEmpty())
			return;
		
		if(!fList.isPressed())
			return;
		
		selectedFKey = selected;
		Foundation f = DataUtil.foundations.get(selectedFKey);
		
		fNewBtn.setVisible(true);
		fDelBtn.setVisible(true);

		fVolCost.setText("" + f.getVolCost());
		fReCost.setText("" + f.getReCost());
		fFormCost.setText("" + f.getFormCost());

		fQuantity.setText("" + f.getQuantity());
		fBase.setText("" + f.getBase());
		fDepth.setText("" + f.getDepth());
		fSize.getSelectionModel().select(new Integer(f.getSize()));
		fSpacing.setText("" + f.getSpacing());
		fLengthOfBar.setText("" + f.getLengthOfBar());

		fCalcButton.setText("Re-Calculate");
		fCalcButton.setEffect(new ColorAdjust(-0.351, 0, 0, 0));
		
		fList.getSelectionModel().clearSelection();
	}

	// COLUMNS

	@FXML
	private ListView<String> cList;

	// outputs
	@FXML
	private Label cVolCost;
	@FXML
	private Label cReCost;
	@FXML
	private Label cFormCost;
	@FXML
	private Label cTotalCost;

	// inputs
	@FXML
	private TextField cHeight;
	@FXML
	private TextField cNumOfBars;
	@FXML
	private ChoiceBox<Integer> cSize;
	@FXML
	private TextField cDiameter;
	@FXML
	private TextField cWidth;
	@FXML
	private TextField cLength;
	@FXML
	private TextField cQuantity;

	@FXML
	private RadioButton cCirc;
	@FXML
	private RadioButton cRect;

	// button
	@FXML
	private Button cCalcButton;
	@FXML
	private Button cDelBtn;
	@FXML
	private Button cNewBtn;

	// labels
	@FXML
	private Label cDiameterLabel;
	@FXML
	private Label cWidthLabel;
	@FXML
	private Label cLengthLabel;

	// actions
	@FXML
	private void calcColumns(ActionEvent e) {
		if (cCalcButton.getText().equals("Re-Calculate")){
			DataUtil.columns.remove(selectedCKey);
			cOList.remove(selectedCKey);
		}
		
		Column c = new Column(Integer.parseInt(cQuantity.getText()),
				Integer.parseInt(cHeight.getText()),
				Integer.parseInt(cNumOfBars.getText()), cSize
						.getSelectionModel().getSelectedItem(),
				cCirc.isSelected() ? Integer.parseInt(cDiameter.getText()) : 0,
				cRect.isSelected() ? Integer.parseInt(cWidth.getText()) : 0,
				cRect.isSelected() ? Integer.parseInt(cLength.getText()) : 0,
				cCirc.isSelected());

		cVolCost.setText("" + c.getVolCost());
		cReCost.setText("" + c.getReCost());
		cFormCost.setText("" + c.getFormCost());

		String key = c.getQuantity() + " - " + c.getTotalCost();
		DataUtil.columns.put(key, c);
		
		cOList.add(key);
		
		cTotalCost.setText("" + DataUtil.getTotalCCost());

		updateSummary();

		resetC();
	}

	@FXML
	private void delC(ActionEvent e){
		DataUtil.columns.remove(selectedCKey);
		cOList.remove(selectedCKey);
		resetC();
	}
	
	@FXML
	private void newC(ActionEvent e){
		resetC();
	}

	@FXML
	private void selectCircular(ActionEvent e) {
		cDiameter.setVisible(true);
		cDiameterLabel.setVisible(true);
		cWidth.setVisible(false);
		cWidthLabel.setVisible(false);
		cLength.setVisible(false);
		cLengthLabel.setVisible(false);
	}

	@FXML
	private void selectRectangular(ActionEvent e) {
		cDiameter.setVisible(false);
		cDiameterLabel.setVisible(false);
		cWidth.setVisible(true);
		cWidthLabel.setVisible(true);
		cLength.setVisible(true);
		cLengthLabel.setVisible(true);
	}

	private void cListSelect(String selected) {
		if (DataUtil.columns.isEmpty())
			return;

		selectedCKey = selected;
		Column c = DataUtil.columns.get(selectedCKey);
		
		cNewBtn.setVisible(true);
		cDelBtn.setVisible(true);

		cVolCost.setText("" + c.getVolCost());
		cReCost.setText("" + c.getReCost());
		cFormCost.setText("" + c.getFormCost());

		cQuantity.setText("" + c.getQuantity());
		cHeight.setText("" + c.getHeight());
		cNumOfBars.setText("" + c.getNumOfBars());
		cSize.getSelectionModel().select(new Integer(c.getSize()));

		if (c.isCircular()) {
			cCirc.setSelected(true);
			selectCircular(new ActionEvent());
			cDiameter.setText("" + c.getDiameter());
		} else {
			cRect.setSelected(true);
			cWidth.setText("" + c.getWidth());
			selectRectangular(new ActionEvent());
			cLength.setText("" + c.getLength());
		}

		cCalcButton.setText("Re-Calculate");
		cCalcButton.setEffect(new ColorAdjust(-0.351, 0, 0, 0));
	}

	// BEAMS
	@FXML
	private ListView<String> bList;

	// outputs
	@FXML
	private Label bVolCost;
	@FXML
	private Label bReCost;
	@FXML
	private Label bFormCost;
	@FXML
	private Label bTotalCost;

	// inputs
	@FXML
	private TextField bX;
	@FXML
	private TextField bY;
	@FXML
	private TextField bLength;
	@FXML
	private ChoiceBox<Integer> bTopSize;
	@FXML
	private ChoiceBox<Integer> bTopSupportSize;
	@FXML
	private ChoiceBox<Integer> bBottomSize;
	@FXML
	private TextField bTopLength;
	@FXML
	private TextField bTopSupportLength;
	@FXML
	private TextField bBottomLength;
	@FXML
	private TextField bTopNumOfBars;
	@FXML
	private TextField bTopSupportNumOfBars;
	@FXML
	private TextField bBottomNumOfBars;
	@FXML
	private TextField bQuantity;

	// button
	@FXML
	private Button bCalcButton;
	@FXML
	private Button bDelBtn;
	@FXML
	private Button bNewBtn;

	// actions
	@FXML
	private void calcBeams(ActionEvent e) {
		if (bCalcButton.getText().equals("Re-Calculate")){
			DataUtil.beams.remove(selectedBKey);
			bOList.remove(selectedBKey);
		}
		
		Beam b = new Beam(Integer.parseInt(bQuantity.getText()),
				Integer.parseInt(bX.getText()), Integer.parseInt(bY.getText()),
				Integer.parseInt(bLength.getText()), bTopSize
						.getSelectionModel().getSelectedItem(), bTopSupportSize
						.getSelectionModel().getSelectedItem(), bBottomSize
						.getSelectionModel().getSelectedItem(),
				Integer.parseInt(bTopLength.getText()),
				Integer.parseInt(bTopSupportLength.getText()),
				Integer.parseInt(bBottomLength.getText()),
				Integer.parseInt(bTopNumOfBars.getText()),
				Integer.parseInt(bTopSupportNumOfBars.getText()),
				Integer.parseInt(bBottomNumOfBars.getText()));

		bVolCost.setText("" + b.getVolCost());
		bReCost.setText("" + b.getReCost());
		bFormCost.setText("" + b.getFormCost());

		String key = b.getQuantity() + " - " + b.getTotalCost();
		DataUtil.beams.put(key, b);
		bOList.add(key);
		
		bTotalCost.setText("" + DataUtil.getTotalBCost());

		updateSummary();

		resetB();
	}

	@FXML
	private void delB(ActionEvent e){
		DataUtil.beams.remove(selectedBKey);
		bOList.remove(selectedBKey);
		resetB();
	}
	
	@FXML
	private void newB(ActionEvent e){
		resetB();
	}
	
	private void bListSelect(String selected) {
		if (DataUtil.beams.isEmpty())
			return;

		selectedBKey = selected;
		Beam b = DataUtil.beams.get(selectedBKey);
		bNewBtn.setVisible(true);
		bDelBtn.setVisible(true);

		bVolCost.setText("" + b.getVolCost());
		bReCost.setText("" + b.getReCost());
		bFormCost.setText("" + b.getFormCost());

		bQuantity.setText("" + b.getQuantity());
		bX.setText("" + b.getX());
		bY.setText("" + b.getY());
		bLength.setText("" + b.getLength());
		bTopSize.getSelectionModel().select(new Integer(b.getTopSize()));
		bTopSupportSize.getSelectionModel().select(
				new Integer(b.getTopSupportSize()));
		bBottomSize.getSelectionModel().select(new Integer(b.getBottomSize()));
		bTopLength.setText("" + b.getTopLength());
		bTopSupportLength.setText("" + b.getTopSupportLength());
		bBottomLength.setText("" + b.getBottomLength());
		bTopNumOfBars.setText("" + b.getTopNumOfBars());
		bTopSupportNumOfBars.setText("" + b.getTopSupportNumOfBars());
		bBottomNumOfBars.setText("" + b.getBottomNumOfBars());

		bCalcButton.setText("Re-Calculate");
		bCalcButton.setEffect(new ColorAdjust(-0.351, 0, 0, 0));
	}

	// SLABS

	@FXML
	private ListView<String> sList;

	// outputs
	@FXML
	private Label sVolCost;
	@FXML
	private Label sReCost;
	@FXML
	private Label sFormCost;
	@FXML
	private Label sTotalCost;

	// inputs
	@FXML
	private TextField sX;
	@FXML
	private TextField sY;
	@FXML
	private TextField sThick;
	@FXML
	private ChoiceBox<Integer> sXSize;
	@FXML
	private ChoiceBox<Integer> sYSize;
	@FXML
	private TextField sXSpacing;
	@FXML
	private TextField sYSpacing;
	@FXML
	private TextField sXLengthOfBar;
	@FXML
	private TextField sYLengthOfBar;
	@FXML
	private TextField sQuantity;

	// button
	@FXML
	private Button sCalcButton;
	@FXML
	private Button sDelBtn;
	@FXML
	private Button sNewBtn;

	// actions
	@FXML
	private void calcSlabs(ActionEvent e) {
		if (sCalcButton.getText().equals("Re-Calculate")){
			DataUtil.slabs.remove(selectedSKey);
			sOList.remove(selectedSKey);
		}
		
		Slab s = new Slab(Integer.parseInt(sQuantity.getText()),
				Integer.parseInt(sX.getText()), Integer.parseInt(sY.getText()),
				Integer.parseInt(sThick.getText()), sXSize.getSelectionModel()
						.getSelectedItem(), sYSize.getSelectionModel()
						.getSelectedItem(), Integer.parseInt(sXSpacing
						.getText()), Integer.parseInt(sYSpacing.getText()),
				Integer.parseInt(sXLengthOfBar.getText()),
				Integer.parseInt(sYLengthOfBar.getText()));

		sVolCost.setText("" + s.getVolCost());
		sReCost.setText("" + s.getReCost());
		sFormCost.setText("" + s.getFormCost());

		String key = s.getQuantity() + " - " + s.getTotalCost();
		DataUtil.slabs.put(key, s);
		sOList.add(key);
		
		sTotalCost.setText("" + DataUtil.getTotalSCost());

		updateSummary();

		resetS();

	}

	@FXML
	private void delS(ActionEvent e){
		DataUtil.slabs.remove(selectedSKey);
		sOList.remove(selectedSKey);
		resetS();
	}
	
	@FXML
	private void newS(ActionEvent e){
		resetS();
	}
	private void sListSelect(String selected) {
		if (DataUtil.slabs.isEmpty())
			return;

		selectedSKey = selected;
		Slab s = DataUtil.slabs.get(selectedSKey);
		sNewBtn.setVisible(true);
		sDelBtn.setVisible(true);
		
		sVolCost.setText("" + s.getVolCost());
		sReCost.setText("" + s.getReCost());
		sFormCost.setText("" + s.getFormCost());

		sQuantity.setText("" + s.getQuantity());
		sX.setText("" + s.getX());
		sY.setText("" + s.getY());
		sThick.setText("" + s.getThick());
		sXSize.getSelectionModel().select(new Integer(s.getxSize()));
		sYSize.getSelectionModel().select(new Integer(s.getySize()));
		sXSpacing.setText("" + s.getxSpacing());
		sYSpacing.setText("" + s.getySpacing());
		sXLengthOfBar.setText("" + s.getxLengthOfBar());
		sYLengthOfBar.setText("" + s.getyLengthOfBar());

		sCalcButton.setText("Re-Calculate");
		sCalcButton.setEffect(new ColorAdjust(-0.351, 0, 0, 0));
	}

	// SUMMARY

	// outputs
	@FXML
	private Label sumFCost;
	@FXML
	private Label sumCCost;
	@FXML
	private Label sumBCost;
	@FXML
	private Label sumSCost;
	@FXML
	private Label sumTotalCost;

	private void updateSummary() {
		sumFCost.setText("" + DataUtil.getTotalFCost());
		sumCCost.setText("" + DataUtil.getTotalCCost());
		sumBCost.setText("" + DataUtil.getTotalBCost());
		sumSCost.setText("" + DataUtil.getTotalSCost());
		sumTotalCost.setText("" + DataUtil.getTotalCost());
	}

	// resets
	private void resetF() {
		fVolCost.setText("0");
		fReCost.setText("0");
		fFormCost.setText("0");

		fQuantity.clear();
		fBase.clear();
		fDepth.clear();
		fSize.getSelectionModel().clearSelection();
		fSpacing.clear();
		fLengthOfBar.clear();

		fCalcButton.setText("Calculate");
		fCalcButton.setEffect(new ColorAdjust(0, 0, 0, 0));
		
		fNewBtn.setVisible(false);
		fDelBtn.setVisible(false);

	}

	private void resetC() {
		cVolCost.setText("0");
		cReCost.setText("0");
		cFormCost.setText("0");

		cQuantity.clear();
		cHeight.clear();
		cNumOfBars.clear();
		cSize.getSelectionModel().clearSelection();
		cDiameter.clear();
		cWidth.clear();
		cLength.clear();

		cCirc.setSelected(true);
		selectCircular(new ActionEvent());

		cCalcButton.setText("Calculate");
		cCalcButton.setEffect(new ColorAdjust(0, 0, 0, 0));
		
		cNewBtn.setVisible(false);
		cDelBtn.setVisible(false);
	}

	private void resetB() {
		bVolCost.setText("0");
		bReCost.setText("0");
		bFormCost.setText("0");

		bQuantity.clear();
		bX.clear();
		bY.clear();
		bLength.clear();
		bTopSize.getSelectionModel().clearSelection();
		bTopSupportSize.getSelectionModel().clearSelection();
		bBottomSize.getSelectionModel().clearSelection();
		bTopLength.clear();
		bTopSupportLength.clear();
		bBottomLength.clear();
		bTopNumOfBars.clear();
		bTopSupportNumOfBars.clear();
		bBottomNumOfBars.clear();

		bCalcButton.setText("Calculate");
		bCalcButton.setEffect(new ColorAdjust(0, 0, 0, 0));
		
		bNewBtn.setVisible(false);
		bDelBtn.setVisible(false);
	}

	private void resetS() {
		sVolCost.setText("0");
		sReCost.setText("0");
		sFormCost.setText("0");

		sQuantity.clear();
		sX.clear();
		sY.clear();
		sThick.clear();
		sXSize.getSelectionModel().clearSelection();
		sYSize.getSelectionModel().clearSelection();
		sXSpacing.clear();
		sYSpacing.clear();
		sXLengthOfBar.clear();
		sYLengthOfBar.clear();

		sCalcButton.setText("Calculate");
		sCalcButton.setEffect(new ColorAdjust(0, 0, 0, 0));
		
		sNewBtn.setVisible(false);
		sDelBtn.setVisible(false);
	}

}
