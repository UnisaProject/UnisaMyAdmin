package za.ac.unisa.myadmin.services.utilities;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;

public class RestServiceUtility {

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

}
