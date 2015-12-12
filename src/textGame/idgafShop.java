package textGame;

public class idgafShop {
	int gold = TextGame.WALLET;
	int buying = TextGame.buying;
	//String[] inven = TextGame.inven;
	int[] idgafShopValue = TextGame.idgafShopValue;
	int[] invenNo = TextGame.invenNo;
	
	public void setInven(int buying){
		if(gold>= idgafShopValue[buying]){
			gold -= idgafShopValue[buying];
			invenNo[buying] += 1;
		}
	}
	
	public int getGold(){
		return gold;
	}
	
	public int getInven(){
		return invenNo[buying];
	}
}
