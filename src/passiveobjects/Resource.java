/**
 * 
 */
package passiveobjects;

/**
 * @author lxmonk
 * 
 */
public class Resource {

	String type;
	int amount;

	String getType() {
		return this.type;
	}
	/**
	 * the {@link Resource} constructor
	 * @param aType the {@link Resource} type
	 * @param aAmount the {@link Resource} amount
	 */
	public Resource(String aType, int aAmount) {
		this.type = aType;
		this.amount = aAmount;
	}
	/**
	 * a {@link Resource} constructor with the default amount 1
	 * @param aType the {@link Resource} type
	 */
	public Resource(String aType){
		this(aType, 1);
	}
	
	/**
	 * returns the {@link Resource} amount
	 * @return the {@link Resource} amount
	 */
	public int getAmount() {
		return this.amount;
	}
	/**
	 * Increases the {@link Resource} amount by 1
	 */
	public void incAmount(){
		this.amount++;
	}
	
	/**
	 * Decreases the {@link Resource} amount by 1
	 */
	public void decAmount(){
		this.amount--;
	}

	/**
	 * sets the {@link Resource} amount by  
	 * @param aAmount the new amount of the resouese
	 */
	public void setAmount(int aAmount){
		this.amount = aAmount;
	}
	
	/**
	 * returns the {@link Resource} type/name
	 * @return the type/name
	 */
	public String getName(){
		return this.type;
	}

}
