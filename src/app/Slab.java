package app;


public class Slab {
	private int quantity, volCost, reCost, formCost, totalCost, x, y, thick, xSize, ySize, xSpacing, ySpacing, xLengthOfBar, yLengthOfBar;

	private Slab(){
		quantity = volCost = reCost = formCost = totalCost = x = y = thick = xSize = ySize = xSpacing = ySpacing = xLengthOfBar = yLengthOfBar = 0;
	}

	public Slab(int quantity, int x,int y, int thick, int xSize, int ySize, int xSpacing, int ySpacing,
			int xLengthOfBar, int yLengthOfBar) {
		this.quantity = quantity;
		this.x = x;
		this.y = y;
		this.thick = thick;
		this.xSize = xSize;
		this.ySize = ySize;
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
		this.xLengthOfBar = xLengthOfBar;
		this.yLengthOfBar = yLengthOfBar;
		calc();
	}

	private void calc(){
		calcVolCost();
		calcReCost();
		calcFormCost();
		calcTotalCost();
	}
	
	private void calcVolCost(){
		double quantity = DataUtil.convertMMtoM(x)*DataUtil.convertMMtoM(y)*DataUtil.convertMMtoM(thick);
		
		volCost = (int) (quantity * DataUtil.costPerM3);
	}
	
	private void calcReCost(){
		double quantity = (int) Math.ceil(Math.ceil((DataUtil.convertMMtoM(x)/DataUtil.convertMMtoM(xSpacing)) * DataUtil.convertMMtoM(xLengthOfBar) * DataUtil.sizeFactorMap.get(xSize))
			+  (int) Math.ceil(Math.ceil(DataUtil.convertMMtoM(y)/DataUtil.convertMMtoM(ySpacing)) * DataUtil.convertMMtoM(yLengthOfBar) * DataUtil.sizeFactorMap.get(ySize)));
		
		//adding reinforcement factor
		quantity *= 1 +(DataUtil.reFactor/100);
		
		reCost = (int) (quantity * DataUtil.costPerKg);
	}
	
	private void calcFormCost(){
		double quantity = 2*(DataUtil.convertMMtoM(x)+DataUtil.convertMMtoM(y))*DataUtil.convertMMtoM(thick);
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

	public int getThick() {
		return thick;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}

	public int getxSpacing() {
		return xSpacing;
	}

	public int getySpacing() {
		return ySpacing;
	}

	public int getxLengthOfBar() {
		return xLengthOfBar;
	}

	public int getyLengthOfBar() {
		return yLengthOfBar;
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
