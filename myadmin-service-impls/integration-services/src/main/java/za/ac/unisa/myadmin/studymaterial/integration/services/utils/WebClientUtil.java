package za.ac.unisa.myadmin.studymaterial.integration.services.utils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class WebClientUtil {

	/**
	 * Create an Invocation builder to access the study material service
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static WebTarget getWebClient(boolean serviceTrustAllSsl, String serviceUrl) throws NoSuchAlgorithmException, KeyManagementException {
		ClientBuilder webClientBuilder = ClientBuilder.newBuilder();
		if(serviceTrustAllSsl){
			webClientBuilder.hostnameVerifier((s, sslSession) -> true);
			webClientBuilder.sslContext(trustAllContext());
		}
		return webClientBuilder.build()
			.target(serviceUrl);
	}

	/**
	 * Creates a SSL Context that allows ALL SSL Certificates, even invalid ones to be accepted.
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private static SSLContext trustAllContext() throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext ctx = SSLContext.getInstance("SSL");
		TrustManager[] certs = new TrustManager[]{ new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() { return null; }

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)throws CertificateException {}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		}
		};
		ctx.init(null, certs, new SecureRandom());
		return ctx;
	}
}
