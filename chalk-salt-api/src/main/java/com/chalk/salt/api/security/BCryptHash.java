package com.chalk.salt.api.security;

import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.chalk.salt.api.util.Utility;

public class BCryptHash extends SimpleHash {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -944525289928803277L;

    /**
     * Instantiates a new b crypt hash.
     *
     * @param algorithmName the algorithm name
     * @param source the source
     * @param salt the salt
     * @param hashIterations the hash iterations
     * @throws CodecException the codec exception
     * @throws UnknownAlgorithmException the unknown algorithm exception
     */
    public BCryptHash(final String algorithmName, final Object source, final Object salt, final int hashIterations) throws CodecException, UnknownAlgorithmException {
        super(algorithmName, source, salt, hashIterations);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.crypto.hash.SimpleHash#hash(byte[], byte[], int)
     */
    @Override
    protected byte[] hash(final byte[] password, final byte[] salt, final int hashIterations) throws UnknownAlgorithmException {
        return Utility.getBCryptHashBytes(new String(password), new String(salt));
    }

}
