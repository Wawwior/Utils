package me.wawwior.utils.hashing;

import me.wawwior.utils.hashing.hashes.DefaultHashProvider;

public interface Hashing {

    /** @deprecated SHA-1 is outdated and not recommended for cryptographic use in security applications. */
    @Deprecated
    HashProvider SHA_1 = new DefaultHashProvider("SHA-1");

    /** @deprecated All MD algorithms are outdated and not recommended for cryptographic use in security applications. */
    @Deprecated
    interface MD {

        /** @deprecated MD2 is outdated and not recommended for cryptographic use in security applications. */
        @Deprecated
        HashProvider _2 = new DefaultHashProvider("MD2");

        /** @deprecated MD5 is outdated and not recommended for cryptographic use in security applications. */
        @Deprecated
        HashProvider _5 = new DefaultHashProvider("MD5");

    }

    interface SHA_2 {

        HashProvider _224 = new DefaultHashProvider("SHA-224");
        HashProvider _256 = new DefaultHashProvider("SHA-256");
        HashProvider _384 = new DefaultHashProvider("SHA-384");
        HashProvider _512 = new DefaultHashProvider("SHA-512");

    }

    interface SHA_3 {

        HashProvider _224 = new DefaultHashProvider("SHA3-224");
        HashProvider _256 = new DefaultHashProvider("SHA3-256");
        HashProvider _384 = new DefaultHashProvider("SHA3-384");
        HashProvider _512 = new DefaultHashProvider("SHA3-512");

    }

}
