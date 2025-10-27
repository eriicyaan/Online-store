package validator;

public interface Validator<K, V> {

    V isValid(K obj);
}
