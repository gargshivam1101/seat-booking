package core.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Complaint {
	private static final AtomicInteger count = new AtomicInteger(0);

	private Integer id;

	private String title;

	private String desc;

	public Complaint(String title, String desc) {
		super();
		this.id = count.incrementAndGet();
		this.title = title;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Complaint [id=").append(id).append(", title=").append(title).append(", desc=")
				.append(desc).append("]");
		return builder.toString();
	}
}
