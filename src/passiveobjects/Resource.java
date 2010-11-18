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
		return type;
	}

	public Resource(String type, int amount) {
		this.type = type;
		this.amount = amount;
	}
	
	public Resource(String type){
		this(type, 1);
	}

	public int getAmount() {
		return this.amount;
	}
	
	public void incAmount(){
		this.amount++;
	}

	public void decAmount(){
		this.amount--;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public String getName(){
		return this.type;
	}

}
