package core.entity;

public class Seat {

	private Integer id;

	private Float price;

	private Boolean available;

	private UserDetails owner;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * @return the available
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * @return the owner
	 */
	public UserDetails getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(UserDetails owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seat [id=").append(id).append(", price=").append(price).append(", available=")
				.append(available).append(", owner=").append(owner).append("]");
		return builder.toString();
	}
}
