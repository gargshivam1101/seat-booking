package core.entity;

import java.util.concurrent.atomic.AtomicInteger;

import core.enums.RoomType;

public class Room {

	private static final AtomicInteger count = new AtomicInteger(0);

	private Integer id;

	private RoomType roomType;

	private Integer size;

	private Double regularPrice;

	private User businessOwner;

	public Room(RoomType roomType, Integer size, Double regularPrice, User businessOwner) {
		super();
		this.id = count.incrementAndGet();
		this.roomType = roomType;
		this.size = size;
		this.regularPrice = regularPrice;
		this.businessOwner = businessOwner;
	}

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
	 * @return the roomType
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return the regularPrice
	 */
	public Double getRegularPrice() {
		return regularPrice;
	}

	/**
	 * @param regularPrice the regularPrice to set
	 */
	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}

	/**
	 * @return the businessOwner
	 */
	public User getBusinessOwner() {
		return businessOwner;
	}

	/**
	 * @param businessOwner the businessOwner to set
	 */
	public void setBusinessOwner(User businessOwner) {
		this.businessOwner = businessOwner;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [id=").append(id).append(", roomType=").append(roomType).append(", size=")
				.append(size).append(", regularPrice=").append(regularPrice).append(", businessOwner=")
				.append(businessOwner).append("]");
		return builder.toString();
	}
}
