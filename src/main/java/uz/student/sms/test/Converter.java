package uz.student.sms.test;

import java.util.function.Function;

/*
* T is DTO
* R is Entity
* */
public class Converter<D, E> {

    Function<E, D> from; //from entity
    Function<D, E> to; //to entity

    public Converter(Function<D, E> to, Function<E, D> from) {
        this.to = to;
        this.from = from;
    }

    public E to(D d) {
        return to.apply(d);
    }

    public D from(E e) {
        return from.apply(e);
    }
}
