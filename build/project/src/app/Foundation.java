package app;

public class Foundation {
	private int quantity, volCost, reCost, formCost, totalCost, base, depth, size, spacing, lengthOfBar;

	private Foundation(){
		quantity = volCost = reCost = formCost = totalCost = base = depth = size = spacing = lengthOfBar = 0;
	}
	
	public Foundation(int quantity, int base, int depth, int size, int spacing, int lengthOfBar) {
		this.quantity = quantity;
		this.base = base;
		this.depth = depth;
		this.size = size;
		this.spacing = spacing;
		this.lengthOfBar = lengthOfBar;
		calc();
	}

	private void calc(){
		calcVolCost();
		calcReCost();
		calcFormCost();
		calcTotalCost();
	}
	
	private void calcVolCost(){
		double quantity = DataUtil.convertMMtoM(base) * DataUtil.convertMMtoM(base) * DataUtil.convertMMtoM(depth);
		
		volCost = (int) (quantity * DataUtil.costPerM3);
	}
	
	private void calcReCost(){
		double quantity = (int) Math.ceil(Math.ceil(DataUtil.convertMMtoM(base)/DataUtil.convertMMtoM(spacing)) * DataUtil.convertMMtoM(lengthOfBar) * DataUtil.sizeFactorMap.get(size) * 2);
		
		//adding reinforcement factor
		quantity *= 1 +(DataUtil.reFactor/100);
		
		reCost = (int) (quantity * DataUtil.costPerKg);
	}
	
	private void calcFormCost(){
		double quantity = DataUtil.convertMMtoM(base) * 4 * DataUtil.convertMMtoM(depth);
		formCost = (int) (quantity * DataUtil.costPerM2);
	}
	
	private void calcTotalCost(){
		totalCost = quantity * (volCost + reCost + formCost);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public int getBase() {
		return base;
	}

	public int getDepth() {
		return depth;
	}

	public int getSize() {
		return size;
	}

	public int getSpacing() {
		return spacing;
	}

	public int getLengthOfBar() {
		return lengthOfBar;
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
