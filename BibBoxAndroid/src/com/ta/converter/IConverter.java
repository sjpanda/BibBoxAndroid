package com.ta.converter;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

/**
 * @author Jing SHU
 * @date 05/03/2014
 * @copyright TA Copyright
 * @brief L'interface de converteur qui convert un soap objet (r�cup�r� par l'outil Ksoap2) � un POJO
 */
public interface IConverter<T> {

	public T convertToObject(SoapObject soapObject);
	
	public List<T> convertToListObject(SoapObject soapObject);
}
