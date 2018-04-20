package za.ac.unisa.myadmin.service.rest.impl.utils;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;

public class RestImplUtils {

	/**
	 * Validates parameters passed to the URI by checking existence of the
	 * allowedParams.
	 *
	 * @param allowedParams
	 *            the set of parameters allowed to be in a URL
	 * @param httpServletRequest
	 *            the http servlet request object
	 * @return true only if all the allowedParams were found, false otherwise
	 * @throws allowedParams
	 *             when allowedParams were found with additional parameters
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
