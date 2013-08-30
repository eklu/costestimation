package app;

public class Beam {
	private int quantity, volCost, reCost, formCost, totalCost, x, y, length, topSize, topSupportSize, bottomSize, topLength, topSupportLength, bottomLength, topNumOfBars, topSupportNumOfBars, bottomNumOfBars;

	public Beam(){
		quantity = volCost = reCost = formCost = totalCost = x = y = length = topSize = topSupportSize = bottomSize = topLength = topSupportLength = bottomLength = topNumOfBars = topSupportNumOfBars = bottomNumOfBars = 0;
	}

	public Beam(int quantity, int x, int y, int length, int topSize, int topSupportSize, int bottomSize,
			int topLength, int topSupportLength, int bottomLength,
			int topNumOfBars, int topSupportNumOfBars, int bottomNumOfBars) {
		this.quantity = quantity;
		this.x = x;
		this.y = y;
		this.length = length;
		this.topSize = topSize;
		this.topSupportSize = topSupportSize;
		this.bottomSize = bottomSize;
		this.topLength = topLength;
		this.topSupportLength = topSupportLength;
		this.bottomLength = bottomLength;
		this.topNumOfBars = topNumOfBars;
		this.topSupportNumOfBars = topSupportNumOfBars;
		this.bottomNumOfBars = bottomNumOfBars;
		calc();
	}

	private void calc(){
		calcVolCost();
		calcReCost();
		calcFormCost();
		calcTotalCost();
	}
	
	private void calcVolCost(){
		double quantity = DataUtil.convertMMtoM(x)*DataUtil.convertMMtoM(y)*DataUtil.convertMMtoM(length);
		
		volCost = (int) (quantity * DataUtil.costPerM3);
	}
	
	private void calcReCost(){
		double quantity = Math.ceil(topNumOfBars * DataUtil.convertMMtoM(topLength) * DataUtil.sizeFactorMap.get(topSize))
				+ Math.ceil(topSupportNumOfBars * DataUtil.convertMMtoM(topSupportLength) * DataUtil.sizeFactorMap.get(topSupportSize))
				+ Math.ceil(bottomNumOfBars * DataUtil.convertMMtoM(bottomLength) * DataUtil.sizeFactorMap.get(bottomSize));
		
		//adding reinforcement factor
		quantity *= 1 +(DataUtil.reFactor/100);
		
		reCost = (int) (quantity * DataUtil.costPerKg);
	}
	
	private void calcFormCost(){
		double quantity = 2*(DataUtil.convertMMtoM(x)+DataUtil.convertMMtoM(y))*DataUtil.convertMMtoM(length);
		formCost = (int) (quantity * DataUtil.costPerM2);
	}
	
	private void calcTotalCost(){
		totalCost = quantity * (volCost + reCost + formCost);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLength() {
		return length;
	}

	public int getTopSize() {
		return topSize;
	}

	public int getTopSupportSize() {
		return topSupportSize;
	}

	public int getBottomSize() {
		return bottomSize;
	}

	public int getTopLength() {
		return topLength;
	}

	public int getTopSupportLength() {
		return topSupportLength;
	}

	public int getBottomLength() {
		return bottomLength;
	}

	public int getTopNumOfBars() {
		return topNumOfBars;
	}

	public int getTopSupportNumOfBars() {
		return topSupportNumOfBars;
	}

	public int getBottomNumOfBars() {
		return bottomNumOfBars;
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
