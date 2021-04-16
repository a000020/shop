package cn.tedu.shop.entity;

public class Collect extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer coid;
	Integer uid;
	Long gid;
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
		Collect other = (Collect) obj;
		if (coid == null) {
			if (other.coid != null)
				return false;
		} else if (!coid.equals(other.coid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Collect [coid=" + coid + ", uid=" + uid + ", gid=" + gid + "]";
	}
	

}
