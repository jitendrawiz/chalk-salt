/*******************************************************************************
 * Copyright 2015, Techblue Software Pvt Ltd. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 ******************************************************************************/
package com.chalk.salt.api.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Hash;

/**
 * The Class BCryptCredentialMatcher.
 *
 * @author <a href="mailto:dheeraj.arora@techblue.co.uk">Dheeraj Arora</a>
 */
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
