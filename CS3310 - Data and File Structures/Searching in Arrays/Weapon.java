package hw1cs3310_moussalli_012518;

/**
 * This is a Weapon class which holds information about one weapon in the weaponsArray
 * 
 * @author David Moussalli
 *
 */
public class Weapon{
	private String _weaponName,
				   _minStrength,
				   _maxStrength,
				   _rarity,
				   _currentStrength;
	private int _bagNum; 
	
	/**
	 * Constructor for the Weapon class.
	 * 
	 * @param weapon - String array of weapon stats
	 */
	public Weapon(String[] weapon) {
		_weaponName = weapon[0];
		_minStrength = weapon[1];
		_maxStrength = weapon[2];
		_rarity = weapon[3];
		
		setCurStr("null");//Set it to null for now.
		setBagNum(-10); //Set it to -1 for now.
	}

//************************ Get Methods: *******************************************
	/**
	 * @return - returns the weapon's name
	 */
	public String getName() {
		return _weaponName; 
	}
	
	/**
	 * @return - returns the weapon's minimum strength
	 */
	public String getMinStr() {
		return _minStrength; 
	}
	
	/**
	 * @return - returns the weapon's maximum strength
	 */
	public String getMaxStr() {
		return _maxStrength; 
	}
	
	/**
	 * @return - returns the weapon's rarity
	 */
	public String getRarity() {
		return _rarity; 
	}
	
	/**
	 * @return - returns the weapon's current strength
	 */
	public String getCurStr() {
		return _currentStrength; 
	}
	
	public int getBagNum() {
		return _bagNum;
	}

//*************************** Set Methods: **************************************************
	public void setName(String name) {
		this._weaponName = name;
	}
	public void setMinStr(String min) {
		this._minStrength = min;
	}
	public void setMaxStr(String max) {
		this._maxStrength = max;
	}
	public void setRarity(String rarity) {
		this._rarity = rarity;
	}
	public void setCurStr(String curStr) {
		this._currentStrength = curStr;
	}
	public void setBagNum(int num) {
		this._bagNum = num;
	}
	
}
