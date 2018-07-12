package za.ac.unisa.myadmin.service.rest.impl.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;

public class RestImplUtils {

	/**
	 * Validates parameters passed to the URI by checking existence of the
	 * allowedParams.
	 *
	 * @param allowedParams
	 *            the set of parameters allowed to be in a URL
	 * @param uriInfo
	 *            object of UriInfo
	 * @return true only if all the allowedParams were found, false otherwise
	 * @throws InvalidParameterException
	 *             when allowedParams were found with additional parameters
	 */
	public static boolean validateParameters(Set<String> allowedParams, UriInfo uriInfo)
			throws InvalidParameterException {
		for (String param : allowedParams) {
			if (!uriInfo.getQueryParameters().containsKey(param)) {
				return false;
			}
		}

		Set<String> remainingParams = new HashSet<String>(uriInfo.getQueryParameters().keySet());
		remainingParams.removeAll(allowedParams);
		if (!remainingParams.isEmpty()) {
			throw new InvalidParameterException("Not allowed to specify additional parameters (" + remainingParams
					+ ") when specifying parameters (" + allowedParams);
		}

		return true;
	}

	// ****************************************************************************************
	// ****************************************************************************************
	// * Everything below this comment will be deleted once the refactoring of UMA-51 is done *
	// ****************************************************************************************
	// ****************************************************************************************
	
	/**
	 * Validates parameters passed to the URI by checking existence of the
	 * allowedParams.
	 *
	 * @param allowedParams
	 *            the set of parameters allowed to be in a URL
	 * @param httpRequestParameters
	 *            a map of the HttpServletRequest parameters
	 * @return true only if all the allowedParams were found, false otherwise
	 * @throws InvalidParameterException
	 *             when allowedParams were found with additional parameters
	 */
	public static boolean validateParameters(Set<String> allowedParams, Map<String, String[]> httpRequestParameters)
			throws InvalidParameterException {
		for (String param : allowedParams) {
			if (!httpRequestParameters.containsKey(param)) {
				return false;
			}
		}

		Set<String> remainingParams = new HashSet<String>(httpRequestParameters.keySet());
		remainingParams.removeAll(allowedParams);
		if (!remainingParams.isEmpty()) {
			throw new InvalidParameterException("Not allowed to specify additional parameters (" + remainingParams
					+ ") when specifying parameters (" + allowedParams);
		}

		return true;
	}

	/**
	 * Validates parameters passed to the URI by checking existence of the
	 * allowedParams.
	 *
	 * @param allowedParams
	 *            the set of parameters allowed to be in a URL
	 * @param httpServletRequest
	 *            the http servlet request object
	 * @return true only if all the allowedParams were found, false otherwise
	 * @throws InvalidParameterException when allowedParams were found with additional parameters
	 */
	public static boolean validateParameters(Set<String> allowedParams, HttpServletRequest httpServletRequest)
		throws InvalidParameterException {
		for (String param : allowedParams) {
			if (!httpServletRequest.getParameterMap().containsKey(param)) {
				return false;
			}
		}

		Set<String> remainingParams = new HashSet(httpServletRequest.getParameterMap().keySet());
		remainingParams.removeAll(allowedParams);
		if (!remainingParams.isEmpty()) {
			throw new InvalidParameterException("Not allowed to specify additional parameters (" + remainingParams
				+ ") when specifying parameters (" + allowedParams);
		}

		return true;
	}
}
