package priv.utils.bean;

import java.io.Serializable;

import priv.utils.LoggerUtil;
import priv.utils.ObjectUtil;


/** 基类 */
public class BaseBean implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -943902283260604735L;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/** 克隆自己 */
	public BaseBean cloneSelf() {
		try {
			return ObjectUtil.CloneObject(this);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				return (BaseBean) clone();
			} catch (CloneNotSupportedException e1) {
				LoggerUtil.error(this.getClass(), e1);
				return null;
			}
		}
	}
}
