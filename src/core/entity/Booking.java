package core.entity;

import java.util.Date;

public class Booking {

	private Integer id;

	private Seat seat;

	private Date timestamp;

	private UserDetails user;

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
	 * @return the seat
	 */
	public Seat getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the user
	 */
	public UserDetails getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDetails user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Booking [id=").append(id).append(", seat=").append(seat).append(", timestamp=")
				.append(timestamp).append(", user=").append(user).append("]");
		return builder.toString();
	}
}
