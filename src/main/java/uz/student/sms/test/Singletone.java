package uz.student.sms.test;

public final class Singletone {

    private volatile Singletone ojb;

    private Singletone() {
    }

    public Singletone getOjb() {
        if (ojb == null) {
            synchronized (Singletone.class) {
                if (ojb == null) {
                    ojb = new Singletone();
                }
            }
        }
        return ojb;
    }
}
