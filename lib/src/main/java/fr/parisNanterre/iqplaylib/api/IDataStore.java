package fr.parisNanterre.iqplaylib.api;

import java.util.Optional;

/**
 * Interface générique pour le stockage des données.
 * L'utilisateur peut fournir sa propre implémentation.
 */
public interface IDataStore<K, V> {
    void save(K key, V value);
    Optional<V> load(K key);
    void delete(K key);
}
