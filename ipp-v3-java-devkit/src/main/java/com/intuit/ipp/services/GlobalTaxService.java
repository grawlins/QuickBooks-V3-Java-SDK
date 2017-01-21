package com.intuit.ipp.services;
import java.util.Map;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.data.TaxService;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.interceptors.IntuitInterceptorProvider;
import com.intuit.ipp.interceptors.IntuitMessage;
import com.intuit.ipp.interceptors.RequestElements;
import com.intuit.ipp.net.MethodType;
import com.intuit.ipp.util.Logger;


public class GlobalTaxService {
	
	/**
	 * the logger instance
	 */
	private static final org.slf4j.Logger LOG = Logger.getLogger();

	
	/**
	 * variable context
	 */
	private transient Context context = null;

/**
	 * Hiding the default constructor as Context is always required to function properly
	 */
	@SuppressWarnings("unused")
	protected GlobalTaxService() {
		
	}
	
	/**
	 * Constructor GlobalTaxService
	 * 
	 * @param context
	 *            the context
	 */
	public GlobalTaxService(final Context context) {
		this.context = context;
	}

	/**
	 * Method to retrieve records for the given list of query
	 * 
	 * @param query
	 *            the query string
	 * @return query result
	 * @throws FMSException
	 *             throws FMSException
	 */
	public TaxService addTaxCode(TaxService entity) throws FMSException {

		IntuitMessage intuitMessage = prepareAddTaxCode(entity);

		// execute interceptors
		new IntuitInterceptorProvider().executeInterceptors(intuitMessage);

		// Iterate the IntuitObjects list in QueryResponse and convert to <T> entity 
		TaxService taxServiceResponse = (TaxService) intuitMessage.getResponseElements().getResponse();
		return taxServiceResponse;
	}
	
	private IntuitMessage prepareAddTaxCode(TaxService entity) throws FMSException {
		
		IntuitMessage intuitMessage = new IntuitMessage();
		RequestElements requestElements = intuitMessage.getRequestElements();

		//set the request params
			Map<String, String> requestParameters = requestElements.getRequestParameters();
			requestParameters.put(RequestElements.REQ_PARAM_METHOD_TYPE, MethodType.POST.toString());
			requestElements.setEntity(entity);
			requestElements.setObjectToSerialize(entity);
			requestElements.setContext(context);
			
		return intuitMessage;
	}
	
	}
	