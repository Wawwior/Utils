package me.wawwior.utils.hashing;

public interface HashProvider {

    Hash hash();

    Hash salted(Salt salt);

}
