package com.courtalon.springMementoForm.beans;

public class MathUtils implements IMathUtils {
	
	/* (non-Javadoc)
	 * @see com.courtalon.springMementoForm.beans.IMathUtils#carre(double)
	 */
	@Override
	public double carre(double value) {
		return value * value;
	}

	/* (non-Javadoc)
	 * @see com.courtalon.springMementoForm.beans.IMathUtils#cube(double)
	 */
	@Override
	public double cube(double value) {
		return carre(value) * value;
	}
	
	@Override
	public double addition(double value1, double value2) {
		return value1 + value2;
	}

	
}
