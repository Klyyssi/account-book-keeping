package com.thelairofmarkus.markus.loanpaybacktime.domain;

/**
 * Created by markus on 12.2.2016.
 */
public class Person {

    public final String name;
    private static final String allowedCharSeq = "[a-zA-Z ]+";

    private Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() { return name; }

    public static Person fromString(String name) {
        return name.matches(allowedCharSeq) ?
                new Person(name) :
                getDefault();
    }

    public static Person getDefault() {
        return SingletonHolder.DEFAULT;
    }

    private static class SingletonHolder {
        private static final Person DEFAULT = new Person("N/A");
    }
}
