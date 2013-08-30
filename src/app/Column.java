package app;

public class Column {
	private int quantity, volCost, reCost, formCost, totalCost, height, numOfBars, size, diameter, width, length;
	private boolean circular;
	
	private Column(){
		quantity = volCost = reCost = formCost = totalCost = height = numOfBars = size = diameter = width = length = 0;
		circular = false;
	}

	public Column(int quantity, int height, int numOfBars, int size, int diameter, int width,
			int length, boolean circular) {
		this.quantity = quantity;
		this.height = height;
		this.numOfBars = numOfBars;
		this.size = size;
		this.diameter = diameter;
		this.width = width;
		this.length = length;
		this.circular = circular;
		calc();
	}

	private void calc(){
		calcVolCost();
		calcReCost();
		calcFormCost();
		calcTotalCost();
	}
	
	private void calcVolCost(){
		double quantity = 0;
		if(circular)
			quantity = Math.ceil(Math.pow(DataUtil.convertMMtoM(diameter)/2, 2) * Math.PI * DataUtil.convertMMtoM(height));
		else
			quantity = DataUtil.convertMMtoM(width) * DataUtil.convertMMtoM(length) * DataUtil.convertMMtoM(height);	
		
		volCost = (int) (quantity * DataUtil.costPerM3);
	}
	
	private void calcReCost(){
		double quantity = Math.ceil(numOfBars * DataUtil.convertMMtoM(height) * DataUtil.sizeFactorMap.get(size));
		
		//adding reinforcement factor
		quantity *= 1 +(DataUtil.reFactor/100);
		
		reCost = (int) (quantity * DataUtil.costPerKg);
	}
	
	private void calcFormCost(){
		double quantity = 0;
		if(circular)
			quantity = Math.ceil(DataUtil.convertMMtoM(diameter) * Math.PI * DataUtil.convertMMtoM(height));
		else
			quantity = 2 * (DataUtil.convertMMtoM(width) + DataUtil.convertMMtoM(length)) * DataUtil.convertMMtoM(height);
		
		formCost = (int) (quantity * DataUtil.costPerM2);
	}
	
	private void calcTotalCost(){
		totalCost = quantity * (volCost + reCost + formCost);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public int getHeight() {
		return height;
	}

	public int getNumOfBars() {
		return numOfBars;
	}

	public int getSize() {
		return size;
	}

	public int getDiameter() {
		return diameter;
	}

	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public boolean isCircular() {
		return circular;
	}

	public int getVolCost() {
		return volCost;
	}

	public int getReCost() {
		return reCost;
	}

	public int getFormCost() {
		return formCost;
	}

	public int getTotalCost() {
		return totalCost;
	}
	
}
