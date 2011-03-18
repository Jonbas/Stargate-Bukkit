package net.TheDgtl.Stargate;

import com.nijiko.coelho.iConomy.iConomy;
import com.nijiko.coelho.iConomy.system.Account;

public class Economy {
	public static boolean useEconomy = false;
	public static String currencyName = "Coins";
	public static iConomy iConomy = null;
	
	public static String probCreditMsg = "Not enough coins";
	
	public static double useCharge = 0;
	public static double createCharge = 0;
	public static double destroyRefund = 0;
	
	public static boolean load() {
		
		return false;
	}
	
	public static double getBalance(String shopOwner) {
		if( useEconomy && iConomy != null) {
			
			Account account = iConomy.getBank().getAccount(shopOwner);
			if(account == null) {
				iConomy.getBank().addAccount(shopOwner);
				account = iConomy.getBank().getAccount(shopOwner);
			}
			double balanceFrom = account.getBalance();
			
			return balanceFrom;
		}
		return 0;
	}

	public static boolean chargePlayer(String shopOwner, double chargeAmount) {
		if( useEconomy && iConomy != null ) {
			if(iConomy == null) return false;
			
			Account account = iConomy.getBank().getAccount(shopOwner);
			if(account == null) {
				iConomy.getBank().addAccount(shopOwner);
				account = iConomy.getBank().getAccount(shopOwner);
			}
			double balanceFrom = account.getBalance();
			double newBalance = balanceFrom - chargeAmount;
			if(newBalance >= 0) {
				account.setBalance(newBalance);
				account.save();
				return true;
			} else {
				return false;
			}
			
		}
		return false;
	}
	
	public static boolean creditPlayer(String shopOwner, double chargeAmount) {
		return chargePlayer(shopOwner, chargeAmount*(-1));
	}

}
