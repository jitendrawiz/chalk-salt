package com.chalk.salt.api.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Hash;

public class BCryptCredentialMatcher extends HashedCredentialsMatcher {

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.authc.credential.HashedCredentialsMatcher#hashProvidedCredentials(java.lang.Object,
     * java.lang.Object, int)
     */
    @Override
    protected Hash hashProvidedCredentials(final Object credentials, final Object salt, final int hashIterations) {
        return new BCryptHash(getHashAlgorithmName(), credentials, salt, hashIterations);
    }

}
