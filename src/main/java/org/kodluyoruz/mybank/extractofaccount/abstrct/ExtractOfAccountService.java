package org.kodluyoruz.mybank.extractofaccount.abstrct;

import java.util.Optional;

public interface ExtractOfAccountService<T> {
    T create(T t);

    Optional<T> get(int extractNo);

    T update(T t);
}
