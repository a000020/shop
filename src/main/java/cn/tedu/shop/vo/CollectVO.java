package cn.tedu.shop.vo;

import java.io.Serializable;

public class CollectVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer coid;
	private Integer uid;
	private Long gid;
	private String title;
	private Long price;
	private String image;
	public Integer getCoid() {
		return coid;
	}
	public void setCoid(Integer coid) {
		this.coid = coid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coid == null) ? 0 : coid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectVO other = (CollectVO) obj;
		if (coid == null) {
			if (other.coid != null)
				return false;
		} else if (!coid.equals(other.coid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CollectVO [coid=" + coid + ", uid=" + uid + ", gid=" + gid + ", title=" + title + ", price=" + price
				+ ", image=" + image + "]";
	}
	
}
