package com.chalk.salt.api.security.oauth;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.signature.OAuthSignatureMethod;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.exceptions.security.ApplicationAuthenticationException;
import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class OauthCredentialMatcher.
 * 
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
public class OauthCredentialMatcher implements CredentialsMatcher {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** The Constant OAUTH_PROBLEM. */
    public static final String OAUTH_PROBLEM = "oauth_problem";

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken,
     * org.apache.shiro.authc.AuthenticationInfo)
     */
    @Override
    public boolean doCredentialsMatch(final AuthenticationToken token, final AuthenticationInfo info) {
        /*
         * if (true) { System.out.println("VALIDATING MOCK CREDENTIALS"); return true; }
         */
        final OAuthToken oAuthToken = (OAuthToken) token;
        final SimpleAuthenticationInfo authInfo = (SimpleAuthenticationInfo) info;
        final String consumerSecret = (String) authInfo.getCredentials();
        final OAuthCredentials credentials = (OAuthCredentials) oAuthToken.getCredentials();
        final OAuthMessage oauthMessage = new OAuthMessage(credentials.getHttpMethod(), credentials.getUrl(), credentials.getQueryParameters()
            .entrySet());
        final OAuthConsumer oAuthConsumer = new OAuthConsumer(null, consumerSecret, consumerSecret, null);
        final OAuthAccessor oauthAccessor = new OAuthAccessor(oAuthConsumer);
        try {
            final OAuthSignatureMethod signatureMethod = OAuthSignatureMethod.newMethod("HMAC-SHA1", oauthAccessor);
            signatureMethod.validate(oauthMessage);
        } catch (final IOException | OAuthException | URISyntaxException exception) {
            String signature = null;
            try {
                signature = oauthMessage.getSignature();
            } catch (final IOException ioe) {
                logger.error("An error occurred while getting signature from request.", ioe);
            }

            final String errorMessage = String.format(
                "An error occurred while authenticating request using OAuth. OAuth Info:%nHTTP method:%s%nURL:%s%nSignature:%s",
                credentials.getHttpMethod(), credentials.getUrl(), signature);
            final ApplicationAuthenticationException authException = new ApplicationAuthenticationException(errorMessage, exception);
            final ErrorResponse errorResponse = new ErrorResponse();
            if (exception instanceof IOException || exception instanceof URISyntaxException) {
                errorResponse.setErrorCode(ErrorCode.MALFORMED_AUTH_REQUEST);
                errorResponse.setMessage("OAuth authentication request data is not properly constructed");
            } else if (exception instanceof OAuthProblemException) {
                final String oauthErrorMessage = generateOAuthErrorMessage((OAuthProblemException) exception);
                errorResponse.setErrorCode(ErrorCode.AUTHENTICATION_FAILURE);
                errorResponse.setMessage(oauthErrorMessage);
            }
            authException.setErrorResponse(errorResponse);
            throw authException;
        }
        return true;
    }

    /**
     * Generate o auth error message.
     *
     * @param oauthException the oauth exception
     * @return the string
     */
    private String generateOAuthErrorMessage(final OAuthProblemException oauthException) {
        final Map<String, Object> params = oauthException.getParameters();
        final String oauthProblem = (String) params.get(OAUTH_PROBLEM);
        String oauthErrorMessage = "Authentication failed. OAuth Problem:" + oauthProblem;
        if (OAuth.Problems.PARAMETER_ABSENT.equals(oauthProblem)) {
            oauthErrorMessage += " Missing Parameters:" + params.get(OAuth.Problems.OAUTH_PARAMETERS_ABSENT);
        }
        return oauthErrorMessage;
    }
}
